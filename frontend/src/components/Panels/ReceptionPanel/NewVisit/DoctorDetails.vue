<template>
    <v-card flat class="d-flex flex-column mt-4" min-height="600px">
        <v-list-item three-line>
            <v-list-item-content>
                <div class="overline mb-4">
                    Date: {{ date }}
                </div>
                <div>
                <v-list-item-title class="headline">
                    {{ 'dr ' + doctor.firstName + ' ' + doctor.lastName }}
                </v-list-item-title>
                    <v-list-item-subtitle class="mt-3">proktolog</v-list-item-subtitle>
                </div>

            </v-list-item-content>
            <v-list-item-avatar
                size="150"
            >
                <v-img :src="imgSrc"></v-img>
            </v-list-item-avatar>
        </v-list-item>
        <v-card-text>
            <v-divider></v-divider>
            <v-list>
                <v-list-item>
                    <v-list-item-icon>
                        <v-icon color="primary">
                            mdi-phone
                        </v-icon>
                    </v-list-item-icon>

                    <v-list-item-content>
                        <v-list-item-title>{{ doctor.phone }}</v-list-item-title>
                        <v-list-item-subtitle>Phone number</v-list-item-subtitle>
                    </v-list-item-content>

                </v-list-item>
                <v-divider inset></v-divider>
                <v-list-item>
                    <v-list-item-icon>
                        <v-icon color="primary">
                            mdi-email
                        </v-icon>
                    </v-list-item-icon>
                    <v-list-item-content>
                        <v-list-item-title>{{ doctor.email }}</v-list-item-title>
                        <v-list-item-subtitle>E-mail address</v-list-item-subtitle>
                    </v-list-item-content>
                </v-list-item>
            </v-list>
            <v-divider inset></v-divider>
        </v-card-text>
        <v-card-title class="headline mb-1">Availability</v-card-title>
        <v-card-text>
            <v-chip-group
                color="primary"
                column
                v-model="hourIndex"
            >
                <v-chip v-for="(hour,i) in hours"
                        :key="i"
                >{{ hour }}
                </v-chip>
            </v-chip-group>
        </v-card-text>
        <v-card-actions>
            <v-spacer></v-spacer>
            <v-btn text color="primary" @click="addVisit">Reserve</v-btn>
        </v-card-actions>
    </v-card>
</template>

<script>
import axios, {tokenAxios} from "@/axios";

export default {
    name: "DoctorDetails",
    props: {
        patient: Object,
        doctor: Object,
        date: String
    },
    data() {
        return {
            hours: [],
            selectedItem: undefined,
            hourIndex: '',
            imgSrc: '',
        }
    },

    created(){
        this.imgSrc = "http://localhost:8080/images/doctors/" + this.doctor.imageName
        tokenAxios.get('doctors/' + this.doctor.id + '/visit-hours', {
            params: {
                date: this.date
            }
        }).then((response) => {
            this.hours = response.data
        })
    },

    methods: {
        addVisit() {
            const localDateTime = this.date + ' ' + this.hours[this.hourIndex];
            axios.post("visits/add", {
                patient_id: this.patient.id,
                doctor_id: this.doctor.id,
                date: localDateTime
            }).then((response) => {
                console.log(response)
                this.hours.splice(this.hourIndex, 1)
            }).finally(() => {
                    this.$toast.success('Visit saved.')
                }
            )
        },
        goBack() {
            this.$emit('returnClicked')
        }
    },
}
</script>

<style scoped>

</style>