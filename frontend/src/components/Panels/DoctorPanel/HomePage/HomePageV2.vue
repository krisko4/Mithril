<template>
    <v-row justify="center">
        <v-col cols="6" style="color: red">
            <v-row>
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


        </v-col>
    </v-row>
</template>

<script>
export default {
    name: "HomePageV2",
    data() {
        return {
            name: localStorage.getItem('firstName') || '',
            cardIndex: null,
            cards: [
                {title: 'My visits', src: 'http://localhost:8080/images/static/visit.jpg', flex: 6, index: 0},
                {title: 'My patients', src: 'http://localhost:8080/images/static/doctor-patient.jpg', flex: 6, index: 1},
                {title: 'My schedule', src: 'http://localhost:8080/images/static/schedule.jpg', flex: 6, index: 2},
                {title: 'Forum', src: 'http://localhost:8080/images/static/forum.png', flex: 6, index: 3},
            ],
        }
    },

    methods: {
        redirect(index) {
            console.log(index)
            this.cardIndex = index
            this.$emit('cardChosen', index)
        },

    }
}
</script>

<style scoped>

</style>