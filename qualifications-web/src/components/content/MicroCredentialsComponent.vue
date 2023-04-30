<template>
  <div>
    <h5><b>{{ title ?? "Необхідні навички" }}:</b></h5>
    <div class="mt-3" v-if="editable">
      <input class="form-control form-control-sm" type="text" placeholder="Пошук за назвою...">
    </div>
    <div class="mt-4" v-for="item in competencies ?? (mock ? mock_competencies : [])" :key="item.id">
      <h6><b>{{ item.title }}</b></h6>
      <div class="d-flex flex-wrap gap-2">
        <span class="badge d-flex p-2 align-items-center text-bg-primary rounded-pill" v-for="badge in item.items"
              :key="badge.title">
          <span class="px-1">{{ badge.title }}</span>
          <span role="button" v-if="editable"><CloseIcon /></span>
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
  props: {
    editable: Boolean,
    showSearch: Boolean,
    mock: Boolean,
    competencies: Array,
    title: String
  },
  data() {
    return {
      mock_competencies: [
        {
          title: "PHP",
          items: [
            {
              title: "Laravel"
            },
            {
              title: "Symphony"
            },
            {
              title: "Composer"
            },
            {
              title: "OpenSwoole"
            }
          ]
        },
        {
          title: "Relation Databases",
          items: [
            {
              title: "MySQL"
            },
            {
              title: "PostgreSQL"
            }
          ]
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