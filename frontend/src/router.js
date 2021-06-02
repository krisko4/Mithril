import Vue from 'vue';
import Router from 'vue-router';
import Registration from "@/components/Registration/Registration";
import VisitComponent from "@/components/VisitSaving/VisitComponent";
import Confirmation from "@/components/Registration/Confirmation/Confirmation";
import Login from "@/components/Login/Login";
import {tokenAxios} from "@/axios";
import DoctorPanel from "@/components/DoctorPanel/DoctorPanel";
import PatientDetailsComponent from "@/components/DoctorPanel/MyPatients/PatientDetails";

Vue.use(Router);

const RouterVue = new Router({
    mode: 'history',
    base: process.env.BASE_URL,
    routes: [
        {
            path: '/register',
            name: 'register',
            component: Registration
        },
        {
            path: '/visit',
            name: 'visit',
            component: VisitComponent
        },
        {
            path: '/confirm/:token',
            name: 'confirm',
            component: Confirmation,
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
            component: DoctorPanel
        },
        {
            path: '/test',
            name: 'test',
            component: PatientDetailsComponent
        },
        {
            path: '/login',
            name: 'login',
            component: Login,

        }


    ],
    props: {
        default: true
    },


});

RouterVue.beforeEach((to, from, next) => {
    if (to.matched.some(record => record.meta.requiresAuth)) {
        if (localStorage.getItem('user') === null) {
            next({name: 'login'})
        } else {
            tokenAxios.get('jwt').then(() => next())
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