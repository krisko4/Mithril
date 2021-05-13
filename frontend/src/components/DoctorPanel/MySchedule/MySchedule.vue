<template>
    <v-container fill-height>
        <v-row justify="center">
            <v-col cols="7">
                <MyCalendar @dayChosen="dayChosen"
                            :events="events"
                            :breakDuration="breakDuration"
                            :date="date"
                            :visitsSubmitted="visitsSubmitted">

                </MyCalendar>
            </v-col>

        <v-col cols="4">
            <SchedulePlanner :isScheduleAlreadyDeclared="isScheduleAlreadyDeclared" @newEvents="newEvents" @visitsSubmitted="setVisitsSubmitted" @breakDurationEvent="breakDurationEvent"></SchedulePlanner>
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
            date: '',
            isScheduleAlreadyDeclared: false,
            visitsSubmitted : false,
            test: 0

        }
    },
    methods: {
        newEvents(events, visitsSubmitted){
            this.events = events
            this.visitsSubmitted = visitsSubmitted
        },
        breakDurationEvent(breakDuration, date){
            this.breakDuration = breakDuration
            this.date = date
        },
        setVisitsSubmitted(visitsSubmitted){
            this.visitsSubmitted = visitsSubmitted
        },
        dayChosen(isScheduleAlreadyDeclared){
            this.isScheduleAlreadyDeclared = isScheduleAlreadyDeclared
        }
    }


}
</script>

<style scoped>

</style>