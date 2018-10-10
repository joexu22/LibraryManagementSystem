lmsApp.controller("borrowerController", function($scope, $http, lmsService,
		lmsConstants, lmsBorrowerService, $window, $location, Pagination) {

	$scope.testingString = "testing";
	$scope.firstName = "Jane";
	$scope.lastName= "Doe";	
	
	lmsService.getBorrowers(
			lmsConstants.LMS_HOST + lmsConstants.READ_ALL_BORROWERS).then(
			function(result) {
				$scope.borrowers = result;
			});

	$scope.enterBorrowerId = function(borrowerId) {
		$scope.borrowerId = borrowerId;
		lmsBorrowerService.setCardNumber(borrowerId);
		lmsBorrowerService.setString("Testing");
	}

	$scope.getCurrentBorrower = function() {
		$scope.currentBorrower = lmsBorrowerService.getBorrower();
	}

	$scope.getCurrentBorrowerName = function() {
		$scope.Name = lmsBorrowerService.getName();
	}
	
	/*
	if ($location.path() === '/viewauthors') {
		lmsService.getBorrowerByID(
				lmsConstants.LMS_HOST + lmsConstants.READ_BORROWER_BY_ID
						+ $scope.borrowerID).then(function(result) {
			//$scope.currentBorrower = result;
			$scope.lmsBorrowerService.borrowerObject = result;
		});
	}
	*/

	$scope.stringValue = sharedProperties.getString;
	$scope.objectValue = sharedProperties.getObject();
	$scope.setString = function(newValue) {
		$scope.objectValue.data = newValue;
		sharedProperties.setString(newValue);
	}
});