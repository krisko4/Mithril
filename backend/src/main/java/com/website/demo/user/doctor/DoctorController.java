package com.website.demo.user.doctor;

import com.website.demo.user.FriendDto;
import lombok.Data;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Data
@CrossOrigin
public class DoctorController {

    private final DoctorService doctorService;

    @GetMapping("doctors")
    public List<DoctorDto> getDoctorsBy(@RequestParam(required = false) String date) {
        return doctorService.getDoctorsBy(date);
    }

    @GetMapping("doctors/except-for/{id}")
    public List<FriendDto> getDoctorsBy(@PathVariable Long id) {
        return doctorService.getDoctorsExceptForOne(id);
    }

}
