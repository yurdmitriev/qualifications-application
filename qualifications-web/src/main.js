import { createApp } from "vue";
import { createPinia } from "pinia";

import App from "./App.vue";
import router from "./router";
import jwt from "@/plugins/jwt";

import "bootstrap/dist/css/bootstrap.css";
import "./assets/scss/main.scss";

const app = createApp(App);

app.use(createPinia());
app.use(router);
app.use(jwt)

app.mount("#app");

import "bootstrap/dist/js/bootstrap.js";