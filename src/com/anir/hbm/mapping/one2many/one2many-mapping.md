# Hibernate One-To-Many Mapping
In this example you will learn how to map one-to-one relationship using Hibernate. Most of the times, database tables are associated with each other. One of the forms of association – **One-To-Many** or **Many-To-One**. 
A one-to-one is type of association where column will be set as unique. For example, an details object can be associated with a single person object.

Here we have three tables
- instructor
- course
- instructorDetails

Here we the relationship Cardinality as follow:
relationship of 1:M cardinality:
> instructor  -- A-HasM --> course

other has a relationship of 1:1 cardinality:
> instructor  -- HasA --> instructorDetails

for DB schema import we have attached SQL file in following folder to import in MYSQL:

>  -->/hibernate-adv-db-mapping/starter_db_files/hibernate-mapping-database-scripts/hb-02-one-to-many


in our sample project we have different module which will help you to understand these mapping and functionality.

*--> Conditional Logic: *
	For better understanding of Mapping and Cardinality we have set a Real world alike Rule, i.e.; 
	- **Where a Instructor can have Many to one relation with  Course.**
	- **Cascading rule is: Deletion of any Instructor A Course should not get Deleted**
	
#Add Ons

Here we have a Bonus module where we discussed detail functionality about **Hibernate Data Fetching Technology**; i.e.; Hibernate data loading technics as;
 
 EAGER Loading & LAZY Loading.
 these 2 functionality is being defined under package --> *om.anir.hbm.mapping.one2many.dbFetchType*
 
 Please Have a look for details. 

Each of the package contains it's own readMe file for better modular level of understanding and code is fully stacked with javadoc commenting for coder better understanding.
