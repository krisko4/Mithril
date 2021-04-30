<template>

    <v-card shaped>
        <v-card-text>
            <v-toolbar flat class="mt-3">

                <v-btn color="primary" text outlined @click="type = 'day'">Day</v-btn>
                <v-btn color="primary" text outlined @click="type = 'week'">Week</v-btn>
                <v-btn color="primary" text outlined @click="type = 'month'">Month</v-btn>
                <v-btn
                    fab
                    text
                    small
                    color="grey darken-2"
                    @click="prev"
                >
                    <v-icon small>
                        mdi-chevron-left
                    </v-icon>
                </v-btn>
                <v-btn
                    fab
                    text
                    small
                    color="grey darken-2"
                    @click="next"
                >
                    <v-icon small>
                        mdi-chevron-right
                    </v-icon>
                </v-btn>
                <v-toolbar-title v-if="$refs.calendar">
                    {{ $refs.calendar.title }}
                </v-toolbar-title>

            </v-toolbar>
            <v-sheet height="500">
                <v-calendar
                    class="mt-5"
                    ref="calendar"
                    v-model="focus"
                    color="primary"
                    @click:more="viewDay"
                    @click:date="viewDay"
                    :type="type"
                    :interval-format="intervalFormat"
                    :first-interval=5
                    :interval-count=18
                    :events="visits"
                ></v-calendar>
            </v-sheet>
        </v-card-text>
    </v-card>

</template>

<script>


export default {
    name: "MyCalendar",

    props: {
        events: Array,
        breakDuration: String,
        date: String

    },


    data() {
        return {
            type: 'week',
            focus: '',
            temporaryEvents: [],
            temporaryEventsCopy: [],
            visits: []
        }
    },


    watch: {
        events(events) {
            // events - array with events i want to add
            // temporaryEvents - array containing previous events
            // visits - array with all events displayed in calendar
            // if temporaryEvents is not empty, replace my temporaryEvents with new events and show them in calendar
            // otherwise skip replacing part
            if (this.temporaryEvents.length > 0) {
                this.temporaryEvents.forEach((element) => {
                    this.visits.splice(this.visits.indexOf(element), 1)
                })
            }
            events.forEach((event) => {
                this.visits.push(event)
            })
            this.copyObjectArray(events, this.temporaryEvents)
            this.copyObjectArray(this.temporaryEvents, this.temporaryEventsCopy)
        },


        '$store.state.date'(date) {
            if (date) {
                this.type = 'day'
                this.focus = this.$store.state.date
                this.$store.state.date = null
            }

        },
        breakDuration() {
            // adding breaks between visits - for each temporaryEvent except for the first one, start and end hours are modified
             this.copyObjectArray(this.temporaryEventsCopy, this.temporaryEvents)
             let breakDuration = this.breakDuration
             this.temporaryEvents.forEach((element) => {
                if (this.temporaryEvents.indexOf(element) === 0) {
                    return
                }
                let index = this.temporaryEvents.indexOf(element)
                let startDate = new Date(element.start)
                let endDate = new Date(element.end)
                let newStartTime
                let newEndTime
                newStartTime = this.calculateNewHours(startDate, index, breakDuration)
                newEndTime = this.calculateNewHours(endDate, index, breakDuration)
                element.start = this.date + ' ' + newStartTime[0] + ':' + newStartTime[1]
                element.end = this.date + ' ' + newEndTime[0] + ':' + newEndTime[1]
            })
            console.log(this.visits)
            console.log(this.temporaryEvents)
            this.temporaryEvents.forEach((element) => {
                this.visits.splice(this.visits.indexOf(element), 1)
            })
            this.temporaryEvents.forEach((element) => {
                this.visits.push(element)
            })


        }
    },
    mounted() {
        this.$refs.calendar.checkChange()
        console.log(this.$refs.calendar.title)
    }
    ,
    methods: {

        copyObjectArray(oldArray, destinationArray){
            for(let i = 0; i < oldArray.length ; i++){
                const event = {
                    name: oldArray[i].name,
                    start: oldArray[i].start,
                    end: oldArray[i].end
                }
                destinationArray[i] = event
            }
        },

        calculateNewHours(date, index, breakDuration) {
            let newDate = new Date(date.getTime() + breakDuration * index * 60000)
            let newHour = newDate.getHours();
            if (newHour < 10) {
                newHour = '0' + newHour
            }
            let newMinute = newDate.getMinutes();
            if (newMinute < 10) {
                newMinute = '0' + newMinute
            }
            return [newHour, newMinute]
        },


        intervalFormat(interval) {
            return interval.time
        }
        ,

        viewDay({date}) {
            this.type = 'day'
            this.focus = date
        }
        ,
        prev() {
            this.$refs.calendar.prev()
        }
        ,
        next() {
            this.$refs.calendar.next()
        }
        ,
    }
}
</script>

<style scoped>

</style>