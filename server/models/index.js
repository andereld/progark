// This file is based on the following example:
//    https://github.com/sequelize/express-example/blob/master/models/index.js

'use strict';

var fs        = require('fs');
var path      = require('path');
var Sequelize = require('sequelize');
var sequelize = new Sequelize(process.env.DATABASE_URL);
var db        = {};

fs
  .readdirSync(__dirname)
  .filter(function(file) {
    return file === 'Board.js';
// TODO: Uncomment this line when all models are Sequelize models.
//    return (file.indexOf('.') !== 0) && (file !== 'index.js');
  })
  .forEach(function(file) {
    var model = sequelize['import'](path.join(__dirname, file));
    db[model.name] = model;
  });

Object.keys(db).forEach(function(modelName) {
  if ('associate' in db[modelName]) {
    db[modelName].associate(db);
  }
});

sequelize.sync();

db.sequelize = sequelize;
db.Sequelize = Sequelize;

module.exports = db;
