package fr.brouillard.solar;

import javax.inject.Singleton;

@Singleton
public class Sun {
	@Override
	public String toString() {
		return "the sun: " + super.toString();
	}
}
