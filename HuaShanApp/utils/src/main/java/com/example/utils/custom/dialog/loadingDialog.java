package com.example.utils.custom.dialog;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;

/**
 * Created by Administrator on 2016/12/26.
 */

public class loadingDialog extends ProgressDialog {

    public loadingDialog(Context context) {
        super(context);
    }

    public loadingDialog(Context context, int theme) {
        super(context, theme);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}
