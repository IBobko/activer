if (window.ACTIVER == undefined) {
    window.ACTIVER = {};
}

window.ACTIVER.Global = {
    stompClient: null,
    /* This structure is intended for messaging */
    message: {
        to: null,
        message: null,
        type: null
    },
    init: function() {
        this.connect();
    },
    submit: function(data){
        this.stompClient.send("/actions", {}, JSON.stringify(data));
    },
    connect: function() {
        var that = this;
        var socket = new SockJS(window.ACTIVER.context_path + '/global');
        this.stompClient = Stomp.over(socket);
        this.stompClient.connect({}, function (frame) {
            console.log(frame);
            that.stompClient.subscribe('/global2', function (greeting) {
                var result = JSON.parse(greeting.body);
                console.log(result);
                $("#popupWindow").css("display","block");
            });
        });
    }
};