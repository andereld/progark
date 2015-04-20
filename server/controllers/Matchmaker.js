var models = require('../models');
var GameController = require("./GameController");

var playerQueue = [];

exports.startGame = function(req, res) {

  var player1 = req.body.username;

  var game = GameController.findGame(player1).then(function(game) {
    // player has ongoing game
    if (game != null) {
      res.json({'game': game});
    }

    // Start a new game if opponent exists and opponent is not the same player. Pops the last element in queue.
    else if (playerQueue.length > 0 && playerQueue.indexOf(player1) === -1) {
      GameController.startGame(player1, playerQueue.pop()).then(function(game) {
        models.Game.find({
          include: [models.Board],
          where: {id: game.id}
        }).then(function(game) {
          res.json({'game': game});
        });
      });
    }

    // Put the player in the queue if the player isn't already there
    else if(playerQueue.indexOf(player1) === -1)  {
      playerQueue.unshift(player1);
      res.json({'game': null});
    }
  })
};

exports.cancelWaitForGame = function(req, res) {

  var player = req.body.username;
  var index = array.indexOf(player);

  if (index > -1) {
    playerQueue.splice(index, 1);
  }
};
