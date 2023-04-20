<template>
  <nav aria-label="breadcrumb">
    <ol class="breadcrumb">
      <li class="breadcrumb-item" :class="{active: path.length === index + 1}" v-for="(el, index) in path"
          :key="el.name">
        <RouterLink v-if="path.length !== index + 1" :to="el.path">{{ el.meta.title ?? "Попередня сторінка" }}
        </RouterLink>
        <span v-else>{{ currentTitle ?? el.meta.title ?? "Поточна сторінка" }}</span>
      </li>
    </ol>
  </nav>
</template>

<script>
export default {
  name: "BreadcrumbsComponent",
  props: {
    current: String,
    currentTitle: String
  },
  methods: {
    getRoute(name) {
      return this.$router.resolve({ name: name });
    }
  },
  computed: {
    path() {
      const result = [];
      let currentRoute = this.getRoute(this.current ?? this.$route.name);

      do {
        if (currentRoute) {
          result.unshift(currentRoute);
          const parent = currentRoute.meta.parent ?? null;
          currentRoute = parent ? this.getRoute(parent) : null;
        } else {
          break;
        }
      } while (currentRoute);

      return result;
    }
  }
};
</script>

<style scoped>

</style>