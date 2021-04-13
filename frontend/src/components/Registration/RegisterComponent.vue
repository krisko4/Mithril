<template>
    <RegisterStep1Component v-if="!step1Completed" @firstStepComplete="firstStepComplete"></RegisterStep1Component>
    <RegisterStep2Component v-else></RegisterStep2Component>
</template>

<script>
import RegisterStep1Component from "@/components/Registration/RegisterStep1Component";
import RegisterStep2Component from "@/components/Registration/RegisterStep2Component";
export default {
    data(){
      return{
          step1Completed: false,
      }
    },
    name: "RegisterComponent",
    components: {RegisterStep2Component, RegisterStep1Component},
    methods:{
        firstStepComplete(){
            this.step1Completed = true
            console.log(this.step1Completed)
        }
    }

}
</script>

<style scoped>

</style>