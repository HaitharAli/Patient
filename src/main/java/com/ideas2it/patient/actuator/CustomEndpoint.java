package com.ideas2it.patient.actuator;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.springframework.boot.actuate.endpoint.annotation.DeleteOperation;
import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.boot.actuate.endpoint.annotation.Selector;
import org.springframework.boot.actuate.endpoint.annotation.WriteOperation;
import org.springframework.stereotype.Component;

@Endpoint(id = "release-notes")
@Component
public class CustomEndpoint {

	Map<String, List<String>> releaseNotesMap = new LinkedHashMap<>();

	@PostConstruct
	public void initNotes() {
		releaseNotesMap.put("version 1.0",
				Arrays.asList("Home page created", "adding new patient details", "view patient information"));
		releaseNotesMap.put("version 2.0",
				Arrays.asList("get patient by ID", "update patient details", "patch patient information"));

	}

	@ReadOperation
	public Map<String, List<String>> getReleaseNotes() {
		return releaseNotesMap;
	}

	@ReadOperation
	public List<String> getNotesByVersion(@Selector String version) {
		return releaseNotesMap.get(version);
	}

	@WriteOperation
	public void addNotesByVersion(@Selector String version, String releaseNotes) {
		releaseNotesMap.put(version, Arrays.stream(releaseNotes.split(",")).collect(Collectors.toList()));
	}

	@DeleteOperation
	public void deleteNotesByVersion(@Selector String version) {
		releaseNotesMap.remove(version);
	}

}
