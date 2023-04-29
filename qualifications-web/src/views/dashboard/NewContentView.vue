<template>
  <section>
    <BreadcrumbsComponent />
    <h1 class="mb-4">Нова пропозиція</h1>
    <form class="row mx-0 gx-md-3 mt-3" action="#">
      <fieldset class="p-0 col-md-6">
        <div class="mb-3 col-md">
          <label for="content_title" class="form-label">Заголовок:</label>
          <input type="text" class="form-control" id="content_title" name="title" required v-model="title">
        </div>
        <div class="mb-3 col-md">
          <label class="form-label">Опис:</label>
          <RichTextEditor v-model="description" />
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
        <div class="mb-3 col-md">
          <label class="form-label">Тип пропозиції:</label>
          <div class="form-check">
            <input class="form-check-input" type="radio" name="content_type" id="content_event" value="event"
                   v-model="type">
            <label class="form-check-label" for="content_event">
              Подія
            </label>
          </div>
          <div class="form-check">
            <input class="form-check-input" type="radio" name="content_type" id="content_vacancy" value="vacancy"
                   v-model="type">
            <label class="form-check-label" for="content_vacancy">
              Вакансія
            </label>
          </div>
        </div>
        <div class="mb-3 col-md" v-if="type === 'vacancy'">
          <label for="vacancy_salary" class="form-label">Заробітна плата, грн:</label>
          <input type="number" min="0" class="form-control" id="vacancy_salary" name="salary" v-model="vacancy.salary">
        </div>
        <div class="mb-3 col-md" v-if="type === 'event'">
          <label for="event_date" class="form-label">Дата проведення:</label>
          <input type="date" class="form-control" id="event_date" name="startDate" v-model="event.startDate">
        </div>
        <div class="mb-3 col-md" v-if="type">
          <label for="content_url" class="form-label"><span v-if="type === 'vacancy'">Посилання на вакансію:</span><span
            v-else>Посилання на реєстрацію на участь у події:</span></label>
          <input type="url" class="form-control" id="content_url" name="url" v-model="url">
        </div>
      </fieldset>
      <fieldset class="p-0 col-md-4 ms-auto">
        <MicroCredentialsComponent />
      </fieldset>
      <fieldset class="col-12 d-flex align-items-center gap-4">
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

export default {
  name: "NewContentView",
  components: { MicroCredentialsComponent, RichTextEditor, BreadcrumbsComponent },
  data() {
    return {
      type: "",
      title: "",
      description: "",
      city: "",
      url: "",
      published: false,
      vacancy: {
        responsibilities: "",
        salary: ""
      },
      event: {
        startDate: ""
      }
    };
  }
};
</script>

<style scoped>

</style>