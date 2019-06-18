var httpLogger = new Object();

httpLogger.doLoggedHttpRequest = function(url, method) {
	 var req = new XMLHttpRequest();
	 req.loggingData = new LoggingData(url, "LOG_REQUESTS");
	 req.onreadystatechange = function() {
		 collectLoggingData(req);
	 }
	 req.open(method, url);
	 req.send();
};

function collectLoggingData(req) {
	req.loggingData.results["readyStateDate_" + req.readyState] = new Date().toISOString();
	if (req.readyState == 4) { 
		req.loggingData.results.statusCode = req.status;
		req.loggingData.results.browser = encodeURIComponent(navigator.appCodeName) + " " + encodeURIComponent(navigator.appVersion);
		req.loggingData.results.responseHeaders = req.getAllResponseHeaders();
		doLog(req.loggingData);
	}
 }
	
function doLog(loggingData) {
	var req = new XMLHttpRequest();
	req.calledByDoLog = true;
	req.open("POST", "JsonLogServlet", true);
	req.send(JSON.stringify(loggingData));
}

class LoggingData {
	constructor(url, tableName) {
		this.logger = new Object();
		this.results = new Object();
		
		this.logger.tableName = tableName;
		this.results.url = url;
	}
}