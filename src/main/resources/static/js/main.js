$(document).ready(function() {
	var parentHeight = $("body").outerHeight();
	var navbarHeight = $(".navbar").outerHeight();
	var height = parentHeight - navbarHeight;
	var currentPos = 0;
	var scrollHeight;

	$(".inner-container").css({
		"min-height" : height + "px"
	})

	$("#raise").click(function() {
		if (currentPos != 1) {
			if (currentPos == 0) {
				$("#dashboard").css({
					marginLeft : "-100%"
				});
			} else if (currentPos == 2) {
				$("#raiseContainer").css({
					marginLeft : "0"
				});
			}
			currentPos = 1;
		}
	})
	
	$("#new-issues").click(function() {
		if (currentPos != 1) {
			if (currentPos == 0) {
				$("#dashboard").css({
					marginLeft : "-100%"
				});
			} else if (currentPos == 2) {
				$("#newIssuesContainer").css({
					marginLeft : "0"
				});
			}
			currentPos = 1;
		}
	})

	$("#history").click(function() {
		if (currentPos != 2) {
			if (currentPos == 0) {
				$("#dashboard").css({
					marginLeft : "-100%"
				});
				$("#raiseContainer").css({
					marginLeft : "-100%"
				});
			} else if (currentPos == 1) {
				$("#raiseContainer").css({
					marginLeft : "-100%"
				});
			}
			currentPos = 2;
		}
	})
	
	$("#all-issues-history").click(function() {
		if (currentPos != 2) {
			if (currentPos == 0) {
				$("#dashboard").css({
					marginLeft : "-100%"
				});
				$("#newIssuesContainer").css({
					marginLeft : "-100%"
				});
			} else if (currentPos == 1) {
				$("#newIssuesContainer").css({
					marginLeft : "-100%"
				});
			}
			currentPos = 2;
		}
	})

	$("#home").click(function() {
		if (currentPos != 0) {
			$("#dashboard").css({
				marginLeft : "0"
			});
			$("#raiseContainer").css({
				marginLeft : "0"
			});
			currentPos = 0;
		}
	})
	
	$("#home-exec").click(function() {
		if (currentPos != 0) {
			$("#dashboard").css({
				marginLeft : "0"
			});
			$("#newIssuesContainer").css({
				marginLeft : "0"
			});
			currentPos = 0;
		}
	})
	
	$(".tokenLink").click(function(){
		
		if ($(window).outerWidth() < 576)
		{
			$(".slider").css({
				"left": "0"
			});
		}
		else
		{
			$(".slider").css({
				"left": "30%",
			});
		}
		$("body").css("overflow", "hidden");
	})
	
	$(".back-btn").click(function(){
		$(".slider").css({"left": "100%"});
		$("body").css("overflow", "auto");
	})
	
	$(".navbar-brand").click(function(){
		$("#progressModal").modal("show");
	})
	
	$(".main-container").click(function(e){
		e.stopPropagation();
		console.log("clicked on main container");
		$(".slider").css({"left": "100%"});
		$("body").css("overflow", "auto");
	})
	
	$(".sliderDiv").click(function(e){
		e.stopPropagation();
	})
	
	$(".btnLink").click(function(){
		console.log("link clicked", this);
	})
	
	$(".closeButton").mouseover(function(){
		console.log($(this).offset());
		var offset = $(this).offset(); 
		var width = $(this).outerWidth();
		$(".status-bubble").css({
			"left": (offset.left - 75 + (width/2)) + "px",
			"top": (offset.top - 100) + "px"
		})
		$(".status-bubble").stop(true, true).fadeIn();
	})
	
	$(".closeButton").mouseleave(function(){
		$(".status-bubble").stop(true, true).fadeOut();
	})
	
	$("#catButton").click(function(e){
		e.stopPropagation();
		
		console.log("button clicked!!!");
		
		if ($('#catDropdownMenu').is(":hidden")){
		    $('#catDropdownMenu').addClass('show');
		  }
		else
		{
			$('.dropdown-menu.show').removeClass("show");
		}
	})
	
	$("#subCatButton").click(function(e){
		e.stopPropagation();
		
		console.log("button clicked!!!");
		
		if ($('#subCatDropdownMenu').is(":hidden")){
		    $('#subCatDropdownMenu').addClass('show');
		  }
		else
		{
			$('.dropdown-menu.show').removeClass("show");
		}
	})
	
	$("#osTypeButton").click(function(e){
		e.stopPropagation();
		
		console.log("button clicked!!!");
		
		if ($('#osDropDownMenu').is(":hidden")){
		    $('#osDropDownMenu').addClass('show');
		  }
		else
		{
			$('.dropdown-menu.show').removeClass("show");
		}
	})
})