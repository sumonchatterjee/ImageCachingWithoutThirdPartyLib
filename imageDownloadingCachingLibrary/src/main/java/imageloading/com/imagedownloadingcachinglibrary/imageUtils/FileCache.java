package imageloading.com.imagedownloadingcachinglibrary.imageUtils;

import android.content.Context;

import java.io.File;
import java.net.URLEncoder;

public class FileCache {

    private File cacheDir;

    public FileCache(Context context){
        //Find the dir to save cached images
      /* if (android.os.Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED))
            cacheDir=new File(android.os.Environment.getExternalStorageDirectory(),"TTImages_cache");
        else
            cacheDir=context.getCacheDir();
        if(!cacheDir.exists())
            cacheDir.mkdirs();*/


        File root =null;
        if (android.os.Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED)){
            root = android.os.Environment.getExternalStorageDirectory();
        }
        cacheDir = new File(root.getAbsolutePath() + "/download");
        if(!cacheDir.exists())
            cacheDir.mkdirs();

    }

    public File getFile(String url){
        String filename = URLEncoder.encode(url);
        File f = new File(cacheDir, filename);
        return f;

    }

    public void clear(){
        File[] files=cacheDir.listFiles();
        if(files==null)
            return;
        for(File f:files)
            f.delete();
    }
}
