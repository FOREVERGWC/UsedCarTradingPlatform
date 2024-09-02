import {defineStore} from 'pinia'

const useUserStore = defineStore('user', {
    state: () => ({
        user: JSON.parse(localStorage.getItem('user') || '{}')
    }),
    getters: {
        getUser: (state) => {
            return state.user
        },
        isAdmin: (state) => {
            return state.user?.roleIdList?.includes('1')
        }
    },
    actions: {
        setUser (user) {
            this.user = user
        },
        clearUser () {
            this.user = {}
        }
    },
    persist: true
})

export default useUserStore
