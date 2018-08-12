# Hibernate One-To-One Mapping
In this example you will learn how to map one-to-one relationship using Hibernate. Most of the times, database tables are associated with each other. One of the forms of association – **One-To-One**. 
A one-to-one is type of association where column will be set as unique. For example, an details object can be associated with a single person object.

Here we have two tables
- instructor
- instructorDetails

which has a relationship of 1:1 cardinality:
> instructor  -- HasA --> instructorDetails

for better understanding we have provided the Visual ERD diagram for you, please see the image file in following folder:
>  -->/hibernate-adv-db-mapping/starter_db_files/ERD/erd-hb-one-to-one

for DB schema import we have attached SQL file in following folder to import in MYSQL:

>  -->/hibernate-adv-db-mapping/starter_db_files/hibernate-mapping-database-scripts/hb-01-one-to-one-uni


These can be further divided into 2 types of mapping 
1. unidirectional mappings
2. bidirectional mappings

in our sample project we have different module which will help you to understand thease mapping and functionality.

*--> First View Unidirectional mappings, then walk through with bidirectional Mappings for better understanding.*

Each of the package contains it's own readMe file for better modular level of understanding and code is fully stacked with javadoc commenting for coder better understanding.
