# Lampaderum

[![Build Status](https://travis-ci.com/A-Julien/lampadaire-project.svg?branch=master)](https://travis-ci.com/A-Julien/lampadaire-project)
[![codecov](https://codecov.io/gh/A-Julien/lampadaire-project/branch/master/graph/badge.svg?token=XBa0XJZpdA)](https://codecov.io/gh/A-Julien/lampadaire-project)
[![codeinsp](https://www.code-inspector.com/project/13966/score/svg)](https://frontend.code-inspector.com/public/project/13966/lampadaire-project/dashboard)
[![codeinsp](https://www.code-inspector.com/project/13966/status/svg)](https://frontend.code-inspector.com/public/project/13966/lampadaire-project/dashboard)
[![CodeFactor](https://www.codefactor.io/repository/github/a-julien/lampadaire-project/badge)](https://www.codefactor.io/repository/github/a-julien/lampadaire-project)

This application was generated using JHipster 6.10.3, you can find documentation and help at [https://www.jhipster.tech/documentation-archive/v6.10.3](https://www.jhipster.tech/documentation-archive/v6.10.3).

## Project architecture:

- [Github](https://github.com/A-Julien/lampadaire-project)
- [CI (Travis)](https://travis-ci.com/A-Julien/lampadaire-project)
- [JavaDoc](https://a-julien.github.io/lampadaire-project/apidocs/index.html)

## Development Policy

- All function names, variables, documentation etc... must be imperatively in **anglais** and written in **CamelCase**.
- Api REST convention, refer to the official website : https://restfulapi.net/resource-naming .
- For any information on the tasks or requests, please refer to the official Trello : https://trello.com/b/aXuA8Jp8/agile .
- It is imperative to follow the git flow.
- All remote communications must go through discord in the right channels in order to keep track.

## Development

The development is done under docker.

All docker config file are in `src/main/docker`.

Dev docker config file :

- `app-dev.yml`
- `lampaderum-dev-node.dockerfile`
- `lampaderum-dev-springboot.dockerfile`
- `postgresql.yml, jhipster-registry.yml` same as prod.

`lampaderum-dev-node.dockerfile` and `lampaderum-dev-springboot.dockerfile` are two docker images build
for dev. This two images use your local project files to compile and execute programs. This means that you can develop
on your favorite IDE.

### Running dev build

In order to start, you need to create a volume to store DB data : `docker volumes create postgesql`.

- With intelliJ, simply go to the `app-dev.yml` and clic on the double arrow in front of `services`.

- Using CLI : `docker compose app-dev.yml up`

Docker for Angular are not ready yet, so, just do `npm start`.

### Recompile back (springboot app)

- With intelliJ
  - go to the 'Services' tab, clic right on the `lampaderum-app` container, stop and start it.
  - simply go to the `app-dev.yml` and clic on the loop arrow in front of `lampaderum-app`.

### Use ng command

`docker-compose exec lampaderum-node ng generate component xyz`

### Using Angular CLI

You can also use [Angular CLI][] to generate some custom client code.

For example, the following command:

```
ng generate component my-component
```

will generate few files:

```
create src/main/webapp/app/my-component/my-component.component.html
create src/main/webapp/app/my-component/my-component.component.ts
update src/main/webapp/app/app.module.ts
```

## Testing

To launch your application's tests, run:

```
./mvnw verify
```

### Client tests

Unit tests are run by [Jest][] and written with [Jasmine][]. They're located in [src/test/javascript/](src/test/javascript/) and can be run with:

```
npm test
```

For more information, refer to the [Running tests page][].

### Code quality

Sonar is used to analyse code quality. You can start a local Sonar server (accessible on http://localhost:9001) with:

```
docker-compose -f src/main/docker/sonar.yml up -d
```

You can run a Sonar analysis with using the [sonar-scanner](https://docs.sonarqube.org/display/SCAN/Analyzing+with+SonarQube+Scanner) or by using the maven plugin.

Then, run a Sonar analysis:

```
./mvnw -Pprod clean verify sonar:sonar
```

If you need to re-run the Sonar phase, please be sure to specify at least the `initialize` phase since Sonar properties are loaded from the sonar-project.properties file.

```
./mvnw initialize sonar:sonar
```

For more information, refer to the [Code quality page][].

## Continuous Integration (optional)

## Contacts

### Responsables projet ECOM

noel.depalma@imag.fr

sybile.caffiau@univ-grenoble-alpes.fr

sebastien.chassande@soprasteria.com

### Equipe :

#### Scrum-Master (Grand Dayallama) :

gary.prat@etu.univ-grenoble-alpes.fr (Gary Prat)

#### Chef d'équipe - DEVOPS (Skippy):

julien.alaimo@gmail.com (Julien Alaimo)

#### Chargé de Com

vincent.arnone@hotmail.fr (Vincent Arnone)

#### Responsable IHM :

kergan.kergan@gmail.com (Antoine Rotival)

#### Pélerins :

feydel.hugo@gmail.com (Hugo Feydel)
