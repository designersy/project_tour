// 마커를 만들어서 반환한다.
function createMarker(map, positionString, name, address, access, telephone) {


	// 관광지 좌표값
	const tempLat = positionString.substring(positionString.indexOf(":")+1, positionString.indexOf(","));
	const tempLon = positionString.substring(positionString.lastIndexOf(":")+1, positionString.indexOf("}"));

	const position =  new kakao.maps.LatLng(tempLat, tempLon);

	// 마커 생성
	const marker = new kakao.maps.Marker({
	position: position
	});
	
	marker.setMap(map)
	
	const iwContent = `<div style="padding:5px;">${name}</div>
	<div style="padding:5px;">주소: ${address}</div>
	<div style="padding:5px;">주변 지하철: ${access}</div>
	<div style="padding:5px;">전화번호: ${telephone}</div>
	`,
	iwRemoveable = true; // removeable 속성을 ture 로 설정하면 인포윈도우를 닫을 수 있는 x버튼이 표시됩니다

	// 인포윈도우를 생성합니다
	const infowindow = new kakao.maps.InfoWindow({
		content : iwContent,
		removable : iwRemoveable
	});

	// 마커에 클릭이벤트를 등록합니다
	kakao.maps.event.addListener(marker, 'click', function() {
		// 마커 위에 인포윈도우를 표시합니다
		infowindow.open(map, marker);  
	});
}


// 주소를 입력받아 좌푯값 "Lat Lon" 형태로 반환
function getPosition(address) {
	
	let position = "";
	
	$.ajax({url:'https://dapi.kakao.com/v2/local/search/address.json?query='+address,
		type:'GET',
		headers: {'Authorization' : 'KakaoAK 609cb597ba4d2059b8980706f5fdf0a5'},
		success:function(data){
			
			// address에 해당하는 좌표값
			const tempLat = parseFloat(data["documents"][0]["y"]);
			const tempLon = parseFloat(data["documents"][0]["x"]);
			
			position =  tempLat + " " + tempLon
		}
	})
	
	return position;
}

function latlon(positionString) {
	const tempLat = positionString.substring(positionString.indexOf(":")+1, positionString.indexOf(","));
	const tempLon = positionString.substring(positionString.lastIndexOf(":")+1, positionString.indexOf("}"));

	return [tempLon, tempLat];
}