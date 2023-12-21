document.addEventListener('DOMContentLoaded', function () {
    viewProduct();
    getCategories();
});

document.addEventListener('DOMContentLoaded', function () {
    // Kiểm tra xem có kết quả tìm kiếm được lưu trữ hay không
    var storedSearchResults = sessionStorage.getItem('searchResults');
    if (storedSearchResults) {
        // Hiển thị kết quả tìm kiếm
        displayProducts(JSON.parse(storedSearchResults));
    } else {
        displayProducts();
    }

    function getParameterByName(name, url) {
        if (!url) url = window.location.href;
        name = name.replace(/[\[\]]/g, "\\$&");
        var regex = new RegExp("[?&]" + name + "(=([^&#]*)|&|#|$)"),
            results= regex.exec(url);
        if (!results) return null;
        if (!results[2]) return '';
        return decodeURIComponent(results[2].replace(/\+/g, " "));
    }
});

document.addEventListener('DOMContentLoaded', function () {
    // var searchButton = document.getElementById('searchButton');
    // var closeButton = document.getElementById('closeSearchForm');
    // searchButton.addEventListener('click', function () {
    //     // Gọi hàm searchProducts khi nút tìm kiếm được ấn
    //     searchProducts();
    //
    //     // Đóng form tìm kiếm (thay đổi ID của form tùy vào mã HTML của bạn)
    //     // var searchForm = document.getElementById('searchForm');
    //     // if (searchForm) {
    //     //     searchForm.style.display = 'none'; // Hoặc thực hiện các hành động đóng form tương ứng
    //     // }
    // });

    var closeButton = document.getElementById('closeSearchForm');
    var searchButton = document.getElementById('searchButton');

    closeButton.addEventListener('click', function () {
        var searchForm = document.getElementById('searchForm');
        if (searchForm) {
            searchForm.style.display = 'none';
        }
    });

    searchButton.addEventListener('click', function () {
        // Gọi hàm searchProducts khi nút tìm kiếm được ấn
        searchProducts();

        // Mở lại form tìm kiếm
        var searchForm = document.getElementById('searchForm');
        if (searchForm) {
            searchForm.style.display = 'flex'; // hoặc 'flex', tùy thuộc vào kiểu hiển thị của form ban đầu
        }
    });
});

function viewProduct(data){
    console.log("Product data:", data);
    $.ajax({
        type: "GET",
        url: "http://localhost:8080/api/clients",
        success: function (data) {
            displayProducts(data);
        }
    })
}

function searchProducts() {
    // event.preventDefault();
    var keyword = $('#searchKeyword').val();
    $.ajax({
        type: "GET",
        url: "http://localhost:8080/api/clients/search",
        data: { keyword: keyword },
        success: function (data) {
            // Lưu trữ kết quả tìm kiếm vào biến hoặc Local Storage
            sessionStorage.setItem('searchResults', JSON.stringify(data));
            displayProducts(data);
        }
    });
}

// $('#searchForm').submit(searchProducts);
function getCategories() {
    $.ajax({
        type: "GET",
        url: "http://localhost:8080/api/clients/getCategory",
        success: function (response) {
            updateCategoryList(response);
        },
        error: function () {
            console.log("Error fetching categories");
        }
    });
}

function filterProductsByCategory(categoryId) {
    console.log("Category Clicked:", categoryId);
    if(categoryId === 0){
        viewProduct();
    }else {
        $.ajax({
            type: "GET",
            url: "http://localhost:8080/api/clients/searchByCategory",
            data: { categoryId: categoryId },
            success: function (response) {
                updateProductList(response);
                console.log("Category Clicked: " + categoryId);
                viewProductsByCategory(categoryId);
            },
            error: function () {
                console.log("Error fetching products");
            }
        });
    }
}

function updateCategoryList(categories) {
    var categoryList = $("#categoryList");
    categoryList.empty();

    var allCategoryHtml = '<li class="active" data-category="0" onclick="filterProductsByCategory(0)">All</li>';
    categoryList.append(allCategoryHtml);

    $.each(categories, function (index, category) {
        if (category.category_id !== undefined) {
            var categoryHtml = '<li data-category="' + category.category_id + '" onclick="filterProductsByCategory(' + category.category_id + ')">' +
                category.name + '</li>';
            categoryList.append(categoryHtml);
        }
    });
}

function viewProductsByCategory(categoryId) {
    $.ajax({
        type: "GET",
        url: "http://localhost:8080/api/clients/searchByCategory",
        data: { categoryId: categoryId },
        success: function (data) {
            console.log("duoc goi")
            displayProducts(data);
        },
        error: function () {
            console.log("Error fetching products");
        }
    });
}

function updateProductList(products) {
    var productList = $("#productList");
    productList.empty();

    console.log("Products received:", products);

    $.each(products, function (index, product) {
        console.log("Processing product:", product);

        console.log("Product name:", product.productName);
        console.log("Product category:", product.category);
        // console.log("Product Category:", product.category);
        var productHtml = '<div class="product ' + product.category.name + '">' +
            '<h3>' + product.productName + '</h3>' +
            '<span>' + product.category.name + '</span>' +
            '<p class="product-price">' + product.price + '</p>' +
            '<a href="/shopping-cart/add/' + product.product_id + '" class="cart-btn"><i class="fas fa-shopping-cart"></i> Add to Cart</a>' +
            '</div>';
        productList.append(productHtml);
        productList.append(productHtml);
    });
}

function displayProducts(data) {
    var productList = $('#productList');
    productList.empty();

    $.each(data, function (index, product) {
        var productHtml =
            '<div class="col-lg-4 col-md-6 text-center">' +
            '<div class="single-product-item">' +
            '<div class="product-image">' +
            '<a href="single-product.html"><img src="' + product.image + '" alt=""></a>' +
            '</div>' +
            '<h3>' + product.productName + '</h3>' +
            '<span>' + product.category.name + '</span>' +
            '<p class="product-price">' + product.price + '</p>' +
            '<a href="/shopping-cart/add/' + product.product_id + '" class="cart-btn"><i class="fas fa-shopping-cart"></i> Add to Cart</a>' +
            '</div>' +
            '</div>';

        productList.append(productHtml);
    });
}