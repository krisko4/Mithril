<template>
    <v-container>
        <v-row class="text-center">
            <v-col class="mb-4">
                <h1 class="display-2 font-weight-bold mb-3">
                    Registration panel
                </h1>
                <h2 class="display-1 font-weight-thin mb-3">
                    Step 3
                </h2>
                <h3 class="display-1 font-weight-thin mb-3">
                    <i>Profile image</i>
                </h3>
            </v-col>
        </v-row>
        <v-row justify="center">
            <v-col cols="4" align="center">
                <v-card
                    ref="card"
                    class="mx-auto"
                    :elevation="elevation"
                    shaped
                    @dragover.prevent="dragover"
                    @dragleave.prevent="dragend"
                    @dragexit.prevent="dragend"
                    @drop="drop"
                >
                    <v-container fluid>
                        <v-img :src="previewImage"
                               max-height="300"
                               max-width="300"/>

                        <v-btn
                            class="ma-2"
                            color="indigo"
                            dark
                            @click="browse"
                            type="input"
                            :loading="isLoading"
                        >
                            <v-icon dark>
                                large
                                mdi-cloud-upload
                            </v-icon>
                        </v-btn>
                        <input
                            type="file"
                            style="display: none"
                            ref="image"
                            accept="image/*"
                            @change="onBrowsing"
                        >
                        <h4 >Drag and drop files to upload.
                        </h4>
                        <v-card-actions>
                            <v-col  v-if="imageLoaded">
                                <v-row justify="end">
                                    <v-btn
                                        text
                                        color="primary"
                                        @click="submitImage"
                                        :loading="isSubmitLoading"
                                    >Submit
                                    </v-btn>
                                </v-row>
                            </v-col>


                        </v-card-actions>

                    </v-container>
                </v-card>
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
            <v-col cols="1" align="center">
                <transition name="fade">
                    <v-btn color="primary" @click="nextStep" medium>Continue
                        <v-icon dark right>mdi-arrow-right</v-icon>
                    </v-btn>
                </transition>
            </v-col>
        </v-row>

    </v-container>
</template>

<script>
export default {
    name: "RegisterStep3Component",
    data() {
        return {
            isLoading: false,
            isSubmitLoading: false,
            selectedFile: null,
            previewImage: null,
            imageLoaded: false,
            buttonEnabled: false,
            elevation: 5,
        }
    },
    methods: {
        browse() {
            this.isLoading = true
            window.addEventListener('focus', () => {
                this.isLoading = false
            }, {once: true})
            this.$refs.image.click()
        },

        onBrowsing(e) {
            this.selectedFile = e.target.files[0]
            this.readImage(this.selectedFile)
        },

        dragover() {
           this.elevation=12
        },
        dragend(){
            this.elevation=5
        },
        drop(event){
            event.preventDefault();
            this.readImage(event.dataTransfer.files[0])
        },
        readImage(val){
            const fr = new FileReader()
            fr.readAsDataURL(val)
            fr.onload = e => {
                this.previewImage = e.target.result;
                this.imageLoaded = true
                this.elevation = 5
            }
        },
        nextStep() {
            this.$emit('thirdStepComplete', 4)
        },
        goBack() {
            this.$emit('goBack', 2)
        },
        submitImage(){
            this.isSubmitLoading = true
            setTimeout(() => {
                this.$emit('imageLoaded', {
                    image: this.previewImage
                })
                this.isSubmitLoading = false
                this.$toast.success('Image uploaded successfully.')
                this.imageLoaded = false
            }, 1000)

        }
    }
}
</script>

<style scoped>

</style>