<template>
  <main>
    <div class="container">
      <BreadcrumbsComponent />
      <div class="row gx-md-5 gy-3 align-items-start mt-2 mx-0">
        <section class="card col-md-4 col-lg-3 p-0 filters">
          <div class="card-body p-4 list-group-flush">
            <MicroCredentialsComponent :editable="true" v-model="filterCompetencies" />
          </div>
        </section>
        <div class="text-center col-md-8 col-lg-9 my-auto mt-5 mt-md-0" v-if="loading">
          <div class="spinner-border text-primary" role="status">
            <span class="visually-hidden">Loading...</span>
          </div>
        </div>
        <section class="col-md-8 col-lg-9 ms-auto" v-else>
          <div class="list-group gap-3">
            <EventItem v-for="item in items" v-bind:key="item.id" :id="item.id" :title="item.title" :city="item.city"
                       :start-date="item.startDate" :company="item.company.title" :summary="item.summary"
                       :published="item.published" />
          </div>
          <PaginationGroup v-if="pagination.max > 1" @page="updateList" :current-page="pagination.current"
                           :total-pages="pagination.max" />
        </section>
      </div>
    </div>
  </main>
</template>

<script>
import BreadcrumbsComponent from "@/components/content/BreadcrumbsComponent.vue";
import PaginationGroup from "@/components/content/PaginationGroup.vue";
import EventItem from "@/components/content/EventItem.vue";
import { listPublishedEvents } from "@/services/events";
import MicroCredentialsComponent from "@/components/content/MicroCredentialsComponent.vue";

export default {
  name: "VacanciesListView",
  components: { MicroCredentialsComponent, EventItem, PaginationGroup, BreadcrumbsComponent },
  data() {
    return {
      items: [],
      filterCompetencies: {},
      loading: false,
      defaultSize: 6,
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