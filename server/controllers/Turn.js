var OngoingGames = require("../models/OngoingGames");

exports.nextPlayer = function(req, res) {
  var player = req.params.username;

  var game = OngoingGames.findGame(player);

  if(game) {
    res.json({username: game.next});
  }
  else {
    res.json({message: 'No game was found.'});
  }

};
