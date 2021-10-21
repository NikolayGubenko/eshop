$(document).ready(function () {

    $("#allOrders").jqGrid({
        url: 'http://localhost:8080/api/v1/admin/orders',
        mtype: 'GET',
        datatype: 'json',
        jsonReader: {
            root: "content",
            page: "page",
            total: "total",
            records: "records",
            repeatitems: false,
            id: "id",
        },

        colNames: ['id', 'Date', 'Status', 'Active'],
        colModel: [
            {name: 'id', index: 'id', width: 20},
            {name: 'orderDate', index: 'orderDate', width: 200},
            {name: 'orderStatus', index: 'orderStatus', width: 175},
            {name: 'active', index: 'active', width: 100},
        ],
        rowNum: 5,
        loadonce: false,
        rowList: [5, 10],
        pager: '#ordersPager',
        gridview: true,
        viewRecords: true,
        caption: "Orders"
    })
    ;

});

function getProductsFromOrder(id) {
    $("#orderProducts").jqGrid({
        url: 'http://localhost:8080/api/v1/order-products/' + id,
        type: 'GET',
        datatype: "json",

        jsonReader: {
            repeatitems: false,
            root: function (obj) {
                return obj;
            },
            page: function () {
                return 1;
            },
            total: function () {
                return 1;
            },
            records: function (obj) {
                return obj.length;
            },
        },
        colNames: ['Product id', 'Product name', 'Product price', 'Product type', "Quantity"],
        colModel: [
            {name: 'product.id', index: 'product.id', width: 50},
            {name: 'product.name', index: 'name', width: 100},
            {name: 'product.price', index: 'price', width: 100},
            {name: 'product.productType', index: 'productType', width: 50},
            {name: 'quantity', index: 'quantity', width: 100},
        ],

        pager: "#orderdetailsPager",
        loadonce: false,
        rowList: [5, 10],
        viewrecords: true,
        sortorder: "desc",
        caption: "Order details",
    });

}

function getOrderStats() {

    let rowId = $("#allOrders").jqGrid('getGridParam', 'selrow');
    let rowData = jQuery("#allOrders").getRowData(rowId);

    if (rowId != null) {
        jQuery("#orderProducts").jqGrid('setGridParam', {url: "http://localhost:8080/api/v1/order-products/" + rowId}).trigger("reloadGrid");
        document.getElementById("editOrderPanel").hidden = false;

        document.getElementById("orderId").textContent = rowId;
        document.getElementById("orderStatus").value = rowData['orderStatus'];
        document.getElementById("orderDate").textContent = rowData['orderDate'];
        document.getElementById("orderActive").value = rowData['active'];

        document.getElementById("editOrderPanel").hidden = false;

    } else {
        alert("Select an order!")
    }
}

function updateOrderStats() {
    let orderId = document.getElementById("orderId").textContent;
    let jsonData = ({
        id: orderId,
        orderDate: document.getElementById("orderDate").textContent,
        orderStatus: document.getElementById("orderStatus").value,
        active: document.getElementById("orderActive").value
    })

    $.ajax({
        type: "PUT",
        url: "http://localhost:8080/api/v1/admin/orders/" + orderId,
        contentType: "application/json",
        data: JSON.stringify(jsonData)
    });

}