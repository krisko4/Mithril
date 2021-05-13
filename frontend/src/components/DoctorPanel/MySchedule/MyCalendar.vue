<template>

    <v-card shaped>
        <v-card-text>
            <v-toolbar flat class="mt-3">


                <v-btn color="primary" text outlined @click="chooseCalendarType('day')">Day</v-btn>
                <v-btn color="primary" text outlined @click="chooseCalendarType('week')">Week</v-btn>
                <v-btn color="primary" text outlined @click="chooseCalendarType('month')">Month</v-btn>
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
            <v-row justify="end">
                <v-btn color="primary" :disabled="!isScheduleAlreadyDeclared" class="mr-10">edit</v-btn>
            </v-row>

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
                    :event-color="getEventColor"
                    @click:event="showEvent"

                ></v-calendar>
                <v-menu
                    v-model="selectedOpen"
                    :close-on-content-click="false"
                    :activator="selectedElement"
                    offset-x
                    max-width="400px"
                >
                    <v-card

                        color="grey lighten-4"
                        flat
                    >
                        <v-toolbar
                            :color="selectedEvent.color"
                            dark
                        >
                            <v-btn icon>
                                <v-icon>mdi-pencil</v-icon>
                            </v-btn>
                            <v-toolbar-title v-html="selectedEvent.name"></v-toolbar-title>
                            <v-spacer></v-spacer>
                            <v-btn icon>
                                <v-icon>mdi-heart</v-icon>
                            </v-btn>
                            <v-btn icon>
                                <v-icon>mdi-dots-vertical</v-icon>
                            </v-btn>
                        </v-toolbar>
                        <v-card-text>
                            <span v-html="selectedEvent.details"></span>
                        </v-card-text>
                        <v-card-actions>
                            <v-btn
                                text
                                color="secondary"
                                @click="selectedOpen = false"
                            >
                                Cancel
                            </v-btn>
                        </v-card-actions>
                    </v-card>
                </v-menu>
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
        date: String,
        visitsSubmitted: Boolean

    },


    data() {
        return {
            type: 'week',
            focus: '',
            temporaryEvents: [],
            temporaryEventsCopy: [],
            visits: [],
            dragEvent: null,
            dragStart: null,
            createEvent: null,
            createStart: null,
            extendOriginal: null,
            selectedEvent: {},
            selectedElement: null,
            selectedOpen: false,
            isScheduleAlreadyDeclared: false


        }
    },


    computed: {
        areVisitsSubmitted() {
            return this.visitsSubmitted
        }

    },


    watch: {

        events(events) {
            if (this.areVisitsSubmitted) {
                this.visits = this.visits.concat(events)
                return
            }
            if (this.temporaryEvents.length > 0) {
                this.temporaryEvents.filter((value) => {
                    this.visits.splice(this.visits.indexOf(value), 1)
                })
            }
            if (!events) {
                this.temporaryEvents = []
                return
            }
            this.visits = this.visits.concat(events)
            this.temporaryEvents = events.map((event) => {
                return event
            })

        },


        '$store.state.date'(date) {
            if (date) {
                this.type = 'day'
                this.focus = this.$store.state.date
                this.$store.state.date = null
                this.isScheduleAlreadyDeclared = this.checkIfScheduleAlreadyDeclared(this.visits, date)
                console.log(this.isScheduleAlreadyDeclared)
                this.$emit('dayChosen', this.isScheduleAlreadyDeclared)
            }

        },

    },
    mounted() {
        this.$refs.calendar.checkChange()
        //  console.log(this.$refs.calendar.title)
    }
    ,
    methods: {

        chooseCalendarType(type){
            this.type=type
            if(type !== 'day'){
                this.isScheduleAlreadyDeclared = false
            }

        },



        showEvent({nativeEvent, event}) {
            const open = () => {
                this.selectedEvent = event
                this.selectedElement = nativeEvent.target
                setTimeout(() => {
                    this.selectedOpen = true
                }, 10)
            }

            if (this.selectedOpen) {
                this.selectedOpen = false
                setTimeout(open, 10)
            } else {
                open()
            }

            nativeEvent.stopPropagation()
        },

        getEventColor(event) {
            const rgb = parseInt(event.color.substring(1), 16)
            const r = (rgb >> 16) & 0xFF
            const g = (rgb >> 8) & 0xFF
            const b = (rgb >> 0) & 0xFF

            return event === this.dragEvent
                ? `rgba(${r}, ${g}, ${b}, 0.7)`
                : event === this.createEvent
                    ? `rgba(${r}, ${g}, ${b}, 0.7)`
                    : event.color
        },


        intervalFormat(interval) {
            return interval.time
        },



        checkIfScheduleAlreadyDeclared(arr, date) {
            let myDate = new Date(date)
            return arr.some(function (event) {
                let eventDate = new Date(event.start)
                return eventDate.getDate() === myDate.getDate()
                    && eventDate.getMonth() === myDate.getMonth()
                    && eventDate.getFullYear() === myDate.getFullYear()
            });
        },


        viewDay({date}) {
            this.$store.state.date = date

         //   this.type = 'day'
         //   this.focus = date
         //   this.isScheduleAlreadyDeclared = this.checkIfScheduleAlreadyDeclared(this.visits, date)
         //   this.$emit('dayChosen', this.isScheduleAlreadyDeclared)
        },
        prev() {
            this.$refs.calendar.prev()
        },
        next() {
            this.$refs.calendar.next()
        },
    }
}
</script>

<style scoped>

</style>