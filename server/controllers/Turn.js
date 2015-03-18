var GameController = require("./GameController");

exports.nextPlayer = function(req, res) {
  var player = req.params.username;

  var game = GameController.findGame(player);

  if(game) {
    res.json({username: game.next});
  }
  else {
    res.json({message: 'No game was found.'});
  }

};
