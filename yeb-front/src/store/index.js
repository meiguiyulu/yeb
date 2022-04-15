import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

export default new Vuex.Store({
    state: {
        routes: [],
        currentAdmin: JSON.parse(window.sessionStorage.getItem('user')), // 当前用户
    },
    getters: {},
    mutations: {
        initRoutes(state, data) {
            state.routes = data;
        }
    },
    actions: {},
    modules: {}
})
