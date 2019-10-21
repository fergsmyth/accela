## Accela coding test
#### How to build
mvn clean package spring-boot:repackage.  
This will create an executable jar (user-management-0.0.1-SNAPSHOT.jar) in the /target folder.
#### How to run application
To list options: _java -jar user-management-0.0.1-SNAPSHOT.jar_
```
Enter an option between 1 and 6 and the required arguments. Each argument should be separated by a space.
Add user        : 1 userid firstname lastname
Update user     : 2 userid firstname lastname
Delete user     : 3 userid
Count users     : 4
List users      : 5
Add user(XML)   : 6 filepath/file.xml
```
Adding user example: _java -jar user-management-0.0.1-SNAPSHOT.jar 1 1 Ferg Smyth_

#### Notes/Assumptions
* Due to time constraints and wanting to show as much breadth as possible in my work, I prioritised the application functionality over unit testing. However I do have extensive experience in this area.
* Database is deployed to an Azure managed Postgres server.
* User id is an additional field on the user entity for user identification and is not analogous to the database id.
* No logging framework has been used, instead outputs are directed to System.out.
* No Linux/Unix testing has been done for XML user upload.
* A test xml file is provided in the src/main/resources as an example of the expected structure, see also xsd.
#### Next steps
* Add unit testing to automate testing coverage.
* Add multiple user xml batch import.
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

