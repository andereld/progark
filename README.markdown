# TDT4240 Project – Sea Battle
This is an implementation of the classic game Battleship (Sea Battle), written
with a client–server architecture, for the Android platform.

## Client

### Setup
You will need Intellij IDEA 14, latest Android SDK (install build tools 20.0.0 as well) with an ANDROID_HOME environment variable.
Go to `Import project` in Intellij and select the `build.gradle` file from the client folder.

More detailed instructions can be found [here](https://github.com/libgdx/libgdx/wiki/Gradle-and-Intellij-IDEA).

## Notes on Heroku
The application server is written in [Node.js][node] and deployed to
[Heroku][heroku].

### Setup
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
