using UnityEngine;
using System.Collections;

public class GameCompleteCollision : MonoBehaviour {
	void OnCollisionEnter (Collision col) {
		Application.LoadLevel("Completed");
	}
}
