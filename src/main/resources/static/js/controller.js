srsApp.controller("userController", function($scope, $http){
	
	$scope.srsErrorShow = false;
	$scope.srsSuccessShow = false;
	
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
		else
		{
			$scope.loadRecentSRS();
			$scope.loadSrsData();
		}
	
	}, function error(response){
		
	})
	
// ----- ticket counts for employee-------------///	
		$http({
			url : "/rest/employee/get/ticket-no",
			method : "POST"
			
		}).then(function success(response){
			
			
			$scope.issuesRaised = response.data;
		})
				
		$http({
			url : "/rest/employee/get/status/ticket-no",
			method : "POST",
			data : {
				"status" : "CLOSED"
			}	
			
		}).then(function success(response){
			
			
			$scope.issuesResolved = response.data;
		})
		
		$http({
			url : "/rest/employee/get/status/ticket-no",
			method : "POST",
			data : {
				"status" : "UNRESOLVABLE"
			}	
			
		}).then(function success(response){
			
			
			$scope.issuesUnresolvable = response.data;
		})
		
//------------------------------------------------------------------------		
	
	
	
	
	$http({
		url: "/rest/location/get/all",
		method: "GET"
	})
	.then(function(response){
		$scope.locData = response.data;
	})
	
	$http({
		url: "/rest/issue-category/get/all",
		method: "GET"
	})
	.then(function(response){
		$scope.catData = response.data;
	})
	
	$scope.changeLocation = function($event){
		var target = $event.currentTarget;
		
		$("#locationButton").text(target.innerHTML);
		$("#deptButton").text("Select Your Department");
		
		for (loc of $scope.locData)
		{
			
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
	
	$scope.changeCategory = function($event){
		
		var target = $event.currentTarget;
		$('.dropdown-menu').removeClass("show");
		$("#catButton").text(target.innerHTML);
		$("#subCatButton").text("Select Sub-Category");
		
		for (cat of $scope.catData)
		{
			if (cat.id == target.id)
			{
				$scope.subCatData = cat.issueSubCategories;
				break;
			}
		}
	}
	
	$scope.changeSubCategory = function($event){
		var target = $event.currentTarget;
		$('.dropdown-menu').removeClass("show");
		for (subCat of $scope.subCatData)
		{
			if (subCat.id == target.id)
			{
				$scope.subCat = subCat;
				$scope.classification = subCat.issueType;
				$scope.priority = subCat.issuePriority;
				break;
			}	
		}
		
		$("#subCatButton").text(target.innerHTML);
	}
	
	$scope.changeOsType = function($event){
		var target = $event.currentTarget;
		$('.dropdown-menu').removeClass("show");
		$("#osTypeButton").text(target.innerHTML);
		$scope.osType = target.innerHTML;
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
		url: "rest/options/get/ostype",
		method: "GET"
	})
	.then(function(response){
		$scope.osTypes = response.data;
	})
	
	$scope.loadSrsData = function(){
		
		if ($scope.empData.location === undefined)
		{
			$http({
				url: "/rest/employee/get/employee-location",
				method: "GET"
			})
			.then(function success(response){
				var tmp = response.data.employee.id.split(".")[0];
				response.data.employee.displayName = tmp[0].toUpperCase() + tmp.substr(1);
				
				$scope.empData = response.data;
				
				
			}, function error(response){
				console.log(response);
				
			})
		}
	}
	
	$scope.submitSrs = function(){
		
		$scope.srsErrorShow = false;	
		
		if ($scope.subCat === undefined)
		{
			$scope.srsErrorShow = true;
			$scope.srsError = "Please select Issue category and Sub Category first."
		}
		else if ($scope.osType === undefined)
		{
			$scope.srsErrorShow = true;
			$scope.srsError = "Please select your OS type."
		}
		else if ($scope.description === undefined || $scope.description === "")
		{
			$scope.srsErrorShow = true;
			$scope.srsError = "Please enter description of your problem."
		}
		else
		{
			var srsData = {
				    "subCategory": {
			            "id": $scope.subCat.id
			        },
			    "osType": $scope.osType,
			    "description": $scope.description
			};
			
			$http({
				url: "/rest/ticket/add",
				method: "POST",
				data: srsData,
				headers: {
		             'Content-Type': 'application/json; charset=utf-8'
		        }
			})
			.then(function(response){
				$scope.srsSuccessShow = true;
				$scope.srsSuccess = "Your SRS has been raised successfully with #ID: " + response.data.id;
			})
		}
	}
	
	$scope.loadRecentSRS = function(){
		$http({
			url: "/rest/employee/get/empId",
			method: "GET"
		})
		.then(function(response){
			$scope.recentSrs = response.data;
			
			$scope.recentSrs.forEach(function(data){
				data.formattedTime = String(new Date(data.datetime));
				if (data.engineer == null)
				{
					data.engineer = {
						"id": "Not Assigned."
					}
				}
			});

			getTimeAgo();
		})
	}
	
	
	function getTimeAgo(){
		if($scope.recentSrs != null && $scope.recentSrs.length > 0)
			$scope.recentSrs.forEach(function(data){
				data['timeAgo'] = moment(new Date(data.datetime)).fromNow();
				
			})
	}
	
	$scope.showSlider = function(recent){
		
		$scope.issue = recent;
		
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