<template>
    <v-row justify="center" class="mb-3 mt-3">
        <v-col cols="6" v-if="step === 1">
        <transition name="fade" appear mode="out-in">
            <DoctorComponent @returnClicked="step = 0" :patient="patient" :doctors="doctors" :date="date"
                             ></DoctorComponent>
        </transition>
        </v-col>
        <v-col cols="4">
        <v-card
            min-height="500px"
            :class="animation"
        >
            <v-card-title>
                New visit
            </v-card-title>
            <v-card-subtitle>In this card you can reserve a visit for your patient</v-card-subtitle>
            <v-card-text>
                <v-divider></v-divider>
                <DatePatientComponent @patientAndDateSelected="switchToDoctorComponent"></DatePatientComponent>
            </v-card-text>
            <v-card-actions v-if="step === 100">
                <v-btn text color="primary" @click="step = 0">Return</v-btn>
            </v-card-actions>
        </v-card>
        </v-col>
    </v-row>
</template>

<script>
import DatePatientComponent from "@/components/Panels/ReceptionPanel/NewVisit/DatePatientComponent";
import DoctorComponent from "@/components/Panels/ReceptionPanel/NewVisit/DoctorComponent";

export default {
    name: "NewVisit",
    components: {DatePatientComponent, DoctorComponent},
    data() {
        return {
            step: 0,
            patient: null,
            date: '',
            doctors: [],
            animation: '',
        }
    },
    methods: {
        switchToDoctorComponent(patient, date, doctors) {
            this.patient = patient
            this.date = date
            this.doctors = doctors
            this.animation = 'transform'
            setTimeout(() => {
                this.step = 1
            }, 1000)
        }
    },
}
</script>

<style scoped>

@keyframes animate-transform {

    50%{
        opacity: 0;
    }
    100%{
        opacity: 100;
    }


}

.fade-leave-active {
    transition: all 3s;
}

.fade-leave-to {
    opacity: 0;
}

.transform {
    animation-name: animate-transform;
    animation-fill-mode: forwards;
    animation-duration: 2s;
}

.test {
    transform: translateX(800px);
    transition: all 1s;
}

</style>