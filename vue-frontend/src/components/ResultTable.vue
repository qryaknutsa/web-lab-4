<template>
  <div>
    <point-form :points="points" @addResult="addRow"/>
    <my-button @click="deleteTable">Очистить таблицу</my-button>
    <br/><br/>
    <table>
      <thead>
      <th>X</th>
      <th>Y</th>
      <th>R</th>
      <th>Результат</th>
      <th>Время проверки</th>
      <th>Дата создания</th>
      </thead>
      <tbody>
      <tr v-for="point in points" :key="point.id">
        <td>{{ point.x.toFixed(2) }}</td>
        <td>{{ point.y.toFixed(2) }}</td>
        <td>{{ point.r.toFixed(2) }}</td>
        <td>{{ point.result ? 'Попал' : 'Не попал' }}</td>
        <td>{{ point.time.toFixed(3) }}</td>
        <td>{{ point.creationDate }}</td>
      </tr>
      </tbody>
    </table>
  </div>
</template>

<script>
import PointService from "@/services/PointService";
import PointForm from "@/components/PointForm";
import MyButton from '@/components/UI/MyButton'

export default {
  name: "ResultTable",
  components: {PointForm, MyButton},
  props: {
    points: {
      type: Array,
      required: true
    }
  },
  methods: {
    getPoints: function () {
      return PointService.getPoints().then((response) => {
        response.data.forEach(point => this.points.unshift(point));
      });
    },
    addRow: function (point) {

      this.points.unshift(point)
    },

    deleteTable: function (){
      PointService.deleteTable().then(() => {
        while(this.points.length > 0) this.points.pop();
        this.restoreCanvas(this.$refs.Form.r);
      })
    }
  },
  created() {
    this.getPoints();
  }
}
</script>

<style scoped>
table {
  text-align: center;
  border-collapse: collapse;
  border-radius: 50px;
  width: 100%;
  margin: auto;
  border-right: 2px solid rgba(110, 63, 26, 0.57);
  border-top: 2px solid rgba(110, 63, 26, 0.57);
  border-bottom: 2px solid rgba(110, 63, 26, 0.57);
  border-left: 2px solid rgba(110, 63, 26, 0.57);
}
</style>