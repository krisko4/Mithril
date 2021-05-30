<template>
    <v-app>
        <v-app-bar
            app
            clipped-right
            clipped-left
            height="72"
        >
            <span class="headline">Chatroom</span>
            <v-spacer></v-spacer>
            <v-btn text>
                <v-icon>
                    mdi-home
                </v-icon>
            </v-btn>
            <v-spacer></v-spacer>
            <span class="mr-3">
                <v-avatar>
                <v-img :src="myImage"></v-img>
            </v-avatar>
                <span class="ml-2">{{ firstName }}</span>
            </span>

        </v-app-bar>

        <v-navigation-drawer
            app
            width="300"
            clipped
        >

            <v-list>
                <v-list-item-group v-model="selectedItem">
                    <v-list-item v-for="(friend, i) in friends" :key="i">
                        <v-list-item-avatar>
                            <v-img :src="friend.image"></v-img>
                        </v-list-item-avatar>
                        <v-list-item-content @click="openChat(friend)">
                            <v-list-item-title>
                                {{ friend.fullName }}
                            </v-list-item-title>
                        </v-list-item-content>
                    </v-list-item>
                </v-list-item-group>
            </v-list>
        </v-navigation-drawer>

        <v-navigation-drawer
            app
            clipped
            right
        >
            <v-list v-if="recentMessages">
                <v-list-item
                    v-for="(recentMessage, i) in recentMessages"
                    :key="i"
                    link
                    three-line
                >
                    <v-list-item-avatar>
                        <v-img :src="recentMessage.senderImg"></v-img>
                    </v-list-item-avatar>
                    <v-list-item-content>
                        <v-list-item-title>{{rece}}</v-list-item-title>
                        <v-list-item-subtitle>Szanowni panstwo kochani moi</v-list-item-subtitle>
                    </v-list-item-content>
                </v-list-item>
            </v-list>
        </v-navigation-drawer>
        <v-main>
            <v-container fluid v-if="selectedItem">
                <v-toolbar class="mb-5" flat>
                    <v-spacer></v-spacer>
                    <v-badge bordered bottom dot offset-x="9" offset-y="13" color="green">
                        <v-avatar>
                            <v-img :src="friends[selectedItem].image"></v-img>
                        </v-avatar>
                    </v-badge>
                    <v-toolbar-title class="title ml-3">
                        {{ friends[selectedItem].fullName }}
                    </v-toolbar-title>
                </v-toolbar>
                <v-divider></v-divider>
                <v-col cols="12">
                    <v-toolbar flat v-for="(message, i) in messages" :key="i" class="mt-10">
                        <v-spacer></v-spacer>
                        <v-card color="blue" dark max-width="300px">
                            <v-list-item three-line>
                                <v-list-item-content>
                                    <div class="mb-4">
                                        {{ message.content }}
                                    </div>
                                    <v-list-item-subtitle>16 mins ago <span class="ml-10">Seen 15 mins ago</span>
                                    </v-list-item-subtitle>
                                </v-list-item-content>
                            </v-list-item>
                        </v-card>
                        <v-badge bordered bottom dot offset-x="9" offset-y="13" color="green">
                            <v-avatar class="ml-3">
                                <v-img :src="message.senderImg">

                                </v-img>
                            </v-avatar>

                        </v-badge>
                    </v-toolbar>
                </v-col>

            </v-container>
        </v-main>

        <v-footer
            app
            color="transparent"
            height="72"
            inset
        >
            <v-card>{{ typedMessage }}</v-card>
            <v-text-field
                v-model="typedMessage"
                rounded
                dense
                filled
                placeholder="Send message"
            ></v-text-field>
            <v-btn @click="sendMessage">Send</v-btn>
        </v-footer>
    </v-app>
</template>

<script>

import axios from "axios";
import SockJS from "sockjs-client"
import Stomp from "webstomp-client";



export default {
    name: "Messenger",
    data() {
        return {
            typedMessage: '',
            selectedItem: '',
            friends: [],
            myImage: 'http://localhost:8080/images/doctors/' + localStorage.getItem('imageName'),
            firstName: localStorage.getItem('firstName'),
            messages: [],
            recentMessages: [],
            webSocket: null,
            stompClient: null,
            received_messages: []
        }
    },

    created() {

        this.webSocket = new SockJS('http://localhost:8080/messenger')
        this.stompClient = Stomp.over(this.webSocket)
        this.stompClient.connect(
            {},
            frame => {
                console.log(frame);
                this.stompClient.subscribe('topic/greetings', tick => {
                    console.log(tick);
                    this.received_messages.push(JSON.parse(tick.body).content);
                });
            },
            error => {
                console.log(error);
            }
        );

        axios.get('http://localhost:8080/users/doctors')
            .then((response) => {
                response.data.filter((friend) => {
                    this.friends.push({
                        id: friend.id,
                        fullName: friend.firstName + ' ' +  friend.secondName + ' ' + friend.lastName,
                        image: 'http://localhost:8080/images/doctors/' + friend.imageName
                    })
                })
            })
        axios.get('http://localhost:8080/doctors/' + localStorage.getItem('id') + '/messages')
            .then((response) => {
                response.data.filter((message) => {
                    let friend = this.friends.find((friend) => {
                        return friend.id === message.senderId
                    })
                    this.recentMessages.push({
                        content: message.content,
                        date: message.date,
                        friend: friend
                    })
                })

            })
    },
    methods: {

        sendMessage(){
          // axios.post('http://localhost:8080/new-message', {
          //     content: this.typedMessage,
          //     senderId: localStorage.getItem('id'),
          //     receiverId: this.friends[this.selectedItem].id,
          //     date: '2021-03-19 06:00'
          // })
           // this.webSocket.send('siemanko')
            this.stompClient.send('app/hello', 'powitanka')
        },

        openChat(friend) {
            axios.get('http://localhost:8080/messages', {
                params: {
                    senderId: friend.id,
                    receiverId: localStorage.getItem('id')
                }
            }).then((response) => {
                let senderImg
                response.data.filter((message) => {
                    if (friend.id === message.senderId) {
                        senderImg = friend.image
                    } else {
                        senderImg = this.myImage
                    }
                    this.messages.push({
                        content: message.content,
                        date: message.date,
                        senderImg: senderImg,
                    })
                })

            })
        }
    }

}
</script>

<style scoped>

</style>