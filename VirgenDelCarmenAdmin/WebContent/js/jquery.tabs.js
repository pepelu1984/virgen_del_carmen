

jQuery.fn.myTabs = function (options) {
	var config = $.extend({},{
		tabs: '.tabs',
		contents: '.tabs-content',
		reload: true
	}, options);
	var tabs = $(config.tabs + ' ul');
	var contents = $(config.contents);
	if (tabs && contents) {
		tabs.each(this.find('li a').click(function(){
			var thisClass = this.className.slice(0,2);
			$('div.tabbed div').hide();
			$('div.' + thisClass).show();
			$('div.tabbed ul.tabs li a').removeClass('tab-current');
			$(this).addClass('tab-current');
		}));
	}
	var clazz = param
	// setting the tabs in the sidebar hide and show, setting the current tab
	$('.tabsbed div').hide();
	$('div.tabbed').show();
	$('div.tabbed ul.tabs li.t1 a').addClass('tab-current');

// SIDEBAR TABS
$('div.tabbed ul li a').click(function(){
	var thisClass = this.className.slice(0,2);
	$('div.tabbed div').hide();
	$('div.' + thisClass).show();
	$('div.tabbed ul.tabs li a').removeClass('tab-current');
	$(this).addClass('tab-current');
	});
};


