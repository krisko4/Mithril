<template>
    <v-container fluid>
        <v-row justify="center">
            <v-col cols="6">
                <Activities @cardChosen="cardChosen"></Activities>
            </v-col>
            <v-col cols="6">
               <NoticeBoard></NoticeBoard>
            </v-col>
        </v-row>
    </v-container>
</template>

<script>


import NoticeBoard from "@/components/DoctorPanel/HomePage/NoticeBoard";
import Activities from "@/components/DoctorPanel/HomePage/Activities";
export default {
    name: "HomePage",
    components: {Activities, NoticeBoard},

    methods: {
        cardChosen(index){
            this.$emit('cardChosen', index)
        }
    }


    }

</script>

<style scoped>

</style>