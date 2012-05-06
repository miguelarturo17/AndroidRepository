package com.nokeyboard;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.EditText;

public class NEditText extends EditText {
    public NEditText(Context context, AttributeSet as) {
        super(context, as);
    }
    @Override
    public boolean onCheckIsTextEditor() {
        return false;
    }  
}
