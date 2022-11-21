<template>
  <div>
    <el-tree
      :data="category"
      :props="defaultProps"
      :expand-on-click-node="false"
      node-key="catId"
      :default-expanded-keys="expandedKey"
      draggable
    >
      <span class="custom-tree-node" slot-scope="{ node, data }">
        <span>{{ node.label }}</span>
        <span>
          <el-button
            v-if="node.level <= 2"
            type="text"
            size="mini"
            @click="() => append(data)"
          >
            Append
          </el-button>
          <el-button type="text" size="mini" @click="() => update(data)">
            Update
          </el-button>
          <el-button
            v-if="node.childNodes.length == 0"
            type="text"
            size="mini"
            @click="() => remove(node, data)"
          >
            Delete
          </el-button>
        </span>
      </span>
    </el-tree>
    <el-dialog :title="formTitle" :visible.sync="dialogVisible" width="30%">
      <el-form :model="formProps">
        <el-form-item label="Name">
          <el-input v-model="formProps.name" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="Icon">
          <el-input v-model="formProps.icon" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="Unit">
          <el-input
            v-model="formProps.productUnit"
            autocomplete="off"
          ></el-input>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button @click="closedFormHandle">Cancel</el-button>
        <el-button type="primary" @click="formTypeHandle">Confirm</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script>
export default {
  data() {
    return {
      category: [],
      expandedKey: [],
      dialogVisible: false,
      defaultProps: {
        children: "children",
        label: "name"
      },
      formTitle: "",
      formType: "",
      formProps: {
        name: "",
        parentCid: 0,
        catLevel: null,
        showStatus: 1,
        sort: 0,
        catId: null,
        icon: "",
        productUnit: ""
      }
    };
  },
  methods: {
    getCategory() {
      this.$http({
        url: this.$http.adornUrl("/product/category/list/tree"),
        method: "get"
      }).then(({ data }) => (this.category = data.data));
    },
    append(data) {
      console.log("append", data);
      this.formType = "append";
      this.formTitle = "Appending Category";
      this.dialogVisible = true;
      this.formProps.parentCid = data.catId;
      this.formProps.catLevel = data.catLevel * 1 + 1;
      // reset properties so after update the value doesn't get carry over
      this.formProps.catId = null;
      this.formProps.name = "";
      this.formProps.icon = "";
      this.formProps.productUnit = "";
    },
    addCategoryHandle() {
      console.log("Appending Category", this.formProps);
      this.$http({
        url: this.$http.adornUrl("/product/category/save"),
        method: "post",
        data: this.$http.adornData(this.formProps, false)
      }).then(({ data }) => {
        this.$message({
          message: `${this.formProps.name} has been appended.`,
          type: "success"
        });
        this.closedFormHandle();
        this.getCategory();
        this.expandedKey = [this.formProps.parentCid];
      });
    },
    update(data) {
      console.log(data);
      this.formType = "update";
      this.formTitle = "Updating Category";
      this.dialogVisible = true;
      // get the latest data to display on the form
      this.$http({
        url: this.$http.adornUrl(`/product/category/info/${data.catId}`),
        method: "get"
      }).then(({ data }) => {
        this.formProps.name = data.data.name;
        this.formProps.catId = data.data.catId;
        this.formProps.icon = data.data.icon;
        this.formProps.productUnit = data.data.productUnit;
        this.formProps.parentCid = data.data.parentCid;
      });
    },
    updateCategoryHandle() {
      var { catId, name, icon, productUnit } = this.formProps;
      var data = { catId, name, icon, productUnit };
      this.$http({
        url: this.$http.adornUrl("/product/category/update"),
        method: "post",
        data: this.$http.adornData(data, false)
      }).then(({ data }) => {
        this.$message({
          message: `${this.formProps.name} has been updated.`,
          type: "success"
        });
        this.closedFormHandle();
        this.getCategory();
        this.expandedKey = [this.formProps.parentCid];
      });
    },
    remove(node, data) {
      var ids = [data.catId];
      this.$confirm(`You are deleting ${data.name}`, "Warning", {
        confirmButtonText: "Confirm",
        cancelButtonText: "Cancel",
        type: "warning"
      })
        .then(() => {
          this.$message({
            type: "success",
            message: `${data.name} has been deleted.`
          });
        })
        .then(() => {
          this.$http({
            url: this.$http.adornUrl("/product/category/delete"),
            method: "post",
            data: this.$http.adornData(ids, false)
          }).then(({ data }) => {
            this.getCategory();
            this.expandedKey = [node.parent.data.catId];
          });
        })
        .catch(() => {
          this.$message({
            type: "info",
            message: "Delete Cancel"
          });
        });
      console.log("remove", node, data);
    },
    closedFormHandle() {
      this.dialogVisible = false;
    },
    formTypeHandle() {
      if (this.formType == "append") {
        this.addCategoryHandle();
      }
      if (this.formType == "update") {
        this.updateCategoryHandle();
      }
    }
  },
  created() {
    this.getCategory();
  }
};
</script>
<style></style>
