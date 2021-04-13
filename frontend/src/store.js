import Vue from 'vue';
import Vuex from 'vuex'
import axios from "axios";

Vue.use(Vuex)


export const store = new Vuex.Store({
    state:{
        date: '',
        patient: '',
        doctors: [],
        doctorsAchieved: false,
        patientSelected: false

    },
    mutations: {
        getDoctors(state) {
            if (state.date) {
                axios.get('http://localhost:8080/doctors/' + state.date).then(response => {
                    state.doctors = response.data
                    state.doctorsAchieved = true
                })
            }
        },

    }


});
