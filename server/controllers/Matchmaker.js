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
        res.json({'game': game});
      });
    }

    // If no players in queue, put this player in start of queue.
    else  {
      playerQueue.unshift(player1);
      res.json({'game': null});
    }
  });
};
