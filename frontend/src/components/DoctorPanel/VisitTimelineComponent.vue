<template>
    <v-timeline
        dense
        align-top
    >
        <v-timeline-item
            v-for="item in myVisitArray"
            :key="item.index"
            color="primary"
            small
        >
            <v-row class="pt-1">
                <v-col cols="3">
                    <strong>{{ item.hour }}</strong>
                </v-col>
                <v-col>
                    <strong>{{ item.patientName }}</strong>
                    <div class="caption">
                        {{ item.description }}
                    </div>
                </v-col>
            </v-row>

        </v-timeline-item>
    </v-timeline>
</template>

<script>
export default {
    name: "VisitTimelineComponent",
    data(){
        return{
         //   visits: [{patient: 'Paweł Owsiak', hour: '10:30', reason: 'brak', index: 0},
             //   {patient: 'Paweł Owsiak', hour: '10:30', reason: 'brak', index: 1}
         //   ],
            myVisitArray: []
        }
    },
    props: {
        visits: Array
    },
    created(){
        console.log(this.visits)
        this.visits.forEach((element) => {
            const date = new Date(element.date)
            const hour = date.toTimeString().split(' ')[0]
            this.myVisitArray.push({hour: hour, patientName: element.patientName, description: element.description })
        })

    }

}
</script>

<style scoped>

</style>