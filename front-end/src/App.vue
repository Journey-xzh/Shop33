<script setup>
import axios from "axios"

axios.interceptors.request.use((config) => {
    const url = config.url
    const token = localStorage.getItem("token")
    if (url?.includes("login")) {
        return config
    } else {
        if (token) {
            config.headers.Authorization = `Bearer ${token}`
            return config
        }
    }
})
</script>

<template>
    <!--        <RouterView></RouterView>-->
    <router-view :key="$route.fullPath"></router-view>
</template>

<style scoped>
</style>
