let items = [];
let grandTotal = 0;

// Food item price selection
document.getElementById("foodItem").addEventListener("change", function () {

    let item = this.value;
    let price = 0;

    if (item === "BURGER") {
        price = 150;
    }
    else if (item === "PIZZA") {
        price = 250;
    }
    else if (item === "DOSA") {
        price = 80;
    }
    else if (item === "FRIED RICE") {
        price = 180;
    }
    else if (item === "COFFEE") {
        price = 50;
    }

    document.getElementById("price").value = price;
});


// Calculate total
document.getElementById("quantity").addEventListener("input", function () {

    let quantity = parseInt(this.value) || 0;

    let price =
        parseFloat(document.getElementById("price").value) || 0;

    document.getElementById("total").value =
        quantity * price;
});


// Add item button
document.getElementById("addBtn")
.addEventListener("click", function () {

    let itemName =
        document.getElementById("foodItem").value;

    let quantity =
        parseInt(document.getElementById("quantity").value);

    let price =
        parseFloat(document.getElementById("price").value);

    let total =
        parseFloat(document.getElementById("total").value);

    if (itemName === "") {
        alert("Please select food item.");
        return;
    }

    if (!quantity || quantity <= 0) {
        alert("Enter quantity.");
        return;
    }

    let item = {

        itemName: itemName,
        quantity: quantity,
        price: price,
        total: total
    };

    items.push(item);

    let tableBody =
        document.getElementById("tableBody");

    let row = tableBody.insertRow();

    row.insertCell(0).innerHTML = itemName;
    row.insertCell(1).innerHTML = quantity;
    row.insertCell(2).innerHTML = price;
    row.insertCell(3).innerHTML = total;

    grandTotal += total;

    document.getElementById("grandTotal").value =
        grandTotal;

    // Clear item fields only
    document.getElementById("foodItem").value = "";
    document.getElementById("quantity").value = "";
    document.getElementById("price").value = "";
    document.getElementById("total").value = "";
});


// Save order
document.getElementById("saveOrderBtn")
.addEventListener("click", function () {

    let customerName =
        document.getElementById("customerName").value;

    let tableNumber =
        document.getElementById("tableNumber").value;

    if (customerName === "") {
        alert("Enter customer name.");
        return;
    }

    if (tableNumber === "") {
        alert("Enter table number.");
        return;
    }

    if (items.length === 0) {
        alert("Add at least one item.");
        return;
    }

    const orderData = {

        customerName: customerName,

        tableNumber: parseInt(tableNumber),

        grandTotal: grandTotal,

        items: items
    };

    fetch("/order/save", {

        method: "POST",

        headers: {
            "Content-Type": "application/json"
        },

        body: JSON.stringify(orderData)

    })
    .then(response => response.text())
    .then(data => {

        alert(data);

        // Clear everything
        document.getElementById("customerName").value = "";
        document.getElementById("tableNumber").value = "";
        document.getElementById("grandTotal").value = "";

        document.getElementById("tableBody").innerHTML = "";

        items = [];

        grandTotal = 0;
    })
    .catch(error => {

        console.log(error);

        alert("Error saving order.");
    });
});


// Clear button
document.getElementById("clearBtn")
.addEventListener("click", function () {

    document.getElementById("customerName").value = "";
    document.getElementById("tableNumber").value = "";
    document.getElementById("foodItem").value = "";
    document.getElementById("quantity").value = "";
    document.getElementById("price").value = "";
    document.getElementById("total").value = "";
    document.getElementById("grandTotal").value = "";

    document.getElementById("tableBody").innerHTML = "";

    items = [];

    grandTotal = 0;
});
document.getElementById("dashboardBtn")
.addEventListener("click", function () {

    window.location.href =
        "DashBoard.html";

});
document.getElementById("logoutBtn")
.addEventListener("click", function () {

    window.location.href = "/logout";

});