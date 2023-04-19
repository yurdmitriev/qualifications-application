<template>
  <article>
    <h5 class="d-flex align-items-center flex-wrap">
      <RouterLink :to="`/vacancies/${id}`" class="link-dark text-decoration-none"><b>{{ title }}</b></RouterLink>
      <i class="ms-1" v-if="company"> в {{ company }}</i><i class="ms-auto salary" v-if="salary"><b>₴{{ salary
      }}</b></i>
    </h5>
    <p class="mb-1"><i>{{ publishedDate }}</i> <i class="text-secondary">{{ city ?? "Віддалено" }}</i></p>
    <p>{{ description }}</p>
  </article>
</template>

<script>
import { months } from "@/services/util";

export default {
  name: "VacancyItem",
  props: ["id", "title", "description", "company", "publishedAt", "city", "salary"],
  computed: {
    publishedDate() {
      const current = new Date();
      const date = new Date(this.publishedAt);
      let result = `${date.getDate()} ${months[date.getMonth()]}`
      if (current.getFullYear() - date.getFullYear() > 0) {
        result += ` ${date.getFullYear()}`;
      }

      return result;
    }
  }
};
</script>

<style scoped>
.salary {
    font-size: 75%;
}
</style>