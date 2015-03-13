var Board = require("./Board");
var levels = require("./Levels");

function Game(player1, player2) {

  this.player1 = player1;
  this.player2 = player2;

  this.board1 = new Board(levels[Math.floor(Math.random()*levels.length)]);
  this.board2 = new Board(levels[Math.floor(Math.random()*levels.length)]);
}

Game.prototype.isGameOver = function() {
  return this.board1.isGameOver() || this.board2.isGameOver();
};

Game.prototype.fire = function (player, x, y) {
  if (player === this.player1) {
    return this.board1.fire(x, y);
  }
  else if (player === this.player2) {
    return this.board2.fire(x, y);
  }
};

module.exports = Game;
