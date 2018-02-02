FROM library/postgres

ARG SCRIPT_INITDB

COPY ${SCRIPT_INITDB} /docker-entrypoint-initdb.d/ 

ENV POSTGRES_USER postgres
ENV POSTGRES_PASSWORD changeit
ENV POSTGRES_DB multitenancy_schema
