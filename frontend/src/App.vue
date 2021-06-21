<template>
    <v-app>
        <router-view></router-view>
    </v-app>

</template>

<script>


export default {
    name: 'App',
    data() {
        return {
            transitionName: ''
        }
    },

    created() {
        this.setInitialTransition(this.$route)
    },

    watch: {
        '$route'(to,from) {
            this.setTransition(to, from)
        },

    },
    methods: {
        setInitialTransition(route) {
            if (route.name === 'panelSelector') {
                this.transitionName = 'fade'
            }
            if (route.name === 'login') {
                this.transitionName = 'slide'
            }

        },
        setTransition(to, from) {
            if (from.name === 'panelSelector') {
                this.transitionName = 'slide'
            }
            if (from.name === 'login') {
                this.transitionName = 'fade'
            }

        }


    }
}
</script>

<style>

.fade-enter-active,
.fade-leave-active {
    transition: all 3s;
}

.fade-enter, .fade-leave-to {
    opacity: 0;
}


.slide-enter-active,
.slide-leave-active {
    transition: all .5s;
}

.slide-enter, .slide-leave-to {
    transform: translateY(-600px);
}
</style>
