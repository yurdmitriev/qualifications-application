<template>
  <article :class="{'list-group-item': dashboard}">
    <h5 class="d-flex align-items-center">
      <span>
        <RouterLink :to="`/vacancies/${id}`" class="link-dark text-decoration-none"><b>{{ title }}</b></RouterLink>
        <i class="ms-1" v-if="company"> в {{ company }}</i>
      </span>
      <i class="ms-auto salary" v-if="salary && !dashboard"><b>₴{{ salary }}</b></i>
      <span class="ms-auto btn-group btn-group-sm" role="group" aria-label="Basic example" v-if="dashboard">
        <button type="button" class="btn btn-outline-primary">
          <EditIcon />
        </button>
        <button type="button" class="btn btn-outline-primary">
          <VisibilityIcon />
        </button>
        <button type="button" class="btn btn-outline-danger">
          <DeleteIcon />
        </button>
      </span>
    </h5>
    <p class="mb-1"><i>{{ publishedDate }}</i> <i class="text-secondary">{{ city ?? "Віддалено" }}</i>
      <span v-if="dashboard"><i class="ms-3" v-if="salary"><b>₴{{ salary }}</b></i></span>
    </p>
    <p v-if="!dashboard">{{ summary }}</p>
  </article>
</template>

<script>
import { months } from "@/services/util";
import EditIcon from "@/components/icons/EditIcon.vue";
import VisibilityIcon from "@/components/icons/VisibilityIcon.vue";
import DeleteIcon from "@/components/icons/DeleteIcon.vue";

export default {
  name: "VacancyItem",
  components: { DeleteIcon, VisibilityIcon, EditIcon },
  props: ["id", "title", "description", "summary", "company", "publishedAt", "city", "salary", "dashboard"],
  computed: {
    publishedDate() {
      const current = new Date();
      const date = new Date(this.publishedAt);
      let result = `${date.getDate()} ${months[date.getMonth()]}`;
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