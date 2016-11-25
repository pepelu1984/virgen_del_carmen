<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="false"%>
<script>
$(document).ready(function(){
	$(".numericos").keypress(function(e) {
		return onKeyPresionadaNumericos(e.which);
		
	});
	$(".numericosdbl").keypress(function(e) {
		return onKeyPresionadaNumericosDobles(e.which);
	});
	
	
});

function onKeyPresionadaNumericosDobles(nrevent){
	if(nrevent==46)
		return false;
	if(nrevent==118)
		return true;
												//coma			menos			enter		tab,flechas...
	if (( ((nrevent< 48) ||  (nrevent> 57)) && nrevent!=44 && nrevent!=45 && nrevent!=13 && nrevent!=0) ) {
		if( (nrevent!= 46 && (nrevent!=8)) ){
			    return false;
			}else{
				return true;
			}
		}else{
			return true;
		}
}

function onKeyPresionadaNumericos(nrevent){
	if(nrevent==118)
		return true;
	
	if (((nrevent< 48) ||  (nrevent> 57)) && (nrevent!=8)  && (nrevent!=45 && nrevent!=13 && nrevent!=0) ) {
		//alert(nrevent);
	    return false;
	}else{
		//alert(nrevent);
		return true;

	}	
}
</script>
