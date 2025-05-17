# Automation Framework

This is a test automation framework built with Java and Selenium WebDriver.  
It is designed to perform end-to-end UI testing for the [SauceDemo website](https://www.saucedemo.com/).

## Technologies Used

- **Selenium WebDriver** – For browser automation
- **TestNG** – For test execution and assertions
- **Allure Report** – To generate detailed, interactive reports
- **ExtentReports** – To generate HTML reports with test case summaries

## Project Structure

- Follows the **Page Object Model (POM)** design pattern for maintainability
- Supports **Data-Driven Testing** using JSON files
- Includes a `utils` package for reusable components such as:
  - Browser management
  - Logging
  - Property and test data loading

## How to Run Tests Locally

1. Open the project in IntelliJ or any Java IDE.
2. Go to the main test class:
   ```
   SauceDemoEndToEndTest.java
   ```
3. In the file `src/main/resources/configuration.properties`, make sure:
   ```
   execution.type=Local
   ```
4. Run the test class directly, or run a TestNG XML suite.

## Reports

### Allure Report

To view the Allure report after test execution, run:

```bash
mvn allure:serve
```

This will open the report in your browser.

### Extent Report

An HTML report is automatically generated in the `/reports` folder.  
The file name includes the current date and time, such as:

```
ExtentReport_2025-05-17_14-20-45.html
```

Open the file in a browser to view the test results.

## Notes

- The framework is modular, easy to maintain, and scalable.
- It provides full visibility of test results through both Allure and Extent reports.
