exports.fire = function (req, res) {

    var player = req.body.username;
    var x = req.body.x;
    var y = req.body.y;
    var game = findGame(player);

    if (game != null) {
        var shipWasHit = game.fire(player, x, y);
        res.json({'shipWasHit': shipWasHit});
    }

    else {
        res.json({'message': 'No game was found'});
    }

};