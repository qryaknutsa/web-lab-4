<template>
  <button class="btn">
    <slot></slot>
  </button>
</template>

<script>
export default {
  name: "MyButton"
}
</script>

<style scoped>
.btn{
  font-family: 'Book antiqua',sans-serif;
  text-align: center;
  width: 170px;
  height: 30px;
  background: rgba(110,63,26,0.57);
  transition: 0.3s;
}

.btn:hover{
  text-align: center;
  width: 175px;
  height: 35px;
  background: rgba(110,63,26,0.78);
  transition: 0.3s;
}
</style>