<template>
    <v-app>
        <v-app-bar
            app
            color="lightgrey"
            dark
        >
            EmaMed
        </v-app-bar>
        <v-main>
            <TestPage :msg="message"/>

        </v-main>
    </v-app>
</template>

<script>
//import HelloWorld from './components/HelloWorld';
import TestPage from "@/components/TestPage";

export default {
    name: 'App',

    components: {
        //  HelloWorld,
        TestPage,
    },

    data(){
        return{
            message: 'ema'
        }
    }
};
</script>
