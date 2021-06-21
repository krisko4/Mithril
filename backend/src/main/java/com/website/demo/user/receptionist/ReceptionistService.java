package com.website.demo.user.receptionist;

import com.website.demo.user.AppUserRepository;
import lombok.Data;
import org.springframework.stereotype.Service;

@Service
@Data
public class ReceptionistService {

    private final AppUserRepository appUserRepository;

}
