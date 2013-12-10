function operation(valueName) {
	var operationFlag = valueName;
	var data = "tableType=" + operationFlag;
	var columnUrl = "ColumnAction!list?";
	queryButton(columnUrl, data);
}

function queryButton(columnUrl, columnValue) {
	$.ajax({
		type : "post",
		async : true,
		url : columnUrl,
		data : columnValue,
		dataType : "html",
		success : statechange,
		error : function(XMLHttpRequest, textStatus, errorThrown) {
					if (XMLHttpRequest.status == 417){
						alert("Already exists in the field")
					}else{
						//alert(textStatus);
					}

				}
	});
}

function statechange(data) {
	$("#maincontent").html(data);
}

function changeAddColumn(taNames) {
	tableName = taNames;
	$("#maincontent")
			.html(
					"<form id='addColumnInfo' action='ColumnAction'>"
							+ "<table><tr><th>Column Name </th><th>Column Length</th></tr>"
							+ "<tr><td><input  name='columnName'></input></td>"
							+ "<td><input  name='columnLength'></input></td></tr>"
							+ "<tr><td><input  name='columnName'></input></td>"
							+ "<td><input name='columnLength'></input></td></tr>"
							+ "<tr><td><input  name='columnName'></input></td>"
							+ "<td><input name='columnLength'></input></td></tr>"
							+ "<tr><td><input  name='columnName'></input></td>"
							+ "<td><input  name='columnLength'></input></td></tr>"
							+ "<tr><td><input  name='columnName'></input></td>"
							+ "<td><input  name='columnLength'></input></td></tr></table></form>"
							+ "<input type='submit' value='addCoulmn' onclick='checkAddColumn()'>");
}

function checkAddColumn() {
	var coulumNames = document.getElementsByName("columnName");
	var columnLengths = document.getElementsByName("columnLength");
	var isNull = false;
	var matchName = /^([a-zA-z\d]{1})([\w\_]*)$/g;
	var columnArray = [];

	for ( var i = 0; i < coulumNames.length; i++) {
		var coulumNamesValue = coulumNames[i].value;
		var columnLengthsValue = columnLengths[i].value;

		if (coulumNamesValue != "" && columnLengthsValue != "") {

			if (checkColumn(coulumNamesValue, columnLengthsValue)) {
				columnArray.push(coulumNamesValue + "'~'" + columnLengthsValue)
				isNull = true;
			}
		} else if (coulumNamesValue != "") {
			if (columnLengthsValue == "")
				isNull = false;
		} else if (columnLengthsValue != "") {
			if (coulumNamesValue == "")
				isNull = false;
		}
	}

	if (!isNull) {
		alert("Please enter the data")
		return isNull;
	} else {
		submitAddCoulmn(columnArray)
	}

}

function submitAddCoulmn(columnArray) {
	var data1 = "columnArray=" + columnArray;
	var data2 = "&tableType=" + tableName
	var columnUrl = "ColumnAction!add?";
	queryButton(columnUrl, data1 + data2);
}

function operationColumn(obj, taName, coName, coValue) {
	var operationFlag = obj.value;
	var data1 = "tableType=" + taName;
	var data2 = "&coName=" + coName;
	if (operationFlag == 1) {
		listUpdateColumn(taName, coName, coValue);
	} else if (operationFlag == 2) {
		var boo = confirm("Confirm delete?");
		if (boo == true) {
			var columnUrl = "ColumnAction!delete?";
			queryButton(columnUrl, data1 + data2);
		}
	}
}

function listUpdateColumn(taName, coName, coValue) {
	$("#maincontent")
			.html(
					"<form id='updateColumnInfo' action=''>"
							+ "<input type='hidden' id='tableType' name='tableType' value='"
							+ taName
							+ "'/>"
							+ "<input type='hidden' id='oldColumnName' name='oldColumnName' value='"
							+ coName
							+ "'/>"
							+ "<table><tr><td>Column Name</td><td>Coulumn Length</td></tr>"
							+ "<tr><td><input id='newCoulumnName' name='newCoulumnName' value="
							+ coName
							+ "></input></td><td><input id='coulumnLength' name='coulumnLength' value="
							+ coValue
							+ "></tr></table></form>"
							+ "<input type='button' value='updateCoulmn' onclick='checkUpdateColumn()'>");
}

function checkUpdateColumn() {
	var newCoulumnName = $("#newCoulumnName").val();
	var coulumnLength = $("#coulumnLength").val();

	if (newCoulumnName != "" && coulumnLength != "") {
		if (checkColumn(newCoulumnName, coulumnLength))
			updateExtendColumn();
	} else {
		alert("Please enter the data");
		return false
	}

}

function checkColumn(columnName, columnLength) {
	var matchName = /^([a-zA-z\d]{1})([\w\_]*)$/g;
	var matchLength = /^\d+$/;
	if (columnName.match(matchName) && columnLength.match(matchLength)) {
		if (parseInt(columnLength) >= 1 && parseInt(columnLength) <= 255)
			return true;
		alert("Please enter a range between 1 to 255 of the length ");
		return false;

	}
	return false;
}

function updateExtendColumn() {
	var data1 = $("#updateColumnInfo").serialize();
	var columnUrl = "ColumnAction!update?";
	queryButton(columnUrl, data1);
}

function operationPermissionTable(valueName) {
	var data = "tableType=" + valueName;
	var permissionUrl = "AccessControlAction!list";
	queryButton(permissionUrl, data)
}

function updatePermission() {
	var anonymousObj = document.getElementsByName('anonymousUser');
	var authenticatedObj = document.getElementsByName('authenticatedUser');
	var rootObj = document.getElementsByName('rootUser');
	var tableName = $("#tabletype").val();

	var anonymousArray = checkPermission(anonymousObj);
	var authenticatedArray = checkPermission(authenticatedObj);
	var rootArray = checkPermission(rootObj);

	var permissionUrl = "AccessControlAction!update";
	var permissionData = "tableType=" + tableName + "&" + "anonymous="
			+ anonymousArray + "&" + "authenticated=" + authenticatedArray
			+ "&" + "root=" + rootArray
	queryButton(permissionUrl, permissionData);
}

function checkPermission(obj) {
	var coulumnPermission = '';
	for ( var i = 0; i < obj.length; i++) {
		if (obj[i].checked) {
			coulumnPermission += obj[i].value + '~1,'
		} else {
			coulumnPermission += obj[i].value + '~0,'
		} 
	}
	return coulumnPermission;
}

function operationRedirectTable(tableName) {
	var permissionUrl = "RedirectAction!list";
	queryButton(permissionUrl, "tableName=" + tableName);
}

function operationRedirect(obj, id, start, redirectUrl, end, tableName) {
	var operationFlag = obj.value;
	if (operationFlag == 1) {
		listUpdateRedirect(id, start, redirectUrl, end, tableName);
	} else if (operationFlag == 2) {
		var boo = confirm("Confirm delete?");
		if (boo == true) {
			var permissionUrl = "RedirectAction!delete?";
			queryButton(permissionUrl, "id=" + id + "&tableName=" + tableName);
		}
	}
}

var testTable = "";
function listUpdateRedirect(id, start, redirectUrl, end, tableName) {
	testTable = tableName;
	if (tableName == 'ip' || tableName == 'autnum') {

		$("#maincontent")
				.html(
						"<form id='updateRedirectInfo' action='' type='parent'>"
								+ "<input type='hidden' id='redirectId' name='redirectId' value='"
								+ id
								+ "'/>"
								+ "<table><tr><td>Start Number</td><td>End Number</td><td>RedirectUrl</td></tr>"
								+ "<tr><td><input id='start' name='start' value="
								+ start
								+ "></input></td>"
								+ "<td><input id='end' name='end' value="
								+ end
								+ "></input></td><td><input id='redirectURl' name='redirectURl' value="
								+ redirectUrl
								+ "></tr></table></form>"
								+ "<input type='button' value='update Redirect' onclick='updateRedirect()'>");

	} else {
		$("#maincontent")
				.html(
						"<form id='updateRedirectInfo' action='' type='parent'>"
								+ "<input type='hidden' id='redirectId' name='redirectId' value='"
								+ id
								+ "'/>"
								+ "<table><tr><td>type</td><td>redirectUrl</td></tr>"
								+ "<tr><td><input id='start' name='start' value="
								+ start
								+ "></input></td><td><input id='redirectURl' name='redirectURl' value="
								+ redirectUrl
								+ "></tr></table></form>"
								+ "<input type='button' value='update Redirect' onclick='updateRedirect()'>");
	}
}

function checkRedirect(tableName) {
	var startData = $("#start").val();
	var redirectUrlData = $("#redirectURl").val();
	var matchData = /^\d+$/;
	var matchURL = /^((https|http|ftp|rtsp|mms)?:\/\/)+[A-Za-z0-9]+\.[A-Za-z0-9]+[\/=\?%\-&_~`@[\]\':+!]*([^<>\"\"])*$/;
	 var strRegex = new RegExp(matchURL);
	 var dataRegex =  new RegExp(matchData);
	 
	if (tableName == 'ip' || tableName == 'autnum') {
		var endData = $("#end").val();
		
		if (startData != "" && redirectUrlData != "" && endData != "") {
			if(tableName == 'ip'){
				if (!strRegex.test(redirectUrlData) || !isValidIP(startData) || !isValidIP(endData)) {
					alert("Please enter the correct data");
					return false;
				}
			}else{
				if (!strRegex.test(redirectUrlData) || !dataRegex.test(startData)
						|| !dataRegex.test(endData)) {
					alert("Please enter the correct data");
					return false;
				}
			}

		} else {
			alert("Please enter the data");
			return false
		}
	} else {
		if (startData != "" && redirectUrlData != "") {
			if (!redirectUrlData.match(matchURL)) {
				alert("Please enter the correct URL address");
				return false;
			}
		} else {
			alert("Please enter the data");
			return false
		}
	}
	return true;
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

function updateRedirect() {
	var flag = checkRedirect(testTable);
	if (!flag)
		return false;

	var data = $("#updateRedirectInfo").serialize();
	var redirectUrl = "RedirectAction!update?";
	queryButton(redirectUrl, data + "&tableName=" + testTable);
}

function changeAddRedirect(tableName) {
	testTable = tableName;
	if (tableName == 'ip' || tableName == 'autnum') {
		$("#maincontent")
				.html(
						"<form id='addRedirectfo' action='POST'><table><tr><td>Start Number </td><td>End Number </td><td>RedirectURl</td></tr>"
								+ "<tr><td><input id='start' name='start'></input></td><td><input id='end' name='end'></input></td><td><input id='redirectURl' name='redirectURl'>"
								+ "</input></td></tr></table></form>"
								+ "<input type='button' value='add Redirect' onclick='submitAddRedirect()'>");

	} else {
		$("#maincontent")
				.html(
						"<form id='addRedirectfo' action='POST'><table><tr><td>Name</td><td>RedirectURl</td></tr>"
								+ "<tr><td><input id='start' name='start'></input></td><td><input id='redirectURl' name='redirectURl'>"
								+ "</input></td></tr></table></form>"
								+ "<input type='button' value='add Redirect' onclick='submitAddRedirect()'>");
	}
}

function submitAddRedirect() {
	var flag = checkRedirect(testTable);
	if (!flag)
		return false;

	var data = $("#addRedirectfo").serialize();
	var columnUrl = "RedirectAction!add?";
	queryButton(columnUrl, data + "&tableName=" + testTable);
}

function aboutContent(){
	$("#maincontent").html("<p>&nbsp;&nbsp;" +
					"China Internet Network Information Center (abbreviated"
					+"as CNNIC) is an administration and service organization set up on June 3, 1997 "
					+" upon the approval of the competent authority and undertakes the responsibilities"
					+"as the national Internet network information center." 
					+"In light of the policies of 'providing' efficient and application-oriented services through secure & stable "
					+"Internet infrastructure for public interests\", CNNIC,"
					+"as an important constructor, operator and administrator of infrastructure"
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
