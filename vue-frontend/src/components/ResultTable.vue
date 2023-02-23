<template>
  <div>
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
        <td>{{ point.x }}</td>
        <td>{{ point.y }}</td>
        <td>{{ point.r }}</td>
        <td>{{ point.result }}</td>
        <td>{{ point.time }}</td>
        <td>202020020220</td>
      </tr>
      </tbody>
    </table>
  </div>
</template>

<script>
import PointService from "@/services/PointService";

export default {
  name: "ResultTable",
  data: function () {
    return {
      points: [],
    }
  },
  methods: {
    getPoints: function () {
      PointService.getPoints().then((response) => {
        this.points = response.data;
      });
    }
  },
  mounted(){
    this.getPoints();
  }
}
</script>

<style scoped>

</style>