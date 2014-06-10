function Player(info){
	
	var self = this;
	self.userName = ko.observable(info.userName);
	self.hp = ko.observable(info.hp);
	showGG();
	
	function showGG(){
		if(self.hp() <= 0){
			showWarning("!!!  " +self.userName() + " GG");
		}
		
	}
	
	
}