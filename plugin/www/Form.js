cordova.define("cordova-plugin-formapp.Form", function(require, exports, module) {
    const argscheck = require('cordova/argscheck');
    const exec = require('cordova/exec');
    const cordova = require('cordova');
    
    const Form = function () {
          console.log("Form instanced");
    };
    
    Form.prototype.show = function (msg, onSuccess, onError) {
        const onErr = onError ? onError : () => {};
        const onSucc = onSuccess ? onSuccess : () => {};
    
        //argscheck.checkArgs('fF', 'Form.show', [msg]);
        exec(onSucc, onErr, 'Form', 'show', [msg]);
    };
    
    window.deneme=function () {
        alert('bak yavvv')
    }
    
    module.exports = new Form();
    
    
    });
    