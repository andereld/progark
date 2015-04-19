function coordinatesToString(x, y) {
  return x + ',' + y;
}

module.exports = function(sequelize, DataTypes) {
  var Game = sequelize.define('Game', {
    player1: {type: DataTypes.STRING},
    player2: {type: DataTypes.STRING},
    next: {type: DataTypes.STRING},
    lastMove: {type: DataTypes.STRING, allowNull: false, defaultValue: '-1,-1'},
    finished: {type: DataTypes.BOOLEAN, allowNull: false, defaultValue: false}
  }, {

    classMethods: {
      associate: function (models) {
        Game.hasMany(models.Board);
      }
    },

    instanceMethods: {
      fire: function (player, x, y) {
        function byId(game1, game2) {
          return game1.id > game2.id;
        }

        if (player === this.player1) {
          this.next = this.player2;
          this.lastMove = coordinatesToString(x, y);
          this.save();

          return this.Boards.sort(byId)[1].fire(x, y);
        } else if (player === this.player2) {
          this.next = this.player1;
          this.lastMove = coordinatesToString(x, y);
          this.save();

          return this.Boards.sort(byId)[0].fire(x, y);
        }
      },
    },

    getterMethods: {
      gameOver: function() {
        return this.Boards[0].gameOver || this.Boards[1].gameOver;
      }
    }
  });

  return Game;
};
