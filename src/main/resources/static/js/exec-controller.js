srsApp.controller("dashboardController", function($scope, $http){
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
})