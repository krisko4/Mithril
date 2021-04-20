<template>
    <v-container fill-height>
        <MailConfirmComponent v-if="!isAccountActive" :email="email"></MailConfirmComponent>
<v-container v-else>
    <v-row class="text-center">
        <v-col class="mb-4">
            <h1 class="display-2 font-weight-bold mb-3">
                Login panel
            </h1>
            <h2 class="display-1 font-weight-thin mb-3">
                Please fill your login credentials to sign in
            </h2>
            <transition name="fade">
                <p style="color:red;" v-if="errorPopped">{{ error }}</p>
            </transition>
        </v-col>
    </v-row>
    <v-row justify="center">
    <v-col cols="6">
    <v-form v-model="valid" class="login">
            <v-row justify="center" >
                <v-col cols="6">
                    <v-text-field
                        type="email"
                        :rules="emailRules"
                        outlined
                        clearable
                        rounded
                        label="E-mail"
                        error-count="1"
                        required
                        v-model="email">

                    </v-text-field>
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
                    >
                    </v-text-field>


                </v-col>
            </v-row>
    </v-form>
    </v-col>
    </v-row>
    <v-row justify="center" class="mt-2">
        <transition name="fade">
            <v-btn color="primary" @click="login" :disabled="!buttonEnabled" medium>Sign in
                <v-icon dark right>mdi-checkbox-marked-circle</v-icon>
            </v-btn>
        </transition>
    </v-row>
</v-container>
    </v-container>
</template>

<script>
import axios from "axios";
import MailConfirmComponent from "@/components/Registration/Confirmation/MailConfirmComponent";
export default {
    name: "LoginComponent",
    components: {MailConfirmComponent},

    data(){
        return{
            isAccountActive: true,
            email: '',
            password: '',
            buttonEnabled: false,
            errorPopped: false,
            valid: false,
            error: '',
            emailRules: [
                v => !!v || "Please enter your e-mail.",
                v => /[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?/.test(v) || 'Correct e-mail example: example@example.com'
            ],
            passwordRules: [
                v => !!v || "Please enter your password.",
                v => (v && v.length >= 8) || ("Your password should contain at least 8 digits."),
                v => /(?=.*[A-Z])/.test(v) || "Your password should contain at least one capital letter.",
                v => /(?=.*\d)/.test(v) || "Your password should contain at least one number.",
                v => /([!@$%])/.test(v) || "Your password should contain at least one special sign (!@$%).",


            ],

        }
    },
    methods : {
        login(){
            console.log(this.email)
            axios.post('http://localhost:8080/login', {
                email: this.email,
                password: this.password
            }).then((response) => {
                localStorage.setItem('token', JSON.stringify(response.data));
                console.log(response)
                this.$router.push({name: 'home'})
            }).catch((error) => {
                this.errorPopped = true
                if(error.response.data.message === 'This user has not been activated'){
                    this.isAccountActive = false
                    return
                }
                this.error = error.response.data.message
                console.log(error)
            })
        },
        showButton() {
            this.buttonEnabled = this.valid;
        },
    },
    watch: {
        valid() {
            console.log(this.valid)
            setTimeout(this.showButton, 200);
            this.$emit('validCheck', this.valid)
        }
    },
}
</script>

<style scoped>

</style>