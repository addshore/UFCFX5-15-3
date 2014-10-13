package com.example.kittykatapp;

import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity {

	private int count = 0;
	private EditText text;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		text = (EditText) findViewById(R.id.editText1);
		Button button = (Button) findViewById(R.id.button1);
		ImageButton image = (ImageButton) findViewById(R.id.imageButton1);

		button.setOnClickListener(new ButtonHandler());

		GestureHandler handler = new GestureHandler();
		final GestureDetectorCompat detector = new GestureDetectorCompat(this,
				handler);
		detector.setOnDoubleTapListener(handler);
		image.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent me) {
				return detector.onTouchEvent(me);
			}
		});

	}

	class ButtonHandler implements OnClickListener {
		public void onClick(View view) {
			count++;
			text.setText(count + " Meeow");
		}
	}

	class GestureHandler extends SimpleOnGestureListener {
		public boolean onFling(MotionEvent event1, MotionEvent event2,
				float velocityX, float velocityY) {
			Toast.makeText(MainActivity.this, "Purrr", Toast.LENGTH_SHORT)
					.show();
			return true;
		}

		public boolean onDoubleTap(MotionEvent e) {
			Toast.makeText(MainActivity.this, "Ouch!", Toast.LENGTH_SHORT)
					.show();
			return true;
		}
	}

}
