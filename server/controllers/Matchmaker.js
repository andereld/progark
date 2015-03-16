var OngoingGames = require("../models/OngoingGames");

var playerQueue = [];

exports.startGame = function(req, res) {

  var player1 = req.body.username;

  var game = OngoingGames.findGame(player1);

  // player has ongoing game
  if (game != null) {
    res.json('message': 'Ongoing game', 'game': game);
  }

  // Start a new game if opponent exists and opponent is not the same player. Pops the last element in queue.
  else if (playerQueue.length > 0 && playerQueue.indexOf(player1) === -1) {
    OngoingGames.startGame(player1, playerQueue.pop());
    game = OngoingGames.findGame(player1);
    res.json({'message': 'Starting new game.', 'game': game});
  }

  // If no players in queue, put this player in start of queue.
  else  {
    playerQueue.unshift(player1);
    res.json({'message': 'Waiting for opponent.'});
  }
};
