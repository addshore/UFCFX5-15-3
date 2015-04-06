using UnityEngine;
using System.Collections;

public class MissileLauncher : MonoBehaviour {
	
	public GameObject missilePrefab;
	private GameObject missile;
	public GameObject car;
	public GameObject camera;
	
	public bool missileNeeded = false;
	private Vector3 missileOffset = new Vector3(0f, 325f, 10f);

	//Buttons
	private GameObject firePlane;

	void Start() {
		#if UNITY_IOS || UNITY_ANDROID
		//Fire button
		firePlane = (GameObject)Instantiate(GameObject.CreatePrimitive(PrimitiveType.Plane));
		firePlane.transform.parent = camera.transform;
		firePlane.transform.localPosition = new Vector3(1.75f,-1f,2f);
		firePlane.transform.localRotation = Quaternion.identity;
		firePlane.transform.Rotate( new Vector3(270f,0f,0f) );
		firePlane.transform.localScale = new Vector3(0.05f,1f,0.025f);
		firePlane.name = "Fire";
		DisableFirePlane();
		#endif
	}

	void EnableFirePlane() {
		firePlane.collider.enabled = true;
		firePlane.renderer.enabled = true;
	}
	
	void DisableFirePlane() {
		firePlane.collider.enabled = false;
		firePlane.renderer.enabled = false;
	}
	
	// Update is called once per frame
	void Update () {
		
		//Add a missile to the car if not already there
		if (missileNeeded){
			missileNeeded = false;
			if( !missile ) {
				missile = Instantiate (missilePrefab, transform.TransformPoint (missileOffset), transform.rotation) as GameObject;
				//Physics.IgnoreCollision( missile.collider, car.transform.Find("TruckBody").collider );
				missile.transform.parent = car.transform;
				missile.transform.localScale = new Vector3(2.5f,2.5f,2.5f) * 2500f;
				missile.GetComponent<TrailRenderer>().enabled=false;
				missile.collider.enabled=false;
				EnableFirePlane();
			}
		}
		
		//If the car has a missile update the position
		if(missile && missile.transform.parent == car.transform) {
			missile.transform.position = transform.TransformPoint (missileOffset);
			missile.transform.rotation = car.transform.rotation;
		}
		
		//Fire the missile (and disassociate from the car)
		if( missile && missile.transform.parent == car.transform ) {
			#if UNITY_IOS || UNITY_ANDROID
			//TODO mobile below
			bool fireTouched = false;
			foreach(Touch touch in Input.touches){
				if (touch.phase == TouchPhase.Began ) {
					Ray theRay = Camera.main.ScreenPointToRay(touch.position);
					RaycastHit hit ;
					if (Physics.Raycast (theRay, out hit)) {
						if(hit.transform.name == "Fire"){
							fireTouched = true;
						}
					}
				}
			}
			if( fireTouched )
			#else
			if( Input.GetKeyDown("space") )
			#endif
			{
				missile.transform.parent = null;
				missile.rigidbody.AddForce (missile.transform.TransformDirection (Vector3.forward) * 3000.0f);
				missile.GetComponent<TrailRenderer>().enabled=true;
				missile.collider.enabled=true;
				Physics.IgnoreCollision( missile.collider, car.transform.Find("TruckBody").collider );
				#if UNITY_IOS || UNITY_ANDROID
				DisableFirePlane();
				#endif
			}
		}
		
	}
}
