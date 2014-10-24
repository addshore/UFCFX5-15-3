using UnityEngine;
using System.Collections;

public class Spin : MonoBehaviour {

	string fingerCount;
	int fingersOnScreen;

	void Start () {
		guiText.pixelOffset = new Vector2( Screen.width/2, Screen.height/15 );
	}

	// Update is called once per frame
	void Update () {

		fingersOnScreen = 0;

		foreach (Touch touch in Input.touches) {

			if (touch.phase != TouchPhase.Ended && touch.phase != TouchPhase.Canceled){

				fingersOnScreen ++;

			}
		}

		if (fingersOnScreen > 0){

			guiText.text = "The User has " + fingerCount + " finger(s) touching the screen";

			if (fingersOnScreen == 2){

				guiText.text = "The User has " + fingerCount + " finger(s) touching the screen";
				transform.Rotate(0, 2f, 0);
			}
			if (fingersOnScreen == 3){

				guiText.text = "The User has " + fingerCount + " finger(s) touching the screen";
				transform.Rotate(0, 3f, 0);
			}

			if (fingersOnScreen == 4){

				guiText.text = "The User has " + fingerCount + " finger(s) touching the screen";
				transform.Rotate(0, 4f, 0);
			}
		}

		else{

			guiText.text = "Touch me to go faster!";
		}

		transform.Rotate(0, 1f, 0);

		fingerCount = fingersOnScreen.ToString();
	}

}
