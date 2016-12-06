package cz.borcizfitu.hackbest.gcm;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.WorkerThread;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Utility class for gcm.
 * Created by Jan Stanek[jan.stanek@ackee.cz] on {6.12.16}
 **/
public class GcmUtils {
    private static final String API_KEY = "AAAA7At-ytc:APA91bF0FgCaoqr2fvZKaUG8FQtywlp_4goTkMLHX0A5RMbgr49o7TVnH-fTR4cvZyqHgQmBdIVJldRjckDfhCIrVyybtT_Nymoyg7mVPkLUZf6fAOEIZ6Z6zhlMhCMx7Wefp7BSEUQiANtLdbml0P1Ida9ZWzX3PA";


    public static final String GCM_CONTENT = "gcm_content";

    public static JSONObject createNotification(@NonNull String message) throws JSONException {
        JSONObject notification = new JSONObject();
        notification.put("body", message);
        return notification;
    }

    public static Bundle createBundleFromNotification(@NonNull String notification) {
        Bundle toReturn = new Bundle();
        toReturn.putString(GCM_CONTENT, notification);
        return toReturn;
    }

    @WorkerThread
    public static void sendMessage(@NonNull JSONObject notification, @NonNull String receiver) throws Exception {
        HttpURLConnection conn = null;
        OutputStream outputStream = null;

        try {
            // Prepare JSON containing the GCM message content. What to send and where to send.
            JSONObject jGcmData = new JSONObject();
            jGcmData.put("to", receiver);
            jGcmData.put("priority", "high");
            jGcmData.put("notification", notification);

            // Create connection to send GCM Message request.
            URL url = new URL("https://fcm.googleapis.com/fcm/send");
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestProperty("Authorization", "key=" + API_KEY);
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);

            // Send GCM message content.
            outputStream = conn.getOutputStream();
            outputStream.write(jGcmData.toString().getBytes());

            InputStream inputStream = conn.getInputStream();
            Log.d("KK", "K");
        } finally {
            if (conn != null) {
                conn.disconnect();
            }
            if (outputStream != null) {
                outputStream.close();
            }
        }
    }
}
