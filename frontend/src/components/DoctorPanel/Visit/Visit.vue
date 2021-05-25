<template>
    <v-container class="mt-3 mb-3">
    <v-row justify="center">
    <v-col cols="7">
        <v-card>
            <v-card-title>Visit manager</v-card-title>
            <v-card-subtitle class="mt-1">In this card you can go through the entire process of your visit</v-card-subtitle>
            <v-divider></v-divider>
            <v-card-text>
            <v-stepper
                v-model="step">
                <v-stepper-header>
                    <v-stepper-step step="1" :complete="step > 1"
                    >
                        Interview
                    </v-stepper-step>
                    <v-divider></v-divider>

                    <v-stepper-step step="2" :complete="step > 2"
                    >
                        Research
                    </v-stepper-step>

                    <v-divider></v-divider>

                    <v-stepper-step step="3" :complete="step > 3">
                        Referrals
                    </v-stepper-step>
                    <v-divider></v-divider>
                    <v-stepper-step step="4" :complete="step > 4"
                    >
                        Medicine
                    </v-stepper-step>
                </v-stepper-header>
            </v-stepper>
            </v-card-text>
            <v-card-text>
                <Step1
                    v-show="step === 1"
                    @firstStepComplete="firstStepComplete"
                    @goBack="step--"
                >
                </Step1>
                <transition name="fade">
                <Step2
                    v-show="step === 2"
                    @secondStepComplete="secondStepComplete"
                    @goBack="step--"
                >
                </Step2>
                </transition>

                <Step3
                    v-show="step === 3"
                    @thirdStepComplete="thirdStepComplete"
                    @goBack="step--"
                    :patientData="patientData"
                    :doctorFullName="visitData.doctorFullName"
                >
                </Step3>

                <Step4
                    @visitFinished="closeVisitCard"
                    :visitData="visitData"
                    v-show="step === 4"
                    @fourthStepComplete="fourthStepComplete"
                    @goBack="step--"
                ></Step4>
            </v-card-text>
        </v-card>

    </v-col>
<v-col cols="4">
    <PatientHistory :patientData="patientData"></PatientHistory>
</v-col>
    </v-row>
    </v-container>
</template>

<script>
import PatientHistory from "@/components/DoctorPanel/MyPatients/PatientHistory/PatientHistory";
import Step1 from "@/components/DoctorPanel/Visit/Step1";
import Step2 from "@/components/DoctorPanel/Visit/Step2";
import Step3 from "@/components/DoctorPanel/Visit/Step3";
import Step4 from "@/components/DoctorPanel/Visit/Step4";

export default {
    name: "Visit",
    components: {Step4, PatientHistory, Step1, Step2, Step3},
    props: {
        patientData: Object,
        selectedVisitDate: String,
        selectedVisitDuration: Number
    },
    data(){
        return {
            step: 1,
            visitData: {
                date: this.selectedVisitDate,
                duration: this.selectedVisitDuration,
                doctorImg: localStorage.getItem('imageName'),
                interview: '',
                research: '',
                referrals: [],
                medications: [],
                patient: this.patientData,
                doctorFullName: localStorage.getItem('firstName') + ' ' +  localStorage.getItem('secondName') + ' ' +  localStorage.getItem('lastName'),
            }
        }
    },
    methods: {
        convertDate(date){
            return (new Date(date - (date).getTimezoneOffset() * 60000)).toISOString().substr(0,10)
        },
        firstStepComplete(interview) {
            this.step = 2
            this.visitData.interview = interview

        },

        secondStepComplete(research) {
            this.step = 3
            this.visitData.research = research
        },
        thirdStepComplete(referrals) {
            this.step = 4
            this.visitData.referrals = referrals
        },
        fourthStepComplete() {
            this.step = 5
        },
        closeVisitCard(){
            this.$emit('visitFinished')
        }
    }
}
</script>

<style scoped>

</style>