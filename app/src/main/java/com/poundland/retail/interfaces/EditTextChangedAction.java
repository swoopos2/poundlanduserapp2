package com.poundland.retail.interfaces;

import android.text.Editable;
import android.widget.EditText;

public interface EditTextChangedAction {
    void onAfterTextChanged(EditText editText, int action, Editable s);
}
