var $ = require('jquery');
jQuery.fn.maxLength = function(max,callback){ 
    return this.each(function(){
        var type = this.tagName.toLowerCase(); 
        var inputType = this.type? this.type.toLowerCase() : null; 
        if(type == "input" && inputType == "text" || inputType == "password"){ 
            //Apply the standard maxLength 
           // http://www.sharejs.com
            this.maxLength = max; 
        } else if(type == "textarea"){
            this.onkeypress = function(e){ 
                var ob = e || event; 
                var keyCode = ob.keyCode; 
                var hasSelection = document.selection? document.selection.createRange().text.length > 0 :this.selectionStart != this.selectionEnd; 
                return !(this.value.length >= max && (keyCode > 50 || keyCode == 32 || keyCode == 0 || keyCode == 13) &&!ob.ctrlKey && !ob.altKey && !hasSelection); 
            }; 
            this.onkeyup = function(){
                typeof callback === 'function' && callback(max-this.value.length);
                 if(this.value.length > max){ 
                    this.value = this.value.substring(0,max); 
                } 
            };
        }
    });
};