<script setup>
import {ArrowRight, UserFilled} from "@element-plus/icons-vue";
import {useRoute} from "vue-router";
import {computed, reactive, ref} from "vue";
import {getByCatid} from "@/api/user";
import {useStore} from "vuex";

const route = useRoute()
const catid = Number(route.params.catid)
let products = reactive([])

getByCatid(catid)
    .then(response => {
        // 展开式语法
        response.data.data.forEach(product => {
            products.push(product);
        });
    })

// 购物车
const drawer = ref(false)

const store = useStore();
const cart = computed(() => store.state.cart);
const addToCart = (product) => {
    store.dispatch('addToCart', product);
    alert("Add To Shop Cart");
};

const decreaseQuantity = (item) => {
    store.dispatch('decreaseQuantity', item);
};

const increaseQuantity = (item) => {
    store.dispatch('increaseQuantity', item);
};

const removeFromCart = (item) => {
    store.dispatch('removeFromCart', item);
};

const totalPrice = computed(() => store.getters.totalPrice);

const userName = localStorage.getItem("userName")
</script>

<template>
    <!----------header----------->
    <el-header>
        <router-link to="/user">
            <img src="https://cuhk-web.oss-cn-shenzhen.aliyuncs.com/icon.png">
        </router-link>
        <div class="right">
            <el-container>
                <router-link to="/login">
                    <el-icon color="#fff" size="27px">
                        <UserFilled/>
                    </el-icon>
                </router-link>
                <p>{{userName}}</p>
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
            <el-menu-item index="/user">
                <router-link to="/user">Home</router-link>
            </el-menu-item>
            <el-menu-item index="/user/category/1">
                <router-link to="/user/category/1">Bag</router-link>
            </el-menu-item>
            <el-menu-item index="/user/category/2">
                <router-link to="/user/category/2">Toy</router-link>
            </el-menu-item>
            <el-menu-item index="/user/category/3">
                <router-link to="/user/category/3">Snack</router-link>
            </el-menu-item>
            <el-menu-item index="/user/category/4">
                <router-link to="/user/category/4">Sport</router-link>
            </el-menu-item>
            <el-menu-item index="/user/category/5">
                <router-link to="/user/category/5">Cloth</router-link>
            </el-menu-item>
            <el-menu-item index="/user/category/6">
                <router-link to="/user/category/6">Medicine</router-link>
            </el-menu-item>
            <el-menu-item index="/user/category/7">
                <router-link to="/user/category/7">Architecture</router-link>
            </el-menu-item>
            <el-menu-item index="/user/category/8">
                <router-link to="/user/category/8">Smart Device</router-link>
            </el-menu-item>
        </el-menu>

        <el-icon class="shop-cart" color="#fff" size="27px" @click="drawer = true"><ShoppingCart /></el-icon>
        <el-drawer v-model="drawer" :with-header="false" style="width: 430px">
            <p>Total: ￥<span>{{ totalPrice }}</span></p>
            <el-table :data="cart" style="width: 100%; height: 100%">
                <el-table-column prop="url" label="">
                    <template v-slot:default="scope">
                        <el-image :src="scope.row.url" fit="contain"></el-image>
                    </template>
                </el-table-column>
                <el-table-column prop="" label="Price">
                    <template v-slot:default="scope">
                        ￥{{ scope.row.price * scope.row.quantity }}
                    </template>
                </el-table-column>
                <el-table-column prop="quantity" label="Number">
                    <template v-slot:default="scope">
                        <el-button @click="decreaseQuantity(scope.row)" circle style="width:10px;height:10px;margin-right:5px">-</el-button>
                        <span>{{ scope.row.quantity }}</span>
                        <el-button @click="increaseQuantity(scope.row)" circle style="width:10px;height:10px;margin-left:5px">+</el-button>
                    </template>
                </el-table-column>
                <el-table-column prop="address" label="Operation">
                    <template v-slot:default="scope">
                        <el-button link type="primary" size="small" @click="removeFromCart(scope.row)">Delete</el-button>
                    </template>
                </el-table-column>
            </el-table>
        </el-drawer>
    </div>

    <div class="main">
        <!----------locatioin----------->
        <el-breadcrumb :separator-icon="ArrowRight" class="location">
            <el-breadcrumb-item :to="{ path: '/' }">home</el-breadcrumb-item>
            <el-breadcrumb-item>category</el-breadcrumb-item>
        </el-breadcrumb>

        <!----------category----------->
        <p class="goods-title" v-if="catid==1">Bag</p>
        <p class="goods-title" v-else-if="catid==2">Toy</p>
        <p class="goods-title" v-else-if="catid==3">Snack</p>
        <p class="goods-title" v-else-if="catid==4">Sport</p>
        <p class="goods-title" v-else-if="catid==5">Cloth</p>
        <p class="goods-title" v-else-if="catid==6">Medicine</p>
        <p class="goods-title" v-else-if="catid==7">Architecture</p>
        <p class="goods-title" v-else-if="catid==8">Smart Device</p>

        <div class="goods" v-if="products.length">
            <ul>
                <li v-for="(product, index) in products" :key="index">
                    <router-link :to="`/user/product/${product.pid}`">
                        <el-image :src="product.url" fit="scale-down"></el-image>
                        <p class="goods-name">{{ product.name }}</p>
                        <p class="goods-price">￥ {{ product.price }}</p>
                    </router-link>
                    <button class="goods-button" @click="addToCart(product)">Add To Cart</button>
                </li>
            </ul>
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

.el-header .right p {
    margin-right: 6px;
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

/* -----------category------------ */
.goods-title {
    padding-left: 50px;
    padding-bottom: 20px;
    font-size: 27px;
    font-weight: bold;
}

.goods ul {
    display: flex;
    justify-content: flex-start;
    flex-wrap: wrap;
}

.goods ul li {
    height: 340px;
    width: 280px;
    padding-top: 20px;
    margin-bottom: 30px;
    margin-right: 50px;
    background: white;
    text-align: center;
    border-radius: 10px;
}

.goods ul li:first-child {
    margin-left: 60px;
}

.goods ul li:hover {
    transform: translateY(-2px);
    box-shadow: 0 15px 30px rgba(0, 0, 0, .1);
}

.goods .el-image {
    width: 190px;
    height: 190px;
}

.goods-name {
    color: black;
    font-size: var(--el-font-size-large);
    font-weight: bold;
    margin-top: 13px;
}

.goods-price {
    color: red;
    font-size: var(--el-font-size-large);
    font-weight: bold;
    margin-right: 15px;
    margin-top: 10px;
}

.goods-button {
    width: 125px;
    height: 40px;
    border: none;
    margin-top: 5px;
    border-radius: 8px;
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