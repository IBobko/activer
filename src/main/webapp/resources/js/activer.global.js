if (window.ACTIVER == undefined) {
    window.ACTIVER = {};
}

window.ACTIVER.Global = {
    stompClient: null,
    message: {
        to: null,
        message: null,
        type: null
    },
    init: function() {
        //var that = this;
        //$('#messages').scrollTop($('#messages').height());
        //$("#dialogForm").submit(function () {
        //    var text = this.querySelector("#text").value;
        //    that.stompClient.send("/message1/" + window.ACTIVER.Data.profile.id, {}, JSON.stringify({'accountDataTo': window.ACTIVER.Data.browseProfile, 'text': text}));
        //    document.getElementById('text').value = "";
        //    return false;
        //});
        this.connect();
        //alert("init");
    },
    submit: function(data){
        this.stompClient.send("/actions", {}, JSON.stringify(data));

        //var that = this;
        //$('#messages').scrollTop($('#messages').height());
        //$("#dialogForm").submit(function () {
        //    var text = this.querySelector("#text").value;
        //    that.stompClient.send("/message1/" + window.ACTIVER.Data.profile.id, {}, JSON.stringify({'accountDataTo': window.ACTIVER.Data.browseProfile, 'text': text}));
        //    document.getElementById('text').value = "";
        //    return false;
        //});
    },
    connect: function() {
        var that = this;
        var socket = new SockJS(window.ACTIVER.context_path + '/global/' + window.ACTIVER.Data.profile.id);
        this.stompClient = Stomp.over(socket);
        this.stompClient.connect({}, function (frame) {
            console.log(frame);
            that.stompClient.subscribe('/global2/' + window.ACTIVER.Data.profile.id, function (greeting) {
                //noinspection JSUnusedLocalSymbols
                var result = JSON.parse(greeting.body);


                //alert(greeting);
                $("#popupWindow").css("display","block");
            });
        });
    }
};