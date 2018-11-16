package com.geo.gnss.util;

import java.util.Comparator;

import com.geo.gnss.station.Station;

public class StationSort implements Comparator<Station> {

	@Override
	public int compare(Station st1, Station st2) {
		return st1.getTargetDistance() > st2.getTargetDistance() ? 1 : -1;
	}

}
