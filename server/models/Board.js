var Board = function(boardString) {

    this.board = [];

    for (var c in boardString) {
        this.board.push(new Cell(c == 'X'));
    }
};

Board.prototype.fire = function(x, y) {
    this.board[y * 10 + x].hit = true;
    return this.board[y * 10 + x].isShip;
};

Board.prototype.isGameOver = function() {
    return this.board.filter(function(cell) {
        return c.isShip && !c.isHit;
    }).length < 1;

};