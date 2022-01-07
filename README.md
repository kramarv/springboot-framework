# Example Java Spring Boot Project

## Pre-requisites

1. Apache maven 3.x
2. Java 1.8
3.  Postgresql

## Building and Testing Artifact

```bash
 cd source
 mvn clean install
```

## Setup local docker
```bash
docker create -v /var/lib/postgresql/data --name PostgresData alpine
docker run -p 5432:5432 --name postgres -e POSTGRES_PASSWORD=admin -d --volumes-from PostgresData postgres
```

## Running artifact that was built
### To build an image with the resulting artifact from above
```bash
 run from Intellij 
 or java -cp target/gem-matching-service-1.0.0.0.jar
```

### Add Mentee
//temporary only localhost:8080/add default endpoint]
1. Open in your browser localhost:8080/add
2. Verify Reponse with id of added object:
```MenteeID [uuid=43c9081a-7c81-43eb-a90a-0579ebd14476]
```


### To view database
setup postgress UI (TablePlus, for example) and connect to default database "postgres" - see application.properties for user/password

### To generate the swagger definition

Not supported yet, TBD
