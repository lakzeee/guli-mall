<template>
  <div>
    <el-tree
      :data="category"
      :props="defaultProps"
      :expand-on-click-node="false"
      show-checkbox
      node-key="catId"
      :default-expanded-keys="expandedKey"
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
    <el-dialog title="Confirm" :visible.sync="dialogVisible" width="30%">
      <el-form :model="formProps">
        <el-form-item label="Category Name">
          <el-input v-model="formProps.name" autocomplete="off"></el-input>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button @click="closedFormHandle">Cancel</el-button>
        <el-button type="primary" @click="addCategoryHandle">Confirm</el-button>
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
      formProps: { name: "", parentCid: 0, catLevel: 0, showStatus: 1, sort: 0 }
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
      this.dialogVisible = true;
      this.formProps.parentCid = data.catId;
      this.formProps.catLevel = data.catLevel * 1 + 1;
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
    }
  },
  created() {
    this.getCategory();
  }
};
</script>
<style></style>
