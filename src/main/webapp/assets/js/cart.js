function Product(idjs, namejs, pricejs) {
    this.idjs = idjs,
        this.namejs = namejs,
        this.pricejs = pricejs;
};

let cart = [];

function addtoCart(idjs) {
    let product = Products.find(function (product, index) {
        return product.idjs == idjs;
    });
    let check = checkCart(product);
    if (check == true) {
        cart.push(product);
        showCart();
        SubmitCart();
    }

}

function checkCart(product) {
    for (let i = 0; i < cart.length; i++) {
        if (cart[i].idjs == product.idjs) {
            alert("Món này đã được thêm");
            return false;
        }
    }
    return true;
}

function showProduct() {
    let tb_drink = document.getElementById('tb_drink');
    for (let i = 0; i < Products.length; i++) {
        tb_drink.innerHTML += `<tr>
                                <td><label>${Products[i].namejs}</label> </td>
                                <td><a class="btn" href="javascript:void(0);" onclick="addtoCart(${Products[i].idjs})" style="font-size: 20px"><i class="far fa-plus-square"></i></a></td>
                                </tr>`
    }
}

function showCart() {
    let tbody = document.getElementById('cart');
    tbody.innerHTML = "";
    for (let i = 0; i < cart.length; i++) {
        tbody.innerHTML += `<tr>
                                <td>${cart[i].namejs}</td>
                                <td><input type="number" value="1" min="1" name="quantity${i}" style="width: 50px" onchange="checkValue(this)">
                                <input type="text" value="${cart[i].idjs}" name="idDrink${i}" hidden>
                                <input type="text" value="${cart[i].pricejs}" name="price${i}" hidden>
                                </td>
                                <td><a class="btn" href="javascript:void(0);" onclick="removeCart(${Products[i].idjs})" style="font-size: 20px"><i class="far fa-minus-square"></i></a></td>
                            </tr>`;
    }
    tbody.innerHTML += `<input type="text" value="${cart.length}" name="total_items" hidden>`;
}

function removeCart(id) {
    if (cart.length == 1)
        cart = [];
    for (let i = 0; i < cart.length; i++) {
        if (cart[i].idjs == id) {
            let items = i;
            cart.splice(i, 1);
        }
    }
    showCart();
    SubmitCart();
}

function checkValue(obj) {
    if (obj.value < 1) {
        alert("Số lượng phải lớn hơn 0");
        obj.value = 1;
    }
}

function calculateTotalAmount() {
    return cart.reduce(function (total, item, index) {
        return total + item.pricejs;
    }, 0)
}
function SubmitCart(){
    if (cart.length>0)
        document.getElementById("submit_cart").disabled=false;
    else
        document.getElementById("submit_cart").disabled=true;
}