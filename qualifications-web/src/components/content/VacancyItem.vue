<template>
  <article :class="{'list-group-item': dashboard, 'not-published': !published}">
    <h5 class="d-flex align-items-center">
      <span>
        <RouterLink :to="`/vacancies/${id}`" class="link-dark text-decoration-none"><b>{{ title }}</b></RouterLink>
        <i class="ms-1" v-if="company"> в {{ company }}</i>
      </span>
      <i class="ms-auto salary" v-if="salary && !dashboard"><b>₴{{ salary }}</b></i>
      <span class="ms-auto btn-group btn-group-sm" role="group" aria-label="Basic example" v-if="dashboard">
        <button type="button" class="btn btn-outline-primary" @click="() => {}" :disabled="publishedLoading || deleteLoading">
          <EditIcon />
        </button>
        <button type="button" class="btn btn-outline-primary" @click="togglePublished" :disabled="publishedLoading || deleteLoading">
          <span class="spinner-border spinner-border-sm" role="status" v-if="publishedLoading">
            <span class="visually-hidden">Loading...</span>
          </span>
          <span v-else>
          <VisibilityIcon />
          </span>
        </button>
        <button type="button" class="btn btn-outline-danger" @click="deleteVacancy" :disabled="publishedLoading || deleteLoading">
          <span class="spinner-border spinner-border-sm" role="status" v-if="deleteLoading">
            <span class="visually-hidden">Loading...</span>
          </span>
          <span v-else>
            <DeleteIcon />
          </span>
        </button>
      </span>
    </h5>
    <p class="mb-1"><i v-if="publishedAt"><span v-if="dashboard">Опубліковано </span>{{ publishedDate }}</i> <i class="text-secondary">{{ city ?? "Віддалено" }}</i>
      <span v-if="dashboard"><i class="ms-3" v-if="salary"><b>₴{{ salary }}</b></i></span>
    </p>
    <p v-if="!dashboard">{{ summary }}</p>
    <small class="text-danger" v-if="dashboard && !published"><b>Знято з публікації</b></small>
  </article>
</template>

<script>
import { months } from "@/services/util";
import EditIcon from "@/components/icons/EditIcon.vue";
import VisibilityIcon from "@/components/icons/VisibilityIcon.vue";
import DeleteIcon from "@/components/icons/DeleteIcon.vue";
import { deleteById, setPublishedState } from "@/services/vacancies";

export default {
  name: "VacancyItem",
  components: { DeleteIcon, VisibilityIcon, EditIcon },
  props: ["id", "title", "description", "summary", "company", "publishedAt", "city", "salary", "dashboard", "published"],
  data() {
    return {
      publishedLoading: false,
      deleteLoading: false
    }
  },
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
    deleteVacancy() {
      this.deleteLoading = true;
      deleteById(this.id)
        .then(res => {
          this.$emit('deleted-vacancy');
        })
        .catch(err => {
          // TODO: alert
        })
        .finally(() => {
          this.deleteLoading = false;
        });
    }
  }
};
</script>

<style scoped>
.salary {
    font-size: 75%;
}

.not-published h5>*:not(.btn-group),
.not-published p {
    opacity: .5;
}
</style>