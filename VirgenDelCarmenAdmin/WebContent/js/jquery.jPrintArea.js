/*
 * jQuery J Plugin
 * version: 2.1 (2009-08-14)
 * jPrintArea 1.0.0
 * Submitted by designerkamal on September 17, 2007 - 8:02am
 * http://plugins.jquery.com/node/517/release?order=file_name&sort=asc
 * Last updated: September 17, 2007 - 8:02am
 */
jQuery.jPrintArea=function(el)
{
var iframe=document.createElement('IFRAME');
var doc=null;
$(iframe).attr('style','position:absolute;width:0px;height:0px;left:-500px;top:-500px;');
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
//alert('Printing...');
document.body.removeChild(iframe);
}