package com.example.alert_notification_firebase;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;
import java.io.FileInputStream;
import java.io.IOException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

@Configuration
public class FirebaseConfig {

  @Bean
  public FirebaseApp initializeFirebase() throws IOException {
    FileInputStream serviceAccount =
        new FileInputStream("src/main/resources/serviceKey.json");

    FirebaseOptions options = new FirebaseOptions.Builder()
        .setCredentials(GoogleCredentials.fromStream(serviceAccount))
        .setDatabaseUrl("https://notification-alert-6085f-default-rtdb.firebaseio.com/")
        .build();

    return FirebaseApp.initializeApp(options);
  }

  @Bean
  @Lazy
  public Firestore firestore(FirebaseApp firebaseApp) {
    return FirestoreClient.getFirestore(firebaseApp);
  }
}
