$(document).ready(function () {
    $("#allProducts").jqGrid({
        url: 'http://localhost:8080/api/v1/admin/products',
        datatype: "json",
        type: 'GET',
        jsonReader: {

            root: "content",
            page: "page",
            total: function(result) {
                //Total number of pages
                return Math.ceil(result.total / result.records)+1;
            },
            //total: "total",
            records: "records",
            repeatitems: false,
            cell: "cell",
            id: "id",
        },
        colNames: ['id', 'Product name', 'Price', 'Product type'],
        colModel: [
            {name: 'id', index: 'id', width: 20},
            {name: 'name', index: 'name', width: 200},
            {name: 'price', index: 'price', width: 175},
            {name: 'productType', index: 'productType', width: 100},
        ],

        rowNum:5,
        loadonce: false,
       // rowList: [5, 10],
        pager: '#mypager',
        pageable: true,
        serverPaging: true,
        pginput: true,
        viewRecords: true,
        caption: "Products"

    })
    ;
    $("#allOrders").jqGrid({
        page:1,
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


    $("#allPostal").jqGrid({
        url: 'http://localhost:8080/api/v1/admin/postal-offices',
        type: 'GET',
        datatype: "json",
        gridview: true,
        page: 0,
        jsonReader: {
            repeatitems: false,
            id: "id",
            root: "content",
            page: "page",
            total: "total",
            records: "records",
        },

        colNames: ['id', 'Postal office name', 'Address'],
        colModel: [
            {name: 'id', index: 'id', width: 20},
            {name: 'name', index: 'name', width: 200},
            {name: 'address', index: 'address', width: 175},
        ],
        pageSize: 20,
        loadOnce: false,
        pageable: true,
        serverPaging: true,
        rowList: [5, 10],
        viewRecords: true,
        caption: "Postal offices"
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

        pager:"#orderdetailsPager",
        loadonce: false,
        rowList: [5, 10],
        sortname: 'name',
        viewrecords: true,
        sortorder: "desc",
        caption: "Order details",
    });

}

function getOrderStats() {

    let rowId = $("#allOrders").jqGrid('getGridParam', 'selrow');
    let rowData = jQuery("#allOrders").getRowData(rowId);

    if (rowData) {
        $('#orderProducts').jqGrid('clearGridData');
        $('#orderProducts').trigger('reloadGrid');
        getProductsFromOrder(rowId);
        document.getElementById("editOrderPanel").hidden = false;
        document.getElementById("orderId").textContent = rowId;
        document.getElementById("orderStatus").value = rowData['orderStatus'];
        document.getElementById("orderDate").textContent = rowData['orderDate'];
        document.getElementById("orderActive").value = rowData['active'];

        document.getElementById("editOrderPanel").hidden = false;

    } else {
        alert("Please, select an order");
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

function addProduct() {
    document.getElementById("editProductPanel").hidden = false;

    document.getElementById("productId").textContent = "";
    document.getElementById("productName").value = "Type product name";
    document.getElementById("productPrice").value = 0;
    document.getElementById("productType").value = "CPU";
}

function editProduct() {
    let rowId = $("#allProducts").jqGrid('getGridParam', 'selrow');
    let rowData = jQuery("#allProducts").getRowData(rowId);

    document.getElementById("editProductPanel").hidden = false;

    if (rowData) {
        document.getElementById("productId").textContent = rowId;
        document.getElementById("productName").value = rowData['name'];
        document.getElementById("productPrice").value = rowData['price'];
        document.getElementById("productType").value = rowData['productType'];
    }
}

function saveProduct() {
    let productId = document.getElementById("productId").textContent;
    let jsonData = ({
        id: productId,
        name: document.getElementById("productName").value,
        price: document.getElementById("productPrice").value,
        productType: document.getElementById("productType").value
    })

    if (productId) {
        $.ajax({
            type: "PUT",
            url: "http://localhost:8080/api/v1/admin/products/" + productId,
            contentType: "application/json",
            data: JSON.stringify(jsonData)
        });
    } else {
        $.ajax({
            type: "POST",
            url: "http://localhost:8080/api/v1/admin/products/",
            contentType: "application/json",
            data: JSON.stringify(jsonData)
        });
    }
    document.getElementById("editProductPanel").hidden = true;
}

function deleteProduct() {
    let rowId = $("#allProducts").jqGrid('getGridParam', 'selrow');
    $.ajax({
        type: "DELETE",
        url: "http://localhost:8080/api/v1/admin/products/" + rowId,

    });

}

function editPostal() {
    let rowId = $("#allPostal").jqGrid('getGridParam', 'selrow');
    let rowData = jQuery("#allPostal").getRowData(rowId);

    document.getElementById("editPostalPanel").hidden = false;

    if (rowData) {
        document.getElementById("postalId").textContent = rowId;
        document.getElementById("postalName").value = rowData['name'];
        document.getElementById("postalAddress").value = rowData['address'];
    }
}

function addPostal() {
    document.getElementById("editPostalPanel").hidden = false;
    document.getElementById("postalId").textContent = "";
    document.getElementById("postalName").value = "Type postal office name";
    document.getElementById("postalAddress").value = "Type postal office address";
}

function savePostal() {
    let postalId = document.getElementById("postalId").textContent;
    let jsonData = ({
        id: postalId,
        name: document.getElementById("postalName").value,
        address: document.getElementById("postalAddress").value,
    })

    if (postalId) {
        $.ajax({
            type: "PUT",
            url: "http://localhost:8080/api/v1/admin/postal-offices/" + postalId,
            contentType: "application/json",
            data: JSON.stringify(jsonData)
        });
    } else {
        $.ajax({
            type: "POST",
            url: "http://localhost:8080/api/v1/admin/postal-offices/",
            contentType: "application/json",
            data: JSON.stringify(jsonData)
        });
    }
    document.getElementById("editPostalPanel").hidden = true;
}

function deletePostal() {
    let rowId = $("#allPostal").jqGrid('getGridParam', 'selrow');
    $.ajax({
        type: "DELETE",
        url: "http://localhost:8080/api/v1/admin/postal-offices/" + rowId,

    });

}

function openTab(evt, tabName) {

    let i, tabcontent, tablinks;

    tabcontent = document.getElementsByClassName("tabcontent");
    for (i = 0; i < tabcontent.length; i++) {
        tabcontent[i].style.display = "none";
    }

    tablinks = document.getElementsByClassName("tablinks");
    for (i = 0; i < tablinks.length; i++) {
        tablinks[i].className = tablinks[i].className.replace(" active", "");
    }

    document.getElementById(tabName).style.display = "block";
    evt.currentTarget.className += " active";
}