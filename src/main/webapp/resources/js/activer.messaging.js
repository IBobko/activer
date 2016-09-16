if (window.ACTIVER == undefined) {
    window.ACTIVER = {};
}

window.ACTIVER.Dialog = {

    init: function () {

    },

    /**
     * Used in case the use of the template, sending data, and reload the template.
     *
     * @param templateId It is a id of html tag.
     * @param url URL for sending of data.
     * @param callback Function is called by receiving data.
     * @constructor
     */
    Messages: function (templateId, url, callback) {
        this.r = function r(data, path, template) {
            for (var index in data) {
                var Path = path;
                if (path != "") {
                    Path += "_";
                }
                Path += index;
                if (data.hasOwnProperty(index)) {
                    if (typeof data[index] === "object" && data[index] != null) {
                        r(data[index], Path, template);
                    } else {
                        if (data[index] == null) {
                            data[index] = "";
                        }
                        template.val = template.val.replace(new RegExp('#' + Path, 'g'), data[index]);
                    }
                }
            }
        };
        var that = this;

        this.submit = function (data) {
            var that = this;

            $.ajax({
                url: url,
                data:data,
                type: 'POST',
                contentType: false,
                processData: false
            }).done(function(data){
                var template = that.getHtmlPost(data);
                callback(template);
            });
        };

        this.getHtmlPost = function (data) {
            var template = {val: $(templateId).val()};
            that.r(data, "", template);
            return template.val;
        }
    },

    Loader: function () {
        this.end = false;
        this.already_downloading = false;
        this.loaded = 1;
        this.lastScrollTop = 0;
    }
};