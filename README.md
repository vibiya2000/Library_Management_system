# Library_Management_System

 ![image](https://github.com/vibiya2000/Library_Management_system/blob/main/Library%20Logo.png)

 The Library Management System is designed to streamline the operations of a library. It facilitates efficient management of books, user 
 accounts,and borrowing transactions. Key features include adding, updating, and searching for books, managing user roles.

 # Development Tools
 - JAVA
 - SPRING BOOT
 - SPRING DATA JPA
 - HIBERNATE
 - MYSQL
 # Modules
 - Login and Logout Module
 - Admin Module
 - User Module
 - Book Module
# Features
- User and Admin auhtentication & Validation with session uuid.
- Admin Features:
    + Only resgistered Admin with valid session token can add/update/delete book from main database.
    + Admin can access the detaails of different users.
- User Features:
    + Registering themselves with application and logging in  to get the valid session token.
    + Viewing and searching from the list of book and borrowing/returning of books.
    + Only logged in user can acccess his profile updation and borrow books from library.
  # Api Root Endpoint

  http://localhost:8080/swagger-ui/index.html#/

  # Sample Api Response for Borrow book

  1)post http://localhost:8080/swagger-ui/index.html#/user-login-controller/logInUser

     + User can login with mobile number and password and using uuid key can borrow book

        Sample Request Body for Login

           {
              "mobile": "9123458900",
              "userPassword": "sovmi1705"
            }

       Sample Response Body

           {
               "userId": 1,
               "uuid": "7961",
               "userLoginTime": 2024,7,6,20,00,00
 
            }

  2) post http://localhost:8080/swagger-ui/index.html#/book-controller/borrowBook

       Sample Request Body for Borrow book

          "key": "7961"
          "bookId": 4
  
        Sample Response Body

           {
               "bookId": 4,
               "author": "J.R.R. Tolkien",
               "title": " The Hobbit",
               "availability": false,
               "borrowedBy": {
               "userId": 1,
               "userName": "sovmiya",
               "mobile": "9123458900",
               "userPassword": "sovmi1705"
                      }
             }

  
