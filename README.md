Benefex technical interview

Initial thoughts before coding :
Goal : create API to save new "employee"
Complexity :

- Email validation -> validate input and validate unique
  - Possible solutions :
    - use external library like Apache Commons Validator
    - create own validation -> possible cache so we don't need to fetch emails in DB to validate unique names every time
    - Input validation not mentionned for DOB and Address -> might need some validation
- Generate unique EmployeeNo -> use random UUID

HOW TO RUN :
The project is a Spring Boot app. You can simply run it using your IDE run button.
Valid genders are : Male, Female, Other
The api will be accessible under the URL : POST http://localhost:8080/api/employees - I have not changed the default project
Payload example :
{
"id": 1,
"employeeNo": "b47f4156-47c5-43c2-b35f-136e18c078d6",
"title": "Mr",
"firstName": "John",
"surname": "Smith",
"dateOfBirth": "1996-04-03",
"gender": "Male",
"email": "John.Smith@gmail.com",
"address": "1 Somewhere Street"
}

Valid Response example :
200 Created
{
"id": 1,
"employeeNo": "b47f4156-47c5-43c2-b35f-136e18c078d6",
"title": "Mr",
"firstName": "John",
"surname": "Smith",
"dateOfBirth": "1996-04-03",
"gender": "Male",
"email": "John.Smith@gmail.com",
"address": "1 Somewhere Street"
}

Invalid Reponse example :
400 Bad Request
{
"timestamp": "2024-10-11T09:25:43.360+00:00",
"status": 400,
"error": "Bad Request",
"message": "This employee has already been registered.",
"path": "/api/employees"
}

NOTE : I have added a GET http://localhost:8080/api/employees for testing purposes

Post implementation discussion :

- Decisions:

  - I decided to use an H2 database to not have to bother setting up a DB server. This means data is cleared whenever we shut down the server.
  - I decided to not use an external library for the email validation and do it myself. Other tools like Apache Commons Validator exist to do the work.
  - I did not use a cache to save emails as it didn't seem necessary for the scale of this app. Adding some kind of cached list to check emails without having to call the DB would be useful if the list of emails gets very large.
  - I did not check the duplication of UUIDs as the chance of having a duplicated UUID is extremely unlikely. It would have been possible to check for UUID uniqueness the same way I checked for email uniqueness (calling DB or checking cache).
  - I did not add unit tests to the EmployeeController because I had issues with importing jackson libraries for the LocalDate input in the payload. I did not want to waste more time on it ad I wanted to make other modifications before submitting the project.
  - I did not validate the address format (number, street, city, etc) as it didn't seem necessary in the context of this app and I was running out of time. Adding a regex or multiple ifs to check input would be desirable.
  - I used a regex for email format validation as it seemed cleaner than checking each part of the email input manually.
  - I did not have time to do a final refactor of the EmployeeValidator class to avoid duplication and make the validateEmail() a bit cleaner.

- Improvements given more time :
  - Final refactor to EmployeeValidator as there is multiple duplication for Null or Empty validations
  - Final refactor to EmployeeValidator as email format validation could be a bit clearer
  - Moving null or empty validation at API level to fail early and avoid duplication in the EmployeeValidator
  - Adding format validation for address
  - Adding unit tests to EmployeeController to validate response format
  - Adding cache to avoid calling DB for email duplication validation
  - Adding more APIs to fetch, update and delete employees (not in scope of the interview)
  - If employee deletion was implemented, I would have used a soft delete (adding "active" parameter to DB entity and the delete would have changed the active flag to false)
  - Clearer error messages
  - Adding unit tests to getAllEmployees() test API
