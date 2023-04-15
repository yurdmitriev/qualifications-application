<template>
  <main>
    <div class="container">
      <nav aria-label="breadcrumb">
        <ol class="breadcrumb">
          <li class="breadcrumb-item">
            <RouterLink to="/">Головна</RouterLink>
          </li>
          <li class="breadcrumb-item active" aria-current="page">Профіль</li>
        </ol>
      </nav>

      <h1>That's my profile page</h1>
    </div>
  </main>
</template>

<script>
export default {
  name: "ProfileView"
};
</script>

<style scoped>

</style>