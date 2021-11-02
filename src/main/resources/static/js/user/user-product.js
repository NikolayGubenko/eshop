$(document).ready(function () {
    $("#allProducts").jqGrid({
        url: 'http://localhost:8080/api/v1/users/products',
        datatype: "json",
        mtype: 'GET',
        jsonReader: {
            root: "content",
            page: "page",
            total: "total",
            records: "records",
            repeatitems: false,
            id: "id",
        },
        colNames: ['id', 'Product name', 'Price', 'Product type'],
        colModel: [
            {name: 'id', index: 'id', width: 20},
            {name: 'name', index: 'name', width: 200},
            {name: 'price', index: 'price', width: 175},
            {name: 'productType', index: 'productType', width: 100},
        ],
        rowNum: 10,
        loadonce: false,
        rowList: [10, 20],
        pager: '#prodFooter',
        pageable: true,
        serverPaging: true,
        pginput: true,
        viewRecords: true,
        caption: "Products"
    })
    ;
});

function editProductInOrder() {

    let rowId = $("#orderDetails").jqGrid('getGridParam', 'selrow');
    let rowData = jQuery("#orderDetails").getRowData(rowId);

    if (rowId != null) {

        document.getElementById("order-product-id").textContent = rowData['id'];
        document.getElementById("product-id").textContent = rowData['product.id'];
        document.getElementById("product-name").textContent = rowData['product.name'];
        document.getElementById("product-price").textContent = rowData['product.price'];
        document.getElementById("product-type").textContent = rowData['product.productType'];
        document.getElementById("product-quantity").value = rowData['quantity'];

    } else {
        alert("Select product in order!")
    }
}

function showProducts() {
    document.getElementById("productsPanel").hidden = false;
    jQuery("#allProducts").jqGrid('setGridParam', {url: "http://localhost:8080/api/v1/users/products/"}).trigger("reloadGrid");
}

function addProduct() {
    showProducts();
    document.getElementById("order-product-id").textContent = 'null';
    document.getElementById("product-id").textContent = 'Select product';
    document.getElementById("product-name").textContent = 'none';
    document.getElementById("product-price").textContent = 'none';
    document.getElementById("product-type").textContent = 'none';
    document.getElementById("product-quantity").value = 0;
}

function selectProduct() {
    let rowId = $("#allProducts").jqGrid('getGridParam', 'selrow');
    let rowData = jQuery("#allProducts").getRowData(rowId);

    if (rowId != null) {

        document.getElementById("product-id").textContent = rowData['id'];
        document.getElementById("product-name").textContent = rowData['name'];
        document.getElementById("product-price").textContent = rowData['price'];
        document.getElementById("product-type").textContent = rowData['productType'];
        document.getElementById("productsPanel").hidden = true;

    } else {
        alert("Select product")
    }
}

function acceptProduct() {
    let prodId = document.getElementById("order-product-id").textContent;
    let grid = $("#orderDetails");
    let addRow = {
        "product.id": document.getElementById("product-id").textContent,
        "product.name": document.getElementById("product-name").textContent,
        "product.price": document.getElementById("product-price").textContent,
        "product.productType": document.getElementById("product-type").textContent,
        "quantity": document.getElementById("product-quantity").value
    };
    if (prodId === 'null') {
        grid.jqGrid('addRowData', "after", addRow);
    } else {
        grid.jqGrid('setRowData', prodId, addRow);
    }

}