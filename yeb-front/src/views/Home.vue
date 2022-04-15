<template>
  <div>
    <el-container>
      <el-header>标题</el-header>
      <el-container>
        <!--侧边栏-->
        <el-aside width="200px">
          <el-menu router unique-opened>
            <el-submenu :index="index + ''" v-for="(item, index) in routes"
                        :key="index"
                        v-if="!item.hidden">
              <template slot="title">
                <i :class="item.iconCls" style="margin-right: 5px;"></i>
                <span>{{ item.name }}</span>
              </template>
              <el-menu-item-group>
                <el-menu-item :index="children.path" v-for="(children, index) in item.children">
                  {{ children.name }}
                </el-menu-item>
              </el-menu-item-group>
            </el-submenu>
          </el-menu>
        </el-aside>

        <el-container>
          <el-main>
            内容
            <router-view/>
          </el-main>
        </el-container>
      </el-container>
    </el-container>
  </div>
</template>

<script>
export default {
  name: "Home",
  computed: {
    routes() {
      return this.$store.state.routes;
    }
  },
  methods: {
    /*    menuClick(index) {
          this.$router.push(index);
        }*/
  }
}
</script>

<style scoped>

</style>