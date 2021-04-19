package com.website.demo.registration;

import com.github.javafaker.App;
import com.website.demo.address.Address;
import com.website.demo.address.AddressRepository;
import com.website.demo.authorities.AppUserRole;
import com.website.demo.registration.email.EmailSender;
import com.website.demo.registration.token.ConfirmationToken;
import com.website.demo.registration.token.ConfirmationTokenService;
import com.website.demo.user.AppUser;
import com.website.demo.user.AppUserRepository;
import com.website.demo.user.AppUserService;
import com.website.demo.validation.EmailValidator;
import com.website.demo.validation.PasswordValidator;
import lombok.AllArgsConstructor;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.Multipart;
import java.io.File;
import java.io.FileOutputStream;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Service
@AllArgsConstructor
public class RegistrationService {

    private static final String EMAIL_NOT_VALID_MSG = "E-mail %s is invalid";
    private static final String PASSWORD_NOT_VALID_MSG = "Password %s is invalid";
    private final EmailValidator emailValidator;
    private final PasswordValidator passwordValidator;
    private final AppUserService appUserService;
    private final AppUserRepository appUserRepository;
    private final AddressRepository addressRepository;
    private final ConfirmationTokenService confirmationTokenService;
    private final EmailSender emailSender;
    private final String CONFIRMATION_LINK = "http://localhost:8081/confirm/";


    public LocalDate birthdayToLocalDate(String birthdateString){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return LocalDate.parse(birthdateString, formatter);
    }

    public String register(AppUser appUser, MultipartFile image) {

        // resending email confirmation message for existing user
        if(appUserRepository.existsByEmail(appUser.getEmail())){
            appUser = appUserRepository.findByEmail(appUser.getEmail()).get();
            if(!appUser.isEnabled()){
                resendEmail(appUser.getEmail(), CONFIRMATION_LINK);
            }
        }

        boolean isValidEmail = emailValidator.test(appUser.getEmail());
        boolean isValidPassword = passwordValidator.test(appUser.getPassword());
        if (!isValidEmail) {
            throw new IllegalStateException(String.format(EMAIL_NOT_VALID_MSG, appUser.getEmail()));
        }
        if (!isValidPassword) {
            throw new IllegalStateException(String.format(PASSWORD_NOT_VALID_MSG, appUser.getEmail()));
        }

 /*       Address requestAddress = new Address(
                request.getCountry(),
                request.getCity(),
                request.getStreet(),
                request.getFlatNumber(),
                request.getPostCode()
        );
*/
        /*
        Address address;
        boolean addressExists = addressRepository.exists(Example.of(requestAddress));
        if (addressExists) {
            address = addressRepository.findOne(Example.of(requestAddress)).get();
        } else {
            address = requestAddress;
            addressRepository.save(address);
        }*/
      //  Address address = addressRepository.save(requestAddress);

   /*     String birthdateString = request.getBirthdate();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate birthdate = LocalDate.parse(birthdateString, formatter);

        AppUser appUser =   new AppUser(
                request.getFirstName(),
                request.getSecondName(),
                request.getLastName(),
                request.getEmail(),
                request.getPassword(),
                request.getPhone(),
                address,
                birthdate,
                request.getRole()
        );*/

        String token = appUserService.signUp(appUser);

        if(image != null) {
            // full path to project directory
            String projectDirectory = new File("").getAbsolutePath();
            // directory where we want our files to be stored
            String fileDirectory = projectDirectory + "/src/main/images/";
            String originalFilename = image.getOriginalFilename();
            String filePath = fileDirectory + originalFilename;
            File dest = new File(filePath);
            try {
                image.transferTo(dest);
            } catch (Exception e) {
                throw new RuntimeException("Failed to transfer file to destinated directory.");
            }
            appUserService.setImageName(originalFilename, appUser.getEmail());

        }

        String link = "http://localhost:8081/confirm/" + token;
        emailSender.send(appUser.getEmail(), buildEmail(appUser.getFirstName(), link));
        return token;
    }

    @Transactional
    public String confirmToken(String token) {
        ConfirmationToken confirmationToken = confirmationTokenService.getToken(token)
                .orElseThrow(() -> new IllegalStateException("Token not found"));

        if (confirmationToken.getConfirmedAt() != null) {
            throw new IllegalStateException("E-mail already confirmed");
        }

        LocalDateTime expiredAt = confirmationToken.getExpiresAt();

        if (expiredAt.isBefore(LocalDateTime.now())) {
            throw new IllegalStateException("Token expired");
        }

        confirmationTokenService.setConfirmedAt(token);
        appUserService.enableAppUser(confirmationToken.getAppUser().getEmail());

        return "confirmed";
    }

    private String buildEmail(String name, String link) {
        return "<div style=\"font-family:Helvetica,Arial,sans-serif;font-size:16px;margin:0;color:#0b0c0c\">\n" +
                "\n" +
                "<span style=\"display:none;font-size:1px;color:#fff;max-height:0\"></span>\n" +
                "\n" +
                "  <table role=\"presentation\" width=\"100%\" style=\"border-collapse:collapse;min-width:100%;width:100%!important\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\">\n" +
                "    <tbody><tr>\n" +
                "      <td width=\"100%\" height=\"53\" bgcolor=\"#0b0c0c\">\n" +
                "        \n" +
                "        <table role=\"presentation\" width=\"100%\" style=\"border-collapse:collapse;max-width:580px\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" align=\"center\">\n" +
                "          <tbody><tr>\n" +
                "            <td width=\"70\" bgcolor=\"#0b0c0c\" valign=\"middle\">\n" +
                "                <table role=\"presentation\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse\">\n" +
                "                  <tbody><tr>\n" +
                "                    <td style=\"padding-left:10px\">\n" +
                "                  \n" +
                "                    </td>\n" +
                "                    <td style=\"font-size:28px;line-height:1.315789474;Margin-top:4px;padding-left:10px\">\n" +
                "                      <span style=\"font-family:Helvetica,Arial,sans-serif;font-weight:700;color:#ffffff;text-decoration:none;vertical-align:top;display:inline-block\">Confirm your email</span>\n" +
                "                    </td>\n" +
                "                  </tr>\n" +
                "                </tbody></table>\n" +
                "              </a>\n" +
                "            </td>\n" +
                "          </tr>\n" +
                "        </tbody></table>\n" +
                "        \n" +
                "      </td>\n" +
                "    </tr>\n" +
                "  </tbody></table>\n" +
                "  <table role=\"presentation\" class=\"m_-6186904992287805515content\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse;max-width:580px;width:100%!important\" width=\"100%\">\n" +
                "    <tbody><tr>\n" +
                "      <td width=\"10\" height=\"10\" valign=\"middle\"></td>\n" +
                "      <td>\n" +
                "        \n" +
                "                <table role=\"presentation\" width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse\">\n" +
                "                  <tbody><tr>\n" +
                "                    <td bgcolor=\"#1D70B8\" width=\"100%\" height=\"10\"></td>\n" +
                "                  </tr>\n" +
                "                </tbody></table>\n" +
                "        \n" +
                "      </td>\n" +
                "      <td width=\"10\" valign=\"middle\" height=\"10\"></td>\n" +
                "    </tr>\n" +
                "  </tbody></table>\n" +
                "\n" +
                "\n" +
                "\n" +
                "  <table role=\"presentation\" class=\"m_-6186904992287805515content\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse;max-width:580px;width:100%!important\" width=\"100%\">\n" +
                "    <tbody><tr>\n" +
                "      <td height=\"30\"><br></td>\n" +
                "    </tr>\n" +
                "    <tr>\n" +
                "      <td width=\"10\" valign=\"middle\"><br></td>\n" +
                "      <td style=\"font-family:Helvetica,Arial,sans-serif;font-size:19px;line-height:1.315789474;max-width:560px\">\n" +
                "        \n" +
                "            <p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\">Hi " + name + ",</p><p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\"> Thank you for registering. Please click on the below link to activate your account: </p><blockquote style=\"Margin:0 0 20px 0;border-left:10px solid #b1b4b6;padding:15px 0 0.1px 15px;font-size:19px;line-height:25px\"><p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\"> <a href=\"" + link + "\">Activate Now</a> </p></blockquote>\n Link will expire in 15 minutes. <p>See you soon</p>" +
                "        \n" +
                "      </td>\n" +
                "      <td width=\"10\" valign=\"middle\"><br></td>\n" +
                "    </tr>\n" +
                "    <tr>\n" +
                "      <td height=\"30\"><br></td>\n" +
                "    </tr>\n" +
                "  </tbody></table><div class=\"yj6qo\"></div><div class=\"adL\">\n" +
                "\n" +
                "</div></div>";
    }

    public String resendEmail(String email, String firstName) {
        AppUser appUser = appUserRepository.findByEmail(email).get();
        String token = UUID.randomUUID().toString();
        ConfirmationToken confirmationToken = new ConfirmationToken(
                token,
                LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(10),
                appUser
        );
        confirmationTokenService.saveConfirmationToken(confirmationToken);
        String link = CONFIRMATION_LINK + token;
        emailSender.send(email, buildEmail(firstName, link));
        return token;
    }
}
