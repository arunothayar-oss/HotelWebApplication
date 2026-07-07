document.getElementById("sendOtp")
.addEventListener("click", function ()
{

    const username =
        document.getElementById("username").value.trim();

    const role =
        document.getElementById("role").value;

    const message =
        document.getElementById("message");

    if(username === "" || role === "")
    {
        message.innerHTML = "Please enter Username and Role.";
        message.style.color = "red";
        return;
    }

    const otpData =
    {
        username: username,
        role: role
    };

    fetch("/sendotp",
    {
        method: "POST",

        headers:
        {
            "Content-Type": "application/json"
        },

        body: JSON.stringify(otpData)
    })

    .then(response => response.text())

    .then(data =>
    {
        alert(data);

        message.innerHTML = data;

        if(data.includes("OTP"))
        {
            message.style.color = "green";
        }
        else
        {
            message.style.color = "red";
        }

    })

    .catch(error =>
    {
        message.innerHTML = "Unable to send OTP.";
        message.style.color = "red";
    });

});



// ======================
// RESET PASSWORD
// ======================

document.getElementById("forgotForm")
.addEventListener("submit", function (event)
{

    event.preventDefault();

    const username =
        document.getElementById("username").value.trim();

    const role =
        document.getElementById("role").value;

    const otp =
        document.getElementById("otp").value.trim();

    const newPassword =
        document.getElementById("newPassword").value;

    const confirmPassword =
        document.getElementById("confirmPassword").value;

    const message =
        document.getElementById("message");



    if(username === "" ||
       role === "" ||
       otp === "" ||
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
        otp: otp,
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

   .then(response => {

    if (!response.ok) {
        throw new Error("Request Failed");
    }

    return response.text();
})

    .then(data =>
    {

        alert(data);

        message.innerHTML = data;
       
        if(data.includes("Successfully"))
    {
    message.innerHTML = data;
    message.style.color = "green";

    document.getElementById("forgotForm").reset();

    setTimeout(function ()
    {
        window.location.href = "login.html";
    }, 2000);
    }
        else
        {
            message.style.color = "red";
        }

    })

    .catch(error =>
    {

        message.innerHTML = "Error resetting password.";

        message.style.color = "red";

    });

});