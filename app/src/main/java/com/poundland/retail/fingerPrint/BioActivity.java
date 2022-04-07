package com.poundland.retail.fingerPrint;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.poundland.retail.R;
import com.poundland.retail.fingerPrint.Helper.BiometricCallback;
import com.poundland.retail.fingerPrint.Helper.BiometricManager;

public class BioActivity extends AppCompatActivity implements BiometricCallback {
    private Button button;
    BiometricManager mBiometricManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bio2);


        button = findViewById(R.id.btn_authenticate);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                /*
                 *
                 * */
                mBiometricManager = new BiometricManager.BiometricBuilder(BioActivity.this)
                        .setTitle("Authentication!")
                        .setSubtitle("Unlock Swoope Local")
                        .setDescription("Place your finger on the device sensor to verify your identity")
                        .setNegativeButtonText("CANCEL")
                        .build();

                //start authentication
                mBiometricManager.authenticate(BioActivity.this);
            }
        });
    }

    @Override
    public void onSdkVersionNotSupported() {
        Toast.makeText(getApplicationContext(), "biometric_error_sdk_not_supported", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onBiometricAuthenticationNotSupported() {
        Toast.makeText(getApplicationContext(), "biometric_error_hardware_not_supported", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onBiometricAuthenticationNotAvailable() {
        Toast.makeText(getApplicationContext(), "biometric_error_fingerprint_not_available", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onBiometricAuthenticationPermissionNotGranted() {
        Toast.makeText(getApplicationContext(), "biometric_error_permission_not_granted", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onBiometricAuthenticationInternalError(String error) {
        Toast.makeText(getApplicationContext(), error, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onAuthenticationFailed() {
//        Toast.makeText(getApplicationContext(), getString(R.string.biometric_failure), Toast.LENGTH_LONG).show();
    }

    @Override
    public void onAuthenticationCancelled() {
        Toast.makeText(getApplicationContext(), "biometric_cancelled", Toast.LENGTH_LONG).show();
        mBiometricManager.cancelAuthentication();
    }

    @Override
    public void onAuthenticationSuccessful() {
        Toast.makeText(getApplicationContext(), "biometric_success", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onAuthenticationHelp(int helpCode, CharSequence helpString) {
//        Toast.makeText(getApplicationContext(), helpString, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onAuthenticationError(int errorCode, CharSequence errString) {
//        Toast.makeText(getApplicationContext(), errString, Toast.LENGTH_LONG).show();
    }
}