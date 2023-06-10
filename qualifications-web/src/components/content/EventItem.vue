<template>
  <article :class="{'list-group-item': dashboard, 'not-published': !published}">
    <h5 class="d-flex align-items-center">
      <RouterLink :to="`/events/${id}`" class="link-dark text-decoration-none"><b>{{ title }}</b></RouterLink>
      <i class="ms-1" v-if="company && !dashboard"> від {{ company }}</i>
      <span class="ms-auto btn-group btn-group-sm" role="group" aria-label="Basic example" v-if="dashboard">
        <button type="button" class="btn btn-outline-primary" :disabled="publishedLoading || deleteLoading">
          <EditIcon />
        </button>
        <button type="button" class="btn btn-outline-primary" :disabled="publishedLoading || deleteLoading" @click="togglePublished">
           <span class="spinner-border spinner-border-sm" role="status" v-if="publishedLoading">
            <span class="visually-hidden">Loading...</span>
          </span>
          <span v-else>
          <VisibilityIcon />
          </span>
        </button>
        <button type="button" class="btn btn-outline-danger" :disabled="publishedLoading || deleteLoading" @click="deleteEvent">
          <span class="spinner-border spinner-border-sm" role="status" v-if="deleteLoading">
            <span class="visually-hidden">Loading...</span>
          </span>
          <span v-else>
            <DeleteIcon />
          </span>
        </button>
      </span>
    </h5>
    <p class="mb-1"><i v-if="startDate"><span>Початок </span>{{ startAtDate }}</i> <i class="text-secondary">{{ city ? city : "Віддалено" }}</i></p>
    <p v-if="!dashboard">{{ summary }}</p>
    <p class="mb-1" v-if="company && adminView">Компанія: {{ company }}</p>
    <small class="text-danger" v-if="dashboard && !published"><b>Знято з публікації</b></small>
  </article>
</template>

<script>
import { months } from "@/services/util";
import VisibilityIcon from "@/components/icons/VisibilityIcon.vue";
import DeleteIcon from "@/components/icons/DeleteIcon.vue";
import EditIcon from "@/components/icons/EditIcon.vue";
import { deleteById, setPublishedState } from "@/services/events";

export default {
  name: "EventItem",
  components: { EditIcon, DeleteIcon, VisibilityIcon },
  props: ["id", "title", "description", "summary", "company", "startDate", "city", "dashboard", "published", "adminView"],
  data() {
    return {
      publishedLoading: false,
      deleteLoading: false
    }
  },
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
  },
  methods: {
    togglePublished() {
      this.publishedLoading = true;
      setPublishedState(this.id, !this.published)
        .then(res => {
          this.$emit('published-toggle');
        })
        .catch(res => {
          // TODO: handle error
        })
        .finally(() => this.publishedLoading = false);
    },
    deleteEvent() {
      this.deleteLoading = true;
      deleteById(this.id)
        .then(res => this.$emit('deleted-event'))
        .catch(err => {
          // TODO: handle error
        })
        .finally(() => this.deleteLoading = false);
    }
  }
};
</script>

<style scoped>
.not-published h5>*:not(.btn-group),
.not-published p {
    opacity: .5;
}
</style>