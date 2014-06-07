var _EGG_CSS_CLASS = "egg";
var _SELF_CSS_CLASS = "self";
var _RIVAL_CSS_CLASS = "rival";

function Cell(_x,_y){
	var self = this;
	self.x = _x;
	self.y = _y;
	self.cssClass = ko.observable("");
	
	self._isSetCssClass = false;
	
	self.update = function(data,userName){
		updateSnakes(data.snakes,userName);
		updateEmpty();
		self._isSetCssClass = false;
	};
	
	function updateEmpty(){
		if(!self._isSetCssClass){
			if(self.cssClass() != ""){
				self.cssClass("");
			}
		}
	}
	
	function updateSnakes(snakes,userName){
		for(var key in snakes){
			snake = snakes[key];
			if(snake.userName == userName){
				updateSelfSnake(snake);
			}else{
				
			}
		}
	}
	
	function updateSelfSnake(snake){
		updateSnakes(snake,_SELF_CSS_CLASS);
	}
	
	
	function updateSnakes(snake,_cssClass){
		for(var key in snake.bodys){
			body = snake[key];
			if(body.x == self.x && body.y == self.y){
				if(!self._isSetCssClass ){
					self._isSetCssClass = true;
					self.cssClass(_cssClass);
				}
			}
		}
	}
}