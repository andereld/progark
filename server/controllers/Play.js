exports.fire = function (req, res) {

  var player = req.body.username;
  var x = req.body.x;
  var y = req.body.y;
  var game = findGame(player);

  if (game != null) {

    // If game is already over, it means the other player won.
    if (game.isGameOver()) {
      res.json({'message': "You lost"});
      return;
    }

    // Fire at the enemy board. If game over after hit, you won.
    var shipWasHit = game.fire(player, x, y);
    var msg = game.isGameOver() ? "You won" : "Ongoing game";
    res.json({'message': msg, 'shipWasHit': shipWasHit});
  }

  else {
    res.json({'message': 'No game was found'});
  }
};
