<script setup>
import {ArrowRight, UserFilled} from "@element-plus/icons-vue";
import {getByPid} from "@/api/tourist";
import {reactive} from "vue";
import {useRoute} from "vue-router";
import router from "@/router";

const route = useRoute()
const pid = Number(route.params.pid)
const product = reactive({
    pid: 0,
    catid: 0,
    name: '',
    price: 0,
    url: ''
})

getByPid(pid)
    .then(response => {
        Object.assign(product, response.data.data)
    })

const handleClick = () => {
    alert("Require Login")
}

const handleLogin = () => {
    router.push("/login")
}
</script>

<template>
    <!----------header----------->
    <el-header>
        <router-link to="/tourist">
            <img src="https://cuhk-web.oss-cn-shenzhen.aliyuncs.com/icon.png">
        </router-link>
        <div class="right">
            <el-container>
                <el-icon color="#fff" size="27px">
                    <UserFilled/>
                </el-icon>
                <p class="name">tourist</p>
                <div class="expanded">
                    <p @click="handleLogin">log in</p>
                    <p>log out</p>
                </div>
            </el-container>
        </div>
    </el-header>

    <!----------navbar----------->
    <div class="navbar">
        <el-menu
            :default-active="$route.path"
            mode="horizontal"
            class="navbar-menu"
            background-color="#545c64"
            text-color="#fff"
            active-text-color="#ffd04b">
            <el-menu-item index="/tourist">
                <router-link to="/tourist">Home</router-link>
            </el-menu-item>
            <el-menu-item index="/tourist/category/1">
                <router-link to="/tourist/category/1">Bag</router-link>
            </el-menu-item>
            <el-menu-item index="/tourist/category/2">
                <router-link to="/tourist/category/2">Toy</router-link>
            </el-menu-item>
            <el-menu-item index="/tourist/category/3">
                <router-link to="/tourist/category/3">Snack</router-link>
            </el-menu-item>
            <el-menu-item index="/tourist/category/4">
                <router-link to="/tourist/category/4">Sport</router-link>
            </el-menu-item>
            <el-menu-item index="/tourist/category/5">
                <router-link to="/tourist/category/5">Cloth</router-link>
            </el-menu-item>
            <el-menu-item index="/tourist/category/6">
                <router-link to="/tourist/category/6">Medicine</router-link>
            </el-menu-item>
            <el-menu-item index="/tourist/category/7">
                <router-link to="/tourist/category/7">Architecture</router-link>
            </el-menu-item>
            <el-menu-item index="/tourist/category/8">
                <router-link to="/tourist/category/8">Smart Device</router-link>
            </el-menu-item>
        </el-menu>

        <el-icon class="shop-cart" color="#fff" size="27px" @click="handleClick"><ShoppingCart /></el-icon>
    </div>

    <div class="main">
        <!----------locatioin----------->
        <el-breadcrumb :separator-icon="ArrowRight" class="location">
            <el-breadcrumb-item :to="{ path: '/' }">home</el-breadcrumb-item>
            <el-breadcrumb-item>product</el-breadcrumb-item>
        </el-breadcrumb>

        <!----------goods detail----------->
        <div class="detail" v-if="product.url">
            <div class="left">
                <el-image :src="product.url" fit="fill"></el-image>
            </div>
            <div class="right">
                <p class="goods-name">{{ product.name }}</p>
                <p class="goods-price">$ {{ product.price }}</p>
                <button class="goods-button" @click="handleClick">Add To Chart</button>
            </div>
        </div>
    </div>

    <!----------footer----------->
    <el-footer>
        <p>
            <a href="#">About Us</a> |
            <a href="#">Help Center</a> |
            <a href="#">Service</a> |
            <a href="#">Delivery</a> |
            <a href="#">Bussiness</a> |
            <a href="#">Links</a>
        </p>
        <p>CopyRight @ 1155203419@link.cuhk.edu.hk</p>
    </el-footer>
</template>

<style scoped>
/* -----------header------------ */
.el-header {
    background: #131921;
}

.el-header img {
    height: 50px;
    padding-top: 5px;
    padding-left: 10px;
    display: inline-block;
}

.el-header .right {
    height: 100%;
    float: right;
    color: #ffffff;
}

.el-header .right .el-container {
    height: 100%;
    align-items: center;
}

.el-header .right .el-icon {
    margin-top: 3px;
    margin-right: 8px;
}

.el-header .right .el-icon:hover {
    cursor: pointer;
}

.el-header .right .name {
    margin-right: 6px;
}

.expanded {
    width: 80px;
    height: 75px;
    background-color: #ffffff;
    color: #000;
    border-radius: 5px;
    box-shadow: 0 15px 30px rgba(0, 0, 0, .1);
    position: absolute;
    top: 50px;
    right: 80px;
    display: none;
}

.right:hover .expanded {
    display: block;
}

.expanded p {
    height: 30px;
    padding-left: 13px;
    font-family: "Helvetica Neue";
}

.expanded p:first-child {
    padding-top: 8px;
    border-radius: 5px 5px 0 0;
}

.expanded p:last-child {
    padding-top: 8px;
    border-radius: 0 0 5px 5px;
}

.expanded p:hover {
    background-color: #c8c9cc;
    cursor: pointer;
}

/* -----------navbar------------ */
.navbar {
    height: 46px;
    background-color: #545c64;
}

.navbar-menu {
    float: left;
    height: 47px;
    width: 80%;
}

.shop-cart {
    float: right;
    padding-right: 30px;
    padding-top: 8px;
}

.shop-cart:hover {
    cursor: pointer;
}

.main {
    background: #f5f5f5;
}

.main .location {
    height: 50px;
    padding-top: 20px;
    padding-left: 30px;
}

/* -----------location------------ */
.main .location .el-breadcrumb_item {
    height: 50px;
    display: inline-block;
}

/* -----------detail------------ */
.main {
    padding-bottom: 30px;
}

.detail {
    width: 90%;
    height: 500px;
    margin: 0 auto;
    border-radius: 10px;
    background: #ffffff;
}

.detail .left {
    float: left;
    width: 600px;
    height: 100%;
}

.detail .left .el-image {
    width: 450px;
    margin: 5px 0 0 70px;
}

.goods-name {
    font-size: 35px;
    font-weight: bold;
    padding-top: 50px;
}

.goods-price {
    font-size: 27px;
    font-weight: bold;
    color: red;
    padding-top: 20px;
}

.goods-button {
    border: none;
    border-radius: 8px;
    padding: 10px 20px;
    margin-top: 20px;
    background-color: #f5f5f5;
    color: rgb(46, 49, 49);
    font-size: 16px;
}

.goods-button:hover {
    background-color: rgb(0, 0, 0, 0.5);
}

/* -----------footer------------ */
.el-footer {
    height: 100px;
    background-color: #131921;
    text-align: center;
    padding-top: 20px;
}

.el-footer p {
    color: white;
    margin-top: 6px;
}

.el-footer a {
    color: white;
}
</style>