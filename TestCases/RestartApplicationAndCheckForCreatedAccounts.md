---
Test Case ID: B_1
Test Name: Restart And Check For Accounts
Test Designed By: Josef Vetrovsky
Brief Description: Successfully save and load accounts after restart of the application
Pre-conditions: Have the application already running and be successfully connected
Dependencies And Requirements:
---

| Step | Test Steps                    | Test Data | Expected Result                                                                                                                  | Notes |
|------|-------------------------------|-----------|----------------------------------------------------------------------------------------------------------------------------------|-------|
| 1    | Type AC and enter a few times |           | Everytime, the application should return AC with the created account number and bank code                                        |       |
| 2    | Type BN                       |           | The application should return BN the number of clients, which should be equal to the number of times you executed the AC command |       |
| 3    | Restart the application       |           |                                                                                                                                  |       |
| 4    | Type BN                       |           | The application should return the same number as the last executed BN                                                            |       |
