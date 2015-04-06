using UnityEngine;
using System.Collections;

public class MissileTrajectory : MonoBehaviour {
	
	public GameObject explosion;
	Object thisExplosion;

	public void OnCollisionEnter(Collision collision) {
	  
		ContactPoint contact = collision.contacts[0];
		thisExplosion = Instantiate(explosion, contact.point + (contact.normal * 5.0f) , Quaternion.identity);
		
		if (collision.gameObject.tag == "Target")
		{
			Destroy (collision.gameObject);
		}
		
		Destroy (thisExplosion, 2.0f);
		Destroy (gameObject);
	}

}
