srsApp2.controller("dashboardController", function($scope, $http, $interval){
	
	$scope.loaderShow = false;
	
	$scope.issuesData = [];
	$http({
		url: "/rest/employee/get",
		method: "GET"
	})
	.then(function success(response){
		$scope.empData = response.data;
		if ($scope.empData.firstLogin)
		{
			$("#firstLoginModal").modal("show");
		}
		var role = ($scope.empData.role).toLowerCase();
		
		$http({
			url : "/rest/"+role+"/get/ticket-no",
			method : "POST"
			
		}).then(function success(response){
			
			$scope.issuesRaised = response.data;
		})
		
		$http({
			url : "/rest/"+role+"/get/status/ticket-no",
			method : "POST",
			data : {
				"status" : "ONHOLD"
			}	
			
		}).then(function success(response){
			
			$scope.issuesOnHold = response.data;
		})
		
		$http({
			url : "/rest/"+role+"/get/status/ticket-no",
			method : "POST",
			data : {
				"status" : "CLOSED"
			}	
			
		}).then(function success(response){
			
			$scope.issuesResolved = response.data;
		})
		
		$http({
			url : "/rest/"+role+"/get/status/ticket-no",
			method : "POST",
			data : {
				"status" : "WORKING"
			}	
			
		}).then(function success(response){
			
			$scope.issuesWorkingOn = response.data;
		})
		
		$http({
			url : "/rest/"+role+"/get/status/ticket-no",
			method : "POST",
			data : {
				"status" : "UNRESOLVABLE"
			}	
			
		}).then(function success(response){
			
			$scope.issuesUnresolvable = response.data;
		})	
		
	}, function error(response){
		console.log(response);
	})
	
	$http({
		url: "/rest/issue-category/get/all",
		method: "GET"
	})
	.then(function(response){
		$scope.categoryData = response.data;
	})
	
	$http({
		url: "/rest/location/get/all",
		method: "GET"
	})
	.then(function(response){
		$scope.locData = response.data;
	})
	
	$http({
		url: "/rest/options/get/status",
		method: "GET"
	})
	.then(function(response){
		$scope.statusData = response.data;
	})
	
	$scope.changeLocation = function($event){
		$event.stopPropagation();
		var target = $event.currentTarget;
		$('.dropdown-menu').removeClass("show");
		$("#locationButton").text(target.innerHTML);
		$("#deptButton").text("Select Your Department");
		
		for (loc of $scope.locData)
		{
			console.log(target.id, loc.id);
			if (loc.id == target.id)
			{
				$scope.locId = loc.id;
				$scope.departments = loc.departments;
				break;
			}
		}
	}
	
	$scope.changeDept = function($event){
		$event.stopPropagation();
		$('.dropdown-menu').removeClass("show");
		var target = $event.currentTarget;
		$("#deptButton").text(target.innerHTML);
		for (dept of $scope.departments)
		{
			if (dept.id == target.id)
			{
				$scope.department = dept;
				break;
			}
		}
	}
	

	$scope.updateEmp = function(){
		
		$(".alert-danger").css("display", "none");
		
		if ($scope.department === undefined)
		{
			$(".alert-danger").css("display", "block");
			$scope.genError = "Please select your department."
		}
		else if ($scope.empData.seatNo === null || $scope.empData.seatNo === undefined || $scope.empData.seatNo === "")
		{
			console.log($scope.empData.seatNo);
			$(".alert-danger").css("display", "block");
			$scope.genError = "Please enter your Seat Number. If not assigned enter 0."
		}
		else if ($scope.empData.extNo === null || $scope.empData.extNo === null || $scope.empData.extNo === "")
		{
			$(".alert-danger").css("display", "block");
			$scope.genError = "Please enter your Extension Number. If not assigned enter 0."
		}
		else
		{
			$scope.empData.department = $scope.department;
			$scope.empData.extNo = parseInt($scope.empData.extNo);
			$scope.empData.seatNo = parseInt($scope.empData.seatNo);
			$scope.empData.firstLogin = false;
			
			$http({
				url: "/rest/employee/post",
				method: "POST",
				data: $scope.empData,
				headers: {
		             'Content-Type': 'application/json; charset=utf-8'
		        }
			})
			.then(function(response){
				$(".alert-success").css("display", "block");
				$scope.successMsg = "Information updates successfully."
				$("#saveButton").attr("disabled", "true");
			})
		}
	}
	
	
	$http({
		url: "/rest/employee/get",
		method: "GET"
	})
	.then(function(response){
		var empData = response.data;
		console.log(empData);
		
		if (empData.firstLogin)
		{
			$(".modal").modal("show");
			$("#emp_username_input").val(empData.id);
			$("#emp_email_input").val(empData.email);
			$("#emp_dept_input").val(empData.id);
			$("#emp_username_input").val(empData.id);
			$("#emp_username_input").val(empData.id);
			$("#emp_username_input").val(empData.id);
		}
	})

	$http({
		url: "/rest/executive/get/engId",
		method: "GET"
	})
	.then(function(response){
		var issuesData = response.data;
		console.log(issuesData);
		
		issuesData.forEach(function(data){
			data.formattedTime = String(new Date(data.datetime));
		});
		
		$scope.recentIssuesData = issuesData;
	})
	
	$scope.getOpenSrs = function(){
		$http({
			url: "/rest/executive/get/status",
			method: "POST",
			data: {"status" : "OPEN"}
		})
		.then(function(response){
			var issuesData = response.data;
			console.log(issuesData);
			
			issuesData.forEach(function(data){
				data.formattedTime = String(new Date(data.datetime));
				
				if (data.engineer == null)
				{
					data.showAssigned = true;
				}
				else
				{
					data.showAssigned = false;
				}
			});

			$scope.issuesData = issuesData;
		})
	}
	
	$scope.getOpenSrs();
	
	$interval(getTimeAgo, 3000);
	
	function getTimeAgo(){
		if($scope.issuesData != null && $scope.issuesData.length > 0)
			$scope.issuesData.forEach(function(data){
				data['timeAgo'] = moment(new Date(data.datetime)).fromNow();
			})
			
		if($scope.recentIssuesData != null && $scope.recentIssuesData.length > 0)
			$scope.recentIssuesData.forEach(function(data){
				data['timeAgo'] = moment(new Date(data.datetime)).fromNow();
			})
	}
	
	getComments = function(issue){
		$http({
			url : "rest/comment/get/ticket/"+issue.id,
			method: "GET"
		}).then(function(response){

			response.data.forEach(function(data){
				data.formattedTime = String(new Date(data.datetime));
			});
			
			$scope.commentData = response.data;
		})
		
	}
	
	$scope.showSlider = function(issue){
		$scope.issue = issue;
		
		getComments(issue);
		
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
	}
	
	
	$scope.changeCommentStatus = function($event){
		var target = $event.currentTarget;
		$('.dropdown-menu').removeClass("show");
		$("#changeCommentStatusButton").text(target.innerHTML);
		
		$scope.commentStatus = target.innerHTML;
	}
	
	
	$scope.changeCategory = function($event){
		var target = $event.currentTarget;
		$('.dropdown-menu').removeClass("show");
		$("#changeIssueCategoryButton").text(target.innerHTML);
		$("#changeIssueSubCategoryButton").text("Select Sub-Category");
		
		for (cat of $scope.categoryData)
		{
			if (cat.id == target.id)
			{
				$scope.selectedCategory = cat;
				$scope.subCategoryData = cat.issueSubCategories;
				$scope.subCategory = null;
				break;
			}
		}
	}
	
	$scope.changeSubCategory = function($event){
		
		var target = $event.currentTarget;
		$('.dropdown-menu').removeClass("show");
		for (subCat of $scope.subCategoryData)
		{
			if (subCat.id == target.id)
			{
				$scope.subCategory = subCat;
				break;
			}	
		}
		
		$("#changeIssueSubCategoryButton").text(target.innerHTML);
	}
	
	$scope.filterData = function(){
		if($scope.selectedCategory == null)
			return;
		if($scope.subCategory == null){

			$http({
				url: "/rest/executive/get/category/status",
				method: "POST",
				data: {
					"category_id": $scope.selectedCategory.id,
					"status": "OPEN"
				}
			})
			.then(function(response){
				var issuesData = response.data;
				console.log(issuesData);
				
				issuesData.forEach(function(data){
					data.formattedTime = String(new Date(data.datetime));
				});
				
				$scope.issuesData = issuesData;
				console.log($scope.issuesData);
				
				
			})
		}
		else{

			$http({
				url: "/rest/executive/get/sub_category/status",
				method: "POST",
				data: {
					"sub_category_id": $scope.subCategory.id,
					"status": "OPEN"
				}
			})
			.then(function(response){
				var issuesData = response.data;
				console.log(issuesData);
				
				issuesData.forEach(function(data){
					data.formattedTime = String(new Date(data.datetime));
				});
				
				$scope.issuesData = issuesData;
				console.log($scope.issuesData);
				
				
			})
		}
	}
	
	$scope.comment = function(issue){
		console.log(issue, $scope.commentStatus, $scope.commentText);
		
		$http({
			url: "/rest/comment/add",
			method: "POST",
			data: {
				"ticket" : issue,
				"statusFrom" : issue.status,
				"statusTo" : $scope.commentStatus,
				"message" : $scope.commentText
			}
		}).then(function(data){
			console.log(data);
			getComments(issue);
			$scope.issuesData.forEach(function(i){
				if(i.id == issue.id){
					i.status = data.data.statusTo;
				}
			})
		});
	}
	
	function getTicket(id, issue){
		$scope.loaderShow = true;
		$http({
			url : "/rest/ticket/get?id=" + id,
			method: "GET"
		}).then(function success(response){
			console.log(response);
			if(response.data == null || response.data == undefined || response.data == ""){
				issue = response.data;
				alert("No ticket with Ticket Id : " + id);
			} else {
				response.data.formattedTime = String(new Date(response.data.datetime));
				$scope.showSlider(response.data);
			}
			$scope.loaderShow = false;
		}, function error(error){				
			alert("No ticket with Ticket Id : " + id);
			$scope.loaderShow = false;
		})
	}
	
	$scope.search = function(keyEvent){
		keyEvent.stopPropagation();
		if(keyEvent.which == 13){
			keyEvent.target.select();
			getTicket($scope.searchTicketId);
		}
	}

	var params = new window.URLSearchParams(window.location.search);
	console.log()
	if(params.get("srs") !== "" && params.get("srs") != undefined){
		getTicket(params.get("srs"));
	}
	
	
	function getPriority(p){
		switch(p){
		case "LOW" 		:	return 0;
		case "MEDIUM" 	:	return 1;
		case "HIGH" 	:	return 2;
		case "CRITICAL" : 	return 3;
		}
	}
	
	function getStatusInt(s){
		switch(s){
		case "OPEN" 		:	return 0;
		case "ONHOLD" 		:	return 1;
		case "WORKING" 		: 	return 2;
		case "UNRESOLVABLE" : 	return 3;
		case "CLOSED" 		:	return 4;
		}
	}
	
	$scope.IsSortByPriority = true;
	$scope.IsSortByTime = true;
	$scope.IsSortByStatus = true;
	
	$scope.sortByTime = function(sort_method = true){
		$scope.IsSortByTime = !sort_method;
		$scope.issuesData.sort(function(a, b){
			x = new Date(a.datetime);
			y = new Date(b.datetime);
			if(x > y){
				return sort_method ? -1 : 1;
			} else {
				return sort_method ? 1 : -1;
			}
		});
	}
	
	$scope.sortByPriority = function(sort_method = true){
		$scope.IsSortByPriority = !sort_method;
		
		$scope.issuesData.sort(function(a, b){
			x = a.subCategory.issuePriority;
			y = b.subCategory.issuePriority;
			
			
			if(getPriority(x) > getPriority(y)){
				return sort_method ? -1 : 1;
			} else {
				return sort_method ? 1 : -1;
			}
		});
	}
	
	$scope.sortByStatus = function(sort_method = true){
		$scope.IsSortByStatus = !sort_method;
		
		$scope.issuesData.sort(function(a, b){
			x = a.status;
			y = b.status;
			
			
			if(getStatusInt(x) < getStatusInt(y)){
				return sort_method ? -1 : 1;
			} else {
				return sort_method ? 1 : -1;
			}
		});
	}
	
	$scope.changeFromTo = function($event){
		
		var x = $event.target.getBoundingClientRect().x;
		var y = $event.target.getBoundingClientRect().y;
		var width= $event.target.getBoundingClientRect().width;
		var heigth = $event.target.getBoundingClientRect().height;
		
		var bubbleWidth = document.getElementById('bubble').getBoundingClientRect().width;
		var bubbleHeight = document.getElementById('bubble').getBoundingClientRect().height;
		
		console.log(x, y, width, bubbleWidth);
		
		
		$(".status-bubble").css({
			"top": (y - (bubbleHeight/4)) + "px",
			"right": ($(window).outerWidth()) - x + 20 + "px",
			"opacity": "1"
		})
		
		if ($event.target.id == "1")
		{
			$scope.from = "OPEN";
			$scope.to = "WORKING";
		}
		else
		{
			$scope.from = "WORKING";
			$scope.to = "URESOLVABLE";
		}
	}
	
	$scope.hideBubble = function(){
		$(".status-bubble").css({
			"opacity": "0"
		})	
	}
	
	$scope.assignEngineer = function($event, issue)
	{
		$event.stopPropagation();
		console.log("assigned clicked");
		issue.engineer = {
				"id": $scope.empData.id
		}
		issue.status = "WORKING";
		console.log("issue", issue);
		
		$http({
			url : "/rest/executive/set/assign",
			method: "POST",
			data: issue
		}).then(function success(response){
			console.log(response);
			issue.showAssigned = false;
			getTicket(issue.id, issue);
			$scope.getOpenSrs();
		}, function error(error){				
			console.log(error);
		})
	}
	
})
