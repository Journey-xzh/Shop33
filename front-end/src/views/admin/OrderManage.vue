<script setup>
import {UserFilled} from "@element-plus/icons-vue";
import router from "@/router";
import {onMounted, ref} from "vue";
import {getOrder} from "@/api/user";

const userName = localStorage.getItem("userName")
const handleLogout = () => {
    localStorage.setItem("userName", null)
    router.push("/login")
}

const tableData = ref([])
onMounted(async () => {
    try {
        const response = await getOrder()
        tableData.value = response.data.data
    } catch (error) {
        console.log("Mount error: " + error)
    }
});

// 处理category
const categoryMap = ref({
    1: 'Bag',
    2: 'Toy',
    3: 'Snack',
    4: 'Sport',
    5: 'Cloth',
    6: 'Medicine',
    7: 'Architecture',
    8: 'Smart Device'
})
function getCategoryName(catid) {
    return categoryMap.value[catid] || '未知类别';
}

</script>

<template>
    <el-container class="baseContainer">
        <!----------header----------->
        <el-header>
            <router-link to="/admin">
                <img src="https://cuhk-web.oss-cn-shenzhen.aliyuncs.com/icon.png">
            </router-link>
            <div class="right">
                <el-container>
                    <el-icon color="#fff" size="27px">
                        <UserFilled/>
                    </el-icon>
                    <p class="name">{{ userName }}</p>
                    <div class="expanded">
                        <p>log in</p>
                        <p @click="handleLogout">log out</p>
                    </div>
                </el-container>
            </div>
        </el-header>

        <el-container>
            <el-aside width="20%">
                <el-menu
                    default-active="3"
                    active-text-color="#ffd04b"
                    background-color="#545c64"
                    class="el-menu-vertical"
                    text-color="#fff">
                    <el-menu-item index="1">
                        <el-icon>
                            <document/>
                        </el-icon>
                        <router-link to="/admin/category" class="custom-link">Category</router-link>
                    </el-menu-item>
                    <el-menu-item index="2">
                        <el-icon>
                            <setting/>
                        </el-icon>
                        <router-link to="/admin/product" class="custom-link">Product</router-link>
                    </el-menu-item>
                    <el-menu-item index="3">
                        <el-icon><ShoppingCart /></el-icon>
                        <router-link to="/admin/order" class="custom-link">Order</router-link>
                    </el-menu-item>
                </el-menu>
            </el-aside>

            <el-main>
                <el-table :data="tableData" border style="width: 100%; margin-top: 20px">
                    <el-table-column prop="id" label="Order Id"/>
                    <el-table-column prop="pid" label="pid"/>
                    <el-table-column label="Category">
                        <template v-slot="scope">
                            <span>{{ getCategoryName(scope.row.catid) }}</span>
                        </template>
                    </el-table-column>
                    <el-table-column prop="name" label="Name"/>
                    <el-table-column prop="price" label="Price"/>
                    <el-table-column prop="quantity" label="Quantity"/>
                </el-table>
            </el-main>
        </el-container>
    </el-container>
</template>

<style scoped>
.baseContainer {
    height: 100%;
}

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

.el-menu-vertical {
    height: 100%;
}

.custom-link {
    text-decoration: none;
    color: inherit;
}

.el-main .add-button {
    background-color: #545c64;
    color: #ffffff;
}
</style>