import { http } from "@/plugins/axios";

const token = localStorage.getItem("token") ?? undefined;

export const listPublishedEvents = async (page, size) => {
  return await http().get(`/api/events?size=${size}&page=${page}`);
}

export const getPublishedEventById = async (id) => {
  return await http().get(`/api/events/${id}`);
};