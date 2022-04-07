package com.poundland.retail.appUtils.file;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.poundland.retail.enumeratio.FileType;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.PatternSyntaxException;

public class FileUtils {
    Context context;
    public String folderRoot = "/swoopos";
    public String ROOT_PATH = Environment.getExternalStorageDirectory() + folderRoot + "/";
    public String CUSTOMER_SCREEN_PATH = ROOT_PATH + "customerScreen/";
    public String SLIDER_IMG_PATH = CUSTOMER_SCREEN_PATH + "SliderImage/";
    public String IMAGE = "image/";
    public String CATEGOTY_IMAGE_PATH = ROOT_PATH + "CategoryImage/";
    public String CATEGOTY_MODIFIER_PATH = ROOT_PATH + "ModifierImage/";
    public String VIDEO = "video/";

    private final List<String> videoSupported = new ArrayList<String>() {
        {
            add("3gp");
            add("mp4");
            add("webm");
            add("mkv");
        }
    };
    private final List<String> audioSupported = new ArrayList<String>() {
        {
            add("3gp");
            add("m4a");
            add("aac");
            add("flac");
            add("gsm");
            add("mp3");
            add("wav");
            add("ogg");
        }
    };
    private final List<String> imageSupported = new ArrayList<String>() {
        {
            add("bmp");
            add("gif");
            add("jpg");
            add("png");
            add("webp");
            add("heic");
            add("heif");
        }
    };

    public FileUtils(Context context) {
        this.context = context;
    }

    public String getCustomerSliderFolderPath() {
        return folderRoot + "/customerScreen/" + IMAGE;
    }

    public String createCustomerSliderFolder() {
        String folderPath = CUSTOMER_SCREEN_PATH + IMAGE;
        File dir = new File(CUSTOMER_SCREEN_PATH);
        if (!dir.exists())
            dir.mkdirs();

        File file = new File(folderPath);

        if (!file.exists()) {
            if (!file.mkdirs()) {
                Log.d("App", "failed to create directory");
            }
        }
        return folderPath;
    }

    public String getCategoryImageFolderPath() {
        return folderRoot + "/CategoryImage/"+ IMAGE;
    }

    public String createCategoryImagePath() {
        String folderPath = CATEGOTY_IMAGE_PATH+ IMAGE;
        File dir = new File(CATEGOTY_IMAGE_PATH);
        if (!dir.exists())
            dir.mkdirs();

        File file = new File(folderPath);

        if (!file.exists()) {
            if (!file.mkdirs()) {
                Log.d("App", "failed to create directory");
            }
        }
        return folderPath;
    }

    public String getModifierImageFolderPath() {
        return folderRoot + "/ModifierImage/"+ IMAGE;
    }

    public String createModifierImagePath() {
        String folderPath = CATEGOTY_MODIFIER_PATH+ IMAGE;
        File dir = new File(CATEGOTY_MODIFIER_PATH);
        if (!dir.exists())
            dir.mkdirs();

        File file = new File(folderPath);

        if (!file.exists()) {
            if (!file.mkdirs()) {
                Log.d("App", "failed to create directory");
            }
        }
        return folderPath;
    }

    public boolean saveImageDrawableToSdCard(Context context, int drawable, String fileName) {
        Bitmap bitmap;
        OutputStream output;

        // Retrieve the image from the res folder
        bitmap = BitmapFactory.decodeResource(((AppCompatActivity) context).getResources(), drawable);

        // Find the SD Card path
        File filepath = Environment.getExternalStorageDirectory();

        // Create a new folder in SD Card
        File dir = new File(CUSTOMER_SCREEN_PATH);
        if (!dir.exists())
            dir.mkdirs();

        // Create a name for the saved image
        File file = new File(dir, fileName);

        try {

            output = new FileOutputStream(file);

            // Compress into png format image from 0% - 100%
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, output);
            output.flush();
            output.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public String getExtension(File file) {
        return getExtension(file.getName());
    }

    public String getExtension(String name) {
        String ext;
        if (name.lastIndexOf(".") == -1) {
            ext = "";

        } else {
            int index = name.lastIndexOf(".");
            ext = name.substring(index + 1, name.length());
        }
        return ext;
    }

    public String getFileName(String file) {
        return file.substring(file.lastIndexOf('/') + 1);
    }

    public String getRemovedExtensionName(String name) {
        String baseName;
        if (name.lastIndexOf(".") == -1) {
            baseName = name;

        } else {
            int index = name.lastIndexOf(".");
            baseName = name.substring(0, index);
        }
        return baseName;
    }

    public FileType getFileType(String file) {
        if (imageSupported.contains(getExtension(file))) {
            return FileType.IMAGE;
        } else if (videoSupported.contains(getExtension(file))) {
            return FileType.VIDEO;
        } else if (audioSupported.contains(getExtension(file))) {
            return FileType.AUDIO;
        } else {
            return FileType.UNDEFINED;
        }
    }

    public File getChangedExtensionFile(File file, String extension, boolean overwrite) {
        File newFile = null;
        String baseName = null;
        if (file.getName().lastIndexOf(".") == -1) {
            baseName = file.getName();

        } else {
            int index = file.getName().lastIndexOf(".");
            baseName = file.getName().substring(0, index);
        }
        String bName = baseName;
        if (!extension.equals("")) {
            bName += "." + extension;
        }
        newFile = new File(file.getParent(), bName);
        int index = 1;


        if (!overwrite) {
            while (newFile.exists()) {
                String specific = "(" + index + ")";
                String tmpName = baseName + specific;
                if (!extension.equals("")) {
                    tmpName += "." + extension;
                }
                newFile = new File(file.getParent(), tmpName);
                index++;
            }
        }
        return newFile;
    }

    public final String SCHME_FILE = "file";
    public final String SCHME_FILE_AND_SEPARATOR = "file://";

    public String uriToPath(Uri uri) {
        if (uri.getScheme().equals(SCHME_FILE)) {
            return uri.getPath();
        }
        return null;
    }

    public Uri pathToUri(String path) {
        return Uri.parse(SCHME_FILE_AND_SEPARATOR + path);
    }

    public boolean isFileUri(String uri) {
        return uri != null && uri.startsWith(SCHME_FILE_AND_SEPARATOR);
    }

    public String uriToPath(String uri) {
        if (isFileUri(uri)) {
            return uri.substring(SCHME_FILE_AND_SEPARATOR.length());
        }
        return null;
    }

    public File saveImageToSdCard(String fileName) {
        File file = null;
        // Create a new folder in SD Card
        File dir = new File(SLIDER_IMG_PATH);
        if (!dir.exists())
            dir.mkdirs();

        // Create a name for the saved image
        file = new File(dir, fileName);

        return file;
    }

    public ArrayList<String> getFileNames(final String folder, final String fileNameFilterPattern, final int sort) throws PatternSyntaxException {
        ArrayList<String> myData = new ArrayList<String>();
        File fileDir = new File(folder);
        if (!fileDir.exists() || !fileDir.isDirectory()) {
            return null;
        }

        String[] files = fileDir.list();

        if (files.length == 0) {
            return null;
        }
        for (int i = 0; i < files.length; i++) {
            if (fileNameFilterPattern == null || files[i].matches(fileNameFilterPattern))
                myData.add(files[i]);
            else {
                myData.add(files[i]);
            }
        }
        if (myData.size() == 0)
            return null;

        if (sort != 0) {
            Collections.sort(myData, String.CASE_INSENSITIVE_ORDER);
            if (sort < 0)
                Collections.reverse(myData);
        }

        return myData;
    }

    public boolean copyFile
            (String fromFilePath, String toFilePath, final boolean overwriteExisting)
            throws IOException {
        try {
            File fromFile = new File(fromFilePath);
            File toFile = new File(toFilePath);
            if (overwriteExisting && toFile.exists())
                toFile.delete();
            return copyFile(fromFile, toFile);
        } catch (SecurityException e) {
            return false;
        }
    }

    public boolean copyFile(File source, File dest)
            throws IOException {
        FileChannel in = null;
        FileChannel out = null;
        try {
            in = new FileInputStream(source).getChannel();
            out = new FileOutputStream(dest).getChannel();

            long size = in.size();
            MappedByteBuffer buf = in.map(FileChannel.MapMode.READ_ONLY, 0, size);

            out.write(buf);

            if (in != null)
                in.close();
            if (out != null)
                out.close();
            return true;
        } catch (IOException e) {
            try {
                if (in != null)
                    in.close();
            } catch (IOException e2) {
            }
            try {
                if (out != null)
                    out.close();
            } catch (IOException e2) {
            }
            throw e;
        }
    }

}
