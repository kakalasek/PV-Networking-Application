---
title: PV-Networking-Application
Author: Josef Vetrovsky
Date: 7. 2. 2025
---

Context
===

Documentation
===

Setup
---

First clone the repository from GitHub, or download the whole zip file.             
After that you will need to copy the config.properties file into the same directory as the jar file. The jar file 
is by default located inside the ./out/artifacts/PV_Networking_Application_jar directory.               
There are some example data inside, do not pay too much attention to them.              
| variable | description | extra |                  
| -------- | ----------- | ----- |                  
| TIMEOUT  | Sets timeout for user inaction in seconds. The client socket will be closed after the specified time if no more commands are sent | hello |                  
| PORT     | The port on which the application will start on | hello |                
| IP       | The IPv4 address the application will start on | If left blank, the program will dynamically start your program on one of your NICs |                  

How to use
---

Materials
---

Baeldung - A Guide To Java Sockets - https://www.baeldung.com/a-guide-to-java-sockets           
Medium (Dylan Smith) - Comprehensive Understanding of Shutdown Hook Mechanism - https://medium.com/javarevisited/java-concurrency-8-comprehensive-understanding-of-shutdown-hook-mechanism-2ce9906f30cb             
Baeldung - JVM Shutdown Hooks - https://www.baeldung.com/jvm-shutdown-hooks                 
DataSet Blog - Log4j2 - https://www.dataset.com/blog/maven-log4j2-project/          


