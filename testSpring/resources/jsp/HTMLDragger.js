/*
 * Author: Jegatheesh
 * Purpose: HTML Dragging Tool
 * Project Started: 2017-06-03
 */

var lastElement = "";
var currentElementId = "";
var dropStatus = false;
function dragStart(event) {
	event.dataTransfer.setData("Text", event.target.id);
	document.getElementById("status").innerHTML = "Dragging "
			+ $(event.target).text();
	lastElement = $(event.target).text();
	dropStatus = false;
}

function dragEnd(event) {
	if (dropStatus)
		document.getElementById("status").innerHTML = lastElement + " Added.";
	else
		document.getElementById("status").innerHTML = lastElement + " Dropped.";
}

function allowDrop(event) {
	event.preventDefault();
}

function drop(event) {
	event.preventDefault();
	dropStatus = true;
	var id = event.dataTransfer.getData("Text");
	var value = getValues(id);
	$(event.target).append("" + value + "");
}

function back() {
	if (currentIndex != 0) {
		document.getElementById("status").innerHTML = ""
				+ document.getElementById(myMap[currentIndex - 1]).title
				+ " Removed.";
		$("#" + myMap[currentIndex - 1]).unwrap();// remove parent
		$("#" + myMap[currentIndex - 1]).remove();
		currentIndex--;
	} else {
		document.getElementById("status").innerHTML = "Nothing...";
	}
}

var currentIndex = 0; // Last Added index
var myMap = {}; // Added History
function getValues(id) {
	var value = "";
	var latestId = document.getElementsByName(id).length + 1;
	currentElementId = id + latestId;
	myMap[currentIndex++] = currentElementId;
	value = '<span name="' + id + '">'; // Hidden value to know number
	// of items in that element
	value = value + '<span title="' + lastElement + '" id="' + currentElementId
			+ '">';
	switch (id) {
	case "text": {
		value = value + 'Text';
		break;
	}
	case "textField": {
		value = value + '<input type="text" />';
		break;
	}
	case "break": {
		value = value + '<br />';
		break;
	}
	case "tab": {
		value = value + '&nbsp;&nbsp;&nbsp;&nbsp;';
		break;
	}
	}
	value = value + '</span></span>';
	return value;
}

function copyToClipboard(element) {
	var aux = document.createElement("input");
	aux.setAttribute("value", $(element).html());
	document.body.appendChild(aux);
	aux.select();
	document.execCommand("copy");
	document.body.removeChild(aux);
	if ($(element).html() == "")
		alert("Nothing to Copy.");
}