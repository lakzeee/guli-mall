<template>
  <div>
    <el-tree
      :data="category"
      :props="defaultProps"
      :expand-on-click-node="false"
      node-key="catId"
      ref="category tree"
      @node-click="nodeClick"
    >
    </el-tree>
  </div>
</template>
<script>
export default {
  data() {
    return {
      category: [],
      expandedKey: [],
      defaultProps: {
        children: "children",
        label: "name"
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
    nodeClick(data, node, component) {
      // console.log("Clicking category tree", data, node, component);
      this.$emit("emit-to-parent", data, node, component);
    }
  },

  created() {
    this.getCategory();
  }
};
</script>
<style></style>
