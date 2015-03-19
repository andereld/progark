var GameController = require("./GameController");

exports.fire = function (req, res) {

  var player = req.body.username;
  var x = req.body.x;
  var y = req.body.y;

  GameController.findGame(player).then(function(game) {
    if (game != null) {
      // TODO: Fix this. Game cannot be over (see findGame).
      // If game is already over, it means the other player won.
      if (game.gameOver) {
        res.json({'message': "You lost"});
        return;
      }

      // Fire at the enemy board. If game over after hit, you won.
      var shipWasHit = game.fire(player, x, y);
      var msg = game.gameOver ? "You won" : "Ongoing game";
      res.json({'message': msg, 'shipWasHit': shipWasHit});
    }

    else {
      res.json({'message': 'No game was found'});
    }
  });
};
