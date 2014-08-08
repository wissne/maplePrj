package com.aurogon.zhuogui.util;

import java.util.List;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.aurogon.zhuogui.R;
 
 
public class CustomDialog extends Dialog {
	
    public CustomDialog(Context context) {
        super(context);
    }
 
    public CustomDialog(Context context, int theme) {
        super(context, theme);
    }
 
    public static class Builder {
        private Context context;
        private String title;
        private String message;
        private String text;
        private String hint;
        private List<String> list;
        private OnItemClickListener listItemClickListener;
        private String positiveButtonText;
        private String negativeButtonText;
        private View contentView;
        private DialogInterface.OnClickListener positiveButtonClickListener;
        private DialogInterface.OnClickListener negativeButtonClickListener;
 
        public Builder(Context context) {
            this.context = context;
        }
 
        public Builder setMessage(String message) {
            this.message = message;
            return this;
        }
        
		public Builder setHint(String hint) {
        	this.hint = hint;
        	return this;
        }

		public void setList(List<String> list, OnItemClickListener listener) {
			this.list = list;
			this.listItemClickListener = listener;
		}

		public Builder setText(String text) {
        	this.text = text;
        	return this;
        }
        
        public String getText() {
        	return this.text;
        }
        
        
 
        /**
         * Set the Dialog message from resource
         * 
         * @param title
         * @return
         */
        public Builder setMessage(int message) {
            this.message = (String) context.getText(message);
            return this;
        }
        
        /**
         * Set the Dialog text from resource
         * 
         * @param text
         * @return
         */
        public Builder setText(int text) {
        	this.text = (String) context.getText(text);
        	return this;
        }
        
        /**
         * Set the Dialog hint from resource
         * 
         * @param hint
         * @return
         */
        public Builder setHint(int hint) {
        	this.hint = (String) context.getText(hint);
        	return this;
        }
 
        /**
         * Set the Dialog title from resource
         * 
         * @param title
         * @return
         */
        public Builder setTitle(int title) {
            this.title = (String) context.getText(title);
            return this;
        }
 
        /**
         * Set the Dialog title from String
         * 
         * @param title
         * @return
         */
 
        public Builder setTitle(String title) {
            this.title = title;
            return this;
        }
 
        public Builder setContentView(View v) {
            this.contentView = v;
            return this;
        }
 
        /**
         * Set the positive button resource and it's listener
         * 
         * @param positiveButtonText
         * @return
         */
        public Builder setPositiveButton(int positiveButtonText,
                DialogInterface.OnClickListener listener) {
            this.positiveButtonText = (String) context
                    .getText(positiveButtonText);
            this.positiveButtonClickListener = listener;
            return this;
        }
 
        public Builder setPositiveButton(String positiveButtonText,
                DialogInterface.OnClickListener listener) {
            this.positiveButtonText = positiveButtonText;
            this.positiveButtonClickListener = listener;
            return this;
        }
 
        public Builder setNegativeButton(int negativeButtonText,
                DialogInterface.OnClickListener listener) {
            this.negativeButtonText = (String) context
                    .getText(negativeButtonText);
            this.negativeButtonClickListener = listener;
            return this;
        }
 
        public Builder setNegativeButton(String negativeButtonText,
                DialogInterface.OnClickListener listener) {
            this.negativeButtonText = negativeButtonText;
            this.negativeButtonClickListener = listener;
            return this;
        }
 
        public CustomDialog create() {
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            // instantiate the dialog with the custom Theme
            final CustomDialog dialog = new CustomDialog(context,R.style.Dialog);
            dialog.setCanceledOnTouchOutside(false);
            View layout = inflater.inflate(R.layout.dialog_normal_layout, null);
            contentView = layout;
            dialog.addContentView(layout, new LayoutParams(
                    LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT));
            // set the dialog title
            ((TextView) layout.findViewById(R.id.title)).setText(title);
            // set the confirm button
            if (positiveButtonText != null) {
                ((Button) layout.findViewById(R.id.positiveButton))
                        .setText(positiveButtonText);
                if (positiveButtonClickListener != null) {
                    ((Button) layout.findViewById(R.id.positiveButton))
                            .setOnClickListener(new View.OnClickListener() {
                                public void onClick(View v) {
//                                	value =((EditText)v.findViewById(R.id.text)).getText().toString(); 
                                    positiveButtonClickListener.onClick(dialog,
                                            DialogInterface.BUTTON_POSITIVE);
                                }
                            });
                }
            } else {
                // if no confirm button just set the visibility to GONE
                layout.findViewById(R.id.positiveButton).setVisibility(
                        View.GONE);
            }
            // set the cancel button
            if (negativeButtonText != null) {
                ((Button) layout.findViewById(R.id.negativeButton))
                        .setText(negativeButtonText);
                if (negativeButtonClickListener != null) {
                    ((Button) layout.findViewById(R.id.negativeButton))
                            .setOnClickListener(new View.OnClickListener() {
                                public void onClick(View v) {
                                    negativeButtonClickListener.onClick(dialog,
                                            DialogInterface.BUTTON_NEGATIVE);
                                }
                            });
                }
            } else {
                // if no confirm button just set the visibility to GONE
                layout.findViewById(R.id.negativeButton).setVisibility(
                        View.GONE);
            }
            // set the content message
            if (message != null) {
                ((TextView) layout.findViewById(R.id.message)).setText(message);
            } else {
//            	if (contentView != null) {
            	layout.findViewById(R.id.message).setVisibility(
                        View.GONE);
                // if no message set
                // add the contentView to the dialog body
            	/* Comment by aurogon
                ((LinearLayout) layout.findViewById(R.id.message))
                        .removeAllViews();
                ((LinearLayout) layout.findViewById(R.id.message)).addView(
                        contentView, new LayoutParams(
                                LayoutParams.WRAP_CONTENT,
                                LayoutParams.WRAP_CONTENT));
            	}
			 */
            }
            // set the content message
            if (text != null || hint != null) {
                ((EditText) layout.findViewById(R.id.text)).setText(text);
                ((EditText) layout.findViewById(R.id.text)).setHint(hint);
                ((EditText) layout.findViewById(R.id.text)).setFocusable(true);
            } else {
            	layout.findViewById(R.id.text).setVisibility(
                        View.GONE);
            }     
            
            if (list != null && list.size() > 0) {
            	((ListView)layout.findViewById(R.id.listView)).setAdapter(new ArrayAdapter<String>(context, android.R.layout.simple_list_item_1, list));
                if (listItemClickListener != null) {
                    ((ListView) layout.findViewById(R.id.listView))
                            .setOnItemClickListener(new OnItemClickListener() {
								public void onItemClick(AdapterView<?> arg0,
										View arg1, int arg2, long arg3) {
									text = list.get(arg2);
									((EditText) contentView.findViewById(R.id.text)).setText(text);
//									((ListItem)arg1).
									positiveButtonClickListener.onClick(dialog,
                                            DialogInterface.BUTTON_POSITIVE);
								}
                            });
                }
            } else {
            	layout.findViewById(R.id.listView).setVisibility(
                        View.GONE);
            }            
            
            dialog.setContentView(layout);
            return dialog;
        }
 
    }
}

