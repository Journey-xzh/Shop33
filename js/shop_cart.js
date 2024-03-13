var addToChartButtons = document.querySelectorAll(".goods_button");
var shopCart = document.getElementById("total_price");
const shopCartDetails = document.querySelector(".expanded");
var total = 0;
addToChartButtons.forEach(button => {
    button.addEventListener("click", () => {
        const name = button.dataset.name;
        const price = button.dataset.price;
        total += parseInt(price);
        shopCart.innerText = total.toString();

        const paragraph = document.createElement("p");
        paragraph.textContent = name + '\t' + price;
        shopCartDetails.appendChild(paragraph);
    })
})