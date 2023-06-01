import { http } from "@/plugins/axios";

const token = localStorage.getItem("token") ?? undefined;

export const searchCompetencies = async (query) => {
  return await http().get(`/api/competencies?q=${query}`);
}