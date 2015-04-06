using UnityEngine;
using System.Collections;

public class GameOverCollision : MonoBehaviour {
	void OnCollisionEnter (Collision col) {
		Application.LoadLevel("Failed");
	}
}
