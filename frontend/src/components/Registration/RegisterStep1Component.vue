<template>
    <v-container>

        <v-row class="text-center">
            <v-col class="mb-4">
                <h1 class="display-2 font-weight-bold mb-3">
                    Registration panel
                </h1>
                <h2 class="display-1 font-weight-thin mb-3">
                    Step 1
                </h2>
                <h3  class="display-1 font-weight-thin mb-3">
                    <i>Login credentials </i>
                </h3>
                <transition name="fade">
                    <p style="color:red;" v-if="errorPopped">{{ error }}</p>
                </transition>
            </v-col>
        </v-row>
        <v-form ref="form" v-model="valid" class="login" @submit.prevent="validateForm">


            <v-row justify="center">
                <v-col cols="3">
                    <v-flex
                        xs-12
                        md-4
                    >
                        <v-text-field
                            type="email"
                            :rules="emailRules"
                            outlined
                            clearable
                            rounded
                            label="E-mail"
                            error-count="1"
                            required
                            v-model="email"
                        ></v-text-field>
                        <v-text-field
                            type="email"
                            :rules="emailRules"
                            outlined
                            clearable
                            rounded
                            label="Confirm e-mail"
                            error-count="1"
                            required
                            v-model="confirmEmail"
                        ></v-text-field>
                        <v-text-field
                            type="password"
                            :rules="passwordRules"
                            outlined
                            clearable
                            rounded
                            label="Password"
                            error-count="5"
                            required
                            v-model="password"
                        ></v-text-field>
                        <v-text-field
                            type="password"

                            outlined
                            clearable
                            rounded
                            label="Confirm password"
                            :rules="[
            v => !!v || 'Please confirm your password.',

            ]"
                            error-count="5"
                            required
                            v-model="confirmPassword"
                        ></v-text-field>
                        <v-row justify="center">
                            <transition name="fade">
                                <v-btn color="primary" @click="validateForm" :disabled="!buttonEnabled" medium>Submit
                                    <v-icon dark right>mdi-checkbox-marked-circle</v-icon>
                                </v-btn>
                            </transition>
                        </v-row>
                    </v-flex>
                </v-col>
            </v-row>
        </v-form>

    </v-container>


</template>

<script>
//import axios from 'axios'
import axios from "axios";

export default {
    name: "RegisterStep1Component",


    data() {
        return {

            valid: false,
            passwordRules: [
                v => !!v || "Please enter your password.",
                v => (v && v.length >= 8) || ("Your password should contain at least 8 digits."),
                v => /(?=.*[A-Z])/.test(v) || "Your password should contain at least one capital letter.",
                v => /(?=.*\d)/.test(v) || "Your password should contain at least one number.",
                v => /([!@$%])/.test(v) || "Your password should contain at least one special sign (!@$%).",


            ],

            emailRules: [
                v => !!v || "Please enter your e-mail.",
                v => /.+@.+/.test(v) || 'Correct e-mail example: example@example.com'
            ],

            buttonEnabled: false,
            errorPopped: false,
            password: '',
            confirmPassword: '',
            confirmEmail: '',
            email: '',
            error: '',
            user: [],

        }
    },
    watch: {
        valid() {
            console.log(this.valid)
            setTimeout(this.showButton, 200);
            this.$emit('validCheck', this.valid)
        }
    },
    methods: {

        showButton() {
            this.buttonEnabled = this.valid;
        },


        validateForm() {
            this.errorPopped = false;
            if (this.password !== this.confirmPassword) {
                this.error = "Passwords are different."
                this.errorPopped = true;
                return
            }
            if (this.email !== this.confirmEmail) {
                this.error = "E-mails are different."
                this.errorPopped = true;
            }
            axios.get("http://localhost:8080/users/email", {
                params: {
                    "email": this.email
                }
            })
                .then((response) => {
                        console.log(response.data)
                        this.$emit('firstStepComplete', 2, {
                            'email': this.email,
                            'password': this.password
                        })
                        this.$toast.success('Step 1 completed successfully.')
                    }
                ).catch((error) => {
                this.error = error.response.data.message
                this.errorPopped = true
            })


            //   axios.post('http://localhost:8080/doctors/register', {
            //     password: this.password,
            //        email: this.email,

            //   }).then(()=>{
            //      this.$toast.success('Step 1 completed successfully.')

            //     this.$emit('firstStepComplete', 2)
            //    }).catch((error)=>{
            //      this.error = error.response.data.message
            //      this.errorPopped = true
            //    })


        }

    }
    ,
}

</script>

<style scoped>

</style>