function viewProductDetail(productId) {
    $.ajax({
        type: "GET",
        url: "http://localhost:8080/api/clients/detail/" + productId,
        success: function (product) {
            // Cập nhật thông tin sản phẩm trên trang
            $('#productImage').attr('src', product.image);
            $('#productName').text(product.productName);
            $('#productPrice').text(product.price);
            $('#productDescription').text(product.description);
            $('#productCategory').text(product.category.name);

            // Cập nhật link thêm vào giỏ hàng
            var addToCartLink = "/shopping-cart/add/" + product.product_id;
            $('#addToCartBtn').attr('href', addToCartLink);

            // Cập nhật các liên kết chia sẻ (cần cài đặt đúng các URL chia sẻ)
            var facebookShareLink = "https://www.facebook.com/sharer.php?u=" + window.location.href;
            var twitterShareLink = "https://twitter.com/intent/tweet?url=" + window.location.href;
            var googlePlusShareLink = "https://plus.google.com/share?url=" + window.location.href;
            var linkedinShareLink = "https://www.linkedin.com/shareArticle?url=" + window.location.href;

            $('#facebookShare').attr('href', facebookShareLink);
            $('#twitterShare').attr('href', twitterShareLink);
            $('#googlePlusShare').attr('href', googlePlusShareLink);
            $('#linkedinShare').attr('href', linkedinShareLink);
        }
    });
}

$(document).ready(function () {
    // Đặt ID của sản phẩm bạn muốn hiển thị chi tiết
    var productId = 30; // Thay thế bằng ID thực tế của sản phẩm
    viewProductDetail(productId);
});
