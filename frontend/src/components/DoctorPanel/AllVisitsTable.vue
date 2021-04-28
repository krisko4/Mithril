<template>
    <v-card-text>
        <v-row justify="center">
            <v-col cols="10">
                <v-dialog v-model="visitDetailsDialog" max-width="500">
                    <VisitDetailsComponent :visitData="visitData"></VisitDetailsComponent>
                </v-dialog>
                <v-data-table
                    :headers="headers"
                    :items="patients"
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
import VisitDetailsComponent from "@/components/DoctorPanel/VisitDetailsComponent";

export default {
    name: "AllVisitsTable",
    components: {VisitDetailsComponent},
    props: {
        patientData: Object,
        tabIndex: Number
    },
    data() {
        return {
            search: '',
            singleSelect: true,
            patients: [],
            visitDetailsDialog: false,
            visitData: null
        }
    },
    methods: {
        openVisitDetailsDialog(item) {
            this.visitData = item
            this.visitDetailsDialog = true
        },
        getAllVisitsForPatient() {
            axios.get('http://localhost:8080/visits/forPatient', {
                params: {
                    patientID: this.patientData.id
                }
            })
                .then((response) => {
                    console.log(response)
                    response.data.forEach((element) => {
                        this.patients.push(
                            {
                                date: element.date.replace(/T/g, ' '),
                                patientFullName: element.patientFirstName + ' ' + element.patientSecondName + ' ' + element.patientLastName,
                                doctorFullName: element.doctorFirstName + ' ' + element.doctorSecondName + ' ' + element.doctorLastName
                            }
                        )
                    })
                })
        },
        getAllVisitsForPatientAndDoctor() {
            axios.get('http://localhost:8080/visits/forPatientAndDoctor', {
                params: {
                    patientID: this.patientData.id,
                    doctorID: localStorage.getItem('id')
                }
            }).then((response) => {
                response.data.forEach((element) => {
                    this.patients.push(
                        {
                            date: element.date.replace(/T/g, ' '),
                            patientFullName: element.patientFirstName + ' ' + element.patientSecondName + ' ' + element.patientLastName,
                            doctorFullName: element.doctorFirstName + ' ' + element.doctorSecondName + ' ' + element.doctorLastName
                        }
                    )
                })
            })
        }
    },
    watch: {
        tabIndex(val) {
            this.patients = []
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