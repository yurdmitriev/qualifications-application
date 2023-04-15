<template>
  <main>
    <div class="container">
      <form class="mx-auto">
        <h1 class="h3 mb-4 fw-normal text-center">Авторизація</h1>

        <div class="w-100 form-floating mb-3">
          <input type="email" class="form-control" id="floatingInput" placeholder="name@example.com" v-model="email">
          <label class="form-label" for="floatingInput">Електронна пошта</label>
        </div>

        <div class="form-floating mb-3">
          <input type="password" class="form-control" id="floatingPassword" placeholder="Password" v-model="password">
          <label class="form-label" for="floatingPassword">Пароль</label>
        </div>

        <div class="mb-3 form-check">
          <input type="checkbox" class="form-check-input" id="exampleCheck1" v-model="rememberMe">
          <label class="form-check-label" for="exampleCheck1">Запам'ятати мене</label>
        </div>

        <div class="mb-3 d-flex gap-3 flex-wrap">
          <button class="flex-grow-1 btn btn-lg btn-primary" type="submit" @click.prevent="login">Увійти</button>
          <button class="flex-grow-1 btn btn-lg btn-primary" type="submit" @click.prevent="googleLogin">Увійти із
            Google
          </button>
        </div>

        <div>
          <span>Не зареєстровані? <RouterLink to="/register">Зареєструватися</RouterLink></span>
        </div>
      </form>
    </div>
  </main>
</template>

<script>
import { onsiteLogin } from "@/services/auth";

export default {
  name: "LoginView",
  data() {
    return {
      email: localStorage.getItem('remember_email') ?? "",
      password: "",
      rememberMe: false
    };
  },
  methods: {
    login() {
      onsiteLogin(this.email, this.password);
      this.$router.push("/");
      if (this.rememberMe) {
        localStorage.setItem('remember_email', this.email);
      }

      return true;
    },
    googleLogin() {
      return false;
    }
  }
};
</script>

<style scoped>
form {
    max-width: 350px;
}
</style>