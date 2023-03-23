---
Generate a spring boot application from scratch. 
The application should use H2 as a database and must provide 
endpoints to store and retrieve a Book entity. 
the book entity should have the field "author" of 30 chars, 
and the field "name" of 30 chars.

---

Add an interceptor that checks the presence of the authorization header, 
and returns an error if the header does not start with the string bearer.

---
Add a Unit Test for this Interceptor. In the test, try to call the post book endpoint 
with or without a correct HTTP header. 

---
Generate a curl request so that I can test this endpoint manually

---
I would like to add logging to my application. Is it better to log on 
controller or on service layer?

---
How can I configure logging to print SQL queries?

---
How can I optimize the performance of the query 
to retrieve the list of books? 

---
