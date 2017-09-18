// Trigger action when the contexmenu is about to be shown
$(document).bind("contextmenu", function(event) {

	// Avoid the real one
	// event.preventDefault();

});

// If the document is clicked somewhere
$(document).bind("mousedown", function(e) {

	// If the clicked element is not the menu
	if (!$(e.target).parents(".custom-source-menu").length > 0) {
		// Hide it
		$(".custom-source-menu").hide(100);
	}
	if (!$(e.target).parents(".custom-target-menu").length > 0) {
		// Hide it
		$(".custom-target-menu").hide(100);
	}
});

$(function() {

	$(".dropSource").contextmenu(function(event) {
		// Avoid the real one
		event.preventDefault();
	});

	$(".tags").contextmenu(function(event) {
		// Avoid the real one
		event.preventDefault();

		// Cursor Position
		pageX = event.pageX;
		pageY = event.pageY - 5;

		lastElement = $(event.target).text();
		currentElementId = event.target.id; // Copying the id value

		// Show contextmenu
		$(".custom-source-menu").finish().toggle(100).

		// In the right position (the mouse)
		css({
			top : pageY + "px",
			left : pageX + "px"
		});
	});

	$(".dropTarget").contextmenu(function(event) {
		// Avoid the real one
		event.preventDefault();

		// Show contextmenu
		$(".custom-target-menu").finish().toggle(100).

		// In the right position (the mouse)
		css({
			top : event.pageY + "px",
			left : event.pageX + "px"
		});
	});

	// If the menu element is clicked
	$(".custom-source-menu li").click(function() {

		var result = "";

		// This is the triggered action name
		switch ($(this).attr("data-action")) {

		// A case for each action. Your actions here
		case "add": {
			result = getValues(currentElementId);
			break;
		}
		}

		$('.dropTarget').append("" + result + "");
		document.getElementById("status").innerHTML = lastElement + " Added.";

		// Hide it AFTER the action was triggered
		$(".custom-source-menu").hide(100);
	});

	// If the menu element is clicked
	$(".custom-target-menu li").click(function() {

		// This is the triggered action name
		switch ($(this).attr("data-action")) {

		// A case for each action. Your actions here
		case "copy": {
			copyToClipboard(".dropTarget");
			break;
		}
		}

		// Hide it AFTER the action was triggered
		$(".custom-target-menu").hide(100);
	});
});