import axios from "axios";

export const getRecommendation = () => {
    return axios({
        method: "GET",
        url: "/api/tourist"
    })
}

export const getByPid = (pid) => {
    return axios({
        method: "GET",
        url: "/api/tourist/product",
        params: {
            pid: pid
        }
    })
}

export const getByCatid = (catid) => {
    return axios({
        method: "GET",
        url: "/api/tourist/category",
        params: {
            catid: catid
        }
    })
}