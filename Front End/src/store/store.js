import Vue from 'vue'
import Vuex from 'vuex'
import axios from 'axios'

Vue.use(Vuex)

export default new Vuex.Store({
    state: {
        status: '',
        token: '',
        user: {},
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
                        localStorage.setItem('role', resp.data.roles)
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
        authStatus: state => state.status,
        getJwtToken: state => {return state.token},
        getEmail: state =>  state.user.email,
        isUser: (state) => {
            return new Promise((req, res) => {
                axios.get('http://localhost:8081/api/test/user', {
                    headers: {
                        Authorization: `${state.token}`
                    }
                    }).then((response) => {
                        return true;
                    }, (error) => {
                        localStorage.removeItem('token')
                        localStorage.removeItem('user')
                        return false;
                    });
            })
        },
        isAdmin: (state) => {
            return new Promise((req, res) => {
                axios.get('http://localhost:8081/api/test/admin', {
                    headers: {
                        Authorization: `${state.token}`
                    }
                    }).then((response) => {
                        return true;
                    }, (error) => {
                        localStorage.removeItem('token')
                        localStorage.removeItem('user')
                        return false;
                    });
            })
        },
    }
})