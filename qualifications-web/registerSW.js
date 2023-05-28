if ("serviceWorker" in navigator) {
  window.addEventListener("load", () => {
    // navigator.serviceWorker.register("/sw.js", { scope: "/" });
    // navigator.serviceWorker.register("/firebase-messaging-sw.js", { type: 'module', scope: "/" });
  });
}