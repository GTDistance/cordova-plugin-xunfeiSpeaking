var exec = require('cordova/exec');

var xunfeiSpeaking = {
	startListen:function (success,error){
		exec(success,error,"XunfeiSpeaking","startListen",[]);
	},
	stopListen:function(success,error){
		exec(success,error,"XunfeiSpeaking","stopListen",[]);
	}
};

module.exports = xunfeiSpeaking;
