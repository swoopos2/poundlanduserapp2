package com.poundland.retail.appUtils;

import android.app.Activity;
import android.content.ClipData;
import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.provider.OpenableColumns;

import androidx.core.content.FileProvider;

import com.poundland.retail.R;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import static android.app.Activity.RESULT_OK;

/**
 * Created by king on 30/11/16.
 */
public class ImagePickerUtilNew {
    static File mFileCaptured;
    static Uri mImageCaptureUri;
    static ImagePickerListener imagePickerListener;
    static Activity context;
    static File fileProfilePic = null;
    static String tag;
    static File appDir;

    static int picWidth = 400;
    static int picHeight = 400;

    static boolean cropImage = false;
    static boolean compressImage = true;

    public static void pickFromCamera(Activity context, ImagePickerListener imagePickerListener, String tag, boolean compressImage) {
        ImagePickerUtilNew.tag = tag;
        ImagePickerUtilNew.compressImage = compressImage;
        cropImage = false;
        ImagePickerUtilNew.imagePickerListener = imagePickerListener;
        ImagePickerUtilNew.context = context;

        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        try {
            String state = Environment.getExternalStorageState();
            if (Environment.MEDIA_MOUNTED.equals(state)) {
                String appDirPath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/" + context.getString(R.string.app_name);
                appDir = new File(appDirPath);
                if (!appDir.exists()) {
                    appDir.mkdirs();
                }

                mFileCaptured = new File(appDir, tag + ".jpg");

                mImageCaptureUri = FileProvider.getUriForFile(context,
                        context.getApplicationContext().getPackageName() + ".provider",
                        mFileCaptured);
            }
            if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.LOLLIPOP) {
                intent.setClipData(ClipData.newRawUri("", mImageCaptureUri));
                intent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION | Intent.FLAG_GRANT_READ_URI_PERMISSION);
            }
            intent.putExtra(MediaStore.EXTRA_OUTPUT, mImageCaptureUri);
            intent.putExtra("return-data", true);
            context.startActivityForResult(intent, 10000);

        } catch (Exception e) {
            String error = e.getLocalizedMessage();
        }
    }

    public static void pickFromCameraWithCrop(Activity context, ImagePickerListener imagePickerListener, String tag) {
        try {
        ImagePickerUtilNew.tag = tag;
        cropImage = true;
        ImagePickerUtilNew.imagePickerListener = imagePickerListener;
        ImagePickerUtilNew.context = context;

      //  Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

            String state = Environment.getExternalStorageState();
            if (Environment.MEDIA_MOUNTED.equals(state)) {
                String appDirPath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/" + context.getString(R.string.app_name);
                appDir = new File(appDirPath);
                if (!appDir.exists()) {
                    appDir.mkdirs();
                }

                mFileCaptured = new File(appDir, tag + ".jpg");

                mImageCaptureUri = FileProvider.getUriForFile(context,
                        context.getApplicationContext().getPackageName() + ".provider",
                        mFileCaptured);
            }
            /*if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.LOLLIPOP) {
                intent.setClipData(ClipData.newRawUri("", mImageCaptureUri));
                intent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION | Intent.FLAG_GRANT_READ_URI_PERMISSION);
            }*/
            //intent.putExtra(MediaStore.EXTRA_OUTPUT, mImageCaptureUri);
            //intent.putExtra("return-data", true);
           // context.startActivityForResult(intent, 10000);
            CropImage.activity(/*picUri*//*mImageCaptureUri*/)
                    .setGuidelines(CropImageView.Guidelines.ON)
                    .setAspectRatio(1, 1)
                    .start(context);
        } catch (Exception e) {
            String error = e.getLocalizedMessage();
        }
    }

    public static void pickFromGallery(Activity context, ImagePickerListener imagePickerListener, String tag, boolean compressImage) {
        ImagePickerUtilNew.tag = tag;
        ImagePickerUtilNew.compressImage = compressImage;
        cropImage = false;
        ImagePickerUtilNew.imagePickerListener = imagePickerListener;
        ImagePickerUtilNew.context = context;

        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);//
        context.startActivityForResult(Intent.createChooser(intent, "Select File"), 20000);
    }

    public static void pickFromGalleryWithCrop(Activity context, ImagePickerListener imagePickerListener, String tag) {
        ImagePickerUtilNew.tag = tag;
        cropImage = true;
        ImagePickerUtilNew.imagePickerListener = imagePickerListener;
        ImagePickerUtilNew.context = context;

        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);//
        context.startActivityForResult(Intent.createChooser(intent, "Select File"), 20000);
    }


    public static void pickFile(Activity context, ImagePickerListener imagePickerListener, String tag) {

        ImagePickerUtilNew.tag = tag;
        ImagePickerUtilNew.imagePickerListener = imagePickerListener;
        ImagePickerUtilNew.context = context;

        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("*/*");
        context.startActivityForResult(Intent.createChooser(intent, "Select File"), 30000);
    }

    public static void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == RESULT_OK) {
                Uri resultUri = result.getUri();

                fileProfilePic = compressToFile(resultUri.getPath());
                imagePickerListener.onImagePicked(fileProfilePic, tag);

            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                Exception error = result.getError();
            }
        }

        if (resultCode == RESULT_OK) {
            if (requestCode == 10000) {
                Bitmap bm = null;
                if (cropImage) {
                    //  Uri picUri = data.getData();
                    /* CropImage.activity(*//*picUri*//**//*mImageCaptureUri*//*)
                            .setGuidelines(CropImageView.Guidelines.ON)
                            .setAspectRatio(1, 1)
                            .start(context);*/
                } else {
                    if (ImagePickerUtilNew.compressImage) {
                        mFileCaptured = writeToFile(mImageCaptureUri);
                        mFileCaptured = compressToExistingFile(mFileCaptured.getPath(), mFileCaptured);
                        tag = mFileCaptured.getAbsolutePath();
                        imagePickerListener.onImagePicked(mFileCaptured, tag);
                    } else {
                        mFileCaptured = writeToFile(mImageCaptureUri);
                        imagePickerListener.onImagePicked(mFileCaptured, tag);
                    }
                }

            } else if (requestCode == 10001) {
                imagePickerListener.onImagePicked(fileProfilePic, tag);
            } else if (requestCode == 20000) {
                Bitmap bm = null;
                if (data != null) {
                    if (cropImage) {
                        CropImage.activity(data.getData())
                                .setAspectRatio(1, 1)
                                .start(context);
                    } else {
                        if (ImagePickerUtilNew.compressImage) {
                            fileProfilePic = writeToFile(data.getData());
                            fileProfilePic = compressToFile(fileProfilePic.getPath());
                            tag = fileProfilePic.getAbsolutePath();
                            imagePickerListener.onImagePicked(fileProfilePic, tag);
                        } else {
                            fileProfilePic = writeToFile(data.getData());
                            tag = fileProfilePic.getAbsolutePath();
                            imagePickerListener.onImagePicked(fileProfilePic, tag);
                        }
                    }
                }
            } else if (requestCode == 20001) {
                imagePickerListener.onImagePicked(fileProfilePic, tag);
            } else if (requestCode == 30000) {
                if (data != null) {
                    try {
                        File selectedFile = null;
                        Uri uri = data.getData();
                        String fileName = queryName(context.getContentResolver(), uri);
                        String filenameArray[] = fileName.split("\\.");
                        String extension = filenameArray[filenameArray.length - 1];
                        fileName = tag + "." + extension;

                        InputStream input = context.getContentResolver().openInputStream(uri);
                        try {
                            selectedFile = new File(context.getFilesDir(), fileName);
                            OutputStream output = new FileOutputStream(selectedFile);
                            try {
                                byte[] buffer = new byte[4 * 1024]; // or other buffer size
                                int read;
                                while ((read = input.read(buffer)) != -1) {
                                    output.write(buffer, 0, read);
                                }
                                output.flush();
                            } finally {
                                output.close();
                            }
                        } finally {
                            input.close();
                        }
                        fileProfilePic = selectedFile;
                        imagePickerListener.onImagePicked(fileProfilePic, tag);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    private static File writeToFile(Uri fileUri) {
        File selectedFile = null;
        String fileName = queryName(context.getContentResolver(), fileUri);
        String filenameArray[] = fileName.split("\\.");
        String extension = filenameArray[filenameArray.length - 1];
        fileName = tag + "." + extension;

        try {
            InputStream input = context.getContentResolver().openInputStream(fileUri);
            selectedFile = new File(context.getFilesDir(), fileName);
            OutputStream output = new FileOutputStream(selectedFile);

            byte[] buffer = new byte[4 * 1024]; // or other buffer size
            int read;
            while ((read = input.read(buffer)) != -1) {
                output.write(buffer, 0, read);
            }
            output.flush();
            output.close();
            input.close();

            if (fileName.contains(".jpg") || fileName.contains(".JPG")
                    || fileName.contains(".jpeg") || fileName.contains(".JPEG")
                    || fileName.contains(".png") || fileName.contains(".PNG")) {

                Bitmap bm = decodeFile(selectedFile.getAbsolutePath(), 800, 800, ScalingLogic.CROP);
                bm = fixOrientation(bm, selectedFile.getAbsolutePath());
                FileOutputStream outputStream = new FileOutputStream(selectedFile);
                bm.compress(Bitmap.CompressFormat.PNG, 100, outputStream);
                outputStream.flush();
                outputStream.close();
            }
        } catch (Exception e) {
            return null;
        }
        return selectedFile;
    }

    private static File compressToExistingFile(String filePath, File file) {
        try {
            Bitmap bm = decodeFile(filePath, picWidth, picHeight, ScalingLogic.CROP);
            bm = fixOrientation(bm, filePath);
            FileOutputStream outputStream = new FileOutputStream(file);
            bm.compress(Bitmap.CompressFormat.PNG, 100, outputStream);
            outputStream.flush();
            outputStream.close();
        } catch (Exception e) {
            return null;
        }
        return file;
    }

    private static File compressToFile(String filePath) {
        File selectedFile = null;
        try {
            Bitmap bm = decodeFile(filePath, picWidth, picHeight, ScalingLogic.CROP);
            bm = fixOrientation(bm, filePath);
            FileOutputStream outputStream;

            selectedFile = new File(context.getFilesDir(), tag + ".jpg");
            outputStream = new FileOutputStream(selectedFile);
            bm.compress(Bitmap.CompressFormat.PNG, 100, outputStream);
            outputStream.flush();
            outputStream.close();
        } catch (Exception e) {
            return null;
        }
        return selectedFile;
    }

    public static Bitmap decodeFile(String path, int dstWidth, int dstHeight, ScalingLogic scalingLogic) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(path, options);
        options.inJustDecodeBounds = false;
        options.inSampleSize = calculateSampleSize(options.outWidth, options.outHeight, dstWidth,
                dstHeight, scalingLogic);
        Bitmap unscaledBitmap = BitmapFactory.decodeFile(path, options);
        return unscaledBitmap;
    }

    public static enum ScalingLogic {
        CROP, FIT
    }

    public static int calculateSampleSize(int srcWidth, int srcHeight, int dstWidth, int dstHeight, ScalingLogic scalingLogic) {
        if (scalingLogic == ScalingLogic.FIT) {
            final float srcAspect = (float) srcWidth / (float) srcHeight;
            final float dstAspect = (float) dstWidth / (float) dstHeight;

            if (srcAspect > dstAspect) {
                return srcWidth / dstWidth;
            } else {
                return srcHeight / dstHeight;
            }
        } else {
            final float srcAspect = (float) srcWidth / (float) srcHeight;
            final float dstAspect = (float) dstWidth / (float) dstHeight;

            if (srcAspect > dstAspect) {
                return srcHeight / dstHeight;
            } else {
                return srcWidth / dstWidth;
            }
        }
    }

    private static void deleteDirRecursive(File fileOrDirectory) {
        if (fileOrDirectory.isDirectory())
            for (File child : fileOrDirectory.listFiles())
                deleteDirRecursive(child);

        fileOrDirectory.delete();
    }

    private static String queryName(ContentResolver resolver, Uri uri) {
        if (uri.getScheme().equalsIgnoreCase("content")) {
            Cursor returnCursor =
                    resolver.query(uri, null, null, null, null);
            assert returnCursor != null;
            int nameIndex = returnCursor.getColumnIndex(OpenableColumns.DISPLAY_NAME);
            returnCursor.moveToFirst();
            String name = returnCursor.getString(nameIndex);
            returnCursor.close();
            return name;
        } else {
            String filenameArray[] = uri.toString().split("\\/");
            String fileName = filenameArray[filenameArray.length - 1];
            return fileName;
        }
    }


    public static Bitmap fixOrientation(Bitmap bm, String filePath) {
        Bitmap bitmap = null;
        ExifInterface ei = null;

        try {
            ei = new ExifInterface(filePath);
            if (ei != null) {
                int orientation = ei.getAttributeInt(ExifInterface.TAG_ORIENTATION,
                        ExifInterface.ORIENTATION_UNDEFINED);
                switch (orientation) {
                    case ExifInterface.ORIENTATION_ROTATE_90:
                        bitmap = rotateImage(bm, 90);
                        break;
                    case ExifInterface.ORIENTATION_ROTATE_180:
                        bitmap = rotateImage(bm, 180);
                        break;
                    case ExifInterface.ORIENTATION_ROTATE_270:
                        bitmap = rotateImage(bm, 270);
                        break;
                    case ExifInterface.ORIENTATION_NORMAL:
                        bitmap = bm;
                        break;
                 /*   case ExifInterface.ORIENTATION_UNDEFINED:
                       // if(bm.getWidth()>bm.getHeight())
                        bitmap = rotateImage(bm, 0);
                        break;*/
                    default:
                        bitmap = bm;
                        break;
                }
                if (bitmap != null)
                    return bitmap;
                else
                    return bm;
            } else {
                return bm;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return bm;
        }
    }

    public static Bitmap rotateImage(Bitmap source, float angle) {

        Matrix matrix = new Matrix();
        matrix.postRotate(angle);
        return Bitmap.createBitmap(source, 0, 0, source.getWidth(), source.getHeight(), matrix,
                true);
    }

    public interface ImagePickerListener {
        public void onImagePicked(File imageFile, String tag);
    }
}
