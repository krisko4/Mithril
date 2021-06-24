package com.website.demo.patient;

import com.website.demo.address.Address;
import com.website.demo.address.AddressService;
import com.website.demo.user.AppUser;
import com.website.demo.user.AppUserRepository;
import com.website.demo.user.AppUserService;
import com.website.demo.visit.Visit;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@CrossOrigin
@RestController
@Data
@RequiredArgsConstructor
@RequestMapping("patients")
public class PatientController {

    private final PatientService patientService;
    private final AppUserService appUserService;
    private final AddressService addressService;


    @PostMapping
    public void addNewPatient(@RequestBody NewPatientRequest newPatientRequest){

        Address requestAddress = new Address(
                newPatientRequest.getCountry(),
                newPatientRequest.getCity(),
                newPatientRequest.getStreet(),
                newPatientRequest.getFlatNumber(),
                newPatientRequest.getPostCode()
        );
        AppUser appUser = appUserService
                .findById(newPatientRequest.getDoctorId());
        Patient patient = new Patient(
                newPatientRequest.getFirstName(),
                newPatientRequest.getSecondName(),
                newPatientRequest.getLastName(),
                newPatientRequest.getPesel(),
                patientService.birthdayToLocalDate(newPatientRequest.getBirthdate()),
                newPatientRequest.getPhone(),
                newPatientRequest.getEmail(),
                addressService.getAddressAndSaveIfNotExists(requestAddress),
                appUser
        );
        patientService.addNewPatient(patient);
    }

    @GetMapping("{id}")
    public Optional<Patient> getPatient(@PathVariable Long id) {
        return patientService.findPatientById(id);
    }


    @GetMapping
    public List<PatientDto> getPatientsBy(@RequestParam(required = false) String name, @RequestParam(required = false) Long doctorId) {
        return patientService.getPatientsBy(name, doctorId);
    }


    @PatchMapping("{id}/remove-doctor")
    public void removeDoctorForPatient(@PathVariable Long id, @RequestParam Long doctorId) {
        patientService.removeDoctorForPatient(id, doctorId);
    }

    @PatchMapping("{id}/add-doctor")
    public void addDoctorForPatient(@PathVariable Long id, @RequestParam Long doctorId) {
        patientService.addDoctorForPatient(doctorId, id);
    }


    @DeleteMapping("{id}")
    public void deleteById(@PathVariable Long id) {
        patientService.deleteById(id);
    }




}
