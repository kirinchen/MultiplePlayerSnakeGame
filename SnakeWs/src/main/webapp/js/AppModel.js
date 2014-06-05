function AppModel(){
	var self = this;
	self.rows = ko.observableArray();
	initTest();
	
	function initTest(){
		for(var i=0;i<10;i++){
			self.rows.push(new Row());
		}
	}
}