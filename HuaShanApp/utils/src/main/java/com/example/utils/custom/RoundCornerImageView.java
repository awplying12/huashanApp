package com.example.utils.custom;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.widget.ImageView;

public class RoundCornerImageView extends ImageView {
	
	private Path clipPath;
	private float rx=60;
	private float ry=60;
	public RoundCornerImageView(Context context) {
		super(context);
		clipPath = new Path();
	}

	public RoundCornerImageView(Context context, AttributeSet attrs) {
		super(context, attrs);
		clipPath = new Path();
	}

	public RoundCornerImageView(Context context, AttributeSet attrs,
			int defStyle) {
		super(context, attrs, defStyle);
		clipPath = new Path();
	}

	@Override
	protected void onDraw(Canvas canvas) {
		
		int w = this.getWidth();
		int h = this.getHeight();
		clipPath.addRoundRect(new RectF(0, 0, w, h), rx, ry,
				Path.Direction.CW);
		
		canvas.clipPath(clipPath);
		super.onDraw(canvas);
	}
	
	public void setAngie(float rx,float ry) {
		this.rx=rx;
		this.ry=ry;
		postInvalidate();
	}
}