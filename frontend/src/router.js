import Vue from 'vue';
import Router from 'vue-router';
// import RegisterStep1Component from "@/components/Registration/RegisterStep1Component";
//import RegisterStep2Component from "@/components/Registration/RegisterStep2Component";
//import RegisterStep3Component from "@/components/Registration/RegisterStep3Component";
import RegisterComponent from "@/components/Registration/RegisterComponent";
import VisitComponent from "@/components/VisitSaving/VisitComponent";
//import RegisterStep4Component from "@/components/Registration/RegisterStep4Component";


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
    ]
});

export default RouterVue;