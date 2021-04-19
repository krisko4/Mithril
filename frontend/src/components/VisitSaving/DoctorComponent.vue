<template>
    <v-container fill-height>
        <v-row justify="center">

            <v-col cols="3" v-if="selectedItem !== undefined">
                <v-card
                    elevation="20"
                >
                    <v-img
                        :src="imgSrc"
                        height="300px"
                        dark
                    >
                    </v-img>
                    <v-card-title>{{ 'dr ' + doctor.firstName + ' ' + doctor.lastName }}</v-card-title>
                    <v-card-subtitle>proktolog</v-card-subtitle>
                    <v-list>
                        <v-list-item>
                            <v-list-item-icon>
                                <v-icon color="indigo">
                                    mdi-phone
                                </v-icon>
                            </v-list-item-icon>

                            <v-list-item-content>
                                <v-list-item-title>{{ doctor.phone }}</v-list-item-title>
                                <v-list-item-subtitle>Mobile</v-list-item-subtitle>
                            </v-list-item-content>

                            <v-list-item-icon>
                                <v-icon>mdi-message-text</v-icon>
                            </v-list-item-icon>
                        </v-list-item>
                        <v-divider inset></v-divider>
                        <v-list-item>
                            <v-list-item-icon>
                                <v-icon color="indigo">
                                    mdi-email
                                </v-icon>
                            </v-list-item-icon>

                            <v-list-item-content>
                                <v-list-item-title>{{ doctor.email }}</v-list-item-title>
                                <v-list-item-subtitle>Personal</v-list-item-subtitle>
                            </v-list-item-content>
                        </v-list-item>
                    </v-list>
                    <v-divider inset></v-divider>
                    <v-card-title>Availability({{date}})</v-card-title>
                    <v-card-text>
                        <v-chip-group
                            active-class="deep-purple accent-4 white--text"
                            column
                            v-model = "hourIndex"
                        >
                            <v-chip v-for="(hour,i) in hours"
                                    :key="i"
                            >{{ hour }}
                            </v-chip>
                        </v-chip-group>

                    </v-card-text>
                    <v-card-actions>
                        <v-btn
                            color="deep-purple lighten-1"
                            text
                            @click="addVisit"
                        >
                            Reserve
                        </v-btn>
                    </v-card-actions>
                </v-card>
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
                <v-btn color="error" @click="returnClicked"
                >
                    <v-icon
                        dark
                        left
                    >
                        mdi-arrow-left
                    </v-icon>Return</v-btn>
            </v-col>
        </v-row>

    </v-container>
</template>

<script>
 import axios from 'axios'
export default {

    data() {
        return {
            doctor: '',
            hours: [],
            selectedItem: undefined,
            hourIndex: '',
            imgSrc: '',
        }
    },
    computed: {
        doctors(){
            return this.$store.state.doctors
        },
        date(){
            return this.$store.state.date
        },
        patient(){
            return this.$store.state.patient
        }
    },
    methods: {
        getVisitHours(doctor) {
            this.doctor = doctor
            this.imgSrc = "http://localhost:8080/images/" + doctor.imageName
            axios.get('http://localhost:8080/visits/get',{
                params: {
                    date : this.date,
                    doctor_id : doctor.id
                }
            }).then((response) => {
                this.hours = response.data
                console.log(this.hours)
            })

        },
        addVisit() {
            const localDateTime = this.date + ' ' + this.hours[this.hourIndex];
               axios.post("http://localhost:8080/visits/add",{
                   patient_id: this.patient.id,
                   doctor_id : this.doctor.id,
                   date: localDateTime
               }).then((response)=>{
                   console.log(response)
                   this.hours.splice(this.hourIndex, 1)
               }).finally(()=>{
                       this.$toast.success('Visit saved.')
               }
               )
        },
        returnClicked(){
            this.$store.state.doctorsAchieved = false
            this.$store.state.patientSelected = false
            this.$store.state.date = ''
        }
    },
}
</script>