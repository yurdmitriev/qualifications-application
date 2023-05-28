importScripts('https://www.gstatic.com/firebasejs/9.22.1/firebase-app-compat.js');
importScripts('https://www.gstatic.com/firebasejs/9.22.1/firebase-messaging-compat.js');

firebase.initializeApp({
  apiKey: "AIzaSyBUQSTW4Hpc6jKUt468WUv_RO117KOPrd8",
  authDomain: "microcredentials-proj.firebaseapp.com",
  projectId: "microcredentials-proj",
  storageBucket: "microcredentials-proj.appspot.com",
  messagingSenderId: "371834642626",
  appId: "1:371834642626:web:5250011b7bce4d396a1b5b",
  measurementId: "G-2QLGEC32Y6"
});
const swMessaging = firebase.messaging();

swMessaging.onBackgroundMessage((payload) => {
  console.log('[firebase-messaging-sw.js] Received background message ', payload);
  // Customize notification here
  const notificationTitle = payload.notification.title;
  const notificationOptions = {
    body: payload.notification.body,
  };

  self.registration.showNotification(notificationTitle,
    notificationOptions);
});

// onMessage(messaging, (payload) => {
//   console.log('Message received. ', payload);
//   const notificationTitle = 'Foreground Message Title';
//   const notificationOptions = {
//     body: 'Foreground Message body.'
//   };
//
//   self.registration.showNotification(notificationTitle,
//     notificationOptions);
// });