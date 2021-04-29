<template>

    <v-container fill-height>
    <v-container>

        <v-row justify="center">
            <h2 class="display-1 font-weight-thin mb-3">
                Please choose a visit date
            </h2>
        </v-row>
        <v-row justify="center">
            <DatePicker class="mt-8"/>
        </v-row>
        <v-row justify="center">
            <v-col cols="3">
                <PatientSearchComponent></PatientSearchComponent>
            </v-col>
        </v-row>


        <v-row justify="center">
                <v-btn @click="getDoctors" :disabled="!date || !patientSelected" color="indigo" class="white--text">Submit
                </v-btn>

        </v-row>


    </v-container>
    </v-container>

</template>

<script>
import DatePicker from "@/components/VisitSaving/DatePicker";
import PatientSearchComponent from "@/components/VisitSaving/PatientSearchComponent";


export default {
    components: {DatePicker, PatientSearchComponent},
    computed: {
        date() {
            return this.$store.state.date
        },
        patientSelected() {
            return this.$store.state.patientSelected
        },

    },
    methods: {
        getDoctors() {
            this.$store.commit('getDoctors')
            }
        },

}
</script>