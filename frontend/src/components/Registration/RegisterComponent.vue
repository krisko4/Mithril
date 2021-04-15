<template>
    <v-container>
        <v-row justify="center">
            <v-col cols="6">
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
            v-if="!step1Completed"
            @firstStepComplete="firstStepComplete">
        </RegisterStep1Component>
        <RegisterStep2Component @goBack="goBack"
                                v-else-if="!step2Completed"
                                @secondStepComplete="secondStepComplete"
        ></RegisterStep2Component>
        <RegisterStep3Component v-else-if="!step3Completed"
                                @imageLoaded="imageLoaded"
                                @goBack="goBack"
                                @thirdStepComplete="thirdStepComplete">
        </RegisterStep3Component>
        <RegisterStep4Component :userData="userData" v-else @goBack="goBack"></RegisterStep4Component>
    </v-container>
</template>

<script>
import RegisterStep1Component from "@/components/Registration/RegisterStep1Component";
import RegisterStep2Component from "@/components/Registration/RegisterStep2Component";
import RegisterStep3Component from "@/components/Registration/RegisterStep3Component";
import RegisterStep4Component from "@/components/Registration/RegisterStep4Component";

export default {
    data() {
        return {
            step1Completed: false,
            step2Completed: false,
            step3Completed: false,
            step4Completed: false,
            step: 1,
            valid: true,
            userData: {},
        }
    },
    name: "RegisterComponent",
    components: {RegisterStep2Component, RegisterStep1Component, RegisterStep3Component, RegisterStep4Component},
    methods: {

        firstStepComplete(nextStep, form) {
            this.step1Completed = true
            this.step = nextStep
            this.userData = Object.assign(this.userData, form)
            console.log(this.userData)
        },

        secondStepComplete(nextStep, form) {
            this.step2Completed = true
            this.step = nextStep
            this.userData = Object.assign(this.userData, form)
            console.log(this.userData)
        },
        thirdStepComplete(nextStep) {
            this.step3Completed = true
            this.step = nextStep
        },

        goBack(step) {
            this.step = step
            if (step === 1) {
                this.step1Completed = false
            } else if (step === 2) {
                this.step2Completed = false
            } else if (step === 3) {
                this.step3Completed = false
            } else {
                this.step4Completed = false
            }

        },
        imageLoaded(image) {
            this.userData = Object.assign(this.userData, image)
            console.log(this.userData)
        }
    }

}
</script>

<style scoped>

</style>