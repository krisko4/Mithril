<template>
    <v-card>
        <v-card-title>Patient medical history</v-card-title>
        <v-card-subtitle class="mt-1">In this card you can browse through medical history and previous visits of your patient</v-card-subtitle>
        <v-img src="https://anmj.org.au/wp-content/uploads/2020/03/Older-patient-specialling-in-acute-MAIN-WEB.jpg" height="300px">
        </v-img>
        <v-card-title>{{patientData.firstName}} {{patientData.secondName}} {{patientData.lastName}}</v-card-title>
        <v-card-subtitle>Patient</v-card-subtitle>
        <v-card-text class="text-center" v-if="visitChosen">
            <v-btn color="primary" @click="openConfirmDialog">Begin visit</v-btn>
        </v-card-text>
        <v-tabs>
            <v-row justify="center">
            <v-tab @click="openPatientDetails">Patient details</v-tab>
            <v-tab @click="openAllVisitsTable">All visits</v-tab>
            <v-tab @click="openMyVisits">My visits</v-tab>
            </v-row>
        </v-tabs>


        <PatientDetailsV2 :patientData="patientData" v-if="tabIndex === 0"></PatientDetailsV2>
        <VisitTable :patientData="patientData" :tabIndex="tabIndex" v-if="tabIndex === 1 || tabIndex === 2"></VisitTable>
        <v-dialog v-model="confirmDialogOpen" max-width="400">
            <v-card>
                <v-card-title>
                    Visit begin confirmation
                </v-card-title>
                <v-card-text>
                    Are you sure you want to begin your visit with <b>{{patientData.firstName}} {{patientData.secondName}} {{patientData.lastName}}</b>?
                </v-card-text>
                <v-card-actions>
                    <v-spacer></v-spacer>
                    <v-btn color="blue darken-1" text @click="closeDeleteDialog">
                        Cancel
                    </v-btn>
                    <v-btn color="blue darken-1" text @click="beginVisit">Yes,
                        I'm sure
                    </v-btn>
                    <v-spacer></v-spacer>
                </v-card-actions>
            </v-card>
        </v-dialog>
    </v-card>
</template>

<script>
import PatientDetailsV2 from "@/components/DoctorPanel/MyPatients/PatientHistory/PatientDetailsV2";
import VisitTable from "@/components/DoctorPanel/MyPatients/PatientHistory/VisitTable";
export default {
    name: "PatientHistory",
    components: {VisitTable, PatientDetailsV2},
    props:{
        patientData: Object,
        visitChosen: Boolean,
    },
    data(){
        return{
            tabIndex: 0,
            confirmDialogOpen: false
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
        },
        openConfirmDialog(){
            this.confirmDialogOpen = true
        },
        closeDeleteDialog(){
            this.confirmDialogOpen = false
        },
        beginVisit(){
            this.confirmDialogOpen = false
            this.$emit('visitStarted')
        }

    }
}
</script>

<style scoped>

</style>