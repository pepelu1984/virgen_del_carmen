<%@ page session="false"%> 

<link rel="stylesheet" type="text/css" media="screen" href="<%=request.getContextPath()%>/css/ui.jqgrid.css" />
<link rel="stylesheet" type="text/css" media="screen" href="<%=request.getContextPath()%>/css/ui-lightness/jquery-ui-1.7.3.custom.css" />
<link rel="stylesheet" type="text/css" media="screen" href="<%=request.getContextPath()%>/css/jquery-ui-1.8.1.custom.css" />
<link rel="stylesheet" type="text/css" media="screen" href="<%=request.getContextPath()%>/css/flexslider.css" />
<link rel="stylesheet" type="text/css" media="screen" href="<%=request.getContextPath()%>/css/menudesChrome.css" />

<script src="<%=request.getContextPath()%>/js/i18n/grid.locale-es.js" type="text/javascript"></script>

<style>
.button-orange-small {
	display: inline-block;
	padding: 5px 10px 6px;
	color: #FFF;
	text-decoration: none;
	-moz-border-radius: 6px;
	-webkit-border-radius: 6px;
	-moz-box-shadow: 0 1px 3px rgba(0, 0, 0, 0.6);
	-webkit-box-shadow: 0 1px 3px rgba(0, 0, 0, 0.6);
	text-shadow: 0 -1px 1px rgba(0, 0, 0, 0.25);
	border-bottom: 1px solid rgba(0, 0, 0, 0.25);
	position: relative;
	cursor: pointer;
	background-color: #FF5C00;
	font-size: 12px;
	font-weight: bold;
	font-family: Arial, Helvetica, sans-serif;
}


</style>
<style>
div.floating-menu {position:fixed;background:#fff4c8;border:3px solid #ffcc00;width:200px;z-index:20;top:1;left:1;visibility :hidden}
div.floating-menu a, div.floating-menu h3 {display:block;margin:0 0.5em;}


 .ui-jqgrid tr.ui-row-ltr td { border-right-color: transparent; }
 .ui-jqgrid tr.ui-row-ltr td { border-bottom-color: transparent; }
 /*.ui-jqgrid { border-width: 0px; }*/
 .ui-jqgrid { border-right-width: 0px; border-left-width: 0px; }
 th.ui-th-column { border-right-color: transparent !important }

.myAltRowClass { background: #CCFF33; }

</style>


<script>

function puntosAComas(){
	$(".numericosdbl").each(
	    function(intIndex) {
	    	var contenido = $(this).html();
	    	if(contenido!=null && contenido!=""){ 
		    	//span
	    		contenido = contenido.replace(/\./g, ",");
		    	$(this).html(contenido);
	    	}else{
		    	//Input
		    	var valor = $(this).val();
		    	valor = valor.replace(/\./g, ",");
		    	$(this).val(valor);
	    	}
	    }
	);
}


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

function preparaCamposNumericos(){
	$(".numericos").keypress(function(e) {
		return onKeyPresionadaNumericos(e.which);
		
	});
	$(".numericosdbl").keypress(function(e) {
		return onKeyPresionadaNumericosDobles(e.which);
	});
}
$(".numericos").keypress(function(e) {
	return onKeyPresionadaNumericos(e.which);
	
});
$(".numericosdbl").keypress(function(e) {
	return onKeyPresionadaNumericosDobles(e.which);
});

function onKeyPresionadaNumericos(nrevent){
	
	if (((nrevent< 48) ||  (nrevent> 57)) && (nrevent!=8)) {
	    return false;
	}else{
		return true;

	}	
}

$(document).ready(function(){
	
	$(".numericos").keypress(function(e) {
		return onKeyPresionadaNumericos(e.which);
		
	});
	$(".numericosdbl").keypress(function(e) {
		return onKeyPresionadaNumericosDobles(e.which);
	});
	//$('input:([readonly="readonly"])').css("background-color","grey");
	
});


</script>



     