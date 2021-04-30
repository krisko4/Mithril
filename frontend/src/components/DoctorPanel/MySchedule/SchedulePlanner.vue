<template>
    <v-card>
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
                    v-model="startingHour"
                    placeholder="09:00"
                    :rules="[() => !!startingHour || 'This field is required',
                    (v) => /^([0-9]{2})(:[0-9]{2}){1}$/.test(v) || 'Valid input format: hh:mm']"
                ></v-text-field>
                <v-text-field
                    hint="At what time would you like to finish your work?"
                    placeholder="16:00"
                    persistent-hint
                    v-mask="mask"
                    v-model="endingHour"
                    :rules="[() => !!endingHour || 'This field is required',
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
                    <v-btn :disabled="!valid || !date" color="primary">Submit</v-btn>
                </v-row>
            </v-col>

        </v-card-actions>


    </v-card>
</template>

<script>
import DatePicker from "@/components/VisitSaving/DatePicker";

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
    name: "SchedulePlanner",
    components: {DatePicker},
    data() {
        return {
            startingHour: '',
            endingHour: '',
            visitDuration: '',
            mask: timeMask,
            event: null,
            date: '',
            events: [],
            valid: false,
            breakDuration: '',
        }
    },
    methods: {

        dateChanged(date) {
            this.date = date
            this.emitVisits()
        },

        emitVisits() {
            setTimeout(() => {
                this.events = []
                this.$emit('newEvents', this.events)
                if (!this.valid) {
                    return
                }
                if (!this.date) {
                    this.$toast.warning('You have not specified a date')
                    return
                }
                let startDate = new Date(this.date + ' ' + this.startingHour);
                const endDate = new Date(this.date + ' ' + this.endingHour);
                let newStartDate = new Date(startDate.getTime() + this.visitDuration * 60000);
                let startingHour = this.startingHour
                while (endDate >= newStartDate) {
                    let hour = newStartDate.getHours();
                    if (hour < 10) {
                        hour = '0' + hour
                    }
                    let minute = newStartDate.getMinutes();
                    if (minute < 10) {
                        minute = '0' + minute
                    }
                    this.event = {
                        name: 'Visit',
                        start: this.date + ' ' + startingHour,
                        end: this.date + ' ' + hour + ':' + minute
                    }
                    this.events.push(this.event)
                    startingHour = hour + ':' + minute
                    startDate = new Date(this.date + ' ' + startingHour)
                    newStartDate = new Date(startDate.getTime() + this.visitDuration * 60000);
                }
                this.$emit('newEvents', this.events)
            }, 100)
        },
        emitBreak(){
            setTimeout(() => {
                if (!this.valid) {
                    return
                }
                this.$emit('breakDurationEvent', this.breakDuration, this.date)
            }, 100)
        }
    },
    watch: {

        valid() {
            console.log(this.valid)
        },


        startingHour() {
            this.emitVisits()
        },

        endingHour() {
            this.emitVisits()
        },

        visitDuration() {
            this.emitVisits()
        },

        breakDuration() {
            this.emitBreak()
        }
    }
}
</script>

<style scoped>

</style>