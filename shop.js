document.addEventListener('DOMContentLoaded', function () {
    viewProduct();
});

document.addEventListener('DOMContentLoaded', function () {
    // Trích xuất dữ liệu từ URL
    var searchResults = window.location.search.replace("&searchResults=", "");
    searchResults = searchResults ? JSON.parse(decodeURIComponent(searchResults)) : null;
    // Kiểm tra xem có dữ liệu tìm kiếm hay không và thực hiện các hành động cần thiết.
    if (searchResults) {
        // Hiển thị dữ liệu tìm kiếm trong trang shop
        viewProduct(searchResults);
    } else {
        viewProduct();
    }
});

function viewProduct(data){
    $.ajax({
        type: "GET",
        url: "http://localhost:8080/api/clients",
        success: function (data) {
            var productList = $('#productList');
            productList.empty();
            $.each(data, function (index, product) {
                var productHtml =
                    '<div class="col-lg-4 col-md-6 text-center">' +
                    '<div class="single-product-item">' +
                    '<div class="product-image">' +
                    // '<a href="/api/clients/detail/' + product.product_id + '"><img src="' + product.image + '" alt=""></a>' +
                    '<a href="single-product.html"><img src="' + product.image + '" alt=""></a>' +
                    '</div>' +
                    '<h3>' + product.productName + '</h3>' +
                    '<span>' + product.category.name + '</span>' +
                    '<p class="product-price">' + product.price + '</p>' +
                    '<a href="/shopping-cart/add/' + product.product_id + '" class="cart-btn"><i class="fas fa-shopping-cart"></i> Add to Cart</a>' +
                    '</div>' +
                    '</div>';

                productList.append(productHtml);
            })
        }
    })
}

function searchProducts() {
    var keyword = $('#searchKeyword').val();

    $.ajax({
        type: "GET",
        url: "http://localhost:8080/api/clients/search",
        data: { keyword: keyword },
        success: function (data) {
            // Gọi hàm để cập nhật dữ liệu trên trang shop
            window.location.href = "shop.html&searchResults=" + encodeURIComponent(JSON.stringify(data));
            // viewProduct(data);
        }
    });
}




