<template>
    <div>
        <transition appear name="test" mode="out-in">
            <v-app v-if="!messengerOpened && cardIndex !== 6" style="background-color: whitesmoke">
                <v-main>
                    <Navigation :navigationOpened="navigationOpened" @navigationChosen="navigationChosen"></Navigation>
                    <v-img
                        gradient="to top, rgba(255, 255, 255, 0.3), rgba(255, 255, 255, 0.3)"
                        height="700px"
                        src="https://rapidoffice.furniture/wp-content/uploads/2018/11/multi-front-zion-desk.jpg"
                    >

                        <v-toolbar style="background-color: transparent">
                            <v-app-bar-nav-icon @click=openNavigationDrawer></v-app-bar-nav-icon>
                            <v-toolbar-title>Title</v-toolbar-title>
                            <v-row justify="end" class="mr-4">
                                <v-btn @click="messengerOpened = true" text>Messenger</v-btn>
                                <v-btn text>My account</v-btn>
                                <v-btn text @click="logout">Sign out</v-btn>
                            </v-row>
                        </v-toolbar>
                        <v-container fill-height>
                            <v-container>
                                <v-row>
                                    <div class="text-uppercase  font-weight-bold mb-4 display-3">Hello, {{ name }}</div>
                                </v-row>
                                <v-row>
                                    <div class="mb-4 display-2 font-weight-light">Welcome to the reception panel
                                    </div>
                                </v-row>
                                <v-row>
                                    <div class="mb-4 display-1 font-weight-light font-italic">Enjoy your work</div>
                                </v-row>
                            </v-container>
                        </v-container>
                    </v-img>

                <v-main class="mt-4">
                    <HomePage @cardChosen="cardChosen" v-if="cardIndex === 0"></HomePage>
                    <NewVisit v-if="cardIndex === 2"></NewVisit>
                </v-main>
                </v-main>
                <Footer></Footer>
            </v-app>
        </transition>
    </div>
</template>

<script>
import Footer from "@/components/Footer";

import HomePage from "@/components/Panels/ReceptionPanel/HomePage/HomePage";
import NewVisit from "@/components/Panels/ReceptionPanel/NewVisit/NewVisit";
import Navigation from "@/components/Panels/ReceptionPanel/Navigation";

export default {
    name: "ReceptionPanel",
    components: {HomePage, Footer, Navigation, NewVisit},
    data() {
        return {
            messengerOpened: false,
            isActive: false,
            patientData: '',
            cardIndex: 0,
            navigationOpened: false,
            selectedVisitDate: '',
            selectedVisitDuration: 0,
            name: localStorage.getItem('firstName'),

        }
    },

    methods: {
        navigationChosen(index) {
            this.cardIndex = index
        },
        cardChosen(index) {
            this.cardIndex = index
        },

        logout() {
            localStorage.removeItem('user')
            localStorage.removeItem('id')
            localStorage.removeItem('email')
            localStorage.removeItem('firstName')
            if (localStorage.getItem('imageName') != null) {
                localStorage.removeItem('imageName')
            }
            this.$store.commit('signOut')
            this.$router.push({name: 'login'})
        },
        openNavigationDrawer() {
            this.navigationOpened = !this.navigationOpened
        },
        loadHomeComponent() {
            this.cardIndex = 0
        },
    },

}

</script>

<style scoped>

</style>