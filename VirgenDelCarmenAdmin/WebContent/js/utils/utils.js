function Utils() {}

Utils.padding = function (padding,text) {
	if (padding && padding.length) {
		if (text) {return String(padding+text).slice(-padding.length);}
		else {return padding;}
	} else {return text;}
};
Utils.endsWith = function (string, suffix) {
	return (string.indexOf(suffix, string.length - suffix.length) !== -1);
};
Utils.contains = function (containerString, containedString) {
	return (containerString.indexOf(containedString) != -1);
};

Utils.enable  = function (id) {jQuery('#'+id).removeAttr('disabled');};
Utils.disable = function (id) {jQuery('#'+id).attr('disabled','disabled');};

Utils.toNumber = function (value) {
	if (isNumber(value)) {
		if (typeof value == number) { return value; }
		else { return Integer.parseInt(number,10); }
	} else { return NaN;}
};
Utils.isNumber = function (value) { return ! isNaN (value-0); };
Utils.radioButtons = function (name) {
	jQuery('input:radio[name='+name+']').livequery('click',
		function() { jQuery('#'+name).val( $('input:radio[name='+name+']:checked').val() ).change(); }
	);
};
Utils.getId = function (element,idSuffix) {
	var id = jQuery(element).attr('id');
	if (idSuffix) { id = id.substring(0,id.indexOf(idSuffix)); }
	return id;
}
Utils.modularAddition = function (number,lowerLimit,upperLimit,summand) {
	number = parseInt(number);
	number += summand;
	while (number > upperLimit) {
		number = (lowerLimit + number - upperLimit - 1);
	}
	while (number < lowerLimit) {
		number = (upperLimit - lowerLimit - number - 1);
	}
	return number;
};
Utils.onkeydownIncrementAndWrapAroundNumber = function (event,number,lowerLimit,upperLimit) {
	var key = (event.keyCode ? event.keyCode : event.which);
	var summand;
	switch(key) {
		case jQuery.ui.keyCode.UP:   summand =  1; break;
		case jQuery.ui.keyCode.DOWN: summand = -1; break;
		default: summand = 0; break;
	}
	return Utils.modularAddition(number,lowerLimit,upperLimit,summand);
};
Utils.serializeArray = function (array) {
	if (!array || !array.length) {return '';}
	else {
		var string = '', isFirst = true;
		for (var i = 0; i < array.length; ++i) {
			if (isFirst) {isFirst = false;} else {string+=',';}
			string += array[i];
		}
		return string;
	}
};
