package com.website.demo.visit;

//import com.website.demo.doctor.DoctorRepository;

import com.website.demo.dispensary.Dispensary;
import com.website.demo.dispensary.DispensaryRepository;
import com.website.demo.dispensary.DispensaryService;
import com.website.demo.examination.Examination;
import com.website.demo.examination.ExaminationRepository;
import com.website.demo.medication.Medication;
import com.website.demo.medication.MedicationRepository;
import com.website.demo.medication.MedicationRequest;
import com.website.demo.patient.Patient;
import com.website.demo.patient.PatientRepository;
import com.website.demo.prescription.Prescription;
import com.website.demo.prescription.PrescriptionRepository;
import com.website.demo.referral.Referral;
import com.website.demo.referral.ReferralRepository;
import com.website.demo.referral.ReferralRequest;
import com.website.demo.schedule.Schedule;
import com.website.demo.schedule.ScheduleService;
import com.website.demo.specialization.Specialization;
import com.website.demo.user.AppUser;
import com.website.demo.user.AppUserRepository;
import lombok.Data;
import org.apache.tomcat.jni.Local;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

@Service
@Data
public class VisitService {

    private final VisitRepository visitRepository;
    private final ScheduleService scheduleService;
    private final PatientRepository patientRepository;
    private final AppUserRepository appUserRepository;
    private final DispensaryRepository dispensaryRepository;
    private final ReferralRepository referralRepository;
    private final MedicationRepository medicationRepository;
    private final ExaminationRepository examinationRepository;
    private final PrescriptionRepository prescriptionRepository;



    public List<Visit> getAllVisits() {
        return visitRepository.findAll();
    }



    public List<LocalTime> getAvailableVisitHoursForDoctorByDate(String date, Long doctor_id) {
        Schedule schedule = scheduleService.findSchedulesForDoctorBy(date, doctor_id).get(0);
        LocalTime startHour = LocalTime.parse(schedule.getStartHour());
        LocalTime endHour = LocalTime.parse(schedule.getEndHour());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.ENGLISH);
        LocalDate localDate = LocalDate.parse(date, formatter);
        List<Visit> visitList = visitRepository.findAllVisitsForOneDoctorByDate(localDate, doctor_id);
        List<LocalTime> visitHourList = new ArrayList<>();
        if (!visitList.isEmpty()) {
            for (Visit visit : visitList) {
                int hour = visit.getDate().getHour();
                int minute = visit.getDate().getMinute();
                LocalTime visitHour = LocalTime.of(hour, minute);
                visitHourList.add(visitHour);
            }
        }
        List<LocalTime> hourList = new ArrayList<>();
        while (startHour.isBefore(endHour)) {
            if (!visitHourList.contains(startHour)) {
                hourList.add(startHour);
            }
            startHour = startHour.plusMinutes(20);
        }
        return hourList;
    }

    public List<Visit> getAllVisitsForPatient(Long patientID, Boolean finished) {
        if(finished != null){
            return visitRepository.findByPatient_idAndFinished(patientID, finished);
        }
        return visitRepository.findByPatient_id(patientID);


    }



    public List<Visit> getVisitsForDoctorBy(Long doctorId,
                                            String date,
                                            Long patientId,
                                            Boolean finished) {
        if (date != null && doctorId != null) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.ENGLISH);
            LocalDate localDate = LocalDate.parse(date, formatter);
            if(finished != null){
                return visitRepository.findAllVisitsForOneDoctorByDateAndFinished(localDate, doctorId, finished);
            }
            return visitRepository.findAllVisitsForOneDoctorByDate(localDate, doctorId);


        }
        if (patientId != null && doctorId != null) {
            if(finished != null){
                return visitRepository.findByPatient_idAndDoctor_idAndFinished(patientId, doctorId, finished);
            }
            return visitRepository.findByPatient_idAndDoctor_id(patientId, doctorId);
        }
        return visitRepository.findAllByDoctorId(doctorId);
    }



    public void addVisit(String date,
                         Long doctorId,
                         Long patientId,
                         int duration,
                         String interview,
                         Long[] medicationIds,
                         ReferralRequest[] referrals,
                         String research) {
        Patient patient = patientRepository.findById(patientId).get();
        AppUser doctor = appUserRepository.findById(doctorId).get();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm", Locale.ENGLISH);
        LocalDateTime localDateTime = LocalDateTime.parse(date, formatter);
        Example<Visit> example = Example.of(new Visit(patient, doctor, localDateTime, duration));
        Visit visit = visitRepository.findOne(example).get();
        Examination examination = new Examination(interview, research, visit);
        examinationRepository.save(examination);


        for(ReferralRequest referralRequest : referrals){
            Dispensary dispensary = dispensaryRepository.findByNameAndSpecializationName(referralRequest.getDispensary(), referralRequest.getSpeciality()).get();
            int priority = 0;
            if(referralRequest.getPriority().equals("Urgent")){
                priority = 1;
            }
            Referral referral = new Referral(
                    dispensary,
                    visit,
                    referralRequest.getReason(),
                    priority
                    );
            referralRepository.save(referral);
        }

        Prescription prescription = new Prescription("1111", visit);
        prescriptionRepository.save(prescription);
        for(Long medicationId : medicationIds){
             medicationRepository.addMedicationToPrescription(prescription.getId(), medicationId);
        }
        visit.setFinished(true);
        visitRepository.save(visit);


    }



}
