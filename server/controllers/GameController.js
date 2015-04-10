var models = require('../models');

/*
 * Returns the promise of a game belonging to the given player.
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
 * Starts a new game for two players.
 * Returns a promise which can be used to access the newly created game.
 */
exports.startGame = function(player1, player2) {
  return models.Game.create({
    player1: player1,
    player2: player2,
    next: player1
  }).then(function(game) {
    var player1Board = models.Board.randomBoard({belongsTo: game.id}),
        player2Board = models.Board.randomBoard({belongsTo: game.id});

    game.setBoards([player1Board, player2Board]);

    return game;
  });
};
