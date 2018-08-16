package imageloading.com.imageloadingstuff.activity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import imageloading.com.imagedownloadingcachinglibrary.imageUtils.ImageLoader;
import imageloading.com.imagedownloadingcachinglibrary.imageUtils.TCImageLoader;
import imageloading.com.imageloadingstuff.R;


public class MainActivity extends AppCompatActivity {

    private ImageView imgView;

    private ProgressBar imageProgress;
    ImageLoader imgLoader;
    TCImageLoader img;

    String[] fruitArray = {"http://i.imgur.com/DvpvklR.png", "http://www.androidbegin.com/wp-content/uploads/2013/07/HD-Logo.gif", "https://api.androidhive.info/images/sample.jpg",
                             "http://pixel.nymag.com/imgs/daily/vulture/2016/08/11/11-obama-sex-playlist.w529.h529.jpg","https://pbs.twimg.com/profile_images/556495456805453826/wKEOCDN0_400x400.png"};
    int i=0;


    public static final int REQUEST_PERMISSION_CALL_PHONE = 0x01;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(ContextCompat.checkSelfPermission(this,Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED ) {
            //if you dont have required permissions ask for it (only required for API 23+)
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_PERMISSION_CALL_PHONE);
        }else{
            initilizeImageLoader();
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode) {
            case REQUEST_PERMISSION_CALL_PHONE: {

                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    Log.d("permission","granted");
                    initilizeImageLoader();

                } else {
                    Toast.makeText(MainActivity.this, "Permission denied to read your External storage", Toast.LENGTH_SHORT).show();
                    onDestroy();
                }
            }
        }


    }


    private void initilizeImageLoader(){
        imgView = (ImageView) findViewById(R.id.imageView1);
        imageProgress = (ProgressBar)findViewById(R.id.progressBar) ;
        //imgLoader = new ImageLoader(this);
        img = new TCImageLoader(this);
    }

    public void btnLoadImageClick(View v){

        if(img!=null) {
            if(i < fruitArray.length) {
              // imgLoader.DisplayImage(fruitArray[i], imgView);
                img.display(fruitArray[i],imgView,R.drawable.user);
                i++;
            }
        }
    }
}
