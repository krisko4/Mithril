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
                    @goBack="goBack"
                >
                </Step1>
                <Step2
                    v-show="step === 2"
                    @secondStepComplete="secondStepComplete"
                    @goBack="goBack"
                >
                </Step2>
                <Step3
                    v-show="step === 3"
                    @thirdStepComplete="thirdStepComplete"
                    @goBack="goBack"
                    :patientData="patientData"
                >
                </Step3>

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

export default {
    name: "Visit",
    components: {PatientHistory, Step1, Step2, Step3},
    props: {
        patientData: Object
    },
    data(){
        return {
            step: 1
        }
    },
    methods: {
        firstStepComplete() {
            this.step = 2

        },

        secondStepComplete() {
            this.step = 3

        },
        thirdStepComplete() {
            this.step = 4

        },
        fourthStepComplete() {
            this.step = 5
        },
        goBack() {
            this.step--
        },
    }
}
</script>

<style scoped>

</style>