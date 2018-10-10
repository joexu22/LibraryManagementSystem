lmsApp.config(["$routeProvider", function($routeProvider){
	return $routeProvider.when("/", {
		redirectTo: "/home"
	}).when("/home", {
		templateUrl: "home.html"
	}).when("/admin", {
		templateUrl: "admin.html"
	}).when("/crud", {
		templateUrl: "crud.html"
	}).when("/author", {
		templateUrl: "author.html"
	}).when("/viewauthors", {
		templateUrl: "viewAuthors.html"
	}).when("/addauthor", {
		templateUrl: "addauthor.html"
	}).when("/borrower", {
		templateUrl: "borrower.html"
	}).when("/borrowerOptions", {
		templateUrl: "borrowerOptions.html"
	}).when("/librarian", {
		templateUrl: "librarian.html"
	})
}])