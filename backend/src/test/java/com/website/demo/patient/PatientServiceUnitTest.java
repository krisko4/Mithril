package com.website.demo.patient;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


class PatientServiceUnitTest {


    @Test
    public void testFindingById(){
        PatientRepository patientRepository = Mockito.mock(PatientRepository.class);
        PatientService patientService = Mockito.mock(PatientService.class);
        assertThat(patientService.findPatientById(1L)).isNotNull();
    }

    @Test
    public void testPatientSaving(){

    }


}