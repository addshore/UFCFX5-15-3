using UnityEngine;
using System.Collections;

public class CarControlScript : MonoBehaviour {
	public WheelCollider WheelFL;
	public WheelCollider WheelFR;
	public WheelCollider WheelRL;
	public WheelCollider WheelRR;

	public Transform WheelFLTrans;
	public Transform WheelFRTrans;
	public Transform WheelRLTrans;
	public Transform WheelRRTrans;

	public GameObject TruckBody;
	public Texture TruckDefaultTexture;
	public Texture TruckBrakeTexture;
	public Texture TruckReverseTexture;

	public GameObject camera;
	
	// Movement settings
	private float motorMax = 80f;
	private float brakeMax = 40f;
	private float maxSpeedForward = 60f;
	private float maxSpeedReverse = 25f;
	
	// Steer settings
	private float steerMax = 15f;
	private float steerIncrements = 1f;
	private float lastSteerAngle = 0;

	//Buttons
	private GameObject drivePlane;
	private GameObject brakePlane;

	// Use this for initialization
	void Start () {
		rigidbody.centerOfMass = new Vector3(0,0,0);
		audio.pitch = 0.25f;
		#if UNITY_IOS || UNITY_ANDROID
		//Drive button
		drivePlane = (GameObject)Instantiate(GameObject.CreatePrimitive(PrimitiveType.Plane));
		drivePlane.transform.parent = camera.transform;
		drivePlane.transform.localPosition = new Vector3(-1.75f,-0.70f,2f);
		drivePlane.transform.localRotation = Quaternion.identity;
		drivePlane.transform.Rotate( new Vector3(270f,0f,0f) );
		drivePlane.transform.localScale = new Vector3(0.05f,1f,0.025f);
		drivePlane.name = "Drive";
		//Brake button
		brakePlane = (GameObject)Instantiate(GameObject.CreatePrimitive(PrimitiveType.Plane));
		brakePlane.transform.parent = camera.transform;
		brakePlane.transform.localPosition = new Vector3(-1.75f,-1f,2f);
		brakePlane.transform.localRotation = Quaternion.identity;
		brakePlane.transform.Rotate( new Vector3(270f,0f,0f) );
		brakePlane.transform.localScale = new Vector3(0.05f,1f,0.025f);
		brakePlane.name = "Brake";
		#endif
	}

	void Update () {
		#if UNITY_IOS || UNITY_ANDROID
		//TODO mobile
		float vertical = 0f;
		foreach(Touch touch in Input.touches){
			Ray theRay = Camera.main.ScreenPointToRay(touch.position);
			RaycastHit hit ;
			if (Physics.Raycast (theRay, out hit)) {
				if(hit.transform.name == "Drive"){
					vertical = 1f;
				}else if(hit.transform.name == "Brake"){
					vertical = -1f;
				}
			}
		}
		float horizontal = Input.acceleration.x;
		if( horizontal < 0.05f && horizontal > -0.05f ) {
			horizontal = 0f;
		}
		#else
		float vertical = Mathf.Clamp(Input.GetAxis("Vertical"), -1, 1);
		float horizontal = Mathf.Clamp(Input.GetAxis("Horizontal"), -1, 1);
		#endif

		float forwardVelocity = transform.InverseTransformDirection(rigidbody.velocity).z;
		
		// Accelerate up to a max speed
		if( forwardVelocity > maxSpeedForward || forwardVelocity < -maxSpeedReverse ) {
			setMotorTorque( 0 );
		} else {
			setMotorTorque( vertical * motorMax );
		}
		
		// Brake depending on current direction vs user input
		if( forwardVelocity > 0 && vertical < 0 ) {
			//Forward but braking
			setBrakeTorque( -vertical * brakeMax );
			TruckBody.renderer.material.mainTexture = TruckBrakeTexture;
		} else if( forwardVelocity < 0 & vertical > 0 ) {
			//Backward but braking
			setBrakeTorque( vertical * brakeMax );
			TruckBody.renderer.material.mainTexture = TruckBrakeTexture;
		} else {
			setBrakeTorque( 0 );
			// If we are not breaking then determine the correct texture for lights
			if( vertical > 0 ) {
				//Forward
				TruckBody.renderer.material.mainTexture = TruckDefaultTexture;
			} else if( vertical < 0 ) {
				//Backward
				TruckBody.renderer.material.mainTexture = TruckReverseTexture;
			}
		}

		//Set the Audio Pitch
		if( forwardVelocity > 0 ) {
			audio.pitch = 0.25f + ( forwardVelocity / maxSpeedForward );
		} else {
			audio.pitch = 0.25f + ( -forwardVelocity / maxSpeedForward );
		}

		
		// Set the steer angle
		float steer = horizontal * steerMax;
		if( steer < lastSteerAngle ) {
			setSteerAngle( lastSteerAngle - steerIncrements );
		} else if( steer > lastSteerAngle ) {
			setSteerAngle( lastSteerAngle + steerIncrements );
		}
	}

	void setMotorTorque( float torque ) {
		WheelRL.motorTorque = torque;
		WheelRR.motorTorque = torque;
	}
	
	void setBrakeTorque( float torque ) {
		WheelRL.brakeTorque = torque;
		WheelRR.brakeTorque = torque;
	}
	
	void setSteerAngle( float angle ) {
		lastSteerAngle = angle;
		WheelFL.steerAngle = angle;
		WheelFR.steerAngle = angle;
	}
}
