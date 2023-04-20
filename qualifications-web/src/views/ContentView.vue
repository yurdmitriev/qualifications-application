<template>
  <main v-if="found">
    <div class="container">
      <breadcrumbs-component :current-title="title" />
      <div class="text-center" v-if="loading">
        <div class="spinner-border text-primary" role="status">
          <span class="visually-hidden">Loading...</span>
        </div>
      </div>
      <article v-else>
        <h1 class="mt-4 d-flex align-items-center flex-wrap"><span>{{ title }}</span> <i
          class="ms-md-auto me-md-0 mt-2 mt-md-0 text-dark salary" v-if="salary"><b>₴{{ salary }}</b></i></h1>
        <h6 class="mb-1 text-secondary"><i>{{ publishedDate }}</i></h6>
        <p class="mt-1 text-secondary d-flex align-items-center">
          <GeoFilledIcon class="me-1" />
          <b><i>{{ city ?? "Віддалено" }}</i></b>
        </p>
        <div class="row mx-0 gx-md-3 mt-3">
          <section class="p-0 col-md-9">
            <section>
              <h5><b>Про вакансію:</b></h5>
              <p v-html="description"></p>
            </section>
            <section class="mt-3" v-if="responsibilities">
              <h5><b>Твої обов'язки на посаді:</b></h5>
              <p v-html="responsibilities"></p>
            </section>
          </section>
          <section class="p-0 col-md-3">
            <h5><b>Необхідні навички:</b></h5>
            <ul>
              <li v-for="item in competencies" :key="item.id">{{ item.title }}
                <ul v-if="item.microCompetencies">
                  <li v-for="i in item.microCompetencies" :key="i.id">{{ i.title }}</li>
                </ul>
              </li>
            </ul>
          </section>
          <section class="mt-3">
            <a target="_blank" class="btn btn-primary btn-lg fw-semibold" :href="url" v-if="url">
              <ExternalIcon /> Відкрити вакансію
            </a>
            <a target="_blank" class="btn btn-primary btn-lg fw-semibold" :href="`mailto:${company.hrEmail}`"
               v-else-if="company.hrEmail">
              <SendEmailIcon />
              Відгукнутися на вакансію
            </a>
          </section>
          <hr class="mt-3">
          <section class="col-md-9 mt-3">
            <h5>
              <a target="_blank" :href="company.url" v-if="company.url"><b>{{ company.title }}</b></a>
              <b v-else>{{ company.title }}</b>
            </h5>
            <p v-html="company.description"></p>
          </section>
        </div>
      </article>
    </div>
  </main>
  <NotFoundView v-else />
</template>

<script>
import { getById } from "@/services/vacancies";
import NotFoundView from "@/views/NotFoundView.vue";
import BreadcrumbsComponent from "@/components/content/BreadcrumbsComponent.vue";
import { months } from "@/services/util";
import GeoFilledIcon from "@/components/icons/GeoFilledIcon.vue";
import ExternalIcon from "@/components/icons/ExternalIcon.vue";
import SendEmailIcon from "@/components/icons/SendEmailIcon.vue";

export default {
  name: "ContentView",
  components: { SendEmailIcon, ExternalIcon, GeoFilledIcon, BreadcrumbsComponent, NotFoundView },
  data() {
    return {
      found: true,
      loading: false,
      title: "",
      description: "",
      salary: 0,
      company: {},
      city: "",
      publishedAt: "",
      responsibilities: "",
      url: "",
      competencies: []
    };
  }, beforeMount() {
    this.loading = true;
    getById(this.$route.params.id).then(res => {
      const info = res.data;

      this.title = info.title;
      this.description = info.description;
      this.salary = info.salary;
      this.publishDate = info.publishDate;
      this.company = info.company;
      this.competencies = info.micro_credentials;
      this.publishedAt = info.publishDate;
      this.city = info.city;
      this.url = info.url;
      this.responsibilities = info.responsibilities;
    }).catch(() => {
      this.found = false;
    }).finally(() => this.loading = false);
  },
  computed: {
    publishedDate() {
      const date = new Date(this.publishedAt);
      return `${date.getDate()} ${months[date.getMonth()]} ${date.getFullYear()}`;
    }
  }
};
</script>

<style scoped>
.salary {
    font-size: 1.5rem;
}

a.btn {
    display: flex;
    justify-content: flex-start;
    width: fit-content;
    align-items: center;
    column-gap: .5em;
}
</style>