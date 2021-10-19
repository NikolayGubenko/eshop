function registerUser() {
    let user = ({
        email: document.getElementById("inputEmail").textContent,
        firstName: document.getElementById("inputFirstName").textContent,
        lastName: document.getElementById("inputLastName").textContent,
        password: document.getElementById("inputPassword").textContent,
    });
    alert(JSON.stringify(user));
    /*let select = document.getElementById("productList");
    let quantity = parseInt(document.getElementById("count").value);
    let productId = select.options[select.selectedIndex].value;
    let grid = $("#orderDetails");
    $.ajax({
        type: "POST",
        url: 'http://localhost:8080/api/v1/products/' + productId,
        datatype: "json",
        success: function (jsonObj) {
            let addRow = {
                "product.id": jsonObj.id, "product.name": jsonObj.name,
                "product.price": jsonObj.price, "product.productType": jsonObj.productType,
                quantity: quantity
            };
            alert(JSON.stringify(addRow));
            grid.jqGrid('addRowData', "after", addRow);
        }
    });*/
}