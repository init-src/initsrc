import isHeader from "@/layout/header/index.vue"
import isCrossHeader from "@/layout/header/header.vue"
import isMenu from "@/layout/menu/index.vue"
import isMain from "@/layout/main/index.vue"
import isHtabs from "@/layout/tabs/index.vue"
const InitSrcLayout = {
  install: function(Vue) {
    Vue.component("is-cross-header", isCrossHeader)
    Vue.component("is-header", isHeader)
    Vue.component("is-menu", isMenu)
    Vue.component("is-main", isMain)
    Vue.component("is-header-tabs", isHtabs)
  }
}
export default InitSrcLayout
