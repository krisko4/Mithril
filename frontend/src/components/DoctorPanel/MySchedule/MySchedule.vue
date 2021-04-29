<template>
    <v-container fill-height>
        <v-row justify="center">
            <v-col cols="7">
                <MyCalendar :events="events"></MyCalendar>
            </v-col>

        <v-col cols="4">
            <SchedulePlanner @newEvents="newEvents"></SchedulePlanner>
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
            events: null
        }
    },
    methods: {
        newEvents(events){
            this.events = events
        },
    }
}
</script>

<style scoped>

</style>