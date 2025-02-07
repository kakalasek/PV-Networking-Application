---
Test Case ID: C_2
Test Name: Invalid Amount Of Money
Test Designed By: Josef Vetrovsky
Brief Description: The program returns an error code after trying to use an invalid number of money
Pre-conditions: Have the application already running and be successfully connected
Dependencies And Requirements:
---

| Step | Test Steps                                                           | Test Data | Expected Result                                                                                | Notes |
|------|----------------------------------------------------------------------|-----------|------------------------------------------------------------------------------------------------|-------|
| 1    | Type AC and enter                                                    |           | The application should return AC with the created account number and bank code                 |       |
| 2    | Type AD &lt;account_number&gt;/&lt;ip&gt; &lt;hihihoho&gt; and enter |           | The application should return an error code stating that number of money to deposit is invalid |       |
