<template>
    <v-container>
       <ConfirmationSuccessComponent v-if="confirmationSucceeded"></ConfirmationSuccessComponent>
        <EmailAlreadyConfirmedComponent v-if="emailAlreadyConfirmed"></EmailAlreadyConfirmedComponent>
        <TokenExpiredComponent v-if="tokenExpired"></TokenExpiredComponent>
    </v-container>

</template>

<script>

import axios from 'axios'
import ConfirmationSuccessComponent from "@/components/Registration/Confirmation/ConfirmationSuccessComponent";
import EmailAlreadyConfirmedComponent from "@/components/Registration/Confirmation/EmailAlreadyConfirmedComponent";
import TokenExpiredComponent from "@/components/Registration/Confirmation/TokenExpiredComponent";
export default {
    name: "ConfirmationComponent",
    components: {TokenExpiredComponent, EmailAlreadyConfirmedComponent, ConfirmationSuccessComponent},
    data(){
        return{
            tokenExpired: false,
            emailAlreadyConfirmed: false,
            confirmationSucceeded: false,
        }

    },
    methods:{


    },
    created(){
        axios.get('http://localhost:8080/doctor/registration/confirm?token=' + this.$route.params.token)
            .then((response)=>{
                console.log(response)
                this.confirmationSucceeded = true
            }).catch((error) => {
            let errorMessage = error.response.data.message
            if(errorMessage === 'Token expired'){
                this.tokenExpired = true
            }
            else if(errorMessage === 'E-mail already confirmed'){
                this.emailAlreadyConfirmed = true
            }
        })
    }
}
</script>

<style scoped>

</style>