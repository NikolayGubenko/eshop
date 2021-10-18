$(document).ready(function () {
    $("#orders").jqGrid({
        url: 'http://localhost:8080/api/v1/orders',
        type: 'GET',
        datatype: "json",
        gridview: true,
        page:0,
        jsonReader: {

            repeatitems: false,
            root: function (obj) {
                return obj;
            },
            page: function (obj) {
                return $("#orders").jqGrid('getGridParam', 'page');
            },
            total: function (obj) {
                return Math.ceil(obj.length / $("#orders").jqGrid('getGridParam', 'rowNum'));
            },
            records: function (obj) {
                return obj.length;
            },
        },

        colNames: ['id', 'Date', 'Status', 'Description', "Postal office"],
        colModel: [
            {name: 'id', index: 'id', width: 20},
            {name: 'orderDate', index: 'orderDate', width: 200},
            {name: 'orderStatus', index: 'orderStatus', width: 175},
            {name: 'description', index: 'description', width: 100},
            {name: 'postalOffice.name', index: 'postalOffice.name', width: 300},
        ],
        loadonce: true,
        rowList: [5, 10],
        pager: '#pager2',
        sortname: 'name',
        viewrecords: true,
        sortorder: "desc",
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
        colNames: ['Product id','Product name', 'Product price', 'Product type', "Quantity"],
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

}