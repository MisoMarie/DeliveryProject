// 좋아요 하기
function add_wishlist(storeNo, addBtn){
    const csrfToken = document.querySelector('meta[name=_csrf]').getAttribute('content');
    fetch(`/user/wishlist/${storeNo}`, {
        method: "POST",
        headers: { "X-Csrf-Token": csrfToken }
    }).then(response => {
        if(response.ok && response.status === 201){
            change_wishlist_btn(storeNo, addBtn, true);
        }
    });
}

// 좋아요 취소하기
function remove_wishlist(storeNo, addBtn){
    const csrfToken = document.querySelector('meta[name=_csrf]').getAttribute('content');
    fetch(`/user/wishlist/${storeNo}`, {
        method: "DELETE",
        headers: { "X-Csrf-Token": csrfToken }
    }).then(response => {
        if(response.ok && response.status === 200){
            change_wishlist_btn(storeNo, addBtn, false);
        }
    });
}

// wishlist 버튼을 클릭 시, 버튼의 모양과 내용을 변경
function change_wishlist_btn(storeNo, btn, isWished){
    const iconTag = btn.querySelector('i');
    const spanTag = btn.querySelector('span');
    // add_wishlist가 실행된 후 (좋아요 이후. 버튼을 좋아요 취소로 변경해야함)
    if(isWished){
        iconTag.classList.add('fa-solid'); // 색을 채우는 것을 나타내는 클래스 추가
        iconTag.classList.remove('fa-regular'); // 빈 색을 나타내는 클래스 제거
        if(spanTag != null) spanTag.textContent = '좋아요 취소'; // 좋아요 글자를 좋아요 취소로
        // 버튼을 눌렀을 시 , 좋아요를 제거하는 동작을 적용시킨다
        btn.onclick = event => {
            remove_wishlist(storeNo, btn);
        }
    }
    // remove_wishlist가 실행된 후 (좋아요 취소 이후. 버튼을 좋아요로 변경해야함)
    else{
        iconTag.classList.remove('fa-solid'); // 색을 채우는 것을 나타내는 클래스 제거
        iconTag.classList.add('fa-regular'); // 빈 색을 나타내는 클래스 추가
        if(spanTag != null) spanTag.textContent = '좋아요'; // 좋아요 취소 글자를 좋아요로
        // 버튼을 눌렀을 시 , 좋아요하는 동작을 적용시킨다
        btn.onclick = event => {
            add_wishlist(storeNo, btn);
        }
    }
}












