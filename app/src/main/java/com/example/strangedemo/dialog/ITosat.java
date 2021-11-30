package com.example.strangedemo.dialog;

import android.content.Context;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.strangedemo.R;

/**
 * 自定义dialog
 */
public class ITosat {

    public static void showTextToas(Context context, String message) {
        if (message == null) return;
        if (TextUtils.isEmpty(message.trim())) return;
        View toastview = LayoutInflater.from(context).inflate(R.layout.toast_text_layout, null);
        TextView text = (TextView) toastview.findViewById(R.id.tv_message);
        text.setText(message);
        Toast toast = new Toast(context);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(toastview);
        toast.show();
    }
}
