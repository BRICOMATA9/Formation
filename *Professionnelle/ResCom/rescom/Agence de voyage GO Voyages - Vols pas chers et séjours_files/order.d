function sirData(){this.insertDataURL="js.sddan.com/ID.d?";this.searchKeywords='';this.keywords='';this.partnerID=0;this.siteID=0;this.subID=0;this.requestID='';this.userID='';this.country='';this.getParams={};this.userCat={};this.userIteratorFunc=[];this.customVar={};this.cookieData={};this.hashList={};this.service='';this.u='';this.ts=null;this.welcomeMediaHash='';};sirData.prototype.getDataURL=function(){return 'https://'+this.insertDataURL;};sirData.prototype.definePartner=function(partnerID,siteID,subID){this.partnerID=partnerID;this.siteID=siteID;this.subID=subID;};sirData.prototype.getALlKeywords=function(){return this.searchKeywords+' '+this.keywords;};sirData.prototype.addKeyword=function(keywords){this.keywords+=' '+keywords;};sirData.prototype.addSearchKeyword=function(keywords){this.searchKeywords+=' '+keywords;};sirData.prototype.callUrlIfBlock=function(blockPixelArray,notBlockPixelArray){var isOpera=!!window.opera||navigator.userAgent.indexOf(' OPR/')>=0;var isChrome=!!window.chrome&&!isOpera;var isFirefox=typeof InstallTrigger!=='undefined';if(isChrome||isFirefox){var htmlElem=document.createElement('div');htmlElem.className='AdvertContainer adblock annonces_google barre_adsense adBlock';htmlElem.style.height='1px';htmlElem.style.width='1px';htmlElem.style.opacity='0.1';document.body.appendChild(htmlElem);if(htmlElem.offsetHeight==0){for(var i=0;i<blockPixelArray.length;i++){this.callURL(blockPixelArray[i]);}}
else{for(var i=0;i<notBlockPixelArray.length;i++){this.callURL(notBlockPixelArray[i]);}}
htmlElem.parentNode.removeChild(htmlElem);}};sirData.prototype.httpCall=function(url){if(!url)
return;var i=new Image();i.src=url.substr(0,2048);};sirData.prototype.httpCallList=function(list){for(var c=0;c<list.length;c++){this.httpCall(list[c]);}};sirData.prototype.httpCallWithoutProtocol=function(url){var i=new Image('http://'+url);i.src=url.substr(0,2048);};sirData.prototype.sendData=function(uri){var img=new Image();img.src=this.getDataURL()+uri;};sirData.prototype.getMeta=function(name){var metas=document.getElementsByTagName('meta');for(var x=0,y=metas.length;x<y;x++){if(metas[x].name.toLowerCase()==name){return metas[x].content;}}
return '';};sirData.prototype.sendDataArg=function(args){var uri='';for(key in args){uri+=encodeURIComponent(key)+'='+encodeURIComponent(args[key])+'&';}
this.sendData(uri);};sirData.prototype.getURLKeywords=function(url){var r=new RegExp('^https?://([a-z.-0-9]+)/(.+)$','i');var found=r.exec(url);return '';};sirData.prototype.callURL=function(url){var i=new Image();i.src=url.substr(0,1023);};sirData.prototype.stripAccent=function(str){var rExps=[{re:/[\xC0-\xC6]/g,ch:'A'},{re:/[\xE0-\xE6]/g,ch:'a'},{re:/[\xC8-\xCB]/g,ch:'E'},{re:/[\xE8-\xEB]/g,ch:'e'},{re:/[\xCC-\xCF]/g,ch:'I'},{re:/[\xEC-\xEF]/g,ch:'i'},{re:/[\xD2-\xD6]/g,ch:'O'},{re:/[\xF2-\xF6]/g,ch:'o'},{re:/[\xD9-\xDC]/g,ch:'U'},{re:/[\xF9-\xFC]/g,ch:'u'},{re:/[\xD1]/g,ch:'N'},{re:/[\xF1]/g,ch:'n'}];for(var i=0,len=rExps.length;i<len;i++){str=str.replace(rExps[i].re,rExps[i].ch);}
return str;};sirData.prototype.run=function(){if(arguments.callee.done)return;arguments.callee.done=true;var args={ri:this.requestID,pa:this.partnerID,si:this.siteID,su:this.subID,userID:this.userID,v:1,t:this.stripAccent(document.title).replace(/[^\w -.]/g,'').replace(/[ ]{2,}/g,' ').substr(0,150),k:this.keywords,u:window.location.href,r:document.referrer,s:this.searchKeywords};};sirData.prototype.findHTMLNode=function(nodeInfo){for(i=0;i<nodeInfo.length;i++){if(nodeInfo[i].css==undefined)
return false;var found=this.getElementsBySelector(nodeInfo[i].css);if(found&&found.length>0){if(nodeInfo[i].item==undefined)
for(j=0;j<found.length;j++){this.addKeyword(found[j].text!=undefined?found[j].text:found[j].innerHTML);}
else if(nodeInfo[i].item!=undefined&&found[nodeInfo[i].item]!=undefined)
this.addKeyword(found[nodeInfo[i].item].text!=undefined?found[nodeInfo[i].item].text:found[nodeInfo[i].item].innerHTML);}
return false;}};sirData.prototype.getElementsBySelector=function(all_selectors){var selected=[];if(!document.getElementsByTagName)return selected;all_selectors=all_selectors.replace(/\s*([^\w])\s*/g,"$1");var selectors=all_selectors.split(",");var getElements=function(context,tag){if(!tag)tag='*';var found=new Array;for(var a=0,len=context.length;con=context[a],a<len;a++){var eles;if(tag=='*')eles=con.all?con.all:con.getElementsByTagName("*");else eles=con.getElementsByTagName(tag);for(var b=0,leng=eles.length;b<leng;b++){found.push(eles[b]);}}
return found;};COMMA:for(var i=0,len1=selectors.length;selector=selectors[i],i<len1;i++){var context=new Array(document);var inheriters=selector.split(" ");SPACE:for(var j=0,len2=inheriters.length;element=inheriters[j],j<len2;j++){var left_bracket=element.indexOf("[");var right_bracket=element.indexOf("]");var pos=element.indexOf("#");if(pos+1&&!(pos>left_bracket&&pos<right_bracket)){var parts=element.split("#");var tag=parts[0];var id=parts[1];var ele=document.getElementById(id);if(!ele||(tag&&ele.nodeName.toLowerCase()!=tag)){continue COMMA;}
context=new Array(ele);continue SPACE;}
pos=element.indexOf(".");if(pos+1&&!(pos>left_bracket&&pos<right_bracket)){var parts=element.split('.');var tag=parts[0];var class_name=parts[1];var found=getElements(context,tag);context=new Array;for(var l=0,len=found.length;fnd=found[l],l<len;l++){if(fnd.className&&fnd.className.match(new RegExp('(^|\s)'+class_name+'(\s|$)')))context.push(fnd);}
continue SPACE;}
if(element.indexOf('[')+1){if(element.match(/^(\w*)\[(\w+)([=~\|\^\$\*]?)=?['"]?([^\]'"]*)['"]?\]$/)){var tag=RegExp.$1;var attr=RegExp.$2;var operator=RegExp.$3;var value=RegExp.$4;}
var found=getElements(context,tag);context=new Array;for(var l=0,len=found.length;fnd=found[l],l<len;l++){if(operator=='='&&fnd.getAttribute(attr)!=value)continue;if(operator=='~'&&!fnd.getAttribute(attr).match(new RegExp('(^|\\s)'+value+'(\\s|$)')))continue;if(operator=='|'&&!fnd.getAttribute(attr).match(new RegExp('^'+value+'-?')))continue;if(operator=='^'&&fnd.getAttribute(attr).indexOf(value)!=0)continue;if(operator=='$'&&fnd.getAttribute(attr).lastIndexOf(value)!=(fnd.getAttribute(attr).length-value.length))continue;if(operator=='*'&&!(fnd.getAttribute(attr).indexOf(value)+1))continue;else if(!fnd.getAttribute(attr))continue;context.push(fnd);}
continue SPACE;}
var found=getElements(context,element);context=found;}
for(var o=0,len=context.length;o<len;o++){selected.push(context[o]);}}
return selected;};sirData.prototype.addAxciomTag=function(account_id){var tag=document.createElement('iframe');tag.name='_rlcdn';tag.width=0;tag.height=0;tag.src='https://rc.rlcdn.com/'+account_id+'.html';tag.frameborder=0;tag.style.display='none';this.appendElemToBody(tag);};sirData.prototype.addPixelInIframe=function(url){var tag=document.createElement('iframe');tag.width=0;tag.height=0;tag.src='https://ct.sddan.com/CT.d?imgUrl='+encodeURIComponent(url);tag.frameborder=0;tag.style.display='none';this.appendElemToBody(tag);};sirData.prototype.addIframeInIframe=function(url){var tag=document.createElement('iframe');tag.width=0;tag.height=0;tag.src='https://ct.sddan.com/CT.d?iframeUrl='+encodeURIComponent(url);tag.frameborder=0;tag.style.display='none';this.appendElemToBody(tag);};sirData.prototype.appendElemToBody=function(elem){if(document.getElementsByTagName('body').length>0){document.getElementsByTagName('body')[0].appendChild(elem);return;}
var scripts=document.getElementsByTagName("script");if(scripts.length>0&&scripts[0].parentNode!==undefined&&scripts[0].parentNode!=null){scripts[0].parentNode.appendChild(elem);}};sirData.prototype.getUserID=function(){return this.userID;};sirData.prototype.getPartnerID=function(){return this.partnerID;};sirData.prototype.setService=function(service){this.service=service;};sirData.prototype.getService=function(){return this.service;};sirData.prototype.setCountry=function(country){this.country=country;};sirData.prototype.getCountry=function(){return this.country;};sirData.prototype.setCity=function(city){this.city=city;};sirData.prototype.getCity=function(){return this.city;};sirData.prototype.setHash=function(type,value){this.hashList[type]=value;};sirData.prototype.getHash=function(type){return this.hashList[type]?this.hashList[type]:false;};sirData.prototype.setCookieData=function(data){this.cookieData=data;};sirData.prototype.getCookie=function(name){return this.cookieData[name]?this.cookieData[name]:false;};sirData.prototype.setGetParams=function(params){this.getParams=params;};sirData.prototype.getGetParams=function(){return this.getParams;};sirData.prototype.getGetParam=function(paramName){return this.getParams[paramName]!==undefined?this.getParams[paramName]:false;};sirData.prototype.getIntGetParam=function(paramName){var val=this.getGetParam(paramName);if(!val)
return false;val.replace(',','.').replace(/[^0-9.]/,'');return val?parseFloat(val):false;};sirData.prototype.getUserCat=function(catID){return this.userCat[catID]!==undefined?this.userCat[catID]:false;};sirData.prototype.setUserData=function(userData){this.userData=userData;this.userCat={};for(var i=0;i<userData.categories.length;i++){this.userCat[userData.categories[i].id]=userData.categories[i].cat;}};sirData.prototype.getUserCats=function(){return this.userCat;};sirData.prototype.getUrl=function(){if(this.getGetParam('u'))
return this.getGetParam('u');if(this.getGetParam('r'))
return this.getGetParam('r');return this.u;};sirData.prototype.addUserCatIterator=function(func,beforeExec,afterExec){this.userIteratorFunc.push([func,beforeExec,afterExec]);};sirData.prototype.executeUserCatIteratorFunc=function(){for(var c=0;c<this.userIteratorFunc.length;c++){if(this.userIteratorFunc[c][1])
this.userIteratorFunc[c][1]();}
for(var catID in this.userCat){for(var c=0;c<this.userIteratorFunc.length;c++)
if(this.userIteratorFunc[c][0])
this.userIteratorFunc[c][0](catID,this.userCat[catID]);}
for(var c=0;c<this.userIteratorFunc.length;c++){if(this.userIteratorFunc[c][2])
this.userIteratorFunc[c][2]();}};var _sdAD=new sirData();_sdAD.insertDataURL='/GS.d?';_sdAD.userID="20190506_8499d50ffdc22b8dcf514f4c6fd967ba";_sdAD.partner_id="22523";_sdAD.setService('order.d');_sdAD.setGetParams({"cat_name":"","command_amount":"{orderAmount}","ht_paid":"1","pa":"22523","r":"https://www.govoyages.com/travel/?landingPageType=PASSENGER\u0026locale=fr_FR\u0026mktportal=jetcost\u0026searchId=55923407515\u0026searchMainProductTypeName=FLIGHT\u0026testToken=1%231769951880-2%23146-3%234012-4%2333231-5%23542439174-6%2334362-7%23108487-8%2321241867293-9%2340983293347-10%2311397618687-11%2322215798871-12%2340626740826-13%2339739419014-14%2346525051812-15%2388588740463-16%2348016225072-17%2352721078370-18%2389369785565-19%2349587740256-20%2389350815336-21%2358096230788-22%2355778047071\u0026testTokenDate=2019-05-06T17%3A09%3A33\u0026utm_campaign=PAR-BIQ\u0026utm_medium=metasearch\u0026utm_source=jetcost\u0026utm_term=flight\u0026fareItineraryKey=11,1A\u0026segmentKey0=0,AF7654\u0026segmentKey1=1,AF7491\u0026utm_content=FFR","u":"https://www.govoyages.com/travel/secure/?landingPageType=PASSENGER\u0026locale=fr_FR\u0026mktportal=jetcost\u0026searchId=55923407515\u0026searchMainProductTypeName=FLIGHT\u0026testToken=1%231769951880-2%23146-3%234012-4%2333231-5%23542439174-6%2334362-7%23108487-8%2321241867293-9%2340983293347-10%2311397618687-11%2322215798871-12%2340626740826-13%2339739419014-14%2346525051812-15%2388588740463-16%2348016225072-17%2352721078370-18%2389369785565-19%2349587740256-20%2389350815336-21%2358096230788-22%2355778047071\u0026testTokenDate=2019-05-06T17%3A09%3A33\u0026fareItineraryKey=11,1A\u0026segmentKey0=0,AF7654\u0026segmentKey1=1,AF7491#payment/"});_sdAD.setCountry("FR");_sdAD.setCookieData({});_sdAD.setUserData({"id":"20190506_8499d50ffdc22b8dcf514f4c6fd967ba","segments":[],"categories":[],"mapping_key":null,"mapping_keys":null});_sdAD.u="https://www.govoyages.com/travel/secure/?landingPageType=PASSENGER\u0026locale=fr_FR\u0026mktportal=jetcost\u0026searchId=55923407515\u0026searchMainProductTypeName=FLIGHT\u0026testToken=1%231769951880-2%23146-3%234012-4%2333231-5%23542439174-6%2334362-7%23108487-8%2321241867293-9%2340983293347-10%2311397618687-11%2322215798871-12%2340626740826-13%2339739419014-14%2346525051812-15%2388588740463-16%2348016225072-17%2352721078370-18%2389369785565-19%2349587740256-20%2389350815336-21%2358096230788-22%2355778047071\u0026testTokenDate=2019-05-06T17%3A09%3A33\u0026fareItineraryKey=11,1A\u0026segmentKey0=0,AF7654\u0026segmentKey1=1,AF7491#payment/";_sdAD.run();_sdAD.httpCall("https://secure.adnxs.com/seg?add=3149919:22523&t=2&redir=");