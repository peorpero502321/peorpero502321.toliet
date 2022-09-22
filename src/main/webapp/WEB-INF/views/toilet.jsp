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
var map;
var event;

function initTmap(){
	map = new Tmapv2.Map("map_div",  
	{
		center: new Tmapv2.LatLng(37.566481622437934,126.98502302169841), // 지도 초기 좌표
		width: "890px", 
		height: "400px",
		zoom: 15
	});
	map.addListener("dragend", onDragend); // 지도 드래그 끝났을 때, 이벤트 리스너 등록.
	map.addListener("touchend", onTouchend); // 모바일에서 지도 터치 터치가 끝났을때, 이벤트 리스너 등록.
} 


function onDragend() {
	Info();
}
function onTouchend(e) {
	var result = '모바일에서 터치가 끝난 위치의 좌표는' + e.latLng + '입니다.';
	var resultDiv = document.getElementById("result");
	resultDiv.innerHTML = result;
}

function Info() {
	event = map.getBounds();
	var extent = map.getBounds();//map의 영역의 값
	var result ='지도의 현재 영역값은'+extent+'입니다.'; 
	var resultDiv = document.getElementById("result");
	resultDiv.innerHTML = result;
}
</script>
</head>
<body onload="initTmap()">
<div id="map_div"></div> 
<p id="result" />  
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