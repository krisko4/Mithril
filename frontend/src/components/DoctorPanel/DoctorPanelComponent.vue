<template>
<v-app>
    <v-app-bar app>
        <v-app-bar-nav-icon
            @click="drawer = true"
        ></v-app-bar-nav-icon>
        <v-app-bar-title>Title</v-app-bar-title>
        <v-spacer></v-spacer>
        <v-btn text @click="logout">
            Sign out
            <v-icon>mdi-exit-to-app</v-icon>
        </v-btn>
    </v-app-bar>
    <Navigation></Navigation>
    <v-main>
        <DoctorHomeComponent @cardChosen="cardChosen" v-show="cardIndex === null"></DoctorHomeComponent>
        <DoctorScheduleComponent v-show="cardIndex === 2"></DoctorScheduleComponent>
    </v-main>
</v-app>
</template>

<script>
import Navigation from "@/components/DoctorPanel/Navigation";
import DoctorScheduleComponent from "@/components/DoctorPanel/DoctorScheduleComponent";
import DoctorHomeComponent from "@/components/DoctorPanel/DoctorHomeComponent";
export default {
    name: "DoctorPanelComponent",
    components: {DoctorScheduleComponent, Navigation, DoctorHomeComponent},
    data(){
        return{
            cardIndex: null
        }
    },
    methods:{
        cardChosen(index){
            this.cardIndex = index
        },
        logout() {
            localStorage.removeItem('user')
            this.$router.push({name: 'login'})
        }
    },
    created(){
        console.log(this.cardIndex)
    }
}
</script>

<style scoped>

</style>