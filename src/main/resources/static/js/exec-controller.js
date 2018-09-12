srsApp.controller("dashboardController", function($scope, $http, $interval){
	$scope.issuesData = [];
	
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
				console.log("updated scope time : ", $scope.issuesData);
			})
	}
	
	$scope.showSlider = function(issue){
		$scope.issue = issue;
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
})

