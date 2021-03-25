<template>
    <v-container>
        <v-row style="height: 100px" align-content="center" justify="center">Please choose a date</v-row>
        <v-row justify="center">
            <v-col cols="3" align="center">
                <v-date-picker v-model="dates"
                multiple></v-date-picker>
            </v-col>


        </v-row>
        <v-row justify="center">
            <v-col cols="1" align = "center">
                <v-btn @click="getDates" color="primary">Submit</v-btn>
            </v-col>
        </v-row>
        {{msg}}
    </v-container>

</template>

<script>
import axios from 'axios';
export default {

    name: 'TestPage',
    props:{
        msg: String
    },

    data(){
        return{
            dates:[],
            patients: [],
            picker: new Date().toISOString().substr(0, 10),
        }
    },
    methods: {
        logEma() {
            console.log('ema');
            axios.get('http://localhost:8080/patients').then((response)=>{
                this.patients = response.data;
                console.log(this.patients);
            })
        },
        getDates(){
            this.dates.forEach(element=>
                axios.get('http://localhost:8080/doctors/' + element).then(response =>{
                    console.log(response.data)
                })

            );


        }
    }
}
</script>

<style>

</style>