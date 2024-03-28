<script setup lang="ts">
import {UserFilled} from "@element-plus/icons-vue";
import {reactive, ref} from "vue";

const loginForm = reactive({
    email: '',
    password: ''
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

const loginRef = ref(null)

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
                <el-form :model="loginForm" :rules="loginRules" ref="loginRef" class="form">
                    <img src="../assets/image/amazon.svg" style="width: 200px; margin-bottom: 20px">
                    <el-form-item prop="email">
                        <el-input v-model="loginForm.email" placeholder="email"/>
                    </el-form-item>
                    <el-form-item prop="password">
                        <el-input v-model="loginForm.password" placeholder="password"/>
                    </el-form-item>
                    <el-form-item>
                        <el-button>LOGIN</el-button>
                    </el-form-item>
                </el-form>
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

.login .form .el-form-item .el-button {
    width: 250px;
    height: 34px;
    color: white;
    background-color: #131921;
    margin-top: 10px;
}

/*
.form {
    width: 600px;
    height: 450px;
    background-color: #ffffff;
    border-radius: 10px;
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
}

.form .el-form-item:first-child {
    margin-bottom: 40px;
}
*/

</style>