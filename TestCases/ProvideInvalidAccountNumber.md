---
Test Case ID: C_2
Test Name: Invalid Account Number
Test Designed By: Josef Vetrovsky
Brief Description: The program returns an error code after trying to use an invalid account number
Pre-conditions: Have the application already running and be successfully connected
Dependencies And Requirements:
---

| Step | Test Steps                                 | Test Data | Expected Result                                                                        | Notes |
|------|--------------------------------------------|-----------|----------------------------------------------------------------------------------------|-------|
| 1    | Type AC and enter                          |           | The application should return AC with the created account number and bank code         |       |
| 2    | Type AB &lt;-2000&gt;/&lt;ip&gt; and enter |           | The application should return an error code stating that the account number is invalid |       |
