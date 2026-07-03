document.getElementById("registerForm")
.addEventListener("submit", function(e){

    e.preventDefault();

    const formData = {
        fullName: document.querySelector("[name='fullName']").value,
        username: document.querySelector("[name='username']").value,
        email: document.querySelector("[name='email']").value,
        phoneNumber: document.querySelector("[name='phoneNumber']").value,
        password: document.querySelector("[name='password']").value,
        confirmPassword: document.querySelector("[name='confirmPassword']").value,
        role: document.querySelector("[name='role']").value
    };

    fetch("/api/register", {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(formData)
    })
    .then(response => response.text())
    .then(data => {
        alert(data);
        window.location.href = "login.html";
    })
    .catch(error => {
        alert("Registration Failed");
        console.log(error);
    });

});