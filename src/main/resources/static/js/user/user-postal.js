$(document).ready(function () {

    $("#allPostal").jqGrid({
        url: 'http://localhost:8080/api/v1/users/postal-offices',
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
        pager: '#postalFooter',
        loadOnce: false,
        pageable: true,
        serverPaging: true,
        rowList: [10, 20],
        viewRecords: true,
        caption: "Postal offices"
    })
    ;
});

function getPostalDetails(postalId) {
    $.ajax({
        type: "GET",
        url: "http://localhost:8080/api/v1/users/postal-offices/" + postalId,
        contentType: "application/json",
        dataType: 'json',
        success: function (obj) {
            document.getElementById("postal-id").textContent = obj.id;
            document.getElementById("postal-name").textContent = obj.name;
            document.getElementById("postal-address").textContent = obj.address;
        }
    })
}

function showPostal() {
    document.getElementById("postalPanel").hidden = false;
    jQuery("#allPostal").jqGrid('setGridParam', {url: "http://localhost:8080/api/v1/users/postal-offices/"}).trigger("reloadGrid");
}