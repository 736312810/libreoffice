<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="http://libs.baidu.com/jquery/2.0.0/jquery.js"></script>
<style> 
body{ text-align:center} 
.div{ margin:0 auto; width:400px; height:100px; border:0px solid #F00} 
</style> 
</head>
<body>
<div class="div">
	<form id="formDemo" action="upload.json" method="post" >  
	目标文件:<input type="text" name="inputFilePath" />  <br><br>
	libreOffice路径:<input type="text" name="libreOfficePath" />  <br><br>
	生成文件:<input type="text" name="outputFilePath" /> <br><br>
	<input type="button" value="提交" onclick="submit1()"/>
</form> 
</div>

<script language="javascript"> 
function submit1(){
	if(!$('input[name=inputFilePath]').val()){
		alert("目标文件为空");
		return;
	}
	if(!$('input[name=libreOfficePath]').val()){
		alert("libreOffice路径为空");
        return;
   }
	if(!$('input[name=outputFilePath]').val()){
		alert("生成文件为空");
        return;
    }
	
	var data = $("#formDemo").serializeArray();
	 $.ajax({     
        type: "POST",             
        url: "upload.json",   
        contentType : "application/x-www-form-urlencoded; charset=utf-8",
        data:data,
        dataType : "json", 
        success: function(data) {
        	alert(data.code);
            
        },     
        error: function(err) {
        	alert("数据传输出错！")
        }     
    });
}

</script> 
</body>
</html>
