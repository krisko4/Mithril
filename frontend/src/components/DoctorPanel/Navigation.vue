<template>
    <v-navigation-drawer
        main
        absolute
        temporary
        v-model="drawer"

    >
        <v-list
            nav
            dense
        >
            <v-list-item>
                <v-list-item-avatar>
                    <v-img :src="imgSrc"></v-img>
                </v-list-item-avatar>
                <v-list-item-content>
                    <v-list-item-title>{{ name }}</v-list-item-title>
                </v-list-item-content>
            </v-list-item>

            <v-divider></v-divider>
            <v-list-item-group
                active-class="deep-purple--text text--accent-4"
            >
                <v-list-item @click="cardIndex = null; redirect(cardIndex)">
                    <v-list-item-icon>
                        <v-icon>mdi-home</v-icon>
                    </v-list-item-icon>
                    <v-list-item-title>Home</v-list-item-title>
                </v-list-item>
                <v-list-item>
                    <v-list-item-icon>
                        <v-icon>mdi-account</v-icon>
                    </v-list-item-icon>
                    <v-list-item-title>Account</v-list-item-title>
                </v-list-item>
                <v-list-item @click="cardIndex = 0; redirect(cardIndex)">
                    <v-list-item-icon>
                        <v-icon>mdi-calendar</v-icon>
                    </v-list-item-icon>
                    <v-list-item-title>My visits</v-list-item-title>
                </v-list-item>
                <v-list-item>
                    <v-list-item-icon>
                        <v-icon>mdi-calendar-heart</v-icon>
                    </v-list-item-icon>
                    <v-list-item-title @click="cardIndex = 2; redirect(cardIndex)">My schedule</v-list-item-title>
                </v-list-item>
                <v-list-item>
                    <v-list-item-icon>
                        <v-icon>mdi-account-heart</v-icon>
                    </v-list-item-icon>
                    <v-list-item-title @click="cardIndex = 1; redirect(cardIndex)">My patients</v-list-item-title>
                </v-list-item>

            </v-list-item-group>
        </v-list>
    </v-navigation-drawer>
</template>

<script>
export default {
    name: "Navigation",
    props: {
        navigationOpened: Boolean
    },
    data() {
        return {
            imgSrc: 'http://localhost:8080/images/doctors/' + localStorage.getItem('imageName'),
            cardIndex: null,
            name: localStorage.getItem('firstName'),
            drawer: this.navigationOpened

        }
    },
    watch: {
        navigationOpened() {
            this.drawer = true
        }
    },

    created(){
        if(localStorage.getItem('imageName') === null ){
            this.imgSrc = 'http://localhost:8080/images/static/avatar.png'
        }
    },


    methods: {
        redirect() {
            this.drawer = false
            this.$emit('navigationChosen', this.cardIndex)
        }
    },


}
</script>

<style scoped>

</style>