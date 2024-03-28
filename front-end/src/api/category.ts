import axios from "axios";

export const getAll = () => {
    return axios({
        method: "GET",
        url: "/api/admin/category",
    })
}

export const insertCategory = (name: string) => {
    return axios({
        method: "POST",
        url: "/api/admin/category",
        params: {
            name: name
        }
    })
}

export const deleteCategory = (catid: number) => {
    return axios({
        method: "DELETE",
        url: "/api/admin/category",
        params: {
            catid: catid
        }
    })
}

export const updateCategory = (category: object) => {
    return axios({
        method: "PUT",
        url: "/api/admin/category",
        data: category
    })
}