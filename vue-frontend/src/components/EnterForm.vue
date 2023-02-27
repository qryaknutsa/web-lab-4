<template>
  <div>
    <br/>
    Войдите или зарегистрируйтесь
    <br/><br/>
    <div>
      <label>Логин</label>
      <br/>
      <input type="text" id="login" maxlength="15" title="Введите логин из не более 15 символов" v-model="login"
             @input="validateLength"/><br>

      <br/>

      <label>Пароль</label><br/>
      <div class="password">
        <input type="password" id="password" maxlength="15" @input="validateLength" v-model="password"/>
        <button class="show_button" @click="showPassword">
          <img id="passwordImage" src="../../public/hide.png" width="20" height="20"/>
        </button>
      </div>
      <br/>
    </div>

    <div v-if="!this.isRegistration">
      <br/>
      <my-button id="enter" @click="toEnter">Войти в аккаунт</my-button>
      <br/><br/>
      <my-button @click="switchToRegistration">Создать аккаунт</my-button>
    </div>


    <div v-else>
      <div>
        <label for="password2">Повторите пароль</label>
        <br/>
        <div class="password">
          <input type="password" id="password2" maxlength="15" v-model="passwordConfirm"/>
          <button class="show_button" @click="showPassword">
            <img id="passwordImage2" src="../../public/hide.png" width="20" height="20"/>
          </button>
        </div>
        <br/>
      </div>
      <br/>
      <my-button @click="switchToRegistration">Назад</my-button>
      <br/><br/>
      <my-button id="register" @click="toRegister">Зарегистрироваться</my-button>
    </div>

    <br/>
    <p>{{ message }}</p>
    <img id="hideImg" hidden src="../../public/hide.png"/>
    <img id="showImg" hidden src="../../public/show.png"/>
  </div>
</template>

<script>
import MyButton from '@/components/UI/MyButton'
import UserService from "@/services/UserService";

export default {
  name: "EnterForm",
  components: {
    MyButton
  },
  props: ['authorized'],
  data: function () {
    return {
      login: '',
      password: '',
      passwordConfirm: '',
      message: '',
      isRegistration: false,
    }
  },
  methods: {
    changeVisibility: function (input, button, type) {
      document.getElementById(input).setAttribute("type", type);
      let id = (type === 'text' ? 'showImg' : 'hideImg');
      let src = document.getElementById(id).getAttribute("src");
      document.getElementById(button).setAttribute("src", src);
    },
    showPassword: function (e) {
      let id = e.target.id;
      let src = e.target.src;
      if (id === "passwordImage") {
        if (src.match(/hide/)) this.changeVisibility("password", id, "text");
        else this.changeVisibility("password", id, "password");
      } else {
        if (src.match(/hide/)) this.changeVisibility("password2", id, "text");
        else this.changeVisibility("password2", id, "password");
      }
    },
    switchToRegistration: function () {
      this.isRegistration = !this.isRegistration;
      this.message = ""
    },

    isLengthValid: function (length) {
      return length >= 4 && length <= 15;
    },

    validateLength: function (e) {
      console.log(e.target.value.length);
      if (!this.isLengthValid(e.target.value.length)) {
        this.message = 'Длина логина и пароля должна быть от 4 до 15 символов';
        e.target.style.backgroundColor = 'pink';
      } else {
        if (this.isLengthValid(this.password.length) && this.isLengthValid(this.login.length)) this.message = '';
        e.target.style.backgroundColor = 'white';
      }
    },
    validateData: function () {
      if (this.password.length !== 0 && this.login.length !== 0) {
        if (!this.isLengthValid(this.password.length) || !this.isLengthValid(this.login.length)) return false;
        this.message = "";
        document.getElementById("login").style.backgroundColor = 'white';
        document.getElementById("password").style.backgroundColor = 'white';
        if (this.isRegistration && this.passwordConfirm !== this.password) {
          this.message = 'Пароли не совпадают';
          return false;
        }
        return true;
      } else {
        this.message = 'Поля "логин" и "пароль" должны быть заполнены!';
        document.getElementById("login").style.backgroundColor = (this.login.length ? 'white' : 'pink');
        document.getElementById("password").style.backgroundColor = (this.password.length ? 'white' : 'pink');
        return false;
      }
    },


    toEnter: function () {
      if (this.validateData()) {
        UserService.isExist(this.login, this.password).then(result => {
          let response = result.data;
          console.log(result.data);

          if (response !== "pass") {
            this.message = response
          } else {
            this.message = ""
            this.$emit('switchAuth', true)
            this.$emit('onReg', this.login)
          }
        })
      }
    },

    toRegister: function () {
      if (this.validateData()) {
        UserService.setUser(this.login, this.password).then(result => {
          this.message = result.data
        })
      }
    }
  }
}
</script>

<style scoped>
.show_button {
  border-style: none;
  padding-left: 2%;
}

.password {
  padding-left: 6%;
}
</style>