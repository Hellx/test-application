**Tasks:**
1. Correct all of the deficiencies in index.html

2. "Sectors" selectbox:
   * Add all the entries from the "Sectors" selectbox to database
   * Compose the "Sectors" selectbox using data from database

3. Perform the following activities after the "Save" button has been pressed:
   * Validate all input data (all fields are mandatory)
   * Store all input data to the database (Name, Sectors, Agree to terms)
   * Refill the form using stored data
   * Allow the user to edit his/her own data during the session

 

Write us Your best code!

After completing the tasks, please provide us with:
1. Full database dump (structure and data)
2. Source code
---
Set up database - (install Postgres)
Login to postgres and execute following script:
```
    CREATE DATABASE testdb;
    CREATE USER testapi WITH PASSWORD 'testapipsw' SUPERUSER;
    GRANT ALL PRIVILEGES ON DATABASE TESTDB TO testapi;
```