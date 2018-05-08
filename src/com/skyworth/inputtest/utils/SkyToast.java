package com.skyworth.inputtest.utils;

import com.skyworth.inputtest.R;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class SkyToast{
	private static Toast mToast = null;
	private static TextView mMsgText = null;
	
	public static void showToast(Context context, String msg,int duration, int yOffset)
	{
		if(mToast == null)
        {  
            mToast = new Toast(context); 
        }   
		
		mMsgText = new TextView(context);
		mMsgText.setTextAppearance(context,android.R.attr.textAppearanceMediumInverse);
		mMsgText.setTextSize(25);
		mMsgText.setGravity(Gravity.CENTER);
		mMsgText.setBackgroundResource(R.drawable.toast_style);
		mMsgText.setText(msg);
		
        mToast.setView(mMsgText);
        mToast.setGravity(Gravity.BOTTOM, 0, yOffset);
        mToast.setDuration(duration);

        mToast.show();
	}
	
	public static void showToastForSkyMenu(Context context, String msg,int duration, int yOffset){
		if(mToast == null){  
			mToast = new Toast(context); 
    			mMsgText = new TextView(context);
	    		mMsgText.setTextAppearance(context,android.R.attr.textAppearanceMediumInverse);
	    		mMsgText.setTextSize(25);
	    		mMsgText.setGravity(Gravity.CENTER);
	    		mMsgText.setBackgroundResource(R.drawable.toast_style);
	    		mMsgText.setWidth((int)(mMsgText.getTextSize() * 5.2));
	    		mToast.setView(mMsgText);
		}
		
		mMsgText.setText(msg);		
		mMsgText.setVisibility(View.VISIBLE);

		mToast.setGravity(Gravity.BOTTOM, 0, yOffset);
		mToast.setDuration(duration);
		mToast.show();
	}
	
	public static void showToast(Context context, int res,int duration, int yOffset){
		showToast(context,context.getString(res),duration,yOffset);
	}
	
	public static void showToast(Context context, String msg) {
		showToast(context,msg,Toast.LENGTH_SHORT,40);
	}
	
	public static void showToast(Context context, int res)
	{
		showToast(context,context.getString(res),Toast.LENGTH_SHORT,40);
	}
	
	public static void hide()
	{
		if(mMsgText != null)
		{
			mMsgText.setVisibility(View.GONE);
		}
	}
}
