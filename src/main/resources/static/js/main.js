$(document).ready(function() {
	var parentHeight = $("body").outerHeight();
	var navbarHeight = $(".navbar").outerHeight();
	var height = parentHeight - navbarHeight;
	var currentPos = 0;
	var scrollHeight;
	
	$(window).scroll(function(){
		console.log("window scrolled");
		$(".status-bubble").css({
			"visibility": "hidden"
		})	
	})

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
		$('.dropdown-menu.show').removeClass("show");
	})
	
	$(".sliderDiv").click(function(e){
		e.stopPropagation();
		$('.dropdown-menu.show').removeClass("show");
	})
	
	$(".btnLink").click(function(){
		console.log("link clicked", this);
	})

	$(".dropdown-toggle").click(function(e){
		e.stopPropagation();
		
		console.log(this);
		
		var dropDown = $(this).next();
		
		if ($(this).next().is(":hidden"))
		{
			dropDown.addClass("show");
		}
		else
		{
			dropDown.removeClass("show");
		}
	})

	$(document).keydown(function(e) {
		
		e.stopPropagation();
		
		if (!($(".commentText").is(":focus")) && 
				!($("#comment").is(":focus")) && 
				!($("#emp_seatno_input").is(":focus")) &&
				!($("#emp_extno_input").is(":focus")))
		{
			$('.search input[type=text]').focus();
		}
		
		console.log("key pressed");
		
		if(e.keyCode == 27) {
			console.log("escape pressed!!!");
			$(".slider").css({"left": "100%"});
			$("body").css("overflow", "auto");
	  	}
		else if (e.keyCode == 9){
			return;
		}
	});
	
	$('.slider').click(function(e){
		e.stopPropagation();
	});
	
	$("#screenShot").click(function(e){
		e.stopPropagation();
		var recentCard = document.getElementById("recentSrsContainer");
		html2canvas(recentCard).then(function(canvas) {
		    document.body.appendChild(canvas);
		});
	})
})