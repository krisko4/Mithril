<template>
    <v-card height="765">
            <v-card-title class="text-h2 text-sm-h2 text-md-h2 text-lg-h2">
                Notice board
            </v-card-title>
        <v-card-subtitle>The most recent news from your colleagues</v-card-subtitle>


        <v-divider class="mx-4"></v-divider>
        <v-card-text>

                    <v-timeline class="mb-2">
                        <v-timeline-item
                            v-for="(notice, i) in notices"
                            :key="i"
                            large
                        >

                            <template v-slot:opposite>
                                <span>{{notice.date}}</span>
                            </template>
                            <v-card class="elevation-2">
                                <v-card-title class="headline">
                                    {{ notice.title}}
                                </v-card-title>
                                <v-card-subtitle>
                                    {{ notice.doctorName }}
                                </v-card-subtitle>
                                <div v-if="notice.content.length > 100">
                                    <v-card-text>
                                        {{notice.content.substring(0,100)}}...</v-card-text>
                                    <v-card-actions>
                                        <v-btn text color="primary">Read more</v-btn>
                                    </v-card-actions>
                                </div>
                                <v-card-text v-else>
                                    {{notice.content}}
                                </v-card-text>

                            </v-card>
                        </v-timeline-item>
                    </v-timeline>
            <v-row justify="space-between">
                <v-btn :disabled="!olderTimeline" @click="loadNewerTimeline" text>
                    <v-icon>
                        mdi-arrow-left
                    </v-icon>
                </v-btn>
                <v-btn text class="mt-2 mr-2" @click="loadOlderTimeline">
                    <v-icon>
                        mdi-arrow-right
                    </v-icon>
                    </v-btn>
            </v-row>

        </v-card-text>
    </v-card>
</template>

<script>
import axios from "axios";

export default {
    name: "NoticeBoard",
    data(){
      return{
          notices: [],
          olderTimeline: false
      }
    },
    created(){
        axios.get('http://localhost:8080/notices').then((response) => {
            this.addNotices(response)
            console.log(this.notices)
        })
    },
    methods: {
        loadOlderTimeline(){
            axios.get('http://localhost:8080/notices', {
                params: {
                    date: this.notices[this.notices.length - 1].date
                }
            }).then((response) => {
                this.notices = []
                this.addNotices(response)
                this.olderTimeline = true
            })
        },
        loadNewerTimeline(){

        },
        addNotices(response){
            response.data.filter((notice) => {
                this.notices.push({
                    title: notice.title,
                    content: notice.content,
                    date: notice.date.substring(0,10) + ' ' +  notice.date.substring(11),
                    img: 'http://localhost:8080/images/doctors/' + notice.doctorDto.imageName,
                    doctorName: notice.doctorDto.firstName + ' ' + notice.doctorDto.lastName
                })
            })
        }
    },

}
</script>

<style scoped>

</style>