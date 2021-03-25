<template>
  <v-container>
    <v-row class="text-center">
      <v-col cols="12">
        <v-img
          :src="require('../assets/logo.svg')"
          class="my-3"
          contain
          height="200"
        />
      </v-col>
    </v-row>
<v-row justify="center">
      <v-col align="center">
        <h1 class="display-2 font-weight-bold mb-3">
          Please select the date
        </h1>
      </v-col>
</v-row>
    <v-row justify="center">
      <v-col
          cols="6"
          sm="2"
      >
        <v-date-picker
            v-model="dates"
            multiple
        ></v-date-picker>
      </v-col>
      <v-col
          cols="6"
          sm="2"
      >
        <v-menu
            ref="menu"
            v-model="menu"
            :close-on-content-click="false"
            :return-value.sync="dates"
            transition="scale-transition"
            offset-y
            min-width="auto"
        >
          <template v-slot:activator="{ on, attrs }">
            <v-combobox
                v-model="dates"
                multiple
                chips
                small-chips
                label="Pick the date"
                prepend-icon="mdi-calendar"
                readonly
                v-bind="attrs"
                v-on="on"
            ></v-combobox>
          </template>
          <v-date-picker
              v-model="dates"
              multiple
              no-title
              scrollable
          >
            <v-spacer></v-spacer>
            <v-btn
                text
                color="primary"
                @click="menu = false"
            >
              Cancel
            </v-btn>
            <v-btn
                text
                color="primary"
                @click="$refs.menu.save(dates)"
            >
              OK
            </v-btn>
          </v-date-picker>
        </v-menu>
      </v-col>
    </v-row>
    <v-row justify="center">
      <v-col cols="12" sm="2" align="center">
        <v-btn @click = "getDates" type="submit" color="primary">Submit</v-btn>
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
  export default {
    name: 'HelloWorld',

    data: () => ({
      dates:[],
      menu: false,
      picker: new Date().toISOString().substr(0, 10),

    }),
    methods:{
      getDates(){
        console.log(this.dates)
      }
    }
  }
</script>
