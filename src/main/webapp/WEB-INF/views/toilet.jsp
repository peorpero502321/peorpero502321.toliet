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
<body onload="initTmap()" style="background-color:#a3a3a3">
<div id="headBox"></div>
<div id="map_div"></div>
<p id="result" />
<div id="footBox">
	<table style="width:100%; height: 80px;">
		<tr>
			<th class="taget_button">
				<button onclick="getLocation();"><b>내 위치로</b></button>
			</th>
			<th class="taget_button">
				<button onclick="getMyCloseToilet()"><b>가장 가까운 화장실로</b></button>
			</th>
		<tr>
	</table>

</div>

<section id="fixed-form-container">
	<div class="box"></div>
	<div class="body">
		<div id="toilet-info">
			<table>
				<tr>
					<th colspan="5">
						<p id="pbctltPlcNm" style="font-size: 19px; font-weight: bold;"></p>
					</th>
				</tr>
				<tr>
					<td class="tdHead">
						<img src="/static/img/male.png" alt="남자">
						<img src="/static/img/uil.png" alt="소변기">
					</td>
					<td id="maleUilCnt" class="tdContentCnt"></td>
					<td class="tdHead">
						<img src="/static/img/female.png" alt="여자">
						<img src="/static/img/wtrcls.png" alt="대변기">
					</td>
					<td id="femaleWtrclsCnt" class="tdContentText"></td>
				</tr>
				<tr>
					<td class="tdHead">
						<img src="/static/img/male.png" alt="남자">
						<img src="/static/img/wtrcls.png" alt="대변기">
					</td>
					<td id="maleWtrclsCnt" class="tdContentCnt"></td>
					<td class="tdHead">
						<img src="/static/img/female.png" alt="여자">
						<img src="/static/img/wtrcls.png" alt="대변기">
						<img src="/static/img/dspsn.png" alt="장애인">
					</td>
					<td id="femaleDspsnWtrclsCnt" class="tdContentText"></td>
				</tr>
				<tr>
					<td class="tdHead">
						<img src="/static/img/male.png" alt="남자">
						<img src="/static/img/uil.png" alt="소변기">
						<img src="/static/img/dspsn.png" alt="장애인">
					</td>
					<td id="maleDspsnUilCnt" class="tdContentCnt"></td>
					<td class="tdHead">
						<img src="/static/img/femaleKid.png" alt="여자아이">
						<img src="/static/img/wtrcls.png" alt="대변기">
					</td>
					<td id="femaleKidWtrclsCnt" class="tdContentText"></td>
				</tr>
				<tr>
					<td class="tdHead">
						<img src="/static/img/male.png" alt="남자">
						<img src="/static/img/wtrcls.png" alt="대변기">
						<img src="/static/img/dspsn.png" alt="장애인">
					</td>
					<td id="maleDspsnWtrclsCnt" class="tdContentCnt"></td>
					<td class="tdHead">
						<img src="/static/img/toilet.png" alt="남여공용">
					</td>
					<td id="maleFemaleCmnuseToiletYn" class="tdContentText"></td>
				</tr>
				<tr>
					<td class="tdHead">
						<img src="/static/img/maleKid.png" alt="남자아이">
						<img src="/static/img/uil.png" alt="소변기">
					</td>
					<td id="maleKidUilCnt" class="tdContentCnt"></td>
					<td class="tdHead">
						<img src="/static/img/maleKid.png" alt="남자아이">
						<img src="/static/img/wtrcls.png" alt="대변기">
					</td>
					<td id="maleKidWtrclsCnt" class="tdContentCnt"></td>
				</tr>
				<tr>
					<td class="tdHead">오픈시간</td>
					<td id="openTmInfo" colspan="3" class="tdContentText"></td>
				</tr>
				<tr>
					<td class="tdHead">주소</td>
					<td id="refineLotnoAddr" colspan="3" class="tdContentText"></td>
				</tr>
			</table>
		</div>
	</div>
</section>

<script type="text/javascript">
		(function init() {
			$("#fixed-form-container .body").hide();
		})();

		var map;
		var event;
		var toiletList;
		var toiletInfo;
		var merker2;
		var myMerkers = [];

		function initTmap(){
			map = new Tmapv2.Map("map_div",
			{
				center: new Tmapv2.LatLng(37.566481622437934,126.98502302169841), // 지도 초기 좌표
				width: "100%",
				height: "580px",
				zoom: 15,
				httpsMode: true
			});
			map.setZoomLimit("15","17");
			map.addListener("dragend", onDragend); // 지도 드래그 끝났을 때, 이벤트 리스너 등록.
			map.addListener("touchend", onTouchend); // 모바일에서 지도 터치 터치가 끝났을때, 이벤트 리스너 등록.
			map.addListener("dragstart", onDragstart); // 지도 드래그 시작시, 이벤트 리스너 등록.
			map.addListener("touchstart", onTouchstart); // 모바일에서 지도 터치 시작시, 이벤트 리스너 등록.
			map.setOptions({zoomControl:false});
		}

		function onDragend(e) {
			info();
		}
		function onTouchend(e) {
			info();
		}
		function onDragstart(e) {
			$("#fixed-form-container .body").hide();
			$(".taget_button").children('button').css('background-color', '#a3a3a3');
		}
		function onTouchstart(e) {
			$("#fixed-form-container .body").hide();
			$(".taget_button").children('button').css('background-color', '#a3a3a3');
		}

		function info() {
			event = map.getBounds();
			var extent = map.getBounds();//map의 영역의 값
			var result ='지도의 현재 영역값은'+extent+'입니다.';
			var markers = []; // 마커를 저장할 배열

			toiletList = getToiletList(event);
			var positions = [];
			if (null != toiletList) {
				for (var z = 0; z< toiletList.length; z++) {
					positions[z] = {
						title: toiletList[z].pbctltPlcNm,
						lonlat: new Tmapv2.LatLng(toiletList[z].refineWgs84Lat, toiletList[z].refineWgs84Logt),//좌표 지정
						add : toiletList[z].refineRoadnmAddr
					}
				}
			}

			//for문을 통하여 배열 안에 있는 값으로 마커 생성
			for (var i = 0; i< positions.length; i++){
				var lonlat = positions[i].lonlat;
				var title = positions[i].title;
				label="<span style='color:#000000'>"+title+"</span>";
				//Marker 객체 생성.
				var marker = new Tmapv2.Marker({
					position : lonlat, //Marker의 중심좌표 설정.
					title : title, //Marker 타이틀.
					label : label, //Marker의 라벨.
					icon : "/static/img/toilet.png"
				});
				marker.addListener("touchend", function (evt) {
					$("#fixed-form-container .body").hide();
					$(".box").next("#fixed-form-container div").slideToggle(400);
					var obj = "";
					obj = getToiletDtl(evt.latLng._lat, evt.latLng._lng);
					map.setCenter(new Tmapv2.LatLng(evt.latLng._lat, evt.latLng._lng));
					setToiletInfo(obj);
				});

				marker.addListener("click", function(evt) {
					$("#fixed-form-container .body").hide();
					$(".box").next("#fixed-form-container div").slideToggle(400);
					var obj = "";
					obj = getToiletDtl(evt.latLng._lat, evt.latLng._lng);
					map.setCenter(new Tmapv2.LatLng(evt.latLng._lat, evt.latLng._lng));
					setToiletInfo(obj);
				});

				marker.setMap(map); //Marker가 표시될 Map 설정.
				markers.push(marker);
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

		// 클릭마커 좌표로 데이터 조회
		function getToiletDtl(lat, lng) {
			var obj;
			$.ajax({
				url:'/api/toilet/dtl',
				async: false,
				data : {lat :lat, logt:lng},
				success:function(data){
					obj = data;
				}
			})
			return obj;
		}

		// 좌표의 근사치 화장실 정보 조회
		function getToiletCloseDtl(lat, lng) {
			var obj;
			$.ajax({
				url:'/api/toilet/close',
				async: false,
				data : {lat :lat, logt:lng},
				success:function(data){
					obj = data;
				}
			})
			return obj;
		}

		// 내위치 구하기
		function getLocation() {
			var gps;
			if (navigator.geolocation) { // GPS를 지원하면
				navigator.geolocation.getCurrentPosition(function (position) {

					delMyMerker(); // 내위치 마커 지우기

					map.setCenter(new Tmapv2.LatLng(position.coords.latitude, position.coords.longitude));
					merker2 = new Tmapv2.Marker({
						position : new Tmapv2.LatLng(position.coords.latitude, position.coords.longitude),//좌표 지정, Marker의 중심좌표 설정.
						map : map, //Marker가 표시될 Map 설정.
						icon : "/static/img/icon.png"
					});

					myMerkers.push(merker2);
					info();
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

		// 내위치 마커 지우기
		function delMyMerker() {
			for (var i = 0; i < myMerkers.length; i++) {
				myMerkers[i].setMap(null);
			}
			myMerkers = [];

		}

		// 내위치와 가장가까운 화장실로 이동
		function getMyCloseToilet() {
			var gps;
			if (navigator.geolocation) { // GPS를 지원하면
				navigator.geolocation.getCurrentPosition(function (position) {

					// 내위치 좌표로 인접 화장실 1개 뽑고 db 조회 obj 반환
					var toiletObj = getMyCloseToiletCrdnt(position.coords.latitude, position.coords.longitude);

					if (null != toiletObj) {
						setToiletInfo(toiletObj);
					}
					// 해당위치 센터로 이동 시키면 됨
					map.setCenter(new Tmapv2.LatLng(toiletObj.refineWgs84Lat ,toiletObj.refineWgs84Logt));
					info();
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

			$("#fixed-form-container .body").hide();
			$(".box").next("#fixed-form-container div").slideToggle(400);
		}

		getLocation();

		// popup 화장실 정보 바인딩
		function setToiletInfo(obj) {
			document.getElementById("pbctltPlcNm").innerHTML = obj.pbctltPlcNm;
			document.getElementById("maleUilCnt").innerHTML = obj.maleUilCnt;
			document.getElementById("femaleWtrclsCnt").innerHTML = obj.femaleWtrclsCnt;
			document.getElementById("maleWtrclsCnt").innerHTML = obj.maleWtrclsCnt;
			document.getElementById("femaleDspsnWtrclsCnt").innerHTML = obj.femaleDspsnWtrclsCnt;
			document.getElementById("maleDspsnUilCnt").innerHTML = obj.maleDspsnUilCnt;
			document.getElementById("femaleKidWtrclsCnt").innerHTML = obj.femaleKidWtrclsCnt;
			document.getElementById("maleDspsnWtrclsCnt").innerHTML = obj.maleDspsnWtrclsCnt;
			document.getElementById("maleFemaleCmnuseToiletYn").innerHTML = obj.maleFemaleCmnuseToiletYn;
			document.getElementById("maleKidUilCnt").innerHTML = obj.maleKidUilCnt;
			document.getElementById("openTmInfo").innerHTML = obj.openTmInfo;
			document.getElementById("maleKidWtrclsCnt").innerHTML = obj.maleKidWtrclsCnt;
			document.getElementById("refineLotnoAddr").innerHTML = obj.refineLotnoAddr;
		}

		var chackList = [];

		// 가까운 화장실정보 반환 X
		/* script 버전임 DB조회 버전으로 변경
		function getMyCloseToiletCrdnt(lat, logt) {
			for (var i = 0; i < toiletList.length; i++) {

				var chkLat = 0;
				var chkLogt = 0;
				chkLat = Math.abs(toiletList[i].refine_WGS84_LAT - lat);
				chkLogt = Math.abs(toiletList[i].refine_WGS84_LOGT - logt);
				chackList[i] = chkLat + chkLogt;
				console.log(chackList[i]);
			}
			console.log();
			return toiletList[getMinIndex(chackList)];
		} */


		// 가까운 화장실정보 반환 DB조회
		function getMyCloseToiletCrdnt(lat, logt) {

			return getToiletCloseDtl(lat, logt);
		}

		// 최소값 인덱스 구하기
		function getMinIndex(lst) {

			// 최소값 인덱스 초기값 세팅
			var min = lst[0];
			var minIndex = 0;

			// 최소값의 인덱스 구하기
			for (var i = 0; i < lst.length; i++) {
				if (lst[i] < min) {
					minIndex = i;
				}
			}
			return minIndex;
		}

		function getTolietObjectData(lat, logt) {
			for (var i = 0; i < toiletList.length; i++) {
				if (toiletList[i].refineWgs84Lat == lat && toiletList[i].refineWgs84Logt == logt ) {
					return toiletList[i];
				}
			}
		}



	</script>
</body>
</html>