var Cell = require("./Cell");

function Board(boardString) {
  this.board = boardString.split("").map(function(e) {
    if(e == 'X') {
      return new Cell(true);
    }
    return new Cell(false);
  });
}

Board.prototype.fire = function(x, y) {
  this.board[y * 10 + x].isHit = true;
  return this.board[y * 10 + x].isShip;
};

Board.prototype.isGameOver = function() {
  return this.board.filter(function(cell) {
      return cell.isShip && !cell.isHit;
  }).length < 1;
};

module.exports = Board;
