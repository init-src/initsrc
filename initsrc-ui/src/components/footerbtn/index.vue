<template>
  <div class="dshfn-footer-btn" :style="THEAM.ISDEVICE==1||THEAM.ISCROSS?'left:0px':THEAM.ISCOLLAPSE?'left:60px':'200px'">
    <template v-for="(btn, key) in option">
      <div v-if="key < 3">
        <div class="op-item" v-if="(btn.show != true && btn.show !=false)?btn.show():btn.show" :key="btn.id">
          <el-button :type="btn.type" :size="btn.size" :icon="btn.icon" :disabled="(btn.disabled != true && btn.disabled !=false)?btn.disabled():btn.disabled"
            :plain="btn.plain" @click.native.prevent="btn.method()" :loading="btn.loading">{{ btn.label }}</el-button>
        </div>
      </div>
    </template>
  </div>
</template>

<script>
  export default {
    props: {
      option: {
        type: Array,
        default: () => [] //按钮集合 === label: 文本，type :类型（primary / success / warning / danger / info / text），show：是否显示，icon：按钮图标，plain：是否朴素按钮，disabled：是否禁用，method：回调方法
      },
    },
    data() {
      return {
        THEAM: {},
      }
    },
    mounted() {
      this.THEAM = this.$store.state.ts
    },
    methods: {

    }
  }
</script>

<style scoped>
  .op-item {
    float: left;
    padding-left: 20px;
  }
</style>
