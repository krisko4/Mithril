<template>
    <v-container>
        <v-row justify="center">
            <v-col cols="3">
                <v-autocomplete
                    placeholder="Fill in patient data"
                    prepend-icon="mdi-database-search"
                    :search-input.sync="search"
                    :loading="loading"
                    :items="items"
                    label="Patients"
                    cache-items
                >
                </v-autocomplete>
            </v-col>
        </v-row>

    </v-container>
</template>

<script>
import axios from 'axios'

export default {
    data() {
        return {
            loading: false,
            search: null,
            items: [],
        }
    },
    watch: {
        search(val) {
            if(this.items.includes(val)){
                this.$store.state.patientSelected = true
            }
            this.loading = true
            this.findPatientsDebounced(val)
        },
    },
    methods: {
        findPatientsDebounced(val) {
            clearTimeout(this._searchTimerId)
            this._searchTimerId = setTimeout(() => {
                this.findPatients(val)
            }, 500)

        },

        findPatients(val) {
            if (!val) {
                this.items = []
                this.loading = false
                return
            }
            axios.get("http://localhost:8080/patients/bychar", {
                params: {
                    character: val
                }
            }).then((response) => {
                response.data.forEach((patient) => {
                    this.$store.state.patient = patient
                    const patientData = patient.firstName + " " + patient.lastName + " " + patient.pesel;
                    if (!this.items.includes(patientData)) {
                        this.items.push(patientData)
                    }
                })
            }).finally(() => {
                this.loading = false
            })

        }

    }
}
</script>

<style>

</style>