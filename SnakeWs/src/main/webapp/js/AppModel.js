function AppModel(stompClient) {
	var self = this;
	self.rows = ko.observableArray();
	self.userName = ko.observable();

	self.connect = function() {

		stompClient.connect({}, self._successHandler, function(error) {
			console.log("STOMP protocol error " + error);
		});

	};

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