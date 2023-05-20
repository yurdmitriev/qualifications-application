<template>
  <section>
    <div class="text-center my-auto mt-5 mt-md-0" v-if="loading">
      <div class="spinner-border text-primary" role="status">
        <span class="visually-hidden">Loading...</span>
      </div>
    </div>
    <section v-else>
      <div class="list-group">
        <EventItem v-for="item in items" v-bind:key="item.id" :id="item.id" :title="item.title" :city="item.city"
                   :start-date="item.startDate" :dashboard="true" />
      </div>
      <PaginationGroup v-if="pagination.max > 1" @page="updateList" :current-page="pagination.current"
                       :total-pages="pagination.max" />
    </section>
  </section>
</template>

<script>
import PaginationGroup from "@/components/content/PaginationGroup.vue";
import EventItem from "@/components/content/EventItem.vue";
import { listPublishedEvents } from "@/services/events";

export default {
  name: "DashboardEventsList",
  components: { EventItem, PaginationGroup },
  data() {
    return {
      items: [],
      loading: false,
      defaultSize: 10,
      pagination: {
        current: 1,
        max: 1
      }
    };
  },
  methods: {
    updateList(value) {
      if (this.pagination.current !== value) {
        this.loadItems(value);
        this.pagination.current = value;
      }
    },
    loadItems(page) {
      this.loading = true;

      listPublishedEvents(page, this.defaultSize)
        .then(res => {
          const response = res.data;
          this.items = response.items;
          this.pagination.max = response.totalPages ?? 1;
        })
        .catch(err => {
          // TODO: throw error
        })
        .finally(() => {
          this.loading = false;
        });
    }
  },
  beforeMount() {
    this.loadItems(1);
  }
};
</script>

<style scoped>

</style>