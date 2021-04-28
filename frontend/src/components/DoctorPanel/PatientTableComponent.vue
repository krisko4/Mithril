<template>
    <v-container>
        <v-row justify="center">
            <v-col cols="10">
                <v-card>
                    <v-card-title>
                        Patients
                    </v-card-title>
                    <v-card-subtitle>
                        <v-row justify="space-between">
                            <v-col cols="5">
                                <v-text-field
                                    v-model="search"
                                    append-icon="mdi-magnify"
                                    label="Search"
                                    single-line
                                    hide-details
                                ></v-text-field>
                            </v-col>
                            <v-col cols="7" align="end">
                                <v-dialog max-width="400"
                                          v-model="newPatientDialog">
                                    <template v-slot:activator="on" v-on="on">
                                        <v-btn @click="openNewPatientDialog" color="primary">New patient</v-btn>
                                    </template>
                                    <template>
                                        <v-card>
                                            <v-card-title>
                                                Add new patient to your table
                                            </v-card-title>
                                            <v-card-actions>
                                                <PatientSearchComponent
                                                    v-if="newPatientDialog"></PatientSearchComponent>
                                            </v-card-actions>
                                            <v-card-text align="end">
                                                <v-dialog max-width="300" v-model="warningDialog">
                                                    <template v-slot:activator="on" v-on="on">
                                                        <v-btn color="primary" @click="addNewPatient"
                                                               :disabled="!patientSelected">Add
                                                        </v-btn>
                                                    </template>
                                                    <template>
                                                        <v-card>
                                                           <v-card-title>Warning</v-card-title>
                                                            <v-card-text>Patient <b>{{$store.state.patient.firstName}} {{$store.state.patient.lastName}}</b> is already a patient of doctor <b>Jerzy Owsiak</b>. Are you sure you want to place him as your patient?</v-card-text>
                                                            <v-card-actions>
                                                                <v-spacer></v-spacer>
                                                                <v-btn color="blue darken-1" text @click="closeWarningDialog">
                                                                    Cancel
                                                                </v-btn>
                                                                <v-btn color="blue darken-1" text @click="submitNewPatient">Yes,
                                                                    I'm sure
                                                                </v-btn>
                                                                <v-spacer></v-spacer>
                                                            </v-card-actions>
                                                        </v-card>
                                                    </template>
                                                </v-dialog>

                                            </v-card-text>
                                        </v-card>
                                    </template>
                                </v-dialog>
                            </v-col>
                        </v-row>
                    </v-card-subtitle>
                    <v-dialog max-width="400" v-model="patientDetails">
                        <PatientDetailsComponent :patientData="patientData"></PatientDetailsComponent>
                    </v-dialog>
                    <v-dialog v-model="historyDialog" max-width="600">
                        <PatientHistoryComponent :patientData="patientData"></PatientHistoryComponent>
                    </v-dialog>
                    <v-dialog v-model="deleteDialog" max-width="500">
                        <PatientDeleteComponent @deleteDialogClosed="closeDeleteDialog" @patientDeleteSubmitted="deletePatient" :patientData="patientData"></PatientDeleteComponent>
                    </v-dialog>
                    <v-data-table
                        :headers="headers"
                        :items="patients"
                        :single-select="singleSelect"
                        item-key="name"
                        :search="search"
                    >
                        <template v-slot:item="{ item }">
                                    <tr @click.stop="showPatientDetails(item)">
                                        <td>{{ item.firstName }}</td>
                                        <td class="text-xs-right">{{ item.secondName }}</td>
                                        <td class="text-xs-right">{{ item.lastName }}</td>
                                        <td class="text-xs-right">{{ item.pesel }}</td>
                                        <td class="text-xs-right">{{ item.birthdate }}</td>
                                        <td class="text-xs-right">{{ item.phone }}</td>
                                        <td class="text-xs-right">{{ item.email }}</td>
                                        <td class="text-xs-right">{{ item.street }}</td>
                                        <td class="text-xs-right">{{ item.flatNumber }}</td>
                                        <td class="text-xs-right">{{ item.postCode }}</td>
                                        <td class="text-xs-right">{{ item.city }}</td>
                                        <td class="text-xs-right">
                                            <v-btn color="primary" x-small  @click.stop="openHistoryDialog(item)">Check</v-btn>
                                        </td>
                                        <td>
                                            <v-icon
                                                @click.stop="openDeleteDialog(item)"
                                                color="primary"
                                            >
                                                mdi-delete
                                            </v-icon>
                                        </td>
                                    </tr>
                        </template>
                    </v-data-table>
                    <v-card-actions>
                        <v-btn text color="primary" @click="goBack">Return</v-btn>
                    </v-card-actions>

                </v-card>
            </v-col>
        </v-row>
    </v-container>
</template>

<script>
import axios from "axios";
import PatientDetailsComponent from "@/components/DoctorPanel/PatientDetailsComponent";
import PatientSearchComponent from "@/components/VisitSaving/PatientSearchComponent";
import PatientHistoryComponent from "@/components/DoctorPanel/PatientHistoryComponent";
import PatientDeleteComponent from "@/components/DoctorPanel/PatientDeleteComponent";


export default {
    name: "PatientListComponent",
    components: {PatientDeleteComponent, PatientHistoryComponent, PatientDetailsComponent, PatientSearchComponent},
    data() {
        return {
            newPatientDialog: false,
            findPatient: '',
            patientDetails: false,
            search: '',
            singleSelect: true,
            patients: [],
            patientData: '',
            deleteDialog: false,
            warningDialog: false,
            historyDialog: false,
        }

    },
    methods: {

        openHistoryDialog(item){
            this.patientData = item
            this.historyDialog = true
        },
        closeWarningDialog(){
            this.warningDialog = false
        },
        showPatientDetails(item) {
            this.patientData = item
            this.patientDetails = true
        },
        goBack() {
            this.$emit('goBack')
        },
        closeDeleteDialog(){
          this.deleteDialog = false
        },
        openNewPatientDialog() {
            this.newPatientDialog = true
        },
        openDeleteDialog(item) {
            this.patientData = item
            this.deleteDialog = true
        },

        submitNewPatient(){
            axios.patch('http://localhost:8080/patients/addDoctor', {
                    'doctorID': localStorage.getItem('id'),
                    'patientID': this.$store.state.patient.id
                }, {
                    headers: {
                        'Authorization': 'Bearer ' + localStorage.getItem('user')
                    }
                }
            ).then((response) => {
                this.patients.push(
                    {
                        id: response.data.id,
                        firstName: response.data.firstName,
                        secondName: response.data.secondName,
                        lastName: response.data.lastName,
                        pesel: response.data.pesel,
                        birthdate: response.data.birthdate,
                        phone: response.data.phone,
                        email: response.data.email,
                        street: response.data.address.street,
                        flatNumber: response.data.address.flatNumber,
                        postCode: response.data.address.postCode,
                        city: response.data.address.city

                    })
            }).finally(() => {
                this.newPatientDialog = false
                this.warningDialog = false
                this.$toast.success('Patient added successfully')
            })
        },

        addNewPatient() {
            if(this.$store.state.patient.doctorID){
                this.newPatientDialog = false
                this.warningDialog = true
                return
            }
            this.submitNewPatient()

        },
        deletePatient() {
            axios.patch('http://localhost:8080/patients/removeDoctor', {

                    'doctorID': localStorage.getItem('id'),
                    'patientID': this.patientData.id

                }, {
                    headers: {
                        'Authorization': 'Bearer ' + localStorage.getItem('user')
                    },
                }
            ).then(() => {
                this.patients.splice(this.patients.indexOf(this.patientData), 1)
            }).finally(() => {
                this.deleteDialog = false
            })
        }


    },

    watch: {
        '$store.state.patientSelected'(val) {
            // if patientSelected is true <=> button is enabled, check if such patient is already present in the table
            // if so, disable button
            if (val) {
                this.patients.filter((element) => {
                    if (element.pesel === this.$store.state.patient.pesel) {
                        this.$store.state.patientSelected = false
                    }
                })
            }
        },

    },

    computed: {
        patientSelected() {
            return this.$store.state.patientSelected
        },
        headers() {
            return [
                {
                    text: 'First name',
                    align: 'start',
                    sortable: true,
                    value: 'firstName',
                },
                {
                    text: 'Second name',
                    value: 'secondName',

                },
                {text: 'Last name', value: 'lastName'},
                {text: 'PESEL', value: 'pesel'},
                {text: 'Date of birth', value: 'birthdate'},
                {text: 'Phone', value: 'phone'},
                {text: 'E-mail', value: 'email'},
                {text: 'Street', value: 'street'},
                {text: 'Flat number', value: 'flatNumber'},
                {text: 'Post code', value: 'postCode'},
                {text: 'City', value: 'city'},
                {text: 'History', value: 'history', sortable: false},
                {text: 'Actions', value: 'actions', sortable: false},

            ]
        },

    },

    created() {
        axios.get('http://localhost:8080/patients/byDoctor/', {
            headers: {
                'Authorization': 'Bearer ' + localStorage.getItem('user')
            },
            params: {
                doctor_id: localStorage.getItem('id')
            }
        }).then((response) => {
            response.data.forEach((element) => {
                this.patients.push(
                    {
                        id: element.id,
                        firstName: element.firstName,
                        secondName: element.secondName,
                        lastName: element.lastName,
                        pesel: element.pesel,
                        birthdate: element.birthdate,
                        phone: element.phone,
                        email: element.email,
                        street: element.address.street,
                        flatNumber: element.address.flatNumber,
                        postCode: element.address.postCode,
                        city: element.address.city

                    }
                )
            })
        })
    }
}
</script>

<style scoped>

</style>