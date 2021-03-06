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
	
	$("#menu-item-1").click(function(e){
		e.stopPropagation();
		$("#container-1").css("left", "0");
		$("#container-2").css("left", "100%");
		$("#container-3").css("left", "100%");
		$("#container-4").css("left", "100%");
	})
	
	$("#menu-item-2").click(function(e){
		e.stopPropagation();
		$("#container-1").css("left", "-100%");
		$("#container-2").css("left", "0");
		$("#container-3").css("left", "100%");
		$("#container-4").css("left", "100%");
	})
	
	$("#menu-item-3").click(function(e){
		e.stopPropagation();
		$("#container-1").css("left", "-100%");
		$("#container-2").css("left", "-100%");
		$("#container-3").css("left", "0");
		$("#container-4").css("left", "100%");
	})
	
	$("#menu-item-4").click(function(e){
		e.stopPropagation();
		$("#container-1").css("left", "-100%");
		$("#container-2").css("left", "-100%");
		$("#container-3").css("left", "-100%");
		$("#container-4").css("left", "0");
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
				!($(".searchManagerInput").is(":focus")) &&
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
	
	$(".dropdown-item").click(function(){
		$(".dropdown-menu").removeClass("show");
	})
})