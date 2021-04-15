<template>
    <v-container>
        <v-row justify="center">
            <v-col cols="4">
                <v-card>
                    <v-card-title>Please choose your specialization</v-card-title>
                    <v-card-text>
                        <v-select
                            placeholder="Please choose your specialization"
                            prepend-icon="mdi-database-search"
                            :loading="loading"
                            :items="items"
                            v-model="value"
                            label="Specializations"
                            cache-items
                        >
                        </v-select>
                    </v-card-text>
                    <v-card-actions>
                        <v-col>
                            <v-row justify="space-between">


                                <v-btn
                                    color="primary"
                                    text
                                    @click="goBack"
                                >
                                    <v-icon
                                        dark
                                        left
                                    >
                                        mdi-arrow-left
                                    </v-icon>
                                    Return
                                </v-btn>
                                <v-btn
                                    color="primary"
                                    v-if="buttonVisible"
                                    text
                                    @click="registerDoctor"
                                >Submit</v-btn>
                            </v-row>
                        </v-col>
                    </v-card-actions>
                </v-card>
            </v-col>
        </v-row>
    </v-container>
</template>

<script>
import axios from 'axios'
export default {
    name: "RegisterStep4Component",
    props:{
      userData: {},
    },
    data() {
        return {
            items: [],
            loading: false,
            search: '',
            value: '',
        }
    },
    created() {
        axios.get("http://localhost:8080/specializations").then((response) => {
            response.data.forEach((element)=>{
                this.items.push(element.name)
            })
        })
    },

    methods:{
        goBack() {
            this.$emit('goBack', 3)
        },
        registerDoctor(){
            axios.post("http://localhost:8080/registration/doctor")
        }
    },

    computed:{
        buttonVisible(){
            return !!this.value;
        }
    }
}
</script>

<style scoped>

</style>