<template>

        <v-card>
            <v-img src="https://img.lovepik.com/element/40171/5187.png_860.png" height="300px">

            </v-img>
            <v-card-title>{{patientData.firstName}} {{patientData.secondName}} {{patientData.lastName}}</v-card-title>
            <v-card-subtitle>Patient</v-card-subtitle>
            <v-divider></v-divider>
            <v-card-text>
                <v-list>
                    <v-list-item>
                        <v-list-item-icon>
                            <v-icon color="indigo">
                                mdi-phone
                            </v-icon>
                        </v-list-item-icon>
                        <v-list-item-content>
                            <v-list-item-title>{{ patientData.phone }}</v-list-item-title>
                            <v-list-item-subtitle>Mobile</v-list-item-subtitle>
                        </v-list-item-content>
                    </v-list-item>
                    <v-divider inset></v-divider>
                    <v-list-item>
                        <v-list-item-icon>
                            <v-icon color="indigo">
                                mdi-calendar
                            </v-icon>
                        </v-list-item-icon>
                        <v-list-item-content>
                            <v-list-item-title>{{patientData.birthdate}}</v-list-item-title>
                            <v-list-item-subtitle>Date of birth</v-list-item-subtitle>
                        </v-list-item-content>
                    </v-list-item>
                    <v-divider inset></v-divider>
                    <v-list-item>
                        <v-list-item-icon>
                            <v-icon color="indigo">
                                mdi-email
                            </v-icon>
                        </v-list-item-icon>

                        <v-list-item-content>
                            <v-list-item-title>{{patientData.email}}</v-list-item-title>
                            <v-list-item-subtitle>Personal e-mail</v-list-item-subtitle>
                        </v-list-item-content>
                    </v-list-item>
                    <v-divider inset></v-divider>
                    <v-list-item>
                        <v-list-item-icon>
                            <v-icon color="indigo">
                                mdi-home
                            </v-icon>
                        </v-list-item-icon>
                        <v-list-item-content>
                            <v-list-item-title>{{ patientData.street }} {{patientData.flatNumber}} {{ patientData.postCode }} {{patientData.city}}</v-list-item-title>
                            <v-list-item-subtitle>Address</v-list-item-subtitle>
                        </v-list-item-content>
                    </v-list-item>
                    <v-divider></v-divider>
                    <v-list-item>
                        <v-list-item-icon>
                            <v-icon color="indigo">
                                mdi-identifier
                            </v-icon>
                        </v-list-item-icon>
                        <v-list-item-content>
                            <v-list-item-title>{{ patientData.pesel }}</v-list-item-title>
                            <v-list-item-subtitle>PESEL</v-list-item-subtitle>
                        </v-list-item-content>
                    </v-list-item>
                </v-list>
            </v-card-text>
        </v-card>

</template>

<script>
export default {
    name: "PatientDetailsComponent",
    props:{
        patientData: Object
    },
    data(){
        return{
            dialog: true,
        }
    },
    created() {
        console.log('crieted')
    }

}
</script>

<style scoped>

</style>