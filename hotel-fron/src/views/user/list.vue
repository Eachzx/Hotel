<template>
  <div>
    <el-button type="primary" @click="showHandle()">新增</el-button>

    <el-table
        :highlight-current-row=true
        :data="tableData"
        border
    >
      <el-table-column
          fixed
          label="账号"
          prop="account"
          width="150">
      </el-table-column>
      <el-table-column
          label="姓名"
          prop="name"
          width="120">
      </el-table-column>
      <el-table-column
          label="性别"
          prop="sex"
          width="120">
      </el-table-column>
      <el-table-column
          label="电话"
          prop="phone"
          width="200">
      </el-table-column>
      <el-table-column
          label="邮箱"
          prop="email"
          width="120">
      </el-table-column>
      <el-table-column
          label="操作"
          width="100">
        <template slot-scope="scope">
          <el-button size="small" type="text" @click="showHandle(scope.row)">编辑</el-button>
          <el-button size="small" type="text" @click="deleteHandle(scope.row)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    <edit-model @getUserList="getUserList" ref="edit"></edit-model>
  </div>
</template>

<script>
import editModel from './editModel.vue'

export default {
  components: {
    editModel
  },
  created() {
    this.getUserList()
  },
  methods: {
    showHandle(row) {
      this.$refs.edit.showDialog(row)
    },
    getUserList() {
      debugger
      this.$http.get('/user/getUserList').then(res => {
        if (res.status == 200) {
          debugger
          this.tableData = res.data
        }
      })
    },
    deleteHandle(row){
      this.$http.delete('/user/delUserById', {params: {id: row.id}}).then(res => {
        if (res.data) {
          this.$message({
            message: '删除成功',
            type: 'success'
          });
          this.getUserList()
        }else {
          this.$message({
            message: '删除失败',
            type: 'error'
          });
        }
      })
    }
  },

  data() {
    return {
      tableData: []
    }
  }
}
</script>