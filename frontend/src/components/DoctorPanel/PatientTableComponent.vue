<template>
<v-container>
    <v-row justify="center">
    <v-col cols="9">
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
                          v-model="newPatient">
                    <template v-slot:activator="on" v-on="on">
                            <v-btn @click="addNewPatient" color="primary">New patient</v-btn>
                    </template>
                    <template>
                       <v-card>
                           <v-card-title>
                               Add new patient to your table
                           </v-card-title>
                           <v-card-actions>
                                   <PatientSearchComponent></PatientSearchComponent>
                           </v-card-actions>
                          <v-card-text align="end">
                                  <v-btn color="primary" :disabled="!patientSelected">Add</v-btn>
                          </v-card-text>
                       </v-card>
                    </template>
                </v-dialog>
                </v-col>
            </v-row>
        </v-card-subtitle>
        <v-data-table
            v-model="selected"
            :headers="headers"
            :items="patients"
            :single-select="singleSelect"
            item-key="name"

            :search="search"
        >
            <template v-slot:item="{ item }" >
                <v-dialog max-width="400" v-model="patientDetails">
                <template v-slot:activator="on" v-on="on">
                <tr @click="showPatientDetails(item)">
                    <td>{{ item.firstName }}</td>
                    <td class="text-xs-right">{{item.secondName }}</td>
                    <td class="text-xs-right">{{item.lastName }}</td>
                    <td class="text-xs-right">{{item.pesel }}</td>
                    <td class="text-xs-right">{{item.birthdate }}</td>
                    <td class="text-xs-right">{{item.phone }}</td>
                    <td class="text-xs-right">{{item.email }}</td>
                    <td class="text-xs-right">{{item.street }}</td>
                    <td class="text-xs-right">{{item.flatNumber }}</td>
                    <td class="text-xs-right">{{item.postCode }}</td>
                    <td class="text-xs-right">{{item.city }}</td>
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
    data(){
        return {
            newPatient: false,
            findPatient: '',
            patientDetails: false,
            search: '',
            singleSelect: true,
            selected: [],
            patients : [],
            patientData: null
        }

    },
    methods:{
       showPatientDetails(item){
           console.log(item)
           this.patientData = item
           this.patientDetails = true
       },
        goBack(){
            this.$emit('goBack')
        },
        addNewPatient(){
           this.newPatient = true
        }
    },
    computed: {

        patientSelected() {
            return this.$store.state.patientSelected
        },
        headers () {
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
                { text: 'Last name', value: 'lastName' },
                { text: 'PESEL', value: 'pesel' },
                { text: 'Date of birth', value: 'birthdate' },
                { text: 'Phone', value: 'phone' },
                { text: 'E-mail', value: 'email' },
                { text: 'Street', value: 'street' },
                { text: 'Flat number', value: 'flatNumber' },
                { text: 'Post code', value: 'postCode' },
                { text: 'City', value: 'city' },

            ]
        },

    },

    created(){
        axios.get('http://localhost:8080/patients/byDoctor/', {
            params: {
                doctor_id : localStorage.getItem('id')
            }
        } ).then((response) => {
            response.data.forEach((element) => {
                this.patients.push(
                    {
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