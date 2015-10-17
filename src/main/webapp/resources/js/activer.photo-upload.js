if (window.ACTIVER == undefined) {
    window.ACTIVER = {};
}

window.ACTIVER.PhotoUpload = {
    init:function() {


        $('[type="file"]').change(function(e){

            var file = e.target.files[0],
                imageType = /image.*/;

            if (!file.type.match(imageType))
                return;

            var reader = new FileReader();
            reader.onload = fileOnload;
            reader.readAsDataURL(file);
        });

        var image = new Image();
        image.onload = function() {
            alert("onload");
            var canvas = $('#canvas')[0];
            var context = canvas.getContext('2d');
            context.drawImage(image, 0, 0);
        };


        function fileOnload(e) {
            console.log(this.result);
            $.post("/test/",{'resizedImage':this.result},function(data){
                console.log("Final" + data);

                image.src = data;
            });
        }

    }
};