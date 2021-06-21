<template>
    <v-container fill-height>
        <v-row justify="center">
            <v-col cols="3" v-if="selectedItem !== undefined">
                <DoctorDetails :doctor="doctor"></DoctorDetails>
            </v-col>
            <v-col cols="3" align="center" justify="center">
                <v-list rounded>
                    <v-subheader>DOCTORS</v-subheader>
                    <v-list-item-group
                        color="primary"
                        v-model="selectedItem"
                    >
                        <v-list-item
                            v-for="(doctor,i) in doctors"
                            :key="i"
                            @click="getVisitHours(doctor)"
                        >
                            <v-list-item-content>
                                <v-list-item-title
                                    v-text="'dr ' + doctor.firstName + ' ' + doctor.lastName"></v-list-item-title>
                            </v-list-item-content>
                        </v-list-item>
                    </v-list-item-group>
                </v-list>
            </v-col>
        </v-row>
    </v-container>
</template>

<script>
import {tokenAxios} from '@/axios'
import DoctorDetails from "@/components/Panels/ReceptionPanel/NewVisit/DoctorDetails";

export default {

    components: {DoctorDetails},
    props: {
        patient: Object,
        date: String,
        doctors: Array
    },
    data() {
        return {
            doctor: '',
            hours: [],
            selectedItem: undefined,
            hourIndex: '',
            imgSrc: '',
        }
    },

    methods: {
        getVisitHours(doctor) {
            this.doctor = doctor
            this.imgSrc = "http://localhost:8080/images/" + doctor.imageName
            tokenAxios.get('visits/get', {
                params: {
                    date: this.date,
                    doctor_id: doctor.id
                }
            }).then((response) => {
                this.hours = response.data
            })
        },
        addVisit() {
            const localDateTime = this.date + ' ' + this.hours[this.hourIndex];
            tokenAxios.post("visits/add", {
                patient_id: this.patient.id,
                doctor_id: this.doctor.id,
                date: localDateTime
            }).then((response) => {
                console.log(response)
                this.hours.splice(this.hourIndex, 1)
                this.$toast.success('Visit saved.')
            })
        },
        goBack() {
            this.$emit('returnClicked')
        }
    },
}
</script>