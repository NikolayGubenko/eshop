function registerNewUser() {
    let jsonData = ({
        email: document.getElementById("inputEmail").value,
        firstName: document.getElementById("inputFirstName").value,
        lastName: document.getElementById("inputLastName").value,
        password: document.getElementById("inputPassword").value,
    });
    $.ajax({
        type: "POST",
        url: "http://localhost:8080/api/v1/users/register",
        contentType: "application/json",
        data: JSON.stringify(jsonData),
        success: function () {
            alert("Account has been registered. Check your email for verification link!");
            history.go(-1);
        },
        error: function (errorObj) {

        },
    });
}