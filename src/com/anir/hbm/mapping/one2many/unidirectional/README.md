# Hibernate One-To-Many Mapping Unidirectional
In this example you will learn how to map one-to-one relationship with Uni-Directional using Hibernate. Most of the times, database tables are associated with each other. One of the forms of association – **One-To-Many** . 

Here we already have three tables
- instructor
- course
- instructorDetails

Here we the relationship Cardinality as follow:
relationship of 1:M cardinality:
> instructor  -- A-HasM --> course

other has a relationship of 1:1 cardinality:
> instructor  -- HasA --> instructorDetails

Now are are going to add a new Table Named **Review**, which a *has a One-To-Many relationship with "Course" Table*, Thus as per the perspective of Course Entity we are going to have both **One-To-Many** and **Many-To-One** relationship. as follows
>	 instructor  -- A-HasM --> course -- A-HasM --> Review

for better understanding we have provided the Visual ERD diagram for you, please see the image file in following folder:
>  -->/hibernate-adv-db-mapping/starter_db_files/ERD/erd-hb-one-to-many-uni

for DB schema import we have attached SQL file in following folder to import in MYSQL:

>  -->/hibernate-adv-db-mapping/starter_db_files/hibernate-mapping-database-scripts/hb-02-one-to-many/02-add-review-table-db.sql


in our sample project we have different module which will help you to understand these mapping and functionality.

*--> Conditional Logic: *
	For better understanding of Mapping and Cardinality we have set a Real world alike Rule, i.e.; 
	- **Where a Course can have One to Many relation with Review .**
	- **Cascading rule is: Deletion of any Course will Delete/update the Reviews but not Vice-versa**
	
Functionality is being defined under package --> *com.anir.hbm.mapping.one2many.unidirectional*
 
 Please Have a look for details. 

Each of the package contains it's own readMe file for better modular level of understanding and code is fully stacked with javadoc commenting for coder better understanding.

