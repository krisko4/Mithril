<template>
    <div>
        <v-container class="mt-5">
            <v-row justify="center">
                <h2 class="display-1 font-weight-thin mb-3">
                    Please choose a visit date
                </h2>
            </v-row>
            <v-row justify="center">
                <DatePicker @dateChanged="getDate" class="mt-8"/>
            </v-row>
            <v-row justify="center" class="mt-4">
                <v-col cols="7">
                    <PatientSearchComponent @patientSelected="getPatient"></PatientSearchComponent>
                </v-col>
            </v-row>
        </v-container>
        <v-card-actions>
            <v-spacer></v-spacer>
            <v-btn @click="getDoctors" text large :disabled="!date || !patientSelected" color="primary" c>Submit</v-btn>
        </v-card-actions>
    </div>
</template>

<script>
import DatePicker from "@/components/Panels/ReceptionPanel/NewVisit/DatePicker";
import PatientSearchComponent from "@/components/Panels/ReceptionPanel/NewVisit/PatientSearchComponent";
import {tokenAxios} from "@/axios";


export default {
    components: {DatePicker, PatientSearchComponent},
    data() {
        return {
            doctors: [],
            date: '',
            patientSelected: false,
            patient: null
        }
    },

    methods: {

        getPatient(patient, patientSelected) {
            this.patient = patient
            this.patientSelected = patientSelected
        },
        getDate(date) {
            this.date = date
        },
        getDoctors() {
            tokenAxios.get('doctors', {
                params: {
                    date: this.date
                }
            })
                .then(response => {
                    this.doctors = response.data
                    this.$emit('patientAndDateSelected', this.patient, this.date, this.doctors)
                })
        }
    },
}
</script>