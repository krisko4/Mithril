import Vue from 'vue';
import Vuex from 'vuex'
import SockJS from "sockjs-client";
import Stomp from "webstomp-client";


Vue.use(Vuex)


export const store = new Vuex.Store({
    state:{
        signedIn: false,
        token: '' | localStorage.getItem('token'),
        date: '',
        doctorsAchieved: false,
        patientSelected: false,
        webSocketConnectionEstablished: false,
        cardIndex: null,
        stompClient: null,
        webSocket: null,
        scrollDuration: 300,
        scrollOffset: 0,
        scrollEasing: 'easeInOutCubic',
        target: 650,
        options: {duration: 300, offset: 0, easing: 'easeInOutCubic'}

    },
    mutations: {
        signIn(state){
            state.signedIn = true
        },
        signOut(state){
            state.signedIn = false
        },
        initializeWebSocketConnection(state){
            state.webSocket = new SockJS('http://localhost:8080/doctor-panel')
            state.stompClient = Stomp.over(state.webSocket)
            state.stompClient.connect({}, (frame) => {
                console.log('Connected: ' + frame);
                state.webSocketConnectionEstablished = true
            })
        },


    },




});
