package com.poundland.retail.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Point;
import android.os.CountDownTimer;
import android.text.TextUtils;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.goibibo.libs.views.ScratchRelativeLayoutView;
import com.goibibo.libs.views.ScratchTextView;
import com.google.zxing.BarcodeFormat;
import com.kaopiz.kprogresshud.KProgressHUD;
import com.poundland.retail.R;
import com.poundland.retail.adapter.CancellationReasonAdapter;
import com.poundland.retail.apiUtils.ApiRequestUrl;
import com.poundland.retail.appUtils.Contents;
import com.poundland.retail.appUtils.FlipAnimator;
import com.poundland.retail.appUtils.HelperClass;
import com.poundland.retail.appUtils.QRCodeEncoder;
import com.poundland.retail.interfaces.DrawerListner;
import com.poundland.retail.interfaces.OnDialogClickListener;
import com.poundland.retail.model.responseModel.MyOrderResponseModel;
import com.poundland.retail.notificationService.NotificationModel;

import java.util.ArrayList;
import java.util.List;

import static android.content.Context.WINDOW_SERVICE;
import static com.poundland.retail.interfaces.Constants.REPLACE_REFUND;

public class DialogUtils {

    /**
     * get progress dialog
     *
     * @param context current application context
     * @return dialog
     */
    private static CountDownTimer mCountDownTimer = null;
    private static List<Dialog> dialogList = new ArrayList<>();
    private static KProgressHUD hud;
  /*  hud = KProgressHUD.create(this)
            .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE);*/

    public static void dismissAll() {
        for (Dialog dialog : dialogList) {
            dialog.dismiss();
        }
        dialogList.clear();
    }

    public static void dismissCountDownTimer() {
        if (mCountDownTimer != null)
            mCountDownTimer.cancel();
    }


    public static Dialog getProgressDialog(Context context) {
        dismissAll();
        final Dialog dialog = new Dialog(context, android.R.style.Theme_Translucent_NoTitleBar);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCanceledOnTouchOutside(true);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.layout_progress_dialog);

        WindowManager.LayoutParams layoutParams = dialog.getWindow().getAttributes();
        layoutParams.dimAmount = 0.7f;
        layoutParams.height = ViewGroup.LayoutParams.MATCH_PARENT;
        layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT;
        dialog.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        dialog.getWindow().setAttributes(layoutParams);
        // dialog.show();
        dialogList.add(dialog);
        return dialog;
    }


    public static KProgressHUD getCustomDialog(Context context) {
        dismissAll();
        final KProgressHUD dialog = KProgressHUD.create(context)
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setDimAmount(0.5f);
        // dialog.show();
        // dialogList.add(dialog);
        return dialog;
    }

    /*public static void showDialogSubmitReason(final Context context, final GetCallbackListner getCallbackListner) {
        dismissAll();
        final Dialog dialog = new Dialog(context, android.R.style.Theme_Translucent_NoTitleBar);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCanceledOnTouchOutside(true);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.layout_alert_dialog_submit_reason);
        final EditText etReason = dialog.findViewById(R.id.etRemark);
        WindowManager.LayoutParams layoutParams = dialog.getWindow().getAttributes();
        layoutParams.dimAmount = 0.7f;
        layoutParams.height = ViewGroup.LayoutParams.WRAP_CONTENT;
        layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT;
        dialog.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        dialog.getWindow().setAttributes(layoutParams);
        dialog.getWindow().setWindowAnimations(R.style.AnimationCenterPopUp);
        dialogList.add(dialog);
        dialog.show();

        dialog.findViewById(R.id.btnSubmit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!etReason.getText().toString().trim().isEmpty()) {
                    dialog.dismiss();
                    if (getCallbackListner != null)
                        getCallbackListner.onResponse(etReason.getText().toString().trim());
                } else
                    showSnackBar(etReason, context.getResources().getString(R.string.please_enter_remarks));
            }
        });

        dialog.findViewById(R.id.ivClose).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
    }


    public static void showDialogWithRadio(Context context, String message, final OnDialogClickListener onDialogClickListener) {
        dismissAll();
        final Dialog dialog = new Dialog(context, android.R.style.Theme_Translucent_NoTitleBar);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCanceledOnTouchOutside(true);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.layout_alert_dialog_with_radio);
        TextView tvMessaage = dialog.findViewById(R.id.tvInvoiceId);
        tvMessaage.setText(message);

        WindowManager.LayoutParams layoutParams = dialog.getWindow().getAttributes();
        layoutParams.dimAmount = 0.7f;
        layoutParams.height = ViewGroup.LayoutParams.WRAP_CONTENT;
        layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT;
        dialog.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        dialog.getWindow().setAttributes(layoutParams);
        dialog.getWindow().setWindowAnimations(R.style.AnimationCenterPopUp);
        dialogList.add(dialog);
        dialog.show();
        dialog.findViewById(R.id.btnOkay).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                if (onDialogClickListener != null)
                    onDialogClickListener.onPositiveClick();
            }
        });
        dialog.findViewById(R.id.btnCancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.findViewById(R.id.ivClose).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
    }

    public static void showAlertDialog(Context context, String message, final OnDialogClickListener onDialogClickListener) {
        dismissAll();
        final Dialog dialog = new Dialog(context, android.R.style.Theme_Translucent_NoTitleBar);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCanceledOnTouchOutside(true);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.layout_alert_dialog);
        TextView tvMessaage = dialog.findViewById(R.id.tvMessage);
        tvMessaage.setText(message);

        WindowManager.LayoutParams layoutParams = dialog.getWindow().getAttributes();
        layoutParams.dimAmount = 0.7f;
        layoutParams.height = ViewGroup.LayoutParams.WRAP_CONTENT;
        layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT;
        dialog.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        dialog.getWindow().setAttributes(layoutParams);
        dialog.getWindow().setWindowAnimations(R.style.AnimationCenterPopUp);
        dialogList.add(dialog);
        dialog.show();
        dialog.findViewById(R.id.btnOkay).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                if (onDialogClickListener != null)
                    onDialogClickListener.onPositiveClick();
            }
        });
        dialog.findViewById(R.id.btnCancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
    }

    public static void showAlertDialog(Context context, String message) {
        dismissAll();
        final Dialog dialog = new Dialog(context, android.R.style.Theme_Translucent_NoTitleBar);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCanceledOnTouchOutside(true);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.layout_alert_dialog);
        TextView tvMessaage = dialog.findViewById(R.id.tvMessage);
        tvMessaage.setText(message);

        WindowManager.LayoutParams layoutParams = dialog.getWindow().getAttributes();
        layoutParams.dimAmount = 0.7f;
        layoutParams.height = ViewGroup.LayoutParams.WRAP_CONTENT;
        layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT;
        dialog.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        dialog.getWindow().setAttributes(layoutParams);
        dialog.getWindow().setWindowAnimations(R.style.AnimationCenterPopUp);
        dialogList.add(dialog);
        dialog.show();
        dialog.findViewById(R.id.btnOkay).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.findViewById(R.id.btnCancel).setVisibility(View.GONE);
    }
*/
    public static void showAlertDialog(Context context, String message, final OnDialogClickListener onDialogClickListener) {
        dismissAll();
        final Dialog dialog = new Dialog(context, android.R.style.Theme_Translucent_NoTitleBar);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCanceledOnTouchOutside(true);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.layout_alert_dialog);
        TextView tvMessaage = dialog.findViewById(R.id.tvMessage);
        tvMessaage.setText(message);

        WindowManager.LayoutParams layoutParams = dialog.getWindow().getAttributes();
        layoutParams.dimAmount = 0.7f;
        layoutParams.height = ViewGroup.LayoutParams.WRAP_CONTENT;
        layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT;
        dialog.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        dialog.getWindow().setAttributes(layoutParams);
        dialog.getWindow().setWindowAnimations(R.style.AnimationCenterPopUp);
        dialogList.add(dialog);
        dialog.show();
        dialog.findViewById(R.id.btnOkay).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                if (onDialogClickListener != null)
                    onDialogClickListener.onPositiveClick();
            }
        });
        dialog.findViewById(R.id.btnCancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                if (onDialogClickListener != null)
                    onDialogClickListener.onNegativeClick();
            }
        });
    }

    public static void cancelOrderDialog(Context context, String message, final OnDialogClickListener onDialogClickListener) {
        dismissAll();
        final Dialog dialog = new Dialog(context, android.R.style.Theme_Translucent_NoTitleBar);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCanceledOnTouchOutside(true);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.dialog_cancel_order);
        TextView tvMessaage = dialog.findViewById(R.id.tvMessage);
        tvMessaage.setText(message);

        WindowManager.LayoutParams layoutParams = dialog.getWindow().getAttributes();
        layoutParams.dimAmount = 0.7f;
        layoutParams.height = ViewGroup.LayoutParams.WRAP_CONTENT;
        layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT;
        dialog.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        dialog.getWindow().setAttributes(layoutParams);
        dialog.getWindow().setWindowAnimations(R.style.AnimationCenterPopUp);
        dialogList.add(dialog);
        dialog.show();
        dialog.findViewById(R.id.btnOkay).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                if (onDialogClickListener != null)
                    onDialogClickListener.onPositiveClick();
            }
        });
        dialog.findViewById(R.id.btnCancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
    }

    public static void scanLoyaltyDialog(Context context, String qrData, String message) {
        dismissAll();
        WindowManager manager = (WindowManager) context.getSystemService(WINDOW_SERVICE);
        Display display = manager.getDefaultDisplay();
        Point point = new Point();
        display.getSize(point);
        int width = point.x;
        int height = point.y;
        int smallerDimension = width < height ? width : height;
        smallerDimension = smallerDimension * 3 / 4;


        final Dialog dialog = new Dialog(context, android.R.style.Theme_Translucent_NoTitleBar);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCanceledOnTouchOutside(true);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.dialog_loyalty_qr);
        TextView tvMessaage = dialog.findViewById(R.id.tv_qr_message);
        ImageView iv_qr_code = dialog.findViewById(R.id.iv_qr_code);
        //Encode with a QR Code image

        QRCodeEncoder qrCodeEncoder = new QRCodeEncoder(qrData, null, Contents.Type.TEXT,
                BarcodeFormat.QR_CODE.toString(), smallerDimension);
        try {
            Bitmap bitmap = qrCodeEncoder.encodeAsBitmap();
            Bitmap overlay = BitmapFactory.decodeResource(context.getResources(), R.drawable.qr_code_resised);   ////  qr_bg_image

            //setting bitmap to image view
            iv_qr_code.setPadding(5, 5, 5, 5);
            iv_qr_code.setImageBitmap(mergeBitmaps(overlay, bitmap));

            //  iv_qr_code.setImageBitmap(bitmap);
        } catch (Exception e) {
            e.printStackTrace();
        }

        tvMessaage.setText(message);

        WindowManager.LayoutParams layoutParams = dialog.getWindow().getAttributes();
        layoutParams.dimAmount = 0.7f;
        layoutParams.height = ViewGroup.LayoutParams.WRAP_CONTENT;
        layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT;
        dialog.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        dialog.getWindow().setAttributes(layoutParams);
        dialog.getWindow().setWindowAnimations(R.style.AnimationCenterPopUp);
        dialogList.add(dialog);
        dialog.show();
        dialog.findViewById(R.id.iv_close).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
    }

    public static void dialogLogOut(Context context, String message, String title, final OnDialogClickListener onDialogClickListener) {
        dismissAll();
        final Dialog dialog = new Dialog(context, android.R.style.Theme_Translucent_NoTitleBar);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCanceledOnTouchOutside(true);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.dialog_logout);
        TextView tvTitle = dialog.findViewById(R.id.tv_title);
        TextView tvMessaage = dialog.findViewById(R.id.tvMessage);
        tvMessaage.setText(message);
        tvTitle.setText(title);

        WindowManager.LayoutParams layoutParams = dialog.getWindow().getAttributes();
        layoutParams.dimAmount = 0.7f;
        layoutParams.height = ViewGroup.LayoutParams.WRAP_CONTENT;
        layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT;
        dialog.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        dialog.getWindow().setAttributes(layoutParams);
        dialog.getWindow().setWindowAnimations(R.style.AnimationCenterPopUp);
        dialogList.add(dialog);
        dialog.show();
        dialog.findViewById(R.id.btnOkay).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                if (onDialogClickListener != null)
                    onDialogClickListener.onPositiveClick();
            }
        });
        dialog.findViewById(R.id.btnCancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                if (onDialogClickListener != null)
                    onDialogClickListener.onNegativeClick();
            }
        });
    }

    public static void appVersionDialog(Context context) {
        dismissAll();
        final Dialog dialog = new Dialog(context, android.R.style.Theme_Translucent_NoTitleBar);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCanceledOnTouchOutside(true);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.dialog_app_version);

        TextView tvMessaage = dialog.findViewById(R.id.tv_version_of_swoope_app);

        tvMessaage.setText("" + HelperClass.appVersionName(context) + "\n" + HelperClass.appVersionCode(context));


        WindowManager.LayoutParams layoutParams = dialog.getWindow().getAttributes();
        layoutParams.dimAmount = 0.7f;
        layoutParams.height = ViewGroup.LayoutParams.WRAP_CONTENT;
        layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT;
        dialog.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        dialog.getWindow().setAttributes(layoutParams);
        dialog.getWindow().setWindowAnimations(R.style.AnimationCenterPopUp);
        dialogList.add(dialog);
        dialog.show();

        dialog.findViewById(R.id.tv_ok).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
    }

    public static void deleteAccountDialog(Context context, String message, final OnDialogClickListener listener) {
        dismissAll();
        final Dialog dialog = new Dialog(context, android.R.style.Theme_Translucent_NoTitleBar);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCanceledOnTouchOutside(true);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.dialog_delete_account);

        TextView tvMessaage = dialog.findViewById(R.id.tv_version_of_swoope_app);

        WindowManager.LayoutParams layoutParams = dialog.getWindow().getAttributes();
        layoutParams.dimAmount = 0.7f;
        layoutParams.height = ViewGroup.LayoutParams.WRAP_CONTENT;
        layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT;
        dialog.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        dialog.getWindow().setAttributes(layoutParams);
        dialog.getWindow().setWindowAnimations(R.style.AnimationCenterPopUp);
        dialogList.add(dialog);
        dialog.show();

        dialog.findViewById(R.id.tv_send_message).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                listener.onNegativeClick();
            }
        });
        dialog.findViewById(R.id.tv_delete_account).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                listener.onPositiveClick();
            }
        });

        dialog.findViewById(R.id.iv_close).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });


    }

    public static Bitmap mergeBitmaps(Bitmap overlay, Bitmap bitmap) {

        int height = bitmap.getHeight();
        int width = bitmap.getWidth();

        Bitmap combined = Bitmap.createBitmap(width, height, bitmap.getConfig());
        Canvas canvas = new Canvas(combined);
        int canvasWidth = canvas.getWidth();
        int canvasHeight = canvas.getHeight();

        canvas.drawBitmap(bitmap, new Matrix(), null);

        int centreX = (canvasWidth - overlay.getWidth()) / 2;
        int centreY = (canvasHeight - overlay.getHeight()) / 2;
        canvas.drawBitmap(overlay, centreX, centreY, null);

        return combined;
    }

    public static void showMinValueDialog(Context context, String message, final OnDialogClickListener onDialogClickListener) {
        dismissAll();
        final Dialog dialog = new Dialog(context, android.R.style.Theme_Translucent_NoTitleBar);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCanceledOnTouchOutside(true);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.dialog_single_button);
        TextView tvMessaage = dialog.findViewById(R.id.tvMessage);
        tvMessaage.setText(message);
        WindowManager.LayoutParams layoutParams = dialog.getWindow().getAttributes();
        layoutParams.dimAmount = 0.7f;
        layoutParams.height = ViewGroup.LayoutParams.WRAP_CONTENT;
        layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT;
        dialog.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        dialog.getWindow().setAttributes(layoutParams);
        dialog.getWindow().setWindowAnimations(R.style.AnimationCenterPopUp);
        dialogList.add(dialog);
        dialog.show();
        dialog.findViewById(R.id.btnOkay).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                if (onDialogClickListener != null)
                    onDialogClickListener.onPositiveClick();
            }
        });
    }

    public static void showAlertDialogEndSelf(Context context, String message, boolean isRed, final OnDialogClickListener onDialogClickListener) {
        dismissAll();
        final Dialog dialog = new Dialog(context, android.R.style.Theme_Translucent_NoTitleBar);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCanceledOnTouchOutside(true);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.layout_alert_dialog_no_button);
        TextView tvMessaage = dialog.findViewById(R.id.tvMessage);
        if (isRed) {
            tvMessaage.setTextColor(ContextCompat.getColor(context, R.color.color_light_red));
        } else tvMessaage.setTextColor(ContextCompat.getColor(context, R.color.app_header_color));

        tvMessaage.setText(message);
       /* dialog.findViewById(R.id.btnCancel).setVisibility(View.GONE);
        dialog.findViewById(R.id.btnOkay).setVisibility(View.INVISIBLE);*/
        WindowManager.LayoutParams layoutParams = dialog.getWindow().getAttributes();
        layoutParams.dimAmount = 0.7f;
        layoutParams.height = ViewGroup.LayoutParams.WRAP_CONTENT;
        layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT;
        dialog.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        dialog.getWindow().setAttributes(layoutParams);
        dialog.getWindow().setWindowAnimations(R.style.AnimationCenterPopUp);
        dialogList.add(dialog);
        dialog.show();

        mCountDownTimer = new CountDownTimer(2000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {
                dialog.dismiss();
                if (onDialogClickListener != null)
                    onDialogClickListener.onPositiveClick();
            }
        };
        mCountDownTimer.start();


       /* dialog.findViewById(R.id.btnOkay).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                if (onDialogClickListener != null)
                    onDialogClickListener.onPositiveClick();
                mCountDownTimer.cancel();


            }
        });*/
    }


    public static void dialogCouponApply(Context context, String code, final OnDialogClickListener onDialogClickListener) {
        dismissAll();
        final Dialog dialog = new Dialog(context, android.R.style.Theme_Translucent_NoTitleBar);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCanceledOnTouchOutside(true);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.layout_dialog_coupon_apply);
        TextView tvCode = dialog.findViewById(R.id.tv_code);
        tvCode.setText(code);
        WindowManager.LayoutParams layoutParams = dialog.getWindow().getAttributes();
        layoutParams.dimAmount = 0.7f;
        layoutParams.height = ViewGroup.LayoutParams.WRAP_CONTENT;
        layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT;
        dialog.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        dialog.getWindow().setAttributes(layoutParams);
        dialog.getWindow().setWindowAnimations(R.style.AnimationCenterPopUp);
        dialogList.add(dialog);
        dialog.show();

        mCountDownTimer = new CountDownTimer(1500, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {
                dialog.dismiss();
                if (onDialogClickListener != null)
                    onDialogClickListener.onPositiveClick();
            }
        };
        mCountDownTimer.start();

    }


    public static void showAlwayAlertDialogWithSingleButton(Context context, String message, final OnDialogClickListener onDialogClickListener) {
        // dismissAll();
        final Dialog dialog = new Dialog(context, android.R.style.Theme_Translucent_NoTitleBar);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCanceledOnTouchOutside(true);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.layout_alert_dialog);
        TextView tvMessaage = dialog.findViewById(R.id.tvMessage);
        tvMessaage.setText(message);
        dialog.findViewById(R.id.btnCancel).setVisibility(View.GONE);
        WindowManager.LayoutParams layoutParams = dialog.getWindow().getAttributes();
        layoutParams.dimAmount = 0.7f;
        layoutParams.height = ViewGroup.LayoutParams.WRAP_CONTENT;
        layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT;
        dialog.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        dialog.getWindow().setAttributes(layoutParams);
        dialog.getWindow().setWindowAnimations(R.style.AnimationCenterPopUp);
        //  dialogList.add(dialog);
        dialog.show();
        dialog.findViewById(R.id.btnOkay).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                if (onDialogClickListener != null)
                    onDialogClickListener.onPositiveClick();
            }
        });
    }

    public static void showAlertDialogWithSingleButton(Context context, String message, final OnDialogClickListener onDialogClickListener) {
        dismissAll();
        final Dialog dialog = new Dialog(context, android.R.style.Theme_Translucent_NoTitleBar);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCanceledOnTouchOutside(true);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.layout_alert_dialog);
        TextView tvMessaage = dialog.findViewById(R.id.tvMessage);
        tvMessaage.setText(message);
        dialog.findViewById(R.id.btnCancel).setVisibility(View.GONE);
        WindowManager.LayoutParams layoutParams = dialog.getWindow().getAttributes();
        layoutParams.dimAmount = 0.7f;
        layoutParams.height = ViewGroup.LayoutParams.WRAP_CONTENT;
        layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT;
        dialog.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        dialog.getWindow().setAttributes(layoutParams);
        dialog.getWindow().setWindowAnimations(R.style.AnimationCenterPopUp);
        dialogList.add(dialog);
        dialog.show();
        dialog.findViewById(R.id.btnOkay).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                if (onDialogClickListener != null)
                    onDialogClickListener.onPositiveClick();
            }
        });
    }

    public static void dismiss(Dialog dialog) {
        dialogList.remove(dialog);
        dialog.dismiss();
    }

    public static void stampTextInfoDialog(final Context context, String heading, String content, String imgUrl, boolean isShowScratch) {
        dismissAll();
        final Dialog dialog = new Dialog(context, android.R.style.Theme_Translucent_NoTitleBar);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCanceledOnTouchOutside(true);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.dialog_stamp_info);

        final RelativeLayout rl_main = dialog.findViewById(R.id.rl_main);
        final ScratchTextView scratch_view_before = (ScratchTextView) dialog.findViewById(R.id.scratch_view_before);
        final TextView tv_scratch_view_after = (TextView) dialog.findViewById(R.id.tv_scratch_view_after);

        if (!isShowScratch) {
            scratch_view_before.setVisibility(View.GONE);
            tv_scratch_view_after.setVisibility(View.VISIBLE);
        } else {
            scratch_view_before.setVisibility(View.VISIBLE);
            tv_scratch_view_after.setVisibility(View.GONE);
        }


        scratch_view_before.setText("Scratch here");


        if (scratch_view_before != null) {
            scratch_view_before.setRevealListener(new ScratchTextView.IRevealListener() {
                @Override
                public void onRevealed(ScratchTextView tv) {


                    FlipAnimator animator = new FlipAnimator(scratch_view_before, tv_scratch_view_after, rl_main.getWidth() / 2, rl_main.getHeight() / 2);
                    animator.setDuration(800);
                    animator.setRotationDirection(FlipAnimator.DIRECTION_Y);
                    rl_main.startAnimation(animator);

                    tv_scratch_view_after.setText(R.string.dummy_item_refer_message);
                }

                @Override
                public void onRevealPercentChangedListener(ScratchTextView stv, float percent) {
                    // on percent reveal.
                  //  HelperClass.toast(context, "You scratched " + percent + " %");
                }
            });
        }


        WindowManager.LayoutParams layoutParams = dialog.getWindow().getAttributes();
        layoutParams.dimAmount = 0.7f;
        layoutParams.height = ViewGroup.LayoutParams.WRAP_CONTENT;
        layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT;
        dialog.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        dialog.getWindow().setAttributes(layoutParams);
        dialog.getWindow().setWindowAnimations(R.style.AnimationCenterPopUp);
        dialogList.add(dialog);
        dialog.show();

        dialog.findViewById(R.id.tv_ok).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
    }

    public static void stampImageInfoDialog(final Context context, String heading, String content, String imgUrl, final OnDialogClickListener listener) {
        dismissAll();
        final Dialog dialog = new Dialog(context, android.R.style.Theme_Translucent_NoTitleBar);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCanceledOnTouchOutside(true);
        dialog.setCancelable(false);
        LayoutInflater inflater = ((LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE));
        dialog.setContentView(R.layout.activity_rl_scratch);

        ScratchRelativeLayoutView scratchRelativeLayoutView = dialog.findViewById(R.id.scratch_card);
        scratchRelativeLayoutView.setStrokeWidth(20);

        // scratchRelativeLayoutView.setScratchView(R.layout.lyt_scratch);  // for activity

        /**
         Using Inflated View for dialog
         */
        final View scratchView = inflater.inflate(R.layout.lyt_scratch, scratchRelativeLayoutView, true);
        scratchRelativeLayoutView.setScratchView(scratchView, scratchRelativeLayoutView);


        scratchRelativeLayoutView.setRevealListener(new ScratchRelativeLayoutView.IRevealListener() {
            @Override
            public void onRevealed(ScratchRelativeLayoutView tv) {
                // on reveal
                HelperClass.toast(context, "You scratched " + "");
            }

            @Override
            public void onRevealPercentChangedListener(ScratchRelativeLayoutView siv, float percent) {
                // on percent change
              //  HelperClass.toast(context, "You scratched " + percent + " %");
            }
        });

        WindowManager.LayoutParams layoutParams = dialog.getWindow().getAttributes();
        layoutParams.dimAmount = 0.7f;
        layoutParams.height = ViewGroup.LayoutParams.MATCH_PARENT;
        layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT;
        dialog.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        dialog.getWindow().setAttributes(layoutParams);
        dialog.getWindow().setWindowAnimations(R.style.AnimationCenterPopUp);
        dialogList.add(dialog);
        dialog.show();

        dialog.findViewById(R.id.tv_done).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                listener.onPositiveClick();
            }
        });
    }

    public static void dialogReasonForCancellation(Context context, List<MyOrderResponseModel.ReturnReasonsBean> reasonsBeanList, final DrawerListner listener) {
        dismissAll();
        final Dialog dialog = new Dialog(context, android.R.style.Theme_Translucent_NoTitleBar);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCanceledOnTouchOutside(true);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.dialog_reason_for_cancellation);

        WindowManager.LayoutParams layoutParams = dialog.getWindow().getAttributes();
        layoutParams.dimAmount = 0.7f;
        layoutParams.height = ViewGroup.LayoutParams.WRAP_CONTENT;
        layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT;
        dialog.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        dialog.getWindow().setAttributes(layoutParams);
        dialog.getWindow().setWindowAnimations(R.style.AnimationCenterPopUp);
        dialogList.add(dialog);
        dialog.show();
        RecyclerView recyclerView = dialog.findViewById(R.id.rv_reason_list);
        final int[] reasonId = new int[1];
        dialog.findViewById(R.id.iv_close).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.findViewById(R.id.tv_submit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                if (listener != null)
                    listener.onDrawerSelect(reasonId[0], REPLACE_REFUND);
            }
        });

        //  this.selectionListner = (MyOrderActivity) mContext;
        CancellationReasonAdapter loyaltyStampVoucherAdapter = new CancellationReasonAdapter(context, reasonsBeanList, new DrawerListner() {
            @Override
            public void onDrawerSelect(int position, int clickId) {
                reasonId[0] = position;
            }
        }, 0);
        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(loyaltyStampVoucherAdapter);
    }

    public static void orderSuccesDialog(Activity context, String message, boolean status, String totalAmountPayable, String email,
                                         String orderId, String image,
                                         double couponPrice, final OnDialogClickListener onDialogClickListener,
                                         double loyelty_consumed, double loyaltyApplied) {
        dismissAll();
        final Dialog dialog = new Dialog(context, android.R.style.Theme_Translucent_NoTitleBar);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCanceledOnTouchOutside(true);
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.dialog_after_order);
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
        WindowManager.LayoutParams layoutParams = dialog.getWindow().getAttributes();
        layoutParams.dimAmount = 0.7f;
        layoutParams.height = ViewGroup.LayoutParams.MATCH_PARENT;
        layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT;
        layoutParams.gravity = Gravity.CENTER;
        dialog.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        dialog.getWindow().setAttributes(layoutParams);
        // dialog.getWindow().setWindowAnimations(R.style.AnimationCenterPopIn);
        /*Window window = getWindow();
        WindowManager.LayoutParams layoutParams = getWindow().getAttributes();
        // layoutParams.gravity = Gravity.CENTER;
        window.setAttributes(layoutParams);
        getWindow().setBackgroundDrawableResource(android.R.color.transparent);*/
        dialogList.add(dialog);
        dialog.show();

        TextView tvMessaage = dialog.findViewById(R.id.tv_order_success);
        TextView tv_email = dialog.findViewById(R.id.tv_email);
        TextView tv_order_amount = dialog.findViewById(R.id.tv_order_amount);
        TextView tv_order_id = dialog.findViewById(R.id.tv_order_id);
        TextView tv_view_order_history = dialog.findViewById(R.id.tv_view_order_history);
        TextView tv_email_sent = dialog.findViewById(R.id.tv_email_sent);
        TextView tv_loyalty_earned = dialog.findViewById(R.id.tv_loyalty_earned);

        LinearLayout ll_order_id = dialog.findViewById(R.id.ll_order_id);
        LinearLayout ll_amount = dialog.findViewById(R.id.ll_amount);

        ImageView imageView = dialog.findViewById(R.id.iv_image);
        if (status) {
            ll_order_id.setVisibility(View.VISIBLE);
            ll_amount.setVisibility(View.VISIBLE);
            //  imageView.setImageResource(R.drawable.ic_smile);
            tv_email.setText(email);

            double amt = Double.parseDouble(totalAmountPayable) - loyaltyApplied;


            tv_order_amount.setText(context.getString(R.string.pound) + String.format("%.2f", amt));
            //  tv_order_amount.setText(context.getString(R.string.pound) + String.format("%.2f", Double.parseDouble(totalAmountPayable)));
            tv_order_id.setText(orderId);
            if (loyelty_consumed > 0)
                tv_loyalty_earned.setText("You got " + loyelty_consumed + " loyalty point from this order.");
            tv_view_order_history.setText(context.getString(R.string.view_order_history));

            Glide.with(context).load(TextUtils.isEmpty(image) ? ApiRequestUrl.BASE_URL_VENUE + image : ApiRequestUrl.BASE_URL_VENUE + image).apply(new RequestOptions()
                    .placeholder(R.drawable.ic_app_icon))
                    .into(imageView);

        } else {
            imageView.setImageResource(R.drawable.ic_sad);
            tv_view_order_history.setText(context.getString(R.string.try_again));
            ll_order_id.setVisibility(View.GONE);
            ll_amount.setVisibility(View.GONE);
            tv_email.setVisibility(View.GONE);
            tv_email_sent.setVisibility(View.GONE);
        }

        tvMessaage.setText(message);
        try {
            mCountDownTimer = new CountDownTimer(6000, 1000) {
                @Override
                public void onTick(long millisUntilFinished) {

                }

                @Override
                public void onFinish() {
                    dialog.dismiss();
                    if (onDialogClickListener != null)
                        onDialogClickListener.onPositiveClick();
                }
            };
            mCountDownTimer.start();
        } catch (Exception ex) {
            ex.printStackTrace();
        }


        dialog.findViewById(R.id.tv_view_order_history).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                if (onDialogClickListener != null)
                    onDialogClickListener.onPositiveClick();
                mCountDownTimer.cancel();
            }
        });

    }

    public static void reservationOrderSuccesDialog(Context context, String message, boolean status, String totalAmountPayable, String email,
                                                    String orderId, String image, String date, int nofGuest, String bookinNo, final OnDialogClickListener onDialogClickListener) {
        dismissAll();
        final Dialog dialog = new Dialog(context, android.R.style.Theme_Translucent_NoTitleBar);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCanceledOnTouchOutside(true);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.dialog_after_reservation);
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
        WindowManager.LayoutParams layoutParams = dialog.getWindow().getAttributes();
        layoutParams.dimAmount = 0.7f;
        layoutParams.height = ViewGroup.LayoutParams.MATCH_PARENT;
        layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT;
        layoutParams.gravity = Gravity.CENTER;
        dialog.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        dialog.getWindow().setAttributes(layoutParams);
        dialogList.add(dialog);
        dialog.show();

        TextView tv_booking_no = dialog.findViewById(R.id.tv_booking_no);
        TextView tv_booking_time = dialog.findViewById(R.id.tv_booking_time);
        TextView tv_no_of_guest = dialog.findViewById(R.id.tv_no_of_guest);
        TextView tv_payable_amt = dialog.findViewById(R.id.tv_payable_amt);
        TextView tv_view_booking = dialog.findViewById(R.id.tv_view_booking);
        TextView tv_booking_summery = dialog.findViewById(R.id.tv_booking_summery);
        ImageView iv_image_venue = dialog.findViewById(R.id.iv_image_venue);
        RelativeLayout rl_order_details = dialog.findViewById(R.id.rl_order_details);

        ImageView imageView = dialog.findViewById(R.id.iv_image);
        if (status) {

            //  tv_confirm_meesage.setText("Your booking has been confirmed for "+HelperClass.formatddMMMMyyyyDayName(date)+" at "+HelperClass.formatDateTimeTO_Time(date)+" with "+nofGuest+ " guest.");
            tv_booking_no.setText(bookinNo);
            tv_booking_time.setText(HelperClass.formatddMMMMyyyyDayName(date) + ", " + HelperClass.formatDateTimeTO_Time(date));
            tv_no_of_guest.setText(nofGuest + " Guest");
            tv_payable_amt.setText(context.getString(R.string.pound) + String.format("%.2f", Double.parseDouble(totalAmountPayable)));
            tv_view_booking.setText(context.getString(R.string.view_booking_history));

            Glide.with(context).load(TextUtils.isEmpty(image) ? image : image).apply(new RequestOptions()
                    .placeholder(R.drawable.ic_app_icon))
                    .into(iv_image_venue);

        } else {
            rl_order_details.setVisibility(View.GONE);
            imageView.setImageResource(R.drawable.ic_sad);
            imageView.setColorFilter(ContextCompat.getColor(context, R.color.color_light_red), android.graphics.PorterDuff.Mode.MULTIPLY);
            int paddingDp = 65;
            float density = context.getResources().getDisplayMetrics().density;
            int paddingPixel = (int) (paddingDp * density);
            //  int padding_5dp = (int) (5 * density + 0.5f);
            imageView.setPadding(paddingDp, paddingPixel, paddingDp, paddingDp);


            tv_booking_summery.setTextColor(ContextCompat.getColor(context, R.color.color_light_red));
            tv_booking_summery.setText("Sorry Please Try Again ! \nBooking Failed.");
            tv_booking_no.setText(bookinNo);
            tv_booking_time.setText(date);
            tv_no_of_guest.setText(nofGuest + " Guest");
            tv_payable_amt.setText("");
            tv_view_booking.setText(context.getString(R.string.oops) + " " + context.getString(R.string.try_again));
        }

        mCountDownTimer = new CountDownTimer(8000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {

                dialog.dismiss();
                if (onDialogClickListener != null)
                    onDialogClickListener.onPositiveClick();
            }
        };
        mCountDownTimer.start();

        dialog.findViewById(R.id.tv_view_booking).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                if (onDialogClickListener != null)
                    onDialogClickListener.onPositiveClick();
                mCountDownTimer.cancel();
            }
        });

    }

    public static void offerNotificationDialog(final Context context, String title, String body, String imgUrl, NotificationModel responseModel, DrawerListner listner) {
        dismissAll();
        final Dialog dialog = new Dialog(context, android.R.style.Theme_Translucent_NoTitleBar);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCanceledOnTouchOutside(true);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.dialog_offer_notification);

        WindowManager.LayoutParams layoutParams = dialog.getWindow().getAttributes();
        layoutParams.dimAmount = 0.7f;
        layoutParams.height = ViewGroup.LayoutParams.WRAP_CONTENT;
        layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT;
        dialog.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        dialog.getWindow().setAttributes(layoutParams);
        dialog.getWindow().setWindowAnimations(R.style.AnimationCenterPopUp);
        dialogList.add(dialog);
        dialog.show();
        // imgUrl=  BASE_URL+imgUrl;
        final TextView tv_title = (TextView) dialog.findViewById(R.id.tv_title);
        final TextView tv_body = (TextView) dialog.findViewById(R.id.tv_body);
        final ImageView iv_logo = (ImageView) dialog.findViewById(R.id.iv_logo);
        Glide.with(context).load(/*BASE_URL +*/ imgUrl).apply(new RequestOptions()
                .placeholder(R.drawable.ic_app_icon))
                .into(iv_logo);

        if (!TextUtils.isEmpty(title))
            tv_title.setText(title);
        if (!TextUtils.isEmpty(body))
            tv_body.setText(body);
        iv_logo.setImageResource(R.drawable.ic_app_icon);
        dialog.findViewById(R.id.tv_shop_now).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                listner.onDrawerSelect(0, 0);
            }
        });
        dialog.findViewById(R.id.tv_cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
    }

    public static void simpleNotificationDialog(final Context context, String title, String body, String imgUrl, NotificationModel responseModel, DrawerListner listner) {
        dismissAll();
        final Dialog dialog = new Dialog(context, android.R.style.Theme_Translucent_NoTitleBar);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCanceledOnTouchOutside(true);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.dialog_simple_notification);

        WindowManager.LayoutParams layoutParams = dialog.getWindow().getAttributes();
        layoutParams.dimAmount = 0.7f;
        layoutParams.height = ViewGroup.LayoutParams.WRAP_CONTENT;
        layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT;
        dialog.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        dialog.getWindow().setAttributes(layoutParams);
        dialog.getWindow().setWindowAnimations(R.style.AnimationCenterPopUp);
        dialogList.add(dialog);
        dialog.show();
        // imgUrl=  BASE_URL+imgUrl;
        final TextView tv_title = dialog.findViewById(R.id.tv_title);
        final TextView tv_body = dialog.findViewById(R.id.tv_body);
        final ImageView iv_logo = dialog.findViewById(R.id.iv_logo);
        final TextView tv_shop_now = dialog.findViewById(R.id.tv_shop_now);
        tv_shop_now.setText("DONE");
        Glide.with(context).load(/*BASE_URL +*/ imgUrl).apply(new RequestOptions()
                .placeholder(R.drawable.ic_app_icon))
                .into(iv_logo);


        if (!TextUtils.isEmpty(title))
            tv_title.setText(title);


        if (!TextUtils.isEmpty(body))
            tv_body.setText(body);
        iv_logo.setImageResource(R.drawable.ic_app_icon);
        dialog.findViewById(R.id.tv_shop_now).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                listner.onDrawerSelect(0, 0);
            }
        });
        dialog.findViewById(R.id.tv_cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
    }

    public static void dialogGeneratePinSuccess(Context context, String message, String title, final OnDialogClickListener onDialogClickListener) {
        dismissAll();
        final Dialog dialog = new Dialog(context, android.R.style.Theme_Translucent_NoTitleBar);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCanceledOnTouchOutside(true);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.dialog_logout);
        TextView tvCancel = dialog.findViewById(R.id.btnCancel);
        TextView btnOkay = dialog.findViewById(R.id.btnOkay);
        View viewDiv = dialog.findViewById(R.id.viewDiv);
        tvCancel.setVisibility(View.GONE);
        viewDiv.setVisibility(View.GONE);
        TextView tvTitle = dialog.findViewById(R.id.tv_title);
        TextView tvMessaage = dialog.findViewById(R.id.tvMessage);
        tvTitle.setText(title);
        tvMessaage.setText(message);
        btnOkay.setText(context.getString(R.string.ok));

        WindowManager.LayoutParams layoutParams = dialog.getWindow().getAttributes();
        layoutParams.dimAmount = 0.7f;
        layoutParams.height = ViewGroup.LayoutParams.WRAP_CONTENT;
        layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT;
        dialog.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        dialog.getWindow().setAttributes(layoutParams);
        dialog.getWindow().setWindowAnimations(R.style.AnimationCenterPopUp);
        dialogList.add(dialog);
        dialog.show();
        dialog.findViewById(R.id.btnOkay).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                if (onDialogClickListener != null)
                    onDialogClickListener.onPositiveClick();
            }
        });
        dialog.findViewById(R.id.btnCancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialog.dismiss();
            }
        });
    }

    public static void dialogAddCardSuccess(Context context, String message, final OnDialogClickListener onDialogClickListener) {
        dismissAll();
        final Dialog dialog = new Dialog(context, android.R.style.Theme_Translucent_NoTitleBar);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCanceledOnTouchOutside(true);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.dialog_card_add_success);

        TextView tv_success_message = dialog.findViewById(R.id.tv_success_message);
        tv_success_message.setText(message);

        WindowManager.LayoutParams layoutParams = dialog.getWindow().getAttributes();
        layoutParams.dimAmount = 0.7f;
        layoutParams.height = ViewGroup.LayoutParams.WRAP_CONTENT;
        layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT;
        dialog.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        dialog.getWindow().setAttributes(layoutParams);
        dialog.getWindow().setWindowAnimations(R.style.AnimationCenterPopUp);
        dialogList.add(dialog);
        dialog.show();
        dialog.findViewById(R.id.tv_ok).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                if (onDialogClickListener != null)
                    onDialogClickListener.onPositiveClick();
            }
        });
    }

    private void buildDialog(int animationSource, String type, Context context) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Animation Dialog");
        builder.setMessage(type);
        builder.setNegativeButton("OK", null);
        AlertDialog dialog = builder.create();
        dialog.getWindow().getAttributes().windowAnimations = animationSource;
        dialog.show();
    }
}
