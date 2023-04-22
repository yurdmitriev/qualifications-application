<template>
  <article>
    <h5>
      <RouterLink :to="`/events/${id}`" class="link-dark text-decoration-none"><b>{{ title }}</b></RouterLink>
      <i class="ms-1" v-if="company"> від {{ company }}</i>
    </h5>
    <p class="mb-1"><i>{{ startAtDate }}</i> <i class="text-secondary">{{ city ?? "Віддалено" }}</i></p>
    <p>{{ summary }}</p>
  </article>
</template>

<script>
import { months } from "@/services/util";

export default {
  name: "EventItem",
  props: ["id", "title", "description", "summary", "company", "startDate", "city"],
  computed: {
    startAtDate() {
      const current = new Date();
      const date = new Date(this.startDate);
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