document.addEventListener('DOMContentLoaded', () => {
    fetch('/geartech/top-products')
        .then(response => response.json())
        .then(data => {
            const container = document.getElementById("top-product");

            data.forEach(product => {
                container.innerHTML += `
                    <h2 class="section-title">${product.name}</h2>
                    <div class="section-showcase">
                            <div class="courses-container">
                                <div class="grid-courses" id="${product.id}"></div>
                            </div>
                
                            <button class="courses-container-btn courses-prev hidden" id="${product.id}-prev-btn">
                                <span class="material-symbols-outlined">chevron_left</span>
                            </button>
                
                            <button class="courses-container-btn courses-next" id="${product.id}-next-btn">
                                <span class="material-symbols-outlined">chevron_right</span>
                            </button>
                        </div>
                
                        <button type="button" class="section-more-btn" id="${product.id}-more-btn"><b>Xem tất cả sản phẩm</b></button>
                    </section>`;
            });
        })
        .catch(error => console.error('Fetch error:', error));
});


document.addEventListener('DOMContentLoaded', () => {
    fetch('/geartech/top-products')
        .then(response => response.json())
        .then(data => {

            console.log(data);
            data.forEach(topProduct => {
                const productContainer = document.getElementById(topProduct.id);

                // Kiểm tra có container và danh sách sản phẩm hợp lệ
                if (productContainer && Array.isArray(topProduct.products) && topProduct.products.length > 0) {
                    topProduct.products.forEach(product => {
                        productContainer.innerHTML += `
                        <a class="card-item" href="">
                            <div>
                                <img src="https://product.hstatic.net/200000722513/product/thit-k-cha-c-tn-_4__d80b68c7123a41b89bf213ffadb4d43f_grande.png" alt="">
                            </div>
                            <div class="card-info">
                                <h3 class="card-title">${product.name}</h3>
                                <div class="specs">
                                    <span><span class="material-symbols-outlined">select_all</span></span>
                                    <span><span class="material-symbols-outlined">memory</span></span>
                                </div>
                                <div class="hardware">
                                    <span><span class="material-symbols-outlined">developer_board</span></span>
                                    <span><span class="material-symbols-outlined">memory_alt</span></span>
                                    <span><span class="material-symbols-outlined">storage</span></span>
                                </div>
                                <div class="rating">
                                    <span><span class="material-icons-outlined">star</span></span>
                                </div>
                                <h3 class="price">${product.price}₫</h3>
                            </div>
                        </a>`;
                    });
                }
            });
        })
        .catch(error => console.error('Fetch error:', error));
});





let slidesBanner = [
    '/geartech/assets/banner1.jpg',
    '/geartech/assets/banner2.jpg',
    '/geartech/assets/banner3.jpg',
    '/geartech/assets/banner4.jpg'
];

const carousel = document.getElementById('carousel');
const bannerNextBtn = document.getElementById('banner-next-btn');
const bannerPrevBtn = document.getElementById('banner-prev-btn');
let currentSlide = 0;

slidesBanner.forEach(element => {
    let img = document.createElement('img');
    img.src = element;
    carousel.appendChild(img);
});

function moveSlideBanner(currentSlide, carouselWidth) {
    carousel.style.transform = `translateX(-${currentSlide * carouselWidth}px)`;
}

bannerNextBtn.addEventListener('click', function () {
    const carouselWidth = carousel.offsetWidth;
    currentSlide = (currentSlide + 1) % slidesBanner.length;
    moveSlideBanner(currentSlide, carouselWidth);
});

bannerPrevBtn.addEventListener('click', function () {
    const carouselWidth = carousel.offsetWidth;
    currentSlide = (currentSlide - 1 + slidesBanner.length) % slidesBanner.length;
    moveSlideBanner(currentSlide, carouselWidth);
});




//
// function toSlug(str) {
//     return str
//         .normalize('NFD')                   // Tách dấu khỏi ký tự gốc
//         .replace(/[\u0300-\u036f]/g, '')    // Xóa các dấu
//         .replace(/đ/g, 'd')                 // Chuyển đ -> d
//         .replace(/Đ/g, 'D')                 // Chuyển Đ -> D
//         .toLowerCase()                     // Chuyển thành chữ thường
//         .trim()                            // Xóa khoảng trắng đầu/cuối
//         .replace(/\s+/g, '-')              // Thay khoảng trắng bằng gạch ngang
//         .replace(/[^a-z0-9\-]/g, '');      // Xóa ký tự đặc biệt (nếu muốn làm ID an toàn)
// }
//
// const mainChildren = document.querySelector('main').children;
// const referenceNode = mainChildren[0];
// referenceNode.insertAdjacentHTML('beforebegin', renderSection(topProduct));
//
// topProduct.forEach(element => {
//     let idSection = toSlug(element.id);
//     document.getElementById(idSection).innerHTML= renderProductCard(element.product);
//
// })
//
// function renderSection(topProduct){
//     return topProduct.map(product => `<section>
//         <h2 class="section-title">${product.name}</h2>
//
//         <div class="section-showcase">
//             <div class="courses-container">
//                 <div class="grid-courses" id="${toSlug(product.id)}"></div>
//             </div>
//
//             <button class="courses-container-btn courses-prev hidden" id="${toSlug(product.id)}-prev-btn">
//                 <span class="material-symbols-outlined">chevron_left</span>
//             </button>
//
//             <button class="courses-container-btn courses-next" id="${toSlug(product.id)}-next-btn">
//                 <span class="material-symbols-outlined">chevron_right</span>
//             </button>
//         </div>
//
//         <button type="button" class="section-more-btn" id="${toSlug(product.id)}-more-btn"><b>Xem tất cả sản phẩm</b></button>
//     </section>`).join('');
// }
//
// function renderProductCard(list){
//     return list.map(product => `<a class="card-item" href="${product.href}?id=${product.id}" >
//         <div>
//             <img src="${product.imgSrc}" alt="${product.imgAlt}">
//             <div class="card-infor">
//                 <h4>${product.title}</h4>
//                 <div class="hardware">
//                     <span><span class="material-symbols-outlined">select_all</span>${product.specs.gpu}</span>
//                     <span><span class="material-symbols-outlined">memory</span>${product.specs.cpu}</span>
//                 </div>
//
//                 <div class="hardware">
//                     <span><span class="material-symbols-outlined">developer_board</span>${product.specs.mainboard}</span>
//                     <span><span class="material-symbols-outlined">memory_alt</span>${product.specs.ram}</span>
//                     <span><span class="material-symbols-outlined">storage</span>${product.specs.storage}</span>
//                 </div>
//
//                 <div class="rating">
//                     <span><span class="material-icons-outlined">star</span>${product.rating.stars} (${product.rating.votes})</span>
//                 </div>
//                 <h3 class="price">${product.price}₫</h3>
//             </div>
//         </div>
//     </a>`).join('');
// }
//
//
// topProduct.forEach(element => {
//     let currentProduct = element.currentIndex;
//     const prevBtnId = toSlug(element.id) + "-prev-btn";
//     const nextBtnid = toSlug(element.id) + "-next-btn";
//
//     document.getElementById(prevBtnId).addEventListener('click', function(){
//         currentProduct -= 4;
//         moveProductShowcase(currentProduct, element.id);
//
//         updateNextBtn(currentProduct, element.product.length, nextBtnid);
//         updatePrevBtn(currentProduct, prevBtnId);
//     })
//
//     document.getElementById(nextBtnid).addEventListener('click', function(){
//         currentProduct += 4;
//         moveProductShowcase(currentProduct, element.id);
//
//         updateNextBtn(currentProduct, element.product.length, nextBtnid);
//         updatePrevBtn(currentProduct, prevBtnId);
//     })
// })
//
// const cardWidth = 260;
// const gap = 10;
//
// function moveProductShowcase(current, id) {
//     let distance = current * cardWidth + current * gap;
//     document.getElementById(id).style.transform = `translateX(-${distance}px)`;
// }
//
// function updatePrevBtn(currentProduct, btnId){
//     if (currentProduct === 0) {
//         document.getElementById(btnId).classList.add('hidden');
//     } else {
//         document.getElementById(btnId).classList.remove('hidden');
//     }
// }
//
// function updateNextBtn(currentProduct, productQuantity, btnId) {
//     if (currentProduct >= productQuantity - 4) {
//         document.getElementById(btnId).classList.add('hidden');
//     } else {
//         document.getElementById(btnId).classList.remove('hidden');
//     }
// }
//
//
//
// // move to show all product
// let tabBar = document.getElementById("tab-bar");
// let subTabBar = document.getElementById("sub-tab-bar");
// let allProduct = document.getElementById("all-product");
//
// function activeTabBtn(parentElement) {
//     parentElement.addEventListener("click", function (e) {
//         const clicked = e.target;
//         const children = Array.from(parentElement.children);
//         children.forEach(child => child.classList.remove("active"));
//         clicked.classList.add("active");
//
//         openProductList(event, categories[children.indexOf(clicked)].subcategories, 0);
//     });
// }
//
// function activeSubTabBtn(parentElement) {
//     parentElement.addEventListener("click", function (e) {
//         const clicked = e.target;
//         const children = Array.from(parentElement.children);
//         children.forEach(child => child.classList.remove("active"));
//         clicked.classList.add("active");
//     });
// }
//
// categories.forEach(element =>{
//     let btn = document.createElement('button');
//     btn.classList.add("tab-button");
//     btn.textContent = element.categoryName;
//     btn.id = element.id;
//     btn.onclick = (event) => openSubcategory(event, element.subcategories);
//     tabBar.appendChild(btn);
// })
// tabBar.children[0].classList.add('active');
// openSubcategory(event, categories[0].subcategories);
//
// function openSubcategory(event, subcategories){
//     subTabBar.innerHTML = '';
//
//     for(let [index, subcategory] of subcategories.entries()){
//         let btn = document.createElement('button');
//         btn.classList.add("sub-tab-button");
//         btn.textContent = subcategory.name;
//         btn.id = subcategory.id;
//         btn.onclick = (event) => openProductList(event, subcategories, index);
//         subTabBar.appendChild(btn);
//     }
//
//     subTabBar.children[0].classList.add('active');
// }
// openProductList(event, categories[0].subcategories, 0);
//
// activeTabBtn(tabBar);
// activeSubTabBtn(subTabBar);
//
// function openProductList(event, subcategories, subcategoriesIndex){
//     allProduct.innerHTML = '';
//     allProduct.innerHTML = renderAllProductContainer(subcategories, subcategoriesIndex);
//
//     document.getElementById(subcategories[subcategoriesIndex].id + '-grid-courses').innerHTML= renderProductCard(subcategories[subcategoriesIndex].product);
//
//     categories.forEach(category => {
//
//         category.subcategories.forEach(subcategory => {
//
//             let currentProduct = subcategory.currentIndex;
//
//             const prevBtnId = subcategory.id + "-prev-btn";
//             const nextBtnid = subcategory.id + "-next-btn";
//             const gridCourses = subcategory.id + "-grid-courses";
//
//             let prevbtn = document.getElementById(prevBtnId);
//             let nextBtn = document.getElementById(nextBtnid);
//
//             if(prevbtn && nextBtn){
//                 prevbtn.addEventListener('click', function(){
//                     currentProduct -= 4;
//                     moveProductShowcase(currentProduct, gridCourses);
//
//                     updateNextBtn(currentProduct, subcategory.product.length, nextBtnid);
//                     updatePrevBtn(currentProduct, prevBtnId);
//                 })
//
//                 nextBtn.addEventListener('click', function(){
//                     currentProduct += 4;
//                     moveProductShowcase(currentProduct, gridCourses);
//
//                     updateNextBtn(currentProduct, subcategory.product.length, nextBtnid);
//                     updatePrevBtn(currentProduct, prevBtnId);
//                 })
//             }
//         })
//     })
// }
//
// function renderAllProductContainer(subcategories, subcategoriesIndex){
//     return `<div class="section-showcase">
//                 <div class="courses-container">
//                     <div class="grid-courses" id="${subcategories[subcategoriesIndex].id}-grid-courses"></div>
//                 </div>
//
//                 <button class="courses-container-btn courses-prev hidden" id="${subcategories[subcategoriesIndex].id}-prev-btn">
//                     <span class="material-symbols-outlined">chevron_left</span>
//                 </button>
//
//                 <button class="courses-container-btn courses-next" id="${subcategories[subcategoriesIndex].id}-next-btn">
//                     <span class="material-symbols-outlined">chevron_right</span>
//                 </button>
//             </div>
//
//             <button type="button" class="section-more-btn" id="${subcategories[subcategoriesIndex].id}-more-btn"><b>Xem tất cả sản phẩm</b></button>`;
// }
//
//
// // ----------category loading-----------
// document.getElementById('category-container').innerHTML= renderCategoryItem(categories);
//
// function renderCategoryItem(listCategory){
//     return listCategory.map(category => `<div class="category-item">
//                 <img src="${category.img}" alt="${category.categoryName}">
//                 <p>${category.categoryName}</p>
//             </div>`).join('');
// }