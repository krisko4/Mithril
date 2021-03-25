<template>
  <v-app>
    <v-app-bar
      app
      color="lightgrey"
      dark
    >
      <v-btn type="primary">Witaj</v-btn>
    </v-app-bar>
    <v-main>
      <TestPage/>
    </v-main>
  </v-app>
</template>

<script>
//import HelloWorld from './components/HelloWorld';
import TestPage from "@/components/TestPage";
export default {
  name: 'App',

  components: {
  //  HelloWorld,
    TestPage,
  },

  data: () => ({
    //
  }),
};
</script>
