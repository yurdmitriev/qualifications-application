<template>
  <article :class="{'list-group-item': dashboard}">
    <h5 class="d-flex align-items-center">
      <RouterLink :to="`/events/${id}`" class="link-dark text-decoration-none"><b>{{ title }}</b></RouterLink>
      <i class="ms-1" v-if="company && !dashboard"> від {{ company }}</i>
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
    <p class="mb-1"><i>{{ startAtDate }}</i> <i class="text-secondary">{{ city ?? "Віддалено" }}</i></p>
    <p v-if="!dashboard">{{ summary }}</p>
  </article>
</template>

<script>
import { months } from "@/services/util";
import VisibilityIcon from "@/components/icons/VisibilityIcon.vue";
import DeleteIcon from "@/components/icons/DeleteIcon.vue";
import EditIcon from "@/components/icons/EditIcon.vue";

export default {
  name: "EventItem",
  components: { EditIcon, DeleteIcon, VisibilityIcon },
  props: ["id", "title", "description", "summary", "company", "startDate", "city", "dashboard"],
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