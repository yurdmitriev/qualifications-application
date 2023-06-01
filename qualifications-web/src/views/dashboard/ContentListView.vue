<template>
  <div class="container">
    <header class="d-flex align-items-center justify-content-between">
      <h1 v-if="useUserStore().info.role === 'COMPANY'">Наші пропозиції</h1>
      <h1 v-else-if="useUserStore().info.role === 'ADMIN'">Створені пропозиції</h1>
      <div>
        <RouterLink to="/dashboard/content/new" class="btn btn-primary flex-grow-0">Нова пропозиція</RouterLink>
      </div>
    </header>
    <div class="row gap-3 mt-4">
      <DashboardEventsList class="col-lg" />
      <DashboardVacanciesList class="col-lg" />
    </div>
  </div>
</template>

<script>
import DashboardEventsList from "@/views/dashboard/DashboardEventsList.vue";
import DashboardVacanciesList from "@/views/dashboard/DashboardVacanciesList.vue";
import { useUserStore } from "@/stores/user";

export default {
  name: "ContentListView",
  methods: { useUserStore },
  components: { DashboardVacanciesList, DashboardEventsList }
};
</script>

<style scoped>

</style>