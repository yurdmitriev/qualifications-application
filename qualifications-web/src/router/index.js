import { createRouter, createWebHistory } from "vue-router";
import HomeView from "../views/HomeView.vue";
import ProfileView from "@/views/ProfileView.vue";
import LoginView from "@/views/LoginView.vue";
import DashboardView from "@/views/DashboardView.vue";
import NotFoundView from "@/views/NotFoundView.vue";
import RegisterView from "@/views/RegisterView.vue";
import ContentView from "@/views/ContentView.vue";
import ContentListView from "@/views/ContentListView.vue";
import { useUserStore } from "@/stores/user";

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: "/",
      name: "home",
      component: HomeView,
      meta: {
        title: 'Головна'
      }
    },
    {
      path: "/login",
      name: "login",
      component: LoginView
    },
    {
      path: "/register",
      name: "register",
      component: RegisterView
    },
    {
      path: "/profile",
      name: "profile",
      component: ProfileView,
      meta: {
        parent: 'home',
        title: 'Профіль користувача'
      }
    },
    {
      path: "/dashboard",
      name: "dashboard",
      component: DashboardView,
      meta: {
        title: "Панель керування",
        roles: ['ADMIN', 'COMPANY']
      }
    },
    {
      path: "/vacancies",
      name: "vacancies",
      component: ContentListView,
      props: { showEvents: false },
      meta: {
        parent: 'home',
        title: 'Вакансії'
      }
    },
    {
      path: "/events",
      name: "events",
      component: ContentListView,
      props: { showEvents: true },
      meta: {
        parent: 'home',
        title: 'Події'
      }
    },
    {
      path: "/vacancies/:id",
      name: "vacancy",
      component: ContentView,
      props: true,
      meta: {
        parent: 'vacancies',
        title: 'Вакансія'
      }
    },
    {
      path: "/:pathMatch(.*)*",
      name: "404",
      component: NotFoundView
    }
  ]
});

router.beforeEach((to, from) => {
  const expires = localStorage.getItem("expires")
  const now = Date.now();

  if (+expires <= now) {
    const userStore = useUserStore();
    userStore.resetUser();
    localStorage.removeItem("token");
    localStorage.removeItem("expires");
  }

  return true;
})

export default router;
