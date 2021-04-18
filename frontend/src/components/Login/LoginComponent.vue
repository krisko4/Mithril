<template>
    <v-container fill-height>
<v-container>
    <v-row class="text-center">
        <v-col class="mb-4">
            <h1 class="display-2 font-weight-bold mb-3">
                Login panel
            </h1>
            <h2 class="display-1 font-weight-thin mb-3">
                Please fill your login credentials to sign up
            </h2>
        </v-col>
    </v-row>
    <v-row justify="center">
    <v-col cols="6">
    <v-form v-model="valid" class="login" @submit.prevent="validateLogin">
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
            <v-btn color="primary" @click="validateLogin" :disabled="!buttonEnabled" medium>Sign up
                <v-icon dark right>mdi-checkbox-marked-circle</v-icon>
            </v-btn>
        </transition>
    </v-row>
</v-container>
    </v-container>
</template>

<script>
export default {
    name: "LoginComponent",

    data(){
        return{
            email: '',
            password: '',
            buttonEnabled: false,
            valid: false,
            emailRules: [
                v => !!v || "Please enter your e-mail.",
                v => /.+@.+/.test(v) || 'Correct e-mail example: example@example.com'
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
        validateLogin(){

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