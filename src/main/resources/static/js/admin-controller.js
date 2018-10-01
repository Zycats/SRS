srsApp.controller("controller", function($scope, $http, $interval){
	$scope.reportType = "SRS";
	
	$scope.setReportType = function(type)
	{
		$scope.reportType = type;
	}
	
	$scope.getReports = function(){
		
		console.log($scope.fromDate, $scope.tillDate);
		
		if ($scope.fromDate === undefined)
		{
			alert("Please select From Date.");
		}
		else if ($scope.tillDate === undefined)
		{
			alert("Please select Till Date.");
		}
		else
		{
			//$scope.fromDate = $scope.fromDate.getFullYear() + "-" + (($scope.fromDate.getMonth()+1)<10?) + "-" + $scope.fromDate.getDate();
			//$scope.tillDate = $scope.tillDate.getFullYear() + "-" + ($scope.tillDate.getMonth()+1) + "-" + $scope.tillDate.getDate();
			
			console.log($scope.fromDate, $scope.tillDate);
			
			$http({
				url: "/rest/report/get/all",
				method: "POST",
				data: {
					"fromDate": $scope.fromDate,
					"toDate": $scope.tillDate
				}
			}).then(function(response){
				console.log(response);
				
				google.charts.load("current", {packages:["timeline"]});
				google.charts.setOnLoadCallback(drawChart);
				function drawChart() {

				var container = document.getElementById('gantt_chart');
				var chart = new google.visualization.Timeline(container);
				var dataTable = new google.visualization.DataTable();
				var rows = [];
				dataTable.addColumn({ type: 'string', id: 'SRS Id' });
				dataTable.addColumn({ type: 'string', id: 'Status' });
				dataTable.addColumn({ type: 'date', id: 'From' });
				dataTable.addColumn({ type: 'date', id: 'To' });
				
				response.data.forEach(function(d){
					rows.push([d.ticket.id.toString(), d.statusTo, new Date(d.statusFromTime), new Date(d.statusToTime)]);
				})
					
				    dataTable.addRows(rows);

				    chart.draw(dataTable);
				}
				
			})
		}
	}
})