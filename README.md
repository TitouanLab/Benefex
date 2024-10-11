# Benefex

Benefex interview

Initial thoughts before coding :
Goal : create API to save new "employee"
Complexity :

- Email validation -> validate input and validate unique
  - Possible solutions :
    - use external library like Apache Commons Validator
    - create own validation -> possible cache so we don't need to fetch emails in DB to validate unique names every time
    - Input validation not mentionned for DOB and Address -> might need some validation
- Generate unique EmployeeNo -> use random UUID
