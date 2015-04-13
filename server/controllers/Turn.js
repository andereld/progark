var GameController = require("./GameController");

exports.nextPlayer = function(req, res) {
  var player = req.params.username;

  GameController.findGame(player).then(function(game) {
    if (game != null) {
      res.json({username: game.next, lastMove: game.lastMove});
    } else {
      res.json({message: 'No game was found.'});
    }
  });
};
