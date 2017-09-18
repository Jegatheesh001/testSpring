<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<title>URL Decoder/Encoder</title>
<style type="text/css">
<!--
body {
	background: white;
	color: black;
}

form {
	margin: 0;
}

h1 {
	font-family: Arial, sans-serif;
	line-height: 0.85em;
	border-bottom: 2px solid;
	margin-bottom: 0.33em;
	padding-bottom: 0;
}

textarea {
	background: #EEF;
}

#footer {
	border-top: 1px solid #000;
	color: #999;
	font: italic 75% sans-serif;
}

#footer p {
	margin: 0 0 1em 0;
}

#footer img {
	float: right;
	margin: 0 0 0.5em 2em;
}
-->
</style>
<script type="text/javascript">
	function encode() {
		var obj = document.getElementById('dencoder');
		var unencoded = obj.value;
		obj.value = encodeURIComponent(unencoded).replace(/'/g, "%27").replace(/"/g, "%22");
		unencoded.hexEncode();
	}
	function decode() {
		var obj = document.getElementById('dencoder');
		var encoded = obj.value;
		obj.value = decodeURIComponent(encoded.replace(/\+/g, " "));
		// encoded.hexDecode();
	}
	
	String.prototype.hexEncode = function() {
		var hex, i;

		var result = "";
		for (i = 0; i < this.length; i++) {
			hex = this.charCodeAt(i).toString(16);
			result += "\\u";
			result += ("000" + hex).slice(-4);
		}
		document.getElementById('unicode').value = result;
		
		return result
	}
	
	String.prototype.hexDecode = function(){
	    var j;
	    var hexes = this.match(/.{1,4}/g) || [];
	    var back = "";
	    for(j = 0; j<hexes.length; j++) {
	        back += String.fromCharCode(parseInt(hexes[j], 16));
	    }
	    document.getElementById('unicode').value = back;
	    
	    return back;
	}
</script>
</head>
<body>


	<form onsubmit="return false;">
		<h1>URL Decoder/Encoder</h1>

		<textarea cols="100" rows="20" id="dencoder" title="Encode/Decode"></textarea>
		<div>
			<input type="button" onclick="decode()" value="Decode"> <input
				type="button" onclick="encode()" value="Encode">
		</div>
		<br />
		<textarea cols="100" rows="5" id="unicode" title="Unicode"></textarea>
		<ul>
			<li>Input a string of text and encode or decode it as you like.</li>
			<li>Handy for turning encoded JavaScript URLs from complete
				gibberish into readable gibberish.</li>
			<li>If you'd like to have the URL Decoder/Encoder for offline
				use, just view source and save to your hard drive.</li>
		</ul>

	</form>

</body>
</html>
