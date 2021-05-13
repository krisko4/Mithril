<template>
        <v-date-picker
            v-model="date"
            elevation="24"
            color="indigo"
        >
        </v-date-picker>
</template>

<script>

export default {

    name: 'DatePicker',
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
                console.log(this.$store.state.date)
                this.$emit('dateChanged', this.date)
            }
        },
    },
    methods: {},

}
</script>

