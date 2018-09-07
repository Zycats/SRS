package com.zycats.srs.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zycats.srs.entity.Location;
import com.zycats.srs.repository.LocationRepository;

@Service
public class LocationService implements ILocationService {

	@Autowired
	private LocationRepository locationRepository;

	@Override
	public Location add(Location location) {
		return locationRepository.save(location);
	}

	@Override
	public Iterable<Location> getAll() {
		return locationRepository.findAll();
	}

	@Override
	public Location getById(int id) {
		return locationRepository.findById(id).get();
	}

	@Override
	public boolean delete(int id) {
		try {
			locationRepository.deleteById(id);
		} catch (IllegalArgumentException e) {
			return false;
		}
		return true;
	}
}
