<template>
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

        <RegisterStep1Component
            v-show="step === 1"
            @firstStepComplete="firstStepComplete">
        </RegisterStep1Component>
        <RegisterStep2Component @goBack="goBack"
                                v-show="step === 2"
                                @secondStepComplete="secondStepComplete"
        ></RegisterStep2Component>
        <RegisterStep3Component v-show="step === 3"
                                @imageLoaded="imageLoaded"
                                @goBack="goBack"
                                @thirdStepComplete="thirdStepComplete">
        </RegisterStep3Component>
        <RegisterStep4Component :userData="userData"
                                v-show="step === 4"
                                @fourthStepComplete="fourthStepComplete"
                                :image="image"
                                @goBack="goBack">

        </RegisterStep4Component>
        <MailConfirmComponent v-show="step === 5" :firstName="userData.firstName" :email="userData.email"></MailConfirmComponent>
    </v-container>
</template>

<script>
import RegisterStep1Component from "@/components/Registration/RegisterStep1Component";
import RegisterStep2Component from "@/components/Registration/RegisterStep2Component";
import RegisterStep3Component from "@/components/Registration/RegisterStep3Component";
import RegisterStep4Component from "@/components/Registration/RegisterStep4Component";
import MailConfirmComponent from "@/components/Registration/Confirmation/MailConfirmComponent";
export default {
    data() {
        return {
            step: 1,
            valid: true,
            userData: {},
            image: null,

        }
    },
    created(){

    },

    name: "RegisterComponent",
    components: {RegisterStep2Component, RegisterStep1Component, RegisterStep3Component, RegisterStep4Component, MailConfirmComponent},
    methods: {

        firstStepComplete(form) {
            this.step = 2
            this.userData = Object.assign(this.userData, form)
            console.log(this.userData)
            for(const element in this.userData){
                console.log(element + ':' + this.userData[element])
            }
        },

        secondStepComplete(form) {
            this.step = 3
            this.userData = Object.assign(this.userData, form)
            console.log(this.userData)
        },
        thirdStepComplete(image) {
            this.step = 4
            this.userData = Object.assign(this.userData, image)
            console.log(this.userData)
        },
        fourthStepComplete() {
            this.step = 5
        },

        goBack() {
            this.step--
        },
        imageLoaded(image) {
            this.image = image
            console.log(this.image)
        },

    }

}
</script>

<style scoped>

</style>