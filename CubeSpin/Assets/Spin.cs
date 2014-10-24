using UnityEngine;
using System.Collections;

public class Spin : MonoBehaviour {

	new GUIText guiText;

	void Start () {
		guiText = GameObject.Find("GUI Text").GetComponent<GUIText>();
	}

	// Update is called once per frame
	void Update () {

		int fingersOnScreen = 0;

		foreach (Touch touch in Input.touches) {
			if (touch.phase != TouchPhase.Ended && touch.phase != TouchPhase.Canceled){

				fingersOnScreen ++;

				if( touch.position.x < Screen.width/2 ) {
					//Move left
					transform.position += Vector3.left * Time.deltaTime;
				} else {
					//Move right
					transform.position += Vector3.right * Time.deltaTime;
				}
				if( touch.position.y < Screen.height/2 ) {
					//Move down
					transform.position += Vector3.down * Time.deltaTime;
				} else {
					//Move up
					transform.position += Vector3.up * Time.deltaTime;
				}

			}
		}

		if (fingersOnScreen > 0){
			guiText.text = "The User has " + fingersOnScreen.ToString() + " finger(s) touching the screen";
			transform.Rotate(0, (float)fingersOnScreen, 0);
		}else{
			guiText.text = "Touch me to go faster!";
			transform.Rotate(0, 1f, 0);
		}


	}

}
