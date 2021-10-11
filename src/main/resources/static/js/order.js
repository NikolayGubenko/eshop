function deleteProductFromOrder() {

    let gr = $("#orderDetails").jqGrid('getGridParam', 'selrow');
    $("#orderDetails").jqGrid('delGridRow', gr, {reloadAfterSubmit: false});

}

function deleteOrder() {

}

function createOrder() {

}

function addProduct() {
    let select = document.getElementById("productList");
    let quantity = parseInt(document.getElementById("count").value);
    let productId = select.options[select.selectedIndex].value;
    let grid = $("#orderDetails");
    $.ajax({
        type: "GET",
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
    });
}

function updateOrder() {
    let grid = $("#orders");
    let orderId = grid.jqGrid('getGridParam', "selrow");
    let rows = jQuery("#orderDetails").jqGrid('getRowData');
    let row = [];

    for (let i = 0; i < rows.length; i++) {
        row[i] = ({
            product: {
                id: rows[i]['product.id'],
                name: rows[i]['product.name'],
                productType: rows[i]['product.productType'],
                price: rows[i]['product.price']
            }, quantity: rows[i]['quantity']
        })
    }

    let jsonData = ({
        id: orderId, orderProducts: row, orderDate: "2021-10-01 12:02",
        description: "Order description",
        orderStatus: "NEW",
        postalOffice: {
            id: 1,
            name: "Office N1",
            address: "Some street name 1"
        }
    })
    alert(JSON.stringify(jsonData));

    $.ajax({
        type: "PUT",
        url: "http://localhost:8080/api/v1/orders/"+orderId,
        contentType: "application/json",
        data: JSON.stringify(jsonData)
    });

}

function hideDetailsPanel() {
    document.getElementById("orderProductsPanel").hidden = true;
}

function getOrderToEdit() {
    let grid = $("#orders");
    let orderId = grid.jqGrid('getGridParam', "selrow");

    if (orderId) {
        getOrderDetails(orderId);
        document.getElementById("orderProductsPanel").hidden = false;
    } else
        alert("Please, select an order");
}
