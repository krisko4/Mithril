<template>
    <v-container>
        <DatePickerComponent/>
        <PatientSearchComponent></PatientSearchComponent>
        <v-row justify="center">
            <v-col cols="1" align="center">
                <v-btn @click="getDoctors" :disabled="!date || !patientSelected" color="indigo" class="white--text">Submit
                </v-btn>
            </v-col>
        </v-row>
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