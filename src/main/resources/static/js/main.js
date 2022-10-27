'use strict';

var usernamePage = document.querySelector('#username-page');
var chatPage = document.querySelector('#chat-page');
var usernameForm = document.querySelector('#usernameForm');
var messageForm = document.querySelector('#messageForm');
var messageInput = document.querySelector('#message');
var messageArea = document.querySelector('#messageArea');
var connectingElement = document.querySelector('.connecting');

var messageArea1 = document.querySelector('#messageArea1');
var messageArea3 = document.querySelector('#messageArea3');

var stompClient = null;
var username = null;

var colors = [
    '#2196F3', '#32c787', '#00BCD4', '#ff5652',
    '#ffc107', '#ff85af', '#FF9800', '#39bbb0'
];

function connect(event) {


    username = document.querySelector('#name').value.trim();

    if(username) {

        usernamePage.classList.add('hidden');
        chatPage.classList.remove('hidden');

        var socket = new SockJS('/ws');
        stompClient = Stomp.over(socket);
        stompClient.connect({}, onConnected, onError);
    }
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
            content: messageInput.value,
            type: 'CHAT'
        };
        stompClient.send("/app/chat.sendMessage", {}, JSON.stringify(chatMessage));
        messageInput.value = '';
    }
    event.preventDefault();
}


function onMessageReceived(payload) {
    var message = JSON.parse(payload.body);

    var avatarText3 = document.createTextNode(message.sender);
    var avatarText4 = document.createTextNode("online");
    var x1=document.createElement('li');
    var y1=document.createElement('a');
    var z1=document.createElement('h3');
    var p1=document.createElement('img');
    var q1=document.createElement('p');
    var m1=document.createElement('i');
    m1.className="fa fa-circle text-light-green mr-1";
    x1.appendChild(y1);
    y1.appendChild(z1);
    y1.appendChild(q1);
    q1.appendChild(m1);
    q1.appendChild(avatarText4)
    z1.appendChild(avatarText3);
    p1.src="vendors/images/chat-img2.jpg";
    y1.appendChild(p1);


    var messageElement = document.createElement('li');

    if(message.type === 'JOIN') {
        messageElement.classList.add('event-message');
        message.content = message.sender + ' joined!';
        messageArea3.appendChild(x1);
    } else if (message.type === 'LEAVE') {
        messageElement.classList.add('event-message');
        message.content = message.sender + ' left!';
    } else {
        messageElement.classList.add('chat-message');
        var x=document.createElement('li');
        var y=document.createElement('a');
        var z=document.createElement('h3');
        var p=document.createElement('img');
        var q=document.createElement('p');
        x.appendChild(y);
        y.appendChild(z);
        y.appendChild(q);
        p.src="vendors/images/chat-img2.jpg";
        y.appendChild(p);
        messageArea1.appendChild(x);
        var avatarElement = document.createElement('i');
        var avatarText = document.createTextNode(message.sender[0]);
        var avatarText1 = document.createTextNode(message.sender);
        var avatarText2 = document.createTextNode(message.content);
        avatarElement.appendChild(avatarText);
        avatarElement.style['background-color'] = getAvatarColor(message.sender);
        z.appendChild(avatarText1);
        q.appendChild(avatarText2)
        messageElement.appendChild(avatarElement);

        var usernameElement = document.createElement('span');
        var usernameText = document.createTextNode(message.sender);
        usernameElement.appendChild(usernameText);
        messageElement.appendChild(usernameElement);
    }

    var textElement = document.createElement('p');
    var messageText = document.createTextNode(message.content);
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

usernameForm.addEventListener('submit', connect, true)
messageForm.addEventListener('submit', sendMessage, true)