# WIP - Estudos Docker no Alura

./gradlew bootRepackage

docker network create --driver bridge desenv-local

docker build -f database-tenant-schema.dockerfile -t database-schema .
docker build -f app.dockerfile -t luksrn/multitenant-springboot --build-arg JAR_FILE="build/libs/multitenant.jar" .

docker container run --name database-postgres --network desenv-local -d database-schema
docker container run -e "SPRING_PROFILES_ACTIVE=tenancy-schema" -p 8080:8080 --network desenv-local --name app luksrn/multitenant-springboot
