var exec = require('cordova/exec');

exports.getCommand = function (arg0, success, error) {
    exec(success, error, 'MikrotikApi', 'getCMD', [arg0]);
};

exports.execCommand = function (arg0, success, error) {
    exec(success, error, 'MikrotikApi', 'execCMD', [arg0]);
};

exports.Login = function (arg0, arg1, arg2, success, error) {
    exec(success, error, 'MikrotikApi', 'login', [arg0, arg1, arg2]);
};
