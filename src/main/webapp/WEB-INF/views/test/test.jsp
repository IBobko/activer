<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!-- Load Feather code -->
<script type="text/javascript" src="http://feather.aviary.com/imaging/v2/editor.js"></script>

<!-- Instantiate Feather -->
<script type='text/javascript'>
    var featherEditor = new Aviary.Feather({
        apiKey: 'your-client-id-here',
        theme: 'dark', // Check out our new 'light' and 'dark' themes!
        tools: 'all',
        appendTo: '',
        onSave: function(imageID, newURL) {
            var img = document.getElementById(imageID);
            img.src = newURL;
            console.log(newURL);
        },
        onError: function(errorObj) {
            alert(errorObj.message);
        }
    });
    function launchEditor(id, src) {
        featherEditor.launch({
            image: id,
            url: src
        });
        return false;
    }
</script>

<div id='injection_site'></div>

<img id='image1' src='http://images.aviary.com/imagesv5/feather_default.jpg'/>

<!-- Add an edit button, passing the HTML id of the image and the public URL of the image -->
<p><input type='image' src='http://images.aviary.com/images/edit-photo.png' value='Edit photo' onclick="return launchEditor('image1', 'http://images.aviary.com/imagesv5/feather_default.jpg');" /></p>