import Vue from 'vue';
import Router from 'vue-router';
// import RegisterStep1Component from "@/components/Registration/RegisterStep1Component";
import RegisterStep2Component from "@/components/Registration/RegisterStep2Component";
import VisitComponent from "@/components/VisitSaving/VisitComponent";


Vue.use(Router);

const RouterVue = new Router({
    mode: 'history',
    base: process.env.BASE_URL,
    routes: [
        {
            path: '/register',
            name: 'register',
            component: RegisterStep2Component
        },
        {
            path: '/visit',
            name: 'visit',
            component: VisitComponent
        },
    ]
});

export default RouterVue;