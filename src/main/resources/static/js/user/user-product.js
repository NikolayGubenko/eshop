$(document).ready(function () {
    $("#allProducts").jqGrid({
        url: 'http://localhost:8080/api/v1/products',
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
        pager: '#productPager',
        pageable: true,
        serverPaging: true,
        pginput: true,
        viewRecords: true,
        caption: "Products"
    })
    ;
});

function showProducts() {
    document.getElementById("productsPanel").hidden = false;
    jQuery("#allPostal").jqGrid('setGridParam', {url: "http://localhost:8080/api/v1/products/"}).trigger("reloadGrid");
}

/*function addProduct() {
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
}*/



