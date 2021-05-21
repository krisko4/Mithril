<template>
    <v-main style="background-color: whitesmoke">
    <v-container>
        <v-row justify="center">
            <v-col cols="12" sm="8">
                <v-stepper non-linear
                           v-model="step"
                           alt-labels
                           class="mt-12"
                >
                    <v-stepper-header>
                        <v-stepper-step step="1" :complete="step > 1"
                        >
                            Login credentials
                        </v-stepper-step>

                        <v-divider></v-divider>

                        <v-stepper-step step="2" :complete="step > 2"
                        >
                            Personal data
                        </v-stepper-step>

                        <v-divider></v-divider>

                        <v-stepper-step step="3" :complete="step > 3">
                            Profile image
                            <small>Optional</small>
                        </v-stepper-step>
                        <v-divider></v-divider>
                        <v-stepper-step step="4" :complete="step > 4"
                        >
                            Specialization
                        </v-stepper-step>
                    </v-stepper-header>
                </v-stepper>
            </v-col>
        </v-row>

        <Step1
            v-show="step === 1"
            @firstStepComplete="firstStepComplete">
        </Step1>
        <Step2 @goBack="goBack"
                                v-show="step === 2"
                                @secondStepComplete="secondStepComplete"
        ></Step2>
        <Step3 v-show="step === 3"
                                @goBack="goBack"
                                @thirdStepComplete="thirdStepComplete">
        </Step3>
        <Step4 :userData="userData"
                                v-show="step === 4"
                                @fourthStepComplete="fourthStepComplete"
                                :image="image"
                                @goBack="goBack">

        </Step4>
        <MailConfirmComponent v-show="step === 5" :email="userData.email"></MailConfirmComponent>
    </v-container>
    </v-main>
</template>

<script>
import Step1 from "@/components/Registration/Step1";
import Step2 from "@/components/Registration/Step2";
import Step3 from "@/components/Registration/Step3";
import Step4 from "@/components/Registration/Step4";
import MailConfirmComponent from "@/components/Registration/Confirmation/MailConfirmation";
export default {
    data() {
        return {
            step: 1,
            valid: true,
            userData: {},
            image: null,
        }
    },

    name: "RegisterComponent",
    components: {Step2, Step1, Step3, Step4, MailConfirmComponent},
    methods: {

        firstStepComplete(form) {
            this.step = 2
            this.userData = Object.assign(this.userData, form)
            console.log(this.userData)
        },

        secondStepComplete(form) {
            this.step = 3
            this.userData = Object.assign(this.userData, form)
            console.log(this.userData)
        },
        thirdStepComplete(image) {
            this.step = 4
            console.log(image)
            if(image != null) {
                this.userData = Object.assign(this.userData, {'image': image})
            }
            console.log(this.userData)
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