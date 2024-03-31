<script setup lang="ts">
import {deleteCategory, getAll, insertCategory, updateCategory} from "@/api/category";
import {ref, reactive, onMounted} from "vue";
import { ElForm } from 'element-plus';
import {useStore} from "vuex";
import {UserFilled} from "@element-plus/icons-vue";

const tableData = ref([])

const insertFormVisible = ref(false)
const updateFormVisible = ref(false)

const form = reactive({
    categoryName: ''
})

let category = reactive({
    catid: 0,
    name: ''
})

onMounted(async () => {
    try {
        const response = await getAll()
        tableData.value = response.data.data
    } catch (error) {
        console.log("Mount error: " + error)
    }
});

function insertFormSubmit() {
    insertFormRef.value.validate((valid) => {
        if (valid) {
            insertFormVisible.value = false
            insertCategory(form.categoryName)
                .then(res => {
                    alert("Sucessfully Add New Category")
                })
        } else {
            alert("Please Fill the Form Correctly")
        }
    })
}

function updateFormSubmit() {
    upsertFormRef.value.validate((valid) => {
        if (valid) {
            updateFormVisible.value = false
            category.name = form.categoryName
            updateCategory(category)
                .then(res => {
                    alert("Sucessfully Update Category")
                })
        } else {
            alert("Please Fill the Form Correctly")
        }
    })
}

function handleAdd() {
    insertFormVisible.value = true
}

function handleDelete(row) {
    deleteCategory(row.catid)
        .then(res => {
            alert("Delete Successfully")
        })
}

function handleUpdate(row) {
    category.catid = row.catid
    updateFormVisible.value = true
}

// 安全性：处理用户输入问题
const insertFormRef = ref(null)
const upsertFormRef = ref(null)
const rules = {
    categoryName: [
        { required: true, message: 'please input category name', trigger: 'blur' },
        { min: 1, max: 10, message: 'length should be 1 to 10', trigger: 'blur' },
        { pattern: /^[a-zA-Z]+$/, message: 'only accept words', trigger: 'blur' },
    ]
}

const userName = localStorage.getItem("userName")
</script>

<template>
    <el-container class="baseContainer">
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

        <el-container>
            <el-aside width="20%">
                <el-menu
                    default-active="1"
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
                </el-menu>
            </el-aside>

            <el-main>
                <el-container style="justify-content: flex-end">
                    <el-button class="add-button" @click="handleAdd">
                        Add New Category
                    </el-button>
                </el-container>

                <el-table :data="tableData" border style="width: 100%; margin-top: 20px">
                    <el-table-column prop="catid" label="catid"/>
                    <el-table-column prop="name" label="Name"/>
                    <el-table-column label="Operations">
                        <template v-slot:default="scope">
                            <el-button link type="primary" size="small" @click="handleUpdate(scope.row)">Update</el-button>
                            <el-button link type="primary" size="small" @click="handleDelete(scope.row)">Delete</el-button>
                        </template>
                    </el-table-column>
                </el-table>

                <el-dialog v-model="insertFormVisible" width="500">
                    <el-form :model="form" :rules="rules" ref="insertFormRef">
                        <el-form-item prop="categoryName" label="Category Name" label-width="140px">
                            <el-input v-model="form.categoryName" autocomplete="off"/>
                        </el-form-item>
                    </el-form>
                    <template #footer>
                        <el-button @click="insertFormVisible = false">Cancel</el-button>
                        <el-button type="primary" @click="insertFormSubmit">
                            Confirm
                        </el-button>
                    </template>
                </el-dialog>

                <el-dialog v-model="updateFormVisible" width="500">
                    <el-form :model="form" :rules="rules" ref="upsertFormRef">
                        <el-form-item prop="categoryName" label="Category Name" label-width="140px">
                            <el-input v-model="form.categoryName" autocomplete="off"/>
                        </el-form-item>
                    </el-form>
                    <template #footer>
                        <el-button @click="updateFormVisible = false">Cancel</el-button>
                        <el-button type="primary" @click="updateFormSubmit">
                            Confirm
                        </el-button>
                    </template>
                </el-dialog>
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

.el-header .right p {
    margin-right: 6px;
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