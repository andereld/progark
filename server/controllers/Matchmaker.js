var OngoingGames = require("../models/OngoingGames");

var playerQueue = [];

exports.startGame = function(req, res) {

  var player1 = req.body.username;

  var game = OngoingGames.findGame(player1);

  // player has ongoing game
  if (game != null) {
    res.json(game);
  }

  // Start a new game if opponent exists. Pops the last element in queue.
  else if (playerQueue.length > 0) {
    OngoingGames.startGame(player1, playerQueue.pop());
    res.json({'message': 'Starting new game.'});
  }

  // If no players in queue, put this player in start of queue.
  else  {
    playerQueue.unshift(player1);
    res.json({'message': 'Waiting for opponent.'});
  }
};
