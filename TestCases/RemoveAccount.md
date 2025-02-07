---
Test Case ID: A_6
Test Name: Remove Account
Test Designed By: Josef Vetrovsky
Brief Description: Successfully remove an account
Pre-conditions: Have the application already running and be successfully connected
Dependencies And Requirements:
---

| Step | Test Steps                                                  | Test Data | Expected Result                                                                | Notes |
|------|-------------------------------------------------------------|-----------|--------------------------------------------------------------------------------|-------|
| 1    | Type AC and enter                                           |           | The application should return AC with the created account number and bank code |       |
| 2    | Type AR &lt;created_account_number&gt;/&lt;ip&gt; and enter |           | The application should return AR                                               |       |
