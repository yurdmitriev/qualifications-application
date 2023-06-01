<template>
  <div>
    <h5><b>{{ title ?? "Необхідні навички" }}:</b></h5>
    <div class="mt-3 input dropdown" v-if="editable">
      <input class="form-control form-control-sm" type="text" placeholder="Пошук за назвою..." data-bs-toggle="dropdown"
             data-bs-auto-close="outside" v-model="queryStr">
      <div class="dropdown-menu w-100 mt-1">
        <ul class="w-100" v-if="search_competencies.length > 0">
          <li v-for="item in search_competencies" :key="item.id">
            <input class="btn-check" type="checkbox" :id="`btn-check-${item.id}`" autocomplete="off"
            @change="event => inputChange(event, item)" :checked="isSelected(item.id)">
            <label :for="`btn-check-${item.id}`" class="dropdown-item form-check-label user-select-none">
              {{ item.title }}<span v-if="item.parent"> ({{ item.parent.title }})</span>
              <span class="badge rounded-pill text-bg-success ms-1">Обрано</span>
            </label>
          </li>
        </ul>
        <div class="text-center w-100" v-else-if="loading">
          <div class="spinner-border text-primary" role="status">
            <span class="visually-hidden">Loading...</span>
          </div>
        </div>
        <div class="text-center w-100 p-2" v-else>
          Результати пошуку з'являться тут
        </div>
      </div>
    </div>
    <div class="mt-4" v-for="item of value ?? {}"
         :key="item.id">
      <h6><b>{{ item.title }}</b></h6>
      <div class="d-flex flex-wrap gap-2">
        <span class="badge d-flex p-2 align-items-center text-bg-primary rounded-pill" v-for="badge in item.items"
              :key="badge.title">
          <span class="px-1">{{ badge.title }}</span>
          <span role="button" v-if="editable" @click="e => remove(badge)"><CloseIcon /></span>
        </span>
      </div>
    </div>
  </div>
</template>

<script>
import CloseIcon from "@/components/icons/CloseIcon.vue";
import { searchCompetencies } from "@/services/competencies";

export default {
  name: "MicroCredentialsComponent",
  components: { CloseIcon },
  emits: ['update:modelValue'],
  props: {
    editable: Boolean,
    showSearch: Boolean,
    competencies: Object,
    title: String,
    modelValue: Object
  },
  computed: {
    value: {
      get() {
        return this.modelValue
      },
      set(value) {
        this.$emit('update:modelValue', value)
      }
    }
  },
  data() {
    return {
      queryStr: "",
      loading: false,
      search_competencies: {},
      selected: {}
    };
  },
  watch: {
    queryStr(newValue, oldValue) {
      this.performSearch();
    }
  },
  methods: {
    performSearch() {
      const query = this.queryStr;

      if (query.length >= 2) {
        this.loading = true;
        searchCompetencies(this.queryStr)
          .then(res => {
            const result = res.data;
            this.search_competencies = result;
          })
          .catch(err => {

          })
          .finally(() => {
            this.loading = false;
          });
      } else {
        this.search_competencies = {};
      }
    },
    inputChange(event, item) {
      if (event.target.checked) {
        this.add(item);
      } else {
        this.remove(item);
      }
    },
    add(item) {
      if (item.parent) {
        if (!this.value[item.parent.id]) {
          this.value[item.parent.id] = {
            title: item.parent.title,
            items: []
          }
        }
        this.value[item.parent.id].items.push(item);
      } else {
        if (!this.value[10000]) {
          this.value[10000] = {
            title: "Загальні",
            items: []
          };
        }
        this.value[10000].items.push(item);
      }
    },
    remove(item) {
      for (const [key, value] of Object.entries(this.value)) {
        if (item.parent) {
          if (item.parent.id != key) continue;
          this.value[key].items = value.items.filter(el => el.id !== item.id);
        } else {
          if (key == 10000) {
            this.value[key].items = value.items.filter(el => el.id !== item.id);
          }
        }

        if (this.value[key].items.length === 0) {
          delete this.value[key];
        }
      }
    },
    isSelected(id) {
      for (const [key, value] of Object.entries(this.value)) {
        for (const item of value.items) {
          if (item.id == id) return true;
        }
      }
      return false;
    }
  }
};
</script>

<style scoped lang="scss">
.input {
  max-width: 300px;
}

.dropdown-menu ul {
  list-style: none;
  padding: 0;
  margin: 0;
}

.dropdown-item {
  &, * {
    cursor: pointer !important;
  }
}

label {
  white-space: normal;
}

input:not(:checked) ~ label .badge {
  display: none;
}
</style>