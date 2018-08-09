# Hibernate Eager & Lazy Loading

In this example you will learn detail functionality about **Hibernate Data Fetching Technology**; i.e.; Hibernate data loading technics as;
EAGER Loading & LAZY Loading. which contains more factor when we are dealing with mapping like **One-To-Many** or **Many-To-One**. 

**Eager Loading** is a design pattern in which data initialization occurs on the spot
**Lazy Loading** is a design pattern which is used to defer initialization of an object as long as it’s possible

Advantages and Disadvantages
** Lazy Loading **
*Advantages:*
1. Initial load time much smaller than in the other approach
2. Less memory consumption than in the other approach

*Disadvantages: *
1. Delayed initialization might impact performance during unwanted moments
2. In some cases you need to handle lazily-initialized objects with a special care or you might end up with an exception

** Eager Loading: **
*Advantages:*
1. No delayed initialization related performance impacts

*Disadvantages:*
1. Long initial loading time
2. Loading too much unnecessary data might impact performance

===============================================================================
===============================================================================

Here we have three tables
- instructor
- course
- instructorDetails

Here we the relationship Cardinality as follow:
relationship of 1:M cardinality:
> instructor  -- A-HasM --> course

other has a relationship of 1:1 cardinality:
> instructor  -- HasA --> instructorDetails

Now we have a Conditional Logic where  **A Instructor can have Many to one relation with Course.**. In eager loading strategy, if we load the User data, it will also load up all Course associated with it and will Instructor it in a memory.

But, when lazy loading is enabled, Course Detail data won’t be initialized and loaded into a memory until an explicit call is made to it.
 
These functionalities are being defined under package --> *om.anir.hbm.mapping.one2many.dbFetchType*

Here we demonstrated the Concept of Hibernate Fetch type in Class *EgarVsLazyFetchDemo*. To run & test this feature
1.  go to Entity 'Instructor' change the FetchType.
2.  Then Run the program and Watch the console log of both Hibernate and Syso Print.


*You'll see of EAGER Loading all the Courses data will be loaded  with Instructor data at first time.*  
*For LAZY Loading Courses data will be loaded after, when we call course's getter method. First only Instructor data will be loaded.*

##Conclusion

Hibernate applies lazy loading approach on entities and associations by providing a proxy implementation of classes. Hence it's recommended to used Lazy Loading for Many to One or One to Many relationship 

Hibernate intercepts calls to an entity by substituting it with a proxy derived from an entity’s class. In our example, when a requested information is missing, it will be loaded from a database before control is ceded to the User class implementation.

It should also be noted that when the association is represented as a collection class (in the above examples it is represented as Set<OrderDetail> orderDetailSet), then a wrapper is created and substituted for an original collection.


 
