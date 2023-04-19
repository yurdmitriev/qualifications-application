<template>
  <main v-if="found">
    <div class="container">
      <h1>{{title}} {{salary}} UAH</h1>
      <p>{{description}}</p>
    </div>
  </main>
  <NotFoundView v-else />
</template>

<script>
import { getById } from "@/services/vacancies";
import NotFoundView from "@/views/NotFoundView.vue";

export default {
  name: "ContentView",
  components: { NotFoundView },
  data() {
    return {
      found: true,
      loading: false,
      title: "",
      description: "",
      salary: 0,
      publishDate: "",
      company: "",
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
      this.competencies = info.competencies;
    }).catch(() => {
      this.found = false;
    }).finally(() => this.loading = false)
  }
};
</script>

<style scoped>

</style>