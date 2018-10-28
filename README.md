# Reimagined Simple Mazer
A TDD approach (partially :)) to implement a simple maze solver by well known algorithms in Java 

### Assumptions
* Evaluators will not expect me to re-invent the wheel.
* The purpose is not to develop yet another Maze solver algorithm.
* Use well known algorithms which were developed by genius computer scientists in the world.
* The purpose of this assignment is to evaluate the approach for the solution design
* Expect only command line interface

### Set up
* Clone the project `git clone https://github.com/buddhikasemasinghe/reimagined-simple-mazer `
* `cd reimagined-simple-mazer`
* To build the project
    ` mvn clean build`

### Run the application
* To run the application
 `java -jar ./target/simple-mazer-0.0.1-SNAPSHOT.jar`
 
 * It will prompt you a shell
 * Type `help` then it will list down the accepted commands
  `shell:>help`
  * Commands
    * `process-file <filepath>` 
        eg: process-file /tmp/maze-runner.txt
        
    * `list-file <folder path>`
        eg: list files in the folder/directory
        
  * Type `exit` to exit the program.
  
  
### Evaluation
 * Todo
 
### Authors
 * Buddhika Semasinghe <buddhika.semasinghe@gmail.com>
 
## Enhancement / Todo
* Implement validation
* Increase test coverage
* Refactor service layer
* Implement other algorithms
