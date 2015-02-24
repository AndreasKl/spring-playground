package net.andreaskluth.sample;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;

import org.springframework.hateoas.ExposesResourceFor;
import org.springframework.hateoas.PagedResources;
import org.springframework.hateoas.PagedResources.PageMetadata;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@ExposesResourceFor(PetResource.class)
public class PetController {

	private static final PetResourceAssembler ASSEMBLER = new PetResourceAssembler();
	private static final List<Pet> PETS = new ArrayList<>();
	
	static {
		PETS.add(new Pet("Leo", "Lion"));
		PETS.add(new Pet("Kitty", "Cat"));
	}

	@RequestMapping(value = "/pets/", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
	public HttpEntity<PagedResources<PetResource>> pets() {
		final PagedResources<PetResource> resources = new PagedResources<>(ASSEMBLER.toResources(PETS),
				new PageMetadata(2, 1, 100));
		resources.add(linkTo(methodOn(PetController.class).pets()).withSelfRel());
		PETS.forEach(p -> resources.add(linkTo(methodOn(PetController.class).showPet(p.getName())).withRel("item")));
		return new ResponseEntity<>(resources, HttpStatus.OK);
	}

	@RequestMapping(value = "/pets/{name}", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
	public HttpEntity<Resource<PetResource>> showPet(@PathVariable String name) {
		Resource<PetResource> pet = new Resource<PetResource>(ASSEMBLER.toResource(new Pet("Leo", "Lion")));

		return new ResponseEntity<Resource<PetResource>>(pet, HttpStatus.OK);
	}

	@Component
	static class PetResourceAssembler extends ResourceAssemblerSupport<Pet, PetResource> {

		public PetResourceAssembler() {
			super(PetController.class, PetResource.class);
		}

		@Override
		public PetResource toResource(Pet pet) {
			PetResource resource = new PetResource(pet.getName(), pet.getKind());
			resource.add(linkTo(methodOn(PetController.class).showPet(pet.getName())).withSelfRel());
			return resource;
		}
	}
}
