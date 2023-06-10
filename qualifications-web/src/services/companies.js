import { http } from "@/plugins/axios";

const token = localStorage.getItem("token") ?? undefined;

export const getCompaniesList = async () => {
  return await http(token).get("/api/companies");
}