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
                                                <PatientSearchComponent v-if="newPatientDialog"></PatientSearchComponent>
                                            </v-card-actions>
                                            <v-card-text align="end">
                                                <v-btn color="primary" @click="addNewPatient"
                                                       :disabled="!patientSelected">Add
                                                </v-btn>
                                            </v-card-text>
                                        </v-card>
                                    </template>
                                </v-dialog>
                            </v-col>
                        </v-row>
                    </v-card-subtitle>
                    <v-data-table
                        :headers="headers"
                        :items="patients"
                        :single-select="singleSelect"
                        item-key="name"
                        :search="search"
                    >
                        <template v-slot:item="{ item }">
                            <v-dialog max-width="400" v-model="patientDetails">
                                <template v-slot:activator="on" v-on="on">
                                    <tr @click="showPatientDetails(item)">
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
                                        <v-dialog v-model="deleteDialog" max-width="500" :retain-focus="false">
                                            <template v-slot:activator="{on}" v-on="on">
                                                <td>
                                                    <v-icon
                                                        @click="openDeleteDialog"
                                                        color="primary"
                                                    >
                                                        mdi-delete
                                                    </v-icon>
                                                </td>
                                            </template>
                                            <template>
                                                <v-card>
                                                    <v-card-title>
                                                        Patient removal
                                                    </v-card-title>
                                                    <v-card-text>
                                                        Are you sure you want to remove <b>{{ patientData.firstName }}
                                                        {{ patientData.lastName }}</b> from your table?
                                                    </v-card-text>
                                                    <v-card-actions>
                                                        <v-spacer></v-spacer>
                                                        <v-btn color="blue darken-1" text @click="closeDeleteDialog">
                                                            Cancel
                                                        </v-btn>
                                                        <v-btn color="blue darken-1" text @click="deletePatient">Yes,
                                                            I'm sure
                                                        </v-btn>
                                                        <v-spacer></v-spacer>
                                                    </v-card-actions>
                                                </v-card>
                                            </template>
                                        </v-dialog>
                                    </tr>
                                </template>
                                <template>
                                    <PatientDetailsComponent :patientData="patientData"></PatientDetailsComponent>
                                </template>
                            </v-dialog>
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


export default {
    name: "PatientListComponent",
    components: {PatientDetailsComponent, PatientSearchComponent},
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
        }

    },
    methods: {
        showPatientDetails(item) {
            this.patientData = item
            if (!this.deleteDialog) {
                this.patientDetails = true
            }
        },
        goBack() {
            this.$emit('goBack')
        },
        openNewPatientDialog() {
            this.newPatientDialog = true
        },
        openDeleteDialog() {
            this.deleteDialog = true
        },
        closeDeleteDialog() {
            this.deleteDialog = false
        },
        addNewPatient() {

            axios.put('http://localhost:8080/patients/addDoctor', {
                'doctorID': localStorage.getItem('id'),
                'patientID': this.$store.state.patient.id
            }, {
                    headers: {
                        'Authorization': 'Bearer ' + localStorage.getItem('user')
                    }
                }
                ).then((response) => {
                    console.log(response.data)
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
            })
        },
        deletePatient() {
            // make sure the patient we want to delete is in our table and return his data
            const patient = this.patients.filter((element) => {
                if (element.pesel === this.patientData.pesel) {
                    return element
                }
            })
            if (patient) {
                axios.put('http://localhost:8080/patients/removeDoctor', {

                        'doctorID': localStorage.getItem('id'),
                        'patientID': this.patientData.id

                    }, {
                        headers: {
                            'Authorization': 'Bearer ' + localStorage.getItem('user')
                        },
                    }
                ).then(() => {
                    this.patients.splice(this.patients.indexOf(patient[0]), 1)
                }).finally(() => {
                    this.deleteDialog = false
                })
            }

        }


    },

    watch: {
        '$store.state.patientSelected'(val) {
            // if patientSelected is true <=> button is enabled, check if such patient is already present in the table
            // if so, disable button
            if (val) {
                this.patients.filter((element) => {
                    if (element.pesel === this.$store.state.patient.pesel) {
                        console.log('duplicate')
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