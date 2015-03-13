var Game = require("./Game");

var ongoingGames = [];

exports.findGame = function(player) {
  for (var game in ongoingGames) {
    if (game.player1 === player || game.player2 === player) {
      return game;
    }
  }
  return null;
};

exports.startGame = function(player1, player2) {
  ongoingGames.push(new Game(player1, player2));
};
