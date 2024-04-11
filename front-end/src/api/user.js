import axios from "axios";

export const getRecommendation = () => {
    return axios({
        method: "GET",
        url: "/api/user"
    })
}

export const getByPid = (pid) => {
    return axios({
        method: "GET",
        url: "/api/user/product",
        params: {
            pid: pid
        }
    })
}

export const getByCatid = (catid) => {
    return axios({
        method: "GET",
        url: "/api/user/category",
        params: {
            catid: catid
        }
    })
}

export const getOrder = () => {
    return axios({
        method: "GET",
        url: "/api/user/order"
    })
}