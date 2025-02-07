---
Test Case ID: A_7
Test Name: Get Bank Total
Test Designed By: Josef Vetrovsky
Brief Description: Successfully the total money in the bank
Pre-conditions: Have the application already running and be successfully connected
Dependencies And Requirements:
---

| Step | Test Steps                                                                    | Test Data | Expected Result                                                                                                                  | Notes |
|------|-------------------------------------------------------------------------------|-----------|----------------------------------------------------------------------------------------------------------------------------------|-------|
| 1    | Type AC and enter                                                             |           | The application should return AC with the created account number and bank code                                                   |       |
| 2    | Type AD &lt;account_number&gt;/&lt;ip&gt; &lt;amount_to_deposit&gt; and enter |           | The application should return AD                                                                                                 |       |
| 3    | Type BA                                                                       |           | The application should return BA with the amount of money you deposited, which is also the total amount of money inside the bank |       |
