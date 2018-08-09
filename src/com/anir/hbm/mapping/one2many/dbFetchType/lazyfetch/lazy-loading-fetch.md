# Hibernate Lazy Loading

**Lazy Loading** is a design pattern which is used to defer initialization of an object as long as it is possible. Thus initial load time much smaller than in the other approach and Less memory consumption.

**But There is a big disadvantage**; *In some cases you need to handle lazily-initialized objects with a special care or you might end up with an exception due to delay initialization.*

As per our previous example, we know when lazy loading is enabled, **Course** Detail data won't be initialized and loaded into a memory until an explicit call is made to it.
```java
// Commit the transaction
session.getTransaction().commit();
System.out.println("\n ==> commit trans done. Success!");

//Get Courses for the Instructor afterwards
System.out.println("\n DataLoading ==> Courses for the Instructor : "+ instructorData.getCourses());
``` 
Thus unlike *Eager Loading*  If you tries to retrive a data after Session is closed it'll thorugh an Exception as follows : 
>  org.hibernate.LazyInitializationException : failed to lazily initialize a collection of role:

Then to resolve this issue we have 2 options.

####Option1: 
*Initialize or load the object before closing the session* .
i.e.; call getCourses() before transaction commit, then it'll be loaded in memory and we can call the same data later on. 

```java
// Get Courses for the Instructor First
System.out.println("\n DataLoading ==> Courses for the Instructor : "+ instructorData.getCourses());

// Commit the transaction
session.getTransaction().commit();
System.out.println("\n ==> commit trans done. Success!");

// Recall Course to get object
System.out.println("\n DataLoading ==> Courses for the Instructor : "+ instructorData.getCourses());
``` 

####Option2: 
*Hibernate Query with HQL* .
Here we use custom HQL to load the data set as per our requirement.

```java
// Get instructor from DB using HQL
Query<Instructor> query = session.createQuery("select instr from Instructor instr "
				+"JOIN FETCH instr.courses "
				+ "where instr.id= :instructorID", Instructor.class);

//Set Query parameter, i.e.; instructorID
query.setParameter("instructorID", id);

Instructor instructorData = query.getSingleResult();
System.out.println("\n Opt2-Case 1 DataLoading ==> Instructor data from object: " + instructorData);
```

Here we use HQL to fetch the whole Instructor and respective Courses data set as Joined Result set even though it's works as Lazy Loading. 
Used HQL we have handle 2 types of Cases:
- Case 1: get the course data after we commit a transaction
- Case 2: get the course data after we commit and CLOSED! a transaction

All the code sample are written under package of:
		*com.anir.hbm.mapping.one2many.dbfetchtype.lazyfetch*

Code is fully stacked with javadoc commenting for coder better understanding.
