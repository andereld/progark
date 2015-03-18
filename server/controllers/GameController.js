var Game = require('../models/Game');

/*
 * Find array of all games in database that are not finished
 */
exports.findOngoingGames = function() {
  return Game.findAll({ where: { finished: false } });
};

/*
 * Find game belonging to player
 */
exports.findGame = function(player) {
  return findOngoingGames().filter(function(element, index, array) {
    return element.player1 === player || element.player2 === player;
  })[0];
};

/*
 * Start a new game for two players.
 */
exports.startGame = function(player1, player2) {
  Game.create({
    player1: player1,
    player2: player2,
    next: player1,
    board1: Board.classMethods.randomBoard(),
    board2: Board.classMethods.randomBoard()
  });
};
