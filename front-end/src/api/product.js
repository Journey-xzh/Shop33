import axios from "axios";

export const getAllProduct = () => {
    return axios({
        method: "GET",
        url: "/api/admin/product",
    })
}

export const insertProduct = (formData) => {
    return axios({
        method: "POST",
        url: "/api/admin/product",
        data: formData
    })
}

export const deleteProduct = (pid) => {
    return axios({
        method: "DELETE",
        url: "/api/admin/product",
        params: {
            pid: pid
        }
    })
}

export const updateProduct = (formData) => {
    return axios({
        method: "PUT",
        url: "/api/admin/product",
        data: formData
    })
}