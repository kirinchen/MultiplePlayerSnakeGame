function Test(stompClient) {
	var self = this;

	self.startTime = ko.observable(new Date());
	self.endtTime = ko.observable(new Date());
	self.testData = ko.observable();
	
	self.spendTime = ko.computed(function() {
	        return self.endtTime().getMilliseconds() - self.startTime().getMilliseconds();
	    }, self);

	self.connect = function() {

		stompClient.connect({}, self._successHandler, function(error) {
			console.log("STOMP protocol error " + error);
		});

	};
	
	self._successHandler = function(frame) {
		console.log('Connected ' + frame);
		stompClient.subscribe("/app/test", function(message) {
			alert(JSON.parse(message.body));
		});
		stompClient.subscribe("/message/test", function(message) {
			self.endtTime(new Date());
			self.testData(message.body);
		});
	};
	
}