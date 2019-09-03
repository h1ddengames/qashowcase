# QA Showcase

A showcase of my QA abilities by creating an automation framework using Java, Selenium, TestNG. In short: this framework will be a BDD-POM hybrid framework with support for API and database testing.

This framework will include the following:
   - POM (Page Object Model) - To separate function driven methods into several classes as well as using PageFactory to find elements.
   - Object Repository - To store WebElement locators in properties files.
   - BDD/BDT (Behaviour Driven Development/Testing) - To abstract away the difficulty of creating test scripts by using Gherkin.
   - Rest API Testing - To test REST API using Rest Assured.
   - Database Testing - To test a mysql database using JDBC.
   - Cross-browser Testing - To run tests on multiple browsers at the same time.
   - Jenkins (CI/CD) - To run tests based on certain criteria (new code has been pushed, it is after work hours, etc)
   - Selenium GRID/Browser Stack (parallel distributed testing) - To run tests on a distributed environment either locally (GRID) or on a service (Browser Stack).
   
This framework will be missing the following (for now):
   - Mobile Testing
   - KDD/KDT (Keyword Driven Development/Testing)
   - DDD/DDT (Data Driven Development/Testing)
***

## Setting up a Framework from Scratch

1. Create a Maven project.
2. Create proper project structure.
3. Setup local maven repository, proxy, mirrors, and repositories.
4. Add required dependencies into pom.xml
5. Setup custom test reporting (Extent or Allure reporting or create your own reporting)
6. Create a driver factory that supports ThreadLocal so all your tests can be run in parallel.
   1. The driver factory should make creating and using a WebDriver as simple as possible.
   2. Allow users to choose which WebDriver to use when creating a driver.
   3. Allow users to choose between headless or GUI WebDrivers.
7. Setup cross browser testing by creating a testng.xml runner that utilizes parameters.
8. Implement the POM (Page Object Model) framework.
   1. Navigation (and other WebElements that remain the same from one page to another should be the base class/super class)
   2. Each page of the company website should have it's own class and these classes should all extend the navigation page class.
   3. Implement a function driven framework where each page has several steps of a test wrapped into it's own function.
      1. For example in the Login page class, create a function that takes in a String username and String password. The username and password fields are cleared of any previous text stored. Next, the function enters those Strings into the username and password field respectively. Finally, the login button is clicked.
9. Implement the BDD (Behavior Driven Development/Testing) framework.
10. Implement Rest Assured for API testing.
11. Implement Karate for BDD style API testing.
12. Implement the JDBC (Java Database Connector) for database testing.
    1. Has an added benefit of being used for Keyword and Data Driven Development/Testing.
13. Generate tests using AssertJ, Selenium, and TestNG.
14. Setup Selenium Grid and/or Browser Stack based on company's requirements.
15. Setup Jenkins or Bamboo as a CI/CD pipeline based on company's requirements.

***

## 1. Create a Maven project

1. Using IntelliJ IDEA, click on New > New Project.
2. Select Maven > Next.
3. Provide a GroupId in reverse order (if your company's url is google.com then you should make the GroupId "com.google")
4. Provide an ArtifactId based on the project you are working on then click Next.
5. Provide a project name and location then click Finish.

***

## 2. Project folder structure

- The testcases package contains random test cases just to make sure that the project works with all the dependencies.
- The hybridtestcases package is to make sure the framework has been implemented properly.
- The com.shiftedtech.spree package contains all tests related to the website <http://spree.shiftedtech.com>
- The com.shiftedtech.heatclinic package contains all tests related to the website <http://heatclinic.shiftedtech.com>
- All test scripts belong in src/test/java
- All framework scripts belong in src/main/java

    ```text
    - pom.xml
    - src
      - main
          - java
            - com.h1ddengames.framework
              - All framework files belong here
              - spree.pages
                - BasePage
                - HomePage
                - All other page object model files belong here
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
            - testng-simpletest.xml
    ```

***

## 3. Setup local maven repository, proxy, mirrors, and repositories

1. Download Maven CLI: <https://maven.apache.org/download.cgi>
2. Create and add a MAVEN_HOME variable then put it in your path.
3. Go to MAVEN_HOME/conf and copy the settings.xml file to your user's .m2 folder.
4. Change the settings.xml file to suit your company's requirements.
   1. For example change your localRepository to a path on your computer rather than on the network drive that most companies will use.
   2. Setup the proxy sever information if your company does not allow you to access the internet directly.
   3. Use an internal repository if your company has one <https://maven.apache.org/guides/introduction/introduction-to-repositories.html>
   4. Setup mirrors if required.

***

## 4. Setting up the pom.xml file dependencies

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

## 5. Setting up and Using Allure Reporting Framework

***

## 6. Creating a Driver Factory

***

## 7. Setting up Cross Browser Testing with testng.xml

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

***

## 8. Setting up POM (Page Object Model) Framework

1. Create a package in /src/main/java based on the project.
    - For example: spree.pages (${website}.pages)
2. Create as many java files as there are pages to be tested.
    1. BasePage
    2. HomePage
    3. LoginPage
    4. CartPage
    5. etc.
3. Create a java file that will act as the composition class (holds all other page classes within it)
    - Name it whatever you want (SpreeScriptBase or PageComposition)
    - For this example, we'll use PageComposition
4. Put the following code into the PageComposition class:

    ```java
    private HomePage homePage;
    private LoginPage loginPage;
    private CartPage cartPage;
    // any other pages go here following the above structure.

    // HomePage is different because if it's the first time creating a HomePage object then you need to use driver.navigate to go to the website.
    // For all other times that you ask for the homePage, you get whatever is already in memory.
    public HomePage homePage() {
        if(homePage == null) {
            homePage = new HomePage(driver);
            // When homePage is null, that means the script has not used driver.navigate() to get
            // to the homePage's baseURL. Calling any other method than navigate... will cause
            // WebElements to not be found.
            driver.navigate().to(homePage.baseURL);
        }
        return homePage;
    }

    public LoginPage loginPage() {
        if(loginPage == null) {
            loginPage = new LoginPage(driver);
        }
        return loginPage;
    }

    // ... all other pages follow the same structure as loginPage
    ```

5. For each of your pages you'll need to create a constructor with WebDriver as a parameter:

    ```java
    // The constructor should be the class name (WebDriver driver) with everything else the same.
    public HomePage(WebDriver driver) {
        // This line might be the only line required for you.
        this.driver = driver;
        // I include WebDriverWait because the framework that I'm choosing to make here uses WebDriverWait to find and use WebElements.
        this.driverWait = new WebDriverWait(driver, ScriptBase.DEFAULT_WEB_DRIVER_WAIT);
        // Same idea with JavaScriptExecutor. I choose to include it now so I can use it later.
        this.driverJSExecutor = (JavascriptExecutor) driver;
    }
    ```

6. Create function driven methods for each of the classes.
    - For example: you don't want a login method on every single page if the login method requires access to the username/password textbox that's only available on the login page.
    - For example this would be the login page:

        ```java
        public class LoginPage extends BasePage {

            public LoginPage(WebDriver driver) {
                this.driver = driver;
                this.driverWait = new WebDriverWait(driver, ScriptBase.DEFAULT_WEB_DRIVER_WAIT);
                this.driverJSExecutor = (JavascriptExecutor) driver;
            }

            public void login(String email, String password, boolean shouldLoginWork) {
                goToLoginPage();
                enterUsernameAndPassword(email, password);

                if(shouldLoginWork) {
                    checkLoginSuccessMessage();
                } else {
                    checkLoginFailedMessage();
                }
            }

            public void enterUsernameAndPassword(String email, String password) {
                enterDataIntoElement(By.id("spree_user_email"), email);
                enterDataIntoElement(By.id("spree_user_password"), password);
                clickElement(By.name("commit"));
            }

            public void checkLoginSuccessMessage() {
                WebElement loginSuccessMessage = driver.findElement(
                        By.xpath("//div[@id='content']/div[contains(text(),'Logged in successfully')]"));
                assertThat(loginSuccessMessage.getText()).startsWith("Logged").endsWith("successfully");
            }

            public void checkLoginFailedMessage() {
                WebElement loginFailedMessage = driver.findElement(
                        By.xpath("//div[@id='content']/div[contains(text(),'Invalid email or password.')]"));
                MatcherAssert.assertThat(loginFailedMessage.getText(), equalTo("Invalid email or password."));
            }
        }
        ```

    - The BasePage should implement navigation methods since navigation will be the same across all pages. Notice how the logout function belongs in this class since you can always logout no matter what page you're on.

        ```java
        public class BasePage extends CommonSeleniumTasks {
            public String baseURL = "http://spree.shiftedtech.com";

            protected WebDriver driver;
            protected WebDriverWait driverWait;
            protected JavascriptExecutor driverJSExecutor;

            @Override protected WebDriver getDriver() { return driver; }
            @Override protected WebDriverWait getDriverWait() { return driverWait; }
            @Override protected JavascriptExecutor getDriverJSExecutor() { return driverJSExecutor; }

            public void goToHomePage() {
                clickElement(By.linkText("Home"));
            }
            public void goToLoginPage() {
                clickElement(By.linkText("Login"));
            }
            public void goToCart() {
                clickElement(By.id("link-to-cart"));
            }

            public void logOut() {
                clickElement(By.linkText("Logout"));
                checkSignoutSuccessMessage();
            }

            public void checkSignoutSuccessMessage() {
                WebElement signoutSuccessMessage = driver.findElement(
                        By.xpath("//div[@id='content']/div[contains(text(),'Signed out successfully.')]"));
                Assert.assertEquals(signoutSuccessMessage.getText(), "Signed out successfully.");
            }
        }
        ```

7. Create your test scripts. While it might take one or two more lines compared to a pure function driven framework, you have: increased readability, code reuse by implementing POM, and avoided monolithic Java files. Code reuse because you've implemented OOP through POM (extends SpreeScriptBase, extends BasePage, extends CommonSeleniumTasks), readability because the methods seem more like an English sentence, and avoided monolithic Java files because each function will be contained within the Java file that fits the function (login belongs to the login page, while navigation belongs to every page, etc):

    ```java
    homePage().goToLoginPage();
    loginPage().login("shiftedtech0000@gmail.com", "shiftedtech", true);

    [from] home page, go to login page.
    [when on] login page, login(using email, and password, and expect result).
    ```

    ```java
    public class POMTestCase extends SpreeScriptBase {
        @Test()
        public void positiveLoginCaseWithFunctions() {
            homePage().goToLoginPage();
            loginPage().login("shiftedtech0000@gmail.com", "shiftedtech", true);
        }

        @Test()
        public void negativeLoginCaseWithFunctions() {
            homePage().goToLoginPage();
            loginPage().login("shiftedtech0000@gmail.com", "shiftedtec", false);
        }

        @Test()
        public void logoutCaseWithFunctions() {
            homePage().goToLoginPage();
            loginPage().login("shiftedtech0000@gmail.com", "shiftedtech", true);
            loginPage().logOut();
        }
    }
    ```

***

## 9. Setting up BDD/BDT (Behavior Driven Development/Testing) Framework

***

## 9 (cont). Using the BDD-POM Hybrid Framework

***

## 10. Creating API Tests with Rest Assured

***

## 11. Setting up and Using Karate

***

## 12. Creating Database Tests with JDBC

***

## 13. Creating Tests

***

## 14. Setting up Selenium Grid

***

## 14 (cont). Setting up Tests to run on Browser Stack

***

## 15. Setting up Jenkins and Using Jenkins

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
