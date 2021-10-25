$(document).ready(function () {
    $("#orders").jqGrid({
        url: 'http://localhost:8080/api/v1/orders',
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

        colNames: ['id', 'Date', 'Status', 'Description', "Postal office"],
        colModel: [
            {name: 'id', index: 'id', width: 20},
            {name: 'orderDate', index: 'orderDate', width: 200},
            {name: 'orderStatus', index: 'orderStatus', width: 175},
            {name: 'description', index: 'description', width: 100},
            {name: 'postalOffice.name', index: 'postalOffice.name', width: 300},
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
        colNames: ['Product id', 'Product name', 'Product price', 'Product type', "Quantity"],
        colModel: [
            {name: 'product.id', index: 'product.id', width: 200},
            {name: 'product.name', index: 'name', width: 200},
            {name: 'product.price', index: 'price', width: 175},
            {name: 'product.productType', index: 'productType', width: 100},
            {name: 'quantity', index: 'quantity', width: 300},
        ],

        loadonce: false,
        rowList: [5, 10],
        pager: '#footer',
        sortname: 'name',
        viewrecords: true,
        sortorder: "desc",
        caption: "Order details",
    });
    jQuery("#orderDetails").jqGrid('setGridParam', {url: 'http://localhost:8080/api/v1/order-products/' + id}).trigger("reloadGrid");
}

function deleteProductFromOrder() {

    let gr = $("#orderDetails").jqGrid('getGridParam', 'selrow');
    $("#orderDetails").jqGrid('delGridRow', gr, {reloadAfterSubmit: true});

}

function deleteOrder() {

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
            {name: 'quantity', index: 'quantity', width: 300},
        ],
        rowList: [5, 10],
        pager: '#footer',
        sortname: 'name',
        viewrecords: true,
        sortorder: "desc",
        caption: "Order details",
    });
    jQuery("#orderDetails").jqGrid('clearGridData');

    document.getElementById("orderProductsPanel").hidden = false;
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
        url: "http://localhost:8080/api/v1/orders/" + orderId,
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
