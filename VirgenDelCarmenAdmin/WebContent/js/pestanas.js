
  

$(document).ready(function(){
  $("li").click(function(){
    $(this).animate({
	
	
	
	
	}, 150).css({
		"background-color": "#C4EBA4",
		"opacity":"1"
		
	});
	    $(this).prevAll().css({
	
	"background-color": "#DDF0CE",
	
	"opacity":"0.6",
	"margin-top": "0px"
	
	
	
	});
	    $(this).nextAll().css({
	
	"background-color": "#DDF0CE",
	
	"opacity":"0.6",
	"margin-top": "0px"
	
	
	
	});


	
  });
}); 
	