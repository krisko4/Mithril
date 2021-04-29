<template>
    <v-card>
        <v-card-title>Patient medical history</v-card-title>
        <v-card-subtitle class="mt-1">In this card you can browse through medical history and previous visits of your patient</v-card-subtitle>
        <v-img src="https://anmj.org.au/wp-content/uploads/2020/03/Older-patient-specialling-in-acute-MAIN-WEB.jpg" height="300px">
        </v-img>
        <v-card-title>{{patientData.firstName}} {{patientData.secondName}} {{patientData.lastName}}</v-card-title>
        <v-card-subtitle>Patient</v-card-subtitle>
        <v-tabs>
            <v-row justify="center">
            <v-tab @click="openPatientDetails">Patient details</v-tab>
            <v-tab @click="openAllVisitsTable">All visits</v-tab>
            <v-tab @click="openMyVisits">My visits</v-tab>
            </v-row>
        </v-tabs>
        <PatientDetailsV2 :patientData="patientData" v-if="tabIndex === 0"></PatientDetailsV2>
        <VisitTable :patientData="patientData" :tabIndex="tabIndex" v-if="tabIndex === 1 || tabIndex === 2"></VisitTable>

    </v-card>
</template>

<script>
import PatientDetailsV2 from "@/components/DoctorPanel/MyPatients/PatientHistory/PatientDetailsV2";
import VisitTable from "@/components/DoctorPanel/MyPatients/PatientHistory/VisitTable";
export default {
    name: "PatientHistory",
    components: {VisitTable, PatientDetailsV2},
    props:{
        patientData: Object
    },
    data(){
        return{
            tabIndex: 0
        }
    },
    methods:{
        openPatientDetails(){
            this.tabIndex = 0
        },
        openAllVisitsTable(){
            this.tabIndex = 1
        },
        openMyVisits(){
            this.tabIndex = 2
        }
    }
}
</script>

<style scoped>

</style>