<script>
import { defineComponent } from "vue";
import RecentVacancies from "@/components/content/RecentVacancies.vue";

export default defineComponent({
  components: { RecentVacancies }
});
</script>

<template>
  <main>
    <div class="container">
      <div class="row gap-5">
        <div class="col-lg"></div>
        <RecentVacancies class="col-lg"/>
      </div>
    </div>
  </main>
</template>

<style scoped lang="scss">

</style>
