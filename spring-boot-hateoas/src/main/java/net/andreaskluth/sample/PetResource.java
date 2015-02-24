package net.andreaskluth.sample;

import org.springframework.hateoas.ResourceSupport;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class PetResource extends ResourceSupport {

	private final String name;

	private final String kind;

	@JsonCreator
	public PetResource(@JsonProperty("name") String name, @JsonProperty("kind") String kind) {
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