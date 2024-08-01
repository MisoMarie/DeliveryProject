const orderForm = document.getElementById('order-form');
const productContainers = document.getElementsByClassName('product');
const allSectionBtn = document.querySelector('.cart-select-container input[type="checkbox"]');
const allRmBtn = document.getElementById('cart-all-rm-btn');
const cartBuyBtn = document.getElementById('cart-buy-btn');
// 전체 선택 버튼 눌렀을 때
allSectionBtn.addEventListener('click', () => {
    [...productContainers].forEach(productContainer => {
        const checkBoxInput = productContainer.querySelector('input[type="checkbox"]');
        checkBoxInput.checked = allSectionBtn.checked;
    });
});
// 한개 상품 삭제 버튼 눌렀을 시
[...productContainers].forEach(productContainer => {
    const cartNoInput = productContainer.querySelector('input[name=no]');
    const cartRmBtn = productContainer.querySelector('button');
    cartRmBtn.addEventListener('click', (e) => {
        delete_cart_items([{no: +cartNoInput.value}]);
    });
});
// 선택 삭제 버튼 눌렀을 때
allRmBtn.addEventListener('click', () => {
    const items = collect_cart_selected_items();
    if(items.length <= 0){
        alert('상품을 하나이상 선택해주세요');
        return;
    }
    delete_cart_items(items);
});
// 구매 버튼 눌렀을 때
cartBuyBtn.addEventListener('click', (e) => {
    e.preventDefault();
    const items = collect_cart_selected_items();
    if(items.length <= 0){
        alert('상품을 하나이상 선택해주세요');
        return;
    }
    buy_cart_items(items);
});
// 선택되어있는 상품들을 수집
function collect_cart_selected_items(){
    const items = []; //장바구니 아이템 번호들을 가지는 리스트
    // 모든 장바구니 상품들을 순회한다
    [...productContainers].forEach(productContainer => {
        const checkBoxInput = productContainer.querySelector('input[type="checkbox"]');
        const cartNoInput = productContainer.querySelector('input[name=no]');
        // 상품이 선택되어있다면
        if(checkBoxInput.checked) {
            const productNo = +productContainer.querySelector('.product-no').value;
            const name = productContainer.querySelector('.name').textContent;
            const amount = +productContainer.querySelector('.amount').textContent.replaceAll('개', '');
            const price = +productContainer.querySelector('.price').textContent.replaceAll(',','').replaceAll('원','');
            items.push({
                no: +cartNoInput.value,
                product: { no: productNo, name: name },
                amount: amount,
                price: price
            });
        }
    });
    return items;
}
// 선택되어 있는 장바구니 아이템을 삭제
function delete_cart_items(items){
    fetch(`/user/cart`,{
        method: "DELETE",
        headers: {
            "Content-Type": "application/json",
        },
        body: JSON.stringify(items)
    }).then(response => {
        if(response.ok){
            alert('상품을 장바구니에서 제거하였습니다');
            location.reload(); // 화면 새로고침
        }
    });
}
// 장바구니 아이템들을 구매
function buy_cart_items(items){
    fetch(`/user/order`,{
        method: "POST",
        headers: {
            "Content-Type": "application/json",
        },
        body: JSON.stringify(items)
    }).then(response => {
        if(response.ok){
            location.href = '/user/order';
        }
    });
}


IMP.init("imp14271731");
const amountChoose = document.querySelectorAll('input[name="amount"]'); // amount에 장바구니 최종 금액이 들어감

cartBuyBtn.onclick = () => { // . 안에 클래스나 id명
    let selectedCost;
    let userId;
    let foodName;
    // 사용자가 선택한 결제 금액 가져오기
    amountChoose.forEach(unit => {
        if (unit.checked) {
            selectedCost = unit.value;
        }
    });

    // 선택하지 않고 결제 버튼을 누를시 경고 메시지 출력
    if (!selectedCost) {
        alert("적어도 하나 이상은 선택해야 합니다!");
        return;
    }

    // 아임포트 결제 요청 데이터
    let paymentData = {
        pg: "kakaopay.TC0ONETIME",
        pay_method: "card",
        merchant_uid: `order_no_${new Date().getTime()}`,
        name: foodName,
        buyer_name: userId,
        amount: selectedCost
    };

    // 아임포트 결제 요청
    IMP.request_pay(paymentData, function (response) {
        if (response.success) {
            const token = document.querySelector("meta[name=_csrf]").getAttribute("content");
            // 결제 성공 시 서버에 음식 정보를 전달.
            fetch('/user/cart', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                    "X-CSRF-TOKEN": token
                },
                body: JSON.stringify({...response, foodName,userId,selectedCost}) // 서버에 전송할 데이터
            })
                .then(response => {
                    if(response.status === 201){
                        alert("결제가 성공하였습니다!");
                        window.close();
                        // 결제 성공 시 이전 페이지로 리다이렉트
                    }
                })
        } else {
            // 결제 실패 시 에러 처리
            alert("결제가 실패하였습니다!");
        }
    });
}




