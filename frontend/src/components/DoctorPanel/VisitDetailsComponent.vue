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
        Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.
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