package net.andreaskluth.sample;

public class Pet {

	private final String name;

	private final String kind;

	public Pet(String name, String kind) {
		this.name = name;
		this.kind = kind;
	}

	public String getName() {
		return name;
	}

	public String getKind() {
		return kind;
	}

}
