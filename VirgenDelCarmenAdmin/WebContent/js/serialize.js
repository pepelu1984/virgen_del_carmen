jQuery.fn.jserialize = function () {
	var serialized = this.serialize();
	$(this).find(':checkbox').each (function() {
		var tofind    = $(this).attr('name') + "=" + $(this).val();
		var toreplace = $(this).attr('name') + "=" + (this.checked ? 'true' : 'false');
		if (this.checked) { serialized  = serialized.replace (tofind, toreplace); }
		else              { serialized += "&amp;" + toreplace; }
	});
	return serialized;
};
