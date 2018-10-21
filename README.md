# Library Management System

Library Management System | Java | 3-Tier Architecture

* Business Logic
* Back-end
* Front-end

Business Logic
* The Business Logic is included in the CLI Folder

Back End
* The backend is slit into 3 servers
* Admin - Port:8091
	* The Admin should be able to do everything the Librarian or the Borrower can do
	* Also has greater access to the database/ have better access to more APIs
* Librarian - Port:8092
	* Able to change the name of the library
	* Able to change the how many books are in stock at a time
* Borrower - Port:8093
	* Able to checkout/return books

Front End
* Basic AngularJS Frontend

Incorporates AWS
* S3
* EC2
* ect/etc

TODO:
* Need To Test Triggers
* Another Trigger
