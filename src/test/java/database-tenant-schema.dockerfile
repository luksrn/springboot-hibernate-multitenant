FROM library/postgres

COPY docker/postgres-schema/init.sql /docker-entrypoint-initdb.d/ 
ENV POSTGRES_USER postgres
ENV POSTGRES_PASSWORD changeit
ENV POSTGRES_DB multitenancy_schema
