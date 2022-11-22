<template>
  <el-dialog
    :title="!dataForm.brandId ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible"
  >
    <el-form
      :model="dataForm"
      :rules="dataRule"
      ref="dataForm"
      @keyup.enter.native="dataFormSubmit()"
      label-width="140px"
    >
      <el-form-item label="Name" prop="name">
        <el-input v-model="dataForm.name" placeholder="Name"></el-input>
      </el-form-item>
      <el-form-item label="Logo" prop="logo">
        <!-- <el-input v-model="dataForm.logo" placeholder="品牌logo地址"></el-input> -->
        <single-upload v-model="dataForm.logo"></single-upload>
      </el-form-item>
      <el-form-item label="Description" prop="descript">
        <el-input
          v-model="dataForm.descript"
          placeholder="Description"
        ></el-input>
      </el-form-item>
      <el-form-item label="ShowStatus" prop="showStatus">
        <el-switch
          v-model="dataForm.showStatus"
          active-color="#13ce66"
          :active-value="1"
          inactive-color="#ff4949"
          :inactive-value="0"
        >
        </el-switch>
      </el-form-item>
      <el-form-item label="FirstLetter" prop="firstLetter">
        <el-input
          v-model="dataForm.firstLetter"
          placeholder="FirstLetter"
        ></el-input>
      </el-form-item>
      <el-form-item label="Sort" prop="sort">
        <el-input v-model.number="dataForm.sort" placeholder="Sort"></el-input>
      </el-form-item>
    </el-form>
    <span slot="footer" class="dialog-footer">
      <el-button @click="visible = false">Cancel</el-button>
      <el-button type="primary" @click="dataFormSubmit()">Confirm</el-button>
    </span>
  </el-dialog>
</template>

<script>
import singleUpload from "../../../components/upload2oss/singleUpload.vue";
export default {
  components: { singleUpload },
  data() {
    return {
      visible: false,
      dataForm: {
        brandId: 0,
        name: "",
        logo: "",
        descript: "",
        showStatus: "",
        firstLetter: "",
        sort: ""
      },
      dataRule: {
        name: [
          {
            required: true,
            message: "Please Fill in Brand Name",
            trigger: "blur"
          }
        ],
        logo: [
          { required: true, message: "Please Upload a Logo", trigger: "blur" }
        ],
        descript: [
          {
            required: true,
            message: "Please Fill in Description",
            trigger: "blur"
          }
        ],
        showStatus: [
          {
            required: true,
            message: "On/Off",
            trigger: "blur"
          }
        ],
        firstLetter: [
          {
            validator: (rule, value, callback) => {
              if (value == "") {
                callback(new Error("Please Fill in First Letter"));
              } else if (!/^[a-zA-Z]$/.test(value)) {
                callback(new Error("Please Fill in a Letter"));
              } else {
                callback();
              }
            },
            trigger: "blur",
            required: true
          }
        ],
        sort: [
          {
            validator: (rule, value, callback) => {
              if (value == "") {
                callback(new Error("Please Fill in Sort"));
              } else if (!Number.isInteger(value) || value < 0) {
                callback(new Error("Please Fill in a Non-negative Integer"));
              } else {
                callback();
              }
            },
            required: true,
            trigger: "blur"
          }
        ]
      }
    };
  },
  methods: {
    init(id) {
      this.dataForm.brandId = id || 0;
      this.visible = true;
      this.$nextTick(() => {
        this.$refs["dataForm"].resetFields();
        if (this.dataForm.brandId) {
          this.$http({
            url: this.$http.adornUrl(
              `/product/brand/info/${this.dataForm.brandId}`
            ),
            method: "get",
            params: this.$http.adornParams()
          }).then(({ data }) => {
            if (data && data.code === 0) {
              this.dataForm.name = data.brand.name;
              this.dataForm.logo = data.brand.logo;
              this.dataForm.descript = data.brand.descript;
              this.dataForm.showStatus = data.brand.showStatus;
              this.dataForm.firstLetter = data.brand.firstLetter;
              this.dataForm.sort = data.brand.sort;
            }
          });
        }
      });
    },
    // 表单提交
    dataFormSubmit() {
      this.$refs["dataForm"].validate(valid => {
        if (valid) {
          this.$http({
            url: this.$http.adornUrl(
              `/product/brand/${!this.dataForm.brandId ? "save" : "update"}`
            ),
            method: "post",
            data: this.$http.adornData({
              brandId: this.dataForm.brandId || undefined,
              name: this.dataForm.name,
              logo: this.dataForm.logo,
              descript: this.dataForm.descript,
              showStatus: this.dataForm.showStatus,
              firstLetter: this.dataForm.firstLetter,
              sort: this.dataForm.sort
            })
          }).then(({ data }) => {
            if (data && data.code === 0) {
              this.$message({
                message: "Submit Success",
                type: "success",
                duration: 1500,
                onClose: () => {
                  this.visible = false;
                  this.$emit("refreshDataList");
                }
              });
            } else {
              this.$message.error(data.msg);
            }
          });
        }
      });
    }
  }
};
</script>
