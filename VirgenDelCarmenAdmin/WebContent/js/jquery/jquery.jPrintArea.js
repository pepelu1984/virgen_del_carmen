/*
 * jQuery J Plugin
 * version: 2.2 (2009-09-20)
 * jPrintArea 1.1.0
 * Submitted by designerkamal on September 17, 2007 - 8:02am
 * http://plugins.jquery.com/node/517/release?order=file_name&sort=asc
 * Last updated: September 20, 2011 - 8:02am
 * By: Jorge Casaverde Huatuco
 */
/*jQuery.jPrintArea=function(el)
{
var iframe=document.createElement('IFRAME');
var doc=null;
//$(iframe).attr('style','position:absolute;width:0px;height:0px;left:-500px;top:-500px;');
$(iframe).attr('style','position:absolute;width:300px;height:300px;left:60px;top:50px;');
document.body.appendChild(iframe);
doc=iframe.contentWindow.document;
var links=window.document.getElementsByTagName('link');
for(var i=0;i<links.length;i++)
if(links[i].rel.toLowerCase()=='stylesheet')
doc.write('<link type="text/css" rel="stylesheet" href="'+links[i].href+'"></link>');
doc.write('<div class="'+$(el).attr("class")+'">'+$(el).html()+'</div>');
doc.close();
iframe.contentWindow.focus();
iframe.contentWindow.print();
document.body.removeChild(iframe);
}*/

jQuery.jPrintArea=function(el)
{
var doc=null;
var popupOrIframe = window.open('about:blank', 'printElement2Window', 'dependent=yes,resizable=no,width=5,height=5,scrollbars=no,status=no,menubar=no,titlebar=no,toolbar=no,left=0,top=0');
doc = popupOrIframe.document;
var links=window.document.getElementsByTagName('link');
for(var i=0;i<links.length;i++)
if(links[i].rel.toLowerCase()=='stylesheet')
doc.write('<html><head><link type="text/css" rel="stylesheet" href="'+links[i].href+'"></link>');
doc.write('<script type="text/javascript">function printPage(){focus();print();close();');
doc.write('}</s');
doc.write('cript></head><body ');
doc.write('cript></head><body class="bodyNotFound">');
doc.write('<div class="bodyNotFound '+$(el).attr("class")+'">'+$(el).html()+'</div>');
doc.write('</body></html>');
doc.close();
_callPrintPage(popupOrIframe);
};
function _callPrintPage(element) {
    if (element && element["printPage"])
        element["printPage"]();
    else
        setTimeout(function () {
            _callPrint(element);
        }, 50);
}