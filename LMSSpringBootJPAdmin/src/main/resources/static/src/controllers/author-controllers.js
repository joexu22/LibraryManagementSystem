lmsApp.controller("authorController", function($scope, $http, lmsService,
		lmsConstants, $window, $location, Pagination) {

	$scope.testingString = "TESTING";
	
	if ($location.path() === '/viewauthors') {
		lmsService
				.getAll(lmsConstants.LMS_HOST + lmsConstants.READ_ALL_AUTHORS)
				.then(function(result) {
					$scope.authors = result;
					$scope.pagination = Pagination.getNew(10);
					$scope.pagination.numPages = Math.ceil($scope.authors.length/$scope.pagination.perPage);
				})
	} else {
		lmsService.initAuthor(lmsConstants.LMS_HOST + lmsConstants.INIT_AUTHOR)
				.then(function(result) {
					$scope.newAuthor = result;
				});
		lmsService
		.getAll(lmsConstants.LMS_HOST + lmsConstants.READ_ALL_BOOKS)
		.then(function(result) {
			$scope.books = result;
			$scope.totalBooks = $scope.books.length;
		})
	}

	$scope.deleteAuthor = function(authorId) {
		lmsService.deleteAuthor(lmsConstants.LMS_HOST + lmsConstants.DELETE_AUTHOR + authorId).then(function() {
			$window.location = "#/viewauthors";
			lmsService.getAll(lmsConstants.LMS_HOST + lmsConstants.READ_ALL_AUTHORS).then(function(result) {
				$scope.authors = result;
			})
		})
	}
	
//	$scope.deleteAuthor = function(authorId) {
//		var delAuthor = {
//			authorId : authorId
//		}
//		lmsService.postObj(lmsConstants.LMS_HOST + lmsConstants.SAVE_AUTHOR,
//				delAuthor).then(
//				function() {
//					lmsService.getAll(
//							lmsConstants.LMS_HOST
//									+ lmsConstants.READ_ALL_AUTHORS).then(
//							function(data) {
//								$scope.authors = data;
//								$scope.pagination = Pagination.getNew(10);
//								$scope.pagination.numPages = Math.ceil($scope.authors.length/$scope.pagination.perPage);
//							})
//				})
//	}

	$scope.saveAuthor = function() {
		lmsService.postObj(lmsConstants.LMS_HOST + lmsConstants.SAVE_AUTHOR,
				$scope.author).then(function() {
			$window.location = "#/viewauthors";
			lmsService.getAll(lmsConstants.LMS_HOST + lmsConstants.READ_ALL_AUTHORS).then(function(result) {
				$scope.authors = result;
			})
		})
	}
	
	$scope.editAuthorModal = function(author){
		$scope.author = author;
		lmsService.getAll(lmsConstants.LMS_HOST + lmsConstants.READ_ALL_AUTHORS).then(function(result) {
			$scope.authors = result;
		})
	}
	
	$scope.searchAuthors = function(searchString){
		lmsService
		.getAll(lmsConstants.LMS_HOST + lmsConstants.READ_ALL_AUTHORS + searchString)
		.then(function(result) {
			$scope.authors = result;
			if (!searchString) {
				lmsService.getAll(lmsConstants.LMS_HOST + lmsConstants.READ_ALL_AUTHORS).then(function(result) {
					$scope.authors = result;
				})
			}
//			$scope.pagination = Pagination.getNew(10);
//			$scope.pagination.numPages = Math.ceil($scope.authors.length/$scope.pagination.perPage);
		})
	}
})