<template>
  <div>
    <canvas id="graph" height="300" width="300" @click="processGraphClick"></canvas>
    <form name="form">
      <label>X ∈ [-5, 3]</label>
      <br/>
      <span><input type="radio" name="x" value="-5" v-model="x">-5 </span>
      <span><input type="radio" name="x" value="-4" v-model="x">-4 </span>
      <span><input type="radio" name="x" value="-3" v-model="x">-3 </span>
      <span><input type="radio" name="x" value="-2" v-model="x">-2 </span>
      <span><input type="radio" name="x" value="-1" v-model="x">-1 </span>
      <span><input type="radio" name="x" value="0" v-model="x">0 </span>
      <span><input type="radio" name="x" value="1" v-model="x">1 </span>
      <span><input type="radio" name="x" value="2" v-model="x">2 </span>
      <span><input type="radio" name="x" value="3" v-model="x">3 </span>

      <br/>
      <br/>

      <label>Y ∈ [-5, 3]</label>
      <br/>
      <div id="yInput">
        <input type="text" class="y" placeholder="Введите значение Y" v-model="y" @input="replaceValue"/>
      </div>

      <br/>
      <br/>

      <label for="r" @change="changeR">R ∈ [-5, 3]</label>
      <br/>
      <span><input type="radio" name="r" value="-5" v-model="r" @change="changeR">-5 </span>
      <span><input type="radio" name="r" value="-4" v-model="r" @change="changeR">-4 </span>
      <span><input type="radio" name="r" value="-3" v-model="r" @change="changeR">-3 </span>
      <span><input type="radio" name="r" value="-2" v-model="r" @change="changeR">-2 </span>
      <span><input type="radio" name="r" value="-1" v-model="r" @change="changeR">-1 </span>
      <span><input type="radio" name="r" value="0" v-model="r" @change="changeR">0 </span>
      <span><input type="radio" name="r" value="1" v-model="r" @change="changeR">1 </span>
      <span><input type="radio" name="r" value="2" v-model="r" @change="changeR">2 </span>
      <span><input type="radio" name="r" value="3" v-model="r" @change="changeR">3 </span><br>
      <p>{{ errorY }}</p>
      <p>{{ errorR }}</p>
      <br/>
      <br/>
      <my-button @click.prevent @click="validate">Отправить</my-button>
      <br/><br/>
    </form>
  </div>

</template>

<script>
import MyButton from '@/components/UI/MyButton'
import PointService from "@/services/PointService";
import drawer from '@/js/drawer'

export default {
  name: "PointForm",
  components: {MyButton},
  props: ['points'],
  mixins: [drawer],
  data: function () {
    return {
      x: 0,
      y: 0,
      r: 0,
      text: '',
      id: '',
      errorY: '',
      errorR: '',
    }
  },
  mounted() {
    this.x = 1;
    this.r = 1;
    this.draw(1);
  },
  methods: {
    send: function () {
      this.y = this.y.replace(/,/g, '.')
      PointService.setPoint(this.x, this.y, this.r).then(result => {
        console.log("at send: emit")
        this.$emit('addResult', result.data)
      }), () => {
        console.log("at send: ERROR")
      }
    },

    processGraphClick: function (e) {
      let values = this.processClick(e, this.r)
      if (values !== null) {
        console.log('at processGraphClick: '+values[0], values[1])
        this.x = values[0];
        this.y = values[1];
        this.send();
        this.x = parseFloat(values[0]).toFixed(0);
      }
    },
    changeR: function (e) {
      if (e.target.value <= 0) this.errorR = 'Некорректное значение R. Выберите R > 0'
      else {
        // this.draw(e.target.value);
        this.errorR = '';
        this.restoreCanvas(this.r, false);
      }
    },
    changeY: function (e){
      e.target.value = e.target.value.replace(/[^0-9.,-]/g, '');
    },
    validate: function () {
      let isYCorrect = this.checkValue(this.y, this.y_min, this.y_max, false);
      let isRCorrect = this.checkValue(this.r, 1, 3, true);
      this.errorY = (isYCorrect ? '' : 'Некорректное значение Y.');
      this.errorR = (isRCorrect ? '' : 'Некорректное значение R. Выберите R > 0');
      if (isYCorrect && isRCorrect) this.send();
    },
    replaceValue: function (e) {
      e.target.value = e.target.value.replace(/[^0-9.,-]/g, '');
    },
    onDelete: function (){
      this.restoreCanvas(this.r, false);
    }
  }
}
</script>

<style scoped>
canvas {
  border-radius: 5px;
  margin: 10px;
}

input[type="radio"] {
  margin: 0 0 0 10px;
  vertical-align: bottom;
  height: 25px;
}

</style>