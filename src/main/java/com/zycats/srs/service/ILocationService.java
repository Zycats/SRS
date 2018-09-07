package com.zycats.srs.service;

import com.zycats.srs.entity.Location;

public interface ILocationService {

	Location add(Location location);

	Iterable<Location> getAll();

	Location getById(int id);

	boolean delete(int id);

}