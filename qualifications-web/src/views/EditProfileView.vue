<template>
<main>
  <div class="container">
    <BreadcrumbsComponent />
    <div class="row">
      <div class="row p-0 col-md-9 mx-auto">
        <fieldset class="col-md-6">
          <div class="mt-3">
            <label for="user_last_name" class="form-label">Прізвище:</label>
            <input type="text" class="form-control" id="user_last_name" name="last_name" required
                   v-model="user.lastName">
          </div>
          <div class="mt-3">
            <label for="user_first_name" class="form-label">Ім'я:</label>
            <input type="text" class="form-control" id="user_first_name" name="first_name" required
                   v-model="user.firstName">
          </div>
          <div class="mt-3">
            <label for="user_patronymic" class="form-label">По-батькові:</label>
            <input type="text" class="form-control" id="user_patronymic" name="patronymic" v-model="user.patronymic">
          </div>
          <div class="mt-3">
            <label for="user_email" class="form-label">Електронна пошта</label>
            <input type="email" class="form-control" id="user_email" name="email" required v-model="user.email">
            <span class="form-text">Рекомендується використовувати корпоративну пошту</span>
          </div>
        </fieldset>
        <fieldset class="ms-auto col-md-4">
          <MicroCredentialsComponent :mock="true" :editable="true" title="Мої навички"/>
        </fieldset>
        <fieldset class="col-12">
          <div class="mt-5">
            <button class="btn btn-primary" type="submit">Зберегти</button>
          </div>
        </fieldset>
      </div>
    </div>
  </div>
</main>
</template>

<script>
import BreadcrumbsComponent from "@/components/content/BreadcrumbsComponent.vue";
import MicroCredentialsComponent from "@/components/content/MicroCredentialsComponent.vue";
import { getCurrentUser } from "@/services/auth";

export default {
  name: "EditProfileView",
  components: { MicroCredentialsComponent, BreadcrumbsComponent },
  data() {
    return {
      user: {
        firstName: "",
        lastName: "",
        patronymic: "",
        email: "",
        competencies: []
      }
    }
  },
  mounted() {
    getCurrentUser()
      .then(res => {
        const response = res.data;
        this.user.firstName = response.firstName;
        this.user.lastName = response.lastName;
        this.user.patronymic = response.patronymic;
        this.user.email = response.email;
      })
      .catch(err => {

      })
  }
};
</script>

<style scoped>

</style>