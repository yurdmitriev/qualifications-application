<script>
import { defineComponent } from "vue";
import RecentVacancies from "@/components/content/RecentVacancies.vue";
import RecentEvents from "@/components/content/RecentEvents.vue";

export default defineComponent({
  components: { RecentEvents, RecentVacancies }
});
</script>

<template>
  <main>
    <div class="container">
      <div class="row gap-5">
        <RecentEvents class="col-lg" />
        <RecentVacancies class="col-lg"/>
      </div>
    </div>
  </main>
</template>

<style scoped lang="scss">

</style>
