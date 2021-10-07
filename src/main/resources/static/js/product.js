$(document).ready(function () {
    $.ajax({
        type: "GET",
        url: 'http://localhost:8080/api/v1/products',
        datatype: "json",
        success: function (jsonObj) {
            $(jsonObj).each(function()
            {
                let option = $('<option />');
                option.attr('value', this.id).text(this.name+this.price+this.productType);

                $('#productList').append(option);
            });
        }
    });

});