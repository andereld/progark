// parses a 10x10 array to a 100 char string
exports.getRandomBoardAsString = function() {
  var board = generateRandomBoard(10);
  var boardString = "";
  for (var i = 0; i < board.length; i++) {
    var row = board[i];

    for (var j = 0; j < board.length; j++) {
      if (row[j] == true) {
        boardString += "S";
      }
      else {
        boardString += ".";
      }
    }
  }
  return boardString;
};

// Generates an n-by-n array with all values set to false.
var generateEmptyBoard = function(lengthOfSides) {
  var board = new Array(lengthOfSides);

  for (var i = 0; i < lengthOfSides; i++) {
    board[i] = new Array(lengthOfSides);
    for (var j = 0; j < lengthOfSides; j++) {
      board[i][j] = false;
    }
  }

  return board;
};

// Generates an n-by-n (n >= 5) board with five ships placed randomly
// (both horizontally and vertically).
var generateRandomBoard = function(lengthOfSides) {
  var board = generateEmptyBoard(lengthOfSides);
  var ships = [[2,3], [1,4], [1,3], [1,3], [1,2]];

  for (var i = 0; i < ships.length; i++) {
    var placed = false;

    while (!placed) {
      var ship = ships[i];
      var x = Math.floor((Math.random() * lengthOfSides));
      var y = Math.floor((Math.random() * lengthOfSides));
      var orientation = Math.random() > 0.5 ? 'hori' : 'verti';
      placed = tryToPlaceShip(ship, board, x, y, orientation);
    }
  }

  return board;
};

// Helper method to check that a ship can be placed at the given coordinates.
var canPlace = function(ship, board, x, y, orientation) {
  var length = orientation == 'hori' ? ship[1] : ship[0];
  var height = orientation == 'hori' ? ship[0] : ship[1];

  if ((x + length) > board[0].length || (y + height) > board.length) {
    return false;
  }

  for (var i = y; i < y + height; i++) {
    for (var j = x; j < x + length; j++) {
      if (board[i][j] == true) {
        return false;
      }
    }
  }

  return true;
};

// Helper method to place a ship at the given coordinates.
var tryToPlaceShip = function(ship, board, x, y, orientation) {
  var length = orientation == 'hori' ? ship[1] : ship[0];
  var height = orientation == 'hori' ? ship[0] : ship[1];

  if (canPlace(ship, board, x, y, orientation)) {
    for (var i = y; i < y + height; i++) {
      for (var j = x; j < x + length; j++) {
        board[i][j] = true;
      }
    }

    return true;
  }

  return false;
};
