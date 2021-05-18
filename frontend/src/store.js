import Vue from 'vue';
import Vuex from 'vuex'
import axios from "axios";

Vue.use(Vuex)


export const store = new Vuex.Store({
    state:{
        token: '' | localStorage.getItem('token'),
        date: '',
        patient: '',
        doctors: [],
        doctorsAchieved: false,
        patientSelected: false,
        cardIndex: null

    },
    mutations: {
        getDoctors(state) {
            if (state.date) {
                axios.get('http://localhost:8080/users/doctors/' + state.date).then(response => {
                    state.doctors = response.data
                    state.doctorsAchieved = true
                })
            }
        },



    },
    actions :{

    }

});
