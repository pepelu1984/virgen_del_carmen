<%@ page session="false"%> 
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

 <link rel="stylesheet" type="text/css" media="screen" href="<%=request.getContextPath()%>/css/ui.jqgrid.css" />
 <link rel="stylesheet" type="text/css" media="screen" href="<%=request.getContextPath()%>/css/estilos.css" />
<%-- <link rel="stylesheet" type="text/css" media="screen" href="<%=request.getContextPath()%>/css/ui-lightness/jquery-ui-1.7.3.custom.css" /> --%>
<link rel="stylesheet" type="text/css" media="screen" href="<%=request.getContextPath()%>/css/jquery-ui-1.8.1.custom.css" />
<link rel="stylesheet" type="text/css" media="screen" href="<%=request.getContextPath()%>/css/menudesChrome.css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/js/grid_formatters.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/menu.js"></script>
<%-- <script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.transit.min.js"></script> --%>

<!--  
-->
<%-- <script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.7.2.min.js"></script> --%>
<%-- <script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.5.1.js"></script> --%>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.8.3.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.form.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.validate.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/additional-methods.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.flexslider.js"></script>

<script src="<%=request.getContextPath()%>/js/i18n/grid.locale-es.js" type="text/javascript"></script>

<style>
div.floating-menu {position:fixed;background:#fff4c8;border:3px solid #ffcc00;width:200px;z-index:20;top:1;left:1;visibility :hidden}
div.floating-menu a, div.floating-menu h3 {display:block;margin:0 0.5em;}


 .ui-jqgrid tr.ui-row-ltr td { border-right-color: transparent; }
 .ui-jqgrid tr.ui-row-ltr td { border-bottom-color: transparent; }
 /*.ui-jqgrid { border-width: 0px; }*/
 .ui-jqgrid { border-right-width: 0px; border-left-width: 0px; }
 th.ui-th-column { border-right-color: transparent !important }

</style>


<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.jqGrid.src.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/ddroides-ajax.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/pestanas.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/commons/editableSelect.js"></script>

<script type="text/javascript" src="<%=request.getContextPath()%>/js/datetimepicker.js"></script>


<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.fmatter.js"></script>
<script type="text/javascript" src="<%= request.getContextPath() %>/js/jquery.livequery.js"></script>
<script type="text/javascript" src="<%= request.getContextPath() %>/js/jquery.validity.js"></script>
<script type="text/javascript" src="<%= request.getContextPath() %>/js/jquery.jPrintArea.js"></script>
<script type="text/javascript" src="<%= request.getContextPath() %>/js/jquery.maskedinput-1.2.2.js"></script>
<script type="text/javascript" src="<%= request.getContextPath() %>/js/jquery.printElement.js"></script>
<script type="text/javascript" src="<%= request.getContextPath() %>/js/serialize.js"></script>
<script type="text/javascript" src="<%= request.getContextPath() %>/js/jquey.chained.js"></script>
<script type="text/javascript" src="<%= request.getContextPath() %>/js/jquery.blockUI.js"></script>
<script src="<%= request.getContextPath() %>/js/minified/jquery-ui.min.js"></script>
  

<style>
div.floating-menu {position:fixed;background:#fff4c8;border:3px solid #ffcc00;width:200px;z-index:20;top:1;left:1;visibility :hidden}
div.floating-menu a, div.floating-menu h3 {display:block;margin:0 0.5em;}
</style>
 <script type="text/javascript">
 var bloqueo = true;
 
 function bloquearUI(){
	 if(bloqueo){
		 try{
		 	$.blockUI();
		 }catch (ignore) {
			//Dependiendo de las cargas dinamicas intenta bloquear cuando no esta todo listo
		}
	 }
 }
 function desBloquearUI(){
	 $.unblockUI();
 }
 
 
$(document).ready(function(){
	//$("#searchText").val("yeah");
	$(document).ajaxStart(bloquearUI).ajaxStop(desBloquearUI);
}); 
 
getColumnIndexByName = function(grid, columnName) {
    var cm = grid.jqGrid('getGridParam','colModel'),i=0,l=cm.length;
    for (; i<l; i++) {
        if (cm[i].name===columnName) {
            return i; // return the index
        }
    }
    return -1;
};
loadComplete = function() {
    $("tr.jqgrow:odd").addClass('myAltRowClass');
};
var rowsToColor = [];

function rowColorFormatter(cellValue, options, rowObject) {
            rowsToColor[rowsToColor.length] = options.rowId;
            return cellValue;
}
  

</script>
 
 
     