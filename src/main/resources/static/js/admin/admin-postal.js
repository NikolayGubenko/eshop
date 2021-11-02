$(document).ready(function () {

    $("#allPostal").jqGrid({
        url: 'http://localhost:8080/api/v1/admin/postal-offices',
        type: 'GET',
        datatype: "json",
        gridview: true,
        jsonReader: {
            root: "content",
            page: "page",
            total: "total",
            records: "records",
            repeatitems: false,
            id: "id",
        },

        colNames: ['id', 'Postal office name', 'Address'],
        colModel: [
            {name: 'id', index: 'id', width: 20},
            {name: 'name', index: 'name', width: 200},
            {name: 'address', index: 'address', width: 175},
        ],
        rowNum: 10,
        loadOnce: false,
        rowList: [10, 20],
        pager: '#postalPager',
        pageable: true,
        serverPaging: true,
        viewRecords: true,
        caption: "Postal offices"
    })
    ;
});

function showPostalErrors(errorObj) {
    let obj = JSON.parse(errorObj.responseText);

    $.each(obj.details, function (key, value) {
        $('[data-toggle="popover"]' + 'input[id=postal-' + key + ']').popover({
            html: true,
            placement: "bottom",
            content: value
        })
        $('input[id=postal-' + key + ']').popover('show');
    })
}

function editPostal() {
    let rowId = $("#allPostal").jqGrid('getGridParam', 'selrow');
    let rowData = jQuery("#allPostal").getRowData(rowId);

    if (rowId != null) {
        document.getElementById("editPostalPanel").hidden = false;

        document.getElementById("postal-id").textContent = rowId;
        document.getElementById("postal-name").value = rowData['name'];
        document.getElementById("postal-address").value = rowData['address'];
    } else {
        alert("Select postal office!");
    }
}

function addPostal() {
    document.getElementById("editPostalPanel").hidden = false;

    document.getElementById("postal-id").textContent = "";
    document.getElementById("postal-name").value = "";
    document.getElementById("postal-address").value = "";
}

function savePostal() {
    let postalId = document.getElementById("postal-id").textContent;
    let jsonData = ({
        id: postalId,
        name: document.getElementById("postal-name").value,
        address: document.getElementById("postal-address").value,
    })

    if (postalId) {
        $.ajax({
            type: "PUT",
            url: "http://localhost:8080/api/v1/admin/postal-offices/" + postalId,
            contentType: "application/json",
            data: JSON.stringify(jsonData),
            success: function () {
                document.getElementById("editPostalPanel").hidden = true;
                $("[data-toggle='popover']").popover('destroy');
                jQuery("#allPostal").jqGrid('setGridParam', {url: "http://localhost:8080/api/v1/admin/postal-offices/"}).trigger("reloadGrid");
            },
            error: function (errorObj) {
                showPostalErrors(errorObj);
            },
        });
    } else {
        $.ajax({
            type: "POST",
            url: "http://localhost:8080/api/v1/admin/postal-offices/",
            contentType: "application/json",
            data: JSON.stringify(jsonData),
            success: function () {
                document.getElementById("editPostalPanel").hidden = true;
                $("[data-toggle='popover']").popover('destroy');
                jQuery("#allPostal").jqGrid('setGridParam', {url: "http://localhost:8080/api/v1/admin/postal-offices/"}).trigger("reloadGrid");
            },
            error: function (errorObj) {
                showPostalErrors(errorObj);
            },
        });
    }
}

function deletePostal() {
    let rowId = $("#allPostal").jqGrid('getGridParam', 'selrow');
    $.ajax({
        type: "DELETE",
        url: "http://localhost:8080/api/v1/admin/postal-offices/" + rowId,
        success: function () {
            jQuery("#allPostal").jqGrid('setGridParam', {url: "http://localhost:8080/api/v1/admin/postal-offices/"}).trigger("reloadGrid");
        }
    });
}
