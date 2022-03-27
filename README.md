# springboot-framework
Example Java Spring Boot Project
Pre-requisites

Apache maven 3.x
Java 1.8
Postgresql
Building and Testing Artifact

 git clone git@github.com:GEM-M-Ms/java-gem-matching-service.git
 cd java-gem-matching-service
 mvn clean install
Setup local docker

docker create -v /var/lib/postgresql/data --name PostgresData alpine
docker run -p 5432:5432 --name postgres -e POSTGRES_PASSWORD=admin -d --volumes-from PostgresData postgres
Running artifact

To build an image with the resulting artifact from above

 run from Intellij 
 or java -cp target/gem-matching-service-1.0.0.0.jar
Add Mentee

//temporary only localhost:8080/add default endpoint]

Open in your browser localhost:8080/add
Verify Reponse with id of added object:
To view database

setup postgress UI (TablePlus, for example) and connect to default database "postgres" - see application.properties for user/password

To generate the swagger definition

Not supported yet, TBD