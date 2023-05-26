<template>
  <section>
    <h2 class="text-center">Останні вакансії</h2>
    <div class="text-center mt-5" v-if="loading">
      <div class="spinner-border text-primary" role="status">
        <span class="visually-hidden">Loading...</span>
      </div>
    </div>
    <div class="list-group mt-5 gap-2" v-else>
      <VacancyItem v-for="item in items" v-bind:key="item.id" :id="item.id" :title="item.title" :city="item.city"
                   :description="item.description" :salary="item.salary" :published-at="item.publishDate"
                   :company="item.company.title" :summary="item.summary" :published="item.published" />
    </div>
    <div class="row" v-if="showMore">
      <RouterLink to="/vacancies" class="btn btn-primary w-auto mx-auto mt-3">Переглянути більше</RouterLink>
    </div>
  </section>
</template>

<script>
import { listPublishedVacancies } from "@/services/vacancies";
import VacancyItem from "@/components/content/VacancyItem.vue";

export default {
  name: "RecentVacancies",
  components: { VacancyItem },
  data() {
    return {
      items: [],
      loading: false,
      showMore: false
    };
  },
  beforeMount() {
    this.loading = true;
    listPublishedVacancies(1, 4).then(res => {
      const response = res.data;
      this.items = response.items;
      if (response.totalPages > 1) {
        this.showMore = true;
      }
    }).catch(() => {
      // TODO: catch error
    }).finally(() => {
      this.loading = false;
    });
  }
};
</script>

<style scoped>
</style>