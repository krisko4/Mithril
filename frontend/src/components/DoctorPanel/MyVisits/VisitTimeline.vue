<template>
    <v-timeline
        dense
        align-top
    >
        <v-timeline-item
            v-for="item in myVisitArray"
            :key="item.index"
            color="primary"
            small
        >
            <v-row class="pt-1">
                <v-col cols="3">
                    <strong>{{ item.hour }}</strong>
                </v-col>
                <v-col>
                    <strong>{{ item.patientName }}</strong>
                    <div class="caption">
                        {{ item.reason }}
                    </div>
                </v-col>
                <v-col>
                    <v-btn small color="primary" @click.stop="showPatientDetails(item)">Details</v-btn>
                </v-col>
            </v-row>
        </v-timeline-item>
        <v-dialog max-width="600" v-model="patientDetails">
            <PatientHistory :patientData="patientData"></PatientHistory>
        </v-dialog>
    </v-timeline>
</template>

<script>




import PatientHistory from "@/components/DoctorPanel/MyPatients/PatientHistory/PatientHistory";

export default {
    name: "VisitTimeline",
    components:{PatientHistory},
    data() {
        return {
            myVisitArray: [],
            patientDetails: false,
            patientData: null
        }
    },
    props: {
        visits: Array
    },
    methods: {
        showPatientDetails(item) {
            this.patientData = item.patientData
            this.patientDetails = true
        }
    },
    created() {
        console.log(this.visits)
        this.visits.forEach((element) => {
            const date = new Date(element.date)
            const hour = date.toTimeString().split(' ')[0]
            this.myVisitArray.push({
                hour: hour,
                patientName: element.patient.firstName + ' ' + element.patient.secondName + ' ' + element.patient.lastName,
                patientData: element.patient
            })
        })

    }

}
</script>

<style scoped>

</style>