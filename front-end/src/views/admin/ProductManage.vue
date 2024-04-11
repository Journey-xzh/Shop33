<script setup>
import {onMounted, reactive, ref} from "vue";
import {getAllProduct, insertProduct, deleteProduct, updateProduct} from "@/api/product";
import {ElUpload} from "element-plus";
import {UserFilled} from "@element-plus/icons-vue";
import router from "@/router";

// 处理table
const tableData = ref([])
onMounted(async () => {
    try {
        const response = await getAllProduct()
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

// 处理表单数据
const options = [
    { value: '1', label: 'Bag' },
    { value: '2', label: 'Toy' },
    { value: '3', label: 'Snack' },
    { value: '4', label: 'Sport' },
    { value: '5', label: 'Cloth' },
    { value: '6', label: 'Medicine' },
    { value: '7', label: 'Architecture' },
    { value: '8', label: 'Smart Device' }
]

// 处理表单提交
const insertFormVisible = ref(false)
const updateFormVisible = ref(false)
const insertForm = reactive({
    catid: null,
    name: '',
    price: null,
    fileList: []
})
const updateForm = reactive({
    pid: 0,
    catid: 0,
    name: '',
    price: 0,
    fileList: []
})
function insertFormSubmit() {
    insertFormRef.value.validate((valid) => {
        if (valid) {
            const formData = new FormData();
            formData.append('catid', insertForm.catid);
            formData.append('name', insertForm.name);
            formData.append('price', insertForm.price);
            if (insertForm.fileList.length > 0) {
                if (insertForm.fileList[0].raw.type === 'image/jpeg' ||
                    insertForm.fileList[0].raw.type === 'image/png') {

                    formData.append('file', insertForm.fileList[0].raw)
                    insertProduct(formData)
                    insertFormVisible.value = false
                } else {
                    alert("Type ERROR: Only Accept JPG or PNG")
                }
            } else {
                alert("Please Select one image")
            }
        } else {
            alert("Please Fill the Form Correctly")
        }
    })
}
function updateFormSubmit() {
    updateFormRef.value.validate((valid) => {
        if (valid) {
            const formData = new FormData();
            formData.append('pid', updateForm.pid);
            formData.append('catid', updateForm.catid);
            formData.append('name', updateForm.name);
            formData.append('price', updateForm.price);
            if (updateForm.fileList.length > 0) {
                if (updateForm.fileList[0].raw.type === 'image/jpeg' ||
                    updateForm.fileList[0].raw.type === 'image/png') {

                    formData.append('file', updateForm.fileList[0].raw)
                    updateProduct(formData)
                    updateFormVisible.value = false
                } else {
                    alert("Type ERROR: Only Accept JPG or PNG")
                }
            } else {
                alert("Please Select one image")
            }
        } else {
            alert("Please Fill the Form Correctly")
        }
    })
}

// 处理更新和删除
function handleDelete(row) {
    deleteProduct(row.pid)
        .then( () => {
            alert("Delete Successfully")
        })
}

function handleUpdate(row) {
    // 数据回显
    updateForm.pid = row.pid
    updateForm.catid = row.catid
    updateForm.name = row.name
    updateForm.price = row.price
    updateFormVisible.value = true
}

// 安全性：处理表单输入格式
const insertFormRef = ref(null)
const insertRules = {
    catid: [
        { required: true, message: "please select one category", trigger: 'change' }
    ],
    name: [
        { required: true, message: "please input price", trigger: 'blur' },
        { pattern: /^[a-zA-Z]+$/, message: 'only accept words', trigger: 'blur' }
    ],
    price: [
        { required: true, message: "please input price", trigger: 'blur' },
        { pattern: /^[0-9]+$/, message: 'only accept integer', trigger: 'blur' }
    ],
}
const updateFormRef = ref(null)
const updateRules = {
    catid: [
        { required: true, message: "please select one category", trigger: 'change' }
    ],
    name: [
        { required: true, message: "please input price", trigger: 'blur' },
        { pattern: /^[a-zA-Z]+$/, message: 'only accept words', trigger: 'blur' }
    ],
    price: [
        { required: true, message: "please input price", trigger: 'blur' },
        { pattern: /^[0-9]+$/, message: 'only accept integer', trigger: 'blur' }
    ],
}

const userName = localStorage.getItem("userName")

const handleLogout = () => {
    localStorage.setItem("userName", null)
    router.push("/login")
}
</script>

<template>
    <el-container class="baseContainer">
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
                    default-active="2"
                    active-text-color="#ffd04b"
                    background-color="#545c64"
                    class="el-menu-vertical"
                    text-color="#fff">
                    <el-menu-item index="1">
                        <el-icon><document /></el-icon>
                        <router-link to="/admin/category" class="custom-link-1">Category</router-link>
                    </el-menu-item>
                    <el-menu-item index="2" class="custom-link">
                        <el-icon><setting /></el-icon>
                        <router-link to="/admin/product" class="custom-link-2">Product</router-link>
                    </el-menu-item>
                    <el-menu-item index="3">
                        <el-icon><ShoppingCart /></el-icon>
                        <router-link to="/admin/order" class="custom-link-1">Order</router-link>
                    </el-menu-item>
                </el-menu>
            </el-aside>

            <el-main>
                <el-container style="justify-content: flex-end">
                    <el-button class="add-button" @click="insertFormVisible = true">
                        Add New Product
                    </el-button>
                </el-container>

                <el-table :data="tableData" border style="width: 100%; margin-top: 20px">
                    <el-table-column prop="pid" label="pid"/>
                    <el-table-column prop="catid" label="Category">
                        <template #default="scope">
                            <span>{{ getCategoryName(scope.row.catid) }}</span>
                        </template>
                    </el-table-column>
                    <el-table-column prop="name" label="Name"/>
                    <el-table-column prop="price" label="Price"/>
                    <el-table-column prop="url" label="Image">
                        <template v-slot:default="scope">
                            <el-image :src="scope.row.url" fit="contain" style="width: 100px"></el-image>
                        </template>
                    </el-table-column>
                    <el-table-column label="Operations">
                        <template v-slot:default="scope">
                            <el-button link type="primary" size="small" @click="handleUpdate(scope.row)">Update</el-button>
                            <el-button link type="primary" size="small" @click="handleDelete(scope.row)">Delete</el-button>
                        </template>
                    </el-table-column>
                </el-table>

                <el-dialog v-model="insertFormVisible" width="500">
                    <el-form :model="insertForm" :rules="insertRules" ref="insertFormRef">
                        <el-form-item prop="catid" label="Category" label-width="140px">
                            <el-select v-model="insertForm.catid" placeholder="Select" size="default" style="width: 240px">
                                <el-option v-for="item in options" :key="item.value" :label="item.label" :value="item.value"/>
                            </el-select>
                        </el-form-item>
                        <el-form-item prop="name" label="Name" label-width="140px">
                            <el-input v-model="insertForm.name" autocomplete="off"/>
                        </el-form-item>
                        <el-form-item prop="price" label="Price" label-width="140px">
                            <el-input v-model="insertForm.price" autocomplete="off"/>
                        </el-form-item>
                        <el-form-item prop="file" label="Image" label-width="140px">
                            <el-upload
                                ref="uploadRef"
                                limit="1"
                                v-model:file-list="insertForm.fileList"
                                :auto-upload="false">
                                <template #trigger>
                                    <el-button type="primary">select</el-button>
                                </template>
                            </el-upload>
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
                    <el-form :model="updateForm" :rules="updateRules" ref="updateFormRef">
                        <el-form-item prop="catid" label="Category" label-width="140px">
                            <el-select v-model="updateForm.catid" placeholder="Select" size="default" style="width: 240px">
                                <el-option v-for="item in options" :key="item.value" :label="item.label" :value="item.value"/>
                            </el-select>
                        </el-form-item>
                        <el-form-item prop="name" label="Name" label-width="140px">
                            <el-input v-model="updateForm.name" autocomplete="off"/>
                        </el-form-item>
                        <el-form-item prop="price" label="Price" label-width="140px">
                            <el-input v-model="updateForm.price" autocomplete="off"/>
                        </el-form-item>
                    </el-form>
                    <el-upload
                        ref="uploadRef"
                        action=""
                        class="upload-img"
                        limit="1"
                        v-model:file-list="updateForm.fileList"
                        :auto-upload="false">
                        <template #trigger>
                            <el-button type="primary">select image</el-button>
                        </template>
                    </el-upload>
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

/* -----------menu------------ */
.el-menu-vertical {
    height: 100%;
}

.custom-link-1 {
    text-decoration: none;
    color: white;
}

.custom-link-2 {
    text-decoration: none;
    color: #ffd04b;
}

.el-main .add-button {
    background-color: #545c64;
    color: #ffffff;
}

</style>