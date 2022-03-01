package com.example.pdfdownload;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button startDownload;
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startDownload = findViewById(R.id.startDownload);
        startDownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int[] ids = new int[]{R.id.pdfLink1,R.id.pdfLink2,R.id.pdfLink3,R.id.pdfLink4,R.id.pdfLink5};
                for(int id : ids){
                    EditText t = (EditText) findViewById(id);
                    String text = t.getText().toString();
                    if(!text.isEmpty() && text.endsWith(".pdf")){
                        try {
                            startDownload(text);
                        }catch (Exception e){
                            Log.e("Exception",e.getMessage());
                        }

                    }
                    t.setText("");
                }
            }
        });

    }
    private  void startDownload(String url){
    DownloadManager.Request request= new DownloadManager.Request(Uri.parse(url));
    request.setTitle(url);
    request.allowScanningByMediaScanner();
    request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
    request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS,"tempfile.pdf");
    DownloadManager downloadManager = (DownloadManager)getSystemService(Context.DOWNLOAD_SERVICE);
    request.setMimeType("application/pdf");
    request.allowScanningByMediaScanner();
    request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_MOBILE | DownloadManager.Request.NETWORK_WIFI);
    downloadManager.enqueue(request);
}

}