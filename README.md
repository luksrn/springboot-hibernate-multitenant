# WIP - Infra Testes Tenant Databases

```
host$ sudo docker-compose -f tenant-database-sample-docker-compose.yml build

host$ sudo docker-compose -f tenant-database-sample-docker-compose.yml up

host$ sudo docker-compose -f tenant-database-sample-docker-compose.yml ps

           Name                         Command              State           Ports         
-------------------------------------------------------------------------------------------
multitenant_a_database_1     docker-entrypoint.sh postgres   Up      0.0.0.0:5434->5432/tcp
multitenant_any_database_1   docker-entrypoint.sh postgres   Up      0.0.0.0:5433->5432/tcp
multitenant_b_database_1     docker-entrypoint.sh postgres   Up      0.0.0.0:5435->5432/tcp
```
