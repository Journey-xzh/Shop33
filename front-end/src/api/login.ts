import axios from "axios";

export const login = (formData) => {
    return axios({
        method: "POST",
        url: "/api/login",
        data: formData
    })
}

export const changePassword = (formData) => {
    return axios({
        method: "POST",
        url: "/api/login/password",
        data: formData
    })
}