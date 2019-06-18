// Dependency: httpLogger.js
(function(open) {
	XMLHttpRequest.prototype.open = function(method, url, async, user, pass) {

	if (!this.loggingData) {
		this.loggingData = new LoggingData(url, "LOG_REQUESTS");
	}
	this.addEventListener("readystatechange", function() {
			if (!this.calledByDoLog) {
				collectLoggingData(this);
			}
		}, false);
		
		open.call(this, method, url, async, user, pass);
	};
})(XMLHttpRequest.prototype.open);

(function(send) {
	XMLHttpRequest.prototype.send = function(body) {
		if (!this.loggingData) {
			this.loggingData = new LoggingData(url, "LOG_REQUESTS");
		}
		this.loggingData.results.requestBody = body;
		send.call(this, body);
	};
})(XMLHttpRequest.prototype.send);