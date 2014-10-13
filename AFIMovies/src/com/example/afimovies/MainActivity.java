package com.example.afimovies;

import java.util.Random;

import android.app.Activity;
import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {

	// textview to display chosen movie title
	private TextView filmOne;

	// String variable to store chosen movie title
	private String movieTitle;

	// String array to store selected movie titles
	String filmSelection[] = new String[5];

	// integer variable to store the array element
	// position of chosen movie
	int filmChosen = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// assign the textview variable to the UI object
		filmOne = (TextView) findViewById(R.id.filmOne);

		// initialize the array elements with some movie titles
		filmSelection[0] = "Citizen Kane";
		filmSelection[1] = "The Graduate";
		filmSelection[2] = "Star Wars";
		filmSelection[3] = "Butch Cassidy and the Sundance Kid";
		filmSelection[4] = "American Graffiti";

		// create a button variable and assign to the UI object
		Button button = (Button) findViewById(R.id.button1);
		// assign a button listener to an event handler
		button.setOnClickListener(new ButtonHandler());

	}

	class ButtonHandler implements OnClickListener {

		// this method handles the event
		public void onClick(View view) {

			// get a random film number
			filmChosen = getRandomFilmNumber(5);

			// use the number to locate the associated movie
			movieTitle = filmSelection[filmChosen];

			// set the text of the viewText to the movie chosen
			filmOne.setText(filmSelection[filmChosen]);

		} // end onClick

	} // end ButtonHandler

	// this method is called when the user selects or taps
	// the chosen movie title in the UI.

	public void onClickmyTextView(View v) {

		// calls the searchWeb method with a search
		// item (IMDB) + the movie title chosen
		searchWeb("IMDB " + movieTitle);

	} // end onClickmyViewText

	public void searchWeb(String query) {
		// sets up an intent associated with a search query
		// look up the associated API info for more information!
		Intent intent = new Intent(Intent.ACTION_SEARCH);
		intent.putExtra(SearchManager.QUERY, query);

		if (intent.resolveActivity(getPackageManager()) != null) {
			startActivity(intent);
		}
	} // end searchWeb

	// this method returns a random integer from
	// 1 to the number given as a parameter when the
	// method is called

	private int getRandomFilmNumber(int maxNumber) {

		// instantiate a new instance of the Java Random class

		Random randomNumber = new Random();

		// initialize the random number variable
		int randomFilmNumber = 0;

		// generate and assign a random number
		randomFilmNumber = randomNumber.nextInt(maxNumber);

		// return the random value back to the calling method
		return randomFilmNumber;

	} // end getRandomFilmNumber

}
