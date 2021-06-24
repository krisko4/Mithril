<template>
    <div>
        <v-card-text>
            <v-row class="text-center mt-3">
                <v-col class="mb-4">
                    <h2 class="display-1  mb-3">
                        Choose a leading doctor for new patient
                    </h2>
                </v-col>
            </v-row>
            <v-row justify="center">
                <v-col cols="6">
                    <v-responsive height="400px">
                        <transition name="fade" appear mode="out-in">
                            <v-img :key="imgKey" height="400px" :src=imgSrc></v-img>
                        </transition>
                    </v-responsive>
                </v-col>
            </v-row>
            <v-row justify="center">
                <v-col cols="6">
                    <DoctorSearchComponent @doctorSelected="selectDoctor"></DoctorSearchComponent>
                </v-col>
            </v-row>
            <v-row justify="center" class="mb-3">
                <v-col cols="6">
                    <v-btn block large color="primary" :loading="isSubmitLoading" :disabled="!doctorSelected" @click="registerNewPatient">Finish
                        registration
                    </v-btn>
                </v-col>
            </v-row>
        </v-card-text>
        <v-card-actions>
            <v-btn text color="primary" @click="returnToStep1">Return</v-btn>
        </v-card-actions>
    </div>
</template>

<script>
import DoctorSearchComponent from "@/components/Panels/ReceptionPanel/NewPatient/DoctorSearchComponent";
import {tokenAxios} from "@/axios";

export default {
    name: "Step2",
    components: {DoctorSearchComponent},
    props: {
        patient: Object
    },
    data() {
        return {
            emptyImgSrc: 'https://www.lupus.eu/en/assets/images/home/empty-avatar.png',
            doctorImgSrc: '',
            imgSrc: 'https://www.lupus.eu/en/assets/images/home/empty-avatar.png',
            doctor: null,
            doctorSelected: false,
            imageChanged: false,
            imgKey: 0,
            isSubmitLoading: false
        }
    },
    methods: {
        selectDoctor(doctor, doctorSelected) {
            this.doctor = doctor
            this.doctorSelected = doctorSelected
            if (!doctorSelected) {
                return
            }
            let imgSrc
            if (doctor.imageName) {
                imgSrc = 'http://localhost:8080/images/doctors/' + doctor.imageName
            } else {
                imgSrc = this.emptyImgSrc
            }
            if (imgSrc !== this.imgSrc) {
                this.imgKey++
            }
            this.imgSrc = imgSrc

        },
        returnToStep1() {
            this.$emit('returnClicked')
        },
        registerNewPatient() {
            this.isSubmitLoading = true
            tokenAxios.post('patients', {
                doctorId: this.doctor.id,
                firstName: this.patient.firstName,
                secondName: this.patient.secondName,
                lastName: this.patient.lastName,
                phone: this.patient.phone,
                country: this.patient.country,
                city: this.patient.city,
                street: this.patient.street,
                flatNumber: this.patient.flatNumber,
                postCode: this.patient.postCode,
                birthdate: this.patient.birthdate,
                email: this.patient.email,
                pesel: this.patient.pesel,
            }).then(() => {
                this.$toast.success('New patient has been successfully registered')
                this.$emit('registrationFinished')
            }).finally(() => {
                this.isSubmitLoading = false
            }).catch(() => {
                this.$toast.error('Registration failed')
            })


        }

    },
    watch: {
        imgSrc() {
            this.imageChanged = !this.imageChanged
        }
    }
}
</script>

<style scoped>
.fade-enter-active, .fade-leave-active {
    transition: all .5s;
}

.fade-enter, .fade-leave-to {
    opacity: 0
}
</style>