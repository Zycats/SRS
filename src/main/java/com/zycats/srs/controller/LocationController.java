package com.zycats.srs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.zycats.srs.entity.Location;
import com.zycats.srs.service.ILocationService;

@RestController
@RequestMapping("/rest/location/*")
public class LocationController {

	@Autowired
	private ILocationService locationService;

	@RequestMapping(value = "add", method = RequestMethod.POST)
	public Location addLocation(@RequestBody Location location) {
		return locationService.add(location);
	}

	@RequestMapping(value = "delete/{id}", method = RequestMethod.GET)
	public boolean deleteLocation(@PathVariable int id) {
		return locationService.delete(id);
	}

	@RequestMapping(value = "get/{id}", method = RequestMethod.POST)
	public Location getLocationById(@PathVariable int id) {
		return locationService.getById(id);
	}

	@RequestMapping(value = "get/all", method = RequestMethod.GET)
	public Iterable<Location> getAllLocation() {
		return locationService.getAll();
	}

}
