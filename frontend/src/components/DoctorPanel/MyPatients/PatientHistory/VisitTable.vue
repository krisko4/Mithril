<template>
    <v-card-text>
        <v-row justify="center">
            <v-col cols="10">
                <v-dialog v-model="visitDetailsDialog" max-width="500">
                    <VisitDetails :patientData="patientData" :visitData="visitData"></VisitDetails>
                </v-dialog>
                <v-data-table
                    :headers="headers"
                    :items="visits"
                    :single-select="singleSelect"
                    item-key="name"
                    :search="search"
                >
                    <template v-slot:item="{ item }">
                        <tr>
                            <td class="text-xs-right">{{ item.date }}</td>
                            <td class="text-xs-right">{{ item.patientFullName }}</td>
                            <td class="text-xs-right">{{ item.doctorFullName }}</td>
                            <td>
                                <v-btn @click.stop="openVisitDetailsDialog(item)" x-small color="primary">Details
                                </v-btn>
                            </td>
                        </tr>
                    </template>
                </v-data-table>
            </v-col>
        </v-row>
    </v-card-text>
</template>

<script>
import axios from "axios";
import VisitDetails from "@/components/DoctorPanel/MyPatients/PatientHistory/VisitDetails";

export default {
    name: "VisitTable",
    components: {VisitDetails},
    props: {
        patientData: Object,
        tabIndex: Number
    },
    data() {
        return {
            search: '',
            singleSelect: true,
            visits: [],
            visitDetailsDialog: false,
            visitData: null
        }
    },
    methods: {

        addVisit(element){
            this.visits.push(
                {
                    date: element.date.replace(/T/g, ' '),
                    patientFullName: element.patient.firstName + ' ' + element.patient.secondName + ' ' + element.patient.lastName,
                    doctorFullName: element.doctor.firstName + ' ' + element.doctor.secondName + ' ' + element.doctor.lastName,
                    doctorImg: element.doctor.imageName,
                    description: element.description,
                    interview: element.interview,
                    medicine: element.medicine,
                    reason: element.reason,
                    research: element.research,
                    referrals: element.referrals

                }
            )
        },


        openVisitDetailsDialog(item) {
            this.visitData = item
            this.visitData = Object.assign(this.visitData, {patient: this.patientData})
            console.log(this.visitData)
            this.visitDetailsDialog = true
        },
        getAllVisitsForPatient() {
            axios.get('http://localhost:8080/patients/' + this.patientData.id + '/visits')
                .then((response) => {
                    response.data.forEach((element) => {
                      this.addVisit(element)
                    })
                })
        },
        getAllVisitsForPatientAndDoctor() {
            axios.get('http://localhost:8080/doctors/' + localStorage.getItem('id') + '/visits', {
                params: {
                    patient_id: this.patientData.id
                }
            }).then((response) => {
                response.data.forEach((element) => {
                   this.addVisit(element)
                })
            })
        }
    },
    watch: {
        tabIndex(val) {
            this.visits = []
            if (val === 1) {
                this.getAllVisitsForPatient()
            } else if (val === 2) {
                this.getAllVisitsForPatientAndDoctor()
            }
        }
    },
    created() {
        if (this.tabIndex === 1) {
            // localhost:8080/patients/{id}/visits
            this.getAllVisitsForPatient()

        } else if (this.tabIndex === 2) {
            this.getAllVisitsForPatientAndDoctor()
        }
    },
    computed: {
        headers() {
            return [
                {
                    text: 'Date',
                    align: 'start',
                    sortable: true,
                    value: 'date',
                },
                {
                    text: 'Patient',
                    value: 'patientFullName',

                },
                {
                    text: 'Doctor',
                    value: 'doctorFullName',

                },
                {
                    text: 'Details',
                    value: 'details',

                },


            ]
        },
    }
}
</script>

<style scoped>

</style>