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
		updateEgg(data.eggs);
		updateEmpty();
		self._isSetCssClass = false;
	};
	
	function updateEgg(eggs){
		for(var key in eggs){
			egg = eggs[key];
			if(egg.x == self.x && egg.y == self.y){
				if(!self._isSetCssClass ){
					self._isSetCssClass = true;
					self.cssClass(_EGG_CSS_CLASS);
				}
			}
		}
	}
	
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
				updateRivalSnake(snake);
			}
		}
	}
	
	function updateRivalSnake(snake){
		updateSnakesByClass(snake,_RIVAL_CSS_CLASS);
	}
	
	function updateSelfSnake(snake){
		updateSnakesByClass(snake,_SELF_CSS_CLASS);
	}
	
	
	function updateSnakesByClass(snake,_cssClass){
		for(var key in snake.bodys){
			body = snake.bodys[key];
			if(body.x == self.x && body.y == self.y){
				if(!self._isSetCssClass ){
					self._isSetCssClass = true;
					if(self.cssClass() != _cssClass){
						self.cssClass(_cssClass);
					}
				}
			}
		}
	}
}