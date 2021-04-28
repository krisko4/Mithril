<template>
<v-card>
    <v-card-title>Visit details</v-card-title>
    <v-card-subtitle>{{visitData.date}}</v-card-subtitle>
    <v-card-text>
    <v-tabs>
        <v-tab @click="showInterview">Interview</v-tab>
        <v-tab @click="showResearch">Research</v-tab>
        <v-tab @click="showReferrals">Referrals</v-tab>
        <v-tab @click="showMedicine">Medicine</v-tab>
    </v-tabs>
    </v-card-text>
    <v-card-text class="text--primary" v-if="tabIndex === 0">
       {{visitData.interview}}
    </v-card-text>
    <v-card-text class="text--primary" v-if="tabIndex === 1">
        {{visitData.research}}
    </v-card-text>
    <v-card-text class="text--primary" v-if="tabIndex === 2">
        {{visitData.referrals}}
    </v-card-text>
    <v-card-text class="text--primary" v-if="tabIndex === 3">
        {{visitData.medicine}}
    </v-card-text>
</v-card>
</template>

<script>
export default {
    name: "VisitDetailsComponent",
    props: {
        visitData: Object
    },
    data(){
      return{
          tabIndex: 0
      }
    },
    created() {
        console.log(this.visitData)
    },
    methods:{
        showInterview(){
            this.tabIndex = 0
        },
        showResearch(){
            this.tabIndex = 1
        },
        showReferrals(){
            this.tabIndex = 2
        },
        showMedicine(){
            this.tabIndex = 3
        }

    }
}
</script>

<style scoped>

</style>