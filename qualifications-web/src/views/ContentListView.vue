<template>
  <main>
    <div class="container">
      <BreadcrumbsComponent />
      <div class="row gx-md-5 gy-3 align-items-start mt-2 mx-0">
        <section class="card col-md-4 col-lg-3 p-0 filters">
          <div class="card-body p-4 list-group-flush">
            <div class="list-group-item">
              <h6><b>Тип пропозиції:</b></h6>
              <div class="form-check">
                <input class="form-check-input" type="radio" id="radio_events" :value="true" v-model="eventsSelected">
                <label class="form-check-label" for="radio_events">
                  Події
                </label>
              </div>
              <div class="form-check">
                <input class="form-check-input" type="radio" id="radio_vacancies" :value="false"
                       v-model="eventsSelected">
                <label class="form-check-label" for="radio_vacancies">
                  Вакансії
                </label>
              </div>
            </div>
          </div>
        </section>
        <div class="text-center col-md-8 col-lg-9 my-auto mt-5 mt-md-0" v-if="loading">
          <div class="spinner-border text-primary" role="status">
            <span class="visually-hidden">Loading...</span>
          </div>
        </div>
        <section class="col-md-8 col-lg-9 ms-auto" v-else>
          <div class="list-group gap-3">
            <VacancyItem v-for="item in items" v-bind:key="item.id" :id="item.id" :title="item.title" :city="item.city"
                         :description="item.description" :salary="item.salary" :published-at="item.publishDate"
                         :company="item.company.title" :summary="item.summary" />
          </div>
          <PaginationGroup v-if="pagination.max > 1" @page="updateList" :current-page="pagination.current"
                           :total-pages="pagination.max" />
        </section>
      </div>
    </div>
  </main>
</template>

<script>
import { listVacancies } from "@/services/vacancies";
import VacancyItem from "@/components/content/VacancyItem.vue";
import PaginationGroup from "@/components/content/PaginationGroup.vue";
import BreadcrumbsComponent from "@/components/content/BreadcrumbsComponent.vue";

export default {
  name: "ContentListView",
  components: { BreadcrumbsComponent, PaginationGroup, VacancyItem },
  props: {
    showEvents: Boolean
  },
  data() {
    return {
      eventsSelected: this.showEvents ?? true,
      items: [],
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

      const method = !this.eventsSelected ? listVacancies : (page, size) => {
        console.log(`${page} ${size}`);
      };

      method(page, this.defaultSize)
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
  watch: {
    eventsSelected(newEventsSelected, oldEventsSelected) {
      if (newEventsSelected !== oldEventsSelected) {
        this.loadItems(1);
      }
    }
  },
  beforeMount() {
    this.loadItems(1);
  }
};
</script>

<style scoped>
</style>