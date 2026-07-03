// Generate Bill
document.getElementById("generateBtn").addEventListener("click", function () {

    let subtotal = parseFloat(document.getElementById("subTotal").value);

    if (isNaN(subtotal)) {
        alert("Enter subtotal amount.");
        return;
    }

    let gst = subtotal * 0.05;
    let grandTotal = subtotal + gst;

    document.getElementById("gst").value = gst.toFixed(2);
    document.getElementById("grandTotal").value = grandTotal.toFixed(2);

});


// Print / Save Bill
document.getElementById("printBtn").addEventListener("click", function () {

    const billData = {
        billNo: document.getElementById("billNo").value,
        tableNo: document.getElementById("tableNo").value,
        customerName: document.getElementById("customerName").value,
        billDate: document.getElementById("billDate").value,
        subtotal: parseFloat(document.getElementById("subTotal").value),
        gst: parseFloat(document.getElementById("gst").value),
        grandTotal: parseFloat(document.getElementById("grandTotal").value)
    };

    fetch("/api/billing/save", {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(billData)
    })
    .then(response => response.text())
    .then(data => {
        alert(data);
    })
    .catch(error => {
        console.error(error);
        alert("Failed to save bill.");
    });

});


// Clear Button
document.getElementById("clearBtn").addEventListener("click", function () {

    document.getElementById("billNo").value = "";
    document.getElementById("tableNo").value = "";
    document.getElementById("customerName").value = "";
    document.getElementById("billDate").value = "";
    document.getElementById("subTotal").value = "";
    document.getElementById("gst").value = "";
    document.getElementById("grandTotal").value = "";

});


// Dashboard Button
document.getElementById("dashboardBtn").addEventListener("click", function () {

    window.location.href = "DashBoard.html";

});