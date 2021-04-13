<template>
    <v-container>
        <v-row style="height: 100px" align-content="center" justify="center">Please choose a date</v-row>
        <v-row justify="center">
            <v-col cols="3" align="center">
                <v-date-picker
                    v-model="date"
                    elevation="24"
                    color="indigo"
                >
                </v-date-picker>
            </v-col>
        </v-row>
    </v-container>

</template>

<script>

export default {

    name: 'DatePickerComponent',
    data() {
        return {
            picker: new Date().toISOString().substr(0, 10),
            date: ''
        }
    },
    watch: {
        date() {
            if (this.date) {
                this.$store.state.date = this.date
            }
        }
    },
    methods: {}
}
</script>

