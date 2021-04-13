<template>
    <v-app>
        <v-app-bar
            app
            color="lightgrey"
            dark
        >
            EmaMed
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
