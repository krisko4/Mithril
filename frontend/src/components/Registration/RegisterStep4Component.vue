<template>
    <v-container>
        <v-row class="text-center">
            <v-col class="mb-4">
                <h1 class="display-2 font-weight-bold mb-3">
                    Registration panel
                </h1>
                <h2 class="display-1 font-weight-thin mb-3">
                    Step 4
                </h2>
                <h3 class="display-1 font-weight-thin mb-3">
                    <i>Specialization</i>
                </h3>
            </v-col>
        </v-row>
        <v-row justify="center">
            <v-col cols="4">
                <v-card>
                    <v-card-title>Please choose your specialization</v-card-title>
                    <v-card-text>
                        <v-select
                            placeholder="Please choose your specialization"
                            prepend-icon="mdi-database-search"
                            multiple
                            chips
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
                                    :loading="registerLoading"
                                    @click="registerDoctor"
                                >Submit
                                </v-btn>
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
    props: {
        userData: Object,
        image: File,
    },
    data() {
        return {
            items: [],
            loading: false,
            registerLoading: false,
            search: '',
            value: '',
        }
    },
    created() {
        axios.get("http://localhost:8080/specializations").then((response) => {
            response.data.forEach((element) => {
                this.items.push(element.name)
            })
        })
    },

    methods: {
        goBack() {
            this.$emit('goBack', 3)
        },
        registerDoctor() {
            this.registerLoading = true
            const formData = new FormData()
            if(this.image){
                for(const element in this.userData){
                    formData.append(element.toString(), this.userData[element])
                }
            }
            axios.post("http://localhost:8080/doctor/registration", formData, {
                headers:{
                    'Content-Type': 'multipart/form-data'
                },
            })
                .then(() => {
                    this.$emit('fourthStepComplete', this.userData.email, this.userData.firstName)
                    this.$toast.success('Congratulations! You have registered successfully.')
                    this.registerLoading = false
                })

        }
    },

    computed: {
        buttonVisible() {
            return !!this.value;
        }
    }
}
</script>

<style scoped>

</style>