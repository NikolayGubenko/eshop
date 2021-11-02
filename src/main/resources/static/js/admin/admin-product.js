$(document).ready(function () {

    $("#allProducts").jqGrid({
        url: 'http://localhost:8080/api/v1/admin/products',
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

function showProductErrors(errorObj) {
    let obj = JSON.parse(errorObj.responseText);

    $.each(obj.details, function (key, value) {
        $('[data-toggle="popover"]' + 'input[id=product-' + key + ']').popover({
            html: true,
            placement: "bottom",
            content: value
        })
        $('input[id=product-' + key + ']').popover('show');
    })
}

function addProduct() {
    document.getElementById("editProductPanel").hidden = false;

    document.getElementById("product-id").textContent = "";
    document.getElementById("product-name").value = "";
    document.getElementById("product-price").value = 0;
    document.getElementById("product-productType").value = "CPU";
}

function editProduct() {
    let rowId = $("#allProducts").jqGrid('getGridParam', 'selrow');
    let rowData = jQuery("#allProducts").getRowData(rowId);

    if (rowId != null) {
        document.getElementById("editProductPanel").hidden = false;

        document.getElementById("product-id").textContent = rowId;
        document.getElementById("product-name").value = rowData['name'];
        document.getElementById("product-price").value = rowData['price'];
        document.getElementById("product-productType").value = rowData['productType'];
    } else {
        alert("Select product!")
    }
}

function saveProduct() {
    let productId = document.getElementById("product-id").textContent;
    let jsonData = ({
        id: productId,
        name: document.getElementById("product-name").value,
        price: document.getElementById("product-price").value,
        productType: document.getElementById("product-productType").value
    })

    if (productId) {
        $.ajax({
            type: "PUT",
            url: "http://localhost:8080/api/v1/admin/products/" + productId,
            contentType: "application/json",
            dataType: 'json',
            data: JSON.stringify(jsonData),
            success: function () {
                document.getElementById("editProductPanel").hidden = true;
                $("[data-toggle='popover']").popover('destroy');
            },
            error: function (errorObj) {
                showProductErrors(errorObj);
            },
        });
    } else {
        $.ajax({
            type: "POST",
            url: "http://localhost:8080/api/v1/admin/products/",
            contentType: "application/json",
            data: JSON.stringify(jsonData),
            success: function () {
                document.getElementById("editProductPanel").hidden = true;
                $("[data-toggle='popover']").popover('destroy');
            },
            error: function (errorObj) {
                showProductErrors(errorObj);
            },
        });
    }
}

function deleteProduct() {
    let rowId = $("#allProducts").jqGrid('getGridParam', 'selrow');
    $.ajax({
        type: "DELETE",
        url: "http://localhost:8080/api/v1/admin/products/" + rowId,
    });

}