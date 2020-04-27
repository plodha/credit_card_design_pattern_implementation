# Individual Project for CMPE 202
Design a credit card validation program that accepts a csv, json, or xml file and creates an object of the correct type of credit card. Currently we have created American Express, Mastercard, Discover, and Visa credit cards.

Design Pattern: Chain-of-Responsibility

## Version
JAVA version = 1.8.0_24

## Building java program
while in the project directory (individual-project-plodha) navigate to
```
cd src/main/java/com/sjsu/cmpe202
```
Once you have navigated to the java files run the make command to build the project
```
make
```

## Running program
while in the project directory (individual-project-plodha) navigate to
```
cd src/main/java/com/sjsu/cmpe202
```
Run the command below to test the program
```
java CreditCardIntro inputfile outputfile
```


## Unit Tests
For this I realized that the jar file is needed, so I installed it using maven.
while in the project directory (individual-project-plodha) run the following command.
This will execute the tests located in src/test/java/com/sjsu/cmpe202/
```
mvn test
```
