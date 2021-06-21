<template>
    <v-card min-height="880"
    >
        <v-card-title class="text-h2 text-sm-h2 text-md-h2 text-lg-h2">Activities</v-card-title>
        <v-card-subtitle>Manage your patients and reserve new visits conveniently</v-card-subtitle>
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
                            <v-card-subtitle v-text="card.subtitle"></v-card-subtitle>
                            <v-img
                                :src="card.src"
                                class="white--text align-end"
                                gradient="to bottom, rgba(0,0,0,.1), rgba(0,0,0,.5)"
                                height="280px"
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
                {title: 'Patients', subtitle: 'Browse through patients and their recent visits in your clinic',  src: 'https://msd20-assets.s3.amazonaws.com/wp-content/uploads/2020/08/26165036/2.6_Patient-Engagement.jpg', flex: 6, index: 1},
                {
                    title: 'New visit',
                    subtitle: 'Register a new visit for patient in your clinic',
                    src: 'http://localhost:8080/images/static/visit.jpg',
                    flex: 6,
                    index: 2
                },
                {title: 'Interact',  subtitle: 'Contact your colleagues via chat', src: 'http://localhost:8080/images/static/forum.png', flex: 6, index: 3},
            ],
        }
    },
    methods: {
        redirect(index) {
            this.cardIndex = index
            this.$emit('cardChosen', index)

        },
    }
}
</script>

<style scoped>

</style>