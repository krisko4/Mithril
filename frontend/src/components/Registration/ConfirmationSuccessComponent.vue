<template>
<v-container>
    <v-container fluid>
        <v-row justify="center">
            <v-col cols="3" align="center">
                <v-card>
                    <v-card-subtitle class="display-1 text--primary">Verification successful!</v-card-subtitle>
                    <v-divider class="mx-4"></v-divider>
                    <v-card-text>Your account has been activated. Now you can log in.<br> Thank you for using our services.</v-card-text>

                    <v-row justify="center">
                        <v-card-actions>
                        <v-col cols="6">
                            <v-btn rounded big color="primary" @click="goBack">Return</v-btn>
                        </v-col>
                        <v-col cols="6">
                            <v-btn rounded big color="primary">Login</v-btn>
                        </v-col>
                        </v-card-actions>
                    </v-row>



                </v-card>
            </v-col>
        </v-row>
    </v-container>
</v-container>

</template>

<script>


export default {
    name: "ConfirmationSuccessComponent",
    methods:{
        test(){
            console.log(this.$route.params.token)
        },
        goBack(){
            this.$router.push({name:'home'})
        }
    },
    created(){

    }
}
</script>

<style scoped>

</style>