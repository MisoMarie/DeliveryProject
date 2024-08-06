const orderForm = document.getElementById('order-form');
const productContainers = document.getElementsByClassName('product');
console.log(productContainers)
const csrfTokenInput = orderForm.querySelector('input[name="_csrf"]');
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
    cartRmBtn.addEventListener('click', () => {
        console.log("삭제버튼 클릭")
        delete_cart_items([{cart_no: +cartNoInput.value}]);
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
                cart_no: +cartNoInput.value,
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
    const csrfToken = csrfTokenInput.value;
    fetch(`/user/cart`,{
        method: "DELETE",
        headers: {
            "Content-Type": "application/json",
            "X-CSRF-Token": csrfToken
        },
        body: JSON.stringify(items)
    }).then(response => {
        if(response.ok){
            alert('상품을 장바구니에서 제거하였습니다');
            location.reload(); // 화면 새로고침
        }
    });
}

// 구매 버튼 눌렀을 때
IMP.init("imp14271731");
cartBuyBtn.addEventListener('click', (e) => {
    e.preventDefault();
    console.log("구매 버튼이 눌림")

    let requestData;
    let cost = document.getElementsByClassName('buy-amount');
    const foodId = document.getElementsByClassName('product-no');
    const merchantUid = `order_no_${new Date().getTime()}`; // 주문번호 생성
    const items = collect_cart_selected_items();

    // 음식을 선택하지 않고 결제 버튼을 누를시 경고 메시지 출력
    if (items === null) {
        alert("적어도 하나 이상의 음식을 담아야 합니다.");
        return;
    }else{
        requestData = {
            pg: "kakaopay.TC0ONETIME",
            pay_method: "card",
            merchant_uid: merchantUid, // 상점에서 생성한 고유 주문번호
            name: foodId.value,
            amount: cost.value,
        };

        IMP.request_pay(requestData, function (response){
            if (response.success){
                buy_cart_items(items);
            }
            else {
                // 결제 실패 시 에러 처리
                alert("결제가 실패하였습니다!");
            }
        })
    }
});


// 장바구니 아이템들을 구매
function buy_cart_items(items){
    const csrfToken = csrfTokenInput.value;
    fetch(`/user/order`,{
        method: "POST",
        headers: {
            "Content-Type": "application/json",
            "X-CSRF-Token": csrfToken
        },
        body: JSON.stringify(items)
    }).then(response => {
        if(response.ok){
            location.href = '/user/cart';
        }
    });
}

////////////////////////////////////////
