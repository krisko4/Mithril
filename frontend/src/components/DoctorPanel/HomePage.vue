<template>
    <v-container fluid>
        <v-row justify="center">
            <v-col cols="5" align="center">
                <v-card
                >
                    <v-card-title class="text-h2 text-sm-h2 text-md-h2 text-lg-h2">Hello, {{ name }}</v-card-title>
                    <v-card-subtitle class="text-h6 text-sm-h5 secondary--text mt-4 w-full w-md-8-12 w-xl-half mx-auto">Good to see you!</v-card-subtitle>
                    <v-divider class="mx-4"></v-divider>
                    <v-container fluid>
                        <v-row dense>

                            <v-col
                                v-for="card in cards"
                                :key="card.title"
                                :cols="card.flex"
                            >
                                <v-hover
                                    v-slot="{hover}">
                                    <v-card
                                        :elevation="hover ? 16 : 2"
                                        @click="redirect(card.index)"
                                    >

                                        <v-card-title v-text="card.title"></v-card-title>
                                        <v-img
                                            :src="card.src"
                                            class="white--text align-end"
                                            gradient="to bottom, rgba(0,0,0,.1), rgba(0,0,0,.5)"
                                            height="200px"
                                        >

                                        </v-img>
                                    </v-card>
                                </v-hover>
                            </v-col>
                        </v-row>
                    </v-container>
                </v-card>
            </v-col>
        </v-row>
    </v-container>
</template>

<script>
//import axios from "axios";


export default {
    name: "HomePage",
    data() {
        return {
            name: localStorage.getItem('firstName') || '',
            cardIndex: null,
            cards: [
                {title: 'My visits', src: 'http://localhost:8080/images/static/visit.jpg', flex: 6, index: 0},
                {title: 'My patients', src: 'http://localhost:8080/images/static/doctor-patient.jpg', flex: 6, index: 1},
                {title: 'My schedule', src: 'http://localhost:8080/images/static/schedule.jpg', flex: 6, index: 2},
                {title: 'Interact', src: 'http://localhost:8080/images/static/forum.png', flex: 6, index: 3},
            ],
        }
    },
    methods: {
        redirect(index) {
            console.log(index)
            this.cardIndex = index
            this.$emit('cardChosen', index)


            /*      axios.get('http://localhost:8080/jwt', {
                      headers: {
                          Authorization : 'Bearer ' + localStorage.getItem('user')
                      }
                  }).then(((response)=>{
                      console.log(response)
                  }))*/
        },

    }
}
</script>

<style scoped>

</style>