<template>
    <v-card min-height="880">
        <v-card-title class="text-h2 text-sm-h2 text-md-h2 text-lg-h2">Schedule</v-card-title>
        <v-card-subtitle>Your planned visits for today</v-card-subtitle>
        <v-divider class="mx-4"></v-divider>
        <v-card-subtitle class="text-center">
            <span class="text-h2 text-sm-h2 text-md-h2 text-lg-h2">{{time}}</span><br>{{date.toISOString().substr(0, 10)}}<span></span></v-card-subtitle>
        <v-card-text>
            <v-sheet height="600" class="mt-2">
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
        <v-dialog transition="scale-transition" v-model="historyDialog" max-width="700">
            <PatientHistory @visitStarted="beginVisit" :visitChosen="visitChosen" :selectedVisitDate="selectedVisitDate"  :patientData="patientData"></PatientHistory>
        </v-dialog>
    </v-card>
</template>

<script>
import {tokenAxios} from "@/axios";
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
            selectedVisitDate: '',
            selectedVisitDuration: 0
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

        tokenAxios.get('doctors/' + localStorage.getItem('id') + '/visits', {
            params: {
                date: this.convertDate(this.date),
                finished: false
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
            this.selectedVisitDate = foundEvent.date.substring(0, 10) + ' ' + foundEvent.date.substring(11, 16)
            this.selectedVisitDuration = foundEvent.duration

        },
        beginVisit(){
            this.historyDialog = false
            this.$emit('visitStarted', this.patientData, this.selectedVisitDate, this.selectedVisitDuration)
        }

    }

}
</script>

<style scoped>

</style>