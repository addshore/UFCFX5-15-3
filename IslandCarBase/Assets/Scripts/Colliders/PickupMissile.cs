using UnityEngine;
using System.Collections;

public class PickupMissile : MonoBehaviour {

	public MissileLauncher launcherScript;
	
	// Update is called once per frame
	void OnTriggerStay (Collider other) {
		launcherScript.missileNeeded = true;
	}
}
