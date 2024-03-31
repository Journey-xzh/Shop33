<script setup lang="ts">
import {UserFilled} from "@element-plus/icons-vue";
import {reactive, ref} from "vue";
import {changePassword, login} from "@/api/login";
import {useStore} from "vuex";
import router from "@/router";
import {ElForm} from "element-plus";

const loginForm = reactive({
    email: 'user@email.com',
    password: '1234'
})

const loginRules = {
    email: [
        { required: true, message: 'please input email address', trigger: 'blur' },
        {
            pattern: /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/,
            message: 'please type correct email address',
            trigger: 'blur'
        }
    ],
    password: [
        { required: true, message: 'please input password', trigger: 'blur' },
        { pattern: /^[a-zA-Z0-9]+$/, message: 'only accept words and number', trigger: 'blur' }
    ]
}

const loginFormRef = ref(null)

// const store = useStore()

// 处理表单提交
function loginFormSubmit() {
    loginFormRef.value.validate((valid) => {
        if (valid) {
            const formData = new FormData()
            formData.append("email", loginForm.email)
            formData.append("password", loginForm.password)
            login(formData)
                .then(res => {
                    console.log(res)
                    if (res.data.data != null) {
                        const userName = res.data.data.userName
                        const jwt = res.data.data.jwt
                        // store.dispatch('setUserName', userName)
                        localStorage.setItem("token", jwt)
                        localStorage.setItem("userName", userName)
                        if (userName == "admin") {
                            router.push("/admin/category")
                        } else {
                            router.push("/user")
                        }
                    } else {
                        alert("email or password ERROR!")
                    }
                })
        } else {
            alert("Please Fill the Form Correctly")
        }
    })
}

// 处理修改密码
const pwdFormVisible = ref(false)
const pwdFormRef = ref(null)
const pwdForm = reactive({
    email: '',
    oldPassword: '',
    newPassword: ''
})
const pwdRules = {
    email: [
        { required: true, message: 'please input email address', trigger: 'blur' },
        {
            pattern: /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/,
            message: 'please type correct email address',
            trigger: 'blur'
        }
    ],
    oldPassword: [
        { required: true, message: 'please input password', trigger: 'blur' },
        { pattern: /^[a-zA-Z0-9]+$/, message: 'only accept words and number', trigger: 'blur' }
    ],
    newPassword: [
        { required: true, message: 'please input password', trigger: 'blur' },
        { pattern: /^[a-zA-Z0-9]+$/, message: 'only accept words and number', trigger: 'blur' }
    ]
}

function pwdFormSubmit() {
    pwdFormRef.value.validate((valid) => {
        if (valid) {
            const formData = new FormData()
            formData.append("email", pwdForm.email)
            formData.append("oldPassword", pwdForm.oldPassword)
            formData.append("newPassword", pwdForm.newPassword)
            changePassword(formData).then(res => {
                if (res.data.code == 1) {
                    alert("Successfully change password")
                } else {
                    alert("email or old password ERROR!")
                }
            })
        } else {
            alert("Please Fill the Form Correctly")
        }
    })
}


</script>

<template>
    <!----------header----------->
    <el-header>
        <router-link to="/user">
            <img src="https://cuhk-web.oss-cn-shenzhen.aliyuncs.com/icon.png">
        </router-link>
        <router-link to="/admin/category">
            <el-icon color="#fff" size="27px">
                <UserFilled/>
            </el-icon>
        </router-link>
    </el-header>

    <!----------login----------->
    <div class="login">
        <div class="wrapper">
            <div class="left">
                <img src="../assets/image/login_ikun.png">
            </div>
            <div class="right">
                <el-form :model="loginForm" :rules="loginRules" ref="loginFormRef" class="form">
                    <img src="../assets/image/amazon.svg" style="width: 200px; margin-bottom: 20px">
                    <el-form-item prop="email">
                        <el-input v-model="loginForm.email" placeholder="email"/>
                    </el-form-item>
                    <el-form-item prop="password">
                        <el-input v-model="loginForm.password" placeholder="password" show-password/>
                    </el-form-item>
                    <el-form-item>
                        <el-button @click="loginFormSubmit" class="button-submit">LOGIN</el-button>
                    </el-form-item>
                    <el-form-item class="password">
                        <div class="p-left">
                            <input type="checkbox" value="Option 1">
                            <label for="option1" style="margin-left: 8px;font-family: 'Helvetica Neue';color: #919399">Remember Me</label>
                        </div>
                        <div class="p-right">
                            <el-button link type="info" @click="pwdFormVisible = true">Forgot password</el-button>
                        </div>
                    </el-form-item>
                </el-form>

                <el-dialog v-model="pwdFormVisible" width="500">
                    <el-form :model="pwdForm" :rules="pwdRules" ref="pwdFormRef">
                        <el-form-item prop="email" label="email" label-width="100px">
                            <el-input v-model="pwdForm.email" style="width: 250px"/>
                        </el-form-item>
                        <el-form-item prop="oldPassword" label="old password">
                            <el-input v-model="pwdForm.oldPassword" style="width: 250px"/>
                        </el-form-item>
                        <el-form-item prop="newPassword" label="new password">
                            <el-input v-model="pwdForm.newPassword" style="width: 250px"/>
                        </el-form-item>
                    </el-form>
                    <template #footer>
                        <el-button @click="pwdFormVisible = false">Cancel</el-button>
                        <el-button type="primary" @click="pwdFormSubmit">
                            Confirm
                        </el-button>
                    </template>
                </el-dialog>
            </div>
        </div>
    </div>

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

.el-header .el-icon {
    float: right;
    padding-top: 14px;
    padding-right: 10px;
}

/* -----------login------------ */
.login {
    width: 100%;
    height: calc(100vh - 50px);
    background-color: #f5f5f5;
    justify-content: center;
    align-items: center;
    display: flex;
}

.login .wrapper {
    width: 900px;
    height: 400px;
    background-color: #fff;
    border-radius: 10px;
    box-shadow: 0 15px 30px rgba(0, 0, 0, .1);
}

.login .left {
    float: left;
    width: 500px;
    height: 100%;
}

.login .left img {
    height: 400px;
    border-radius: 10px 0 0 10px; /* 左上角和左下角圆角，右上角和右下角没有 */
}

.login .right {
    float: right;
    width: 400px;
    height: 400px;
}

.login .right .form {
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
    height: 400px;
}

.login .form .el-form-item .el-input {
    width: 250px;
    height: 38px;
}

.login .form .el-form-item .button-submit {
    width: 250px;
    height: 34px;
    color: white;
    background-color: #131921;
    margin-top: 10px;
}

.password p {
    margin-left: 10px;
    font-family: "PingFang SC";
}

.password {
    width: 300px;
}

.password .p-left {
    float: left;
    width: 50%;
}

.password .p-right {
    float: right;
    width: 50%;
    text-align: right;
    font-family: "Helvetica Neue";
}

.el-dialog .el-form {
    display: flex;
    justify-content: center;
    align-items: center;
    flex-direction: column;
}

</style>