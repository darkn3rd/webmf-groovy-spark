# Groovy-Gradle SparkJava micro-webframework example

When I first created this repository in 2016, the installation process for the [Java](https://wikipedia.org/wiki/Java_(software_platform)), [Groovy](https://groovy-lang.org/), and [Gradle](https://gradle.org/) toolchains were consistent across macOS, Windows, and Linux (Debian/Ubuntu). However, since then, multiple JDKs have emerged, and the availability of Gradle and Groovy has varied. Therefore, I have switched to [SDKMan](https://sdkman.io/) for managing Java JDKs and SDKs.

## Installing

### Install SDKMan

```bash
curl -s "https://get.sdkman.io" | bash
source "$HOME/.sdkman/bin/sdkman-init.sh"
```

### Install Java

Now this gets interesting as there are many different Java versions, each with its own trade-offs. I consider what other vendors, such as JetBrains TeamCity, use. They bundle [Amazon Corretto](https://aws.amazon.com/corretto/) for Windows and Docker images. For Apple ARM systems running on Apple Silicon, [Azul OpenJDK (Zulu)](https://www.azul.com/downloads/#zulu)  builds are popular and recommended. A team I worked with recently recommends [Eclipse Temurin](https://projects.eclipse.org/projects/adoptium.temurin).

You can see a full list of JDKs supported by SDKman at https://sdkman.io/jdks.

As an example, for Macbook M1, you can install JDK11 with:

```bash
sdk install java 21.0.3-zulu
```

### Install Groovy and Gradle

```bash
# small helper function to fetch latest version
latest_version() {
  sdk list $1 \
   | grep -vE '.*-.*' \
   | grep -oE '\b[0-9]+\.[0-9]+\.[0-9]+\b' \
   | sort \
   | tail -1
}

GRADLE_VERSION=$(latest_version gradle)
GROOVY_VERSION=$(latest_version groovy)

# install 
sdk install gradle $GRADLE_VERSION
sdk install groovy $GROOVY_VERSION
```

## Running

You can build with the auto-generated [Gradle](https://gradle.org/) wrapper using `./gradlew run &` or run the build commands separately: 

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
