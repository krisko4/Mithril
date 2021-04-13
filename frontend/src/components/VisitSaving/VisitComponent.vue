<template>
    <DoctorComponent
        v-if="doctorsAchieved"
    />
    <DatePatientComponent
        v-else
    ></DatePatientComponent>
</template>

<script>
import DoctorComponent from "@/components/VisitSaving/DoctorComponent";
import DatePatientComponent from "@/components/VisitSaving/DatePatientComponent";
export default {
    name: "VisitComponent",
    components:{
      DoctorComponent,
      DatePatientComponent
    },
    computed: {
        doctorsAchieved(){
            return this.$store.state.doctorsAchieved
        },
    }
}
</script>

<style scoped>

</style>