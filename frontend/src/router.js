import Vue from 'vue';
import Router from 'vue-router';
// import RegisterStep1Component from "@/components/Registration/RegisterStep1Component";
//import RegisterStep2Component from "@/components/Registration/RegisterStep2Component";
//import RegisterStep3Component from "@/components/Registration/RegisterStep3Component";
//import RegisterComponent from "@/components/Registration/RegisterComponent";
import VisitComponent from "@/components/VisitSaving/VisitComponent";
//import MailConfirmComponent from "@/components/Registration/MailConfirmComponent";
import RegisterComponent from "@/components/Registration/RegisterComponent";
//import RegisterStep4Component from "@/components/Registration/RegisterStep4Component";
import ConfirmationSuccessComponent from "@/components/Registration/ConfirmationSuccessComponent";
import App from "@/App";
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
            component: ConfirmationSuccessComponent
        },
        {
            path: '',
            name: 'home',
            component: App
        }

    ],
    props:{
        default: true
    }
});

export default RouterVue;