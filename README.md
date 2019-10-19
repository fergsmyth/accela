## Accela coding test

#### Notes/Assumptions

* Application executed on a per command basis. To run multiple options, execute each separately. 
* Database is deployed to an AKS managed instance.
* User id is a unique field that identify users for addition, deletion etc and are not tied to the database id.
* No logging framework has been used, instead validation outs are directed to System.out.
* No Linux/Unix testing has been done for XML user upload.
* Test files are provided in the src/main/resources for convenience.
* No units test due to time constraints. 

#### Adding via XML

Below is the expected format for user xml import.
File should end with .xml.

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<user>
    <userId>12</userId>
    <firstname>Ferg</firstname>
    <surname>Smyth</surname>
</user>
```
#### Stated problem
Create a simple command line JAVA application with database access;
Based on an input from the command line provide the following functionality;
Provide help

1. Add Person (id, firstName, surname)
2. Edit Person (firstName, surname)
3. Delete Person (id)
4. Count Number of Persons
5. List Persons

Additional Requirements (nice to have, but not required);

1. Ability to Add Person from XML (Senior Only)
2. Test coverage
3. Maven or Gradle Build
4. Executable Jar

Show us what you know, use any third party frameworks that you think will help you complete the exercise. Keep it simple.  
Please supply the source code and instructions on how to build and run your application.  
Please upload your all or your code & work into Github, Dropbox or Drive etc... and send us the link.  
Please spend no more than 4 hrs on this.

