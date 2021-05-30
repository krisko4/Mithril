<template>
    <v-card height="765"
    >
        <v-card-title class="text-h2 text-sm-h2 text-md-h2 text-lg-h2">Activities</v-card-title>
        <v-card-subtitle>Manage your visits, patients and schedule</v-card-subtitle>
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
                                height="244px"
                            >

                            </v-img>
                        </v-card>
                    </v-hover>
                </v-col>
            </v-row>
        </v-container>
    </v-card>
</template>

<script>
export default {
    name: "Activities",
    data() {
        return {
            name: localStorage.getItem('firstName') || '',
            cardIndex: null,
            cards: [
                {title: 'My visits', src: 'http://localhost:8080/images/static/visit.jpg', flex: 6, index: 2},
                {
                    title: 'My patients',
                    src: 'http://localhost:8080/images/static/doctor-patient.jpg',
                    flex: 6,
                    index: 4
                },
                {title: 'My schedule', src: 'http://localhost:8080/images/static/schedule.jpg', flex: 6, index: 3},
                {title: 'Interact', src: 'http://localhost:8080/images/static/forum.png', flex: 6, index: 3},
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