var Game = require("./Game");

var ongoingGames = [];

exports.findGame = function(player) {
  var i = 0;
  for (i; i < ongoingGames.length; i++) {
    var game = ongoingGames[i];
    if (game.player1 === player || game.player2 === player) {
      return game;
    }
  }
  return null;
};

exports.startGame = function(player1, player2) {
  ongoingGames.push(new Game(player1, player2));
};
