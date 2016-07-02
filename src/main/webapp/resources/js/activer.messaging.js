if (window.ACTIVER == undefined) {
    window.ACTIVER = {};
}

window.ACTIVER.Dialog = {

    init: function () {

    },

    Messages: function (templateId, url, callback) {
        this.r = function r(data, path, template) {
            for (var index in data) {
                var Path = path;
                if (path != "") {
                    Path += "_";
                }
                Path += index;
                if (data.hasOwnProperty(index) && typeof data[index] === "object") {
                    r(data[index], Path, template);
                } else {
                    template.val = template.val.replace("#" + Path, data[index]);
                }
            }
        };
        var that = this;

        this.submit = function (data) {
            var that = this;
            $.post(url, data, function (data) {
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