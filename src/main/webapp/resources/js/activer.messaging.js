if (window.ACTIVER == undefined) {
  window.ACTIVER = {};
}

window.ACTIVER.Dialog = {
  stompClient: null,
  init: function() {
    var that = this;
    $('#messages').scrollTop($('#messages').height());
    $("#dialogForm").submit(function () {
      var text = this.querySelector("#text").value
      that.stompClient.send(window.ACTIVER.context_path + "/message1/" + window.ACTIVER.Data.profile.id, {}, JSON.stringify({'accountDataTo': window.ACTIVER.Data.browseProfile, 'text': text}));
      document.getElementById('text').value = "";
      return false;
    });
    this.connect();
  },
  connect: function() {
    var that = this;
    var socket = new SockJS(window.ACTIVER.context_path + '/message1/' + window.ACTIVER.Data.profile.id);
    this.stompClient = Stomp.over(socket);
    this.stompClient.connect({}, function (frame) {
      that.stompClient.subscribe('/message2/' + window.ACTIVER.Data.profile.id, function (greeting) {
        var message = JSON.parse(greeting.body);
        if (message.text == null || message.text == undefined || message.text == ""){
          return;
        }
        var template = $("#templatePost").html();
        template = template.replace(/%date%/gi,message.date);
        template = template.replace(/%firstName%/gi,message.sender.firstName);
        template = template.replace(/%lastName%/gi,message.sender.lastName);
        template = template.replace(/%text%/gi,message.text);
        $('#messages').append(template);
        $('#messages').scrollTop($('#messages').height());
      });
    });
  }
};