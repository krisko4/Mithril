<template>
    <v-navigation-drawer
        main
        absolute
        temporary
        v-model="drawer"
    >
        <v-list>
            <v-list-item>
                <v-list-item-avatar>
                    <v-img :src="imgSrc"></v-img>
                </v-list-item-avatar>
                <v-list-item-content>
                    <v-list-item-title>{{ name }}</v-list-item-title>
                </v-list-item-content>
            </v-list-item>
        </v-list>
        <v-divider></v-divider>
        <v-list  rounded>
            <v-list-item-group
                active-class="deep-purple--text text--accent-4"
                v-model="selectedItem"
            >
                <v-list-item
                    v-for="(item, i) in items"
                    :key="i"
                    @click="redirect(i)"
                    >
                    <v-list-item-icon>
                        <v-icon color="primary">{{item.icon}}</v-icon>
                    </v-list-item-icon>
                    <v-list-item-title>{{item.title}}</v-list-item-title>
                </v-list-item>
            </v-list-item-group>
        </v-list>
    </v-navigation-drawer>
</template>

<script>
export default {
    name: "Navigation",
    props: {
        navigationOpened: Boolean
    },
    data() {
        return {
            imgSrc: 'http://localhost:8080/images/doctors/' + localStorage.getItem('imageName'),
            cardIndex: null,
            name: localStorage.getItem('firstName'),
            drawer: this.navigationOpened,
            selectedItem: null,
            items: [
                {icon: 'mdi-home', title: 'Home'},
                {icon: 'mdi-account', title: 'My account'},
                {icon: 'mdi-calendar', title: 'My visits'},
                {icon: 'mdi-calendar-heart', title: 'My schedule'},
                {icon: 'mdi-account-heart', title: 'My patients'},
            ]

        }
    },
    watch: {
        navigationOpened(val) {
            this.drawer = val
        },

        drawer(val){
            if(!val){
                this.$emit('navigationClosed')
            }
        }
    },

    created(){
        if(localStorage.getItem('imageName') === null ){
            this.imgSrc = 'http://localhost:8080/images/static/avatar.png'
        }
    },


    methods: {
        redirect(index) {
            this.drawer = false
            this.$emit('navigationChosen', index)
            this.$vuetify.goTo(this.$store.state.target, this.$store.state.options)


        }
    },


}
</script>

<style scoped>
.v-navigation-drawer {
    will-change: initial;
}
</style>