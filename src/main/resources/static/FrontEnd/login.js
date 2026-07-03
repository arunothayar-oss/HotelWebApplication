window.onload = function () 
{

    const params =
        new URLSearchParams(
            window.location.search);

    if (params.has("error")) {

        alert("Invalid Username or Password");

        document.getElementById("password").value = "";
    }
};