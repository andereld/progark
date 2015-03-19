// Cell objects are not stored in the database. A board's cells are serialized
// to a string of the form:
//
//    '...S.h....HS'
//
//  where
//
//    '.' represents a cell of clear ocean which has not been hit,
//    'S' represents a cell occupied by a ship which has not been hit,
//    'h' represents a cell of clear ocean which has been hit, and
//    'H' represents a cell occupied by a ship which has been hit.
function Cell(containsShip, hasBeenHit) {
    this.containsShip = containsShip;
    this.hasBeenHit = hasBeenHit;
}

module.exports = function(sequelize, DataTypes) {
  var Board = sequelize.define('Board', {
    cellString: DataTypes.STRING
  }, {
    classMethods: {

      associate: function(models) {
        Board.belongsTo(models.Game);
      },

      randomBoard: function() {
        var levels = ["...XX........XX..XXX...XX..........................X.........X...XXX...X.........X..XX..............",
          ".XXX.......XXX....X.........X.........X....XX...X.............X..XXX....X.........X.................",
          "................................XX.........XXX........XXXX.....XXX.....XXX.......XXX................"];
        return Board.create({
          cellString: levels[Math.floor(Math.random() * levels.length)]
        });
      }
    },

    getterMethods: {
      cells: function() {
        // Converts the board's cell string to an array of Cell objects and
        // returns this array.
        return this.cellString.split('').map(function(c) {
          switch (c) {
            case 'S': return new Cell(true, false);
            case 'H': return new Cell(true, true);
            case 'h': return new Cell(false, true);
            default:  return new Cell(false, false);
          }
        });
      },

      gameOver: function() {
        return this.cellString.indexOf('S') === -1;
      }
    },

    setterMethods: {
      cells: function(cells) {
        // Converts an array of Cell objects to a cell string and sets
        // the instance variable 'cellString' accordingly.
        var str = cells.reduce(function(accumulator, current) {
          if (current.containsShip && !current.hasBeenHit) {
            return accumulator + 'S';
          } else if (current.containsShip && current.hasBeenHit)  {
            return accumulator + 'H';
          } else if (!current.containsShip && current.hasBeenHit) {
            return accumulator + 'h';
          } else {
            return accumulator + '.';
          }
        }, '');

        this.setDataValue('cellString', str);
      }
    },

    instanceMethods: {
      fire: function(x, y) {
        // We need to store the list of cells in a temporary variable
        // and set 'this.cells' to the modified version in order to
        // trigger the custom setter.
        var cells = this.cells;
        cells[y * 10 + x].hasBeenHit = true;
        this.cells = cells;

        this.save();

        return this.cells[y * 10 + x].containsShip;
      }
    }
  });

  return Board;
};
