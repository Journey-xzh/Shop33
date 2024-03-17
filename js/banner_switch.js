var prev = document.getElementById("prev");
var next = document.getElementById("next");
var bannerImg = document.getElementById("banner_img");

var imageSources = ["./images/banner_1.png", "./images/banner_2.png"]
var index = 0;

var squareItems = document.querySelectorAll(".banner ol li");
squareItems[0].style.backgroundColor = "#fff";

next.addEventListener("click", bannerNext);
prev.addEventListener("click", bannerPrev);

function bannerNext() {
    squareItems[index].style.backgroundColor = "rgba(255,255,255,0.4)";
    index = (index + 1) % imageSources.length;
    bannerImg.src = imageSources[index];
    squareItems[index].style.backgroundColor = "#fff";
}
function bannerPrev() {
    squareItems[index].style.backgroundColor = "rgba(255,255,255,0.4)";
    index = (index - 1 + imageSources.length) % imageSources.length;
    bannerImg.src = imageSources[index];
    squareItems[index].style.backgroundColor = "#fff";
}



