if (window.ACTIVER == undefined) {
  window.ACTIVER = {};
}

window.ACTIVER.Dialog = {

  init: function() {

  },
  Messages : function(templateId,url,callback){
    this.r = function r(data,path,template) {
      for (var index in data) {
        var Path = path;
        if (path != "") {
          Path += "_";
        }
        Path += index;
        if (typeof data[index] === "object") {
          r(data[index],Path,template);
        } else {
          console.log("#"+Path + " = " + data[index]);
          template.val = template.val.replace("#"+Path, data[index]);
        }
      }
    };
    var that = this;

    this.submit = function(data){
      $.post(url, data, function (data) {
        var template={val:$(templateId).val()};
        that.r(data,"",template);
        callback(template.val);
      });
    }
  }
};