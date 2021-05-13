<template>
    <v-card :disabled="isScheduleAlreadyDeclared">
        <v-card-title>Schedule planner</v-card-title>
        <v-card-subtitle>Plan your working hours</v-card-subtitle>
        <v-divider class="ml-3 mr-3"></v-divider>
        <v-card-text>
            <v-row justify="center">
                <DatePicker @dateChanged="dateChanged" class="mt-2"></DatePicker>
            </v-row>
        </v-card-text>
        <v-card-text>
            <v-form v-model="valid">
                <v-text-field
                    hint="At what time would you like to start your work?"
                    persistent-hint
                    v-mask="mask"
                    v-model="startHour"
                    placeholder="09:00"
                    :rules="[() => !!startHour || 'This field is required',
                    (v) => /^([0-9]{2})(:[0-9]{2}){1}$/.test(v) || 'Valid input format: hh:mm']"
                ></v-text-field>
                <v-text-field
                    hint="At what time would you like to finish your work?"
                    placeholder="16:00"
                    persistent-hint
                    v-mask="mask"
                    v-model="endHour"
                    :rules="[() => !!endHour || 'This field is required',
                    (v) => /^([0-9]{2})(:[0-9]{2}){1}$/.test(v) || 'Valid input format: hh:mm']"
                ></v-text-field>
                <v-row justify="space-between">
                    <v-col cols="6">
                        <v-text-field
                            hint="How long would you like your visit to last?"
                            persistent-hint
                            placeholder="Visit duration(minutes)"
                            v-model="visitDuration"
                            :rules="[() => !!visitDuration || 'This field is required', (v) => v > 10 || 'value must be greater than 10']"
                            v-mask="'X#'"
                        >
                        </v-text-field>
                    </v-col>
                    <v-col cols="6">
                        <v-text-field
                            hint="Would you like to set a break between each visit?"
                            persistent-hint
                            placeholder="Break duration(minutes)"
                            v-model="breakDuration"
                            v-mask="'X#'"
                        >
                        </v-text-field>
                    </v-col>
                </v-row>
            </v-form>
        </v-card-text>
        <v-card-actions>
            <v-col>
                <v-row justify="end">
                    <v-btn :disabled="!valid || !date" color="primary" @click="submitVisits">Submit</v-btn>
                </v-row>
            </v-col>

        </v-card-actions>


    </v-card>
</template>

<script>
import DatePicker from "@/components/VisitSaving/DatePicker";
import axios from "axios";

export function timeMask(value) {
    const hours = [
        /[0-2]/,
        value.charAt(0) === '2' ? /[0-3]/ : /[0-9]/,
    ];
    const minutes = [/[0-5]/, /[0-9]/];
    return value.length > 2
        ? [...hours, ':', ...minutes]
        : hours;
}


export default {
    props: {
      isScheduleAlreadyDeclared: Boolean
    },
    name: "SchedulePlanner",
    components: {DatePicker},
    data() {
        return {
            startHour: '',
            endHour: '',
            visitDuration: '',
            startDate: '',
            endDate: '',
            mask: timeMask,
            //  event: null,
            date: '',
            events: [],
            valid: false,
            breakDuration: '',
            colors: ['#2196F3', '#3F51B5', '#673AB7', '#00BCD4', '#4CAF50', '#FF9800', '#757575'],
            visitsSubmitted: false,


        }
    },



    created() {

        axios.get('http://localhost:8080/doctors/' + localStorage.getItem('id') + '/schedules')
            .then(response => {

                let allEvents = []
                this.visitsSubmitted = true
                response.data.filter((schedule) => {
                    let events = this.setVisits(
                        schedule.date,
                        schedule.startHour,
                        schedule.endHour,
                        schedule.visitDuration,
                        schedule.breakDuration
                    )
                    allEvents = allEvents.concat(events)
                })
                this.$emit('newEvents', allEvents, this.visitsSubmitted)
            })


    },

    methods: {

        submitVisits() {
            axios.post('http://localhost:8080/doctors/' + localStorage.getItem('id') + '/schedules', {
                date: this.date,
                startHour: this.startHour,
                endHour: this.endHour,
                visitDuration: this.visitDuration,
                breakDuration: this.breakDuration
            }, {
                headers: {
                    'Authorization': 'Bearer ' + localStorage.getItem('user')
                }
            }).then(() => {
                this.setEvents(false)
            }).finally(() => {
                setTimeout(() => {
                    this.$toast.success('Visits submitted successfully')
                }, 200)

            })
        },

        dateChanged(date) {
            this.date = date
        //    if (this.isFormValid) {
            //    this.setEvents()
        //    }

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

        setVisits(date, startHour, endHour, visitDuration, breakDuration) {
            let events = []
            // this.events = []
            //  this.$emit('newEvents', this.events)
            if (!date) {
                this.$toast.warning('You have not specified a date')
                return
            }
            this.startDate = new Date(date + ' ' + startHour);
            this.endDate = new Date(date + ' ' + endHour);
            let newStartDate = new Date(this.startDate.getTime() + visitDuration * 60000);
            let startingHour = startHour
            while (this.endDate >= newStartDate) {
                let hour = newStartDate.getHours();
                if (hour < 10) {
                    hour = '0' + hour
                }
                let minute = newStartDate.getMinutes();
                if (minute < 10) {
                    minute = '0' + minute
                }
                let event = {
                    name: 'Visit',
                    start: date + ' ' + startingHour,
                    end: date + ' ' + hour + ':' + minute,
                    color: this.rndElement(this.colors),

                }
                //  this.events.push(this.event)
                events.push(event)
                startingHour = hour + ':' + minute
                this.startDate = new Date(date + ' ' + startingHour)
                newStartDate = new Date(this.startDate.getTime() + visitDuration * 60000);
            }
            events = this.setBreak(events, breakDuration, date)
            return events
        },


        rnd(a, b) {
            return Math.floor((b - a + 1) * Math.random()) + a
        },
        rndElement(arr) {
            return arr[this.rnd(0, arr.length - 1)]
        },

        setBreak(noBreakEvents, breakDuration, date) {
            if (breakDuration) {
                let events = noBreakEvents.map((event) => {
                    return Object.assign({}, event)
                })
                let eventsCopy = []
                events.filter((element) => {
                    if (events.indexOf(element) === 0) {
                        eventsCopy.push(element)
                        return
                    }
                    let index = events.indexOf(element)
                    let startDate = new Date(element.start)
                    let endDate = new Date(element.end)
                    let newStartTime
                    let newEndTime
                    newStartTime = this.calculateNewHours(startDate, index, breakDuration)
                    newEndTime = this.calculateNewHours(endDate, index, breakDuration)
                    element.start = date + ' ' + newStartTime[0] + ':' + newStartTime[1]
                    element.end = date + ' ' + newEndTime[0] + ':' + newEndTime[1]
                    startDate = new Date(element.start)
                    endDate = new Date(element.end)
                    if (startDate < this.endDate && endDate < this.endDate) {
                        eventsCopy.push(element)
                    }

                })
                //   this.$emit('newEvents', eventsCopy, this.visitsSubmitted)
                return eventsCopy
            }
            return noBreakEvents
            //  this.$emit('newEvents', this.events, this.visitsSubmitted)


            //  this.$emit('breakDurationEvent', this.breakDuration, this.date)
        },

        setEvents(visitsSubmitted) {
            this.visitsSubmitted = visitsSubmitted
            let events = this.setVisits(
                this.date,
                this.startHour,
                this.endHour,
                this.visitDuration,
                this.breakDuration
            )
            this.$emit('newEvents', events, this.visitsSubmitted)
        }
    },

    computed: {

        isFormValid() {
            return !!(this.isStartingHourValid && this.isEndingHourValid && this.isVisitDurationValid && this.date);
        },
        isStartingHourValid() {
            return !!(this.startHour.match(/^([0-9]{2})(:[0-9]{2}){1}$/) && this.startHour);
        },
        isEndingHourValid() {
            return !!(this.endHour.match(/^([0-9]{2})(:[0-9]{2}){1}$/) && this.endHour);
        },
        isVisitDurationValid() {
            return !!(this.visitDuration > 10 && this.visitDuration);
        }


    },

    watch: {



        valid() {
            if (this.isFormValid) {
                this.setEvents(false)
                return
            }
            this.$emit('newEvents')

        },

        breakDuration() {
            if (this.isFormValid) {
                this.setEvents(false)
            }

        }
    }
}
</script>

<style scoped>

</style>