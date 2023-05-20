<template>
  <div class="container">
    <header class="d-flex align-items-center justify-content-between">
      <h1>Наші пропозиції</h1>
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

export default {
  name: "ContentListView",
  components: { DashboardVacanciesList, DashboardEventsList }
};
</script>

<style scoped>

</style>