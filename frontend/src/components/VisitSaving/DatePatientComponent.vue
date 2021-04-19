<template>

    <v-container fill-height>
    <v-container>

        <v-row justify="center">
            <h2 class="display-1 font-weight-thin mb-3">
                Please choose a visit date
            </h2>
        </v-row>

        <DatePickerComponent class="mt-10"/>
        <PatientSearchComponent></PatientSearchComponent>
        <v-row justify="center">
                <v-btn @click="getDoctors" :disabled="!date || !patientSelected" color="indigo" class="white--text">Submit
                </v-btn>

        </v-row>
    </v-container>
    </v-container>

</template>

<script>
import DatePickerComponent from "@/components/VisitSaving/DatePickerComponent";
import PatientSearchComponent from "@/components/VisitSaving/PatientSearchComponent";


export default {
    components: {DatePickerComponent, PatientSearchComponent},
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