# TDT4240 Project – Sea Battle

This is an implementation of the classic game Battleship (Sea Battle), written
with a client–server architecture, for the Android platform.

## Heroku

The application server is written in [Node.js][node] and deployed to
[Heroku][heroku]. Heroku expects applications to placed in the root directory
of the Git repository. Because we have two separate applications in our
repository, located in the client/ and server/ directories respectively, we
need to use `git subtree` to push just the server application to Heroku. When
you want to deploy to Heroku, simply run:

```
git subtree push --prefix server heroku master
```

[node]: http://nodejs.org/
[heroku]: https://www.heroku.com/
