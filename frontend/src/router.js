import Vue from 'vue';
import Router from 'vue-router';
import RegisterComponent from "@/components/Registration/RegisterComponent";
import VisitComponent from "@/components/VisitSaving/VisitComponent";
import ConfirmationComponent from "@/components/Registration/Confirmation/ConfirmationComponent";
import LoginComponent from "@/components/Login/LoginComponent";
import axios from "axios";
import DoctorPanelComponent from "@/components/DoctorPanel/DoctorPanelComponent";

import PatientDetailsComponent from "@/components/DoctorPanel/PatientDetailsComponent";



Vue.use(Router);

const RouterVue = new Router({
    mode: 'history',
    base: process.env.BASE_URL,
    routes: [
        {
            path: '/register',
            name: 'register',
            component: RegisterComponent
        },
        {
            path: '/visit',
            name: 'visit',
            component: VisitComponent
        },
        {
            path: '/confirm/:token',
            name: 'confirm',
            component: ConfirmationComponent,
            meta: {
                reload: false
            }
        },
        {
            path: '',
            name: 'home',
            meta: {
                requiresAuth: true
            },
            component: DoctorPanelComponent
        },
        {
            path: '/test',
            name: 'test',
            component: PatientDetailsComponent
        },
        {
            path: '/login',
            name: 'login',
            component: LoginComponent,

        }


    ],
    props:{
        default: true
    },



});

RouterVue.beforeEach((to, from, next) => {
    if (to.matched.some(record => record.meta.requiresAuth)) {
        console.log(localStorage.getItem('user'))
        if (localStorage.getItem('user') === null) {
            next({name: 'login'})
        } else {
            axios.get('http://localhost:8080/jwt', {
                headers: {
                    'Authorization' : 'Bearer ' + localStorage.getItem('user')
                }
            }).then(() => next())
                .catch((error) => {
                    console.log(error)
                next({name: 'login'})
            })
        }
    } else {
        next()
    }
})

export default RouterVue;