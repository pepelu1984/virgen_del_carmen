Utils.DateTime = function () {}
Utils.DateTime.readDateTime = function (value) { return new Date(string); };
Utils.DateTime.readOnlyDate = function (value) {
	var dateTime = new Date(value);
	return new Date(dateTime.getFullYear(),dateTime.getMonth(),dateTime.getDate(),0,0,0,0);
};
