# Cucumber-Java-Selenium

This is a simple setup for Cucumber with Selenium using Java.

## Project Requirements / Recommendations

- GitHub Account
- IDE - IntelliJ IDEA
- Defaults to run using Google Chrome, please ensure this is installed
- Maven
- Java 21

## Setup Instructions

Open a command/terminal window at the same level as this readme and run:

```sh
mvn clean install
```

## Run the tests

The runner is available [here](src/test/java/io/cucumber/RunCucumberTest.java)

A basic HTML report will be available [here](target/cucumber-report/cucumber.html) after a test run.

### Running Tests by Tags

You can run specific tests by filtering with Cucumber tags.

Run tests with a single tag:

```sh
mvn clean test "-Dcucumber.filter.tags=@login"
```

Run tests with multiple tags (using OR condition):

```sh
mvn clean test "-Dcucumber.filter.tags=@regression or @login"
```

Run tests with multiple tags (using AND condition):

```sh
mvn clean test "-Dcucumber.filter.tags=@regression and @smoke"
```

## Feature Files

The feature files live [here](src/test/resources/io/cucumber/features/theInternet.feature)

## Required Task

```
Please clone this repo (do NOT fork it), and follow good version control practices.

- An initial commit after cloning the repo, before making any changes.
- Any additional commits you want as you progress through the task.
```

There are 3 titled scenarios in the [feature file](src/test/resources/io/cucumber/features/theInternet.feature).

Please complete the scenarios to cover the requirements. Feel free to rework/create step definitions, rework/create Page Object Models as you deem appropriate.

Once you have completed the task, please push your work to your own GitHub repo and share the link to the project with us for review prior to the interview. Please expect some questions/technical discussion during the interview, relating to your implementation of the given task.

## Scenario Requirements

### 1. Homepage has a list of links to Expected examples

Ensure the displayed list of listed examples is as expected. The expected list should contain:

```
A/B Testing
Add/Remove Elements
Basic Auth (user and pass: admin)
Broken Images
Challenging DOM
Context Menu
Digest Authentication (user and pass: admin)
Disappearing Elements
Drag and Drop
Dropdown
Dynamic Content
Dynamic Controls
Dynamic Loading
Entry Ad
Exit Intent
File Download
File Upload
Floating Menu
Forgot Password
Form Authentication
Geolocation
Horizontal Slider
Infinite Scroll
Inputs
JavaScript Alerts
JavaScript onload event error
Key Presses
Large & Deep DOM
Multiple Windows
Nested Frames
Notification Messages
Redirect Link
Secure File Download
Shadow DOM
Shifting Content
Slow Resources
Sortable Data Tables
Status Codes
Typos
WYSIWYG Editor
```

### 2. Basic Auth allows validated access

Test that navigation behavior works as expected from the 'Homescreen' to 'Basic Auth'. Username and password are both given on the page.

### 3. Sortable Data Tables - Example 1 displays the expected 4 results

Ensure the Example 1 table displays the following results:

```
Last Name   First Name  Email                   Due     Web Site
Smith       John        jsmith@gmail.com       $50.00  http://www.jsmith.com
Bach        Frank       fbach@yahoo.com        $51.00  http://www.frank.com
Doe         Jason       jdoe@hotmail.com       $100.00 http://www.jdoe.com
Conway      Tim         tconway@earthlink.net  $50.00  http://www.timconway.com
```

---

### Notes
- Ensure you use good version control practices with meaningful commit messages.
- Update the README file if additional setup steps are needed.
- Validate that all tests pass before pushing changes to your repository.

---

This project follows industry best practices for test automation using Cucumber with Selenium. 🚀
