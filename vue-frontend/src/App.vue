<template>
  <div>
    <div class="header">
      <br/>
      <p class="name">Батомункуева Виктория. P32101.</p>
      <p class="name">Вариант 223.</p>
    </div>

    <div v-if="!this.authorized"><enter-form :authorized="authorized" @switchAuth="switchAuth" @onReg="setLogin"/></div>
    <div v-else-if="this.authorized"><result-table :points="points" :login="login" @switchAuth="switchAuth"/></div>
  </div>
</template>

<script>
import PointForm from '@/components/PointForm.vue'
import ResultTable from "@/components/ResultTable";
import EnterForm from "@/components/EnterForm";

export default {
  components: {
    PointForm, ResultTable, EnterForm
  },
  data: function () {
    return {
      points: [],
      authorized: false,
      login: ''
    }
  },
  methods: {
    switchAuth: function (bool){
      this.authorized = bool
    },
    setLogin: function (login){
      this.login = login
    }
  }
}
</script>

<style>
.name {
  color: black;
  font-size: 22px;
}
*{
  border-radius: 10px;
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