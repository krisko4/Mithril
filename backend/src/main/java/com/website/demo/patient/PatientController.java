package com.website.demo.patient;

import com.website.demo.visit.Visit;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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




    @GetMapping("{id}")
    public Optional<Patient> getPatient(@PathVariable Long id) {
        return patientService.findPatientById(id);
    }


    //  @GetMapping
    //  public List<PatientDto> getPatientsByChar(@RequestParam String character){
    //      return patientService.getPatientsByChar(character);
    //   }

    @GetMapping
    public List<PatientDto> getPatientsBy(@RequestParam(required = false) String name, @RequestParam(required = false) Long doctor_id) {

        return patientService.getPatientsBy(name, doctor_id)
                .stream().map(PatientDto::from)
                .collect(Collectors.toList());


    }

//   @GetMapping
    //   public List<PatientDto> getPatientsForDoctor(@RequestParam Long doctorID){
    //       return patientService.getPatientsForDoctor(doctorID).stream().map(PatientDto::from).collect(Collectors.toList());
    //   }

    @PatchMapping("removeDoctor")
    public void removeDoctorForPatient(@RequestBody DoctorPatientIDRequest doctorPatientIDRequest) {
        patientService.removeDoctorForPatient(doctorPatientIDRequest.getDoctorID(), doctorPatientIDRequest.getPatientID());
    }

    @PatchMapping("{id}")
    public PatientDto addDoctorForPatient(@PathVariable Long id, @RequestParam Long doctor_id) {
        return PatientDto.from(patientService.addDoctorForPatient(doctor_id, id));
    }

    @PostMapping
    public void addPatientList() {
        patientService.addPatientList();
    }

    @PostMapping("/add")
    public void addPatient() {
        patientService.addPatient();
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        patientService.deleteById(id);
    }




}
