var models = require('../models');

/*
 * Return the promise of a game belonging to player
 */
exports.findGame = function(player) {
  return models.Game.find({
    include: [models.Board],
    where: models.Sequelize.and(
      { finished: false },
      models.Sequelize.or(
        { player1: player },
        { player2: player }
      )
    )
  });
};

/*
 * Start a new game for two players.
 */
exports.startGame = function(player1, player2) {
  models.Game.create({
    player1: player1,
    player2: player2,
    next: player1
  }).then(function(game) {
    var player1Board = models.Board.randomBoard(),
        player2Board = models.Board.randomBoard();
    game.boards = [player1Board, player2Board];
    game.save();
  });
};
