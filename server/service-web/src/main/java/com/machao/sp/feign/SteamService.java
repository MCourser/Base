package com.machao.sp.feign;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "SteamClient", url = "http://steamcommunity.com", fallback=SteamServiceFallback.class)
public interface SteamService {
	
	@RequestMapping(value = "/inventory/{steamId}/{appId}/{contextId}", method = RequestMethod.GET)
	public String listInventory(@PathVariable("steamId") String steamId, @PathVariable("appId") int appId, @PathVariable("contextId") int contextId, 
			@RequestParam(value = "l", defaultValue="schinese", required=false) String l,
			@RequestParam(value = "count", defaultValue="15", required=false) int count);
	
}