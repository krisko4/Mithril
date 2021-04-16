<template>
    <v-container fluid>
        <v-row justify="center">
            <v-col cols="4" align="center">
                <v-card>
                    <v-card-subtitle class="display-1 text--primary">Account is active</v-card-subtitle>
                    <v-divider class="mx-4"></v-divider>
                    <v-card-text>Your account is already active. Feel free to log in to your account. Thank you for using our services. </v-card-text>
                    <v-card-actions>
                        <v-col>
                            <v-row justify="space-between">
                                <v-btn
                                    color="primary"
                                    text
                                    @click="goBack"
                                >
                                    <v-icon
                                        dark
                                        left
                                    >
                                        mdi-arrow-left
                                    </v-icon>
                                    No, thanks
                                </v-btn>
                                <v-btn
                                    color="primary"
                                    text
                                >Sign up
                                </v-btn>
                            </v-row>
                        </v-col>
                    </v-card-actions>
                </v-card>
            </v-col>
        </v-row>
    </v-container>
</template>

<script>
export default {
    name: "EmailAlreadyConfirmedComponent",
    methods:{
        goBack(){
            this.$router.push({name:'home'})
        }
    }
}
</script>

<style scoped>

</style>