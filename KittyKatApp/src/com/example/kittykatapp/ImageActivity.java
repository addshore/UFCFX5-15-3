package com.example.kittykatapp;

import android.app.Activity;
import android.graphics.Matrix;
import android.graphics.PointF;
import android.os.Bundle;
import android.util.FloatMath;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;

public class ImageActivity extends Activity implements OnTouchListener {

	ImageView image;
	int view_width = 0; // imageview width
	int view_height = 0; // imageview height
	float GScale = 1; // zoom factor

	float TX = 0; // translation X value
	float TY = 0; // translation Y value
	float tTX = 0; // translation X temporary value
	float tTY = 0; // translation Y temporary value
	Matrix savedMatrix = new Matrix(); // saved current matrix
	boolean PanMode = false; // Pan mode ON/OFF
	boolean MultiTouch = false; // Multi Touch mode ON/OFF
	float dist0, distCurrent, GScale0, TX0, TY0; // temporary variables for
													// Pinch zoom
	PointF start = new PointF(); // start position of first touch

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_image);

		image = (ImageView) findViewById(R.id.imageView1);
		Button ZoomIn = (Button) findViewById(R.id.button1);
		Button ZoomOut = (Button) findViewById(R.id.button2);
		ZoomIn.setOnClickListener(new ButtonInHandler());
		ZoomOut.setOnClickListener(new ButtonOutHandler());

		image.setOnTouchListener(new View.OnTouchListener() {
			float distx, disty, ttscale;

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				if (event.getAction() == MotionEvent.ACTION_DOWN) {
					start.set(event.getX(), event.getY());
					if (PanMode) {
						Matrix mat = image.getImageMatrix();
						savedMatrix.set(mat);
						image.setScaleType(ScaleType.MATRIX);
						tTX = TX;
						tTY = TY;
					}
				} else if ((event.getAction() & MotionEvent.ACTION_MASK) == MotionEvent.ACTION_POINTER_DOWN) {
					int numpt = event.getPointerCount();
					if (numpt > 1) {
						MultiTouch = true;
						PanMode = false;
						// Get the distance when the second pointer touch
						distx = event.getX(0) - event.getX(1);
						disty = event.getY(0) - event.getY(1);
						dist0 = FloatMath.sqrt(distx * distx + disty * disty);
						GScale0 = GScale;
						TX0 = TX;
						TY0 = TY;

						Matrix mat = image.getImageMatrix();
						savedMatrix.set(mat);
						image.setScaleType(ScaleType.MATRIX);
					}
				} else if ((event.getAction() & MotionEvent.ACTION_MASK) == MotionEvent.ACTION_POINTER_UP) {
					int numpt = event.getPointerCount();
					if (numpt > 1 && numpt < 3) {
						MultiTouch = false;
						Matrix mat = new Matrix();
						mat.set(savedMatrix);
						distx = event.getX(0) - event.getX(1);
						disty = event.getY(0) - event.getY(1);
						distCurrent = FloatMath.sqrt(distx * distx + disty
								* disty);
						ttscale = distCurrent / dist0;
						GScale = GScale0 * ttscale;

						if (GScale < 1.0) {
							image.setScaleType(ScaleType.FIT_CENTER);
							GScale = 1;
							TX = TY = 0;
							PanMode = false;

							mat = image.getImageMatrix();
							savedMatrix.set(mat);
							tTX = TX;
							tTY = TY;
							TX0 = TX;
							TY0 = TY;
							GScale0 = GScale;

						} else {
							mat.postScale(ttscale, ttscale, view_width / 2,
									view_height / 2);
							image.setScaleType(ScaleType.MATRIX);
							image.setImageMatrix(mat);
							TX = TX0 * ttscale;
							TY = TY0 * ttscale;
							PanMode = true;

							mat = image.getImageMatrix();
							savedMatrix.set(mat);
							image.setScaleType(ScaleType.MATRIX);
							tTX = TX;
							tTY = TY;

						}

						int p = event.getActionIndex();
						if (p == 0)
							start.set(event.getX(1), event.getY(1));
						if (p == 1)
							start.set(event.getX(0), event.getY(0));
						image.invalidate();
					} // if(numpt>1&&numpt<3)
				} else if (event.getAction() == MotionEvent.ACTION_MOVE) {
					int numpt = event.getPointerCount();
					if (MultiTouch && numpt > 1) // Pinch zoom
					{
						distx = event.getX(0) - event.getX(1);
						disty = event.getY(0) - event.getY(1);
						distCurrent = FloatMath.sqrt(distx * distx + disty
								* disty);
						ttscale = distCurrent / dist0;
						GScale = GScale0 * ttscale;
						if (GScale < 1.0) {
							image.setScaleType(ScaleType.CENTER_INSIDE);
							GScale = 1;
							TX = TY = 0;
							PanMode = false;

							Matrix mat = new Matrix();
							mat = image.getImageMatrix();
							savedMatrix.set(mat);
							tTX = TX;
							tTY = TY;
							TX0 = TX;
							TY0 = TY;
							GScale0 = GScale;
						} else {
							view_width = image.getWidth();
							view_height = image.getHeight();
							Matrix mat = new Matrix();
							mat.set(savedMatrix);
							mat.postScale(ttscale, ttscale, view_width / 2,
									view_height / 2);
							image.setScaleType(ScaleType.MATRIX);
							image.setImageMatrix(mat);

							TX = TX0 * ttscale;
							TY = TY0 * ttscale;
						}
						image.invalidate();
					} else {
						if (PanMode) {
							float TranX = event.getX() - start.x;
							float TranY = event.getY() - start.y;
							Matrix mat = new Matrix();
							mat.set(savedMatrix);
							mat.postTranslate(TranX, TranY);
							image.setImageMatrix(mat);
							TX = tTX + TranX;
							TY = tTY + TranY;
							image.invalidate();
						}
					}// else
				}

				return true;
			}
		});

	}

	class ButtonInHandler implements OnClickListener {
		public void onClick(View view) {
			if (GScale < 5.0) {
				view_width = image.getWidth();
				view_height = image.getHeight();
				Matrix mat = image.getImageMatrix();
				mat.postScale(1.5f, 1.5f, view_width / 2, view_height / 2);
				image.setScaleType(ScaleType.MATRIX);
				image.setImageMatrix(mat);
				GScale = GScale * 1.5f;
				TX = TX * 1.5f;
				TY = TY * 1.5f;
				PanMode = true;
				image.invalidate();
			}
		}
	}

	class ButtonOutHandler implements OnClickListener {
		public void onClick(View view) {
			image.setScaleType(ScaleType.FIT_CENTER);
			GScale = 1;
			TX = TY = 0;
			PanMode = false;
			image.invalidate();

		}
	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		// TODO Auto-generated method stub
		return false;
	}

}
