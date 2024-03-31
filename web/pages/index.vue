<template>
  <div>
    <h1>Hello World Application</h1>

    <p>{{ response.data }}</p>
  </div>
</template>

<script setup lang="ts">
const response = await useFetch('/api/hello-world-db', {
  baseURL: "http://localhost:8000",
});
</script>