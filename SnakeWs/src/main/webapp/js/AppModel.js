var _DOWN_KEY_CODE = 40;
var _UP_KEY_CODE = 38;
var _LEFT_KEY_CODE = 37;
var _RIGHT_KEY_CODE = 39;



function AppModel(stompClient) {
	var self = this;
	self.rows = ko.observableArray();
	self.userName = ko.observable();

	self.connect = function() {

		stompClient.connect({}, self._successHandler, function(error) {
			console.log("STOMP protocol error " + error);
		});

	};
	
	self.leftClick = function(){
		sendDirection(_LEFT_KEY_CODE);
	};
	
	self.rightClick = function(){
		sendDirection(_RIGHT_KEY_CODE);
	};
	
	self.upClick = function(){
		sendDirection(_UP_KEY_CODE);
	};
	
	self.downClick = function(){
		sendDirection(_DOWN_KEY_CODE);
	};
	
	self.keyDown = function(event){
		sendDirection(event.keyCode);
	};
	
	function sendDirection(keyCode){
		var e = getKeyEnum(keyCode);
		if(e != null){
			stompClient.send("/app/snake/direction/" + e, {},{});
		}
	}
	
	function getKeyEnum(keyCode){
		switch (keyCode) {
		case _DOWN_KEY_CODE:
			return "DOWN";
		case _UP_KEY_CODE:
			return "UP";
		case _LEFT_KEY_CODE :
			return "LEFT";
		case _RIGHT_KEY_CODE:
			return "RIGHT";
		}
		return null;
	}

	self._successHandler = function(frame) {
		console.log('Connected ' + frame);
		stompClient.subscribe("/app/hi", function(message) {
			init(JSON.parse(message.body));
		});
		stompClient.subscribe("/message/game", function(message) {
			updateGame(JSON.parse(message.body));
		});

	};

	function updateGame(data) {

		ko.utils.arrayForEach(self.rows(), function(row) {
			ko.utils.arrayForEach(row.cells(), function(cell) {
				cell.update(data,self.userName());
			});
		});
	}

	function init(data) {
		width = data.width;
		height = data.height;
		initGameSize(width, height);
		self.userName(data.myName);
	}

	function initGameSize(width, height) {
		for (var i = 0; i < height; i++) {
			row = new Row(i);
			row.initWidth(width);
			self.rows.push(row);
		}
	}
}