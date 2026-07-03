document.getElementById("forgotForm")
.addEventListener("submit", function (event)
{
    event.preventDefault();
      

    const username =
        document.getElementById("username").value.trim();

    const role =
        document.getElementById("role").value;

    const newPassword =
        document.getElementById("newPassword").value;

    const confirmPassword =
        document.getElementById("confirmPassword").value;

    const message =
        document.getElementById("message");

    if(username === "" ||
       role === "" ||
       newPassword === "" ||
       confirmPassword === "")
    {
        message.innerHTML = "Please fill all fields.";
        message.style.color = "red";
        return;
    }

    if(newPassword !== confirmPassword)
    {
        message.innerHTML = "Passwords do not match.";
        message.style.color = "red";
        return;
    }

    const forgotData =
    {
        username: username,
        role: role,
        newPassword: newPassword
    };

    fetch("/forgotpassword",
    {
        method: "POST",

        headers:
        {
            "Content-Type": "application/json"
        },

        body: JSON.stringify(forgotData)
    })

    .then(response => response.text())

    .then(data =>
    {
         alert(data);
        message.innerHTML = data;
        message.style.color = "green";

        document.getElementById("forgotForm").reset();

        //setTimeout(() =>
       // {
       //     window.location.href = "login.html";
        //}, 3000);
    })

    .catch(error =>
    {
        message.innerHTML =
            "Error resetting password.";

        message.style.color = "red";
    });
});