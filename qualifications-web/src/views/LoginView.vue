<template>
  <main>
    <div class="container">
      <form class="mx-auto">
        <h1 class="h3 mb-4 fw-normal text-center">Авторизація</h1>

        <div class="w-100 form-floating mb-3">
          <input type="email" class="form-control" :class="{'is-invalid': error.active}" @keydown="() => error.active = false" id="floatingInput" placeholder="name@example.com" v-model="email">
          <label class="form-label" for="floatingInput">Електронна пошта</label>
        </div>

        <div class="form-floating mb-3">
          <input type="password" class="form-control" :class="{'is-invalid': error.active}" @keydown="() => error.active = false" id="floatingPassword" placeholder="Password" v-model="password">
          <label class="form-label" for="floatingPassword">Пароль</label>

          <small class="invalid-feedback" v-if="error.active">
            Не вірна поштова адреса або пароль
          </small>
        </div>

        <div class="mb-3 form-check">
          <input type="checkbox" class="form-check-input" id="exampleCheck1" v-model="rememberMe">
          <label class="form-check-label" for="exampleCheck1">Запам'ятати мене</label>
        </div>

        <div class="mb-3 d-flex gap-3 flex-wrap">
          <button class="flex-grow-1 btn btn-lg btn-primary" type="submit" :disabled="loading" @click.prevent="login">
            <span class="spinner-border spinner-border-sm me-1" role="status" v-if="loading" aria-hidden="true"></span>
            <span>Увійти</span>
          </button>
          <button class="flex-grow-1 btn btn-lg btn-primary" type="submit" :disabled="loading"
                  @click.prevent="googleLogin">
            <span class="spinner-border spinner-border-sm me-1" role="status" v-if="false" aria-hidden="true"></span>
            <span>Увійти із Google</span>
          </button>
        </div>

        <div>
          <span>Не зареєстровані? <RouterLink to="/register" :disabled="loading">Зареєструватися</RouterLink></span>
        </div>
      </form>
    </div>
  </main>
</template>

<script>
import { onsiteLogin } from "@/services/auth";
import { useUserStore } from "@/stores/user";

export default {
  name: "LoginView",
  data() {
    return {
      email: localStorage.getItem("remember_email") ?? "",
      password: "",
      rememberMe: false,
      error: {
        message: "",
        active: false
      },
      loading: false
    };
  },
  methods: {
    login() {
      this.loading = true;
      onsiteLogin(this.email, this.password).then(res => {
        const userStore = useUserStore();
        const response = res.data;
        const decoded = this.$jwt.decode(response.token);

        localStorage.setItem("token", response.token);
        localStorage.setItem("expires", response.expires);
        userStore.setUser(decoded);
        this.$router.push("/");

        if (this.rememberMe) {
          localStorage.setItem("remember_email", this.email);
        }
      }).catch(() => {
        this.error.active = true;
        return false;
        // TODO: show error on the form
      }).finally(() => {
        this.loading = false;
      });
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