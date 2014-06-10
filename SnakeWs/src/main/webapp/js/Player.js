function Player(info){
	
	var self = this;
	self.userName = ko.observable(info.userName);
	self.hp = ko.observable(info.hp);
	
	
}