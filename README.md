# <p align="center"> API Rest Only Bank </p>

<p align="justify"> Bank account management API Rest </p>

## Requirements

* [Java 8](https://www.oracle.com/technetwork/pt/java/javase/downloads/jdk8-downloads-2133151.html)
* [Maven](https://maven.apache.org/download.cgi)
* [Lombok](https://projectlombok.org/download)
* [Docker](https://www.docker.com/products/docker-desktop)
* [Docker Compose](https://docs.docker.com/compose/install/)

### Note
For Linux distributions, the configuration of the Java development environment can be done through the [SDK man](https://sdkman.io/)
```sh
curl -s "https://get.sdkman.io" | bash
```
```sh
source $HOME/.sdkman/bin/sdkman-init.sh
```
```sh
sdk install java ${java.version}.hs-adpt
```

## Getting Started

## Configure Database

<p align="justify"> Log in to the docker hub, access the link https://hub.docker.com/_/oracle-database-enterprise-edition and give the necessary acceptances to download the Oracle Database image. 

Then, you will need to login to the docker using the command:
```sh
docker login
```

To correct problems regarding permissions to launch the docker container, enter the commands:

```sh
sudo groupadd docker
```
```sh
sudo usermod -aG docker $USER
```
```sh
newgrp docker
```

Then run the commands below: </p>

Start oracle database docker
```sh
docker-compose up -d
```

Be sure that container has been started [state should be: up (healthy)]
```sh
docker-compose ps
```

Execute script to create database schema
```sh
bash setup-db.sh
```

To follow the logs of the docker container initialization process, insert:
```sh
docker-compose logs -f
```
## Project dependencies

To install the project dependencies, run:

```sh
mvn dependency:purge-local-repository
```

To compile the project, run:
```sh
mvn clean compile
```
