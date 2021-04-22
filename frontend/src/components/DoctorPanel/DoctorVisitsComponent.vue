<template>
    <v-container fluid>
        <v-row justify="center">
            <v-col cols="4" align="center">
                <v-card
                >
                    <v-toolbar
                        tabs
                        flat
                    >
                        <v-app-bar-nav-icon
                        ></v-app-bar-nav-icon>
                        <v-toolbar-title>My visits</v-toolbar-title>
                    </v-toolbar>
                    <v-tabs
                        align-with-title
                    >
                        <v-tab v-for="tab in tabs" :key="tab.title" @click="openTab(tab)">
                            {{ tab.title }}
                        </v-tab>
                        <v-dialog
                            max-width="300"
                            v-model="dialog"
                        >
                            <template v-slot:activator="{ on, attrs }" v-bind="attrs"
                                      v-on="on">
                        <v-tab @click="openCalendarTab">
                            Calendar
                        </v-tab>
                            </template>
                                <template>
                                <DatePickerComponent></DatePickerComponent>
                                </template>
                        </v-dialog>

                        <v-tabs-slider color="primary"></v-tabs-slider>
                    </v-tabs>
                    <v-divider></v-divider>
                    <v-container ma-0 pa-0 v-show="index !== 2">
                    <v-img
                        src="https://cdn.vuetifyjs.com/images/cards/forest.jpg"
                        gradient="to top, rgba(0,0,0,.44), rgba(0,0,0,.44)"
                        height="300"
                    >
                        <v-container class="fill-height">
                            <v-row justify="center">
                                <strong style="color: white" class="display-4 font-weight-regular mr-6">{{ day }}</strong>
                            </v-row>
                            <v-row justify="center">
                                <div style="color: white" class="headline font-weight-light">
                                    {{ dayString }}
                                </div>
                                <div style="color: white" class="text-uppercase font-weight-light">
                                    {{ month }} {{year}}
                                </div>
                            </v-row>

                    </v-container>
                    </v-img>

                    <v-container class="fill-height">
                    <v-card-text>
                        <VisitTimelineComponent :visits="visits" v-if="isVisitListNotEmpty"></VisitTimelineComponent>
                        <strong v-else>You don't have any visits on {{dayString}}. Enjoy your free time :)</strong>
                    </v-card-text>
                        <v-card-actions>
                            <v-btn text color="primary" @click="goBack">Return</v-btn>
                        </v-card-actions>
                    </v-container>
                    </v-container>
                    <v-row justify="center">
                    </v-row>
                </v-card>
            </v-col>
        </v-row>
    </v-container>

</template>

<script>

import VisitTimelineComponent from "@/components/DoctorPanel/VisitTimelineComponent";
import axios from "axios";
import DatePickerComponent from "@/components/VisitSaving/DatePickerComponent";


export default {
    name: "DoctorScheduleComponent",
    components: {DatePickerComponent, VisitTimelineComponent},
    data() {
        return {
            tabs: [{title: 'Today'}, {title: 'Tomorrow'}],
            days: ['Monday', 'Tuesday', 'Wednesday', 'Thursday', 'Friday', 'Saturday', 'Sunday'],
            months: ["January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"],
            dayString: '',
            month: '',
            isVisitListNotEmpty: false,
            year: null,
            index: null,
            chosenTab: null,
            today: '',
            visits: null,
            isCalendarVisible: false,
            day:null,
            date: new Date(),
            dialog: false
        }
    },
    created() {
        this.getDate(this.date)
    },
    watch:{
        date(val){
            this.getDate(val)
        },
        '$store.state.date'(){
            console.log(this.$store.state.date)
            this.dialog = false
            this.date = new Date(this.$store.state.date)
            this.getDate(this.date)
            this.getVisits()
}
    },

    methods: {

        goBack(){
            this.$emit('goBack')
        },

        openCalendarTab(){
            this.isVisitListNotEmpty = false
            this.dialog = true
        },

        getVisits(){
            axios.get('http://localhost:8080/visits/get/all', {
                params: {
                    doctor_id: localStorage.getItem('id'),
                    date: this.date
                }
            }).then((response) => {
                this.visits = response.data
                this.isVisitListNotEmpty = response.data.length !== 0;
            })
        },

        openTab(tab) {
            this.index = this.tabs.indexOf(tab)
            const fullDate = new Date()
            if(this.index === 1) {
                const newDate = fullDate.setDate(fullDate.getDate() + 1)
                this.date = new Date(newDate)
            }
            else{
                this.date = fullDate
            }
            this.getVisits()

        },

        getDate(fullDate){
            const day = String(fullDate.getDate()).padStart(2, '0');
            this.day = day
            const month = String(fullDate.getMonth() + 1).padStart(2, '0');
            const year = fullDate.getFullYear();
            this.year = year
            this.today = [year, month, day].join('-');
            const dateStringArray = fullDate.toDateString().split(' ');
            this.dayString = this.days.filter(element => element.startsWith(dateStringArray[0]))[0]
            this.month = this.months.filter(element => element.startsWith(dateStringArray[1]))[0]
        },

    }
}
</script>

<style scoped>

</style>