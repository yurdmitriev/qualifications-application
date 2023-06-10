<template>
  <section>
    <BreadcrumbsComponent />
    <h1 class="mb-4">Нова пропозиція</h1>
    <form class="row mx-0 gx-md-3 mt-3" action="#" @submit.prevent="submitForm">
      <fieldset class="p-0 col-md-6">
        <div class="mb-3 col-md">
          <label class="form-label">Тип пропозиції:</label><br>
          <div class="form-check form-check-inline">
            <input class="form-check-input" type="radio" name="content_type" id="content_event" value="event"
                   v-model="type" required>
            <label class="form-check-label" for="content_event">
              Подія
            </label>
          </div>
          <div class="form-check form-check-inline">
            <input class="form-check-input" type="radio" name="content_type" id="content_vacancy" value="vacancy"
                   v-model="type" required>
            <label class="form-check-label" for="content_vacancy">
              Вакансія
            </label>
          </div>
        </div>
        <div class="mb-4 col-md" v-if="isAdmin">
          <label for="content_company" class="form-label">ІТ-компанія:</label>
          <select class="form-select form-select-sm mb-3" id="content_company" v-model="company" required>
            <option disabled selected>Оберіть IT-компанію</option>
            <option v-for="item in companiesList" :key="item.id" :value="item.id">{{ item.title }}</option>
          </select>
        </div>
        <div class="mb-3 col-md">
          <label for="content_title" class="form-label">Заголовок:</label>
          <input type="text" class="form-control" id="content_title" name="title" required v-model="title">
        </div>
        <div class="mb-3 col-md">
          <label class="form-label">Опис:</label>
          <RichTextEditor v-model="description" />
        </div>
        <div class="mb-3 col-md">
          <label class="form-label">Короткий опис:</label>
          <textarea class="form-control" v-model="summary" />
        </div>
        <div class="mb-3 col-md" v-if="type === 'vacancy'">
          <label class="form-label">Обов'язки на посаді:</label>
          <RichTextEditor v-model="vacancy.responsibilities" />
        </div>
        <div class="mb-3 col-md">
          <label for="content_city" class="form-label">Місто:</label>
          <input type="text" class="form-control" id="content_city" name="city" v-model="city">
          <small class="form-text">Якщо віддалено — залиште поле пустим</small>
        </div>
        <div class="mb-3 col-md" v-if="type === 'vacancy'">
          <label for="vacancy_salary" class="form-label">Заробітна плата, грн:</label>
          <input type="number" min="0" class="form-control" id="vacancy_salary" name="salary" v-model="vacancy.salary">
        </div>
        <div class="mb-3 col-md" v-if="type === 'event'">
          <label for="event_date" class="form-label">Дата проведення:</label>
          <input type="date" class="form-control" id="event_date" name="startDate" v-model="event.startDate" required>
        </div>
        <div class="mb-3 col-md" v-if="type">
          <label for="content_url" class="form-label">
            <span v-if="type === 'vacancy'">Посилання на вакансію:</span>
            <span v-else>Посилання на реєстрацію на участь у події:</span></label>
          <input type="url" class="form-control" id="content_url" name="url" v-model="url" :required="type === 'event'">
        </div>
      </fieldset>
      <fieldset class="p-0 col-md-4 ms-auto">
        <MicroCredentialsComponent :editable="true" v-model="competencies"/>
      </fieldset>
      <fieldset class="col-12 d-flex align-items-center gap-4 mt-4">
        <button class="btn btn-primary">Зберегти</button>
        <div class="form-check form-switch">
          <input class="form-check-input" type="checkbox" role="switch" id="content_published" name="published"
                 v-model="published">
          <label class="form-check-label" for="content_published">Опублікувати</label>
        </div>
      </fieldset>
    </form>
  </section>
</template>

<script>
import BreadcrumbsComponent from "@/components/content/BreadcrumbsComponent.vue";
import RichTextEditor from "@/components/content/RichTextEditor.vue";
import MicroCredentialsComponent from "@/components/content/MicroCredentialsComponent.vue";
import { createEvent } from "@/services/events";
import { createVacancy } from "@/services/vacancies";
import { useUserStore } from "@/stores/user";
import { getCompaniesList } from "@/services/companies";
import { getCurrentUser } from "@/services/auth";

export default {
  name: "NewContentView",
  components: { MicroCredentialsComponent, RichTextEditor, BreadcrumbsComponent },
  data() {
    return {
      loading: false,
      companiesList: [],
      company: "",
      type: "",
      title: "",
      description: "",
      summary: "",
      city: "",
      url: "",
      published: false,
      competencies: {},
      vacancy: {
        responsibilities: "",
        salary: ""
      },
      event: {
        startDate: ""
      }
    };
  },
  computed: {
    selectedCompetencyIds() {
      const result = [];

      for (const [key, value] of Object.entries(this.competencies)) {
        for (const item of value.items ?? []) {
          result.push(item.id);
        }
      }

      return result;
    },
    isAdmin() {
      const store = useUserStore();
      return store.info.role === 'ADMIN';
    }
  },
  methods: {
    submitForm() {
      if (this.type === 'event') {
        this.loading = true;
        createEvent({
          title: this.title,
          description: this.description,
          summary: this.summary,
          competencies: this.selectedCompetencyIds,
          company: this.company,
          url: this.url ?? null,
          city: this.city ?? null,
          published: this.published,
          startDate: this.event.startDate
        }).then(res => {
          this.$router.push('/dashboard/content');
        })
          .catch(err => {})
          .finally(() => {
            this.loading = false;
          });
      } else {
        this.loading = true;
        createVacancy({
          title: this.title,
          description: this.description,
          summary: this.summary,
          responsibilities: this.vacancy.responsibilities,
          competencies: this.selectedCompetencyIds,
          company: this.company,
          url: this.url ?? null,
          city: this.city ?? null,
          salary: this.vacancy.salary ?? null,
          published: this.published
        }).then(res => {
          this.$router.push('/dashboard/content');
        })
          .catch(err => {})
          .finally(() => {
            this.loading = false;
          });
      }
    }
  },
  created() {
    console.log('here');
    if (this.isAdmin) {
      this.loading = true;

      getCompaniesList()
        .then(res => {
          this.companiesList = res.data;
        })
        .catch(err => {

        })
        .finally(() => {
          this.loading = false;
        })
    } else {
      console.log('here');
      this.loading = true;
      getCurrentUser()
        .then(res => {
          const user = res.data;
          this.company = user.company.id;
        })
        .catch(err => {

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