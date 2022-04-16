<template>
  <div>
    <el-tabs v-model="activeName" type="card" @tab-click="">
      <el-tab-pane label="部门管理" name="DepMana"><DepMana/></el-tab-pane>
      <el-tab-pane label="职位管理" name="PosMana"><PosMana/></el-tab-pane>
      <el-tab-pane label="职称管理" name="JobMana"><JobMana/></el-tab-pane>
      <el-tab-pane label="奖惩规则" name="EcMana"><EcMana/></el-tab-pane>
      <el-tab-pane label="权限组" name="PositionMana"><PermissMana/></el-tab-pane>
    </el-tabs>
  </div>
</template>
<script>
import DepMana from "@/components/sys/basic/DepMana";  // 部门管理
import EcMana from "@/components/sys/basic/EcMana"; // 奖惩规则
import JobMana from "@/components/sys/basic/JobleMana"; // 职称管理
import PermissMana from "@/components/sys/basic/PermissMana"; // 权限组
import PosMana from "@/components/sys/basic/PosMana"; // 职位管理

export default {
  name: "SysBasic",
    components:{
      JobMana,
      DepMana,
      EcMana,
      PermissMana,
      PosMana
    },
  data() {
    return {
      activeName: 'PosMana' // 激活项
    }
  },
  methods: {}
}
</script>

<style scoped>

</style>
