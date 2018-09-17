/**
 * @author Kuldeep Kumar
 * @summary js interface for the cordova plugin 
 */

var exec = require('cordova/exec');

//I have customized the code for my convience 
//easy to understand , you can it remain the same to.
var testing = function(){};

/**
 * This function is called from the cordova app @showDatePicker
 * and this will invoke the active #showDateMethod in datepickercordovaplugin
 * @param {} arg0 would be provided by the user from the cordova application
 * @param {*} success 
 * @param {*} error 
 */

testing.prototype.showDatePicker = function (arg0, success, error) {
    exec(success, error, 'datepickercordovaplugin', 'showDateMethod', [arg0]);
};


testing.install = function () {
    if (!window.plugins) {
      window.plugins = {};
    }
    window.plugins = new testing();
    return window.plugins;
  
  };
cordova.addConstructor(testing.install);
