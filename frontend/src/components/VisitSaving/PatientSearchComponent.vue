<template>
    <v-autocomplete
        placeholder="Fill in patient data"
        prepend-icon="mdi-database-search"
        :search-input.sync="search"
        :loading="loading"
        :items="items"
        label="Patients"
    >
    </v-autocomplete>
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
            this.$store.state.patientSelected = false
            this.loading = true
            this.findPatientsDebounced(val)
        },
    },
    methods: {
        findPatientsDebounced(val) {
            clearTimeout(this._searchTimerId)
            this._searchTimerId = setTimeout(() => {
                this.findPatients(val)
            }, 300)

        },

        findPatients(val) {
            if (!val) {
                this.items = []
                this.loading = false
                this.$store.state.patientSelected = false
                return
            }
            axios.get("http://localhost:8080/patients/bychar", {
                params: {
                    character: val
                }
            }).then((response) => {
                // this means empty response was returned
                if(response.data.length === 0 && this.items.includes(val)){
                    this.$store.state.patientSelected = true
                    return
                }
                response.data.forEach((patient) => {
                    this.$store.state.patient = patient
                    const patientData = patient.firstName + " " + patient.lastName + " " + patient.pesel;
                    if (!this.items.includes(patientData)) {
                        this.items.push(patientData)
                    }
                    if (patientData === val) {
                        this.$store.state.patientSelected = true
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