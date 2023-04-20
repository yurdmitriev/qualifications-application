<template>
  <nav aria-label="Page navigation">
    <ul class="pagination justify-content-center mt-4">
      <li class="page-item" v-for="page in pages" v-bind:key="page">
        <a class="page-link" :class="{active: page === current}"
           :href="url(page)" @click.prevent="() => {
             current = page;
             $emit('page', current);
           }">{{ page }}</a>
      </li>
    </ul>
  </nav>
</template>

<script>
export default {
  name: "PaginationGroup",
  emits:['page'],
  props: {
    totalPages: Number,
    currentPage: Number
  },
  data() {
    return {
      current: this.currentPage ?? 1,
      pages: []
    };
  },
  methods: {
    setupList() {
      this.pages = [];
      for (let i = 0; i < this.totalPages; i++) {
        this.pages.push(i + 1);
      }
    },
    url(page) {
      const url = new URL(window.location.protocol + '//' + window.location.hostname + this.$route.path);
      url.searchParams.set('page', page);

      return url.toString();
    }
  },
  computed: {
  },
  watch: {
    current(newCurrent, oldCurrent) {
      if (newCurrent !== oldCurrent) {
        this.setupList();
      }

      return newCurrent;
    }
  },
  beforeMount() {
    this.setupList();
  }
};
</script>

<style scoped>

</style>