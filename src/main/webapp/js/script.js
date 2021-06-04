/*
 * author : Lovepreet Singh & Purva Saxena
 */

function add_to_cart(pid, pname, price) {

    let cart = localStorage.getItem("cart");

    if (cart == null) {
        let products = [];
        let product = {productId: pid, productName: pname, productQuantity: 1, productPrice: price};
        products.push(product);
        localStorage.setItem("cart", JSON.stringify(products));
        showToast("Product is added to cart");
    } else {
        // if cart is already present

        let pcart = JSON.parse(cart);


        let oldProduct = pcart.find((item) => item.productId == pid)
        if (oldProduct) {
            // increase quantity
            oldProduct.productQuantity = oldProduct.productQuantity + 1;
            pcart.map((item) => {
                if (item.productId == oldProduct.productId) {
                    item.productQuantity = oldProduct.productQuantity;
                }
            })
            localStorage.setItem("cart", JSON.stringify(pcart));
            showToast(oldProduct.productName + "'s Quantity is increased");
        } else {
            let product = {productId: pid, productName: pname, productQuantity: 1, productPrice: price};
            pcart.push(product);
            localStorage.setItem("cart", JSON.stringify(pcart));
            showToast("Product is added to cart");
        }
    }
    update_cart();
}

// update cart

function update_cart() {

    let cartString = localStorage.getItem("cart");
    let cart = JSON.parse(cartString);

    if (cart == null || cart.length == 0) {
        console.log("Cart is Empty!! ");
        $(".cart-items").html("( 0 )");
        $(".cart-body").html("<h3>Cart does not have any items");
        $(".checkout-btn").addClass('disabled');
    } else {
        $(".cart-items").html(`( ${cart.length} )`);
        let table = `
            <table class='table'>
            <thead class='thead-light'>
                <tr>
                    <th>Item Name</th>
                    <th>Price</th>
                    <th>Quantity</th>
                    <th>Total Cost</th>
                    <th>Action</th>
                </tr>
            </thead>
        
        `;

        let totalPrice = 0;
        cart.map((item) => {
            table += `
            <tr>
            <td name="pname">${item.productName}</td>
            <td >${item.productPrice}</td>
            <td>${item.productQuantity} <button class="badge badge-pill badge-success btn-sm" style="border: none;" onclick='incrementItem(${item.productId})'>+</button><button class="badge badge-pill badge-danger btn-sm" style="border: none;" onclick='decrementItem(${item.productId})'>-</button></td>
            <td>${item.productPrice * item.productQuantity}</td>
            <td><button onclick= 'deleteItemFromCart(${item.productId})' class="btn btn-danger btn-sm">Remove</button></td>
            <tr>
        `
            totalPrice += (item.productPrice * item.productQuantity)
        })

        table = table + `
            <tr >
                <td colspan='4' style="font-weight: bold; font-size: 15px">Total Price : </td>
        
                <td style="font-weight: bold; font-size: 20px">&#8377;${totalPrice}</td>
            </tr>
            </table>`
        $(".cart-body").html(table);
        $(".checkout-btn").removeClass('disabled');

    }
}

function deleteItemFromCart(pid) {
    let cart = JSON.parse(localStorage.getItem("cart"));

    let new_cart = cart.filter((item) => item.productId != pid);
    localStorage.setItem("cart", JSON.stringify(new_cart));
    update_cart();
    showToast("Item has been removed from cart.");
}

$(document).ready(function () {
    update_cart();

})

function GoToCheckOut() {
    window.location = "CheckOut.jsp";
}


//function for incrementing the quantity of each item in cart //cart.toString();
function incrementItem(x) {
    let pcart = JSON.parse(localStorage.getItem("cart"));
    let oldProduct = pcart.find((item) => item.productId == x)

    // increase quantity

    oldProduct.productQuantity = oldProduct.productQuantity + 1;
    pcart.map((item) => {
        if (item.productId == oldProduct.productId) {
            item.productQuantity = oldProduct.productQuantity;
        }
    })
    localStorage.setItem("cart", JSON.stringify(pcart));
    update_cart();

}

//function for decrementing the quantity of each item in cart
function decrementItem(x) {
    let pcart = JSON.parse(localStorage.getItem("cart"));
    let oldProduct = pcart.find((item) => item.productId == x)
    if (oldProduct.productQuantity == 1) {
        deleteItemFromCart(x);
    } else {
        oldProduct.productQuantity = oldProduct.productQuantity - 1;
        pcart.map((item) => {
            if (item.productId == oldProduct.productId) {
                item.productQuantity = oldProduct.productQuantity;
            }
        })
        localStorage.setItem("cart", JSON.stringify(pcart));
        update_cart();
    }
}


function showToast(content) {
    $("#toast").addClass("display");
    $("#toast").html(content);
    setTimeout(() => {
        $("#toast").removeClass("display");
    }, 2000);
}



function update_product(pid, name, desc, quantity, cost, discount, category, brand) {

    document.getElementById("ProductId").value = pid;
    document.getElementById("ProductName").value = name;
    document.getElementById("description").value = desc;
    document.getElementById("ProductBrand").value = brand;
    document.getElementById("ProductCategory").value = category;
    document.getElementById("Cost").value = cost;
    document.getElementById("Quantity").value = quantity;
    document.getElementById("Discount").value = discount;
}

function remove_product(pid) {
    document.getElementById("id").value = pid;
}

function initialize_map() {

    var cart = (localStorage.getItem("cart"));
    if (cart == null) {
        $(".placeOrder").addClass('disabled');
    }
    document.getElementById("cart-details").value = JSON.stringify(cart);
}


function editUser(id, name, email, gender, address, phoneNo) {
    document.getElementById("UserId").value = id;
    document.getElementById("name").value = name;
    document.getElementById("email").value = email;
    document.getElementById("gender").value = gender;
    document.getElementById("address").value = address;
    document.getElementById("PhoneNo").value = phoneNo;

}


// ******** Script for BUY NOW button ***********

function incrementSelectedItem(cost){
    let x = ++document.getElementById("Quantity").value ;
    document.getElementById("totalCost").value = x*cost;
    
}
function decrementSelectedItem(cost){
    console.log("hlo i m in decreement");
    let x = --document.getElementById("Quantity").value ;
    document.getElementById("totalCost").value = x*cost;
}
