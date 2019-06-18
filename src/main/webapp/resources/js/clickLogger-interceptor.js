function getWidth() {
  return Math.max(
    document.body.scrollWidth,
    document.documentElement.scrollWidth,
    document.body.offsetWidth,
    document.documentElement.offsetWidth,
    document.documentElement.clientWidth
  );
}

function getHeight() {
  return Math.max(
    document.body.scrollHeight,
    document.documentElement.scrollHeight,
    document.body.offsetHeight,
    document.documentElement.offsetHeight,
    document.documentElement.clientHeight
  );
}

window.onclick = function(evt) { 
	var source = evt.srcElement || evt.originalTarget
    console.log(source);
	relativeX = evt.clientX * 100 / getWidth();
	relativeY = evt.clientY * 100 / getHeight();
	console.log(evt.clientX);
	console.log(evt.clientY);
	
	console.log(relativeX);
	console.log(relativeY);
	
	console.log(getWidth() * relativeX / 100);
	console.log(getHeight() * relativeY / 100 );
}