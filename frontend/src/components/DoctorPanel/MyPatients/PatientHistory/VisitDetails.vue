<template>
    <v-card>
        <v-list-item three-line>
            <v-list-item-content>
                <div class="overline mb-4">
                    visit details
                </div>
                <v-list-item-title class="headline mb-1">
                    Doctor: {{ visitData.doctorFullName }}
                </v-list-item-title>
                <v-list-item-subtitle>Date: {{ visitData.date }}</v-list-item-subtitle>
            </v-list-item-content>

            <v-list-item-avatar
                size="80"
            >
                <v-img :src="doctorImg"></v-img>
            </v-list-item-avatar>
        </v-list-item>


        <v-card-text>
            <v-tabs>
                <v-tab @click="showInterview">Interview</v-tab>
                <v-tab @click="showResearch">Research</v-tab>
                <v-tab @click="showReferrals">Referrals</v-tab>
                <v-tab @click="showMedicine">Medicine</v-tab>
            </v-tabs>
        </v-card-text>
        <v-card-text class="text--primary" v-if="tabIndex === 0">
            {{ visitData.interview }}
        </v-card-text>
        <v-card-text class="text--primary" v-if="tabIndex === 1">
            {{ visitData.research }}
        </v-card-text>
        <v-card-text class="text--primary" v-if="tabIndex === 2">
            {{ visitData.referrals }}
        </v-card-text>
        <v-card-text class="text--primary" v-if="tabIndex === 3">
            {{ visitData.medicine }}
        </v-card-text>
    </v-card>
</template>

<script>
export default {
    name: "VisitDetailsComponent",
    props: {
        visitData: Object
    },
    data() {
        return {
            tabIndex: 0,
            doctorImg: 'http://localhost:8080/images/doctors/' + this.visitData.doctorImg
        }
    },
    created() {
        console.log(this.visitData)
    },
    methods: {
        showInterview() {
            this.tabIndex = 0
        },
        showResearch() {
            this.tabIndex = 1
        },
        showReferrals() {
            this.tabIndex = 2
        },
        showMedicine() {
            this.tabIndex = 3
        }

    }
}
</script>

<style scoped>

</style>