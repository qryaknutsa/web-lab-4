<template>
  <div class="root">
    <form name="form">
      <label>X ∈ [-5, 3]</label>
      <br/>
      -5 &nbsp;&nbsp;&nbsp;-4 &nbsp;&nbsp;&nbsp; -3 &nbsp;&nbsp; -2 &nbsp;&nbsp;&nbsp;-1 &nbsp;&nbsp;&nbsp; 0 &nbsp;&nbsp;&nbsp;  1 &nbsp;&nbsp;&nbsp; 2 &nbsp;&nbsp;&nbsp; 3
      <div id="xRadios">
        <input type="radio" name="x" value="-5" v-model="x"/>&nbsp;
        <input type="radio" name="x" value="-4" v-model="x"/>&nbsp;
        <input type="radio" name="x" value="-3" v-model="x"/>&nbsp;
        <input type="radio" name="x" value="-2" v-model="x"/>&nbsp;
        <input type="radio" name="x" value="-1" v-model="x"/>&nbsp;
        <input type="radio" name="x" value="0" v-model="x"/>&nbsp;
        <input type="radio" name="x" value="1" v-model="x"/>&nbsp;
        <input type="radio" name="x" value="2" v-model="x"/>&nbsp;
        <input type="radio" name="x" value="3" v-model="x"/>
      </div>

      <br/>
      <br/>

      <label>Y ∈ [-5, 3]</label>
      <br/>
      <div id="yInput">
        <input type="number" class="y" placeholder="Введите значение Y" v-model="y"/>
      </div>

      <br/>
      <br/>

      <label>R ∈ [-5, 3]</label>
      <br/>
      -5 &nbsp;&nbsp;&nbsp;-4 &nbsp;&nbsp;&nbsp; -3 &nbsp;&nbsp; -2 &nbsp;&nbsp;&nbsp;-1 &nbsp;&nbsp;&nbsp; 0 &nbsp;&nbsp;&nbsp;  1 &nbsp;&nbsp;&nbsp; 2 &nbsp;&nbsp;&nbsp; 3
      <div id="rRadios">
        <input type="radio" name="r" value="-5" v-model="r"/>&nbsp;
        <input type="radio" name="r" value="-4" v-model="r"/>&nbsp;
        <input type="radio" name="r" value="-3" v-model="r"/>&nbsp;
        <input type="radio" name="r" value="-2" v-model="r"/>&nbsp;
        <input type="radio" name="r" value="-1" v-model="r"/>&nbsp;
        <input type="radio" name="r" value="0" v-model="r"/>&nbsp;
        <input type="radio" name="r" value="1" v-model="r"/>&nbsp;
        <input type="radio" name="r" value="2" v-model="r"/>&nbsp;
        <input type="radio" name="r" value="3" v-model="r"/>
      </div>

      <br/>
      <br/>
      <my-button @click.prevent @click="send">Отправить</my-button>
    </form>
  </div>
</template>

<script>
import MyButton from '@/components/UI/MyButton'
import PointService from "@/services/PointService";
import pointService from "@/services/PointService";


export default {
  name: "PointForm",
  components: {MyButton},
  data: function () {
    return {
      x: 0,
      y: 0,
      r: 0
    }
  },
  methods: {
    send: function () {
      console.log("x = " + this.x + "\n")
      console.log("y = " + this.y + "\n")
      console.log("r = " + this.r + "\n")

      pointService.setPoint(this.x, this.y, this.r)

    }
  }
}
</script>

<style scoped>

</style>