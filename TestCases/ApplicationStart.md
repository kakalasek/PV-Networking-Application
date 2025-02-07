---
Test Case ID: T_1
Test Name: Application Start
Test Designed By: Josef Vetrovsky
Brief Description: Test the setup and start of the application
Pre-conditions:
Dependencies And Requirements: Java 21 or newer
---

| Step | Test Steps                                                               | Test Data | Expected Result                                                                           | Notes |
|------|--------------------------------------------------------------------------|-----------|-------------------------------------------------------------------------------------------|-------|
| 1    | Copy config.properties into out/artifacts/PV_Networking_Application_jar. |           |                                                                                           |       |
| 2    | Configure timeout, port and IP address inside config.properties          |           |                                                                                           |       |
| 3    | Start the application with 'java -jar PV-Networking-Application.jar'     |           | There should a prompt saying your application is running on a certain port and ip address |       |
