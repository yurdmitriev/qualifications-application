<template>
  <main>
    <div class="container">
      <form class="row gap-3" ref="registerForm">
        <section class="col-lg-6 ms-lg-5">
          <h2 class="mb-4">Реєстрація нового користувача</h2>
          <div class="mb-3">
            <label class="form-label">Моя роль у системі:</label>
            <div class="input-group gap-3">
              <div class="form-check">
                <input class="form-check-input" type="radio" name="role" id="radio_role_student" v-model="user.role"
                       :value="radios.student" required>
                <label class="form-check-label" for="radio_role_student">
                  Студент
                </label>
              </div>
              <div class="form-check">
                <input class="form-check-input" type="radio" name="role" id="radio_role_lecturer" v-model="user.role"
                       :value="radios.lecturer" required>
                <label class="form-check-label" for="radio_role_lecturer">
                  Викладач
                </label>
              </div>
              <div class="form-check">
                <input class="form-check-input" type="radio" name="role" id="radio_role_company" v-model="user.role"
                       :value="radios.company" required>
                <label class="form-check-label" for="radio_role_company">
                  ІТ-компанія
                </label>
              </div>
            </div>
          </div>
          <div class="row mb-3">
            <div class="col-md">
              <label for="user_last_name" class="form-label">Прізвище:</label>
              <input type="text" class="form-control" id="user_last_name" name="last_name" required
                     v-model="user.lastName">
            </div>
            <div class="col-md">
              <label for="user_first_name" class="form-label">Ім'я:</label>
              <input type="text" class="form-control" id="user_first_name" name="first_name" required
                     v-model="user.firstName">
            </div>
          </div>
          <div class="row mb-3">
            <div class="col-md">
              <label for="user_patronymic" class="form-label">По-батькові:</label>
              <input type="text" class="form-control" id="user_patronymic" name="patronymic" v-model="user.patronymic">
            </div>
            <div class="col-md">
              <label for="user_email" class="form-label">Електронна пошта</label>
              <input type="email" class="form-control" id="user_email" name="email" required v-model="user.email">
              <span class="form-text">Рекомендується використовувати корпоративну пошту</span>
            </div>
          </div>
          <div class="row mb-4">
            <div class="col-md">
              <label class="form-label" for="user_password">Пароль:</label>
              <input class="form-control" type="password" name="password" id="user_password" required
                     v-model="user.password">
            </div>
            <div class="col-md">
              <label class="form-label" for="user_password_confirm">Повторити пароль:</label>
              <input class="form-control" type="password" name="password_confirmation" id="user_password_confirm"
                     autocomplete="current-password" required v-model="user.passwordConfirm">
            </div>
          </div>
          <div class="d-flex mb-3 gap-3 align-items-center" v-if="(user.role !== radios.company)">
            <button class="flex-grow-1 btn btn-lg btn-primary" :class="{'disabled': !user.role || loading}" type="submit" @click.prevent="register">
              <span class="spinner-border spinner-border-sm me-1" role="status" v-if="loading" aria-hidden="true"></span>
              <span>Зареєструватися</span>
            </button>
            <span> або </span>
            <button class="flex-grow-1 btn btn-lg btn-primary" :class="{'disabled': !user.role}" type="submit" @click.prevent="">Увійти із
              Google
            </button>
          </div>
          <span>Вже зареєстровані? <RouterLink to="/login">Увійти</RouterLink></span>
        </section>
        <section class="col-lg-4 mx-auto" v-if="user.role === radios.company">
          <h2 class="mb-4">Інформація про компанію</h2>
          <div class="mb-3 col-md">
            <label for="company_title" class="form-label">Назва:</label>
            <input type="text" class="form-control" id="company_title" name="company_title" required
                   v-model="user.company.title">
          </div>
          <div class="mb-3 col-md">
            <label for="company_website" class="form-label">Посилання на сайт:</label>
            <input type="url" class="form-control" id="company_website" name="company_url" v-model="user.company.url">
          </div>
          <div class="mb-3 col-md">
            <label for="company_description" class="form-label">Опис:</label>
            <textarea class="form-control" id="company_description" name="company_description"
                   v-model="user.company.description" rows="3"/>
          </div>
        </section>
        <div class="d-flex justify-content-center mt-4" v-if="user.role === radios.company">
          <button class="btn btn-lg btn-primary" type="submit" @click.prevent="() => true">Відправити заявку</button>
        </div>
      </form>
    </div>
  </main>
</template>

<script>
import { registerUser } from "@/services/auth";
import { useUserStore } from "@/stores/user";

export default {
  name: "RegisterView",
  data() {
    return {
      loading: false,
      radios: {
        lecturer: "LECTURER",
        student: "STUDENT",
        company: "COMPANY"
      },
      user: {
        firstName: "",
        lastName: "",
        patronymic: "",
        email: "",
        password: "",
        passwordConfirm: "",
        role: "",
        company: {
          title: "",
          url: "",
          description: ""
        }
      }
    };
  },
  methods: {
    register() {
      this.loading = true;
      registerUser(this.user)
        .then(res => {
          const userStore = useUserStore();
          const response = res.data;
          const decoded = this.$jwt.decode(response.token);

          localStorage.setItem("token", response.token);
          localStorage.setItem("expires", response.expires);
          userStore.setUser(decoded);
          this.$router.push("profile");
        })
        .catch(err => {
          alert(err);
          console.err(err);
        })
        .finally(() => {
          this.loading = false;
        });
    }
  }
};
</script>

<style scoped>

</style>