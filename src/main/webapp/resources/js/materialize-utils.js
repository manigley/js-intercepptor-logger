function errorMessage(message) {
	M.toast({
		html : message,
		classes : "red lighten-2"
	});
}

function infoMessage(message) {
	M.toast({
		html : message,
		classes : "teal lighten-2"
	});
}

function disableElements() {
	document.getElementById("progressBar").style = "display:block";
	changeInputStateByTagName("input", false);
	changeInputStateByTagName("button", false);
}

function enableElements() {
	document.getElementById("progressBar").style = "display:none";
	changeInputStateByTagName("input", true);
	changeInputStateByTagName("button", true);
}

function changeInputStateByTagName(tagName, enabled) {
	var inputs = document.getElementsByTagName(tagName);
	for (var i = 0; i < inputs.length; i++) {
		inputs[i].disabled = (enabled ? '' : 'disabled');
	}
}