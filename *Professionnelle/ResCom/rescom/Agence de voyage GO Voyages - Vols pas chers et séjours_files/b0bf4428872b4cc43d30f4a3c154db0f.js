Bootstrapper.bindDOMParsed(function(){var Bootstrapper=window["Bootstrapper"];var ensightenOptions=Bootstrapper.ensightenOptions;window._boxeverq=window._boxeverq||[];_boxeverq.push(function(){if(!BoxeverNS.areRequiredFieldsPresent())return;var event={type:"IDENTITY",page:tmParam.page_type,browser_id:Boxever.getID(),pos:BoxeverNS.getPos(),channel:BoxeverNS.getChannel(),language:tmParam.language.toUpperCase(),currency:tmParam.currency_code.toUpperCase(),firstname:tmParam.custom_name,lastname:tmParam.custom_surname,
email:tmParam.custom_email,gender:tmParam.pax_gender.split(",")[0].toLowerCase()||undefined,mobile:tmParam.custom_tel||undefined,street:BoxeverNS.getStreet(),city:tmParam.custom_city||undefined,country:tmParam.custom_country||undefined,zip_code:tmParam.postal_code||undefined,registered_user_id:tmParam.reg_user_id||undefined,register_date:BoxeverNS.getRegisterDate()};Boxever.eventCreate(event,function(_data){var event={type:"SUBSCRIPTION",action:"SOFT_SUBSCRIBE",page:tmParam.page_type,browser_id:Boxever.getID(),
pos:BoxeverNS.getPos(),channel:BoxeverNS.getChannel(),language:tmParam.language.toUpperCase(),currency:tmParam.currency_code.toUpperCase(),customer:{email:tmParam.custom_email},subscription:{name:"BookingSearch",pos:BoxeverNS.getPos(),channel:"EMAIL"}};Boxever.eventCreate(event,function(){},"json")},"json")})},2519399,480968);
Bootstrapper.bindDOMParsed(function(){var Bootstrapper=window["Bootstrapper"];var ensightenOptions=Bootstrapper.ensightenOptions;(function(bx){bx.getMktChannel=function(){var marketing_channel=tmParam.mkt_channel;var portalLookup={"google":"Paid search non branded","google-plus-one":"Paid search non branded","bing":"Paid search non branded","yandex":"Paid search non branded","google-brand":"Paid search branded","google-brand-plus-one":"Paid search branded","bing-brand":"Paid search branded","yandex-brand":"Paid search branded",
"google-content":"Paid search content"};var utmMediumLookup={"display":"Online display","retargeting":"Retargeting","organic":"SEO non branded","crm":"CRM","referral":function(){return/.*mail.*/.test(tmParam.utm_source)?"CRM":undefined},"metasearch":"Metasearch","affiliates":"Affiliates networks","partners":"Partners","social":"Social media","none":function(){return/direct/.test(tmParam.utm_source)?"Direct":undefined},"(none)":function(){return/\(direct\)/.test(tmParam.utm_source)?"Direct":undefined}};
marketing_channel=marketing_channel?marketing_channel:portalLookup[tmParam.mkt_portal];marketing_channel=marketing_channel?marketing_channel:utmMediumLookup[tmParam.utm_medium];marketing_channel=typeof marketing_channel!="function"?marketing_channel:marketing_channel();return marketing_channel||"Other"};bx.areRequiredFieldsPresent=function(){return tmParam.brand&&tmParam.market&&tmParam.language&&tmParam.currency_code&&tmParam.device};bx.getPos=function(){var pos;var brandLookup={"E":"edreams.","O":"opodo.",
"G":"govoyages."};var brand=brandLookup[tmParam.brand];if(brand===undefined)throw'The data layer field "brand" contains unexpected value "'+tmParam.brand+'"';pos=brand+tmParam.market.toLowerCase();return pos};bx.addUTMParams=function(event){Boxever.addUTMParams(event);for(var k in tmParam)if(tmParam.hasOwnProperty(k)&&typeof k==="string"&&k.toLowerCase().indexOf("utm_")==0&&tmParam[k])event[k]=tmParam[k];return event};bx.getChannel=function(){return tmParam.device=="D"?"WEB":tmParam.device=="M"?"MOBILE":
tmParam.device=="T"?"TABLET":undefined};bx.getFlightName=function(){return"FI_"+tmParam.departure_airport_code+"-"+tmParam.arrival_airport_code};bx.getFlightType=function(){return tmParam.flight_type=="o"?"OW":tmParam.flight_type=="r"?"RT":tmParam.flight_type=="m"?"OJ":undefined};bx.getStreet=function(){return tmParam.custom_adress?[tmParam.custom_adress]:undefined};bx.getRegisterDate=function(){return tmParam.reg_year&&tmParam.reg_month&&tmParam.reg_day?tmParam.reg_year+"-"+tmParam.reg_month+"-"+
tmParam.reg_day+"T00:00":undefined};bx.getDob=function(pax_dobs,i){return pax_dobs&&pax_dobs[i].trim()?pax_dobs[i].trim()+"T00:00":undefined};bx.getStartDate=function(){return tmParam.departure_date+"T00:00"};bx.getEndDate=function(){return tmParam.return_date?tmParam.return_date+"T00:00":tmParam.departure_date+"T00:00"}})(window.BoxeverNS=window.BoxeverNS||{});(function(){window._boxever_settings={client_key:"grY1NOtKhm4xhDV9rsny82DvKa1iYFBN",target:"https://api.boxever.com/v1.2",cookie_domain:"",
cross_domain:{url:"https://api.boxever.com/v1.2/boxever-cross-domain.html",origin:"https://api.boxever.com"}};javascriptLibraryVersion="1.3.5";window._boxeverq=window._boxeverq||[];var s=document.createElement("script");s.type="text/javascript";s.async=true;s.src="https://d1mj578wat5n4o.cloudfront.net/boxever-"+javascriptLibraryVersion+".min.js";var x=document.getElementsByTagName("script")[0];x.parentNode.insertBefore(s,x)})()},2841526,480944);
Bootstrapper.bindImmediate(function(){var Bootstrapper=window["Bootstrapper"];var ensightenOptions=Bootstrapper.ensightenOptions;(function(){var siteId="a39499f57b93";function t(t,e){for(var n=t.split(""),r=0;r<n.length;++r)n[r]=String.fromCharCode(n[r].charCodeAt(0)+e);return n.join("")}function e(e){return t(e,-h).replace(/%SN%/g,siteId)}function n(t){for(var e=escape(t)+"\x3d",n=document.cookie.split(";"),r=0;r<n.length;r++){for(var o=n[r];" "==o.charAt(0);)o=o.substring(1,o.length);if(0===o.indexOf(e))return unescape(o.substring(e.length,
o.length))}return null}function r(t,e){var r=new Date;r.setTime(r.getTime()+15768E7);var o,i,a,u="; expires\x3d"+r.toGMTString();if(a=location.host,1===a.split(".").length)document.cookie=escape(t)+"\x3d"+escape(e)+u+"; path\x3d/";else{i=a.split("."),i.shift(),o="."+i.join("."),document.cookie=escape(t)+"\x3d"+escape(e)+u+"; path\x3d/; domain\x3d"+o;var c=n(t);null!=c&&c==e||(o="."+a,document.cookie=escape(t)+"\x3d"+escape(e)+u+"; path\x3d/; domain\x3d"+o)}}function o(t){x.ex=t,p(x)}function i(t,
e,n){var r=document.createElement("script");r.onerror=n,r.onload=e,r.type="text/javascript",r.id="ftr__script",r.async=!0,r.src="https://"+t;var o=document.getElementsByTagName("script")[0];o.parentNode.insertBefore(r,o)}function a(){U(S.uAL),setTimeout(u,v,S.uAL)}function u(t){try{var e=t===S.uDF?m:g;i(e,function(){k(),x.ex=t+S.uS,p(x)},function(){try{k(),x.td=1*new Date-x.ts,x.ex=t+S.uF,p(x),t===S.uDF&&a()}catch(e){x.ex=t+S.eUoe,p(x)}})}catch(e){x.ex=t+S.eTlu,p(x)}}var c="fort",s="erTo",d="ken",
f=c+s+d,l="6",h=3,m=e("(VQ(1fgq71iruwhu1frp2vq2(VQ(2vfulsw1mv"),g=e("g68x4yj4t5;e6z1forxgiurqw1qhw2vq2(VQ(2vfulsw1mv"),v=10;window.ftr__startScriptLoad=1*new Date;var p=function(t){var e=function(t){return t||""},n=e(t.id)+"_"+e(t.ts)+"_"+e(t.td)+"_"+e(t.ex)+"_"+e(l);r(f,n)},w=function(){str=n(f)||"";var t=str.split("_"),e=function(e){return t[e]||void 0};return{id:e(0),ts:e(1),td:e(2),ex:e(3),vr:e(4)}},T=function(){for(var t={},e="fgu",n=[],r=0;r<256;r++)n[r]=(r<16?"0":"")+r.toString(16);var o=function(t,
e,r,o,i){var a=i?"-":"";return n[255&t]+n[t>>8&255]+n[t>>16&255]+n[t>>24&255]+a+n[255&e]+n[e>>8&255]+a+n[e>>16&15|64]+n[e>>24&255]+a+n[63&r|128]+n[r>>8&255]+a+n[r>>16&255]+n[r>>24&255]+n[255&o]+n[o>>8&255]+n[o>>16&255]+n[o>>24&255]},i=function(){if(window.Uint32Array&&window.crypto&&window.crypto.getRandomValues){var t=new window.Uint32Array(4);return window.crypto.getRandomValues(t),{d0:t[0],d1:t[1],d2:t[2],d3:t[3]}}return{d0:4294967296*Math.random()>>>0,d1:4294967296*Math.random()>>>0,d2:4294967296*
Math.random()>>>0,d3:4294967296*Math.random()>>>0}},a=function(){var t="",e=function(t,e){for(var n="",r=t;r>0;--r)n+=e.charAt(1E3*Math.random()%e.length);return n};return t+=e(2,"0123456789"),t+=e(1,"123456789"),t+=e(8,"0123456789")};return t.safeGenerateNoDash=function(){try{var t=i();return o(t.d0,t.d1,t.d2,t.d3,!1)}catch(t){try{return e+a()}catch(t){}}},t.isValidNumericalToken=function(t){return t&&t.toString().length<=11&&t.length>=9&&parseInt(t,10).toString().length<=11&&parseInt(t,10).toString().length>=
9},t.isValidUUIDToken=function(t){return t&&32===t.toString().length&&/^[\d\w]+$/.test(t)},t.isValidFGUToken=function(t){return 0==t.indexOf(e)&&t.length>=12},t}(),S={uDF:"UDF",uAL:"UAL",mLd:"1",eTlu:"2",eUoe:"3",uS:"4",uF:"9",tmos:["T5","T10","T15","T30","T60"],tmosSecs:[5,10,15,30,60]},y=function(t,e){for(var n=S.tmos,r=0;r<n.length;r++)if(t+n[r]===e)return!0;return!1};try{var x=w();try{x.id&&(T.isValidNumericalToken(x.id)||T.isValidUUIDToken(x.id)||T.isValidFGUToken(x.id))||(x.id=T.safeGenerateNoDash()),
x.ts=window.ftr__startScriptLoad,p(x);var D=new Array(S.tmosSecs.length),U=function(t){for(var e=0;e<S.tmosSecs.length;e++)D[e]=setTimeout(o,1E3*S.tmosSecs[e],t+S.tmos[e])},k=function(){for(var t=0;t<S.tmosSecs.length;t++)clearTimeout(D[t])};y(S.uDF,x.ex)?a():(U(S.uDF),setTimeout(u,v,S.uDF))}catch(t){x.ex=S.mLd,p(x)}}catch(t){}})()},2323130,520647);
Bootstrapper.bindDOMParsed(function(){var Bootstrapper=window["Bootstrapper"];var ensightenOptions=Bootstrapper.ensightenOptions;window._boxeverq=window._boxeverq||[];_boxeverq.push(function(){if(!BoxeverNS.areRequiredFieldsPresent())return;if(/true/.test(tmParam.ancil_bag)){var event={type:"ADD",page:tmParam.page_type,browser_id:Boxever.getID(),pos:BoxeverNS.getPos(),channel:BoxeverNS.getChannel(),language:tmParam.language.toUpperCase(),currency:tmParam.currency_code.toUpperCase(),product:{type:"BAGGAGE",
item_id:"BAGGAGE",sku:"bag",name:"BAGGAGE",currency:tmParam.currency_code.toUpperCase(),price:tmParam.ancil_bag_price&&/true/.test(tmParam.ancil_bag)?Number(tmParam.ancil_bag_price):0}};Boxever.eventCreate(event,function(_data){},"json")}})},2741044,480967);
Bootstrapper.bindDOMParsed(function(){var Bootstrapper=window["Bootstrapper"];var ensightenOptions=Bootstrapper.ensightenOptions;window._boxeverq=window._boxeverq||[];_boxeverq.push(function(){if(!BoxeverNS.areRequiredFieldsPresent())return;var event={type:"VIEW",page:tmParam.page_type,browser_id:Boxever.getID(),pos:BoxeverNS.getPos(),channel:BoxeverNS.getChannel(),language:tmParam.language.toUpperCase(),currency:tmParam.currency_code.toUpperCase(),marketing_channel:BoxeverNS.getMktChannel()};Boxever.eventCreate(BoxeverNS.addUTMParams(event),
function(_data){},"json")})},2844036,480971);
Bootstrapper.bindDOMParsed(function(){var Bootstrapper=window["Bootstrapper"];var ensightenOptions=Bootstrapper.ensightenOptions;Bootstrapper.data.resolve(["56831","56830"],function(manage_56831,manage_56830){window._boxeverq=window._boxeverq||[];_boxeverq.push(function(){if(!BoxeverNS.areRequiredFieldsPresent())return;var event={type:"UPDATE",page:tmParam.page_type,browser_id:Boxever.getID(),pos:BoxeverNS.getPos(),channel:BoxeverNS.getChannel(),language:tmParam.language.toUpperCase(),currency:tmParam.currency_code.toUpperCase(),
session_data:{searchId:Number(tmParam.searchId),fareItineraryKey:tmParam.fareItineraryKey,OriginCityName:tmParam.departure_city_name,OriginCountryName:tmParam.dep_country_name,DestinationCityName:tmParam.arrival_city_name,DestinationCountryName:tmParam.arr_country_name,prime_price:manage_56831,non_prime_price:manage_56830},product:{item_id:"FLIGHT",promo_code:tmParam.promo_code||undefined,consumer:function(){var pax=[];var pax_types=tmParam.pax_type.split(",");var pax_titles=tmParam.pax_title.split(",");
var pax_firstnames=tmParam.pax_name.split(",");var pax_lastnames=tmParam.pax_surname.split(",");var pax_dobs=tmParam.pax_birthday?tmParam.pax_birthday.split(","):undefined;var pax_ffns=tmParam.pax_number?tmParam.pax_number.split(","):undefined;var pax_nationalitys=tmParam.pax_nation?tmParam.pax_nation.split(","):undefined;var pax_p_nos=tmParam.pax_document_number?tmParam.pax_document_number.split(","):undefined;var pax_p_e_dts=tmParam.pax_document_expiry?tmParam.pax_document_expiry.split(","):undefined;
var pax_genders=tmParam.pax_gender?tmParam.pax_gender.split(","):undefined;var pax_d_types=tmParam.pax_document_type?tmParam.pax_document_type.split(","):undefined;if(pax_titles.length!=pax_firstnames.length||pax_titles.length!=pax_lastnames.length||pax_titles.length!=pax_types.length||pax_dobs&&pax_titles.length!=pax_dobs.length||pax_ffns&&pax_titles.length!=pax_ffns.length||pax_nationalitys&&pax_titles.length!=pax_nationalitys.length||pax_p_nos&&pax_titles.length!=pax_p_nos.length||pax_p_e_dts&&
pax_titles.length!=pax_p_e_dts.length||pax_genders&&pax_titles.length!=pax_genders.length||pax_d_types&&pax_titles.length!=pax_d_types.length)throw"data in passenger related fields does not split equally between passengers";for(var i=0;i<pax_titles.length;i++){var c={pax_type:pax_types[i].trim(),title:pax_titles[i].trim(),firstname:pax_firstnames[i].trim(),lastname:pax_lastnames[i].trim(),dob:BoxeverNS.getDob(pax_dobs,i),ffn:pax_ffns&&pax_ffns[i].trim()||undefined,nationality:pax_nationalitys&&pax_nationalitys[i].trim()||
undefined,passport_no:pax_p_nos&&pax_p_nos[i].trim()||undefined,passport_expire_date:pax_p_e_dts&&pax_p_e_dts[i].trim()||undefined,gender:pax_genders&&pax_genders[i].trim().toLowerCase()||undefined,doc_type:pax_d_types&&pax_d_types[i].trim()||undefined};pax.push(c)}return pax}()}};Boxever.eventCreate(event,function(_data){},"json")})})},2857648,480969);