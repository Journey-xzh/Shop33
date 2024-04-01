import axios from "axios";

export const getAll = () => {
    return axios({
        method: "GET",
        url: "/api/admin/category",
    })
}

export const insertCategory = (name) => {
    return axios({
        method: "POST",
        url: "/api/admin/category",
        params: {
            name: name
        }
    })
}

export const deleteCategory = (catid) => {
    return axios({
        method: "DELETE",
        url: "/api/admin/category",
        params: {
            catid: catid
        }
    })
}

export const updateCategory = (category) => {
    return axios({
        method: "PUT",
        url: "/api/admin/category",
        data: category
    })
}