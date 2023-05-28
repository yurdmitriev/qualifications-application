import { initializeApp } from "firebase/app";
import { getMessaging, getToken } from "firebase/messaging";

function initApp() {
  const firebaseConfig = {
    apiKey: "AIzaSyBUQSTW4Hpc6jKUt468WUv_RO117KOPrd8",
    authDomain: "microcredentials-proj.firebaseapp.com",
    projectId: "microcredentials-proj",
    storageBucket: "microcredentials-proj.appspot.com",
    messagingSenderId: "371834642626",
    appId: "1:371834642626:web:5250011b7bce4d396a1b5b",
    measurementId: "G-2QLGEC32Y6"
  };

  return initializeApp(firebaseConfig);
}

const firebaseApp = initApp();
const messagingRef = getMessaging(firebaseApp);

const messaging = {
  getToken: () => {
    return getToken(messagingRef, { vapidKey: "BKoK7Q_tI-jefkPMWgKM1haEvQ3RCUCRaClYunRA5FkPY2B_VsZcXbWGO8E-SOS0o1ShVly3n33k3szbYGpJwzk" });
  },
  ref: messagingRef,
  install(app) {
    app.config.globalProperties.$messaging = {
      ref: messagingRef
    };
  }
};

export default messaging;