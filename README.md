# Logging Service
> Intercept all XHR Request on a Application server and log them to a log file.  
The data in the logfile is an chain of SQL **INSERT INTO** queries


## Installation

#### Requirements
- Java 8 (JDK)
- Apache Maven
- Web Browser


#### Build with maven
  - Build using Maven
```mvn clean install```
  - Build and run embedded Tomcat (developer)
```mvn clean install tomcat7:run```

## Start the application
  - Deploy as WAR
```target/dev-tools##1.0.war```

#### Use the application

- [http://localhost:8080/logging-service](http://localhost:8080/logging-service/?url=http://localhost:8080/logging-service&interval=1000&loops=10)
  - Mask to create traffic
- [LogServlet](http://localhost:8080/logging-service/LogServlet)
  - Displays all logfiles under the Apache Tomcats logs directory
- [JSF Ajax Interception](http://localhost:8080/logging-service/jsfInterception.jsf)
  - Dummy application in which all XHR requests are intercepted
- Add JS snippets to other applications to log their requests
  ```html
  <script src="/logging-service/resources/js/httpLogger.js"></script>
  <script src="/logging-service/resources/js/httpLogger-interceptor.js"></script>
  ```
## Analyze data

#### create table
[JS readyStates](https://developer.mozilla.org/en-US/docs/Web/API/XMLHttpRequest/readyState)
```sql
CREATE TABLE LOG_REQUESTS (
    id int PRIMARY KEY AUTO_INCREMENT, 
    readyStateDate_1 DATETIME(3),
    readyStateDate_2 DATETIME(3),
    readyStateDate_3 DATETIME(3),
    readyStateDate_4 DATETIME(3),
    statusCode INT,
    url VARCHAR(255),
    browser VARCHAR(255),
    requestBody TEXT,
    responseHeaders TEXT
);
```

#### import (MySQL)
```sh
curl "http://192.168.0.45:8080/logging-service/LogServlet?logFile=MyLogFile2018-06-03.log" > /tmp/dump.sql
mysql -u root -D LOGGING_DATABASE -p < /tmp/dump.sql
```

#### select example (MySQL)
```sql
SELECT 
	url, 
	readyStateDate_1 as start_time, 
	readyStateDate_2 - readyStateDate_1 as time_till_open_called,
	readyStateDate_3 - readyStateDate_2 as time_till_headers_available,
	readyStateDate_4 - readyStateDate_3 as time_till_responsetext_loaded,
	readyStateDate_4 as end_time,
	readyStateDate_4 - readyStateDate_1 as total_time
from 
	LOG_REQUESTS
ORDER BY 
	total_time DESC
```