package com.example.afimovies;

import java.util.Random;

import android.app.Activity;
import android.app.SearchManager;
import android.content.Intent;
import android.graphics.Typeface;
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
	private TextView filmTwo;
	private TextView filmThree;
	private TextView filmFour;
	private TextView filmFive;

	// String array to store selected movie titles
	String filmList[] = new String[101];

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		TextView title = (TextView) findViewById(R.id.textView1);
		title.setTypeface(null, Typeface.BOLD);

		// assign the textview variable to the UI object
		filmOne = (TextView) findViewById(R.id.filmOne);
		filmTwo = (TextView) findViewById(R.id.filmTwo);
		filmThree = (TextView) findViewById(R.id.filmThree);
		filmFour = (TextView) findViewById(R.id.filmFour);
		filmFive = (TextView) findViewById(R.id.filmFive);

		// initialize the array elements with some movie titles
		filmList[1]= "Citizen Kane";
		filmList[2]= "Casablanca";
		filmList[3]= "The Godfather";
		filmList[4]= "Gone with the Wind";
		filmList[5]= "Lawrence of Arabia";
		filmList[6]= "The Wizard of Oz";
		filmList[7]= "The Graduate";
		filmList[8]= "On the Waterfront";
		filmList[9]= "Schindler's List";
		filmList[10]= "Singin' in the Rain";
		filmList[11]= "It's a Wonderful Life";
		filmList[12]= "Sunset Boulevard";
		filmList[13]= "The Bridge on the River Kwai";
		filmList[14]= "Some Like It Hot";
		filmList[15]= "Star Wars";
		filmList[16]= "All About Eve";
		filmList[17]= "The African Queen";
		filmList[18]= "Psycho";
		filmList[19]= "Chinatown";
		filmList[20]= "One Flew Over the Cuckoo's Nest";
		filmList[21]= "The Grapes of Wrath";
		filmList[22]= "2001: A Space Odyssey";
		filmList[23]= "The Maltese Falcon";
		filmList[24]= "Raging Bull";
		filmList[25]= "E.T. the Extra-Terrestrial";
		filmList[26]= "Dr. Strangelove";
		filmList[27]= "Bonnie and Clyde";
		filmList[28]= "Apocalypse Now";
		filmList[29]= "Mr. Smith Goes to Washington";
		filmList[30]= "The Treasure of the Sierra Madre";
		filmList[31]= "Annie Hall";
		filmList[32]= "The Godfather Part II";
		filmList[33]= "High Noon";
		filmList[34]= "To Kill a Mockingbird";
		filmList[35]= "It Happened One Night";
		filmList[36]= "Midnight Cowboy";
		filmList[37]= "The Best Years of Our Lives";
		filmList[38]= "Double Indemnity";
		filmList[39]= "Doctor Zhivago";
		filmList[40]= "North by Northwest";
		filmList[41]= "West Side Story";
		filmList[42]= "Rear Window";
		filmList[43]= "King Kong";
		filmList[44]= "The Birth of a Nation";
		filmList[45]= "A Streetcar Named Desire";
		filmList[46]= "A Clockwork Orange";
		filmList[47]= "Taxi Driver";
		filmList[48]= "Jaws";
		filmList[49]= "Snow White and the Seven Dwarfs";
		filmList[50]= "Butch Cassidy and the Sundance Kid";
		filmList[51]= "The Philadelphia Story";
		filmList[52]= "From Here to Eternity";
		filmList[53]= "Amadeus";
		filmList[54]= "All Quiet on the Western Front";
		filmList[55]= "The Sound of Music";
		filmList[56]= "MASH";
		filmList[57]= "The Third Man";
		filmList[58]= "Fantasia";
		filmList[59]= "Rebel Without a Cause";
		filmList[60]= "Raiders of the Lost Ark";
		filmList[61]= "Vertigo";
		filmList[62]= "Tootsie";
		filmList[63]= "Stagecoach";
		filmList[64]= "Close Encounters of the Third Kind";
		filmList[65]= "The Silence of the Lambs";
		filmList[66]= "Network";
		filmList[67]= "The Manchurian Candidate";
		filmList[68]= "An American in Paris";
		filmList[69]= "Shane";
		filmList[70]= "The French Connection";
		filmList[71]= "Forrest Gump";
		filmList[72]= "Ben-Hur";
		filmList[73]= "Wuthering Heights";
		filmList[74]= "The Gold Rush";
		filmList[75]= "Dances with Wolves";
		filmList[76]= "City Lights";
		filmList[77]= "American Graffiti";
		filmList[78]= "Rocky";
		filmList[79]= "The Deer Hunter";
		filmList[80]= "The Wild Bunch";
		filmList[81]= "Modern Times";
		filmList[82]= "Giant";
		filmList[83]= "Platoon";
		filmList[84]= "Fargo";
		filmList[85]= "Duck Soup";
		filmList[86]= "Mutiny on the Bounty";
		filmList[87]= "Frankenstein";
		filmList[88]= "Easy Rider";
		filmList[89]= "Patton";
		filmList[90]= "The Jazz Singer";
		filmList[91]= "My Fair Lady";
		filmList[92]= "A Place in the Sun";
		filmList[93]= "The Apartment";
		filmList[94]= "Goodfellas";
		filmList[95]= "Pulp Fiction";
		filmList[96]= "The Searchers";
		filmList[97]= "Bringing Up Baby";
		filmList[98]= "Unforgiven";
		filmList[99]= "Guess Who's Coming to Dinner";
		filmList[100]= "Yankee Doodle Dandy";

		// create a button variable and assign to the UI object
		Button button = (Button) findViewById(R.id.button1);
		// assign a button listener to an event handler
		button.setOnClickListener(new ButtonHandler());

	}

	class ButtonHandler implements OnClickListener {

		// this method handles the event
		public void onClick(View view) {

			Random rand = new Random();
			int randValue = 0;

			// set the text of the viewText to the movie chosen
			//TODO not select duplicates
			randValue = rand.nextInt(100)+1; 
			filmOne.setText(randValue + " - " + filmList[randValue]);
			randValue = rand.nextInt(100)+1; 
			filmTwo.setText(randValue + " - " + filmList[randValue]);
			randValue = rand.nextInt(100)+1; 
			filmThree.setText(randValue + " - " + filmList[randValue]);
			randValue = rand.nextInt(100)+1; 
			filmFour.setText(randValue + " - " + filmList[randValue]);
			randValue = rand.nextInt(100)+1; 
			filmFive.setText(randValue + " - " + filmList[randValue]);

		} // end onClick

	} // end ButtonHandler

	// this method is called when the user selects or taps
	// the chosen movie title in the UI.

	public void onClickmyTextView(View v) {

		// calls the searchWeb method with a search
		// item (IMDB) + the movie title chosen
		String text = null;
		if(v instanceof TextView){
			TextView t = (TextView) v;
			text = t.getText().toString();
		}
		//TODO else die as something has gone wrong?
		searchWeb("YouTube " + text);

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

}
