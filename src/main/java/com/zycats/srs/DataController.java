package com.zycats.srs;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DataController
{
	@RequestMapping(value="/getData", produces="application/json", method=RequestMethod.GET)
	public Map<String, String> getData()
	{
		Map<String, String> res = new HashMap<String, String>();
		res.put("message", "This is demo message");
		return res;
	}
}
