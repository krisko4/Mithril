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
                        <v-carousel height="300px"
                                    hide-delimiter-background
                                    v-model="itemIndex"
                                    v-if="isCarouselNotRefreshing"
                        >
                            <v-carousel-item v-model="dodo" v-for="(item, i) in items" :key="i"
                                             gradient="to top, rgba(0,0,0,.44), rgba(0,0,0,.44)"
                                             src="https://cdn.vuetifyjs.com/images/cards/forest.jpg">
                                <v-container class="fill-height">
                                    <v-row justify="center">
                                        <strong style="color: white"
                                                class="display-4 font-weight-regular mr-6">{{ item.day }}</strong>
                                    </v-row>
                                    <v-row justify="center">
                                        <div style="color: white" class="headline font-weight-light">
                                            {{ item.dayString }}
                                        </div>
                                        <div style="color: white" class="text-uppercase font-weight-light">
                                            {{ item.month }} {{ item.year }}
                                        </div>
                                    </v-row>

                                </v-container>

                            </v-carousel-item>
                        </v-carousel>
                        <v-container class="fill-height">
                            <v-card-text>
                                <VisitTimelineComponent :visits="visits"
                                                        v-if="isVisitListNotEmpty"></VisitTimelineComponent>
                                <strong v-else>You don't have any visits on {{ dayString }}. Enjoy your free time
                                    :)</strong>
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
    name: "DoctorVisitsComponent",
    components: {DatePickerComponent, VisitTimelineComponent},
    data() {
        return {
            itemIndex: 0,
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
            day: null,
            date: new Date(),
            dialog: false,
            isCarouselNotRefreshing: true,
            items: [],
            dodo: null
        }
    },
    created() {
        this.getDate(this.date)
        for (let i = 1; i < 4; i++) {
            const fullDate = new Date()
            const newDate = fullDate.setDate(fullDate.getDate() + i)
            this.getDate(new Date(newDate), null, null)
        }


    },
    watch: {

        '$store.state.date'() {
            console.log(this.$store.state.date)
            this.dialog = false
            this.date = new Date(this.$store.state.date)
            this.getDate(this.date)
            this.getVisits()
}
        },

        methods: {

            goBack() {
                this.$emit('goBack')
            },

            openCalendarTab() {
                this.isVisitListNotEmpty = false
                this.dialog = true
            },

            getVisits() {
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
                if (this.index === 1) {
                    const newDate = fullDate.setDate(fullDate.getDate() + 1)
                    this.date = new Date(newDate)
                    this.itemIndex = 1
                } else {
                    this.date = fullDate
                    this.itemIndex = 0
                }
                this.getVisits()

            },

            getDate(fullDate) {

                this.day = String(fullDate.getDate()).padStart(2, '0');
                const month = String(fullDate.getMonth() + 1).padStart(2, '0');
                this.year = fullDate.getFullYear();

                // date in yyyy-mm-dd format
                this.today = [this.year, month, this.day].join('-');

                const dateStringArray = fullDate.toDateString().split(' ');
                this.dayString = this.days.filter(element => element.startsWith(dateStringArray[0]))[0]
                this.month = this.months.filter(element => element.startsWith(dateStringArray[1]))[0]

                this.items.push(
                    {'day': this.day, 'dayString': this.dayString, 'month': this.month, 'year': this.year}
                )


            },

        }
    }
</script>

<style scoped>

</style>