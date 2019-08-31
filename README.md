# QA Showcase

A showcase of my QA abilities.

***

## Setting up a Framework from Scratch

1. Create a Maven project
2. Create the following project structure:

   ```text
   - src
     - main
         - java
           - com.h1ddengames.framework
           - All framework files belong here
         - resources
           - Any properties files for different environments belong here
           - Any properties files for custom reporting belong here
     - test
         - java
           - com.h1ddengames
             - com.shiftedtech
               - spree
                 - heatclinic
                 - Any projects within the same company belong here
         - resources
           - log4j.properties
    ```

3. Add required dependencies into pom.xml
4. Setup custom test reporting (Extent or Allure reporting or create your own reporting)
5. Create a driver factory that supports ThreadLocal so all your tests can be run in parallel.
   1. The driver factory should make creating and using a WebDriver as simple as possible.
   2. Allow users to choose which WebDriver to use when creating a driver.
   3. Allow users to choose between headless or GUI WebDrivers.
6. Setup cross browser testing by creating a testng.xml runner that utilizes parameters.
7. Implement the POM (Page Object Model) framework.
   1. Navigation (and other WebElements that remain the same from one page to another should be the base class/super class)
   2. Each page of the company website should have it's own class and these classes should all extend the navigation page class.
   3. Implement a function driven framework where each page has several steps of a test wrapped into it's own function.
      1. For example in the Login page class, create a function that takes in a String username and String password. The username and password fields are cleared of any previous text stored. Next, the function enters those Strings into the username and password field respectively. Finally, the login button is clicked.
8. Implement the BDD (Behavior Driven Development/Testing) framework.
9. Implement Rest Assured for API testing.
10. Implement Karate for BDD style API testing.
11. Implement the JDBC (Java Database Connector) for database testing.
    1. Has an added benefit of being used for Keyword and Data Driven Development/Testing.
12. Generate tests using AssertJ, Selenium, and TestNG.
13. Setup Selenium Grid and/or Browser Stack based on company's requirements.
14. Setup Jenkins or Bamboo as a CI/CD pipeline based on company's requirements.

***

## Project folder structure

- The testcases package contains random test cases just to make sure that the project works with all the dependencies.
- The hybridtestcases package is to make sure the framework has been implemented properly.
- The com.shiftedtech.spree package contains all tests related to the website <http://spree.shiftedtech.com>
- The com.shiftedtech.heatclinic package contains all tests related to the website <http://heatclinic.shiftedtech.com>
- All test scripts belong in src/test/java
- All framework scripts belong in src/main/java

```text
- src
    - main
        - java
            - com.h1ddengames.framework
        - resources
    - test
        - java
            - com.h1ddengames
                - com.shiftedtech
                    - spree
                    - heatclinic
                - hybridtestcases
                - testcases
        - resources
            - log4j.properties
```

***

## Setting up the pom.xml file dependencies

1. Go to <https://mvnrepository.com> and search for the following dependencies or include the dependencies below:
    - Selenium
    - WebDriverManager
    - Rest-Assured
    - Mysql JDBC
    - TestNG
    - Hamcrest
    - Assertj
    - Cucumber Java
    - Cucumber TestNG
    - Cucumber Reporting
    - slf4j-api
    - slf4j-log4j12
    - Maven Surefire Plugin
    - Maven Compiler Plugin

2. In the properties tag, create a property for all the dependencies with the current version number within the property tag.

    - For example:

    ``` maven
    <properties>
        <java.version>11</java.version>
        <selenium-java.version>3.141.59</selenium-java.version>
        ... All the rest from step 1. ...
        <maven-compiler.version>3.8.1</maven-compiler.version>
    </properties>
    ```

3. Update all the dependencies versions using the property variables created above:

    ```maven
    <dependency>
        <groupId>org.seleniumhq.selenium</groupId>
        <artifactId>selenium-java</artifactId>
        <version>3.141.159</version>
    </dependency>

    becomes:

    <dependency>
        <groupId>org.seleniumhq.selenium</groupId>
        <artifactId>selenium-java</artifactId>
        <version>${selenium-java.version}</version>
    </dependency>
    ```

4. Create a file in src/test/resources and name it log4j.properties. Then put the following lines in the file:

   ```log4j
    # Set root logger level to DEBUG and its only appender to A1.
    log4j.rootLogger=INFO, A1

    # A1 is set to be a ConsoleAppender.
    log4j.appender.A1=org.apache.log4j.ConsoleAppender

    # A1 uses PatternLayout.
    log4j.appender.A1.layout=org.apache.log4j.PatternLayout
    log4j.appender.A1.layout.ConversionPattern=%-4r [%t] %-5p %c %x - %m%n
   ```

5. The final pom.xml should look something like this:

    ``` maven
    <properties>
        <!-- Keep all the version numbers at the top to make it easier to upgrade -->
        <java.version>11</java.version>
        <selenium-java.version>3.141.59</selenium-java.version>
        <webdrivermanager.version>3.6.2</webdrivermanager.version>
        <rest-assured.version>4.0.0</rest-assured.version>
        <mysql-jdbc.version>8.0.17</mysql-jdbc.version>
        <testng.version>7.0.0</testng.version>
        <hamcrest.version>1.3</hamcrest.version>
        <assertj.version>3.13.2</assertj.version>
        <cucumber-java.version>4.7.1</cucumber-java.version>
        <cucumber-testng.version>4.7.1</cucumber-testng.version>
        <cucumber-reporting.version>4.9.0</cucumber-reporting.version>
        <slf4j-api.version>1.7.28</slf4j-api.version>
        <slf4j-log4j12.version>1.7.28</slf4j-log4j12.version>
        <maven-surefire.version>3.0.0-M3</maven-surefire.version>
        <maven-compiler.version>3.8.1</maven-compiler.version>
    </properties>

    <dependencies>
        <!-- https://mvnrepository.com/artifact/org.seleniumhq.selenium/selenium-java
            This dependency is for browser automation -->
        <dependency>
            <groupId>org.seleniumhq.selenium</groupId>
            <artifactId>selenium-java</artifactId>
            <version>${selenium-java.version}</version>
        </dependency>

        ... Include all the other dependencies in the above format ...

        <!-- https://mvnrepository.com/artifact/org.apache.maven.plugins/maven-compiler-plugin -->
        <dependency>
            <groupId>org.apache.maven.plugins</groupId>
            <artifactId>maven-compiler-plugin</artifactId>
            <version>${maven-compiler.version}</version>
        </dependency>
    </dependencies>

    <build>
        <plugins>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-compiler-plugin</artifactId>
                <version>3.8.0</version>
                <configuration>
                    <release>${java.version}</release>
                    <encoding>UTF-8</encoding>
                </configuration>
            </plugin>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-surefire-plugin</artifactId>
                <version>3.0.0-M3</version>
                <configuration>
                    <suiteXmlFiles>
                        <suiteXmlFile>testng-simpletest.xml</suiteXmlFile>
                    </suiteXmlFiles>
                    <argLine>
                        --illegal-access=permit
                    </argLine>
                </configuration>
            </plugin>
        </plugins>
    </build>
    ```

***

## Setting up and Using Allure Reporting Framework

***

## Creating a Driver Factory

***

## Setting up Cross Browser Testing with testng.xml

1. Create an xml file called simple-tests.xml with the following code:

    ```TestNG
    <!DOCTYPE suite SYSTEM "http://testng.org/testng-1.0.dtd">

    <suite name="Simple Test Suite" verbose="1">
        <test name="Chrome Test" >
            <parameter name="browser" value="CH"/>
            <classes>
                <class name="com.h1ddengames.testcases.TestCase1"/>
            </classes>
        </test>
        <test name="Firefox Test" >
            <parameter name="browser" value="FF"/>
            <classes>
                <class name="com.h1ddengames.testcases.TestCase1"/>
            </classes>
        </test>
    </suite>
    ```

2. Update the setup method (@BeforeClass marked method) to look like this:

    ```java
    @Parameters({ "browser" })
    @BeforeClass
    public void setup(String browser) {
        // Downloading directly over the network is forbidden so you might
        // not be able to use WebDriverManager.
        WebDriverManager.chromedriver().version("76.0.3809.68").setup();
        WebDriverManager.firefoxdriver().version("0.24.0").setup();

        if(browser.toUpperCase().contentEquals("CH")) {
            driver = new ChromeDriver();
        } else if(browser.toUpperCase().contentEquals("FF")) {
            driver = new FirefoxDriver();
        } else {
            driver = new ChromeDriver();
        }
    }
    ```

- The simple-tests.xml will run all the tests found in TestCase1 script file with Chrome first then rerun all the tests with Firefox.
  - The simple-tests.xml can be updated to only run certain groups of tests (function, regression, etc) for each of the browsers:

    ```TestNG
    <!DOCTYPE suite SYSTEM "http://testng.org/testng-1.0.dtd">

    <suite name="Simple Test Suite" verbose="1">
        <test name="Chrome Test" >
            <parameter name="browser" value="CH"/>
            <groups>
                <run>
                    <include name="functional"/>
                </run>
            </groups>
            <classes>
                <class name="com.h1ddengames.testcases.TestCase1"/>
            </classes>
        </test>
        <test name="Firefox Test" >
            <parameter name="browser" value="FF"/>
            <groups>
                <run>
                    <include name="regression"/>
                </run>
            </groups>
            <classes>
                <class name="com.h1ddengames.testcases.TestCase1"/>
            </classes>
        </test>
    </suite>
    ```

    - If you are specifying the type of test to be run, you have to mark your test methods like so:

        ```java
        @Test(groups = { "functional" })
        public void positiveLoginCase() {
            // code here
        }
        ```

- Once a DriverFactory that supports ThreadLocal creation of WebDrivers has been implemented you can run both browsers in parallel (both browsers will open at the same time and all the tests will run on both browsers thus speeding up testing) with the following:

    ```TestNG
    <!DOCTYPE suite SYSTEM "http://testng.org/testng-1.0.dtd">

    <suite name="Simple Test Suite" parallel="tests" verbose="1">
        <test name="Chrome Test" >
            <parameter name="browser" value="CH"/>
            <classes>
                <class name="com.h1ddengames.testcases.TestCase1"/>
            </classes>
        </test>
        <test name="Firefox Test" >
            <parameter name="browser" value="FF"/>
            <classes>
                <class name="com.h1ddengames.testcases.TestCase1"/>
            </classes>
        </test>
    </suite>
    ```

## Setting up POM (Page Object Model) Framework

***

## Setting up BDD/BDT (Behavior Driven Development/Testing) Framework

***

## Using the BDD-POM Hybrid Framework

***

## Creating API Tests with Rest Assured

***

## Setting up and Using Karate

***

## Creating Database Tests with JDBC

***

## Creating Tests

***

## Setting up Selenium Grid

***

## Setting up Tests to run on Browser Stack

***

## Setting up Jenkins and Using Jenkins

***

## Notes

- A test can only be a test if you assert that something should happen.
  - For example:

    ```java
    // The success message contained in the WebElement successMessage should have the exact text "Logged in successfully"
    Assert.assertEquals(successMessage.getText(), "Logged in successfully");
    ```

- A false positive is worse than a false negative because no one cares to look at a test when it passes.
  - Example of false positive: being able to log in with an invalid username and/or password.
  - Example of false negative: not being able to log in with a valid username and password.
- Once the bug slips through this crack, it becomes much harder to find later on.
