'use strict';

var usernamePage = document.querySelector('#username-page');
var chatPage = document.querySelector('#chat-page');
var usernameForm = document.querySelector('#usernameForm');
var messageForm = document.querySelector('#messageForm');
var messageInput = document.querySelector('#message');
var messageArea = document.querySelector('#messageArea');
var connectingElement = document.querySelector('.connecting');

var stompClient = null;
var username = null;
var id = null;
var chatId = null;

var colors = [
    '#2196F3', '#32c787', '#00BCD4', '#ff5652',
    '#ffc107', '#ff85af', '#FF9800', '#39bbb0'
];

window.onload = function () {
    changeHistoryMessagesColor();
    scrollMessageAreaToBottom();
}

function connect(event) {
    username = document.querySelector('#name').textContent;
    id = document.querySelector('#id').textContent;
    chatId = document.querySelector('#chatId').textContent;
    // if(username) {
        usernamePage.classList.add('hidden');
        chatPage.classList.remove('hidden');

        var socket = new SockJS('/ws');
        stompClient = Stomp.over(socket);

        stompClient.connect({}, onConnected, onError);
    // }
    event.preventDefault();
}


function onConnected() {
    // Subscribe to the Public Topic
    stompClient.subscribe('/topic/public', onMessageReceived);

    // Tell your username to the server
    stompClient.send("/app/chat.addUser",
        {},
        JSON.stringify({sender: username, type: 'JOIN'})
    )

    connectingElement.classList.add('hidden');
}


function onError(error) {
    connectingElement.textContent = 'Could not connect to WebSocket server. Please refresh this page to try again!';
    connectingElement.style.color = 'red';
}


function sendMessage(event) {
    var messageContent = messageInput.value.trim();

    if(messageContent && stompClient) {
        var chatMessage = {
            sender: username,
            message: messageInput.value,
            type: 'CHAT',
            senderId:id,
            chatId:chatId,
            messageDate:"date"

        };

        stompClient.send("/app/chat.sendMessage", {}, JSON.stringify(chatMessage));
        messageInput.value = '';
    }
    event.preventDefault();
}


function onMessageReceived(payload) {
    var message = JSON.parse(payload.body);
    var chatId = document.getElementById('chatId').innerHTML;


    if(message.type === 'JOIN') {
        // messageElement.classList.add('event-message');
        // message.content = message.sender + ' joined!';
    } else if (message.type === 'LEAVE') {
        // messageElement.classList.add('event-message');
        // message.content = message.sender + ' left!';
    } else if(chatId == message.chatId){
        var messageElement = document.createElement('li');
        messageElement.classList.add('chat-message');

        var avatarElement = document.createElement('i');
        var avatarText = document.createTextNode(message.sender[0]);
        avatarElement.appendChild(avatarText);
        avatarElement.style['background-color'] = getAvatarColor(message.sender);

        messageElement.appendChild(avatarElement);

        var usernameElement = document.createElement('span');
        var usernameText = document.createTextNode(message.sender);






        usernameElement.appendChild(usernameText);
        messageElement.appendChild(usernameElement);

    }
    var dateElement = document.createElement('p');

    var messageDate = document.createTextNode(message.messageDate);
    dateElement.appendChild(messageDate);
    messageElement.appendChild(dateElement);

    var textElement = document.createElement('p');
    var messageText = document.createTextNode(message.message);
    textElement.appendChild(messageText);

    messageElement.appendChild(textElement);

    messageArea.appendChild(messageElement);
    messageArea.scrollTop = messageArea.scrollHeight;
}


function getAvatarColor(messageSender) {
    var hash = 0;
    for (var i = 0; i < messageSender.length; i++) {
        hash = 31 * hash + messageSender.charCodeAt(i);
    }

    var index = Math.abs(hash % colors.length);
    return colors[index];
}


function changeHistoryMessagesColor(){
    var messages = document.getElementsByClassName('chat-message');
    for(var i=0; i< messages.length; i++){
        var message = messages[i];
        var name = message.querySelector("span").innerHTML;
        var color = getAvatarColor(name);
        message.querySelector("i").style['background-color'] = color;
    }
}

function scrollMessageAreaToBottom(){
    messageArea.scrollTop = messageArea.scrollHeight;
}

usernameForm.addEventListener('submit', connect, true)
messageForm.addEventListener('submit', sendMessage, true)
