<template>
  <div class="text-center" v-if="loading">
    <div class="spinner-border text-primary" role="status">
      <span class="visually-hidden">Loading...</span>
    </div>
  </div>
  <section v-else>
    <div></div>
    <h2 class="text-center">Найближчі події</h2>
    <div class="list-group mt-5 gap-2">
      <EventItem v-for="item in items" v-bind:key="item.id" :id="item.id" :title="item.title" :city="item.city"
                 :start-date="item.startDate" :company="item.company.title" :summary="item.summary" />
    </div>
    <div class="row" v-if="showMore">
      <RouterLink to="/vacancies" class="btn btn-primary w-auto mx-auto mt-3">Переглянути більше</RouterLink>
    </div>
  </section>
</template>

<script>
import EventItem from "@/components/content/EventItem.vue";
import { listPublishedEvents } from "@/services/events";

export default {
  name: "RecentEvents",
  components: { EventItem },
  data() {
    return {
      items: [],
      loading: false,
      showMore: false
    };
  },
  beforeMount() {
    this.loading = true;
    listPublishedEvents(1, 4).then(res => {
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