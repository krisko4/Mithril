<template>

                <v-card shaped >
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

    props:{
      events: Array
    },



    data() {
        return {
            type: 'week',
            focus: '',
            temporaryEvents: [],
            visits: []
        }
    },


        watch:{
            events(events){
                console.log('jestem')
                if(this.temporaryEvents.length === 0){
                    console.log('jestem')
                    this.temporaryEvents = events
                    this.events.forEach((event) =>{
                        this.visits.push(event)
                    })
                    return
                }
                this.temporaryEvents.forEach((element) =>{
                    this.visits.splice(this.visits.indexOf(element), 1)
                })
                this.events.forEach((event) =>{
                    this.visits.push(event)
                })
                this.temporaryEvents = events
            },
            '$store.state.date'(date)
            {
                if(date){
                    this.type = 'day'
                    this.focus = this.$store.state.date
                    this.$store.state.date=null
                }

            }
        }
    ,
        mounted()
        {
            this.$refs.calendar.checkChange()
            console.log(this.$refs.calendar.title)
        }
    ,
        methods : {

            newEvent(event){
                console.log(event)
            },

            intervalFormat(interval)
            {
                return interval.time
            }
        ,

            viewDay({date})
            {
                this.type = 'day'
                this.focus = date
            }
        ,
            prev()
            {
                this.$refs.calendar.prev()
            }
        ,
            next()
            {
                this.$refs.calendar.next()
            }
        ,
        }
    }
</script>

<style scoped>

</style>