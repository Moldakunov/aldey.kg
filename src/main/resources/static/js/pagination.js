var sortField = "", direction = "";

function getPageData(data) {
    var nHtml = '';
    var products = [];
    var imageUrl = '';

    for (var p in data) {
        if (data.hasOwnProperty(p)) {
            products.push(data[p]);
        }
    }

    products.forEach(function (i) {
        if (Boolean(i.imageUrl)) {
            imageUrl = i.imageUrl;
        } else {
            imageUrl = "/img/no-photo-small.jpg";
        }

        nHtml +=
            '<div class="col-xl-2 col-lg-4 col-md-4 col-sm-6 col-6 "' +
            '<div class="ps-product">' +
            '<div class="ps-product__thumbnail">' +
            '<a href="/product/' + i.seoUrl + '" th:href="/product/' + i.seoUrl + '">' +
            '<img alt="" src="' + imageUrl + '"/>' +
            '</a>' +
            '</div>' +
            '<div class="ps-product__container">' +
            '<div id="itemContent" class="ps-product__content">' +
            '<a class="ps-product__title" th:href="/product/' + i.seoUrl + '" href="/product/' + i.seoUrl + '">' + i.nameShort + '</a>' +
            '<p class="ps-product__price">' + i.price + ' сом</p>' +
            '</div>' +
            '</div>' +
            '</div>' +
            '</div>'
    });
    return nHtml;
}

// проверка если количество страниц равна одному, то не надо пагинации (просто вывести товары)
if ($('#totalPages').text() < 2) {
    paginate(1);
} else {
    applyPagination();
}

function applyPagination() {
    $('#pagination').twbsPagination({
        totalPages: $('#totalPages').text(),
        visiblePages: 5,
        first: 'Начало',
        next: 'Вперед',
        prev: 'Назад',
        last: 'Конец',
        paginationClass: 'pagination',
        hideOnlyOnePage: true,
        onPageClick: function (event, page) {
            paginate(page);
            $("html, body").animate({ scrollTop: 0 }, "slow");
    });
}

function paginate(page) {
    /*var num = $("#searchText").val() ? 1 : ($("#searchTextMobile").val() ? 2 : ($("#searchTextMobileMenu").val() ? 3 : 0));*/

    if ($("#searchText").val()) {
        $.get("/paginate/search", {
            pageNumber: page,
            searchText: document.getElementById("searchText").value,
            sortField: sortField,
            direction: direction
        }, function (data) {
            document.getElementById("row").innerHTML = getPageData(data);
        });
    }

    if ($('#category').length && $('#subCategory').length < 1) {
        $.get("/paginate/category", {
            pageNumber: page,
            categoryName: document.getElementById("category").textContent,
            sortField: sortField,
            direction: direction
        }, function (data) {
            document.getElementById("row").innerHTML = getPageData(data);
        });
    }

    if ($('#category').length && $('#subCategory').length) {
        $.get("/paginate/subcategory", {
            pageNumber: page,
            categoryName: document.getElementById("category").textContent,
            subCategoryName: document.getElementById("subCategory").textContent,
            sortField: sortField,
            direction: direction
        }, function (data) {
            document.getElementById("row").innerHTML = getPageData(data);
        });
    }
}
