<template>
<v-container fluid>
    <v-row justify="center">
    <v-col cols="3" align="center">
        <v-card>
            <v-card-subtitle class="display-1 text--primary">Verify your account</v-card-subtitle>
            <v-divider class="mx-4"></v-divider>
            <v-card-text>Your account has been created, but is inactive. Activation message has been sent to your e-mail: <b>{{email}}</b>
                <br>To activate your account, please visit your e-mail. In case you've not received an e-mail, please press the following button.</v-card-text>

                <v-row justify="center">
                    <v-card-actions>
                    <v-btn large rounded big color="orange">Resend confirmation e-mail</v-btn>
                    </v-card-actions>
                </v-row>
            <v-card-text>Once confirmed, your account will be available to use.</v-card-text>


        </v-card>
    </v-col>
    </v-row>
</v-container>
</template>

<script>
export default {
    name: "MailConfirmComponent",
    props:{
        email: String
    }
}
</script>

<style scoped>

</style>