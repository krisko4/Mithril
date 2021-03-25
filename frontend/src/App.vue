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

            <TestPage
                :msg="message"
                :date="visitDate"
                @messageFromChild="printMsg"
            />
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

    methods:{
        printMsg(msg, number){
            alert(msg + number)
        }
    },

    data() {
        return {
            message: 'ema',
            visitDate: new Date('2021-03-21')
        }
    }
};
</script>
