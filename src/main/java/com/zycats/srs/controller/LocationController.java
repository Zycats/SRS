package com.zycats.srs.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zycats.srs.entity.Location;
import com.zycats.srs.service.ILocationService;

@RestController
@RequestMapping("rest/location/*")
public class LocationController {

	private ILocationService locationService;

	@RequestMapping(name = "add")
	public Location addLocation(@RequestBody Location location) {
		return locationService.add(location);
	}

	@RequestMapping(name = "delete/{id}")
	public boolean addLocation(@PathVariable int id) {
		return locationService.delete(id);
	}

	@RequestMapping(name = "get/{id}")
	public Location getLocationById(@PathVariable int id) {
		return locationService.getById(id);
	}

	@RequestMapping(name = "get/all")
	public Iterable<Location> getAllLocation() {
		return locationService.getAll();
	}

}
