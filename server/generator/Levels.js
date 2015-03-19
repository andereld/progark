
// setup a 10x10 array
var board = new Array(10);
var ships = [[2,3], [1,4], [1,3], [1,3], [1,2]];

for(var i = 0; i < 10; i++) {
  board[i] = [];
  for(var j = 0; j < 10; j++) {
    board[i][j] = false;
  }
}

// parses a 10x10 array to a 100 char string
exports.getRandomBoardAsString = function() {
  var board = generateRandomBoard();
  var boardString = "";
  for(var i = 0; i < board.length; i++) {
    var row = board[i];

    for(var j = 0; j < board.length; j++) {
      if(row[j] == true) {
        boardString += "X";
      }
      else {
        boardString += ".";
      }
    }
  }
  return boardString;
}

// generates a random 10x10 board with ships placed either horizontally or vertically
var generateRandomBoard = function() {
  for(var i = 0; i < ships.length; i++) {

    var placed = false;

    while(!placed) {
      var ship = ships[i];
      var x = Math.floor((Math.random() * 10));
      var y = Math.floor((Math.random() * 10));
      var orientation = Math.random() > 0.5 ? 'hori' : 'verti';
      placed =  placeShip(ship, x, y, orientation);

    }
  }
  return board;
}

// helper method to check that a ship can be placed at the given coordinates
var canPlace = function(ship, x, y, orientation) {
  var length = orientation == 'hori' ? ship[1] : ship[0];
  var height = orientation == 'hori' ? ship[0] : ship[1];

  if((x + length) > board[0].length || (y + height) > board.length) {
    return false;
  }

  for(var i = y; i < y + height; i++) {
    for(var j = x; j < x + length; j++) {
      if(board[i][j] == true) {
        return false;
      }
    }
  }
  return true;
};

// helper method to place a ship at the given coordinates
var placeShip = function(ship, x, y, orientation) {
  var length = orientation == 'hori' ? ship[1] : ship[0];
  var height = orientation == 'hori' ? ship[0] : ship[1];

  if(canPlace(ship, x, y, orientation)) {
    for(var i = y; i < y + height; i++) {
      for(var j = x; j < x + length; j++) {
        board[i][j] = true;
      }
    }
    return true;
  }
  return false;

};

