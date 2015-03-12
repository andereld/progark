var ongoingGames = [];

var Game = function(player1, player2) {

    this.player1 = player1;
    this.player2 = player2;

    this.board1 = new Board(levels[Math.random()*levels.length]);
    this.board2 = new Board(levels[Math.random()*levels.length]);
};

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

var findGame = function(player) {

    for (var game in ongoingGames) {
        if (game.player1 === player || game.player2 === player) {
            return game;
        }
    }
    return null;
};

var startGame = function(player1, player2) {
    ongoingGames.push(new Game(player1, player2));
};