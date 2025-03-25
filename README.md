# GOLD

Automation scripts for Gold project

#Overview

This repository contains automation scripts for testing the GOLD project.The framework is built using Java, TestNG, and Selenium WebDriver, with a modular structure for easy maintenance and scalability.

#Key Features:

Automated testing of core functionalities

API testing and UI testing

Smoke Testing 

Regression Testing

Reusable utilities and test components

Detailed reports and logs

Configurable environment properties

#🛠️ Technologies Used

Java

TestNG

Selenium WebDriver

Maven

Log4j

#Installation
**Prerequisites**

Ensure the following dependencies are installed:

Java JDK 

Maven (for dependency management)

TestNG

Selenium WebDriver

ChromeDriver or relevant drivers

#Clone the Repository

git clone **<repository_url>**

cd **<repository_name>**

###Install Dependencies

All required dependencies for this project are managed using **Maven** and are specified in the `pom.xml` file.
  
They are sourced from the **Maven Central Repository** and automatically installed during the build process.

#Project Structure

GOLD [GOLD main]

├── src

│   ├── main

│   │   └── java

│   │       ├── APITests

│   │       ├── models.admin

│   │       ├── TestComponent

│   │       ├── TestLib

│   │       ├── TestNg.reader

│   │       └── Utilities

│   ├── test

│   │   └── java

│   │       └── TestExecute

│   └── test/resources

│       ├── Config

│       ├── credentials

│       ├── MailTemplates

│       ├── TestData

│       ├── TestNGfiles

│       └── config.properties

├── JRE System Library

├── Maven Dependencies

├── target

├── TestLogs

├── test-output

├── log4j.properties

├── pom.xml

└── README.md

     

#Usage

- **Run a Single Test Case:**  

  - In **Eclipse**, 
  
    `Run As → TestNG Test`  
 
- **Run the Entire Test Suite:**  

  - Execute the suite XML file (e.g., `TestNGfiles/suite.xml`):  
  
    `Run As → TestNG Suite`  
 
- **Run from the Command Line:**  

  - Use the following Maven command to execute the test cases:  
    
     Example:mvn clean install -PAutomation -DconfigFile=suite\config.properties -DtestNG=suite.xml
    
    
