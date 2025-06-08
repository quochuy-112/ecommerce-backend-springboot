function openPopup(){
    const overplay = document.getElementById('overplay');
    let popUpLeft = document.getElementById('popup-left');
    let popUpRight = document.getElementById('popup-right');

    overplay.style.display = (overplay.style.display === 'block') ? 'none' : 'block';

    popUpLeft.addEventListener('mouseover', () => {
        popUpRight.style.display = 'grid';
    });

    popUpLeft.addEventListener('mouseout', () => {
        popUpRight.style.display = 'none';
    });

    popUpLeft.innerHTML = renderPopupLeftItem(categories);

    let currentMouseoverPLIndex = '';

    popUpRight.addEventListener('mouseover', () => {
        popUpRight.style.display = 'grid';

        const currentMouseoverPLItem = document.getElementById(currentMouseoverPLIndex);
        currentMouseoverPLItem.style.cssText = 'background-color: red; color: white; border-radius: 0;';

        currentMouseoverPLItem.classList.add('arrow');
    });


    popUpRight.addEventListener('mouseout', () => {
        popUpRight.style.display = 'none';

        const currentMouseoverPLItem = document.getElementById(currentMouseoverPLIndex);
        currentMouseoverPLItem.style.cssText = 'background-color: white; color: black; border-radius: 3px;';

        currentMouseoverPLItem.classList.remove('arrow');
    });

    Array.from(document.getElementsByClassName('popup-item')).forEach(element => {
        element.addEventListener('mouseover', () => {
            currentMouseoverPLIndex = '';

            const category = categories.find(category => category.id === element.id);
            popUpRight.innerHTML = renderPopupRightItem(category.subcategories);

            currentMouseoverPLIndex = element.id;

            const currentMouseoverPLItem = document.getElementById(currentMouseoverPLIndex);
            currentMouseoverPLItem.style.cssText = 'background-color: red; color: white; border-radius: 0;';
        })
    });

    Array.from(document.getElementsByClassName('popup-item')).forEach(popItem => {
        popItem.addEventListener('mouseout', () => {
            popItem.style.cssText = 'background-color: white; color: black; border-radius: 3px;';
        })
    })
}

function closePopup(){
    document.getElementById('overplay').style.display = 'none';
}

function renderPopupLeftItem(categories){
    return categories.map(category => `<div class="popup-item" id="${category.id}" onclick="window.location.href='../pages/all-product.html'">
                <span class="material-symbols-outlined">computer</span>
                <p>${category.categoryName}</p>
                <span class="material-symbols-outlined">chevron_right</span>
            </div>`).join('');
}

function renderPopupRightItem(subcategories){
    return subcategories.map(subcategory => `
        <div>
            <a href="">${subcategory.name}</a>
        </div>`).join('');
}