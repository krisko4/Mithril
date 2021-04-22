<template>
    <v-navigation-drawer
        main
        absolute
        temporary
        v-model="drawer"

    >
        <v-list
            nav
            dense
        >
            <v-list-item>
                <v-list-item-avatar>
                    <v-img src="https://randomuser.me/api/portraits/men/78.jpg"></v-img>
                </v-list-item-avatar>
                <v-list-item-content>
                    <v-list-item-title>{{name}}</v-list-item-title>
                </v-list-item-content>
            </v-list-item>

            <v-divider></v-divider>
            <v-list-item-group
                active-class="deep-purple--text text--accent-4"
            >
                <v-list-item @click="cardIndex = null; redirect(cardIndex)">
                    <v-list-item-icon>
                        <v-icon>mdi-home</v-icon>
                    </v-list-item-icon>
                    <v-list-item-title>Home</v-list-item-title>
                </v-list-item>
                <v-list-item>
                    <v-list-item-icon>
                        <v-icon>mdi-account</v-icon>
                    </v-list-item-icon>
                    <v-list-item-title>Account</v-list-item-title>
                </v-list-item>
                <v-list-item @click="cardIndex = 0; redirect(cardIndex)">
                    <v-list-item-icon>
                        <v-icon>mdi-calendar</v-icon>
                    </v-list-item-icon>
                    <v-list-item-title>My visits</v-list-item-title>
                </v-list-item>
                <v-list-item>
                    <v-list-item-icon>
                        <v-icon>mdi-calendar-heart</v-icon>
                    </v-list-item-icon>
                    <v-list-item-title>My schedule</v-list-item-title>
                </v-list-item>
                <v-list-item>
                    <v-list-item-icon>
                        <v-icon>mdi-account-heart</v-icon>
                    </v-list-item-icon>
                    <v-list-item-title>My patients</v-list-item-title>
                </v-list-item>

            </v-list-item-group>
        </v-list>
    </v-navigation-drawer>
</template>

<script>
export default {
    name: "Navigation",
    props:{
        navigationOpened: Boolean
    },
    data(){
        return{
            cardIndex: null,
            name: localStorage.getItem('firstName'),
            drawer: this.navigationOpened

        }
    },
    watch: {
      navigationOpened(){
         this.drawer = this.navigationOpened
      }
    },


    methods: {
        redirect(){
            this.$emit('navigationChosen', this.cardIndex)
        }
    },





}
</script>

<style scoped>

</style>