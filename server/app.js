var bodyParser = require('body-parser');
var express = require('express');
var app = express();
var router = express.Router();
var Sequelize = require('sequelize');
var sequelize = new Sequelize(process.env.DATABASE_URL);

var matchmaker = require('./controllers/Matchmaker');
var playController = require('./controllers/Play');
var turnController = require('./controllers/Turn');

app.set('port', (process.env.PORT || 5000));
app.use(express.static(__dirname + '/public'));
app.use(bodyParser.json());

router.route('/play')
  .post(matchmaker.startGame);

router.route('/fire')
  .post(playController.fire);

router.route('/cancel')
  .post(matchmaker.cancelWaitForGame);

router.route('/turn/:username')
  .get(turnController.nextPlayer);

app.use('/api', router);

app.listen(app.get('port'), function() {
  console.log("Node app is running at localhost:" + app.get('port'));
});
