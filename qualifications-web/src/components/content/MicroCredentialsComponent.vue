<template>
  <div>
    <h5><b>Необхідні навички:</b></h5>
    <div class="mt-3">
      <input class="form-control form-control-sm" type="text" placeholder="Пошук за назвою...">
    </div>
    <div class="mt-4" v-for="item in competencies" :key="item.id">
      <h6><b>{{ item.title }}</b></h6>
      <div class="d-flex flex-wrap gap-2">
        <span class="badge d-flex p-2 align-items-center text-bg-primary rounded-pill" v-for="badge in item.items" :key="badge">
          <span class="px-1">{{ badge }}</span>
          <span role="button"><CloseIcon /></span>
        </span>
      </div>
    </div>
  </div>
</template>

<script>
import CloseIcon from "@/components/icons/CloseIcon.vue";

export default {
  name: "MicroCredentialsComponent",
  components: { CloseIcon },
  data() {
    return {
      competencies: [
        {
          title: "PHP",
          items: ["Laravel", "Symphony", "Composer", "OpenSwoole"]
        },
        {
          title: "Relation Databases",
          items: ["MySQL", "PostgreSQL"]
        }
      ]
    };
  }
};
</script>

<style scoped lang="scss">
input[type=text] {
  max-width: 300px;
}
</style>