package com.website.demo.visit;

import com.website.demo.visit.request.FinishedVisitRequest;
import com.website.demo.visit.request.NewUnfinishedVisitRequest;
import com.website.demo.visit.response.AvailableHoursResponse;
import com.website.demo.visit.response.VisitResponse;
import lombok.Data;
import org.springframework.web.bind.annotation.*;

import java.time.LocalTime;
import java.util.List;

@CrossOrigin
@RestController
@Data
public class VisitController {

    private final VisitService visitService;



    @PostMapping("visits")
    public void addNewUnfinishedVisit(@RequestBody NewUnfinishedVisitRequest newUnfinishedVisitRequest){
        visitService.addNewUnfinishedVisit(
                newUnfinishedVisitRequest.getPatientId(),
                newUnfinishedVisitRequest.getDoctorId(),
                newUnfinishedVisitRequest.getDate(),
                newUnfinishedVisitRequest.getDuration()
        );
    }

    @PutMapping("visits")
    public void saveFinishedVisit(@RequestBody FinishedVisitRequest finishedVisitRequest) {
        visitService.saveFinishedVisit(
                finishedVisitRequest.getDate(),
                finishedVisitRequest.getDoctorId(),
                finishedVisitRequest.getPatientId(),
               // finishedVisitRequest.getDuration(),
                finishedVisitRequest.getInterview(),
                finishedVisitRequest.getMedicationIds(),
                finishedVisitRequest.getReferrals(),
                finishedVisitRequest.getResearch()
                );
    }

    @GetMapping("doctors/{id}/visit-hours")
    @ResponseBody
    public AvailableHoursResponse getAvailableVisitHoursByDate(@PathVariable Long id,
                                                               @RequestParam String date){
        return visitService.getAvailableVisitHoursForDoctorByDate(date, id);
    }

    @GetMapping("doctors/{id}/visits")
    @ResponseBody
    public List<VisitResponse> getVisitsForDoctorBy(@PathVariable Long id,
                                                    @RequestParam(required = false) String date,
                                                    @RequestParam(required = false) Long patient_id,
                                                    @RequestParam(required = false) Boolean finished) {
        return visitService.getFullVisitDataForDoctorBy(id, date, patient_id, finished);
    }

    @GetMapping("patients/{id}/visits")
    public List<VisitResponse> getAllVisitsForPatient(@PathVariable Long id,
                                                      @RequestParam(required = false) Boolean finished){
        return visitService.getFullVisitDataForPatient(id, finished);
    }






}
