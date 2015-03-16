var Board = require("./Board");

var levels = ["...XX........XX..XXX...XX..........................X.........X...XXX...X.........X..XX..............",
  ".XXX.......XXX....X.........X.........X....XX...X.............X..XXX....X.........X.................",
  "................................XX.........XXX........XXXX.....XXX.....XXX.......XXX................"];

function Game(player1, player2) {

  this.player1 = player1;
  this.player2 = player2;
  this.next = player2;

  this.board1 = new Board(levels[Math.floor(Math.random()*levels.length)]);
  this.board2 = new Board(levels[Math.floor(Math.random()*levels.length)]);
}

Game.prototype.isGameOver = function() {
  return this.board1.isGameOver() || this.board2.isGameOver();
};

Game.prototype.fire = function (player, x, y) {
  if (player === this.player1) {
    this.next = this.player2;
    return this.board1.fire(x, y);
  }
  else if (player === this.player2) {
    this.next = this.player1;
    return this.board2.fire(x, y);
  }
};

module.exports = Game;
