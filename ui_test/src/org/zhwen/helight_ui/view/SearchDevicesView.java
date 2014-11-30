package org.zhwen.helight_ui.view;


import java.util.Random;

import android.app.Activity;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;



import org.zhwen.helight_ui.R;
import org.zhwen.helight_ui.TabActivity;

public class SearchDevicesView extends BaseView{
	
	public static final String TAG = "SearchDevicesView";

	private float offsetArgs = 0;
	private boolean isSearching = true;
	private Bitmap bitmap;
	private Bitmap[] bitmap1=new Bitmap[10];
	private Bitmap bitmap2;
	private int Bitmap1_index;
	private int iCountDraw = 0;
	private int iHeight;
	private int iWidth;
	private Context mContext;
	private int iCountDaren=0;
	
	public boolean isSearching() {
		return isSearching;
	}

	public void setSearching(boolean isSearching) {
		this.isSearching = isSearching;
		offsetArgs = 0;
		invalidate();
	}

	public SearchDevicesView(Context context) {
		super(context);
		initBitmap(context);
	}
	
	public SearchDevicesView(Context context, AttributeSet attrs) {
		super(context, attrs);
		initBitmap(context);
	}

	public SearchDevicesView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		initBitmap(context);
	}
	
	private void initBitmap(Context context){
		if(bitmap == null){
			DisplayMetrics mDisplayMetrics = new DisplayMetrics();
			mContext = context;
			((Activity)context).getWindowManager().getDefaultDisplay().getMetrics(mDisplayMetrics);
			iWidth = mDisplayMetrics.widthPixels;
			iHeight = mDisplayMetrics.heightPixels;
			bitmap = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.gplus_search_bg),iWidth,iHeight,true);
		}
		if(bitmap1[0] == null){
			bitmap1[0] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.touxiang0),100,100,true);
			bitmap1[1] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.touxiang1),100,100,true);
			bitmap1[2] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.touxiang2),100,100,true);
			bitmap1[3] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.touxiang3),100,100,true);
			bitmap1[4] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.touxiang4),100,100,true);
			bitmap1[5] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.touxiang5),100,100,true);
			bitmap1[6] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.touxiang6),100,100,true);
			bitmap1[7] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.touxiang7),100,100,true);
			bitmap1[8] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.touxiang8),100,100,true);
			bitmap1[9] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.touxiang9),100,100,true);
			bitmap1[0] = createCircleImage(bitmap1[0],100);
			bitmap1[1] = createCircleImage(bitmap1[1],100);
			bitmap1[2] = createCircleImage(bitmap1[2],100);
			bitmap1[3] = createCircleImage(bitmap1[3],100);
			bitmap1[4] = createCircleImage(bitmap1[4],100);
			bitmap1[5] = createCircleImage(bitmap1[5],100);
			bitmap1[6] = createCircleImage(bitmap1[6],100);
			bitmap1[7] = createCircleImage(bitmap1[7],100);
			bitmap1[8] = createCircleImage(bitmap1[8],100);
			bitmap1[9] = createCircleImage(bitmap1[9],100);
		}
		if(bitmap2 == null){
			bitmap2 = Bitmap.createBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.gplus_search_args));
		}
	}

	/** 
     * 根据原图和变长绘制圆形图片 
     *  
     * @param source 
     * @param min 
     * @return 
     */  
    private Bitmap createCircleImage(Bitmap source, int min)  
    {  
        final Paint paint = new Paint();  
        paint.setAntiAlias(true);  
        Bitmap target = Bitmap.createBitmap(min, min, Config.ARGB_8888);  
        /** 
         * 产生一个同样大小的画布 
         */  
        Canvas canvas = new Canvas(target);  
        /** 
         * 首先绘制圆形 
         */  
        canvas.drawCircle(min / 2, min / 2, min / 2, paint);  
        /** 
         * 使用SRC_IN 
         */  
        paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));  
        /** 
         * 绘制图片 
         */  
        canvas.drawBitmap(source, 0, 0, paint);  
        return target;  
    }  

	@SuppressLint("DrawAllocation")
	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);	
		iCountDraw++;
		//canvas.drawBitmap(bitmap, getWidth() / 2 - bitmap.getWidth() / 2, getHeight() / 2 - bitmap.getHeight() / 2, null);
		canvas.drawBitmap(bitmap, 0, 0, null);
		
		if(isSearching){
			Rect rMoon = new Rect(getWidth()/2-bitmap2.getWidth(),getHeight()/2,getWidth()/2,getHeight()/2+bitmap2.getHeight()); 
			canvas.rotate(offsetArgs,getWidth()/2,getHeight()/2);
			canvas.drawBitmap(bitmap2,null,rMoon,null);
			offsetArgs = offsetArgs + 3;
			// 画随机园
			if (iCountDraw%4==0){
		        Random random = new Random(); 
		        float cx = getWidth()/2-bitmap2.getWidth()+(float)(random.nextInt(getWidth()/2));        //  随机生成圆心横坐标（0至200） 
		        float cy = getHeight()/2 + (float)(random.nextInt(bitmap2.getHeight()/2));        //  随机生成圆心纵坐标（0至400） 
		        float radius =(float)( 5 + random.nextInt(5));      //  随机生成圆的半径（10至100） 
		        int r = 255;//random.nextInt(256); 
		        int g = 255;//random.nextInt(256); 
		        int b = 255;//random.nextInt(256); 
		        int mColor = Color.rgb(r, g, b);                    // 随机生成颜色
		        Paint p = new Paint();
		        p.setColor(mColor);
		        canvas.drawCircle(cx, cy, radius, p);
			}
	        
			if (iCountDraw>20){
				iCountDaren++;
				iCountDraw=0;
				Bitmap1_index = (int)(Math.random() * 10);
			}
			canvas.rotate(-(offsetArgs-3),getWidth()/2,getHeight()/2);
	    	canvas.drawBitmap(bitmap1[Bitmap1_index],  getWidth() / 2 - bitmap1[Bitmap1_index].getWidth() / 2, getHeight() / 2 - bitmap1[Bitmap1_index].getHeight() / 2, null);
		}else{
			
			canvas.drawBitmap(bitmap2,  getWidth() / 2  - bitmap2.getWidth() , getHeight() / 2, null);
		}

		Paint paint = new Paint();
		paint.setColor(Color.WHITE);//可以直接设置颜色，也可通过Argb方法，，设置精确颜色

		Typeface mFace=Typeface.createFromAsset(getContext().getAssets(), "bauhs93.ttf"); 
		paint.setTypeface(mFace);  
		paint.setTextSize(100);//Size优先级更高
		canvas.drawText(iCountDaren+"", getWidth() / 2-50, getHeight()/2-bitmap2.getHeight()-30, paint);

    	if(isSearching) invalidate();
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {	
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:		
			handleActionDownEvenet(event);
			return true;
		case MotionEvent.ACTION_MOVE: 
			return true;
		case MotionEvent.ACTION_UP:
			return true;
		}
		return super.onTouchEvent(event);
	}
	
	private void handleActionDownEvenet(MotionEvent event){
		RectF rectF = new RectF(getWidth() / 2 - bitmap1[Bitmap1_index].getWidth() / 2, 
								getHeight() / 2 - bitmap1[Bitmap1_index].getHeight() / 2, 
								getWidth() / 2 + bitmap1[Bitmap1_index].getWidth() / 2, 
								getHeight() / 2 + bitmap1[Bitmap1_index].getHeight() / 2);
		
		if(rectF.contains(event.getX(), event.getY())){
			Log.d(TAG, "click search device button");
			if(!isSearching()) {
				setSearching(true);
			}else{
				setSearching(false);
				Intent intent = new Intent(mContext, TabActivity.class);
				mContext.startActivity(intent);
				// this.getActivity().finish();
			}
		}
	}
}