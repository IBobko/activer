<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<script src="/resources/sockjs-0.3.4.js"></script>
<script src="/resources/stomp.js"></script>
<script type="text/javascript">
  var stompClient = null;

  function setConnected(connected) {
    document.getElementById('conversationDiv').style.visibility = connected ? 'visible' : 'hidden';
    document.getElementById('response').innerHTML = '';
  }

  function connect() {
    var socket = new SockJS('/message1/${friend.id}');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
      setConnected(true);
      console.log('Connected: ' + frame);
      stompClient.subscribe('/message1/${friend.id}', function (greeting) {
        showGreeting(JSON.parse(greeting.body).text);
      });
    });
  }

  function disconnect() {
    if (stompClient != null) {
      stompClient.disconnect();
    }
    setConnected(false);
    console.log("Disconnected");
  }

  function sendName() {
    var text = document.getElementById('text').value;
    stompClient.send("/message1/" + ${friend.id}, {}, JSON.stringify({'text': text}));
  }

  function showGreeting(message) {
    var response = document.getElementById('response');
    var p = document.createElement('p');
    p.style.wordWrap = 'break-word';
    p.appendChild(document.createTextNode(message));
    response.appendChild(p);
  }
  connect();
</script>

<div>
  <div id="conversationDiv">
    <input type="text" id="text"/>
    <button id="sendName" onclick="sendName();">Send</button>

    <c:forEach items="${lastMessages}" var="message">
      <hr/>
      <div>${message.text}</div>
    </c:forEach>

    <p id="response"></p>
  </div>
</div>
