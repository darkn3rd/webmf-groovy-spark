## Installing

### Mac OS X

```bash
brew install groovy
export GROOVY_HOME=/usr/local/opt/groovy/libexec
brew install gradle
```

Note: Originally created boiler plate with `gradle init --type groovy-library`


## Linux: Ubuntu 16.04

On Ubuntu 16.04, you should have OpenJDK 8 or Oracle JDK 8, for example:

```bash
sudo apt install -y openjdk-8-jdk
```

Then install [Gradle](https://gradle.org/) 2.1 or greater:


```bash
sudo apt get install -y unzip zip
curl -s "https://get.sdkman.io" | bash
source "$HOME/.sdkman/bin/sdkman-init.sh"
sdk install gradle 4.10.2
```

Note that gradle seems to have problems with OpenJDK 9, and the package `openjdk-9-jdk` from Ubuntu repository, does not even install.


## Running

Build with embedded [Gradle](https://gradle.org/) 2.1:

```bash
./gradlew run &
```

or alternatively with more recent [Gradle](https://gradle.org/):

```bash
gradle build
gradle run &
```

## Testing

```bash
curl http://127.0.0.1:4567/
curl http://127.0.0.1:4567/hello
curl http://127.0.0.1:4567/hello/James
```

## Vagrant

For convenience, you can use [Vagrant](https://www.vagrantup.com/) to bring up virtual guest and install java + build tools. Afterwards, you can test it manually.

```bash
# startup and login to virtual guest
vagrant up && vagrant ssh

# inside vagrant environment
cd /vagrant
gradle build
gradle run &
curl http://127.0.0.1:4567/
curl http://127.0.0.1:4567/hello
curl http://127.0.0.1:4567/hello/James
```
