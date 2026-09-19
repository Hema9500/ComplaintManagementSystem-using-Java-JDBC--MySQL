# ComplaintManagementSystem-using-Java-JDBC--MySQL
This project has been done using Java,JDBC, MySQL in Eclipse IDE
ComplaintManagementSystem-using-Java-JDBC-MySQL
This project has done by using Java,JDBC,Mysql,Eclipse IDE

## Problem Statement
In many organizations, colleges, institutions, and service-based environments, complaints are often submitted and managed manually. This can lead to difficulties such as loss of complaint records, delays in resolving issues, lack of transparency, and difficulty in tracking the current status of a complaint.

To overcome these problems, a Complaint Management System is proposed. The system is a Java-based application that allows users to register, log in, submit complaints, and track the status of their own complaints. Every newly submitted complaint is initially assigned a Pending status.

The system also provides an Admin role with additional privileges. Administrators can view individual complaints, view all complaints, update complaint statuses, and delete complaints when required. The administrator can update a complaint through different stages such as Pending, In Progress, and Resolved.

The system follows a layered architecture consisting of Model, DAO, Service, and Controller layers, with MySQL used for storing user and complaint information. Role-based access ensures that normal users can access only their own complaints, while administrators can manage all complaints.

The main objective of the system is to provide a simple, organized, and reliable way to submit, manage, and track complaints while maintaining proper separation between user and administrator functionalities.

## Technology Used
Java – Main programming language

JDBC – Connects Java application with MySQL

MySQL – Stores user and complaint data

Eclipse IDE – Used for development

Layered Architecture – Separates application responsibilities

## 🏗️ Project Architecture
The Complaint Management System follows a Layered Architecture using Java, JDBC, and MySQL. The application is divided into separate layers, where each layer has a specific responsibility.

                    ┌──────────────────────────┐
                    │       USER / ADMIN       │
                    │    Console Application   │
                    └────────────┬─────────────┘
                                 │
                                 ▼
                    ┌──────────────────────────┐
                    │       CONTROLLER         │
                    │                          │
                    │  Main.java               │
                    │  ComplaintController     │
                    └────────────┬─────────────┘
                                 │
                                 ▼
                    ┌──────────────────────────┐
                    │         SERVICE          │
                    │                          │
                    │  UserService             │
                    │  ComplaintService        │
                    │                          │
                    │  • Validation             │
                    │  • Business Logic         │
                    └────────────┬─────────────┘
                                 │
                                 ▼
                    ┌──────────────────────────┐
                    │           DAO            │
                    │                          │
                    │  UserDAO                 │
                    │  ComplaintDAO            │
                    │                          │
                    │  • CRUD Operations       │
                    │  • SQL Queries            │
                    └────────────┬─────────────┘
                                 │
                                 ▼
                    ┌──────────────────────────┐
                    │       DB CONNECTION      │
                    │                          │
                    │      DBConnection        │
                    │         (JDBC)           │
                    └────────────┬─────────────┘
                                 │
                                 ▼
                    ┌──────────────────────────┐
                    │          MySQL           │
                    │                          │
                    │  ┌────────────────────┐  │
                    │  │ users              │  │
                    │  ├────────────────────┤  │
                    │  │ complaints         │  │
                    │  └────────────────────┘  │
                    └──────────────────────────┘
## 📂 Layer Description
Layer	Components	Responsibility
Model	User, Complaint, ComplaintStatus	Represents application data and entities
Controller	Main, ComplaintController	Handles user interaction and controls application flow
Service	UserService, ComplaintService	Handles validation and business logic
DAO	UserDAO, ComplaintDAO	Performs database operations using SQL
Utility	DBConnection	Establishes and manages JDBC database connection
Database	MySQL	Stores users and complaint records
## 🔄 Application Flow
User/Admin
    ↓
Controller
    ↓
Service
    ↓
DAO
    ↓
JDBC
    ↓
MySQL Database
## 👤 User Flow
Login
  ↓
User Menu
  ├── Submit Complaint
  │       ↓
  │    PENDING
  │
  ├── Track My Complaint
  │       ↓
  │    View Own Complaints & Status
  │
  └── Exit
Users can submit complaints and track only their own complaints. Each new complaint is automatically assigned the PENDING status.

## 👨‍💼 Admin Flow
Login
  ↓
Admin Menu
  ├── View Complaint
  ├── View All Complaints
  ├── Update Complaint Status
  │       ↓
  │   PENDING
  │      ↓
  │   IN_PROGRESS
  │      ↓
  │   RESOLVED
  │
  ├── Delete Complaint
  │
  └── Exit
Administrators have access to all complaints and can view, update, and delete complaint records.

## 🔐 Role-Based Access
The system provides two roles:

USER

Submit complaints
Track their own complaints
View complaint status
Exit
ADMIN

View individual complaints
View all complaints
Update complaint status
Delete complaints
Exit
## 🗄️ Database Relationship
              USERS
        ┌─────────────────┐
        │ user_id (PK)    │
        │ name            │
        │ email           │
        │ password        │
        │ role            │
        └────────┬────────┘
                 │
                 │ 1
                 │
                 │
                 │ N
        ┌────────▼────────┐
        │   COMPLAINTS    │
        │─────────────────│
        │ complaint_id PK │
        │ user_id FK      │
        │ title           │
        │ description     │
        │ status          │
        │ created_date    │
        └─────────────────┘
One User can have multiple Complaints, while each complaint belongs to one user.

## 🛠️ Technologies Used
Java
JDBC
MySQL
SQL
Layered Architecture
Object-Oriented Programming (OOP)
## ✨ Key Features
User registration and login
Role-based authentication
Complaint submission
Automatic PENDING status for new complaints
User-specific complaint tracking
Admin complaint management
Complaint status updates
Complaint deletion
MySQL database integration
DAO and Service layer separation
Input validation
## Features
User registration and login

Admin login with role-based access

Submit new complaints

View individual complaints

View all complaints

Update complaint status

Complaint status: Pending, In Progress, Resolved

Delete complaints

Store complaint details securely in MySQL

Input validation and error handling

## Future Enhancement
Develop a web/mobile application.

Add email or SMS notifications for status changes.

Add complaint categories and priority levels.

Add search, filtering, and sorting options.

Add an admin dashboard with charts and reports.

Allow users to upload images/documents with complaints.

Add feedback and rating after complaint resolution.

Improve security using password hashing and authentication.

## Conclusion
The Complaint Management System provides an efficient and organized way to manage complaints digitally. The system allows users to submit and track complaints while administrators can manage complaints and update their status. By using Java, JDBC, MySQL, and layered architecture, the project provides a simple, maintainable, and scalable solution. The system can be further enhanced into a complete web or mobile-based complaint management
