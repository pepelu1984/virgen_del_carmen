$.ajaxSetup ({
    cache: false
});

var appMain="/";
function replaceAll( text, busca, reemplaza ){
	  while (text.toString().indexOf(busca) != -1)
	      text = text.toString().replace(busca,reemplaza);
	  return text;
}

function Salir(){
	window.parent.window.location=appMain+"/index.htm";
}

function generateBreadCrumbs(strtext,strDireccion){
	var antAddress="";
	if($("#migas").html().indexOf("--")>-1){
		//$("#migas").html($("#migas").html()+ " -- "+strUrlAddrr);
		antAddress=$("#migas").html().substr(0,$("#migas").html().indexOf("--"));
		$("#migas").html(antAddress+ " -- <a href='"+strDireccion+"'>"+strtext+"</a>");
	
	}else{
		$("#migas").html($("#migas").html()+ " -- <a href='"+strDireccion+"'>"+strtext+"</a>");
	}
	
	//$("#breadcrumbs").html("<b><a href='javascript:loadContent(\""+strDireccion+"\")'>"+strtext+"</b></a> --->  Detalle");	
}
function addElementsToList(nameForm,nameList,texto,valor){
	var option = document.createElement("option");
	option.text = texto;
	option.value = valor;
	option.selected=true;
	var select = document.forms[nameForm][nameList];
	
	select.appendChild(option);
	
	
}


function addRow(strControllerGeneralLocation,elemento,idparent,caption,isSubElement){
	$("#"+elemento).jqGrid('setGridState', 'hidden');
	var strUrlAddrr="<a href='javascript:addRow(\""+strControllerGeneralLocation+"\",\""+elemento+"\","+idparent+",\""+caption+"\")'><b>Nuevo registro "+"</b></a>";
	
	$("#migas").html($("#migas").html()+ " -- "+strUrlAddrr);

	//$("#detalleDatos").html("<br/><br/><br/><p align='center'><img src='images/loading.gif' border='0'/></p>");
	AjaxUtils.loadIntoOnEdit("content",strControllerGeneralLocation+"?event=add&idparent="+idparent,null,function(dataBack) {
		if(isSubElement){
			$("#subDetalleDatos").html(dataBack);	
		}else{
			$("#detalleDatos").html(dataBack);	

		}
		//cierra(elemento);
		
	});
}
function deleteRow(strControllerGeneralLocation,idrow){
	
	
	AjaxUtils.loadIntoOnEdit("content",strControllerGeneralLocation+"?event=delete&",null,function(dataBack) {
		alert("Registro borrado");
		//$("#detalleDatos").html(dataBack);	
	});
	
	
}
var lastSel=-1;
var lastelementType="";

function editRow2(strControllerGeneralLocation,idrow,elemento){
//alert(strControllerGeneralLocation);
//alert(lastSel);
	
	if(lastSel>-1 && lastelementType==elemento){
	    jQuery('#'+elemento).restoreRow(lastSel);
	}
	jQuery('#'+elemento).jqGrid('editRow',idrow, true,pickdates,null,''+strControllerGeneralLocation+'?event=save_edit');
	//alert("seted");
	lastSel=idrow;
	idElementSelected=idrow;
	lastelementType=elemento;
}


function cancelSearchInGrid(elemento,strElemento){
    elemento.toggleToolbar();
	//$("#btnSearch"+strElemento).css("display","none");
	//$("#btnCRUD"+strElemento).css("display","inline");

}
function doSearchInGrid(elemento,strElemento){
	//$("#"+strElemento).jqGrid('filterToolbar',{autosearch:true,stringResult: false, searchOnEnter: false, defaultSearch : "cn"});
	
	elemento.triggerToolbar();

}
var isSelectedTool=new Array();

function searchInGrid(elemento,strURL,strElemento){
	if(!isSelectedTool[strElemento]){
		$("#"+strElemento).jqGrid('filterToolbar',{ignoreCase: true,autosearch:true,stringResult: false, searchOnEnter: true, defaultSearch : "cn"});
		isSelectedTool[strElemento]=true;
	}else{
		 elemento.clearToolbar();
		 elemento.toggleToolbar();
		
	}
	
}
var rowsToColor=new Array();
var isSelected=new Array();

function rowColorFormatter(cellValue, options, rowObject) {
    //alert(rowObject.isSelected);
    //alert(rowObject.isSelected);
    
	if(rowObject.isSelected!=null && rowObject.isSelected!=undefined && rowObject.isSelected!="" && rowObject.isSelected!="false"){
		isSelected[rowsToColor.length]=true;
	}else{
		isSelected[rowsToColor.length]=false;
		
	}
	rowsToColor[rowsToColor.length] = options.rowId;

    return cellValue;
}
function fillIT(elemento,ancho,caption,numfilas,nomColumnas,columnas,STRuRL,optionalId,isSelectable,isPickApplet,mimethod,myData,onCompleteFunc,optParameter,multiselect,genCSV){
	fillIT2(elemento,ancho,caption,caption,numfilas,nomColumnas,columnas,STRuRL,optionalId,isSelectable,isPickApplet,mimethod,myData,onCompleteFunc,optParameter,multiselect,genCSV);
}

function fillIT2(elemento,ancho,caption,nameSection,numfilas,nomColumnas,columnas,STRuRL,optionalId,isSelectable,isPickApplet,mimethod,myData,onCompleteFunc,optParameter,multiselect,genCSV){
		var botonera="";
		var is360=false;
		var wii=$(window).width();
		var heii=$(window).height();
		
		if(ancho>wii){
			ancho=wii-150;
		}
		
		if(optionalId!=null && optionalId!="" && optionalId!=undefined){
			is360=true;
		}else{
			optionalId="null";
			
		}
    	if(!isPickApplet){
	    	var agr=null;
	    	if(optionalId==null || optionalId=="null"){
		    	agr="<td><a href='javascript:addRow(\""+STRuRL+"\",\""+elemento+"\","+optionalId+",\""+nameSection+"\",false)'><img src='images/adicionar.png' border='0'/></a></td>";
	    	}else{
		    	agr="<td><a href='javascript:addRow(\""+STRuRL+"\",\""+elemento+"\","+optionalId+",\""+nameSection+"\",true)'><img src='images/adicionar.png' border='0'/></a></td>";
	    	}
	    	
	    	if (optionalId!=null) { 
	    		if (optionalId=='prospective')
	    			agr="";
	    	}
		   botonera=("<table style='position:relative;margin-left:80px;align:right'>"+
					 "<tr id='btnCRUD"+elemento+"'>"+
					 agr+
					 "<td><a href='javascript:deleteRow(\""+STRuRL+"\")'><img src='images/eliminar.png' border='0'/></a></td>"+
					 "<td><a href='javascript:searchInGrid("+elemento+",\""+STRuRL+"\",\""+elemento+"\")'><img src='images/search.png' border='0'/></a></td>"+
					 "</tr>");
				botonera=botonera+"<tr id='btnSearch"+elemento+"' style='display:none'>"+
				 "<td ><a href='javascript:doSearchInGrid("+elemento+",\""+elemento+"\"s)'>Ir</a></td>"+
				 "<td class='filagrid' onClick='javascript:cancelSearchInGrid("+elemento+",\""+elemento+"\")'><a>Cancelar</a></td>"+
				 "</tr>"+
				 "</table>";

    	   }
	    if(isPickApplet){
	    	botonera=("<table style='position:relative;margin-left:80px;align:right'>"+
					 "<tr id='btnCRUD"+elemento+"'>"+
					 "<td><a href='javascript:searchInGrid("+elemento+",\""+STRuRL+"\",\""+elemento+"\")'><img src='images/search.png' border='0'/></a></td>"+
					 "</tr></table>");
				 "</table>";

	    	/*
				botonera="<tr id='btnSearch"+elemento+"' style='display:none'>"+
				 "<td><a href='javascript:searchInGrid("+elemento+",\""+STRuRL+"\",\""+elemento+"\")'><img src='images/consultar.png' border='0'/></a></td>"+
				 "</tr>"+
				 "</table>";
				 */
	    	
	    }
	   var isMulti=false;
	  if(multiselect!=null && (multiselect==true || multiselect=="true")){
		   isMulti=true;
	   }
	   $("#btn"+elemento).html(botonera);
		var idOptional=optionalId || null;
		var SelectableIs=isSelectable || null;
		var metodo="list";
		if(mimethod!=null){
			metodo=mimethod;
		}
		var strAdded="";
		//alert(myData);
		if(myData==true || myData=="true"){
			strAdded="&mylink=true";
		}else{
			strAdded="&mylink=false";
			
		}
		if(optParameter==undefined)optParameter="";
		var lastSel=null;
		jQuery("#"+elemento).jqGrid({
	    	url:''+STRuRL+'?event='+metodo+'&idparent='+idOptional+strAdded+""+optParameter,
	        datatype: 'json',
	        loadonce: true,
	        rownumbers: false,
	        height: ((20*numfilas)+30),
	        width:ancho,
	     	rowNum: numfilas,
	     	hiddengrid: false,
     	 excel: true,
	     	jsonReader: {
	    		repeatitems : false,
	    		id: "0"
	    	},
	    	multiselect: isMulti,
	    	 gridview: true,
	    	 onSelectRow: function(id){ 
	    		if((SelectableIs!=null && SelectableIs) && !isPickApplet){
	    			//alert("optional"+optionalId);
	    			if(optionalId==null || optionalId=="null"){
			      	      editRow(""+id,STRuRL,elemento,caption,nameSection,false);
	    			}else{
			      	      editRow(""+id,STRuRL,elemento,caption,nameSection,true);
	    				
	    			}    
		      	      $("#"+id).css("color","#006633");
		      	      //$("#"+id).css("background","#CCFF33");
		      	      //$("#"+id).css("font-weight","bold");
		      	      if(lastSel>0){
			      	      $("#"+lastSel).css("color","black");
		      	      }
 	    		}
	    		if(id && id!=lastSel){ 
	    		        jQuery('#'+elemento).restoreRow(lastSel); 
	    		        lastSel=id; 
	    		}
	    		//jQuery('#'+elemento).editRow(id, true); 
	    		// alert("registro: "+id);
    	      editparameters = {
						"keys" : true,
						"oneditfunc" : function editGridRow(id){
							/*
							AjaxUtils.loadIntoOnEdit("content",strControllerLocation+"?event=edit&idrow="+id,null,function(dataBack) {
								var datos;
								var error=false;
								//$("#detalleDatos").empty();
								//$("#detalleDatos").html(dataBack);	
								
								});
							*/
						},
						"successfunc" : null,
						"url" : ''+STRuRL+'?event=save_edit',
					        "extraparam" : {},
						"aftersavefunc" : null,
						"errorfunc": null,
						"afterrestorefunc" : null,
						"restoreAfterError" : true,
						"mtype" : "POST"
					};
    	      
    	      //jQuery('#'+elemento).jqGrid('editRow',id, true,pickdates,null,''+STRuRL+'?event=save_edit');
    	 
    	   },
	      	rowList: [10,20,30],
         	colNames:nomColumnas,
         	colModel:columnas,
         	pager: "#p"+elemento,
         	viewrecords: true,
         	caption: caption,
         	
        	loadComplete: function(data) {
        		if(data == undefined || data.rows == undefined ){
        			alert(caption+":No se han encontrado datos");
        			return;
        		}
   				 if(data!=null && data.rows.length > 0){
					 //jQuery("#"+elemento).jqGrid("setSelection", data.rows[0].id);
   					 
					 if((SelectableIs!=null && SelectableIs) && !isPickApplet){
						 //alert(data.rows[0].rowId);
						 //editRow(""+data.rows[0].rowId,STRuRL,elemento);
		      	      //jQuery("#"+elemento).jqGrid("setSelection", data.rows[0].id);
		    		}
					 
				}	
   			 },
         	gridComplete: function () {
         		//alert("grid compplete");
         		
         		//cancelSearchInGrid(eval(elemento));
                //tr.jqgrow
         		for (var i = 0; i < rowsToColor.length; i++) {
                    /*
         			if (i % 2 == 0) {
                    	$("#" + rowsToColor[i]).find("td").parent().removeClass('ui-widget-content');
                    	$("#" + rowsToColor[i]).find("td").parent().css("background-color", "white");
                    	$("#" + rowsToColor[i]).find("td").css("color", "black");
                    }else{
                    	$("#" + rowsToColor[i]).find("td").parent().removeClass('ui-widget-content');
                    	$("#" + rowsToColor[i]).find("td").parent().css("background-color", "white");
                    	$("#" + rowsToColor[i]).find("td").css("color", "black");
                    }
                    */
         			if(isSelected!=null && isSelected[i]==true){
         				//alert("is selected");
                    	//$("#" + rowsToColor[i]).find("td").parent().removeClass('ui-widget-content');
                    	//$("#" + rowsToColor[i]).find("td").parent().css("background-color", "green");
                    	$("#" + rowsToColor[i]).find("td").css("color", "red");
         			}else{
                    	//$("#" + rowsToColor[i]).find("td").parent().removeClass('ui-widget-content');
                    	//$("#" + rowsToColor[i]).find("td").parent().css("background-color", "white");
                    	$("#" + rowsToColor[i]).find("td").css("color", "black");
         				
         			}
                }
             }
      
      });

		 
      jQuery("#"+elemento).jqGrid('navGrid','#p'+elemento,
    		  {edit:false,add:false,del:false,search:true},
    		  {},
    		  {},
    		  {},
    		  {multipleSearch:false, multipleGroup:true}
  	);
      jQuery("#"+elemento).jqGrid('navButtonAdd', 
    		  "#p"+elemento,
              {caption:" Export to Excel ", 
              buttonicon:"ui-icon-bookmark", 
              onClickButton: genCSV, position:"last"});
      
    //$("#"+elemento).jqGrid('filterToolbar',{autosearch:true,stringResult: false, searchOnEnter: true, defaultSearch : "cn"});
	    /*
	     $(".sgcollapsed","#"+elemento).each(function() {
          $(this).trigger("click").unbind('click');
    	});
  	*/
    


}  

var arrMigas=new Array();
function loadContentSub(totalPath,idView,detailRefresh,hasMyLinks,title){
	var direccion="";
	//$("#submenuPanel").css("display","none");
	//$("#content").empty("");
	//var antMigas=$("#migas").html()+" --> ";
	$("#migas").empty("");
	$("#migas").html("<a href='javascript:loadIndex()'>Inicio</a>&nbsp; - &nbsp;"+"<a href='javascript:loadContentSub(\""+totalPath+"\","+idView+","+detailRefresh+","+hasMyLinks+",\""+title+"\")'>"+title+"</a>");
	
	
	//$("#content").html("<br/><br/><br/><p align='center'><img src='images/loading.gif' border='0'/></p>");
	if(detailRefresh!=null && !detailRefresh){
		//$("#detalleDatos").empty("");
		//$("#detalleDatos").html("");
	}
	
	
	//alert(idElementSelected);
	if(idElementSelected!=null && idElementSelected!=0 && idElementSelected!="" && idElementSelected!=undefined){
		//alert(whereTO);

		if(totalPath.indexOf("?")>-1 || totalPath.indexOf("event")>-1){
			direccion="&selected="+idElementSelected;
		}else{
			direccion="?selected="+idElementSelected;

		}
	}else{
		if(totalPath.indexOf("?")>-1 || totalPath.indexOf("event")>-1){
			direccion="&selected=";
		}else{
			direccion="?selected=";

		}
		
	}
	
	if(hasMyLinks=="true" || hasMyLinks==true){
		direccion=direccion+"&mylink=true";
		
	}else{
		direccion=direccion+"&mylink=false";
	}
	if(idView!=null && idView>0 && !detailRefresh){
		//loadSubMenu(contextPath,whereTO,idView);
		direccion=direccion+"&idview="+idView;
		
	}
	//alert(contextPath+whereTO+""+direccion);
	AjaxUtils.loadIntoOnEdit("content",totalPath+""+direccion,null,function(dataBack) {
		
		var datos;
		var error=false;
		data2=clearspaces(replaceAll(dataBack.substr(0,2)," ","")).replace(/\r\n/g, "");
		if(data2=='{"'){	
			datos=eval('(' + dataBack + ')');
			error=false;
			//alert("result: "+datos.model.lista[0].lmcomp);
		}else{		
			//$("#content").html("");
			
			//aqui cargamos el resultado de la peticion al servidor
			//es una pagina JSP en este caso, y como tal, la ponemos en el content
			//alert("antes");
			$("#content").html(dataBack);
			//alert("despuies");
			//$("#detalleDatos").html("");	
			//alert("submenu");
			//$("#submenuPanel").css("margin-left","-220px");
			$("#submenuPanel").css("display","block");
			//$("#breadcrumbs").html("");
			//alert(whereTO);
			

		}
		});	

}
function loadContent(contextPath,whereTO,idView,detailRefresh,hasMyLinks,title){
	loadContentSub(contextPath+whereTO,idView,detailRefresh,hasMyLinks,title);
	
}
	var idClienteSeleccionado=0;
function setClienteSelected(idcliente){
	idClienteSeleccionado=idcliente;

}
function getClienteSelected(){
	return idClienteSeleccionado;

}

function loadSubMenu(contextPath,whereTO,idParentView){
		//alert("loadSubMenu"+idParentView);
		//$("#submenu").css("display","none");
		//$("#submenuPanel").empty();
		AjaxUtils.loadIntoOnEdit("content",contextPath+"/index.crm?event=getSubViews&idParentView="+idParentView,null,function(dataBack) {
			var datos;
			var error=false;
			data2=clearspaces(replaceAll(dataBack.substr(0,2)," ","")).replace(/\r\n/g, "");
			if(data2=='{"'){	
				datos=eval('(' + dataBack + ')');
				error=false;
				//alert("result: "+datos.model.lista[0].lmcomp);
			}else{		
				//$("#breadcrumbs").html("");
				//alert("back!!");
				
				$("#submenu").html(dataBack);
				/*
				*/
				$("#submenuPanel").css("margin-left","-220px");
				$("#submenu").css("display","block");
					}
			});	
		
		
	
	}
	
	

var idElementSelected;
function setSelected(selected){
	
	idElementSelected=selected;
}

function editRow(idrow,controllerLocation,elemento,caption,nameelement,isSubElement){
	var strUrlAddrr="<a href='javascript:editRow("+idrow+",\""+controllerLocation+"\",\""+elemento+"\",\""+caption+"\")'><b>Edici&oacute;n de "+caption+": "+idrow+"</b></a>";
	var antAddress="";
	if($("#migas").html().indexOf("--")>-1){
		//$("#migas").html($("#migas").html()+ " -- "+strUrlAddrr);
		antAddress=$("#migas").html().substr(0,$("#migas").html().indexOf("--"));
		$("#migas").html(antAddress+ " -- "+strUrlAddrr);
	
	}else{
		$("#migas").html($("#migas").html()+ " -- "+strUrlAddrr);
	}
	

	$("#"+elemento).jqGrid('setGridState', 'hidden');
	idElementSelected=idrow;	
	AjaxUtils.loadIntoOnEdit("content",controllerLocation+"?event=edit&idrow="+idrow,null,function(dataBack) {	
		//alert("subelement"+isSubElement);
		if(!isSubElement){
			$("#detalleDatos").html("<br/><br/><br/><p align='center'><img src='images/loading.gif' border='0'/></p>");
			$("#detalleDatos").html(dataBack);	
		}else{
			//$("#detalleDatos").css("display","none");
			$("#subDetalleDatos").html("<br/><br/><br/><p align='center'><img src='images/loading.gif' border='0'/></p>");
			$("#subDetalleDatos").html(dataBack);	
			
		}
		//alert(elemento);
		$("#gbox_"+elemento).css("display","none");
		$("#p"+elemento).css("display","none");
		$("#btn"+elemento).css("display","none");
		$("#milinks").attr("style","margin-top:20px;position:absolute;display:none;left:25px; top:120px");
		
	});
	
}


function onKeyPresionadaNumericos(event){
	if (((event.which< 48) ||  (event.which> 57)) && (event.which!=8)) {
	    return false;
	}else{
		return true;
	}	
}

function onKeyPressMoneyBox(event){
	var srcEl = event.srcElement? event.srcElement : event.target;
	if(event.which==46){
		var eId = srcEl.id;
		document.getElementById(eId.substring(0,eId.lastIndexOf('_'))+"_decimalPart").focus();
	}
	if (((event.which< 48) ||  (event.which> 57)) && (event.which!=8)) {
	    return false;
	}else{
		return true;
	}	
}

function onFocusMoneyBox(event){
	var srcEl = event.srcElement? event.srcElement : event.target;
	if((srcEl.value=='0' && !srcEl.readOnly) || (srcEl.value=='00' && !srcEl.readOnly))
		srcEl.value='';
}

function onBlurMoneyBox(event){
	var srcEl = event.srcElement? event.srcElement : event.target;
	if(srcEl.value==''){
		if(srcEl.id.indexOf('decimalPart')!=-1==true){
			srcEl.value="00";
		}else{
			srcEl.value="0";
		}
	}
}


function onKeyPresionadaNumericosDobles(event){
	var srcEl = event.srcElement? event.srcElement : event.target;
	if(srcEl.value.length==0 && event.which==46)
		return false;
	if(event.which==46 && srcEl.value.indexOf('.')!=-1)
		return false;
	if (( (event.which< 48) ||  (event.which> 57) ) ) {
		if( (event.which!= 46 && (event.which!=8)) ){
			    return false;
			}else{
				return true;
			}
		}else{
			return true;
		}
}

function onKeyPresionadaTextbox(event){
	var srcEl = event.srcElement? event.srcElement : event.target;
	if(srcEl.value.length==0 && event.which==32){
		return false;
	}
	return true;
}
String.prototype.endsWith = function (str){
    return this.slice(-str.length) == str;
 }; 
 String.prototype.startsWith = function (str){
	    return this.slice(0, str.length) == str;
	 };
	 

 function clearspaces(s)
 {
 var i1; // stores the index
 	i1=s.indexOf(" ",1);
 	while (i1>0)
 	{
 		s = s.slice(0,i1)+s.slice(i1+1,999); // remove the space
 		i1=s.indexOf(" ",1);	
 	}
 	return s;
 }
 
 
function trim(cadena)
{
	for(i=0; i<cadena.length; )
	{
		if(cadena.charAt(i)==" ")
			cadena=cadena.substring(i+1, cadena.length);
		else
			break;
	}

	for(i=cadena.length-1; i>=0; i=cadena.length-1)
	{
		if(cadena.charAt(i)==" ")
			cadena=cadena.substring(0,i);
		else
			break;
	}
	
	return cadena;
}


//file selection
//function FileSelectHandler(e) {
//	// cancel event and hover styling
//	FileDragHover(e);
//	// fetch FileList object
//	var files = e.target.files || e.dataTransfer.files;
//	// process all File objects
//	for (var i = 0, f; f = files[i]; i++) {
//		ParseFile(f);
//		UploadFile(f);
//	}
//}
//function UploadFile(file) {
//	var xhr = new XMLHttpRequest();
//	alert(xhr.upload);
//	
////	if (xhr.upload && file.type == "image/jpeg" && file.size <= $id("MAX_FILE_SIZE").value) {
//	if (xhr.upload) {
//	
//		// start upload
//		//alert("uploading to "+$id("upload").action);
//		xhr.open("POST", "<%=request.getContextPath()%>/admin_products.crm?event=save", true);
//		xhr.setRequestHeader("X_FILENAME", file.name);
//		xhr.send(file);
//	}
//}



function AjaxUtils() {
}
AjaxUtils.load = function (elementId,target,data,callback) {
	$("#msglog").removeClass("error warning info success");
	$("#msglog").html("");
	
// $('#'+elementId).html("<br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><p
// align='center'><img src='"+appMain+"/img/bar-circle.gif' border='0'/></p>");
	MaabUtils.overlayWaitingIcon(elementId);
	jQuery('#'+elementId).load(target,data,function(responseText, textStatus, req){
		if (textStatus == 'error') { MaabUtils.removeWaitingIcon(elementId); }
		if (callback) { callback(responseText, textStatus, req); }
	});
};

AjaxUtils.loadInto = function (elementId,target,data,callback) {
	$("#msglog").html("");
	$('#'+elementId).html("<br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><p align='center'><img src='/CajaMM/img/bar-circle.gif' border='0'/></p>");
//	MaabUtils.overlayWaitingIcon(elementId);
	if (callback) {$('#'+elementId).load(target,data,callback);}
	else {$('#'+elementId).load(target,data);}
};

AjaxUtils.loadIntoOnEdit = function (elementId,target,data,callback) {
	//alert(data);
	if (callback) {
//		$.ajax({
//		    url: target,
//		    data: data,
//		    cache: false,
//		    contentType: 'multipart/form-data',
//		    processData: false,
//		    type: 'POST',
//		    success: callback
//		});
//		
		$.post(target,data,callback);
	}else {
		$('#'+elementId).load(target,data);
	}
};


AjaxUtils.loadIntoOrErrorMessage = function (elementId,target,data,callback) {
	$("#msglog").removeClass("error warning info success");
	$("#msglog").html("");

	
// MaabUtils.overlayWaitingIcon(elementId);
	if (callback) {
		
		
		$('#'+elementId).load(target,data,callback);
	}else {
		$.post(target,data, function(dataBack) {
			var datos;
			var error=false;
			
			if(dataBack.substr(0,5) != '<META'){
				datos=eval('(' + dataBack + ')');
				error=false;
			}else{
				error=true;
			}
			
			if(error){
				$("#"+elementId).html(dataBack);
			}else{
				AjaxUtils.showErrorMenssage(datos.model.mensajeUsuario,"error");
			}
		});
	}
	
};


AjaxUtils.loadIntoPopUpOrErrorMessage = function (elementId,target,data,callback,widthValue,heightValue) {
	//$("#msglog").removeClass("error warning info success");
	//$("#msglog").html("");

	var element = $('#'+elementId);
	if (callback) {
		element.load(target,data,callback);
	}else {
		var element = $('#'+elementId);
		element.load(target,data,function(){
			element.dialog('destroy').show()
			.dialog({height:heightValue,width:widthValue,modal:true});
		});
		// window.open(target+"&"+data,"pop","menubar=1,resizable=1,width="+widthValue+",height="+heightValue);
	}
};


AjaxUtils.loadFormInto = function (postController,elementId,target,data) {
	$("#msglog").removeClass("error warning info success");
	$("#msglog").html("");
	$('#'+elementId).html("<br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><p align='center'><img src='"+appMain+"/img/bar-circle.gif' border='0'/></p>");
// MaabUtils.overlayWaitingIcon(elementId);
	$.post(postController,data,function(data_jsp){
		
		$('#'+elementId).load(data_jsp);
	});
};

function hideForm(element,toOpen){
	  $(".redorprueba").slideToggle(function ()
	      		 {
	      			 $(this).animate({down:'200px'},1000);
	      			 $(".fielpruebas").transition({ x: '-1000px'});
	      	}, function () {
	      	$(this).animate({up:'0px'}, 300);
	      	$(".fielpruebas").transition({ x: '-1000px'});
      		}
	   	);
	  if(toOpen){
			$("#"+element).css("display","inline-block");
			elementToHiden=element;
			$(".fielpruebas").css("display","inline");
	  }else{
			$("#"+element).empty();
			$("#"+element).html("");
			$(".fielpruebas").css("display","none");
			
	  }
	
}
var elementToHiden=null;
function undoSelection(){
	hideForm(elementToHiden,false);
	
}
AjaxUtils.loadIntoPopUp = function (elementId,target,data,widthValue,heightValue,parent) {
	var element = $('#'+elementId);
	$.post(target,data,function(dataBack) {
		
		var datos;
		var error=false;
		var data2=trim(dataBack.substr(0,2));
		if(data2 == '{"'){
			datos=eval('(' + dataBack + ')');
			error=false;
		}else{
			error=true;
		}
		
		
		if(error){
			var padre=$("#"+elementId).parent();
			//alert(padre);
			//alert(padre[0].id);
			//if(padre[0].id)
			if(padre[0]!=undefined){
				parent=padre[0].id;
			}else{
				parent="content";
			}
			
			element.html(dataBack);
			element.dialog('destroy').show()
			.dialog({height:heightValue,width:widthValue,modal:true},{close: function(event, ui) {
				
				if(parent!=null && parent!="" && parent!=undefined){
					AjaxUtils.closePopUp(elementId,parent);
				}else{
					AjaxUtils.closePopUp(elementId,"content");
				
				}
				//$(this).close();
			}
			});
		}else{
			AjaxUtils.showErrorMenssage(datos.model.mensajeUsuario,"error");
		}
	});
	
	
	
	
	
	
	
	
	
};

AjaxUtils.closePopUp = function (elementId, parentId) {
	var element = $('#'+elementId);
	var $elemClone=null;
		
		$elemClone=$('#'+elementId).dialog('destroy').clone();
		$('#'+elementId).remove();
		$elemClone=element.dialog('destroy').clone();
		element.remove();	
		if (parentId!=undefined){
			$elemClone.appendTo('#' + parentId);
			
		}
	
	
};


AjaxUtils.loadIntoOuterPopUp = function (target,data,widthValue,heightValue) {
	$("#msglog").removeClass("error warning info success");
	$("#msglog").html("");

	window.open(target+"&"+data,"pop","menubar=1,resizable=1,width="+widthValue+",height="+heightValue);
};

AjaxUtils.showErrorMenssage = function (message,type) {
	// Esta pensado para generar cualquier tipo de error. ya sea alert u otros.
	/*
	$("#msglog").removeClass("error warning info success");
	if(type!=null){
		$("#msglog").addClass(type);
	}else{
		$("#msglog").addClass("warning");
	}
	$("#msglog").html("<p id='errorMsg1' name='errorMsg1'><a id='error' name='error'></a><h1><b>"+message+"</b></h1></p>");
	*/
	/*
	var location=""+window.document.location;
	if(location.indexOf("error")== -1){
		window.document.location=location+"#error";
	}
	$("#msglog").html(message);
	*/
	alert(message);
	
};

//ksanoja - 26/08/2013 - Actividades de hoy en inicio

function fillITToday(elemento,ancho,caption,numfilas,nomColumnas,columnas,STRuRL,optionalId,isSelectable,isPickApplet,mimethod,myData,onCompleteFunc,optParameter,multiselect){
	fillIT2Today(elemento,ancho,caption,caption,numfilas,nomColumnas,columnas,STRuRL,optionalId,isSelectable,isPickApplet,mimethod,myData,onCompleteFunc,optParameter,multiselect);
}

function fillIT2Today(elemento,ancho,caption,nameSection,numfilas,nomColumnas,columnas,STRuRL,optionalId,isSelectable,isPickApplet,mimethod,myData,onCompleteFunc,optParameter,multiselect){
	var botonera="";
	var is360=false;
	var wii=$(window).width();
	var heii=$(window).height();
	if(ancho>wii){
		ancho=wii-150;
	}
	
	if(optionalId!=null && optionalId!="" && optionalId!=undefined){
		is360=true;
	}else{
		optionalId="null";
		
	}
	if(!isPickApplet){
    	var agr=null;
    	if(optionalId==null || optionalId=="null"){
	    	agr="<td><a href='javascript:addRow(\""+STRuRL+"\",\""+elemento+"\","+optionalId+",\""+nameSection+"\",false)'><img src='images/adicionar.png' border='0'/></a></td>";
    	}else{
	    	agr="<td><a href='javascript:addRow(\""+STRuRL+"\",\""+elemento+"\","+optionalId+",\""+nameSection+"\",true)'><img src='images/adicionar.png' border='0'/></a></td>";
    	}
    	
    	if (optionalId!=null) { 
    		if (optionalId=='prospective')
    			agr="";
    	}
	   botonera=("<table style='position:relative;margin-left:200px;align:right'>"+
				 "<tr id='btnCRUD"+elemento+"'>"+
				 agr+
				 "<td><a href='javascript:deleteRow(\""+STRuRL+"\")'><img src='images/eliminar.png' border='0'/></a></td>"+
				 "<td><a href='javascript:searchInGrid("+elemento+",\""+STRuRL+"\",\""+elemento+"\")'><img src='images/search.png' border='0'/></a></td>"+
				 "</tr>");
			botonera=botonera+"<tr id='btnSearch"+elemento+"' style='display:none'>"+
			 "<td ><a href='javascript:doSearchInGrid("+elemento+",\""+elemento+"\"s)'>Ir</a></td>"+
			 "<td class='filagrid' onClick='javascript:cancelSearchInGrid("+elemento+",\""+elemento+"\")'><a>Cancelar</a></td>"+
			 "</tr>"+
			 "</table>";

	   }
    if(isPickApplet){
    	botonera=("<table style='position:relative;margin-left:200px;align:right'>"+
				 "<tr id='btnCRUD"+elemento+"'>"+
				 "<td><a href='javascript:searchInGrid("+elemento+",\""+STRuRL+"\",\""+elemento+"\")'><img src='images/search.png' border='0'/></a></td>"+
				 "</tr></table>");
			 "</table>";
    }
   var isMulti=false;
  if(multiselect!=null && (multiselect==true || multiselect=="true")){
	   isMulti=true;
   }
   $("#btn"+elemento).html(botonera);
	var idOptional=optionalId || null;
	var SelectableIs=isSelectable || null;
	var metodo="listToday";
	if(mimethod!=null){
		metodo=mimethod;
	}
	var strAdded="";
	if(myData==true || myData=="true"){
		strAdded="&mylink=true";
	}else{
		strAdded="&mylink=false";
		
	}
	if(optParameter==undefined)optParameter="";
	var lastSel=null;
	jQuery("#"+elemento).jqGrid({
    	url:''+STRuRL+'?event='+metodo+'&idparent='+idOptional+strAdded+""+optParameter,
        datatype: 'json',
        loadonce: true,
        rownumbers: false,
        height: ((20*numfilas)+30),
        width:ancho,
     	rowNum: numfilas,
     	hiddengrid: false,
     	jsonReader: {
    		repeatitems : false,
    		id: "0"
    	},
    	multiselect: isMulti,
    	 gridview: true,
    	 onSelectRow: function(id){ 
    		if((SelectableIs!=null && SelectableIs) && !isPickApplet){
    			if(optionalId==null || optionalId=="null"){
		      	      editRow(""+id,STRuRL,elemento,caption,nameSection,false);
    			}else{
		      	      editRow(""+id,STRuRL,elemento,caption,nameSection,true);
    				
    			}    
	      	      $("#"+id).css("color","#006633");
	      	      if(lastSel>0){
		      	      $("#"+lastSel).css("color","black");
	      	      }
	    		}
    		if(id && id!=lastSel){ 
    		        jQuery('#'+elemento).restoreRow(lastSel); 
    		        lastSel=id; 
    		}
	      editparameters = {
					"keys" : true,
					"oneditfunc" : function editGridRow(id){
					},
					"successfunc" : null,
					"url" : ''+STRuRL+'?event=save_edit',
				        "extraparam" : {},
					"aftersavefunc" : null,
					"errorfunc": null,
					"afterrestorefunc" : null,
					"restoreAfterError" : true,
					"mtype" : "POST"
				};
	   },
      	rowList: [10,20,30],
     	colNames:nomColumnas,
     	colModel:columnas,
     	pager: "#p"+elemento,
     	viewrecords: true,
     	caption: caption,
     	
    	loadComplete: function(data) {
				 if(data!=null && data.rows.length > 0){
				 if((SelectableIs!=null && SelectableIs) && !isPickApplet){
	    		}
				 
			}	
			 },
     	gridComplete: function () {
     		for (var i = 0; i < rowsToColor.length; i++) {
     			if(isSelected!=null && isSelected[i]==true){
                	$("#" + rowsToColor[i]).find("td").css("color", "red");
     			}else{
                	$("#" + rowsToColor[i]).find("td").css("color", "black");
     				
     			}
            }
         }
  
  });

  jQuery("#"+elemento).jqGrid('navGrid','#p'+elemento,
		  {edit:false,add:false,del:false,search:true},
		  {},
		  {},
		  {},
		  {multipleSearch:false, multipleGroup:true}
	);
}





