using UnityEngine;
using System.Collections;

public class CubeBehaviour : MonoBehaviour {
	
	private GameObject cubeObject;
	new GUIText guiText;
	new Camera camera;
	
	void Start () {
		//Get objects
		cubeObject = GameObject.Find("Cube");
		guiText = GameObject.Find("GUI Text").GetComponent<GUIText>();
		camera = GameObject.Find("Main Camera").GetComponent<Camera>();

		//Set detauls
		Defaults();
	}

	void Defaults () {
		cubeObject.renderer.material.color = Color.red;
		cubeObject.transform.position = new Vector3(0,0,0);
		cubeObject.transform.rotation = new Quaternion(0,0,0,0);
		camera.fieldOfView = 60;
	}
	
	void Update () {
		if( Input.touchCount == 0 ) {
			cubeObject.transform.Rotate( 0, 0f, 0 );
			cubeObject.transform.rotation = new Quaternion(0,0,0,0);
		} else if (Input.touchCount == 1) {
			Touch touchZero = Input.GetTouch(0);

			if (touchZero.phase == TouchPhase.Began) {
				Ray ray = Camera.main.ScreenPointToRay(touchZero.position);
				RaycastHit hit ;
				if (Physics.Raycast (ray, out hit)) {
					if( touchZero.tapCount == 1 ) {
						cubeObject.renderer.material.color = new Color( Random.value, Random.value, Random.value, 1.0f );
						guiText.text = "Last action was: Color change";
					}
				}
				if( touchZero.tapCount == 2 ) {
					Defaults();
					guiText.text = "Last action was: Reset";
				}
			}

			if (touchZero.phase == TouchPhase.Moved) {
				cubeObject.transform.Translate( touchZero.deltaPosition * Time.deltaTime);
				guiText.text = "Last action was: Cube move";
			}

		} else if (Input.touchCount == 2) {
			Touch touchZero = Input.GetTouch(0);
			Touch touchOne = Input.GetTouch(1);
			
			// Find the position in the previous frame of each touch.
			Vector2 touchZeroPrevPos = touchZero.position - touchZero.deltaPosition;
			Vector2 touchOnePrevPos = touchOne.position - touchOne.deltaPosition;
			
			// Find the magnitude of the vector (the distance) between the touches in each frame.
			float prevTouchDeltaMag = (touchZeroPrevPos - touchOnePrevPos).magnitude;
			float touchDeltaMag = (touchZero.position - touchOne.position).magnitude;
			
			// Find the difference in the distances between each frame.
			float deltaMagnitudeDiff = prevTouchDeltaMag - touchDeltaMag;

			// Otherwise change the field of view based on the change in distance between the touches.
			camera.fieldOfView += deltaMagnitudeDiff * 0.5f;
			// Clamp the field of view to make sure it's between 0 and 180.
			camera.fieldOfView = Mathf.Clamp(camera.fieldOfView, 0.1f, 179.9f);
			guiText.text = "Last action was: Camera zoom";
		} else if (Input.touchCount >= 3) {
			Touch touchZero = Input.GetTouch(0);
			float rotationValue = 1f * ( Input.touchCount - 2 );
			guiText.text = "Last action was: Rotate";
			int seconds = System.DateTime.Now.Second;
			int timeSplit = 60 / 8; // There are 8 rotation angles..

			//Rotate in different directions depending on time..
			if( seconds < timeSplit * 1 || seconds < timeSplit * 5 ) {
				cubeObject.transform.Rotate( rotationValue, rotationValue, 0 );
			} else if( seconds < timeSplit * 2 || seconds < timeSplit * 6 ) {
				cubeObject.transform.Rotate( -rotationValue, rotationValue, 0 );
			} else if( seconds < timeSplit * 3 || seconds < timeSplit * 7 ) {
				cubeObject.transform.Rotate( -rotationValue, -rotationValue, 0 );
			} else if( seconds < timeSplit * 4 || seconds < timeSplit * 8 ) {
				cubeObject.transform.Rotate( rotationValue, -rotationValue, 0 );
			}
		}
	}
}
