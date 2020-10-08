import Vue from 'vue'
import Vuex from 'vuex'
import axios from 'axios'

Vue.use(Vuex)

export default new Vuex.Store({
    state: {
        status: '',
        token: localStorage.getItem('token') || '',
        user: localStorage.getItem('user') || {},
    },
    mutations: {
        auth_request(state) {
            state.status = 'loading'
        },
        auth_success(state, token) {
            state.status = 'success'
            state.token = token
        },
        auth_error(state) {
            state.status = 'error'
        },
        logout(state) {
            state.status = ''
            state.token = ''
            state.user = {}
        },
        save_user(state, user) {
            state.status = 'userSaved'
            state.user = user
        }
 
    },
    actions: {
        login({ commit }, user) {
            return new Promise((resolve, reject) => {
                commit('auth_request')
                axios({ url: 'http://localhost:8081/user/login', data: user, method: 'POST' })
                    .then(resp => {
                        const token = "Bearer " + resp.data.accessToken;
                        const user = resp.data
                        localStorage.setItem('token', token)
                        localStorage.setItem('user', user)
                        commit('save_user', user)
                        axios.defaults.headers.common['Authorization'] = token
                        commit('auth_success', token)
                        resolve(resp)
                    })
                    .catch(err => {
                        commit('auth_error')
                        localStorage.removeItem('token')
                        localStorage.removeItem('user')
                        reject(err)
                    })
            })
        },
        register({ commit }, user) {
            return new Promise((resolve, reject) => {
                axios({ url: 'http://localhost:8081/user/create', data: user, method: 'POST' })
                    .then(resp => {
                        resolve(resp)
                    })
                    .catch(err => {
                        localStorage.removeItem('token')
                        localStorage.removeItem('user')
                        reject(err)
                    })
            })
        },
        logout({ commit }) {
            return new Promise((resolve, reject) => {
                commit('logout')
                localStorage.removeItem('token')
                localStorage.removeItem('user')
                delete axios.defaults.headers.common['Authorization']
                resolve()
            })
        }

    },
    getters: {
        isLoggedIn: state => !!state.token,
        authStatus: state => state.status,
        isAdmin: state => {
            if (state.user.roles == undefined) {
                return false;
            } else {
                return state.user.roles[0] === "ROLE_ADMIN";
            }
        },
        isUser: state => {
            if (state.user.roles == undefined) {
                return false;
            } else {
            return state.user.roles[0] == "ROLE_USER";
            }
        },
        getJwtToken: state => state.token,
        getEmail: state =>  state.user.email,
    }
})