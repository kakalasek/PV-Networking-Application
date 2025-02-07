---
Test Case ID: A_3
Test Name: Deposit Money to Account
Test Designed By: Josef Vetrovsky
Brief Description: Successfully deposit an amount of money to an account
Pre-conditions: Have the application already running and be successfully connected
Dependencies And Requirements:
---

| Step | Test Steps                                                                    | Test Data | Expected Result                                                                | Notes |
|------|-------------------------------------------------------------------------------|-----------|--------------------------------------------------------------------------------|-------|
| 1    | Type AC and enter                                                             |           | The application should return AC with the created account number and bank code |       |
| 2    | Type AD &lt;account_number&gt;/&lt;ip&gt; &lt;amount_to_deposit&gt; and enter |           | The application should return AD                                               |       |
