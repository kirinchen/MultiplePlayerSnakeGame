function Row(_y){
	var self = this;
	self.cells = ko.observableArray();
	self.y = _y;
	
	self.initWidth =function(width){
		for(var i=0;i<width;i++){
			self.cells.push(new Cell(i,self.y));
		}
	};
	
}