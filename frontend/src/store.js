import Vue from 'vue';
import Vuex from 'vuex'


Vue.use(Vuex)


export const store = new Vuex.Store({
    state:{
        signedIn: false,
        token: '' | localStorage.getItem('token'),
        date: '',
        doctorsAchieved: false,
        patientSelected: false,
        cardIndex: null,

        scrollDuration: 300,
        scrollOffset: 0,
        scrollEasing: 'easeInOutCubic',
        target: 650,
        options: {duration: 300, offset: 0, easing: 'easeInOutCubic'}

    },
    mutations: {
        // getDoctors(state) {
        //     if (state.date) {
        //         // axios.get('users/doctors/' + state.date).then(response => {
        //         //     state.doctors = response.data
        //         //     state.doctorsAchieved = true
        //         // })
        //     }
        // },
        signIn(state){
            state.signedIn = true
        },
        signOut(state){
            state.signedIn = false
        }




    },


});
