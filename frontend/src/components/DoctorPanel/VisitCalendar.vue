<template>
    <v-container fill-height>
        <v-row justify="center">
            <v-col cols="6">
                <v-sheet height="600">
                    <v-toolbar flat>
                    <v-toolbar-items>
                        <v-btn text outlined @click="type = 'day'" >Day</v-btn>
                        <v-btn text outlined @click="type = 'week'" >Week</v-btn>
                        <v-btn text outlined @click="type = 'month'" >Month</v-btn>
                    </v-toolbar-items>
                    </v-toolbar>
                    <v-calendar
                        class="mt-5"
                        ref="calendar"
                        v-model="focus"
                        color="primary"
                        @click:more="viewDay"
                        @click:date="viewDay"
                        :type="type"
                    ></v-calendar>
                </v-sheet>
            </v-col>
        </v-row>
    </v-container>
</template>

<script>
export default {
    name: "VisitCalendar",
    data() {
        return {
            type : 'week',
            focus: '',
        }
    },
    methods : {
        viewDay ({date}) {
            this.type = 'day'
            this.focus = date
        },
    }
}
</script>

<style scoped>

</style>