<template>
  <div>
  <el-dialog title="编辑" :visible.sync="dialogTableVisible">
    <el-form :model="userForm" :rules="rules" ref="userForm" label-width="100px" class="demo-ruleForm">
      <el-row>
        <el-col :span="12">
          <el-form-item label="账户" prop="account">
            <el-input v-model="userForm.account"></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="姓名" prop="name">
            <el-input v-model="userForm.name"></el-input>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="12">
          <el-form-item label="性别" prop="sex">
            <el-select v-model="userForm.sex" placeholder="请选择性别">
              <el-option label="男" value="1"></el-option>
              <el-option label="女" value="0"></el-option>
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="电话" prop="phone">
            <el-input v-model="userForm.phone"></el-input>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="12">
          <el-form-item label="密码" prop="password">
            <el-input v-model="userForm.password"></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="邮箱" prop="email">
            <el-input v-model="userForm.email"></el-input>
          </el-form-item>
        </el-col>
      </el-row>
    </el-form>
    <span slot="footer" class="dialog-footer">
    <el-button @click="dialogTableVisible = false">取 消</el-button>
    <el-button type="primary" @click="submitData()">确 定</el-button>
  </span>
  </el-dialog>
  </div>
</template>
<script>
export default {
  data() {
    return {
      dialogTableVisible: false,
      userForm: {
        account:'',
        name: '',
        sex: '',
        phone:'',
        password:'',
        email:''
      },
      rules: {
        account: [
          {required: true, message: '请输入账户', trigger: 'blur'},
          {min: 3, max: 5, message: '长度在 3 到 5 个字符', trigger: 'blur'}
        ],
        name: [
          {required: true, message: '请输入姓名', trigger: 'blur'},
        ],
        sex: [
          {required: true, message: '请选择性别', trigger: 'change'}
        ],
        password: [
          {required: true, message: '请输入密码', trigger: 'blur'},
        ],

      }
    };
  },
  methods: {
    showDialog(row) {
      this.dialogTableVisible=true
      //row有数据是修改
      if(row){
        this.userForm=row
      }else {
        this.userForm={}
      }

    },

    submitData(){
      this.$refs['userForm'].validate((valid) => {
        if (valid) {
          //id不等空 说明是数据库中的数据,就是进行修改 因为前端不生成id,是后端自动填充的
          if (this.userForm.id != undefined){
            //修改
            this.$http.put('/user/editUser',this.userForm).then(res=>{
              if(res.status==200){
                this.$message({
                  message: '修改成功',
                  type: 'success'
                });
              }else {
                this.$message({
                  message: '内部出错',
                  type: 'warning'
                });
              }
            })
          }else {
            //添加
            this.$http.post('/user/addUser',this.userForm).then(res=>{
              if(res.status==200){
                this.$message({
                  message: '添加成功',
                  type: 'success'
                });
              }else {
                this.$message({
                  message: '内部出错',
                  type: 'warning'
                });
              }
            })
          }
          this.$emit('getUserList')
          this.dialogTableVisible=false
        } else {
          console.log('error submit!!');
        }
      });
    },
    resetForm(formName) {
      this.$refs[formName].resetFields();
    }
  }
}
</script>