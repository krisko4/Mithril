<template>
    <v-main>
        <v-container fill-height>
            <v-row justify="center">
                <v-col cols="7">
                    <v-card elevation="10">
                        <v-card-title>
                            Registration
                        </v-card-title>
                        <v-card-subtitle>
                            Fill all the steps to register your account
                        </v-card-subtitle>
                        <v-card-text>
                            <v-stepper
                                non-linear
                                v-model="step"
                                alt-labels
                                class="elevation-0"
                            >
                                <v-stepper-header>
                                    <v-stepper-step
                                        step="1"
                                        :complete="step > 1"
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
                                <v-stepper-items>
                                    <v-stepper-content step="1">
                                        <Step1
                                            @firstStepComplete="firstStepComplete">
                                        </Step1>
                                    </v-stepper-content>
                                    <v-stepper-content step="2">
                                        <Step2
                                               @secondStepComplete="secondStepComplete"
                                        ></Step2>
                                    </v-stepper-content>
                                    <v-stepper-content step="3">
                                        <Step3
                                            @thirdStepComplete="thirdStepComplete"
                                            :continueClicked="continueClicked"
                                        >
                                        </Step3>
                                    </v-stepper-content>
                                    <v-stepper-content step="4">
                                        <Step4
                                            :userData="userData"
                                            @fourthStepComplete="fourthStepComplete"
                                            :image="image"
                                           >
                                        </Step4>
                                    </v-stepper-content>
                                </v-stepper-items>
                            </v-stepper>
                        </v-card-text>
                        <v-card-actions>
                            <v-btn color="primary" v-show="step > 1" text @click="goBack">
                                Return
                            </v-btn>
                            <v-spacer></v-spacer>
                                <transition name="fade">
                                    <v-btn color="primary" v-show="step === 3" @click="nextStep" text>Continue
                                    </v-btn>
                                </transition>
                        </v-card-actions>
                    </v-card>
                </v-col>
            </v-row>
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
            continueClicked: false
        }
    },

    name: "RegisterComponent",
    components: {Step2, Step1, Step3, Step4, MailConfirmComponent},
    methods: {

        firstStepComplete(form) {
            this.step = 2
            this.userData = Object.assign(this.userData, form)
        },

        secondStepComplete(form) {
            this.step = 3
            this.userData = Object.assign(this.userData, form)
        },
        thirdStepComplete(image) {
            this.step = 4
            if (image) {
                this.userData = Object.assign(this.userData, {'image': image})
            }
        },
        fourthStepComplete() {
            this.step = 5
        },

        goBack() {
            this.step--
        },

        nextStep(){
            this.continueClicked = !this.continueClicked
        }


    }

}
</script>

<style scoped>

</style>