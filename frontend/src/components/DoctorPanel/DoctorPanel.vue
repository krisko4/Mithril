<template>
<v-app style="background-color: whitesmoke">
    <v-main>
    <Navigation :navigationOpened="navigationOpened" @navigationChosen="navigationChosen"></Navigation>
       <v-img
           gradient="to top, rgba(255, 255, 255, 0.3), rgba(255, 255, 255, 0.3)"
           height="700px"
           src="https://imageproxy.themaven.net/https%3A%2F%2Fs3-us-west-2.amazonaws.com%2Fmaven-user-photos%2Fcancerconnect%2Fcolon-cancer%2FgdZ0-CoMmkOJnjahj_em8A%2FofYjfdSPzkSkUXeC9Sr3nQ"
       >

            <v-toolbar style="background-color: transparent">
                <v-app-bar-nav-icon @click=openNavigationDrawer></v-app-bar-nav-icon>
                <v-toolbar-title>Title</v-toolbar-title>
                <v-row justify="end" class="mr-4" >
                    <v-btn text @click="logout">Sign out</v-btn>
                </v-row>
            </v-toolbar>
            <v-container fill-height>
                <v-container>
                <v-row>
                    <div class="text-uppercase  font-weight-bold mb-4 display-3">Hello, {{name}}</div>
                </v-row>
                <v-row>
                    <div class="mb-4 display-2 font-weight-light">Welcome to your personal doctor panel</div>
                </v-row>
                    <v-row>
                        <div class="mb-4 display-1 font-weight-light font-italic">Enjoy your work</div>
                    </v-row>
                </v-container>
            </v-container>

       </v-img>

    <v-main class="mt-4"
    >
        <transition name="fade">
        <HomePage  @cardChosen="cardChosen" @visitStarted="beginVisit" v-if="cardIndex === null" ></HomePage>
        </transition>
        <VisitCard @goBack="loadHomeComponent" v-if="cardIndex === 0"></VisitCard>
        <transition name="fade">
        <MySchedule v-if="cardIndex === 2"></MySchedule>
        </transition>
        <PatientTable v-if="cardIndex === 1" @goBack="loadHomeComponent"></PatientTable>
        <Visit :selectedVisitDate="selectedVisitDate" @visitFinished="loadHomeComponent" :selectedVisitDuration="selectedVisitDuration" :patientData="patientData" v-if="cardIndex === 3"></Visit>
    </v-main>
    </v-main>
    <v-footer class="mt-4"
        padless
    >
        <v-row justify="center">
        <v-card
            width="100%"
            flat
            tile
            class="indigo lighten-1 white--text text-center"
        >
            <v-card-text>
                <v-btn
                    v-for="icon in icons"
                    :key="icon"
                    class="mx-4 white--text"
                    icon
                >
                    <v-icon size="24px">
                        {{ icon }}
                    </v-icon>
                </v-btn>
            </v-card-text>
            <v-card-text class="white--text pt-0">
                This is a simple medical panel created in learning purposes, designed with Vuetify.
            </v-card-text>
            <v-divider></v-divider>
            <v-card-text class="white--text">
                {{ new Date().getFullYear() }} — <strong>Krzysztof Wyszyński</strong>
            </v-card-text>
        </v-card>
        </v-row>
    </v-footer>
</v-app>
</template>

<script>
import Navigation from "@/components/DoctorPanel/Navigation";
import VisitCard from "@/components/DoctorPanel/MyVisits/VisitCard";
import HomePage from "@/components/DoctorPanel/HomePage/HomePage";
import PatientTable from "@/components/DoctorPanel/MyPatients/PatientTable";
import MySchedule from "@/components/DoctorPanel/MySchedule/MySchedule";
import Visit from "@/components/DoctorPanel/Visit/Visit";

export default {
    name: "DoctorPanel",
    components: {PatientTable, MySchedule, HomePage, VisitCard, Navigation, Visit},
    data(){
        return{
            isActive: false,
            patientData: '',
            cardIndex: null,
            navigationOpened: false,
            selectedVisitDate: '',
            selectedVisitDuration: 0,
            name: localStorage.getItem('firstName'),
            icons: [
                'mdi-facebook',
                'mdi-twitter',
                'mdi-linkedin',
                'mdi-instagram',
            ],

        }
    },
    methods:{
        navigationChosen(index){
            this.cardIndex = index
        },
        cardChosen(index){
            this.cardIndex = index
        },

        logout() {
            localStorage.removeItem('user')
            localStorage.removeItem('id')
            localStorage.removeItem('email')
            localStorage.removeItem('firstName')
            if(localStorage.getItem('imageName') != null){
                localStorage.removeItem('imageName')
            }
            this.$router.push({name: 'login'})
        },
        openNavigationDrawer(){
            this.navigationOpened = !this.navigationOpened
        },
        loadHomeComponent(){
            this.cardIndex = null
        },
        beginVisit(patientData, selectedVisitDate, selectedVisitDuration){
            this.cardIndex = 3
            this.patientData = patientData
            this.selectedVisitDate = selectedVisitDate
            this.selectedVisitDuration = selectedVisitDuration
        },


    },

}
</script>

<style scoped>
.fade-enter-active,
.fade-leave-active {
    transition: opacity 1s ease;
}

.fade-enter-from,
.fade-leave-to {
    opacity: 0;
}


</style>