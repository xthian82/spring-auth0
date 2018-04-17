package com.auth0.samples.authapi.contacts;

import com.google.common.collect.ImmutableList;
import java.util.List;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/contacts", produces = MediaType.APPLICATION_JSON_VALUE)
public class ContactController  {

	private static final List<Contact> contacts = ImmutableList.of(
			new Contact().withName("Jerry Recalde").withPhone("+595971426500"),
			new Contact().withName("Jane Doe").withPhone("+5551888989")
	);

	@GetMapping
	@PreAuthorize("hasAuthority('read:contacts')")
	public List<Contact> getContacts() {
		return contacts;
	}

	@PostMapping
	@PreAuthorize("hasAuthority('add:contacts')")
	public void addContact(@RequestBody Contact contact) {
		contacts.add(contact);
	}

}
