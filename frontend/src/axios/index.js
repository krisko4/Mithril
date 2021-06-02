import axios from 'axios'

const tokenAxios = axios.create({
    baseURL: 'http://localhost:8080',

})

tokenAxios.interceptors.request.use(
    function (config) {
        config.headers.common['Authorization'] = 'Bearer ' + localStorage.getItem('user')
        return config
    })

export{
    tokenAxios
}

export default axios.create({
    baseURL: 'http://localhost:8080'
})


