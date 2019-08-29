# QA Showcase

A showcase of my QA abilities.

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

## Setting up the pom.xml file

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

## Setting up BDD/BDT (Behavior Driven Development/Testing) framework

## Setting up POM (Page Object Model) framework

## Using the BDD-POM hybrid Framework

## Creating API tests

## Creating database tests

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
