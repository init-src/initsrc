import iTable from '@/components/table/index.vue'
import isFooter from "@/components/footerbtn/index.vue"
import iTreeSelect from '@riophae/vue-treeselect'
import EditorBar from '@/components/enduit/wangEnduit.vue'

const InitSrcComp = {
  install: function(Vue) {
    Vue.component("i-table", iTable)
    Vue.component("is-footer-btn", isFooter)
    Vue.component('i-tree-select',iTreeSelect)
    Vue.component('editor-bar',EditorBar)
  }
}
export default InitSrcComp
