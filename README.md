# advance-technical-assessment
Advance Technical Assessment – Poker Test
Shingirai Edward Chanakira

Build Tool:
Maven

Credits
The evaluator implements Kevin Suffecool's 5-card hand evaluator, with the perfect hash optimization by Paul Senzee.

https://github.com/jmp/poker-hand-evaluator
https://jmp.github.io/poker-hand-evaluator/

If you have Maven installed, you can use it to build the project. 

The project also includes a Maven wrapper for environments which don't have Maven installed.

To use the Maven wrapper, replace "mvn" with "./mvnw" in the commands below.

Running Tests:
mvn test

Running tests and packaging:
mvn clean compile package

Running the Application
java -jar advance-technical-assessment-1.0-SNAPSHOT.jar

Running using the shell script:
The project has a shell script called run.sh.
This script will 1st build the project if it can't find a compiled jar file, otherwise it will just run the available jar file.
To run the script, run ./run.sh from a terminal.
