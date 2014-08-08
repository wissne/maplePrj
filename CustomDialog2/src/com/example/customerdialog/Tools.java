package com.example.customerdialog;

import java.util.List;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Tools {

	public static Dialog createCustomDialog(Context context,
			List<DialogItem> items,int style) {
		LinearLayout dialogView = (LinearLayout) LayoutInflater.from(context)
				.inflate(R.layout.custom_dialog_layout, null);
		final Dialog customDialog = new Dialog(context,style);
		LinearLayout itemView;
		TextView textView;
		for (DialogItem item : items) {
			itemView = (LinearLayout) LayoutInflater.from(context).inflate(
					item.getViewId(), null);
			textView = (TextView) itemView.findViewById(R.id.popup_text);
			textView.setText(item.getTextId());
			textView.setOnClickListener(new OnItemClick(item, customDialog));
			dialogView.addView(itemView);
		}

		WindowManager.LayoutParams localLayoutParams = customDialog.getWindow().getAttributes();
		localLayoutParams.x = 0;
		localLayoutParams.y = -1000;
		localLayoutParams.gravity = 80;
		dialogView.setMinimumWidth(10000);

		customDialog.onWindowAttributesChanged(localLayoutParams);
		customDialog.setCanceledOnTouchOutside(true);
		customDialog.setCancelable(true);
		customDialog.setContentView(dialogView);

		if (context instanceof Activity) {
			Activity activity = (Activity) context;
			if (!activity.isFinishing()) {
				customDialog.show();
			}
		}

		return customDialog;
	}

}
