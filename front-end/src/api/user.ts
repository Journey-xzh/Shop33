import axios from "axios";

export const getRecommendation = () => {
    return axios({
        method: "GET",
        url: "/api/user"
    })
}

export const getByPid = (pid: number) => {
    return axios({
        method: "GET",
        url: "/api/user/product",
        params: {
            pid: pid
        }
    })
}

export const getByCatid = (catid: number) => {
    return axios({
        method: "GET",
        url: "/api/user/category",
        params: {
            catid: catid
        }
    })
}