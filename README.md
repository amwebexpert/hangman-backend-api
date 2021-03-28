# Hangman backend API

Hangman application backend REST API.

## Commands

### Start locally

Starts the server locally and listen on port `8080`. 

    ./gradlew bootRun

Then open the browser at http://localhost:8080 which will display the welcome page (`index.html`).

### Deploy on GCP

The following script builds the war and deploy it to GAE by calling `./gradlew appengineDeploy`:

    ./deploy-to-Google-Cloud-Platform.sh

Override the project name under `build.gradle` (see `appengine.deploy.projectId`). For instance if the projectId is `esighclouddemo` then the following links
will allow you to see final results:

* https://esighclouddemo.appspot.com/
* https://console.cloud.google.com/logs/query?project=esighclouddemo

### Setup Security for authenticating user against GitHub

You must create your Github clientId and clientSecret. Any users may use the link to authenticate (available inside server welcome page `index.html`).
Next you have to define these environment variables before deploying or before starting locally:

    export ENV_VAR_WEB_APP_BASE_URL=<here your home page URL used by OAuth2 protocol>
    export ENV_VAR_CLIENT_ID_GITHUB=<here your client id>
    export ENV_VAR_CLIENT_SECRET_GITHUB=<here your client secret>

# Gradle tasks

Gradle will automatically fetch all required dependencies for you.

Long story short:

    mvn update        ~= ./gradlew build --refresh-dependencies
    mvn clean install ~= ./gradlew clean build

To force Gradle to re-download dependencies you can execute:

    ./gradlew build --refresh-dependencies

To assemble you project without executing tests:

    ./gradlew assemble

To completely build you project with test execution:

    ./gradlew build

You can skip certain tasks by providing `-x` argument:

    ./gradlew build -x test


## Steps to support GAE

1. Make app boot with servlet (war, not jar) by the mean of `SpringBootServletInitializer` (see `HangmanApplication`)
2. add `app.yaml` to the project
3. add `buildScript` section to `build.gradle`:
```groovy
    buildscript {
        repositories {
            mavenCentral()
        }
    
        dependencies {
            classpath 'com.google.cloud.tools:appengine-gradle-plugin:2.2.0'
        }
    }
```
4. Add `appengineDeploy` section to `build.gradle`:
```groovy
    apply plugin: 'com.google.cloud.tools.appengine'
    appengine {  // App Engine tasks configuration
        deploy {   // deploy configuration
            projectId = 'esighclouddemo' // gcloud config set project
            version = 'GCLOUD_CONFIG'   // gcloud to generate a version
            stopPreviousVersion = true
            promote = true
        }
    }
```
5. Run `./gradlew appengineDeploy` (see `./deploy-to-Google-Cloud-Platform.sh`)

# Tutorial for GAE SpringBoot deploy

* https://wkrzywiec.medium.com/how-to-publish-a-spring-boot-app-with-a-database-on-the-google-cloud-platform-614b88613ce3
* https://www.baeldung.com/spring-boot-google-app-engine

## Commands that helped fixing issues

    sudo apt install python3-distutils -y
    gcloud app update --split-health-checks --project esighclouddemo

# Resources for OAuth2 and JWT

* https://spring.io/guides/tutorials/spring-boot-oauth2/
  To be continued on this section: Login with GitHub
    In this section, you’ll modify the logout app you built already, adding a sticker page so that the end-user can choose between multiple sets of credentials 
* https://jwt.io/
* https://www.youtube.com/watch?v=X80nJ5T7YpE
* https://github.com/koushikkothagal/spring-security-jwt

# Resources on privacy policy + terms and conditions

* https://github.com/nisrulz/app-privacy-policy-generator
* https://app-privacy-policy-generator.nisrulz.com/

# Getting Started

### Reference Documentation
For further reference, please consider the following sections:

* [Official Gradle documentation](https://docs.gradle.org)
* [Spring Boot Gradle Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/2.4.3/gradle-plugin/reference/html/)
* [Create an OCI image](https://docs.spring.io/spring-boot/docs/2.4.3/gradle-plugin/reference/html/#build-image)
* [Spring Web](https://docs.spring.io/spring-boot/docs/2.4.3/reference/htmlsingle/#boot-features-developing-web-applications)
* [Spring Boot DevTools](https://docs.spring.io/spring-boot/docs/2.4.3/reference/htmlsingle/#using-boot-devtools)
* [Spring Data JPA](https://docs.spring.io/spring-boot/docs/2.4.3/reference/htmlsingle/#boot-features-jpa-and-spring-data)
* [Flyway Migration](https://docs.spring.io/spring-boot/docs/2.4.3/reference/htmlsingle/#howto-execute-flyway-database-migrations-on-startup)
* [Spring HATEOAS](https://docs.spring.io/spring-boot/docs/2.4.3/reference/htmlsingle/#boot-features-spring-hateoas)
* [Spring cache abstraction](https://docs.spring.io/spring-boot/docs/2.4.3/reference/htmlsingle/#boot-features-caching)

### Guides
The following guides illustrate how to use some features concretely:

* [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
* [Serving Web Content with Spring MVC](https://spring.io/guides/gs/serving-web-content/)
* [Building REST services with Spring](https://spring.io/guides/tutorials/bookmarks/)
* [Accessing Data with JPA](https://spring.io/guides/gs/accessing-data-jpa/)
* [Building a Hypermedia-Driven RESTful Web Service](https://spring.io/guides/gs/rest-hateoas/)
* [Caching Data with Spring](https://spring.io/guides/gs/caching/)

### Additional Links
These additional references should also help you:

* [Gradle Build Scans – insights for your project's build](https://scans.gradle.com#gradle)

