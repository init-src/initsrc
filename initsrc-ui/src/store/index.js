import Vue from 'vue'
import Vuex from 'vuex'
import templateStore from './templateStore.js'
import permStore from './permStore.js'
Vue.use(Vuex)

export default new Vuex.Store({
  modules:{
    ts:templateStore,
    ps:permStore,
  }
})
