document.addEventListener('DOMContentLoaded', () => {
    function getRandomCoordinates() {
        const minLongitude = 128.4;
        const maxLongitude = 128.7;
        const minLatitude = 35.8;
        const maxLatitude = 36.1;

        const randomLongitude = (Math.random() * (maxLongitude - minLongitude) + minLongitude).toFixed(6);
        const randomLatitude = (Math.random() * (maxLatitude - minLatitude) + minLatitude).toFixed(6);

        return {
            longitude: randomLongitude,
            latitude: randomLatitude
        };
    }

    const randomCoordinates = getRandomCoordinates();
    const locationLongitude = randomCoordinates.longitude;
    const locationLatitude = randomCoordinates.latitude;

    map_setting(locationLongitude, locationLatitude);
    console.log('로딩 완료!');
    console.log(`Random Longitude: ${locationLongitude}`);
    console.log(`Random Latitude: ${locationLatitude}`);
});

function map_setting(longitude, latitude) {
    var mapContainer = document.getElementById('store-position-map'); // 지도를 표시할 div가 존재하는지 확인합니다.
    if (!mapContainer) {
        console.error('지도 컨테이너가 존재하지 않습니다.');
        return;
    }

    var mapOption = {
        center: new kakao.maps.LatLng(latitude, longitude), // 지도의 중심좌표
        level: 3 // 지도의 확대 레벨
    };

    var map = new kakao.maps.Map(mapContainer, mapOption); // 지도를 생성합니다

    // 마커가 표시될 위치입니다 
    var markerPosition = new kakao.maps.LatLng(latitude, longitude);

    // 마커를 생성합니다
    var marker = new kakao.maps.Marker({
        position: markerPosition
    });

    // 마커가 지도 위에 표시되도록 설정합니다
    marker.setMap(map);
}


////////////////////////주문하기////////////////////////////
const cartBtn = document.getElementById('cart-btn');
// 장바구니 버튼
cartBtn.onclick = () => {
    if(check_input()){
        const data = new FormData(orderForm);
        const csrfToken = data.get('_csrf');
        fetch(`/user/cart`, {
            method: 'POST',
            headers: {"X-CSRF-TOKEN": csrfToken},
            body: data
        }).then(response => {
            switch(response.status){
                case 201:
                    alert("장바구니에 상품을 추가하였습니다");
                    break;
                case 401:
                    alert("로그인이 필요합니다");
                    break;
                default:
                    alert('알 수 없는 에러가 발생했습니다. 관리자에게 문의해주세요')
            }
        })
    }
}

// 담기 전, 확인 사항
function check_input(){
const amountInput = document.getElementsByClassName('number-select');

    if(+amountInput.value < 1){
        alert('적어도 하나 이상의 음식을 고르셔야 합니다 !');
        return false;
    }
    return true;
}
///////////////////////////////////////////////////////////////////















