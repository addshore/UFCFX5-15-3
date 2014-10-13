package com.example.kittykatapp;

import android.app.Activity;
import android.graphics.Matrix;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;

public class ImageActivity extends Activity {

	ImageView image;
	int view_width = 0; // imageview width
	int view_height = 0; // imageview height
	float GScale = 1; // zoom factor

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_image);

		image = (ImageView) findViewById(R.id.imageView1);
		Button ZoomIn = (Button) findViewById(R.id.button1);
		Button ZoomOut = (Button) findViewById(R.id.button2);
		ZoomIn.setOnClickListener(new ButtonInHandler());
		ZoomOut.setOnClickListener(new ButtonOutHandler());

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
				image.invalidate();
			}
		}
	}

	class ButtonOutHandler implements OnClickListener {
		public void onClick(View view) {
			image.setScaleType(ScaleType.FIT_CENTER);
			GScale = 1;
			image.invalidate();
		}
	}

}
