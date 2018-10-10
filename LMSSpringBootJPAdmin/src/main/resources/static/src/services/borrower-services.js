lmsApp.factory("lmsBorrowerService", function() {
	var stringValue = 'test string value';

	var objectValue = {
		data : 'test object value'
	};

	var borrowerObject = {
		cardNo : "testValue",
		name : "testValue",
		address : "testValue",
		phone : "testValue",
	};

	return {
		getString : function() {
			return stringValue;
		},
		setString : function(value) {
			stringValue = value;
		},
		getObject : function() {
			return objectValue;
		},
		getCardNumber : function() {
			return borrowerObject.cardNo;
		},
		setCardNumber : function(value) {
			borrowerObject.cardNo = value;
		},
		getName : function() {
			return borrowerObject.name;
		},
		setName : function(value) {
			borrowerObject.name = value;
		},
		getAddress : function() {
			return borrowerObject.address;
		},
		setAddress : function(value) {
			borrowerObject.address = value;
		},
		getPhone : function() {
			return borrowerObject.phone;
		},
		setPhone : function(value) {
			borrowerObject.phone = value;
		},
		getBorrower : function() {
			return borrowerObject;
		}
	}
});