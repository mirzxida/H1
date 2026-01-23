Music Festival Management System

This project is a Java-based Music Festival Management System that demonstrates Object-Oriented Programming principles, JDBC database integration, and exception handling.

The system models a real music festival by managing bands, stages, crew members, and performance time slots. PostgreSQL is used as the database, and Java communicates with it using JDBC.

Project Goals

The main goals of this project are:

Practice advanced Object-Oriented Programming concepts

Use abstraction, inheritance, and composition in a real scenario

Connect a Java application to a PostgreSQL database using JDBC

Implement custom exception handling

Apply business logic instead of only basic CRUD operations

Technologies Used

Java

PostgreSQL

JDBC

IntelliJ IDEA

Project Structure
src   
├── model       
├── repository    
├── service   
├── exception   
├── utils       
└── Main.java    
Class Overview
BaseEntity (abstract)

BaseEntity is an abstract class that contains common fields and methods shared by all entities. It defines abstract methods that must be implemented by subclasses.

Band

Represents a music band performing at the festival. It stores band name, country, and genre.

Stage

Represents a festival stage. Each stage has a name and a capacity. The system uses this data to evaluate stage risk.

CrewMember

Represents a festival crew member. A self-reference is used to model a manager-subordinate relationship.

Slot

Represents a performance time slot. It uses composition by containing both a Band and a Stage.

Key Features

Detection of time collisions when scheduling performances

Validation of input data using custom exceptions

Business logic methods such as stage risk evaluation

Clean separation between data access and logic layers

How to Run the Project

Make sure PostgreSQL is running

Create the database and tables using the provided SQL script

Update database credentials in DatabaseConnection.java

Compile and run the program

Example commands:

javac *.java
java Main
Program Output

The program demonstrates:

Successful scheduling of a performance

Prevention of overlapping time slots using exceptions

Screenshots of the program output are included in the docs/screenshots folder.

Reflection

While working on this project, I improved my understanding of Object-Oriented Programming and how it applies to real-world systems. One of the main challenges was designing meaningful business logic instead of simple database operations.

This project helped me understand the importance of abstraction, validation, and exception handling in building reliable applications.