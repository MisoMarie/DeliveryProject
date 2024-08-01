const reviewSectionH3 = document.querySelector('.review-section-header > h3');
const reviewContainer = document.querySelector('.review-container');
const path = location.pathname.split('/');
const storeNo = path[path.length - 1];
const cartBtn = document.getElementById('cart-btn');
const orderForm = document.getElementById('order-form');


/*************************** KAKAO MAP VIEW *************************/
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
/*************************** 장바구니 ****************************/

// 장바구니 버튼
cartBtn.onclick = () => {
    if(check_input()){
        const data = new FormData(orderForm);
        const csrfToken = data.get('_csrf');
        fetch(`/user/cart/duplicate`).then(response => {
            // 서버에서 받아온 true, false값?
        }).then(value => {
            if(value === false){
                const userConfirm = confirm("장바구니에는 한 가게에서 가져온 음식들만 담아야 합니다. 장바구니에 있는 음식들을 지울까요?");
                if (userConfirm){
                    fetch(`/user/cart`,{
                        method: "DELETE",
                        headers: {
                            "Content-Type": "application/json",
                        },
                    }).then(response => {
                        if(response.ok){
                            alert('상품을 장바구니에서 제거하였습니다');
                            // location.reload(); // 화면 새로고침
                        }
                    });
                }

            }else{
                fetch(`/user/cart`, {
                    method: 'POST',
                    headers: {"X-CSRF-TOKEN": csrfToken},
                    body: data
                }).then(response => {
                    console.log(response)
                    switch(response.status){
                        case 201:
                            alert("장바구니에 음식 상품을 추가하였습니다");
                            break;
                        case 401:
                            alert("로그인이 필요합니다");
                            break;
                        default:
                            alert('알 수 없는 에러가 발생했습니다. 관리자에게 문의해주세요')
                    }
                })
            }
        })
        console.log("보내짐")
    }
}


// 음식 수량 체크
function check_input(){
    const foodAmount = document.querySelector('.number-select');
    if(+foodAmount.value < 1){
        alert('적어도 하나 이상 선택해야 합니다.');
        return false;
    }
    return true;
}


/*************************** REVIEW *************************/

get_reviews('recent'); // 사이트 접속 시 최초 요청
function get_reviews(order){
    fetch(`/review/${storeNo}?order=${order}`)
        .then(response => response.json())
        .then(reviewList => {
            reviewContainer.innerHTML = '';
            reviewSectionH3.textContent = '후기 0개';

            if(reviewList.length === 0){
                reviewContainer.innerHTML =
                    `<section class="review">
                    <div>현재 작성된 리뷰가 없습니다....</div>
                </section>`;
                return;
            }

            reviewSectionH3.textContent = `후기 ${reviewList.length}개`;
            for (const review of reviewList) {
                let starHTML = ``;
                for(let i = 1; i <= review.score; i++){
                    starHTML += `<i class="fa-solid fa-star"></i>`;
                }
                for(let i = 1; i <= 5 - review.score; i++){
                    starHTML += `<i class="fa-regular fa-star"></i>`;
                }
                reviewContainer.insertAdjacentHTML(`beforeend`,
                    `<section class="review">
                    <div class="reviewer-container">
                        <img class="reviewer-img" src="${review.user.image.imageURL}" alt="#" onerror="this.src='https://image.oliveyoung.co.kr/uploads/images/mbrProfile/2024/03/12/1710248677621.png?RS=62x60&CS=60x60'">
                        <span class="reviewer-nickname">${review.user.nickname}</span>
                    </div>
                    <div class="review-content-container">
                        <div class="review-header">
                            <span class="review-score">
                                ${starHTML}
                            </span>
                            <span class="review-date">${review.write_date}</span>
                        </div>
                        <div class="review-body">
                            <p class="review-content">
                                ${review.content}
                            </p>
                        </div>
                    </div>
                </section>`
                );

            }
        })
}