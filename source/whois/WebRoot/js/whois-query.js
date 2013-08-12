
var respJsonObject = null;

$(ready);
function ready(){
	var jsonStr = $("#maincontent").text(); //Access to the server to return information
	
	if(jsonStr == "" || jsonStr == "null") {
		$("#maincontent").html(""); 
		return;
	}

	respJsonObject = eval("("+jsonStr+")");//Information displayed in tabular form to the page
	toHtmlFormat();
}
	
function isStandalone(){
	for(var key in respJsonObject){
		if (key.indexOf("$mul$") >= 0){
			return false;
		} 
	}
	return true;	
}

function toHtmlFormat() {
	var table = $("<font>");
	if (isStandalone()){
		table = $("<table><tr><th colspan='2'>Whois Information</th></tr>");
		table.append(toTable(respJsonObject));
		table.append("</table>");
	}else{
		table.append(toStandaloneTable());
	}

	
	$("#maincontent").html(table);
}
function toTable(JsonObject){
	var urlhead = window.location.protocol + "//" + window.location.host + $("#pathUrl").val() + "/";
	var tableStr = '';
	for(var keyName in JsonObject){//According to the type of the field name to do the corresponding analytical
		var values = JsonObject[keyName];
		if (keyName == "phones"   || keyName == "notices" || keyName == "delegationKeys" ||
			keyName == "variants" || keyName == "postalAddress" || keyName == "remarks" || keyName == "events" ){
			
			tableStr += toCommonTable(urlhead, values, keyName, keyName + "Id");
		} else if(keyName == "nameServer"){
			
			tableStr += toCommonTable(urlhead, values, "nameserver", "LdhName");
		}else if(keyName == "secureDNS"){			
			tableStr += toCommonTable(urlhead, values, "secureDNS", "secureDNSID");
		}else if(keyName == "dsData"){			
			tableStr += toCommonTable(urlhead, values, "dsData", "dsDataID");
		}else if(keyName == "keyData"){			
			tableStr += toCommonTable(urlhead, values, "keyData", "keyDataID");
		}else if(keyName == "entities"){
			
			tableStr += toEntityTable(urlhead, values, "entity", "Roles", "Handle");
		}else if(keyName == "links"){
		
			tableStr += toSpecialTable(values, keyName, "Value", "Href", "");
		}else if(keyName == "registrar"){
			
			tableStr += toSpecialTable(values, keyName, "Entity Names", "Handle", urlhead + "registrar" + "/");
		}else if(keyName == "vCard"){
			tableStr += toVcardTable(values) ;
		}else if(keyName == "ipAddresses"){
			tableStr += toIPAddresses(values, keyName, "v4", "v6");
		}else if(keyName == "publicIds"){
			tableStr += toPublicIds(values, keyName);
		}else if(keyName != "rdapConformance" && keyName != '0'){			
			tableStr += "<tr><td width='20%'>"+keyName+"</td><td>"+values+"&nbsp;</td></tr>";
		}
	}
	return tableStr;
}

function toPublicIds(values, keyName){
	tableStr = "";
	for(var i = 0; i < values.length; i++){
		var realValue = "type: " + values[i]["type"] + "<br/> identifier: " + values[i]["identifier"];
		tableStr += "<tr><td width='20%'>" + keyName + "</td><td>" + realValue + "</td></tr>";
	}
	return tableStr;
}
function toIPAddresses(values, keyName, keyV4, keyV6){
	var realValue = "v4: " + values["v4"] + "<br/> v6: " + values["v6"];
	tableStr = "<tr><td width='20%'>" + keyName + "</td><td>" + realValue + "</td></tr>";
	return tableStr;
}

function toCommonTable(urlhead, values, keyName, attrName) {
	var tableStr = "";
	
	if(keyName == "events"){
		for(var keychild in values){
			var displayValues = values[keychild][attrName];
			if(displayValues == undefined){
				displayValues =  values[attrName];
				var realValue = "Event Action: " + values["EventAction"] + "<br/> Event Actor: " + values["EventActor"] 
					+ "<br/> Event Date: " + values["EventDate"];
				tableStr = "<tr><td width='20%'>" + keyName + "</td><td>" + realValue + "</td></tr>";
				break;
			}else{
				var realValue = "Event Action: " + values[keychild]["EventAction"] + "<br/> Event Actor: " + values[keychild]["EventActor"] 
				+ "<br/> Event Date: " + values[keychild]["EventDate"];
				tableStr += "<tr><td width='20%'>" + keyName + "</td><td>" + realValue + "&nbsp;</td></tr>";
			}
		}
	}else{
		for(var keychild in values){
			var displayValues = values[keychild][attrName];
			
			if(displayValues == undefined){
				displayValues =  values[attrName];
				var realValue = urlhead + keyName+ "/" + displayValues;
				realValue = "<a href=" + realValue + ">" + realValue;
				tableStr = "<tr><td width='20%'>" + keyName + "</td><td>" + realValue + "</td></tr>";
				break;
			}else{
				var realValue = urlhead + keyName + "/" + displayValues;
				realValue = "<a href=" + realValue + ">" + realValue;
				tableStr += "<tr><td width='20%'>" + keyName + "</td><td>" + realValue + "&nbsp;</td></tr>"
			}
		}
	}
	return tableStr;
}
function toVcardTable(values) {
	var tableStr = "";
	var value = (values+"").split(",");
	var keyName = "";
	for(var i = 0; i < value.length; i++){
		
		if(value[i] == "version"){
			keyName = "Version";
		}else if(value[i] == "fn"){
			keyName = "Entity Name";
		}else if(value[i] == "label"){
			var keys = new Array("Street", "Street1",
					"Street2", "City", "SP", "Postal Code", "County Code");
			var index = 0;
			for(var j = i + 3; j < i + 10; j++){
				tableStr += "<tr><td width='20%'>" + keys[index] + "</td><td>" + value[j] + "&nbsp;</td></tr>"
				index++;
			}
			i = i + 9;
			continue;
		}else if(value[i] == "email"){
			keyName = "Email";
		}else if(value[i] == "tel"){
			if(value[i+1].indexOf("work") != -1){
				keyName = "Office";
			}else if(value[i+1].indexOf("fax") != -1){
				keyName = "Fax";
			}else if(value[i+1].indexOf("cell") != -1){
				keyName = "Moblie";
			}else{
				keyName = "phonesId";
			}
		}
		i = i + 3;
		if(keyName != "phonesId"){
			tableStr += "<tr><td width='20%'>" + keyName + "</td><td>" + value[i] + "&nbsp;</td></tr>"
		}
		
	}
	
	return tableStr;
}
function toSpecialTable(values, keyName, dispAttrName, realAttrName, prefix) {
	var tableStr = "";
	
	for(var keychild in values){
		var displayValues = values[keychild][dispAttrName];
		
		if(displayValues == undefined){
			displayValues =  values[dispAttrName];
			var realValue = "<a href="+ prefix + values[realAttrName]+">" + displayValues + "</a>";
			tableStr = "<tr><td width='20%'>" + keyName + "</td><td>" + realValue + "</td></tr>";
			break;
		}else{
			var realValue = "<a href="+ prefix + values[keychild][realAttrName] + ">" + displayValues + "</a>";
			tableStr += "<tr><td width='20%'>" + keyName + "</td><td>" + realValue + "&nbsp;</td></tr>"
		}
	}
	
	return tableStr;
}

function toEntityTable(urlhead, values, keyName, dispAttrName, realAttrName) {
	var tableStr = "";
	
	for(var keychild in values){
		var realValue = "";
		var roleArray = values[keychild][dispAttrName];
		
		if(roleArray == undefined){
			roleArray =  values[dispAttrName];
			var realValueHref = "<a href="+ urlhead + "entity" + "/" + values[realAttrName] + ">";
			
			for(var i = 0;i < roleArray.length;i++){
				realValue += realValueHref + roleArray[i].toLocaleUpperCase() + "</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"
			}
			tableStr = "<tr><td width='20%'>" + keyName + "</td><td>" + realValue + "</td></tr>";
			break;
		}else{
			var realValueHref = "<a href="+ urlhead + "entity" + "/" + values[keychild][realAttrName] + ">";
			
			for(var i = 0;i < roleArray.length;i++){
				realValue += realValueHref + roleArray[i].toLocaleUpperCase() + "</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"
			}
			tableStr += "<tr><td width='20%'>" + keyName + "</td><td>" + realValue + "&nbsp;</td></tr>"
		}
	}
	return tableStr;
}

function toStandaloneTable() {
	var prx = "$mul$";
	var tableStr = "";
	for(var key in respJsonObject){
		
		var dispalykeyName = key.substring(prx.length);
		var values = respJsonObject[key];
		if(values.length >= 2){
			if(dispalykeyName == "IP"){
				tableStr = "<table><tr><th colspan='2'>Whois Information</th></tr>";
					for(var keychilds in values){
						for(var keychild in values[keychilds]){
							if(keychild == 'links'){
								tableStr += toSpecialTable(values[keychilds][keychild], dispalykeyName, "Value", "Href", "");
							}
						}
				
					}
					tableStr += "</table>";
			}else{
				for(var keychilds in values){
					tableStr += "<table><tr><th colspan='2'>Whois Information</th></tr>" + toTable(values[keychilds]) + "</table>";
				}
			}
			
		}else{
			tableStr = "<table><tr><th colspan='2'>Whois Information</th></tr>";
			for(var keyName in values){
				var value = values[keyName];
					tableStr += toTable(value);
				}
			tableStr += "</table>";
		}
	}		
	return tableStr;
}

function processQuery() {
	var queryInfo = $("#queryInfo").val();
	var queryType = $('input:radio[name="optionType"]:checked').val();
	var formatType = $('input:radio[name="showType"]:checked').val();
	var matchStr =  /^(\w+)|([\u0391-\uFFE5]+)([\w\-\.]*)$/g;
	
	if (queryInfo == "") {
		alert("Please enter a query data");
		return false;
	}
	
	if (queryType == null) {
		alert("Please select the type of query");
		return false;
    }
	
	if (queryType == "ip") {
		if (!checkIP(queryInfo)){
	    	alert("Please input correct IP");
	        return false;
		} 
	} else if (queryType == "autnum"){
		if (!checkAS(queryInfo)){
			return false;
		} 
	}else if(!queryInfo.match(matchStr)){
		alert("Please input correct param");
		return false;
	}
	
	if(formatType == undefined){
		alert("Please select the retrun type");
		return false;
	}
	
	respJsonObject = null;	
	//formatType = "xml";
	document.cookie="Format=application/"+formatType+";path=/"; 
	var urlContextPath = $("#pathUrl").val() + "/"; 
	var url  = urlContextPath + queryType + "/" + queryInfo;
	
//	alert(formatType);
//	if(formatType == 'json'){
//		$.ajax({
//			type : "get",
//			async : true,
//			url : url,
//			accepts: {json: 'application/'+ formatType},
//			dataType : "json",
//			success: getJSON
//		});
//	} else {
		window.location.href = url;
//	}
}
function getJSON(data){
	//var str = JSON.parse(data);
	$("#jsoncontent").text(JSON.stringify(data, null, 2));
	$("#maincontent").html("");
}

function checkAS(queryinfo) {
	var pattern = /^\d+$/;
		
	if (queryinfo.match(pattern)){
		return true;
	} else {
  		alert("please input correct AutonomousNumber");
  		return false;
	}
}
	
function checkIP(queryInfo) {
 	if(queryInfo.indexOf("/") >= 0) {
  		var strArray = queryInfo.split("/");
  		if(strArray.length > 2) return false;
  		var flag = isValidIP(strArray[0]);
	  		 	
  		if (flag && strArray[0].indexOf(".") >= 0) {
  			return (1 <= parseInt(strArray[1]) && parseInt(strArray[1]) <= 32);
  		}else if(flag && (strArray[0].indexOf(":") >= 0 || strArray[0].indexOf("::") >= 0)){
  			return (1 <= parseInt(strArray[1]) && parseInt(strArray[1]) <= 128); 
  		}else {
  			return false;
  		}
	}else{
  		return isValidIP(queryInfo);
  	}
}
	
function isValidIP(strArray){
	var pattern =/^\d{1,3}\.\d{1,3}\.\d{1,3}\.\d{1,3}$/;
	if (strArray.match(pattern)){
		var ipStrArray = strArray.split(".");
  		return (parseInt(ipStrArray[0]) <= 255 && parseInt(ipStrArray[0]) >= 0 &&
  			parseInt(ipStrArray[1]) <= 255 && parseInt(ipStrArray[1]) >= 0 && 
  			parseInt(ipStrArray[2]) <= 255 && parseInt(ipStrArray[2]) >= 0 && 
  			parseInt(ipStrArray[3]) <= 255 && parseInt(ipStrArray[3]) >= 0);
	}else{
		return isIPv6(strArray);
	}
}

function isIPv6(str)  {  
	 return /::/.test(str) 
		    ?/^([\da-f]{1,4}(:|::)){1,6}[\da-f]{1,4}$/i.test(str) 
		    :/^([\da-f]{1,4}:){7}[\da-f]{1,4}$/i.test(str); 
}  

function processLogin(){
	window.location.href = "../adv/advindex.jsp"; 
}

function aboutContent(){
	$("#maincontent").html("<p>&nbsp;&nbsp;" +
					"China Internet Network Information Center (abbreviated"
					+"as CNNIC) is an administration and service organization set up on June 3, 1997 "
					+" upon the approval of the competent authority and undertakes the responsibilities"
					+"as the national Internet network information center. " 
					+"In light of the policies of 'providing' efficient and application-oriented services through secure & stable "
					+"Internet infrastructure for public interests\", CNNIC,"
					+" as an important constructor, operator and administrator of infrastructure"
					+"in Chinese information society, is responsible for operation,"
					+"conducts research on Internet development and provides consultancy,"
					+"and promotes the cooperation and technological exchange of global"
					+"Internet in an effort to become a world-class network information"
					+"center. Main responsibilities of CNNIC: <br/>&nbsp;&nbsp;" 
					+"1. Operation, Administration and Service organization of national network"
					+"fundamental resources CNNIC is a registry of domain names and root"
					+"zone operator. It operates and administers country code top level"
					+"domain of .CN and Chinese domain name system, and provides 24-hour"
					+"services of domain name registration and resolution as well as"
					+"WHOIS lookup for worldwide users with its professional"
					+"technologies. CNNIC is a member of Asia-Pacific Network Information"
					+"Centre (APNIC) as a National Internet Registry (NIR). As the"
					+"convener of IP Address Allocation Alliance, CNNIC is responsible"
					+"for providing allocation and administration services to China's"
					+"Internet service providers (ISPs) and Internet users and promoting"
					+"the transition to Internet of next-generation based on IPv6 in"
					+"China. <br/>&nbsp;&nbsp;" 
					+"2. Research, Development and Security center of national"
					+"network fundamental resources CNNIC constructs a world-leading,"
					+"efficient and safe & stable service platform for fundamental"
					+"network resources. It provides multi-level and multi-mode"
					+"not-for-profit services for fundamental network resources, and"
					+"seeks to make a breakthrough in the core competence of fundamental"
					+"network resources and self-developed devices and softwares so as to"
					+"improve the reliability, security and stability of China's system"
					+"of fundamental network resources.<br/>&nbsp;&nbsp;" 
					+" 3. Research and consulting"
					+"services driving force for Internet development CNNIC is"
					+"responsible for conducting surveys about the Internet including"
					+"survey on the development status of China's Internet, and it gives"
					+"a description of the macroscopic picture of the development status"
					+"of China's Internet and records its development faithfully. CNNIC"
					+"will continue to beef up its support for the research of government"
					+"policies on one hand and provide not-for-profit research and"
					+"consulting services for Internet development for enterprises, users"
					+"and research institutes on the other hand.</br>&nbsp;&nbsp;" 
					+" 4. Platform for Internet"
					+"open cooperation and technical exchange CNNIC tracks the latest"
					+"development of Internet policies and technologies and has business"
					+"coordination and cooperation with relevant international"
					+"organizations and the Internet network information centers in other"
					+"countries and regions. CNNIC hosts important international"
					+"conferences and activities concerning the Internet, and creates an"
					+"open research environment and platform for international exchange"
					+"and sharing. It promotes the application of scientific research"
					+"achievements and development of China's Internet.</p>");
}

function getHelp(){
	var url = $("#pathUrl").val() + "/help";
	window.location.href = url;
//	$.ajax({
//		type : "get",
//		async : true,
//		url : url,
//		dataType : "html",
//		success : statechange,
//		error : function(XMLHttpRequest, textStatus, errorThrown) {
//					if (XMLHttpRequest.status == 404){
//						alert("Not found!")
//					}
//				}
//	});
	
}












