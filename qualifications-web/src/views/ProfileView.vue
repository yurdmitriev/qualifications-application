<template>
  <main>
    <div class="container">
      <BreadcrumbsComponent />

      <div class="text-center mt-5" v-if="loading">
        <div class="spinner-border text-primary" role="status">
          <span class="visually-hidden">Loading...</span>
        </div>
      </div>
      <article v-else>
        <h1 class="mt-4">{{ user.firstName }} {{ user.patronymic }} {{ user.lastName }} </h1>
        <div class="row p-0 mt-4 gap-3">
          <section class="col-md-7 list-group-flush">
            <h5 class="list-group-item">
              <b v-if="user.role === 'ADMIN'">Адміністратор системи</b>
              <b v-else-if="user.role === 'STUDENT'">Студент <span v-if="user.course">{{ user.course }} курсу</span></b>
              <b v-else-if="user.role === 'LECTURER'">
                <span v-if="user.qualification">{{ user.qualification }}</span><span v-else>Викладач</span>
                <span v-if="user.department"> у {{ user.department }}</span>
              </b>
              <b v-else>Співробітник ІТ-компанії <span v-if="user.company">{{ user.company.title }}</span></b>
            </h5>
            <h5 class="list-group-item" v-if="user.position"><b>Посада:</b> {{ user.position }}</h5>
            <h5 class="list-group-item mt-4">
              <b>Email:</b> {{ user.email }}
            </h5>
            <div class="mt-3">
              <RouterLink class="btn btn-primary" to="/profile/edit">Редагувати інформацію</RouterLink>
            </div>
            <div class="mt-5">
              <h2>Налаштування</h2>
              <div class="mt-3">
                <div class="form-check form-switch">
                  <input class="form-check-input" :class="{'is-invalid': settings.notifications.denied}" type="checkbox"
                         role="switch" id="notifications-switch" :disabled="!settings.notifications.supported"
                         @click="askNotificationPermission" v-model="settings.notifications.accepted">
                  <label class="form-check-label" for="notifications-switch">Отримувати PUSH сповіщення</label>
                  <div class="invalid-feedback">
                    Отримання сповіщень заблоковано у налаштуваннях браузера
                  </div>
                </div>
                <button class="btn btn-primary mt-3" @click="testNotification"
                        :disabled="settings.notifications.denied && !settings.notifications.accepted && !settings.notifications.supported">
                  Тестувати сповіщення
                </button>
              </div>
            </div>
          </section>
          <section class="col-md-4" v-if="user.role === 'STUDENT' || user.role === 'LECTURER'">
            <MicroCredentialsComponent v-if="user.competencies.length > 0" :editable="false" :mock="true"
                                       :title="(user.role === 'STUDENT') ? 'Володію навичками' : 'Викладаю технології'" />
            <h5 class="mt-3" v-else>Мікрокваліфікації можна додати у формі редагування інформації</h5>
          </section>
        </div>
      </article>
    </div>
  </main>
</template>

<script>
import BreadcrumbsComponent from "@/components/content/BreadcrumbsComponent.vue";
import { getCurrentUser } from "@/services/auth";
import MicroCredentialsComponent from "@/components/content/MicroCredentialsComponent.vue";

export default {
  name: "ProfileView",
  components: { MicroCredentialsComponent, BreadcrumbsComponent },
  data() {
    return {
      user: {
        firstName: "",
        lastName: "",
        patronymic: "",
        email: "",
        role: "",
        position: "",
        course: "",
        company: "",
        competencies: [],
        qualification: "",
        department: ""
      },
      settings: {
        notifications: {
          supported: false,
          denied: false,
          accepted: false
        }
      },
      loading: true
    };
  },
  methods: {
    askNotificationPermission(event) {
      if (Notification.permission === "granted") {
        event.preventDefault();
      } else {
        this.settings.notifications.denied = true;
        this.settings.notifications.accepted = false;

        Notification.requestPermission().then((permission) => {
          // If the user accepts, let's create a notification
          if (permission === "granted") {
            this.settings.notifications.denied = false;
            this.settings.notifications.accepted = true;
            new Notification("Тепер ви зможете отримувати сповіщення прямо в браузері!");
          } else {
            this.settings.notifications.denied = true;
            this.settings.notifications.accepted = false;
          }
        });
      }
    },
    testNotification() {
      new Notification("Тест сповіщення!");
    }
  },
  beforeMount() {
    if ("Notification" in window) {
      this.settings.notifications.supported = true;

      if (Notification.permission === "granted") {
        this.settings.notifications.denied = false;
        this.settings.notifications.accepted = true;
      }
    }

    this.loading = true;

    getCurrentUser()
      .then(res => {
        const response = res.data;
        this.user.firstName = response.firstName;
        this.user.lastName = response.lastName;
        this.user.patronymic = response.patronymic;
        this.user.email = response.email;
        this.user.role = response.role;

        if (response.position) this.user.position = response.position;
        if (response.company) this.user.company = response.company;
        if (response.course) this.user.course = response.course;
        if (response.competencies) this.user.competencies = response.competencies;
        if (response.qualification) this.user.qualification = response.qualification;
        if (response.department) this.user.department = response.department;
      })
      .catch(err => {
        // TODO: catch error
      })
      .finally(() => {
        this.loading = false;
      });
  }
};
</script>

<style scoped>

</style>