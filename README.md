# TDT4240 Project – Sea Battle
This is an implementation of the classic game Battleship (Sea Battle), written
with a client–server architecture (plus some MVC), for the Android platform. It's written as a project at NTNU in Trondheim, Norway, for the course TDT4240 Software Architecture.

## Workflow
Our issue tracker is [Trello](https://trello.com/b/ih5ztWXn/progark). Gonna start on something new?

1. Go to Trello and pick your task. If it's not there, you're gonna have to add it. Assign it to yourself, and move the card to "Doing"
2. Start a new branch. Name should be "client/" or "server/" +  something describing what you're working on.
3. Do your work. Use commits, write commit messages.
4. Submit pull request for feature. Move card to review  on Trello.
5. When the feature is reviewed by someone else than yourself, and it's merged into master, you can move the Trello-card to done.

Any questions? Ask!

## Client

### Setup
You will need Intellij IDEA 14, latest Android SDK (install build tools 20.0.0
as well) with an ANDROID\_HOME environment variable. Go to `Import project` in
Intellij and select the `build.gradle` file from the client folder.

More detailed instructions can be found
[here](https://github.com/libgdx/libgdx/wiki/Gradle-and-Intellij-IDEA).

### Libraries 
* [libgdx](https://github.com/libgdx/libgdx) for graphics and networking features.

## Server
The application server is written in [Node.js][node] and deployed to
[Heroku][heroku].

To run a local version of the server on localhost:5000, run `npm install` and `node app.js`.

### API

_**Warning**: this is not a complete spec of the API!_

#### Start/retrieve game

To retrieve a started game or start a game. 

```
POST /api/play/
```

Req: `{username: STRING}`

Res: `Board object OR {message: "Waiting for opponent."}`

#### Turn

To get the next player turn in the game.

```
GET /api/turn/:username
```

Res: `{username: STRING}`

#### Fire 

To fire on the board of the opponent.

```
POST /api/fire/
```

Req: `{username: STRING, x: INTEGER, y: INTEGER}`

Res: `{shipWasHit: BOOLEAN, message: "No game was found" OR "Ongoing game" OR "You lost" OR "You won"}`

### Models
#### Game
The Game module keeps track of the two players and their boards.
##### Fields
* `player1`: String. Username of the first player.
* `player2`: String. Username of the second player.
* `board1`: Board. The board which player1 shoots at.
* `board2`: Board. The board which player2 shoots at.

#### Board
The Board module contains a 1D array of 100 Cell objects (10x10).
##### Fields
* `board`: Array. 1D Array of the 100 (10x10) Cell objects for this board.

#### Cell 
##### Fields
* `isShip`: Boolean. True if this Cell represents a part of a ship.
* `isHit`: Boolean. True if this Cell has been fired at. Initially false.


### Heroku setup
You need the [Heroku toolbelt][heroku-toolbelt] in order to work with Heroku.
Follow the instructions on the website. On OS X, you can also install the tools
using package managers if you prefer:

```
brew install heroku-toolbelt
gem install foreman
```

Once you've cloned this repository, you need to add the Heroku remote before
you can deploy. Given that you have the Heroku toolbelt installed, have logged
in to Heroku (`heroku login`) and that your name is on the list of
collaborators for this project, simply run:

```
heroku git:remote -a fathomless-waters-2425
```

### Database setup
A Heroku Postgres database has already been added to the Heroku application;
run `heroku pg:info` from the application directory to see information about
the running instance. In order to run a local instance for development, you
must install PostgreSQL locally and then run the following:

```
# Create a Postgres user named 'progark' with no password:
% createuser progark
# Create a databased named 'progark_db' owned by user 'progark':
% createdb -O progark progark_db
```

And add the following line to your `.bashrc` or similar:

```
export DATABASE_URL=postgres://progark:@localhost/progark_db
```

You connect to a database on Heroku by using a connection URL specified (by
Heroku) in the environment variable `DATABASE_URL`. By setting an appropriate
`DATABASE_URL` locally, we avoid the need differentiate between the development
and production database in our code.

*Note*: The database connection is established by the [Sequelize][sequelize]
ORM like so:

```
var sequelize = new Sequelize(process.env.DATABASE_URL);
```

### Deployment
Heroku expects applications to placed in the root directory of the Git
repository. Because we have two separate applications in our repository,
located in the client/ and server/ directories respectively, we need to use
`git subtree` to push just the server application to Heroku. When you want to
deploy to Heroku, simply run:

```
git subtree push --prefix server heroku master
```

[node]: http://nodejs.org/
[heroku]: https://www.heroku.com/
[heroku-toolbelt]: https://toolbelt.heroku.com/
[sequelize]: http://docs.sequelizejs.com/en/latest/
