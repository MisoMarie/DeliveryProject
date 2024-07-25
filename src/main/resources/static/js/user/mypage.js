const [reservationViewBtn, wishlistViewBtn] = document.querySelectorAll('.mypage-list-section li');
const mypageContentsSection = document.querySelector('.mypage-contents');


/************************* wishlist *************************/

wishlistViewBtn.onclick = get_wishlist;

function get_wishlist(){
    fetch(`/user/wishlist`)
        .then(response => {
            if(response.ok){
                return response.json();
            }
        })
        .then(stores => {
            console.log(stores)
            create_wishlist_card(stores);
        });
}

function create_wishlist_card(wishlist){
    mypageContentsSection.innerHTML = '';
    for(stores of wishlist){
        mypageContentsSection.insertAdjacentHTML(`beforeend`,
            `<div class="card">
                <a href="/filter/store/${stores.store_no}">
                    <img src="${stores.store_img.image_url}" alt="">
                </a>
                <div class="card-contents">
                    <h4><a href="/filter/store/${stores.store_no}">${stores.store_name}</a></h4>
                </div>
            </div>`
        );
    }
}