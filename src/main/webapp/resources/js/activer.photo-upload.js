if (window.ACTIVER == undefined) {
    window.ACTIVER = {};
}

window.ACTIVER.PhotoUpload = {
    init:function() {
        console.log("hello");
        /** TODO FileLoad  сделать выбор по идентификатору**/
        $('[type="file"]').change(function(e){
            var file = e.target.files[0];
            var imageType = /image.*/;

            if (!file.type.match(imageType))
                return;

            var reader = new FileReader();
            reader.onload = function fileOnload(){
                var image = new Image();
                image.onload = function() {
                    var canvas = document.createElement('canvas');
                    canvas.width = 620;
                    canvas.height = 416;
                    var newHeight = Math.round(620 * image.height / image.width);
                    canvas.getContext('2d').drawImage(image, 0, 0, 620, newHeight);
                    $('.resize-image').get(0).src = canvas.toDataURL();
                    resizeableImage($('.resize-image'));
                };


                $.post("/test/",{'resizedImage':this.result},function(data){
                    image.src = data;
                });
            };
            reader.readAsDataURL(file);
        });
    }
};