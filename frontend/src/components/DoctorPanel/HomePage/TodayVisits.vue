<template>
    <v-card height="765">
        <v-card-title class="text-h2 text-sm-h2 text-md-h2 text-lg-h2">Your schedule for today</v-card-title>
        <v-card-subtitle>{{date.toISOString().substr(0, 10)}}</v-card-subtitle>
        <v-divider class="mx-4"></v-divider>
        <v-card-subtitle class="text-h2 text-center text-sm-h2 text-md-h2 text-lg-h2">{{time}}</v-card-subtitle>
        <v-card-text>
            <v-sheet height="500" class="mt-2">
                <v-calendar
                    type="day"
                    :events="visits"
                    :interval-format="intervalFormat"
                    :first-interval=5
                    :interval-count=18
                    color="primary"
                    @click:event="showVisitDetails"
                >

                </v-calendar>
            </v-sheet>
        </v-card-text>
        <v-dialog transition="scale-transition" v-model="historyDialog" max-width="600">
            <PatientHistory @visitStarted="beginVisit" :visitChosen="visitChosen"  :patientData="patientData"></PatientHistory>
        </v-dialog>
    </v-card>
</template>

<script>
import axios from "axios";
import PatientHistory from "@/components/DoctorPanel/MyPatients/PatientHistory/PatientHistory";
export default {
    name: "TodayVisits",
    components: {PatientHistory},
    data(){
        return{
            historyDialog: false,
            patientData: '',
            interval: null,
            time: null,
            date: new Date(),
            visits: [],
            visitDetails: [],
            visitChosen: false,
        }
    },
    beforeDestroy() {
        clearInterval(this.interval)
    },
    watch: {
      historyDialog(val){
          if(!val){
              this.visitChosen = false
          }
      }
    },
    created() {
        this.interval = setInterval(() => {
            this.time = Intl.DateTimeFormat(navigator.language, {
                hour: 'numeric',
                minute: 'numeric',
                second: 'numeric'
            }).format()
        }, 1000)

        axios.get('http://localhost:8080/doctors/' + localStorage.getItem('id') + '/visits', {
            params: {
                date: this.convertDate(this.date)
            }
        }).then((response) => {
            console.log(response.data)
            response.data.filter((visit) => {
                let startDate = new Date(visit.date)
                let dateString = this.convertDate(startDate)
                let newDate = new Date(startDate.getTime() + visit.duration * 60000)
                let startHour = this.convertHours(startDate)
                let endHour = this.convertHours(newDate)
                this.visitDetails = response.data
                this.visits.push({
                    name: visit.patient.firstName + ' ' + visit.patient.lastName,
                    start: dateString + ' ' + startHour,
                    end: dateString + ' ' + endHour
                })
            })
        })


    },
    methods: {
        convertDate(date){
            return (new Date(date - (date).getTimezoneOffset() * 60000)).toISOString().substr(0,10)
        },
        convertHours(date){
            let newHour = date.getHours();
            if (newHour < 10) {
                newHour = '0' + newHour
            }
            let newMinute = date.getMinutes();
            if (newMinute < 10) {
                newMinute = '0' + newMinute
            }
            return newHour + ':' + newMinute
        },
        intervalFormat(interval) {
            return interval.time
        },
        showVisitDetails({event}){
            let foundEvent = this.visitDetails.find((visit) => {
                let dateString = visit.date.substring(0, 10) + ' ' + visit.date.substring(11, 16)
                return event.start === dateString
            })
            console.log(foundEvent)
            this.patientData = foundEvent.patient
            this.historyDialog = true
            this.visitChosen = true

        },
        beginVisit(){
            this.historyDialog = false
            this.$emit('visitStarted', this.patientData)
        }

    }

}
</script>

<style scoped>

</style>