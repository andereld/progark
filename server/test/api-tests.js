var app = require('../app');
var request = require('supertest');
var should = require('should');


describe('start a game after two players join', function () {

  it('should return \'Waiting for opponent.\' when the first player joins', function (done) {
    request(app)
      .post('/api/play/')
      .set('Content-Type', 'application/json')
      .send({username: 'test'})
      .expect(200)
      .end(function (err, res) {
        should.not.exist(err);
        res.body.message.should.equal('Waiting for opponent.');
        done();
      });
  });

  it('should keep waiting if the same user joins again', function (done) {
    request(app)
      .post('/api/play/')
      .set('Content-Type', 'application/json')
      .send({username: 'test'})
      .expect(200)
      .end(function (err, res) {
        should.not.exist(err);
        res.body.message.should.equal('Waiting for opponent.');
        done();
      })
  });

  it('should return \'Starting new game\' when a second user joins', function (done) {
     request(app)
      .post('/api/play/')
      .set('Content-Type', 'application/json')
      .send({username: 'test2'})
      .expect(200)
      .end(function (err, res) {
        should.not.exist(err);
        res.body.message.should.equal('Starting new game.');
        done();
      })
  });
});
