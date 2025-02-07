---
Test Case ID: C_1
Test Name: Invalid Ip Address
Test Designed By: Josef Vetrovsky
Brief Description: The program returns an error code after trying to use an invalid ip address
Pre-conditions: Have the application already running and be successfully connected
Dependencies And Requirements:
---

| Step | Test Steps                                                      | Test Data | Expected Result                                                                            | Notes |
|------|-----------------------------------------------------------------|-----------|--------------------------------------------------------------------------------------------|-------|
| 1    | Type AC and enter                                               |           | The application should return AC with the created account number and bank code             |       |
| 2    | Type AB &lt;account_number&gt;/&lt;256.667.128.12&gt; and enter |           | The application should return an error code stating that the ip address is in fact invalid |       |
