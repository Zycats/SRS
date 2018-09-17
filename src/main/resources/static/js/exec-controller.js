srsApp.controller("dashboardController", function($scope, $http, $interval){
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
		var target = $event.currentTarget;
		
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
		console.log($scope.recentIssuesData);
		
		
	})
	
	$http({
		url: "/rest/ticket/get/all",
		method: "GET"
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
	
	
	$scope.changeCategory = function($event){
		var target = $event.currentTarget;
		
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
				url: "/rest/executive/get/category/engId",
				method: "POST",
				data: {
					"category_id": $scope.selectedCategory.id
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
				url: "/rest/executive/get/sub_category/engId",
				method: "POST",
				data: {
					"sub_category_id": $scope.subCategory.id
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
	
})

var stompClient = null;


function connect() {
    var socket = new SockJS('/srs-websocket');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        console.log('Connected: ' + frame);
        stompClient.subscribe('/topic/employee/observe', function (response) {
            console.log(JSON.parse(response));
        });
        stompClient.send("/app/employee/get");
    });
    
}

function sendReq(){
	
}

connect();
sendReq();
