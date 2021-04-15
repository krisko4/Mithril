<template>
    <v-container>

        <v-row class="text-center">
            <v-col class="mb-4">
                <h1 class="display-2 font-weight-bold mb-3">
                    Registration panel
                </h1>
                <h2 class="display-1 font-weight-thin mb-3">
                    Step 2
                </h2>
                <h3 class="display-1 font-weight-thin mb-3">
                    Personal data
                </h3>
                <transition name="fade">
                    <p style="color:red;" v-if="errorPopped">{{ error }}</p>
                </transition>
            </v-col>
        </v-row>
        <v-form ref="form" v-model="valid" class="login">
            <v-row justify="center">
                <v-col cols="3">
                    <v-flex
                        xs-12
                        md-4
                    >
                        <v-text-field
                            label="First name"
                            :rules="[
            v => !!v || 'Please enter your first name.'
            ]"
                            outlined
                            rounded
                            required
                            v-model="firstName"
                        ></v-text-field>
                        <v-text-field
                            label="Second name"
                            outlined
                            rounded
                            v-model="secondName"
                        ></v-text-field>
                        <v-text-field
                            label="Last name"
                            :rules="[
            v => !!v || 'Please enter your last name.'
            ]"
                            outlined
                            rounded
                            required
                            v-model="lastName"
                        ></v-text-field>
                        <v-text-field
                            ref="address"
                            v-model="address"
                            :rules="[
              () => !!address || 'This field is required',
              () => !!address && address.length <= 25 || 'Address must be less than 25 characters',

            ]"
                            label="Address Line"
                            placeholder="Address"
                            counter="25"
                            required
                        ></v-text-field>
                        <v-text-field
                            ref="city"
                            v-model="city"
                            :rules="[() => !!city || 'This field is required']"
                            label="City"
                            placeholder="El Paso"
                            required
                        ></v-text-field>
                        <v-text-field
                            ref="zip"
                            v-model="zip"
                            :rules="[() => !!zip || 'This field is required']"
                            label="ZIP / Postal Code"
                            required
                            placeholder="79938"
                        ></v-text-field>
                        <v-autocomplete
                            ref="country"
                            v-model="country"
                            :rules="[() => !!country || 'This field is required']"
                            :items="countries"
                            label="Country"
                            placeholder="Select..."
                            required
                        ></v-autocomplete>


                    </v-flex>
                </v-col>
            </v-row>
            <v-row justify="center">
                <v-col cols="1" align="center">
                <v-btn color="primary" @click="goBack">
                    <v-icon
                        dark
                        left
                    >
                        mdi-arrow-left
                    </v-icon>Return</v-btn>
                </v-col>
                <v-col cols="1" align="center" v-if="buttonEnabled">
                <transition name="fade">
                    <v-btn color="primary" @click="validateForm"  medium>Submit
                        <v-icon dark right>mdi-checkbox-marked-circle</v-icon>
                    </v-btn>
                </transition>
                </v-col>

            </v-row>


        </v-form>

    </v-container>
</template>

<script>
export default {
    data() {
        return {
            countries: ['Poland', 'England'],
            firstName: '',
            lastName: '',
            secondName: '',
            address: '',
            city: '',
            zip: '',
            country: '',
            buttonEnabled: false,
            valid: false,
            errorPopped: '',
        }
    },
    methods: {
        validateForm() {
            console.log(this.form)
            this.$emit('secondStepComplete', 3, {
                'firstName': this.firstName,
                'lastName': this.lastName,
                'secondName': this.secondName,
                'address': this.address,
                'city': this.city,
                'zip': this.zip,
                'country': this.country
            })

            this.$toast.success('Step 2 completed successfully.')
        },
        showButton() {
            this.buttonEnabled = this.valid
        },
        goBack() {
            this.$emit('goBack', 1)
        }
    },
    watch: {
        valid() {
            console.log(this.valid)
            setTimeout(this.showButton, 200);
        }
    },
}
</script>