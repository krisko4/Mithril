<template>
<v-app style="background-color: whitesmoke">

    <v-main>
    <Navigation  @navigationClosed="navigationClosed" :navigationOpened="navigationOpened" @navigationChosen="navigationChosen"></Navigation>
        <v-img gradient="to top, rgba(255, 255, 255, 0.3), rgba(255, 255, 255, 0.3)" height="700px" src="https://imageproxy.themaven.net/https%3A%2F%2Fs3-us-west-2.amazonaws.com%2Fmaven-user-photos%2Fcancerconnect%2Fcolon-cancer%2FgdZ0-CoMmkOJnjahj_em8A%2FofYjfdSPzkSkUXeC9Sr3nQ">
            <v-toolbar style="background-color: transparent">
                <v-app-bar-nav-icon @click=openNavigationDrawer></v-app-bar-nav-icon>
                <v-toolbar-title>Title</v-toolbar-title>
            </v-toolbar>
            <v-container fill-height>
                <v-container>
                <v-row>
                    <div class="text-uppercase font-weight-bold mb-4 display-3">Hello, {{name}}</div>
                </v-row>
                <v-row>
                    <div class="mb-4 display-2 font-weight-light">Welcome to your personal doctor panel</div>
                </v-row>
                    <v-row>
                        <div class="mb-4 display-1 font-weight-light">Enjoy your work</div>
                    </v-row>
                </v-container>
            </v-container>
        </v-img>
    <v-main
    >
        <DoctorHomeComponentV2 @cardChosen="cardChosen" v-show="cardIndex === null"></DoctorHomeComponentV2>
        <DoctorVisitsComponent @goBack="loadHomeComponent" v-show="cardIndex === 0"></DoctorVisitsComponent>
    </v-main>
    </v-main>
</v-app>
</template>

<script>
import Navigation from "@/components/DoctorPanel/Navigation";
import DoctorVisitsComponent from "@/components/DoctorPanel/DoctorVisitsComponent";
import DoctorHomeComponentV2 from "@/components/DoctorPanel/DoctorHomeComponentV2";
//import DoctorHomeComponent from "@/components/DoctorPanel/DoctorHomeComponent";
//import axios from "axios";
export default {
    name: "DoctorPanelComponent",
    components: {DoctorHomeComponentV2, DoctorVisitsComponent, Navigation},
    data(){
        return{
            cardIndex: null,
            navigationOpened: false,
            name: localStorage.getItem('firstName'),

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
            this.$router.push({name: 'login'})
        },
        openNavigationDrawer(){
            this.navigationOpened = !this.navigationOpened
        },
        loadHomeComponent(){
            this.cardIndex = null
        },
        navigationClosed(){
            this.navigationOpened = false
        }
    },

}
</script>

<style scoped>

</style>