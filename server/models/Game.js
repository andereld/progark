module.exports = function(sequelize, DataTypes) {
  var Game = sequelize.define('Game', {
    player1: {type: DataTypes.STRING},
    player2: {type: DataTypes.STRING},
    next: {type: DataTypes.STRING},
    finished: {type: DataTypes.BOOLEAN, allowNull: false, defaultValue: false}
  }, {

    classMethods: {
      associate: function (models) {
        Game.hasMany(models.Board);
      }
    },

    instanceMethods: {
      fire: function (player, x, y) {
        if (player === this.player1) {
          this.next = this.player2;
          this.save();

          return this.Boards[1].fire(x, y);
        } else if (player === this.player2) {
          this.next = this.player1;
          this.save();

          return this.Boards[0].fire(x, y);
        }
      }
    }

  });

  return Game;
};
