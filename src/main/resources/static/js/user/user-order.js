$(document).ready(function () {
    $("#orders").jqGrid({
        url: 'http://localhost:8080/api/v1/users/orders',
        type: 'GET',
        datatype: "json",
        jsonReader: {
            root: "content",
            page: "page",
            total: "total",
            records: "records",
            repeatitems: false,
            id: "id",
        },

        colNames: ['id', 'Date', 'Status', 'Description', "Postal id", "Postal office"],
        colModel: [
            {name: 'id', index: 'id', width: 40},
            {name: 'orderDate', index: 'orderDate', width: 200},
            {name: 'orderStatus', index: 'orderStatus', width: 175},
            {name: 'description', index: 'description', width: 100},
            {name: 'postalOffice.id', index: 'postalOffice.id', width: 100},
            {name: 'postalOffice.name', index: 'postalOffice.name', width: 200},
        ],
        rowNum: 10,
        loadonce: false,
        rowList: [10, 20],
        pager: '#pager2',
        gridview: true,
        viewRecords: true,
        caption: "Orders"
    })
    ;

});

function getOrderDetails(id) {
    $("#orderDetails").jqGrid({
        url: 'http://localhost:8080/api/v1/order-products/' + id,
        type: 'GET',
        datatype: "json",
        jsonReader: {
            repeatitems: false,
            root: function (obj) {
                return obj;
            },
            page: function (obj) {
                return 1;
            },
            total: function (obj) {
                return 1;
            },
            records: function (obj) {
                return obj.length;
            },
        },
        colNames: ['OrderProduct id', 'Product id', 'Product name', 'Product price', 'Product type', "Quantity"],
        colModel: [
            {name: 'id', index: 'id', width: 100},
            {name: 'product.id', index: 'product.id', width: 100},
            {name: 'product.name', index: 'name', width: 200},
            {name: 'product.price', index: 'price', width: 175},
            {name: 'product.productType', index: 'productType', width: 100},
            {name: 'quantity', index: 'quantity', width: 300},
        ],

        loadonce: true,
        rowList: [10, 20],
        pager: '#footer',
        sortname: 'name',
        viewrecords: true,
        sortorder: "desc",
        caption: "Order details",
    });
    jQuery("#orderDetails").jqGrid('setGridParam', {url: 'http://localhost:8080/api/v1/order-products/' + id}).trigger("reloadGrid");
    document.getElementById("order-id").textContent = id;
    let rowId = $("#orders").jqGrid('getGridParam', 'selrow');
    let rowData = jQuery("#orders").getRowData(rowId);
    getPostalDetails(rowData['postalOffice.id']);
    document.getElementById("order-description").value = rowData['description'];

}

function deleteProductFromOrder() {

    let gr = $("#orderDetails").jqGrid('getGridParam', 'selrow');
    $("#orderDetails").jqGrid('delGridRow', gr, {reloadAfterSubmit: true});

}

function createOrder() {
    $("#orderDetails").jqGrid({
        url: '',
        type: 'GET',
        datatype: "json",
        jsonReader: {
            repeatitems: false,
            root: function (obj) {
                return obj;
            },
            page: function (obj) {
                return 1;
            },
            total: function (obj) {
                return 1;
            },
            records: function (obj) {
                return obj.length;
            },
        },
        colNames: ['Product id', 'Product name', 'Product price', 'Product type', "Quantity"],
        colModel: [
            {name: 'product.id', index: 'product.id', width: 200},
            {name: 'product.name', index: 'name', width: 200},
            {name: 'product.price', index: 'price', width: 175},
            {name: 'product.productType', index: 'productType', width: 100},
            {name: 'quantity', index: 'quantity', width: 100},
        ],
        rowList: [5, 10],
        pager: '#prodFooter',
        sortname: 'name',
        viewrecords: true,
        sortorder: "desc",
        caption: "Order details",
    });
    jQuery("#orderDetails").jqGrid('clearGridData');

    document.getElementById("orderProductsPanel").hidden = false;
    document.getElementById("order-id").textContent = "";
}


function updateOrder() {
    let orderId = document.getElementById("order-id").textContent;
    let rows = jQuery("#orderDetails").jqGrid('getRowData');
    let row = [];

    for (let i = 0; i < rows.length; i++) {
        row[i] = ({
            id: rows[i]['id'],
            product: {
                id: rows[i]['product.id'],
                name: rows[i]['product.name'],
                productType: rows[i]['product.productType'],
                price: rows[i]['product.price']
            }, quantity: rows[i]['quantity']
        })
    }

    let jsonData = ({
        id: orderId,
        orderProducts: row, orderDate: "2021-10-01 12:02",
        description: "111",
        orderStatus: "NEW",
        postalOffice: {
            id: 1,
            name: "Office N1",
            address: "Some street name 1"
        }
    })
    alert(JSON.stringify(jsonData));

    if (orderId !== "") {

        $.ajax({
            type: "PUT",
            url: "http://localhost:8080/api/v1/users/orders/" + orderId,
            contentType: "application/json",
            data: JSON.stringify(jsonData),
        });
    } else {
        $.ajax({
            type: "POST",
            url: "http://localhost:8080/api/v1/users/orders",
            contentType: "application/json",
            data: JSON.stringify(jsonData),
        });
    }

}

function hideDetailsPanel() {
    document.getElementById("orderProductsPanel").hidden = true;
    document.getElementById("productsPanel").hidden = true;

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

function deleteOrder() {
    let rowId = $("#orders").jqGrid('getGridParam', 'selrow');
    $.ajax({
        type: "DELETE",
        url: "http://localhost:8080/api/v1/users/orders/" + rowId,
        success: function () {
            jQuery("#orders").jqGrid('setGridParam', {url: "http://localhost:8080/api/v1/users/orders/"}).trigger("reloadGrid");
        }
    });
}