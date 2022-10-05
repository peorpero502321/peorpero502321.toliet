<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1"/>
	<title>Insert title here</title>
	<link href="/static/styles/style.css" rel="stylesheet" type="text/css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	<script src="https://apis.openapi.sk.com/tmap/jsv2?version=1&appKey=l7xxe313b66ead5248d2bbf5f3c810b0afd2"></script>

</head>
<body onload="initTmap()">
<div id="headBox"></div>
<div id="map_div"></div>
<p id="result" />
<div id="footBox"></div>

<section id="fixed-form-container">
	<div class="box"></div>
	<div class="body">
		<div class="form-group">
			<label for="email">Email address:</label>
			<input type="email" class="form-control" id="email">
		</div>
		<div class="form-group">
			<label for="pwd">Password:</label>
			<input type="password" class="form-control" id="pwd">
		</div>
		<div class="checkbox">
			<label><input type="checkbox"> Remember me</label>
		</div>
		<button type="submit" class="btn btn-default">Submit</button>
	</div>
</section>

<script type="text/javascript">
		(function init() {
			$("#fixed-form-container .body").hide();
		})();

		var map;
		var event;
		var marker;
		var merker2; // user
		var toiletList;
		var toiletInfo;

		function initTmap(){
			map = new Tmapv2.Map("map_div",
			{
				center: new Tmapv2.LatLng(37.566481622437934,126.98502302169841), // 지도 초기 좌표
				width: "100%",
				height: "500px",
				zoom: 15,
				httpsMode: true
			});
			map.setZoomLimit("15","17");
			map.addListener("dragend", onDragend); // 지도 드래그 끝났을 때, 이벤트 리스너 등록.
			map.addListener("touchend", onTouchend); // 모바일에서 지도 터치 터치가 끝났을때, 이벤트 리스너 등록.
			map.setOptions({zoomControl:false});
		}


		function onDragend(e) {
			Info();
		}
		function onTouchend(e) {
			Info();
		}

		function Info() {
			event = map.getBounds();
			var extent = map.getBounds();//map의 영역의 값
			var result ='지도의 현재 영역값은'+extent+'입니다.';
			var resultDiv = document.getElementById("footBox");
			resultDiv.innerHTML = result;


			toiletList = getToiletList(event);
			var positions = [];
			for (var z = 0; z< toiletList.length; z++) {
				positions[z] = {
					title: toiletList[z].pbctlt_PLC_NM,
					lonlat: new Tmapv2.LatLng(toiletList[z].refine_WGS84_LAT, toiletList[z].refine_WGS84_LOGT),//좌표 지정
					add : toiletList[z].REFINE_ROADNM_ADDR
				}
			}

			for (var i = 0; i< positions.length; i++){//for문을 통하여 배열 안에 있는 값을 마커 생성
				var lonlat = positions[i].lonlat;
				var title = positions[i].title;
				label="<span style='background-color: #46414E;color:white'>"+title+"</span>";
				//Marker 객체 생성.
				marker = new Tmapv2.Marker({
					position : lonlat, //Marker의 중심좌표 설정.
					map : map, //Marker가 표시될 Map 설정.
					title : title, //Marker 타이틀.
					label : label, //Marker의 라벨.
					icon : "/static/img/toilet.png"
				});

				marker.addListener("touchstart", function (evt) {
					$(".box").next("#fixed-form-container div").slideToggle(400);
					toiletInfo = getTolietObjectData(evt.latLng._lat, evt.latLng._lng);
					console.log(marker.getPositionEPSG3857);
					console.log(getTolietObjectData(evt.latLng._lat, evt.latLng._lng));
				});

				marker.addListener("click", function(evt) {
					$(".box").next("#fixed-form-container div").slideToggle(400);
					//toiletInfo = getTolietObjectData(evt.latLng._lat, evt.latLng._lng);
					console.log(this.label);
					//console.log(getTolietObjectData(evt.latLng._lat, evt.latLng._lng));
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

		// 내위치 구하기
		function getLocation() {
			var gps;
			if (navigator.geolocation) { // GPS를 지원하면
				navigator.geolocation.getCurrentPosition(function (position) {
					map.setCenter(new Tmapv2.LatLng(position.coords.latitude, position.coords.longitude));
					merker2 = new Tmapv2.Marker({
						position : new Tmapv2.LatLng(position.coords.latitude, position.coords.longitude),//좌표 지정, //Marker의 중심좌표 설정.
						map : map, //Marker가 표시될 Map 설정.
						icon : "/static/img/icon.png"
					});
					Info();
				}, function (error) {
					console.error(error);
				}, {
					enableHighAccuracy: false,
					maximumAge: 0,
					timeout: Infinity
				});
			} else {
			alert('GPS를 지원하지 않습니다');
			}
		}

		getLocation();

		function getTolietObjectData(lat, logt) {
			console.log(lat, logt);

			for (var i = 0; i < toiletList.length; i++) {
				if (toiletList[i].refine_WGS84_LAT == lat && toiletList[i].refine_WGS84_LOGT == logt ) {
					console.log(toiletList[i]);
					return toiletList[i];
				}
			}
		}

	</script>
</body>
</html>