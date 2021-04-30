<template>
    <v-container fill-height>
        <v-row justify="center">
            <v-col cols="7">
                <MyCalendar :events="events" :breakDuration="breakDuration" :date="date"></MyCalendar>
            </v-col>

        <v-col cols="4">
            <SchedulePlanner @newEvents="newEvents" @breakDurationEvent="breakDurationEvent"></SchedulePlanner>
        </v-col>
        </v-row>
    </v-container>
</template>

<script>
import MyCalendar from "@/components/DoctorPanel/MySchedule/MyCalendar";
import SchedulePlanner from "@/components/DoctorPanel/MySchedule/SchedulePlanner";
export default {
    name: "MySchedule",
    components: {MyCalendar, SchedulePlanner},
    data(){
        return{
            events: null,
            breakDuration: '',
            date: ''
        }
    },
    methods: {
        newEvents(events){
            this.events = events
        },
        breakDurationEvent(breakDuration, date){
            this.breakDuration = breakDuration
            this.date = date
        }
    }
}
</script>

<style scoped>

</style>