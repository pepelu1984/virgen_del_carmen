$.ajaxSetup ({
    cache: false
});
function rowColorFormatter(cellValue, options, rowObject) {
    rowsToColor[rowsToColor.length] = options.rowId;

    return cellValue;
}



function DetectChanges(form) {
	var f = FormChanges(form), msg = "";
	for (var e = 0, el = f.length; e < el; e++){
		msg += "\n    #" + f[e].id;
	}
	
	//alert((msg ? "Elements changed:" : "No changes made.") + msg);
}



function onKeyPresionadaNumericos(nrevent){
	
	if (((nrevent< 48) ||  (nrevent> 57)) && (nrevent!=8)) {
	    return false;
	}else{
		return true;

	}	
}

/*
function onKeyPresionadaNumericosDobles(event){

	var srcEl = event.srcElement? event.srcElement : event.target;
	if(srcEl.value.length==0 && event.which==46)
		return false;
	if(event.which==46 && srcEl.value.indexOf(',')!=-1)
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
}*/

function FormChanges(form) {

	// get form
	if (typeof form == "string") form = document.getElementById(form);
	if (!form || !form.nodeName || form.nodeName.toLowerCase() != "form") return null;
	
	// find changed elements
	var changed = [], n, c, def, o, ol, opt;
	for (var e = 0, el = form.elements.length; e < el; e++) {
		n = form.elements[e];
		c = false;
		
		switch (n.nodeName.toLowerCase()) {
		
			// select boxes
			case "select":
				def = 0;
				for (o = 0, ol = n.options.length; o < ol; o++) {
					opt = n.options[o];
					c = c || (opt.selected != opt.defaultSelected);
					if (opt.defaultSelected) def = o;
				}
				if (c && !n.multiple) c = (def != n.selectedIndex);
				break;
			
			// input / textarea
			case "textarea":
			case "input":
				
				switch (n.type.toLowerCase()) {
					case "checkbox":
					case "radio":
						// checkbox / radio
						c = (n.checked != n.defaultChecked);
						break;
					default:
						// standard values
						c = (n.value != n.defaultValue);
						break;				
				}
				break;
		}
		
		if (c) changed.push(n);
	}
	
	return changed;

}


function replaceAll( text, busca, reemplaza ){
	  while (text.toString().indexOf(busca) != -1)
	      text = text.toString().replace(busca,reemplaza);
	  return text;
}

function findParentForm(elem){ 
	var parent = elem.parentNode; 
	if(parent && parent.tagName != 'FORM'){
	parent = findParentForm(parent);
	}
	return parent;
	}




function selFecha(idobj){
	$('#'+idobj).datepick();
}



function getParentForm( elem )
	{
	var parentForm = findParentForm(elem);
	if(parentForm){
		//alert("Form found: ID = " + parentForm.id + " & Name = " +parentForm.name);
		return parentForm.name;
	}else{
		//alert("unable to locate parent Form");
	}

	}

/*function onKeyPresionadaNumericos(event){
	if (((event.which< 48) ||  (event.which> 57)) && (event.which!=8)) {
	    return false;
	}else{
		return true;

	}	
}*/

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




function AjaxUtils() {
}
AjaxUtils.load = function (elementId,target,data,callback) {
	
	jQuery('#'+elementId).load(target,data,function(responseText, textStatus, req){
		if (textStatus == 'error') { 
			//MaabUtils.removeWaitingIcon(elementId); 
		}
		if (callback) { 
			callback(responseText, textStatus, req); 
		}
	});
};

AjaxUtils.loadInto = function (elementId,target,data,callback) {
	
	if (callback) {$('#'+elementId).load(target,data,callback);}
	else {$('#'+elementId).load(target,data);}
};

AjaxUtils.loadIntoOnEdit = function (elementId,target,data,callback) {
	//alert(target);
	if (callback) {
		$.post(target,data,callback);
	}else {
		$('#'+elementId).load(target,data);
	}
};


AjaxUtils.loadIntoPopUp = function (elementId,target,data,widthValue,heightValue,optUrlBack,titulo) {
	try{
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
			element.html("");
			element.html(dataBack);
			element.dialog({height:heightValue,title:titulo,width:widthValue,modal:true},{close: function(event, ui) {
				if(optUrlBack!="")
					loadContent(optUrlBack);
				
				AjaxUtils.closePopUp(elementId,"content");
			}
			});
		}else{
			
			//AjaxUtils.showErrorMenssage(datos.model.mensajeUsuario,"error");
		}
	});
	}catch(exception){
		
		
	}

};

AjaxUtils.closePopUp = function (elementId, parentId) {
	

	try{
		var element = $('#'+elementId);
		//element.dialog('close');
		var $elemClone=element.dialog('destroy').clone();
		element.dialog('destroy').remove();
		element.remove();	
		if (parentId!=undefined){
			$elemClone.appendTo('#' + parentId);
		}
		
	}catch(exception){
		
		
	}
	
	
	
};


