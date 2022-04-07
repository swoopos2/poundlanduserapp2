package com.poundland.retail.appUtils;


import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.location.Address;
import android.location.Geocoder;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.text.format.DateUtils;
import android.util.Base64;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.widget.ListPopupWindow;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.facebook.AccessToken;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.HttpMethod;
import com.facebook.login.LoginManager;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.FirebaseApp;
import com.poundland.retail.BuildConfig;
import com.poundland.retail.R;
import com.poundland.retail.activity.LoginActivity;
import com.poundland.retail.apiUtils.ApiRequestUrl;
import com.poundland.retail.interfaces.Constants;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static android.content.ContentValues.TAG;
import static com.poundland.retail.interfaces.Constants.DD_MMMM_YYYY_space_in_midlle;
import static com.poundland.retail.interfaces.Constants.DD_MMMM_YYYY_space_in_midlle_HHmm;
import static com.poundland.retail.interfaces.Constants.DD_MMM_YYYY_dash_in_middle;
import static com.poundland.retail.interfaces.Constants.DD_MMM_YYYY_space_in_midlle;
import static com.poundland.retail.interfaces.Constants.DD_MMM_YYYY_space_in_midlle_HHmm;
import static com.poundland.retail.interfaces.Constants.EEEE;
import static com.poundland.retail.interfaces.Constants.EEE_dd_MMM;
import static com.poundland.retail.interfaces.Constants.EEE_dd_MMM_YYYY;
import static com.poundland.retail.interfaces.Constants.EEE_dd_MMM_YYYY_HH_mm;
import static com.poundland.retail.interfaces.Constants.FB;
import static com.poundland.retail.interfaces.Constants.GMAIL;
import static com.poundland.retail.interfaces.Constants.HHmmss;
import static com.poundland.retail.interfaces.Constants.LOGIN_TYPE;
import static com.poundland.retail.interfaces.Constants.YYYY_MM_DD;
import static com.poundland.retail.interfaces.Constants.YYYY_MM_dd_HH_mm;
import static com.poundland.retail.interfaces.Constants.YYYY_MM_dd_HH_mm_ss;


/**
 * Created by Shakti Singh Rajput on 6/22/18.
 */

public class HelperClass {
    private static Snackbar snackbar;
    private static Dialog dialog;
    private static Toast mToast;
    private static boolean isApplied;
    private static String mobilePattern = "[0-9]{10-12}";
    private static String password_patter = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$";
    private static ListPopupWindow popupWindow;
    private static boolean isPopUp;
    private static GoogleSignInClient mGoogleSignInClient;
    public static SimpleDateFormat yyyy_MM_dd_HH_mm = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault());
    public static SimpleDateFormat yyyy_MM_dd_HH_mm_ss = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
    public static SimpleDateFormat HHmm = new SimpleDateFormat("HH:mm", Locale.getDefault());
    public static SimpleDateFormat MM_dd_HHmm = new SimpleDateFormat("MM-dd HH:mm", Locale.getDefault());
    public static SimpleDateFormat yyyy_MM_dd = new SimpleDateFormat("yyyy-MM-dd");
    public static SimpleDateFormat dayText = new SimpleDateFormat("EEE");
    public static SimpleDateFormat monthText = new SimpleDateFormat("MMM");
    public static SimpleDateFormat YEAR_ONLY_FORMATE = new SimpleDateFormat("yyyy");
    public static SimpleDateFormat dayMonth = new SimpleDateFormat("dd MMM");
    public static SimpleDateFormat dayMonthyyyy = new SimpleDateFormat("dd-MMM-yyyy");

    private void setTextViewDrawableColor(TextView textView, int color) {

        for (Drawable drawable : textView.getCompoundDrawables()) {
            if (drawable != null) {
                drawable.setColorFilter(new PorterDuffColorFilter(color, PorterDuff.Mode.SRC_IN));
            }
        }
    }

    public static void toast(Context context, String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }

    public static void log(String tag, String msg) {
        Log.e(tag, msg);
    }

    public static void showSnackBar(View view1, String message) {
        try {
            snackbar = Snackbar.make(view1, message, Snackbar.LENGTH_LONG);
            snackbar.show();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static boolean hasPermissions(Context context, String... permissions) {
        if (context != null && permissions != null) {
            for (String permission : permissions) {
                if (ActivityCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
                    return false;
                }
            }
        }
        return true;
    }

    public static int calculateAge(long date) {
        Calendar dob = Calendar.getInstance();
        dob.setTimeInMillis(date);
        Calendar today = Calendar.getInstance();
        int age = today.get(Calendar.YEAR) - dob.get(Calendar.YEAR);
        if (today.get(Calendar.DAY_OF_MONTH) < dob.get(Calendar.DAY_OF_MONTH)) {
            age--;
        }
        HelperClass.log("age", "" + age);
        return age;
    }

    public static long getDateDiff(SimpleDateFormat format, String today, String endDate) {
        try {
            return TimeUnit.DAYS.convert(format.parse(endDate).getTime() - format.parse(today).getTime(), TimeUnit.MILLISECONDS);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public static long printDifference(Date startDate, Date endDate) {
        //milliseconds
        long different = endDate.getTime() - startDate.getTime();
        log("different : ", "" + different + "  startDate" + startDate + " endDate" + endDate);
        long secondsInMilli = 1000;
        long minutesInMilli = secondsInMilli * 60;
        long hoursInMilli = minutesInMilli * 60;
        long daysInMilli = hoursInMilli * 24;

        long elapsedDays = different / daysInMilli;
        different = different % daysInMilli;
        return elapsedDays;
    }


    private static void dialContactPhone(Context context, final String phoneNumber) {
        context.startActivity(new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", phoneNumber, null)));
    }

    /*for checking the validation of the email format  entered by the user*/
    public static boolean isValidEmail(String emailAddress) {
        return Patterns.EMAIL_ADDRESS.matcher(emailAddress).matches();
    }

    public static boolean isValidMobile(String mobile) {
        if (TextUtils.isEmpty(mobile)) {
            return false;
        }
        Pattern pattern = Pattern.compile(new String(mobilePattern));
        Matcher matcher = pattern.matcher(mobile);
        return matcher.matches();
    }

    public static boolean isValidPassword(String password) {
        Pattern pattern = Pattern.compile(new String(password_patter));
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }

    public static boolean isValidName(String name) {
        if (TextUtils.isEmpty(name)) {
            return false;
        }

        //Pattern pattern = Pattern.compile(new String("^[a-zA-Z0-9\\s]*$"));
        Pattern pattern = Pattern.compile(new String("^[a-zA-Z\\s]*$"));
        Matcher matcher = pattern.matcher(name);
        return matcher.matches();
    }

    public static int displayWidth(Context context) {
        DisplayMetrics displaymetrics = new DisplayMetrics();
        ((Activity) context).getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        int height = displaymetrics.heightPixels;
        int width = displaymetrics.widthPixels;
        return width;
    }

    public static void setTextViewDrawableColor(final EditText editText, final int color) {
        for (Drawable drawable : editText.getCompoundDrawables()) {
            if (drawable != null) {
                drawable.setColorFilter(new PorterDuffColorFilter(color, PorterDuff.Mode.SRC_IN));
            }
        }
    }

    public static void setTextViewTintDrawableColor(TextView textView, int color) {

        for (Drawable drawable : textView.getCompoundDrawables()) {
            if (drawable != null) {
                drawable.setColorFilter(new PorterDuffColorFilter(color, PorterDuff.Mode.SRC_IN));
            }
        }
    }

    public static void setTextViewTintDrawableColorInAdapter(TextView textView, int color) {

        for (Drawable drawable : textView.getCompoundDrawablesRelative()) {
            if (drawable != null) {
                drawable.setColorFilter(new PorterDuffColorFilter(color, PorterDuff.Mode.SRC_IN));
            }
        }

    }


    public static String getImagePath(Uri uri, Activity activity) {
        Cursor cursor = activity.getContentResolver().query(uri, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
            String document_id = cursor.getString(0);
            document_id = document_id.substring(document_id.lastIndexOf(":") + 1);
            cursor.close();

            cursor = activity.getContentResolver().query(
                    MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                    null, MediaStore.Images.Media._ID + " = ? ", new String[]{document_id}, null);
            cursor.moveToFirst();
            String path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
            cursor.close();
            return path;
        } else {
            return uri.getPath();
        }
    }

    /* for setting fragment in the container */
    public static void setFragment(Fragment fragment, boolean removeStack, FragmentActivity activity, int mContainer) {
        FragmentManager fragmentManager = activity.getSupportFragmentManager();
        FragmentTransaction ftTransaction = fragmentManager.beginTransaction();
        if (removeStack) {
            int size = fragmentManager.getBackStackEntryCount();
            fragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
            ftTransaction.replace(mContainer, fragment);
        } else {
            ftTransaction.replace(mContainer, fragment);
            ftTransaction.addToBackStack(null);
        }
        ftTransaction.commit();

    }

    public static void hideKeyboard(Activity activity) {
        try {
            InputMethodManager in = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
            View view = activity.findViewById(android.R.id.content);
            if (in != null) {
                in.hideSoftInputFromWindow(view.getWindowToken(),
                        InputMethodManager.HIDE_NOT_ALWAYS);
            }
        } catch (Throwable e) {
        }

    }

    public static void openKeyboard(Activity activity, EditText editText) {
        try {
            InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.showSoftInput(editText, InputMethodManager.SHOW_FORCED);
        } catch (Throwable e) {
        }

    }

    public static String formatDateEEEtype(String inputDate) {
        String outputDate = null;
        SimpleDateFormat inputFormat = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.getDefault());
        SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        Date date = null;
        try {
            date = inputFormat.parse(inputDate);
            outputDate = outputFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return outputDate;
    }


    public static String getDaysName(String inputStringData) {
        String outPutDaysNAme = "";
        try {
            SimpleDateFormat inFormat = new SimpleDateFormat(YYYY_MM_dd_HH_mm_ss);
            Date date = inFormat.parse(inputStringData);
            SimpleDateFormat outFormat = new SimpleDateFormat(EEEE);
            outPutDaysNAme = outFormat.format(date);
        } catch (Exception exc) {
            exc.printStackTrace();
        }
        return outPutDaysNAme;
    }

    public static String getHH_mm_from_YYYY_MM_dd_HH_mm(String inputStringData) {
        String outPutDaysNAme = "";
        try {
            SimpleDateFormat inFormat = new SimpleDateFormat(YYYY_MM_dd_HH_mm);
            Date date = inFormat.parse(inputStringData);
            SimpleDateFormat outFormat = HHmm;
            outPutDaysNAme = outFormat.format(date);
        } catch (Exception exc) {
            exc.printStackTrace();
        }
        return outPutDaysNAme;
    }

    public static String getDaysName_input_YYYY_MM_dd_HH_mm(String inputStringData) {
        String outPutDaysNAme = "";
        try {
            SimpleDateFormat inFormat = new SimpleDateFormat(YYYY_MM_dd_HH_mm);
            Date date = inFormat.parse(inputStringData);
            SimpleDateFormat outFormat = new SimpleDateFormat("EEEE");
            outPutDaysNAme = outFormat.format(date);
        } catch (Exception exc) {
            exc.printStackTrace();
        }


        return outPutDaysNAme;
    }

    public static String getDateOnly(String inputStringData) {
        String outPutDaysNAme = "";
        try {
            SimpleDateFormat inFormat = new SimpleDateFormat(YYYY_MM_dd_HH_mm_ss);
            Date date = inFormat.parse(inputStringData);
            SimpleDateFormat outFormat = new SimpleDateFormat("dd");
            outPutDaysNAme = outFormat.format(date);
        } catch (Exception exc) {
            exc.printStackTrace();
        }


        return outPutDaysNAme;
    }

    public static String getMonthOnly(String inputStringData) {
        String outPutDaysNAme = "";
        try {
            SimpleDateFormat inFormat = new SimpleDateFormat(YYYY_MM_dd_HH_mm_ss);
            Date date = inFormat.parse(inputStringData);
            SimpleDateFormat outFormat = monthText;
            outPutDaysNAme = outFormat.format(date);
        } catch (Exception exc) {
            exc.printStackTrace();
        }


        return outPutDaysNAme;
    }

    public static String getYearOnly(String inputStringData) {
        String outPutDaysNAme = "";
        try {
            SimpleDateFormat inFormat = new SimpleDateFormat(YYYY_MM_dd_HH_mm_ss);
            Date date = inFormat.parse(inputStringData);
            SimpleDateFormat outFormat = YEAR_ONLY_FORMATE;
            outPutDaysNAme = outFormat.format(date);
        } catch (Exception exc) {
            exc.printStackTrace();
        }


        return outPutDaysNAme;
    }

    public static String getDaysNameFromYYYY_MM_DD(String inputStringData) {
        String outPutDaysNAme = "";
        try {

            SimpleDateFormat inputFormat = new SimpleDateFormat(YYYY_MM_DD, Locale.getDefault());
            Date date = inputFormat.parse(inputStringData);
            SimpleDateFormat outFormat = new SimpleDateFormat("EEEE");
            outPutDaysNAme = outFormat.format(date);
        } catch (Exception exc) {
            exc.printStackTrace();
        }


        return outPutDaysNAme;
    }

    public static String getDayOfWeekString(int daysNameInInt) {
        String daysName = null;

        //  Calendar calendar = Calendar.getInstance();
        // int day = calendar.get(Calendar.DAY_OF_WEEK);
        switch (daysNameInInt) {
            case Calendar.SUNDAY:
                //  daysName = "Sun";
                daysName = "Mon";
                break;
            case Calendar.MONDAY:
                //  daysName = "Mon";
                daysName = "Tue";
                break;
            case Calendar.TUESDAY:
                // daysName = "Tue";
                daysName = "Wed";
                break;
            case Calendar.WEDNESDAY:
                //  daysName = "Wed";
                daysName = "Thu";
                break;
            case Calendar.THURSDAY:
                //  daysName = "Thu";
                daysName = "Fri";
                break;
            case Calendar.FRIDAY:
                // daysName = "Fri";
                daysName = "Sat";
                break;
            case Calendar.SATURDAY:
                // daysName = "Sat";
                daysName = "Sun";
                break;
        }

        return daysName;
    }

    public static int getDayOfWeekInInt(String daysNameString) {
        int daysName = 1001;
        switch (daysNameString) {
            case "Sun":
                daysName = 1;
                break;
            case "Mon":
                daysName = 2;
                break;
            case "Tue":
                daysName = 3;
                break;
            case "Wed":
                daysName = 4;
                break;
            case "Thu":
                daysName = 5;
                break;
            case "Fri":
                daysName = 6;
                break;
            case "Sat":
                daysName = 7;
                break;
        }

        return daysName;
    }

    public static int getDayOfWeekInInt(int daysNameString) {
        int daysName = 1001;
        switch (daysNameString) {
            case 1:
                daysName = 7;
                break;
            case 2:
                daysName = 1;
                break;
            case 3:
                daysName = 2;
                break;
            case 4:
                daysName = 3;
                break;
            case 5:
                daysName = 4;
                break;
            case 6:
                daysName = 5;
                break;
            case 7:
                daysName = 6;
                break;
        }

        return daysName;
    }

    public static String formatDate(Date inputDate, SimpleDateFormat format) {
        return format.format(inputDate);
    }

    public static Date formatDateIntoString(String inputDate, SimpleDateFormat format) {
        Date date = null;
        try {
            date = format.parse(inputDate);
            System.out.println(date);

        } catch (ParseException e) {
            e.printStackTrace();
        }

        return date;
    }

    public static String formatDate(String inputDate) {     // 22/12/1992
        String outputDate = null;
        SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.getDefault());
        SimpleDateFormat outputFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        Date date = null;
        try {
            date = inputFormat.parse(inputDate);
            outputDate = outputFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return outputDate;
    }

    public static String formatDateAndTime(String inputDate) {     // 22/12/1992
        String outputDate = null;
        SimpleDateFormat inputFormat = new SimpleDateFormat(YYYY_MM_dd_HH_mm_ss, Locale.getDefault());
        SimpleDateFormat outputFormat = new SimpleDateFormat(DD_MMM_YYYY_space_in_midlle_HHmm, Locale.getDefault());
        Date date = null;
        try {
            date = inputFormat.parse(inputDate);
            outputDate = outputFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return outputDate;
    }

    public static String formatDateMM(String inputDate) {     // 22/12/1992
        String outputDate = null;
        SimpleDateFormat inputFormat = new SimpleDateFormat(YYYY_MM_dd_HH_mm_ss, Locale.getDefault());
        SimpleDateFormat outputFormat = dayMonth;
        Date date = null;
        try {
            date = inputFormat.parse(inputDate);
            outputDate = outputFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return outputDate;
    }


    public static String formatDateyMMdd(String inputDate) {     // 2021-01-03
        String outputDate = null;
        SimpleDateFormat inputFormat = new SimpleDateFormat(EEE_dd_MMM_YYYY, Locale.getDefault());
        SimpleDateFormat outputFormat = new SimpleDateFormat(YYYY_MM_DD, Locale.getDefault());
        Date date = null;
        try {
            date = inputFormat.parse(inputDate);
            outputDate = outputFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return outputDate;
    }

    public static String formatddMMyyyy(String inputDate) {     // 2021-01-03 to o3 Jan 2021
        String outputDate = null;
        SimpleDateFormat inputFormat = new SimpleDateFormat(YYYY_MM_DD, Locale.getDefault());
        SimpleDateFormat outputFormat = new SimpleDateFormat(DD_MMM_YYYY_space_in_midlle, Locale.getDefault());
        Date date = null;
        try {
            date = inputFormat.parse(inputDate);
            outputDate = outputFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return outputDate;
    }

    public static String formatHrMinSec_TO_hrMin(String inputDate) {
        String outputDate = null;
        SimpleDateFormat inputFormat = new SimpleDateFormat(HHmmss, Locale.getDefault());
        SimpleDateFormat outputFormat = new SimpleDateFormat("HH:mm", Locale.getDefault());
        Date date = null;
        try {
            date = inputFormat.parse(inputDate);
            outputDate = outputFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return outputDate;
    }

    public static String formatddMMMMyyyy(String inputDate) {  // 2021-01-30 12:12:00
        if (inputDate == null) {
            return "";
        }
        String outputDate = "";
        try {
            SimpleDateFormat inputFormat = new SimpleDateFormat(YYYY_MM_dd_HH_mm_ss, Locale.getDefault());
            SimpleDateFormat outputFormat = new SimpleDateFormat(DD_MMMM_YYYY_space_in_midlle, Locale.getDefault());
            Date date = null;

            date = inputFormat.parse(inputDate);
            outputDate = outputFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return outputDate;
    }

    public static String formatddMMMMyyyyDayName(String inputDate) {  // 2021-01-30 12:12:00
        String outputDate = "";
        SimpleDateFormat inputFormat = new SimpleDateFormat(YYYY_MM_dd_HH_mm_ss, Locale.getDefault());
        SimpleDateFormat outputFormat = new SimpleDateFormat(EEE_dd_MMM_YYYY, Locale.getDefault());
        Date date = null;
        try {
            date = inputFormat.parse(inputDate);
            outputDate = outputFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return outputDate;
    }


    public static String formatddMMMMyyyyDayName_HHmm(String inputDate) {  // 2021-01-30 12:12:00
        String outputDate = "";
        SimpleDateFormat inputFormat = new SimpleDateFormat(YYYY_MM_dd_HH_mm_ss, Locale.getDefault());
        SimpleDateFormat outputFormat = new SimpleDateFormat(EEE_dd_MMM_YYYY_HH_mm, Locale.getDefault());
        Date date = null;
        try {
            date = inputFormat.parse(inputDate);
            outputDate = outputFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return outputDate;
    }

    public static String formatDateTimeTO_Time(String inputDate) {
        String outputDate = "";
        SimpleDateFormat inputFormat = new SimpleDateFormat(YYYY_MM_dd_HH_mm_ss, Locale.getDefault());
        SimpleDateFormat outputFormat = HHmm;
        Date date = null;
        try {
            date = inputFormat.parse(inputDate);
            outputDate = outputFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return outputDate;
    }

    public static String formatDateMonthHr(String inputDate) {
        String outputDate = "";
        SimpleDateFormat inputFormat = new SimpleDateFormat(YYYY_MM_dd_HH_mm_ss, Locale.getDefault());
        SimpleDateFormat outputFormat = new SimpleDateFormat(DD_MMMM_YYYY_space_in_midlle_HHmm, Locale.getDefault());
        Date date = null;
        try {
            date = inputFormat.parse(inputDate);
            outputDate = outputFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return outputDate;
    }


    public static String formatddMMMyyyy_EEE_dd_MMM(String inputDate) {  // 22-jan-1992 to EEE dd MMM(Wed 22 jan)
        String outputDate = "";
        SimpleDateFormat inputFormat = new SimpleDateFormat(DD_MMM_YYYY_dash_in_middle, Locale.getDefault());
        SimpleDateFormat outputFormat = new SimpleDateFormat(EEE_dd_MMM, Locale.getDefault());
        Date date = null;
        try {
            date = inputFormat.parse(inputDate);
            outputDate = outputFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return outputDate;
    }

    public static String formatDD_MMM_YYYY_to_YYYY_MM_DD(String inputDate) {  // 22-jan-1992 to EEE dd MMM(Wed 22 jan)
        String outputDate = "";
        SimpleDateFormat inputFormat = new SimpleDateFormat(DD_MMM_YYYY_dash_in_middle, Locale.getDefault());
        SimpleDateFormat outputFormat = new SimpleDateFormat(YYYY_MM_DD, Locale.getDefault());
        Date date = null;
        try {
            date = inputFormat.parse(inputDate);
            outputDate = outputFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return outputDate;
    }

    public static String getCurrentDate(String format) {
        return new SimpleDateFormat(format, Locale.getDefault()).format(new Date());
    }

    public static String getComparedDate_time(String dateInput) {  //  if currentDate today true , if currentDate  is yet to come false
        String result = "";
        SimpleDateFormat sdf = new SimpleDateFormat(YYYY_MM_dd_HH_mm_ss, Locale.getDefault());
        Date inputDate = null;

        try {
            Date current = sdf.parse(sdf.format(Calendar.getInstance().getTime()));
            inputDate = sdf.parse(dateInput);
            if (inputDate.before(current)) { // if input date passed from cureent time
                result = "PASSED";
            } else if (inputDate.after(current)) {  // // if input date yet to come from cureent time
                result = "YET TO START";
            } else if (inputDate.equals(current)) {
                result = "EQUAL";
            }
            Log.e("TTTTTT", inputDate + ": " + result + " * " + current);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return result;
    }

    public static boolean getComparedDate(String date) {  //  if strDate today true , if strDate  is yet to come false

        SimpleDateFormat sdf = new SimpleDateFormat(YYYY_MM_DD);
        Date strDate = null;
        try {
            strDate = sdf.parse(date);
            if (new Date().after(strDate)) {
                return true;
            } else {
                return false;
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return false;
    }

    ///**** startTime :17:27:00 , endTime: 17:30:00  >>  Result = true
    public static boolean getCheckTimings(String startTime, String endTime) {  // if startTime is smaller than endTime then true
        SimpleDateFormat sdf = HHmm;

        try {
            Date date1 = sdf.parse(startTime);
            Date date2 = sdf.parse(endTime);
            if (date1.before(date2)) {
                return true;
            } else {

                return false;
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return false;
    }


    public static String getDifferenceBetweenTime(String str_date) {
        //Calendar cal = Calendar.getInstance();
        // TimeZone tz = cal.getTimeZone();
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.getDefault());
        formatter.setTimeZone(TimeZone.getTimeZone("UTC"));
        Date date = null;
        try {
            date = formatter.parse(str_date);
            return DateUtils.getRelativeTimeSpanString(date.getTime(), System.currentTimeMillis(), 0).toString();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        System.out.println("Today is " + date.getTime());
        return "";

    }

    public static String getTime(String datetIime) {
        String outputDate = "";
        try {                                       /////   2020-01-02 08:00
            SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault());

            SimpleDateFormat outputFormat = new SimpleDateFormat("HH:mm", Locale.getDefault());
            Date date = null;

            date = inputFormat.parse(datetIime);
            outputDate = outputFormat.format(date);

        } catch (Exception j) {
            j.printStackTrace();
        }
        return outputDate;
    }


    /**
     * This method is to return duration in minute and seconds for the playing time
     *
     * @param seconds specifies the total millisceonds played
     */

    public static String formatPlayingDuration(int seconds) {
        // int seconds = milliseconds / 1000;
        int minutes = seconds / 60;
        int displayedSeconds = seconds % 60;
        if (minutes == 0)
            return "00:" + addZero(displayedSeconds);
        return addZero(minutes) + ":" + addZero(displayedSeconds);

    }

    /**
     * This method is to return number with zero or not zero i.e. to make a number in two digit
     *
     * @param number specifies the number that needs to be foramtted
     */
    private static String addZero(int number) {
        if (number < 10)
            return "0" + number;
        return "" + number;
    }

    public static boolean isInternetOn(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = null;
        if (cm != null
        ) {
            netInfo = cm.getActiveNetworkInfo();
        }
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }

    public static String getFcmToken(Context context) {
        FirebaseApp.initializeApp(context);
        // return FirebaseInstanceId.getInstance().getToken();

       /* FirebaseMessaging.getInstance().getToken().addOnCompleteListener(new OnCompleteListener<String>() {
            @Override
            public void onComplete(@NonNull Task<String> task) {

            }
        });*/

        return "";
    }

    /*------------------------------------------- Method to print Hash key-----------------------------------------------------*/
    public static void hashFromSHA1(String sha1) {
        String[] arr = sha1.split(":");
        byte[] byteArr = new byte[arr.length];

        for (int i = 0; i < arr.length; i++) {
            byteArr[i] = Integer.decode("0x" + arr[i]).byteValue();
        }

        Log.e("HASH KEY: ", Base64.encodeToString(byteArr, Base64.NO_WRAP));
        Log.e("HASH KEY - : ", "hash key from SHA1");
    }

    public static void printHashKey(Context pContext) {
        try {
            PackageInfo info = pContext.getPackageManager().getPackageInfo(pContext.getPackageName(), PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                String hashKey = new String(Base64.encode(md.digest(), 0));
                Log.e(TAG, " () Hash Key: " + hashKey);
            }
        } catch (NoSuchAlgorithmException e) {
            Log.e(TAG, "printHashKey()", e);
        } catch (Exception e) {
            Log.e(TAG, "printHashKey()", e);
        }
    }

    public static void logOut(Activity activity) {
        PrefManager prefManager = PrefManager.getInstance(activity);
        if (prefManager.getPreference(LOGIN_TYPE, "").equals(GMAIL)) {
            HelperClass.signOutGmail(activity);
        } else if (prefManager.getPreference(LOGIN_TYPE, "").equals(FB)) {
            signOutFb(activity);
        } else HelperClass.logoutUser(activity, "");
    }

    public static void logoutUser(Activity activity, String type) {
        PrefManager prefManager = PrefManager.getInstance(activity);
        prefManager.deleteAllPreference();
        if (type.equals("")) {
            try {
                activity.startActivity(new Intent(activity, LoginActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK));
                activity.finish();
            } catch (Exception ex) {
                ex.printStackTrace();
                Log.e("EXEP", "" + ex.getLocalizedMessage());
            }

        } else {

            Intent in = new Intent(activity, LoginActivity.class);
            in.putExtra(Constants.TITLE, type);
            in.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            activity.finish();
        }
    }

    public static void signOutGmail(final Activity activity) {
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(activity, gso);

        mGoogleSignInClient.signOut()
                .addOnCompleteListener(activity, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        logoutUser(activity, "");
                    }
                });
    }

    public static void signOutFb(final Activity activity) {

        if (AccessToken.getCurrentAccessToken() == null) {
            HelperClass.logoutUser(activity, "");
            return; // already logged out
        }

        new GraphRequest(AccessToken.getCurrentAccessToken(), "/me/permissions/", null, HttpMethod.DELETE, new GraphRequest
                .Callback() {
            @Override
            public void onCompleted(GraphResponse graphResponse) {

                LoginManager.getInstance().logOut();
                HelperClass.logoutUser(activity, "");

            }
        }).executeAsync();
    }

    public String getDurationString(int seconds) {
        //   int hours = seconds / 3600;
        int minutes = (seconds % 3600) / 60;
        seconds = seconds % 60;
        return twoDigitString(minutes) + " : " + twoDigitString(seconds);
    }

    /*-------------------------------------xxxxxxxxxxxxxxxxxx----------------------------------------------------------------------*/
    private String twoDigitString(int number) {
        if (number == 0) {
            return "00";
        }
        if (number / 10 == 0) {
            return "0" + number;
        }
        return String.valueOf(number);
    }

    /*  public void downloadFile(Context context, String url, String fileNameWithXtention, String desc, String title) {
          try {
              // String fileName = "99Invoice_invoice_" + Calendar.getInstance().get(Calendar.HOUR_OF_DAY) + ".xlsx";
              // String DownloadUrl = "http://gst-api.omnisttechhub.com/api/business/exportInvoiceData";
              //Description("99Invoice");   //appears the same in Notification bar while downloading
              //Title("99Invoice_invoice_.xlsx");
              DownloadManager.Request request1 = new DownloadManager.Request(Uri.parse(url));
              request1.setDescription(desc);   //appears the same in Notification bar while downloading
              request1.setTitle(title);
              request1.setVisibleInDownloadsUi(false);

              if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
                  request1.allowScanningByMediaScanner();
                  request1.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE);
              }
              request1.setDestinationInExternalFilesDir(context.getApplicationContext(), "/File", fileNameWithXtention);

              DownloadManager manager1 = (DownloadManager) context.getSystemService(Context.DOWNLOAD_SERVICE);
              Objects.requireNonNull(manager1).enqueue(request1);
              if (DownloadManager.STATUS_SUCCESSFUL == 8) {
                  toast(context, "File is downloaded successfully");
              }
          } catch (Exception e) {
              e.printStackTrace();
              Log.e("fill..Exce", e.toString());
              return;
          }
      }
  */
    public static void sendMail(Context context) {
        Intent mailer = new Intent(Intent.ACTION_SEND);
        mailer.setType("text/plain");
        mailer.putExtra(Intent.EXTRA_EMAIL, new String[]{"hello@quikcatalog.com"});
        mailer.putExtra(Intent.EXTRA_SUBJECT, "");
        mailer.putExtra(Intent.EXTRA_TEXT, "");
        mailer.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(Intent.createChooser(mailer, "Send email..."));
    }

    public static void shareApp(Context context, String title, String link) {
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.setType("image/*");
        sendIntent.putExtra(Intent.EXTRA_SUBJECT, title);
        sendIntent.putExtra(Intent.EXTRA_TEXT, title + "\n" + link);
        context.startActivity(Intent.createChooser(sendIntent, context.getString(R.string.share_using)));
    }

    public static void shareImageWithText(Context context, String title, String imagePath, View view, String productId, String offerId) {
        try {
            if (offerId == null) {
                offerId = "0";
            }
            Intent shareIntent = new Intent();
            shareIntent.setAction(Intent.ACTION_SEND);
            shareIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            shareIntent.putExtra(Intent.EXTRA_STREAM, getImageUri(context, getBitmapFromView(view)));
            shareIntent.putExtra(Intent.EXTRA_SUBJECT, imagePath);

            String apiLink = "\n\n" + ApiRequestUrl.BASE_URL + "api/swoope/getProductDetails/" + productId + "/" + offerId;

            shareIntent.putExtra(Intent.EXTRA_TEXT, title + "\n\n" + imagePath + apiLink);
            shareIntent.setType("image/*");
            shareIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);

            context.startActivity(Intent.createChooser(shareIntent, context.getString(R.string.share_using)));
        } catch (Exception exc) {
            exc.getMessage();
            log("Share Link Err", exc.getMessage());
        }
    }

    public static Bitmap getBitmapFromView(View view) {
        Bitmap returnedBitmap = Bitmap.createBitmap(view.getWidth(), view.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(returnedBitmap);
        Drawable bgDrawable = view.getBackground();
        if (bgDrawable != null)
            bgDrawable.draw(canvas);
        else
            //does not have background drawable, then draw white background on the canvas
            // canvas.drawColor(Color.WHITE);
            view.draw(canvas);
        return returnedBitmap;
    }

    public static Uri getImageUri(Context inContext, Bitmap inImage) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        inImage.compress(Bitmap.CompressFormat.PNG, 100, bytes);
        String path = MediaStore.Images.Media.insertImage(inContext.getContentResolver(), inImage, "Title", null);
        return Uri.parse(path);
    }

    public static String appVersionName(Context context) {
        String version = "";
        try {
            PackageInfo pInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            version = pInfo.versionName;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return "Version Name : " + version;
    }

    @RequiresApi(api = Build.VERSION_CODES.P)
    public static String appVersionCode(Context context) {

        long verCode = 0;
        try {
            PackageInfo pInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            if (BuildConfig.VERSION_CODE > 0) {
                verCode = BuildConfig.VERSION_CODE;
            } else verCode = pInfo.getLongVersionCode();

            //   int versionCode = BuildConfig.VERSION_CODE;
            //  String versionName = BuildConfig.VERSION_NAME;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "Version Code : " + verCode;
    }

    public static String Convert24to12(String time) {
        String convertedTime = "";
        try {
            SimpleDateFormat displayFormat = new SimpleDateFormat("hh:mm a");
            SimpleDateFormat parseFormat = new SimpleDateFormat("HH:mm:ss");
            Date date = parseFormat.parse(time);
            convertedTime = displayFormat.format(date);
            //System.out.println("convertedTime : "+convertedTime);
        } catch (final ParseException e) {
            e.printStackTrace();
        }
        return convertedTime;
        //Output will be 10:23 PM
    }


    ///////////////////////


    public static String getDataColumn(Context context, Uri uri, String selection,
                                       String[] selectionArgs) {

        Cursor cursor = null;
        final String column = "_data";
        final String[] projection = {
                column
        };

        try {
            cursor = context.getContentResolver().query(uri, projection, selection, selectionArgs,
                    null);
            if (cursor != null && cursor.moveToFirst()) {
                final int index = cursor.getColumnIndexOrThrow(column);
                return cursor.getString(index);
            }
        } finally {
            if (cursor != null)
                cursor.close();
        }
        return null;
    }


    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is ExternalStorageProvider.
     */
    public static boolean isExternalStorageDocument(Uri uri) {
        return "com.android.externalstorage.documents".equals(uri.getAuthority());
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is DownloadsProvider.
     */
    public static boolean isDownloadsDocument(Uri uri) {
        return "com.android.providers.downloads.documents".equals(uri.getAuthority());
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is MediaProvider.
     */
    public static boolean isMediaDocument(Uri uri) {
        return "com.android.providers.media.documents".equals(uri.getAuthority());
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is Google Photos.
     */
    public static boolean isGooglePhotosUri(Uri uri) {
        return "com.google.android.apps.photos.content".equals(uri.getAuthority());
    }

    public static void QrGen() {

    }

    public static String[] getAddress(Context context, double lat, double lng) {
        String ar[] = new String[2];
        Geocoder geocoder = new Geocoder(context, Locale.getDefault());
        String post_coce = null;
        String address_str = null;
        try {
            List<Address> addresses = geocoder.getFromLocation(lat, lng, 1);
            if (addresses.size() > 0) {

                Address add = null;
                for (int i = 0; i < addresses.size(); i++) {
                    add = addresses.get(i);
                    if (add.getPostalCode() != null) {
                        post_coce = add.getPostalCode(); // postal code
                        break;
                    }
                }
                address_str = addresses.get(0).getLocality() + " " + addresses.get(0).getSubLocality() + " " + addresses.get(0).getPremises() + " ," + post_coce;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        ar[0] = address_str;
        ar[1] = post_coce;
        return ar;
    }

    private static String uniqueID = null;
    private static final String PREF_UNIQUE_ID = "PREF_UNIQUE_ID";

    public synchronized static String getUniqueID(Context context) {
        if (uniqueID == null) {
            SharedPreferences sharedPrefs = context.getSharedPreferences(PREF_UNIQUE_ID, Context.MODE_PRIVATE);
            uniqueID = sharedPrefs.getString(PREF_UNIQUE_ID, null);
            if (uniqueID == null) {
                uniqueID = UUID.randomUUID().toString();
                SharedPreferences.Editor editor = sharedPrefs.edit();
                editor.putString(PREF_UNIQUE_ID, uniqueID);
                editor.apply();
            }
        }
        return uniqueID;
    }


    public static String toBase64(String message) {
        byte[] data;
        try {
            data = message.getBytes(StandardCharsets.UTF_8);
            String base64Sms = Base64.encodeToString(data, Base64.DEFAULT);
            return base64Sms;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public static String fromBase64ToStr(String message) {
        byte[] data = Base64.decode(message, Base64.DEFAULT);
        try {
            return new String(data, StandardCharsets.UTF_8);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public static String date2TodayTomorrow(Date oldTime, String text) {
        Date newTime = new Date();
        try {
            Calendar currCal = Calendar.getInstance();
            currCal.setTime(newTime);
            Calendar oldCal = Calendar.getInstance();
            oldCal.setTime(oldTime);

            int oldYear = oldCal.get(Calendar.YEAR);
            int year = currCal.get(Calendar.YEAR);
            int oldDay = oldCal.get(Calendar.DAY_OF_YEAR);
            int day = currCal.get(Calendar.DAY_OF_YEAR);

            if (oldYear == year) {
                int value = oldDay - day;
                if (value == -1) {
                    return "yesterday";// + HHmm.format(oldTime);
                } else if (value == 0) {
                    return "today";// + HHmm.format(oldTime);
                } else if (value == 1) {
                    return "tomorrow";// + HHmm.format(oldTime);
                } else {
                    if (text == null)
                        return MM_dd_HHmm.format(oldTime);

                    return text;
                }
            }
        } catch (Exception e) {

        }
        return "N/A";//yyyy_MM_dd_HH_mm.format(oldTime);
    }

    public static String venue_content = "{\n" +
            "  \"status\": true,\n" +
            "  \"message\": \"List Of Category\",\n" +
            "  \"categories\": [\n" +
            "    {\n" +
            "      \"id\": 26,\n" +
            "      \"cat_name\": \"The Spot\",\n" +
            "      \"parent_cat_id\": \"24\",\n" +
            "      \"image\": \"Electroincs07012021072630.png\",\n" +
            "      \"color\": \"e66465\",\n" +
            "      \"menu_level\": \"5\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"id\": 17,\n" +
            "      \"cat_name\": \"The Carters\",\n" +
            "      \"parent_cat_id\": \"0\",\n" +
            "      \"image\": \"Chocolates07012021071325.png\",\n" +
            "      \"color\": \"e66465\",\n" +
            "      \"menu_level\": \"5\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"id\": 9,\n" +
            "      \"cat_name\": \"Parle Cafe\",\n" +
            "      \"parent_cat_id\": \"0\",\n" +
            "      \"image\": \"Clothing18082020054001.png\",\n" +
            "      \"color\": \"e66465\",\n" +
            "      \"menu_level\": \"3\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"id\": 11,\n" +
            "      \"cat_name\": \"Sweeggy\",\n" +
            "      \"parent_cat_id\": \"0\",\n" +
            "      \"image\": \"Electroincs07012021072630.png\",\n" +
            "      \"color\": \"64ade6\",\n" +
            "      \"menu_level\": \"4\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"id\": 15,\n" +
            "      \"cat_name\": \"Womato\",\n" +
            "      \"parent_cat_id\": \"0\",\n" +
            "      \"image\": \"Fashion18082020053744.png\",\n" +
            "      \"color\": \"b13132\",\n" +
            "      \"menu_level\": \"3\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"id\": 22,\n" +
            "      \"cat_name\": \"Raj Darbar\",\n" +
            "      \"parent_cat_id\": \"18\",\n" +
            "      \"image\": \"Floor Cleaning29102020043432.png\",\n" +
            "      \"color\": \"e66465\",\n" +
            "      \"menu_level\": \"2\"\n" +
            "    }\n" +
            "  ]\n" +
            "}";


    public static String product_content = "{\n" +
            "  \"status\": true,\n" +
            "  \"message\": \"List Of Category\",\n" +
            "  \"categories\": [\n" +
            "    {\n" +
            "      \"id\": 26,\n" +
            "      \"cat_name\": \"Women Navy Blue Top\",\n" +
            "      \"parent_cat_id\": \"24\",\n" +
            "      \"image\": \"uploaded/products/8700715990269570.png\",\n" +
            "      \"color\": \"e66465\",\n" +
            "      \"menu_level\": \"5\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"id\": 17,\n" +
            "      \"cat_name\": \"Pink Printed Sports Cloth\",\n" +
            "      \"parent_cat_id\": \"0\",\n" +
            "      \"image\": \"uploaded/products/8416416268447620.png\",\n" +
            "      \"color\": \"e66465\",\n" +
            "      \"menu_level\": \"5\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"id\": 9,\n" +
            "      \"cat_name\": \"Gown\",\n" +
            "      \"parent_cat_id\": \"0\",\n" +
            "      \"image\": \"uploaded/products/7808616268704150.png\",\n" +
            "      \"color\": \"e66465\",\n" +
            "      \"menu_level\": \"3\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"id\": 11,\n" +
            "      \"cat_name\": \"Men Black Zone Shoe\",\n" +
            "      \"parent_cat_id\": \"0\",\n" +
            "      \"image\": \"uploaded/products/9901515990285140.png\",\n" +
            "      \"color\": \"64ade6\",\n" +
            "      \"menu_level\": \"4\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"id\": 1,\n" +
            "      \"cat_name\": \"Men Black Zone XT Shoe\",\n" +
            "      \"parent_cat_id\": \"0\",\n" +
            "      \"image\": \"uploaded/products/7413015990284840.png\",\n" +
            "      \"color\": \"b13132\",\n" +
            "      \"menu_level\": \"3\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"id\": 22,\n" +
            "      \"cat_name\": \"Women White Solid Top\",\n" +
            "      \"parent_cat_id\": \"18\",\n" +
            "      \"image\": \"uploaded/products/4539716014503780.png\",\n" +
            "      \"color\": \"e66465\",\n" +
            "      \"menu_level\": \"2\"\n" +
            "    }\n" +
            "  ]\n" +
            "}";


    public static String decimalFormatReturnString(Double aFloat) {
        if (aFloat == null) {
            aFloat = 0d;
        }
        aFloat = decimal2DigitFormat(aFloat);
        String pattern = "#0.00;" + "-" + "#0.00";
        DecimalFormat df = (DecimalFormat) DecimalFormat.getInstance();
        df.applyPattern(pattern);
        return df.format(aFloat);
    }

    public static double decimal2DigitFormat(double aDouble) {
        return new BigDecimal(aDouble).setScale(2, BigDecimal.ROUND_HALF_DOWN).doubleValue();
    }

    public static String decimalFormat(Context context, Float aFloat) {
        if (aFloat == null) {
            aFloat = 0f;
        }
        aFloat = decimal2DigitFormat(aFloat);
        String pattern = ((context == null) ? "£" : context.getResources().getString(R.string.pound)) + "#0.00;" + "-" + ((context == null) ? "£" : context.getResources().getString(R.string.pound)) + "#0.00";
        DecimalFormat df = (DecimalFormat) DecimalFormat.getInstance();
        df.applyPattern(pattern);
        return df.format(aFloat);
    }

    public static float decimal2DigitFormat(Float aFloat) {
        if (aFloat == null) {
            return 0;
        }
//        return new BigDecimal(aFloat).setScale(2, RoundingMode.UP).floatValue();
        return new BigDecimal(aFloat).setScale(2, BigDecimal.ROUND_HALF_DOWN).floatValue();
        //return Float.parseFloat(Constants._DECIMAL_FORMAT.format(aFloat));
    }

    public static String decimalFormat(double aDouble) {
        return Constants.DECIMAL_FORMAT.format(aDouble);
    }

    public static String poundLandProduct = "{\n" +
            "  \"status\": true,\n" +
            "  \"message\": \"List of Wishlist.\",\n" +
            "  \"likes\": {\n" +
            "    \"current_page\": 1,\n" +
            "    \"data\": [\n" +
            "      {\n" +
            "        \"id\": 2132,\n" +
            "        \"guest\": null,\n" +
            "        \"customer_id\": \"142\",\n" +
            "        \"product_id\": \"832\",\n" +
            "        \"modifier_id\": \"887\",\n" +
            "        \"offer_id\": null,\n" +
            "        \"ip_address\": null,\n" +
            "        \"venue_id\": \"2020071407361121\",\n" +
            "        \"merchant_id\": \"15\",\n" +
            "        \"created_at\": \"2021-04-25 16:02:47\",\n" +
            "        \"updated_at\": \"2022-02-01 06:31:13\",\n" +
            "        \"product_name\": \"Amayra Women's Cotton Straight Top(Blue)\",\n" +
            "        \"product_type\": \"1\",\n" +
            "        \"images\": \"uploaded\\/products\\/1134816174375080.jpeg\",\n" +
            "        \"brand_id\": null,\n" +
            "        \"modifier_name\": \"M,Pink\",\n" +
            "        \"selling_price\": \"11.00\",\n" +
            "        \"avl_quantity\": \"49931\",\n" +
            "        \"modifier_images\": \"uploaded\\/products\\/8700715990269570.png\",\n" +
            "        \"venue_name\": \"Go Amazon Retail\",\n" +
            "        \"venue_type\": \"1\",\n" +
            "        \"latitude\": \"52.586973\",\n" +
            "        \"longitude\": \"-2.12882\",\n" +
            "        \"address_1\": \"Wolverhampton, UK\",\n" +
            "        \"delivery_distance\": \"0\",\n" +
            "        \"distance\": \"4,088.65\",\n" +
            "        \"offer_title\": \"\",\n" +
            "        \"offer_type\": null,\n" +
            "        \"discount_amount\": null,\n" +
            "        \"discount_type\": null,\n" +
            "        \"disc_detail_type\": null,\n" +
            "        \"new_price\": \"11.00\",\n" +
            "        \"brand_name\": \"\",\n" +
            "        \"match_count\": 1,\n" +
            "        \"min_price\": \"11.00\",\n" +
            "        \"max_price\": \"11.00\"\n" +
            "      },\n" +
            "      {\n" +
            "        \"id\": 2091,\n" +
            "        \"guest\": null,\n" +
            "        \"customer_id\": \"142\",\n" +
            "        \"product_id\": \"809\",\n" +
            "        \"modifier_id\": \"815\",\n" +
            "        \"offer_id\": \"1\",\n" +
            "        \"ip_address\": \"180.151.88.152\",\n" +
            "        \"venue_id\": \"2020071407361121\",\n" +
            "        \"merchant_id\": \"15\",\n" +
            "        \"created_at\": \"2021-04-09 06:03:15\",\n" +
            "        \"updated_at\": \"2021-04-09 06:03:15\",\n" +
            "        \"product_name\": \"VERO MODA Women's Checkered Regular Fit Top\",\n" +
            "        \"product_type\": \"1\",\n" +
            "        \"images\": \"uploaded\\/products\\/3888116174366181.jpeg\",\n" +
            "        \"brand_id\": \"24\",\n" +
            "        \"modifier_name\": \"Prism violet,64GB\",\n" +
            "        \"selling_price\": \"50.00\",\n" +
            "        \"avl_quantity\": \"896\",\n" +
            "        \"modifier_images\": \"uploaded\\/products\\/4498615983565820.png\",\n" +
            "        \"venue_name\": \"Go Amazon Retail\",\n" +
            "        \"venue_type\": \"1\",\n" +
            "        \"latitude\": \"52.586973\",\n" +
            "        \"longitude\": \"-2.12882\",\n" +
            "        \"address_1\": \"Wolverhampton, UK\",\n" +
            "        \"delivery_distance\": \"0\",\n" +
            "        \"distance\": \"4,088.65\",\n" +
            "        \"offer_title\": \"\",\n" +
            "        \"offer_type\": null,\n" +
            "        \"discount_amount\": \"10.00\",\n" +
            "        \"discount_type\": \"1\",\n" +
            "        \"disc_detail_type\": \"1\",\n" +
            "        \"new_price\": \"45.00\",\n" +
            "        \"brand_name\": \"Samsung\",\n" +
            "        \"match_count\": 1,\n" +
            "        \"min_price\": \"40.00\",\n" +
            "        \"max_price\": \"40.00\"\n" +
            "      },\n" +
            "      {\n" +
            "        \"id\": 2060,\n" +
            "        \"guest\": null,\n" +
            "        \"customer_id\": \"142\",\n" +
            "        \"product_id\": \"2649\",\n" +
            "        \"modifier_id\": \"3413\",\n" +
            "        \"offer_id\": null,\n" +
            "        \"ip_address\": \"180.151.77.251\",\n" +
            "        \"venue_id\": \"2020071407361121\",\n" +
            "        \"merchant_id\": \"15\",\n" +
            "        \"created_at\": \"2021-04-07 12:23:57\",\n" +
            "        \"updated_at\": \"2021-04-07 12:23:57\",\n" +
            "        \"product_name\": \"MALLORY WINSTON Women's Top\",\n" +
            "        \"product_type\": \"1\",\n" +
            "        \"images\": \"uploaded\\/products\\/7179116174365200.jpeg\",\n" +
            "        \"brand_id\": \"209\",\n" +
            "        \"modifier_name\": null,\n" +
            "        \"selling_price\": \"1.09\",\n" +
            "        \"avl_quantity\": \"0\",\n" +
            "        \"modifier_images\": \"uploaded\\/products\\/6343816153720167.jpeg\",\n" +
            "        \"venue_name\": \"Go Amazon Retail\",\n" +
            "        \"venue_type\": \"1\",\n" +
            "        \"latitude\": \"52.586973\",\n" +
            "        \"longitude\": \"-2.12882\",\n" +
            "        \"address_1\": \"Wolverhampton, UK\",\n" +
            "        \"delivery_distance\": \"0\",\n" +
            "        \"distance\": \"4,088.65\",\n" +
            "        \"offer_title\": \"\",\n" +
            "        \"offer_type\": null,\n" +
            "        \"discount_amount\": null,\n" +
            "        \"discount_type\": null,\n" +
            "        \"disc_detail_type\": null,\n" +
            "        \"new_price\": \"1.09\",\n" +
            "        \"brand_name\": \"Heinz\",\n" +
            "        \"match_count\": 4,\n" +
            "        \"min_price\": \"1.09\",\n" +
            "        \"max_price\": \"1.09\"\n" +
            "      },\n" +
            "      {\n" +
            "        \"id\": 2022,\n" +
            "        \"guest\": null,\n" +
            "        \"customer_id\": \"142\",\n" +
            "        \"product_id\": \"2727\",\n" +
            "        \"modifier_id\": \"3495\",\n" +
            "        \"offer_id\": null,\n" +
            "        \"ip_address\": \"180.151.90.141\",\n" +
            "        \"venue_id\": \"2020071407361121\",\n" +
            "        \"merchant_id\": \"21\",\n" +
            "        \"created_at\": \"2021-04-06 07:26:14\",\n" +
            "        \"updated_at\": \"2021-04-06 08:05:56\",\n" +
            "        \"product_name\": \"Amayra Women's Cotton Straight Top(Blue)\",\n" +
            "        \"product_type\": \"1\",\n" +
            "        \"images\": \"uploaded\\/products\\/1134816174375080.jpeg\",\n" +
            "        \"brand_id\": \"245\",\n" +
            "        \"modifier_name\": null,\n" +
            "        \"selling_price\": \"8.00\",\n" +
            "        \"avl_quantity\": \"49993\",\n" +
            "        \"modifier_images\": \"\",\n" +
            "        \"venue_name\": \"Poundland Bilston\",\n" +
            "        \"venue_type\": \"1\",\n" +
            "        \"latitude\": \"51.5073509\",\n" +
            "        \"longitude\": \"-0.1277583\",\n" +
            "        \"address_1\": \"London, UK\",\n" +
            "        \"delivery_distance\": \"0\",\n" +
            "        \"distance\": \"4,056.80\",\n" +
            "        \"offer_title\": \"\",\n" +
            "        \"offer_type\": null,\n" +
            "        \"discount_amount\": null,\n" +
            "        \"discount_type\": null,\n" +
            "        \"disc_detail_type\": null,\n" +
            "        \"new_price\": \"8.00\",\n" +
            "        \"brand_name\": \"Aero\",\n" +
            "        \"match_count\": 1,\n" +
            "        \"min_price\": \"8.00\",\n" +
            "        \"max_price\": \"8.00\"\n" +
            "      },\n" +
            "      {\n" +
            "        \"id\": 2021,\n" +
            "        \"guest\": null,\n" +
            "        \"customer_id\": \"142\",\n" +
            "        \"product_id\": \"1303\",\n" +
            "        \"modifier_id\": \"1798\",\n" +
            "        \"offer_id\": null,\n" +
            "        \"ip_address\": \"180.151.90.141\",\n" +
            "        \"venue_id\": \"2020071407361121\",\n" +
            "        \"merchant_id\": \"21\",\n" +
            "        \"created_at\": \"2021-04-06 07:26:11\",\n" +
            "        \"updated_at\": \"2021-04-06 08:06:13\",\n" +
            "        \"product_name\": \"Women Blue Woven Design Float Ride Run 2.0 Running Shoes\",\n" +
            "        \"product_type\": \"1\",\n" +
            "        \"images\": \"uploaded\\/products\\/3815815978366430.png\",\n" +
            "        \"brand_id\": \"26\",\n" +
            "        \"modifier_name\": null,\n" +
            "        \"selling_price\": \"12.00\",\n" +
            "        \"avl_quantity\": \"49995\",\n" +
            "        \"modifier_images\": \"\",\n" +
            "        \"venue_name\": \"Poundland Bilston\",\n" +
            "        \"venue_type\": \"1\",\n" +
            "        \"latitude\": \"51.5073509\",\n" +
            "        \"longitude\": \"-0.1277583\",\n" +
            "        \"address_1\": \"London, UK\",\n" +
            "        \"delivery_distance\": \"0\",\n" +
            "        \"distance\": \"4,056.80\",\n" +
            "        \"offer_title\": \"\",\n" +
            "        \"offer_type\": null,\n" +
            "        \"discount_amount\": null,\n" +
            "        \"discount_type\": null,\n" +
            "        \"disc_detail_type\": null,\n" +
            "        \"new_price\": \"12.00\",\n" +
            "        \"brand_name\": \"Black Mountain\",\n" +
            "        \"match_count\": 1,\n" +
            "        \"min_price\": \"12.00\",\n" +
            "        \"max_price\": \"12.00\"\n" +
            "      },\n" +
            "      {\n" +
            "        \"id\": 2020,\n" +
            "        \"guest\": null,\n" +
            "        \"customer_id\": \"142\",\n" +
            "        \"product_id\": \"1302\",\n" +
            "        \"modifier_id\": \"1797\",\n" +
            "        \"offer_id\": null,\n" +
            "        \"ip_address\": \"180.151.90.141\",\n" +
            "        \"venue_id\": \"2020071407361121\",\n" +
            "        \"merchant_id\": \"21\",\n" +
            "        \"created_at\": \"2021-04-06 07:26:07\",\n" +
            "        \"updated_at\": \"2021-04-06 08:05:04\",\n" +
            "        \"product_name\": \"Budweiser Streetwear Co\",\n" +
            "        \"product_type\": \"1\",\n" +
            "        \"images\": \"uploaded\\/products\\/2807816034567470.jpeg\",\n" +
            "        \"brand_id\": \"7\",\n" +
            "        \"modifier_name\": null,\n" +
            "        \"selling_price\": \"10.00\",\n" +
            "        \"avl_quantity\": \"49993\",\n" +
            "        \"modifier_images\": \"\",\n" +
            "        \"venue_name\": \"Poundland Bilston\",\n" +
            "        \"venue_type\": \"1\",\n" +
            "        \"latitude\": \"51.5073509\",\n" +
            "        \"longitude\": \"-0.1277583\",\n" +
            "        \"address_1\": \"London, UK\",\n" +
            "        \"delivery_distance\": \"0\",\n" +
            "        \"distance\": \"4,056.80\",\n" +
            "        \"offer_title\": \"\",\n" +
            "        \"offer_type\": null,\n" +
            "        \"discount_amount\": null,\n" +
            "        \"discount_type\": null,\n" +
            "        \"disc_detail_type\": null,\n" +
            "        \"new_price\": \"10.00\",\n" +
            "        \"brand_name\": \"Fila\",\n" +
            "        \"match_count\": 1,\n" +
            "        \"min_price\": \"10.00\",\n" +
            "        \"max_price\": \"10.00\"\n" +
            "      },\n" +
            "      {\n" +
            "        \"id\": 1697,\n" +
            "        \"guest\": null,\n" +
            "        \"customer_id\": \"142\",\n" +
            "        \"product_id\": \"887\",\n" +
            "        \"modifier_id\": \"1046\",\n" +
            "        \"offer_id\": null,\n" +
            "        \"ip_address\": null,\n" +
            "        \"venue_id\": \"2020071407361121\",\n" +
            "        \"merchant_id\": \"15\",\n" +
            "        \"created_at\": \"2021-02-17 09:35:38\",\n" +
            "        \"updated_at\": \"2022-04-06 10:44:40\",\n" +
            "        \"product_name\": \"Women Black Solid Rapid-Dry Training Tights\",\n" +
            "        \"product_type\": \"1\",\n" +
            "        \"images\": \"uploaded\\/products\\/4878115978363290.png\",\n" +
            "        \"brand_id\": \"42\",\n" +
            "        \"modifier_name\": null,\n" +
            "        \"selling_price\": \"19.00\",\n" +
            "        \"avl_quantity\": \"990\",\n" +
            "        \"modifier_images\": \"uploaded\\/products\\/4878115978363290.png\",\n" +
            "        \"venue_name\": \"Go Amazon Retail\",\n" +
            "        \"venue_type\": \"1\",\n" +
            "        \"latitude\": \"52.586973\",\n" +
            "        \"longitude\": \"-2.12882\",\n" +
            "        \"address_1\": \"Wolverhampton, UK\",\n" +
            "        \"delivery_distance\": \"0\",\n" +
            "        \"distance\": \"4,088.65\",\n" +
            "        \"offer_title\": \"\",\n" +
            "        \"offer_type\": null,\n" +
            "        \"discount_amount\": null,\n" +
            "        \"discount_type\": null,\n" +
            "        \"disc_detail_type\": null,\n" +
            "        \"new_price\": \"19.00\",\n" +
            "        \"brand_name\": \"Puma\",\n" +
            "        \"match_count\": 1,\n" +
            "        \"min_price\": \"19.00\",\n" +
            "        \"max_price\": \"19.00\"\n" +
            "      },\n" +
            "      {\n" +
            "        \"id\": 1690,\n" +
            "        \"guest\": null,\n" +
            "        \"customer_id\": \"142\",\n" +
            "        \"product_id\": \"834\",\n" +
            "        \"modifier_id\": \"902\",\n" +
            "        \"offer_id\": \"186\",\n" +
            "        \"ip_address\": \"203.122.0.207\",\n" +
            "        \"venue_id\": \"2020071407361121\",\n" +
            "        \"merchant_id\": \"15\",\n" +
            "        \"created_at\": \"2021-02-15 13:22:59\",\n" +
            "        \"updated_at\": \"2021-02-15 13:22:59\",\n" +
            "        \"product_name\": \"Women Navy Solid 3\\/4th Length Yoga Track Pants\",\n" +
            "        \"product_type\": \"1\",\n" +
            "        \"images\": \"uploaded\\/products\\/3090615978361680.png\",\n" +
            "        \"brand_id\": \"24\",\n" +
            "        \"modifier_name\": null,\n" +
            "        \"selling_price\": \"50.00\",\n" +
            "        \"avl_quantity\": \"1\",\n" +
            "        \"modifier_images\": \"uploaded\\/products\\/3090615978361680.png\",\n" +
            "        \"venue_name\": \"Go Amazon Retail\",\n" +
            "        \"venue_type\": \"1\",\n" +
            "        \"latitude\": \"52.586973\",\n" +
            "        \"longitude\": \"-2.12882\",\n" +
            "        \"address_1\": \"Wolverhampton, UK\",\n" +
            "        \"delivery_distance\": \"0\",\n" +
            "        \"distance\": \"4,088.65\",\n" +
            "        \"offer_title\": \"\",\n" +
            "        \"offer_type\": null,\n" +
            "        \"discount_amount\": \"25.00\",\n" +
            "        \"discount_type\": \"1\",\n" +
            "        \"disc_detail_type\": \"1\",\n" +
            "        \"new_price\": \"37.50\",\n" +
            "        \"brand_name\": \"Samsung\",\n" +
            "        \"match_count\": 1,\n" +
            "        \"min_price\": \"50.00\",\n" +
            "        \"max_price\": \"50.00\"\n" +
            "      },\n" +
            "      {\n" +
            "        \"id\": 1687,\n" +
            "        \"guest\": null,\n" +
            "        \"customer_id\": \"142\",\n" +
            "        \"product_id\": \"2413\",\n" +
            "        \"modifier_id\": \"3127\",\n" +
            "        \"offer_id\": null,\n" +
            "        \"ip_address\": \"203.122.0.207\",\n" +
            "        \"venue_id\": \"2020071407361121\",\n" +
            "        \"merchant_id\": \"5\",\n" +
            "        \"created_at\": \"2021-02-15 11:09:21\",\n" +
            "        \"updated_at\": \"2021-02-15 11:09:21\",\n" +
            "        \"product_name\": \"Women Black Solid Cropped Jersey Strappy Top\",\n" +
            "        \"product_type\": \"2\",\n" +
            "        \"images\": \"uploaded\\/products\\/3856415979216540.png\",\n" +
            "        \"brand_id\": \"177\",\n" +
            "        \"modifier_name\": \"\",\n" +
            "        \"selling_price\": \"3.99\",\n" +
            "        \"avl_quantity\": \"50000\",\n" +
            "        \"modifier_images\": \"uploaded\\/products\\/3856415979216540.png\",\n" +
            "        \"venue_name\": \"Vik Grocery\",\n" +
            "        \"venue_type\": \"2\",\n" +
            "        \"latitude\": \"52.5756465\",\n" +
            "        \"longitude\": \"-2.138598\",\n" +
            "        \"address_1\": \"Oaklands Road, Wolverhampton WV3 0DS, UK\",\n" +
            "        \"delivery_distance\": \"0\",\n" +
            "        \"distance\": \"4,089.48\",\n" +
            "        \"offer_title\": \"\",\n" +
            "        \"offer_type\": null,\n" +
            "        \"discount_amount\": null,\n" +
            "        \"discount_type\": null,\n" +
            "        \"disc_detail_type\": null,\n" +
            "        \"new_price\": \"3.99\",\n" +
            "        \"brand_name\": \"AFRICAN PRIDE\",\n" +
            "        \"match_count\": 1,\n" +
            "        \"min_price\": \"3.99\",\n" +
            "        \"max_price\": \"3.99\"\n" +
            "      },\n" +
            "      {\n" +
            "        \"id\": 1683,\n" +
            "        \"guest\": null,\n" +
            "        \"customer_id\": \"142\",\n" +
            "        \"pr 2022-04-07 13:55:21.406 11450-13141/com.poundland.retail D/OkHttp: oduct_id\": \"2089\",\n" +
            "        \"modifier_id\": \"2707\",\n" +
            "        \"offer_id\": null,\n" +
            "        \"ip_address\": null,\n" +
            "        \"venue_id\": \"2020071407361121\",\n" +
            "        \"merchant_id\": \"15\",\n" +
            "        \"created_at\": \"2021-02-15 08:39:20\",\n" +
            "        \"updated_at\": \"2021-02-15 08:39:20\",\n" +
            "        \"product_name\": \"Unisex White & Green DMX Trail Shadow Colourblocked Running Shoes\",\n" +
            "        \"product_type\": \"1\",\n" +
            "        \"images\": \"uploaded\\/products\\/2463115995701610.png\",\n" +
            "        \"brand_id\": \"5\",\n" +
            "        \"modifier_name\": null,\n" +
            "        \"selling_price\": \"12.00\",\n" +
            "        \"avl_quantity\": \"50000\",\n" +
            "        \"modifier_images\": \"uploaded\\/products\\/2463115995701610.png\",\n" +
            "        \"venue_name\": \"Next\",\n" +
            "        \"venue_type\": \"1\",\n" +
            "        \"latitude\": \"52.5875483\",\n" +
            "        \"longitude\": \"-2.120122100000001\",\n" +
            "        \"address_1\": \"Wolverhampton, Railway Drive, West Midlands, Wolverhampton, UK\",\n" +
            "        \"delivery_distance\": \"10000\",\n" +
            "        \"distance\": \"4,088.31\",\n" +
            "        \"offer_title\": \"\",\n" +
            "        \"offer_type\": null,\n" +
            "        \"discount_amount\": null,\n" +
            "        \"discount_type\": null,\n" +
            "        \"disc_detail_type\": null,\n" +
            "        \"new_price\": \"12.00\",\n" +
            "        \"brand_name\": \"Adidas\",\n" +
            "        \"match_count\": 1,\n" +
            "        \"min_price\": \"12.00\",\n" +
            "        \"max_price\": \"12.00\"\n" +
            "      }\n" +
            "    ],\n" +
            "    \"first_page_url\": \"https:\\/\\/swoopelocaltesting.com\\/admin\\/public\\/api\\/swoopelocalapp\\/getWishlists?page=1\",\n" +
            "    \"from\": 1,\n" +
            "    \"last_page\": 12,\n" +
            "    \"last_page_url\": \"https:\\/\\/swoopelocaltesting.com\\/admin\\/public\\/api\\/swoopelocalapp\\/getWishlists?page=12\",\n" +
            "    \"next_page_url\": \"https:\\/\\/swoopelocaltesting.com\\/admin\\/public\\/api\\/swoopelocalapp\\/getWishlists?page=2\",\n" +
            "    \"path\": \"https:\\/\\/swoopelocaltesting.com\\/admin\\/public\\/api\\/swoopelocalapp\\/getWishlists\",\n" +
            "    \"per_page\": 10,\n" +
            "    \"prev_page_url\": null,\n" +
            "    \"to\": 10,\n" +
            "    \"total\": 118\n" +
            "  }\n" +
            "}";
}
