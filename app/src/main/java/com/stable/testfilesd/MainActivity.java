package com.stable.testfilesd;

import android.content.Context;
import android.os.Environment;
import android.os.storage.StorageManager;
import android.os.storage.StorageVolume;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView TX = findViewById(R.id.xi);
        StorageManager sm=(StorageManager)getSystemService(Context.STORAGE_SERVICE);
        StorageVolume volume = sm.getPrimaryStorageVolume();

            String TEXTO = sm.getStorageVolumes().toString();

        List<StorageVolume> volumes=sm.getStorageVolumes();
        for (StorageVolume volume1 : volumes) {


            TEXTO = TEXTO +"..."+volume1.getState();
            TX.setText(TEXTO);
        }
        boolean mExternalStorageAvailable = false;
        boolean mExternalStorageWriteable = false;
        String state = Environment.getExternalStorageState();

        if (Environment.MEDIA_MOUNTED.equals(state)) {
            // We can read and write the media
            mExternalStorageAvailable = mExternalStorageWriteable = true;
        } else if (Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
            // We can only read the media
            mExternalStorageAvailable = true;
            mExternalStorageWriteable = false;
        } else {
            // Something else is wrong. It may be one of many other states, but all we need
            //  to know is we can neither read nor write
            mExternalStorageAvailable = mExternalStorageWriteable = false;
        }

        File sdCard = Environment.getExternalStorageDirectory();

        //String Path = Environment.getExternalStorageDirectory().getAbsolutePath();
        String Path = "/storage/3532-3434/txt/";
        //File dir = new File(Path + "/txt/uno.txt");

        if(isExternalStorageWritable()){


        }


        if (this.checkSDCardStatus()) {
            File file = Environment.getExternalStorageDirectory();
            Toast.makeText(this, file.getAbsolutePath(), Toast.LENGTH_LONG).show();
        }

        File dir =new File(Path,"MyFolder6");




        if(!dir.exists())
        {
            dir.mkdirs();
        }

        File f=new File(dir ,"w5.txt");
        if(!f.exists())
        {
            try {
                f.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        //String secStore = System.getenv("SECONDARY_STORAGE");
      //  File f_secs = new File(secStore);



        try{
            FileOutputStream fos = new FileOutputStream(f);
            String data ="Hola es un test 5";


            fos.write(data.getBytes());
            fos.close();

        }catch (FileNotFoundException e){
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }



        Toast.makeText(this, Environment.getDataDirectory().getPath(), Toast.LENGTH_LONG).show();

        String removableStoragePath="";
        File fileList[] = new File("/storage/3532-3434/").listFiles();
        for (File file : fileList)
        {


            if(!file.getAbsolutePath().equalsIgnoreCase(Environment.getExternalStorageDirectory().getAbsolutePath()) && file.isDirectory() && file.canRead())
            removableStoragePath += file.getAbsolutePath();

        }
        Toast.makeText(this, removableStoragePath, Toast.LENGTH_LONG).show();
    }

    /* Checks if external storage is available for read and write */
    public boolean isExternalStorageWritable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            return true;
        }
        return false;
    }

    /* Checks if external storage is available to at least read */
    public boolean isExternalStorageReadable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state) ||
                Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
            return true;
        }
        return false;
    }
    private boolean checkSDCardStatus() {
        String SDCardStatus = Environment.getExternalStorageState();

        // MEDIA_UNKNOWN: unrecognized SD card
        // MEDIA_REMOVED: no SD card
        // MEDIA_UNMOUNTED: SD card exists but not mounted, deprecated in Android 4.0+
        // MEDIA_CHECKING: preparing SD card
        // MEDIA_MOUNTED: mounted and ready to use
        // MEDIA_MOUNTED_READ_ONLY
        switch (SDCardStatus) {
            case Environment.MEDIA_MOUNTED:
                return true;
            case Environment.MEDIA_MOUNTED_READ_ONLY:
                Toast.makeText(this, "SD card is ready only.", Toast.LENGTH_LONG).show();
                return false;
            default:
                Toast.makeText(this, "SD card is not available.", Toast.LENGTH_LONG).show();
                return false;
        }
    }
}


