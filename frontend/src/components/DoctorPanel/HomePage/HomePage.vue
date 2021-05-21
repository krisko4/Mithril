<template>
    <v-container fluid>
        <v-row justify="center">
            <v-col cols="6">
                <TodayVisits @visitStarted="beginVisit"></TodayVisits>
            </v-col>
            <v-col cols="6">
               <NoticeBoard></NoticeBoard>
            </v-col>
        </v-row>
        <v-lazy
            :options="{
          threshold: 0.9
        }"
            min-height="400"
            transition="fade-transition"
        >
        <v-row justify="center">
            <v-col cols="6">
                <Activities @cardChosen="cardChosen"></Activities>
            </v-col>
            <v-col cols="6">
                <NoticeBoard></NoticeBoard>
            </v-col>
        </v-row>
        </v-lazy>
    </v-container>
</template>

<script>


import NoticeBoard from "@/components/DoctorPanel/HomePage/NoticeBoard";
import Activities from "@/components/DoctorPanel/HomePage/Activities";
import TodayVisits from "@/components/DoctorPanel/HomePage/TodayVisits";
export default {
    name: "HomePage",
    components: {TodayVisits, Activities, NoticeBoard},

    methods: {
        cardChosen(index){
            this.$emit('cardChosen', index)
        },
        beginVisit(patientData){
            this.$emit('visitStarted', patientData)
        }
    }


    }

</script>

<style scoped>

</style>