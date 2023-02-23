<template>
  <div>
    <point-form/>
    <br/><br/>
    <result-table/>
  </div>
</template>

<script>
import PointForm from '@/components/PointForm.vue'
import ResultTable from "@/components/ResultTable";
export default {
  components : {
    PointForm, ResultTable
  }
}
</script>

<style scoped>
* {
  font-family: 'Book antiqua',sans-serif;
  margin: 0;
  padding: 0;
  box-sizing: border-box;
}

</style>