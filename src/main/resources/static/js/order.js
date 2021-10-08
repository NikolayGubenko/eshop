function deleteProductFromOrder(id) {

    $.ajax({
        url: "http://localhost:8080/api/v1/order-products/" + id,
        type: "DELETE",
        datatype: "json",
        success: function () {
            alert("Product deleted from order");
            $("#orderDetails").trigger("reloadGrid", [{current: true}]);
        }
    });
}

function addOrderProduct(){

    var dataIDs = $("#orderDetails").getDataIDs();
    for(i = 0; i < dataIDs.length; i++)
    {
        var rowData = $("#orderDetails").jqGrid ('getRowData', dataIDs[i]);

        alert(JSON.stringify(rowData));
    }

}

function deleteOrder() {

}

function createOrder() {

}

function addProduct(){
    let select = document.getElementById("productList");
    let quantity = parseInt(document.getElementById("count").value);
    let option = select.options[select.selectedIndex];
    let grid = $("#orders");
    let orderId = grid.jqGrid('getGridParam', "selrow");
    addOrderProduct();


}

function deleteButtonEvent()
{
    let $grid = $("#orderDetails");
    let productId = $grid.jqGrid("getGridParam", "selrow");

    deleteProductFromOrder(productId);
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
