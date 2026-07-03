document.addEventListener("DOMContentLoaded", function () {

    fetch("/api/dashboard")
    .then(response => response.json())
    .then(data => {

        document.getElementById("totalOrders").textContent =
            data.totalOrders;

        document.getElementById("totalSales").textContent =
            "₹" + data.totalSales;

        document.getElementById("feedbackCount").textContent =
            data.feedbackCount;
    })
    .catch(error => {
        console.error("Dashboard Error:", error);
        alert("Unable to load dashboard data.");
    });

});


document.getElementById("orderBtn")
.addEventListener("click", function () {
    window.location.href = "orderEntry.html";
});


document.getElementById("billingBtn")
.addEventListener("click", function () {
    window.location.href = "billing.html";
});


document.getElementById("logoutBtn")
.addEventListener("click", function () {

    if(confirm("Are you sure you want to logout?")) {
        window.location.href = "login.html";
    }

});