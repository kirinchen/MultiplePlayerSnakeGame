function Row(){
	var self = this;
	self.cells = ko.observableArray();
	initTest();
	
	function initTest(){
		for(var i=0;i<10;i++){
			self.cells.push(new Cell());
		}
	}
	
}