Coverage: 86%
# To Do List

This TDL project was aimed to be able to use previous knowledge to link a working front-end to a back-end with two entities to be input into a database

## Getting Started

1. Go to https://github.com/RobertTolleyQA/TDL-Project-2
2. Clone the repository onto your local drive  
	- git clone <url> on your pc  
3. (if using git to run java file) ----- Open git bash in the TDL-Project/target  
	- type "java -jar TDL-Project-0.0.1-SNAPSHOT"  

### Prerequisites
1. Ensure java version 1.11 or higher is installed  
2. (optional) download git for your current OS 
3. Download Spring Tool Suite Version (4.9.0)  

### Using the jar

1. Wait for "finished loading to appear on the cmd line
2. Type "http://localhost:8080/index.html" into your web browser 
3. Read the homepage carefully
4. Navigate to Department page - Create a department if yours is not there and/or take note of your department ID
5. Navigate to Task page - Create a task and enter in previous department ID
6. Your task has now been published!

## Running the tests

1. Junit testing  
Whilst on Spring -- Right click the project or class -> Run As -> Junit Test  
2. Junit cover  
Whilst on Spring-- Right click the project or class -> Coverage As -> Junit Test  
3. Testing specific packages or classes:  
open src/test/java -> perform above actions   

### Unit Tests 

These tests test specific code that is only accessed in that class file and validate each unit.

```
src/test/java  
com.qa.services.TaskServicesUnitTest
```

### Integration Tests 
These tests test code that calls apon other methods that come from seperate classes, ensuring the are able to work with each other correctly.  

```
src/test/java  
com.qa.rest."_"IntergrationTest
```

## Built With

* [Spring](https://spring.io/) - Back-End
* [Visual-Studio-Code](https://visualstudio.microsoft.com/) - Front-End

## Versioning

1.0.0

## Authors

* **Robert Tolley** - *Sole Creator* - [roberttolley](https://github.com/RobertTolleyQA)

## License

This project is licensed under the MIT license - see the [LICENSE.md](LICENSE.md) file for details 

[Bootstrap license](https://github.com/twbs/bootstrap/blob/v4.0.0/LICENSE)

## Acknowledgments

* Hat tip to [Alan Davis](https://gist.github.com/MorickClive) & Savannah Vaithilingam for training me for this project