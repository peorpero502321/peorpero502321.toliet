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
	map.setZoomLimit("15","17");
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

	var toiletList = getToiletList(event);
	var positions = [];
	for (var z = 0; z< toiletList.length; z++) {
		positions[z] = {
			title: toiletList[z].pbctlt_PLC_NM,
			lonlat: new Tmapv2.LatLng(toiletList[z].refine_WGS84_LAT, toiletList[z].refine_WGS84_LOGT)//좌표 지정
		}
	}

	for (var i = 0; i< positions.length; i++){//for문을 통하여 배열 안에 있는 값을 마커 생성
		var lonlat = positions[i].lonlat;
		var title = positions[i].title;
		//Marker 객체 생성.
		marker = new Tmapv2.Marker({
			position : lonlat, //Marker의 중심좌표 설정.
			map : map, //Marker가 표시될 Map 설정.
			title : title //Marker 타이틀.
		});
	}
}

// 현재 지도범위기준 화장실 목록조회
function getToiletList(event) {
	var list;
	$.ajax({
		url:'/api/toilet',
		async: false,
		data : {slat :event._sw._lat, elat:event._ne._lat, slogt:event._sw._lng, elogt:event._ne._lng},
		success:function(data){
			list = data;
		}
	})
	return list;
}
</script>
</head>
<body onload="initTmap()">
<div id="map_div"></div>
<p id="result" />

</body>
</html>