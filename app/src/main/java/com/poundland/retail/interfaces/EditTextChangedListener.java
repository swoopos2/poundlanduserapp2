package com.poundland.retail.interfaces;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import com.google.android.material.textfield.TextInputLayout;

public class EditTextChangedListener implements TextWatcher {
    public static final String TAG = EditTextChangedListener.class.getSimpleName();
    private Context mContext;
    private EditText mEditText;
    private TextInputLayout textInputLayout;
    private int action;
    private EditTextChangedAction mCallBack;

    public EditTextChangedListener(Context mContext, EditText mEditText, int action, EditTextChangedAction mCallBack) {
        this.mContext = mContext;
        this.mEditText = mEditText;
        this.action = action;
        this.mCallBack = mCallBack;
    }
    public EditTextChangedListener(Context mContext, TextInputLayout textInputLayout, EditText mEditText, int action, EditTextChangedAction mCallBack) {
        this.mContext = mContext;
        this.mEditText = mEditText;
        this.textInputLayout = textInputLayout;
        this.action = action;
        this.mCallBack = mCallBack;
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        if (textInputLayout!=null){
            textInputLayout.setError(null);
        }
        if (mCallBack != null) {
            mCallBack.onAfterTextChanged(mEditText, action, s);
        }
    }
}
