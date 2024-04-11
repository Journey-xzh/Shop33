<script setup>
import axios from "axios"
// import PayPal from "@/components/PayPal.vue";

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
        return config
    }
})
</script>

<template>
<!--    <PayPal></PayPal>-->
    <router-view :key="$route.fullPath"></router-view>
</template>

<style scoped>
</style>
