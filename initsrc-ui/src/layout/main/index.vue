<template>
    <el-main>
       <is-header-tabs></is-header-tabs>
     <keep-alive>
        <router-view v-if="$route.meta.isCache"></router-view>
      </keep-alive>
      <router-view v-if="!$route.meta.isCache"></router-view>
    </el-main>
</template>

<script>
</script>

<style scoped>

</style>
