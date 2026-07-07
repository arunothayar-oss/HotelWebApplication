let items = [];
let grandTotal = 0;

/* ================================
   Menu Data
================================ */

const menu = {

    BREAKFAST: {
        "Idli": 20,
        "Dosa": 40,
        "Vada": 10,
        "Pongal": 30,
        "Poori": 40,
        "Upma": 50,
        "Kesari": 10
    },

    LUNCH: {
        "Meals": 100,
        "Biryani": 220,
        "Fried Rice": 120,
        "Veg Rice": 130,
        "Curd Rice": 50
    },

    DINNER: {
        "Chapati": 20,
        "Parotta": 10,
        "Noodles": 50,
        "Idli": 20,
        "Dosa": 40
    },

    BEVERAGES: {
        "Tea": 20,
        "Coffee": 30,
        "Juice": 80,
        "Soft Drink": 50
    }

};


/* ================================
   Category Change
================================ */

document.getElementById("category").addEventListener("change", function () {

    let category = this.value;

    let foodItem = document.getElementById("foodItem");

    foodItem.innerHTML = "<option value=''>Select Item</option>";

    if (category === "") {
        return;
    }

    for (let item in menu[category]) {

        let option = document.createElement("option");

        option.value = item;

        option.text = item;

        foodItem.appendChild(option);
    }

    document.getElementById("price").value = "";
    document.getElementById("quantity").value = "";
    document.getElementById("total").value = "";

});


/* ================================
   Food Item Change
================================ */

document.getElementById("foodItem").addEventListener("change", function () {

    let category = document.getElementById("category").value;

    let item = this.value;

    if (category === "" || item === "") {

        document.getElementById("price").value = "";

        return;
    }

    document.getElementById("price").value = menu[category][item];

});


/* ================================
   Quantity Change
================================ */

document.getElementById("quantity").addEventListener("input", function () {

    let quantity = parseInt(this.value) || 0;

    let price = parseFloat(document.getElementById("price").value) || 0;

    document.getElementById("total").value = quantity * price;

});


/* ================================
   Add Item
================================ */

document.getElementById("addBtn").addEventListener("click", function () {

    let category = document.getElementById("category").value;

    let itemName = document.getElementById("foodItem").value;

    let quantity = parseInt(document.getElementById("quantity").value);

    let price = parseFloat(document.getElementById("price").value);

    let total = parseFloat(document.getElementById("total").value);

    if (category === "") {
        alert("Please select category.");
        return;
    }

    if (itemName === "") {
        alert("Please select food item.");
        return;
    }

    if (!quantity || quantity <= 0) {
        alert("Enter quantity.");
        return;
    }

    let item = {

        category: category,

        itemName: itemName,

        quantity: quantity,

        price: price,

        total: total

    };

    items.push(item);

    let tableBody = document.getElementById("tableBody");

    let row = tableBody.insertRow();

    row.insertCell(0).innerHTML = category;

    row.insertCell(1).innerHTML = itemName;

    row.insertCell(2).innerHTML = quantity;

    row.insertCell(3).innerHTML = price;

    row.insertCell(4).innerHTML = total;

    grandTotal += total;

    document.getElementById("grandTotal").value = grandTotal;

    document.getElementById("category").value = "";

    document.getElementById("foodItem").innerHTML =
        "<option value=''>Select Category First</option>";

    document.getElementById("quantity").value = "";

    document.getElementById("price").value = "";

    document.getElementById("total").value = "";

});


/* ================================
   Save Order
================================ */

document.getElementById("saveOrderBtn").addEventListener("click", function () {

    let customerName =
        document.getElementById("customerName").value.trim();

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

        document.getElementById("customerName").value = "";

        document.getElementById("tableNumber").value = "";

        document.getElementById("category").value = "";

        document.getElementById("foodItem").innerHTML =
            "<option value=''>Select Category First</option>";

        document.getElementById("quantity").value = "";

        document.getElementById("price").value = "";

        document.getElementById("total").value = "";

        document.getElementById("grandTotal").value = "";

        document.getElementById("tableBody").innerHTML = "";

        items = [];

        grandTotal = 0;

    })

    .catch(error => {

        console.error(error);

        alert("Error saving order.");

    });

});


/* ================================
   Clear
================================ */

document.getElementById("clearBtn").addEventListener("click", function () {

    document.getElementById("customerName").value = "";

    document.getElementById("tableNumber").value = "";

    document.getElementById("category").value = "";

    document.getElementById("foodItem").innerHTML =
        "<option value=''>Select Category First</option>";

    document.getElementById("quantity").value = "";

    document.getElementById("price").value = "";

    document.getElementById("total").value = "";

    document.getElementById("grandTotal").value = "";

    document.getElementById("tableBody").innerHTML = "";

    items = [];

    grandTotal = 0;

});


/* ================================
   Dashboard
================================ */

document.getElementById("dashboardBtn").addEventListener("click", function () {

    window.location.href = "DashBoard.html";

});


/* ================================
   Logout
================================ */

document.getElementById("logoutBtn").addEventListener("click", function () {

    window.location.href = "/logout";

});