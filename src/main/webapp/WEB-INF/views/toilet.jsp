<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://apis.openapi.sk.com/tmap/jsv2?version=1&appKey=l7xxe313b66ead5248d2bbf5f3c810b0afd2"></script>
<script type="text/javascript">
function initTmap(){
	var map = new Tmapv2.Map("map_div",  
	{
		center: new Tmapv2.LatLng(37.566481622437934,126.98502302169841), // 지도 초기 좌표
		width: "890px", 
		height: "400px",
		zoom: 15
	});
} 
</script>
</head>
<body onload="initTmap()">
<div id="map_div"></div>   
<script>

$.ajax({
    url:'/api/toilet',
    success:function(data){
        console.log(data);
    }
})

</script>
</body>
</html>