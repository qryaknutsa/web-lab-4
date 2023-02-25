<template>
  <div>
    <result-table :points="points"/>
    <br/><br/>
  </div>
</template>

<script>
import PointForm from '@/components/PointForm.vue'
import ResultTable from "@/components/ResultTable";

export default {
  components: {
    PointForm, ResultTable
  },
  data: function () {
    return {
      points: []
    }
  },
  watch:{

  }
}
</script>

<style>
*{
  font-family: "OCR A Extended", monospace;
  font-size: large;
  background-color: #FFDAB9;
  margin: 0;
  padding: 0;
  box-sizing: border-box;
}

.container{
  max-width: 550px;
  margin: auto;
  text-align: center;
  border-radius: 8px;
}
</style>