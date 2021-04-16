<template>
    <v-app>
        <v-app-bar
            app
            color="lightgrey"
            dark
        >
            <v-app-bar-title>
                Ema
            </v-app-bar-title>

        </v-app-bar>
        <v-main>
            <router-view></router-view>
        </v-main>
    </v-app>
</template>

<script>




export default {
    name: 'App',

};
</script>

<style>

</style>
