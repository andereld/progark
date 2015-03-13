var bodyParser = require('body-parser');
var express = require('express');
var app = express();
var router = express.Router();

var gameController = require('./controllers/Matchmaker');
var playController = require('./controllers/Play');

app.set('port', (process.env.PORT || 5000));
app.use(express.static(__dirname + '/public'));
app.use(bodyParser.json());

router.route('/play')
  .post(gameController.startGame);

router.route('/fire')
  .post(playController.fire);

app.use('/api', router);

app.listen(app.get('port'), function() {
  console.log("Node app is running at localhost:" + app.get('port'));
});
