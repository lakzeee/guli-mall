<template>
  <el-tree
    :data="category"
    :props="defaultProps"
    :expand-on-click-node="false"
    show-checkbox
    node-key
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
</template>

<script>
export default {
  data() {
    return {
      category: [],
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
    append(data) {
      console.log("append", data);
    },

    remove(node, data) {
      console.log("remove", data);
    }
  },
  created() {
    this.getCategory();
  }
};
</script>
<style></style>
