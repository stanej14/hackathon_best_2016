package cz.borcizfitu.hackbest.client;

import android.app.Activity;
import android.os.Environment;
import android.util.Log;

import com.dropbox.core.DbxException;
import com.dropbox.core.v2.DbxClientV2;
import com.dropbox.core.v2.files.WriteMode;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import rx.Observable;
import rx.schedulers.Schedulers;

/**
 * Created by krata on 6.12.16.
 */

public class ApiInterface {
    public static void sendFile(String filepath, String filename) {
        Observable.just(filename)
                .subscribeOn(Schedulers.io())
                .subscribe(mainPresenter -> {
                    try (InputStream inputStream = new FileInputStream(filepath)) {
                        DbxClientV2 dbxClientV2 = DropboxClientFactory.getClient();

                        dbxClientV2.files().uploadBuilder("/data/" + filename)
                                .withMode(WriteMode.OVERWRITE)
                                .uploadAndFinish(inputStream);
                    } catch (DbxException | IOException e) {
                        e.printStackTrace();
                    }
                }, Throwable::printStackTrace);
    }
}
