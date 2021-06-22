<template>
    <v-container fill-height>
    <v-row justify="center" class="mb-3 mt-3">
        <v-col lg="7" cols="12" v-if="step === 1">
        <transition name="fade" appear mode="out-in">
            <DoctorComponent @returnClicked="step = 0" :key="date" :patient="patient" :doctors="doctors" :date="date"
                             ></DoctorComponent>
        </transition>
        </v-col>
        <v-col lg="5" cols="12">
        <v-card
            height="100%"
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
        </v-card>
        </v-col>
    </v-row>
    </v-container>
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
            cols: 5,
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
                this.cols = 4
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

.fade-leave-active, .fade-enter-active {
    transition: all .5s;
}

.fade.enter, .fade-leave-to {
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