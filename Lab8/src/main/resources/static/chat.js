const stompClient = new StompJs.Client({
    brokerURL: 'ws://localhost:8080/poly',
    connectHeaders: {
        login: 'user',
        passcode: 'password',
    }
});

stompClient.onConnect = (frame) => {
    console.log('onConnect()', frame);
    stompClient.subscribe('/topic/publicChatRoom', showMessage);

    // Tell your username to the server
    var message = { sender: username = $('#username').val(), type: 'JOIN' }
    stompClient.publish({
        destination: "/app/chat.addUser",
        body: JSON.stringify(message)
    });
};

stompClient.onWebSocketError = (error) => {
    console.log("onWebSocketError()", error);
};

stompClient.onStompError = (error) => {
    console.log("onStompError()", error);
};

function showMessage(payload) {
    console.log(payload)
    var msg = JSON.parse(payload.body);
    switch (msg.type) {
        case 'CHAT':
            $('#messages').append(`<li>${msg.sender}: ${msg.content}</li>`);
            break;
        default:
            $('#messages').append(`<li>${msg.sender}: ${msg.type}</li>`);
            break;
    }
}

$("#connect").click(() => {
    stompClient.activate();
});

$("#disconnect").click(() => {
    stompClient.deactivate();
});

$("#send").click(() => {
    var message = {
        sender: username,
        content: $("#message").val(),
        type: 'CHAT'
    }
    stompClient.publish({
        destination: "/app/chat.sendMessage",
        body: JSON.stringify(message)
    });
    $("#message").val('')
});