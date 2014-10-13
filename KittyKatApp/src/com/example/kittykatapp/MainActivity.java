package com.example.kittykatapp;

import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity {

	private int count = 0;
	private EditText text;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		text = (EditText) findViewById(R.id.editText1);
		Button buttonClickMe = (Button) findViewById(R.id.button1);
		Button buttonImageMe = (Button) findViewById(R.id.button2);
		ImageButton image = (ImageButton) findViewById(R.id.imageButton1);
		TextView link = (TextView) findViewById(R.id.textView2);

		buttonClickMe.setOnClickListener(new ButtonHandler());
		buttonImageMe.setOnClickListener(new ButtonImgHandler());
		link.setOnClickListener( new LinkHandler() );

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

	class ButtonImgHandler implements OnClickListener {
		public void onClick(View view) {
			Intent intent;
    		intent = new Intent(MainActivity.this, ImageActivity.class);
    		startActivity(intent);
		}
	}

	class LinkHandler implements OnClickListener{
		public void onClick(View v) {
			Intent intent = new Intent(Intent.ACTION_VIEW);
			intent.setData(Uri.parse("http://en.wikipedia.org/wiki/Cat"));
			startActivity(intent);  
		}
	}

	class GestureHandler extends SimpleOnGestureListener {
		public boolean onFling(MotionEvent event1, MotionEvent event2,
				float velocityX, float velocityY) {
			Toast.makeText(MainActivity.this, "Purrr", Toast.LENGTH_SHORT)
					.show();
			MediaPlayer mPlayer;
			mPlayer = MediaPlayer.create(MainActivity.this, R.raw.purr);
			mPlayer.start();
			return true;
		}

		public boolean onDoubleTap(MotionEvent e) {
			Toast.makeText(MainActivity.this, "Ouch!", Toast.LENGTH_SHORT)
					.show();
			return true;
		}
	}

}
