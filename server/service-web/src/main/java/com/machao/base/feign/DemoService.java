package com.machao.base.feign;

//@FeignClient(name = "SteamClient", url = "http://steamcommunity.com", fallback=DemoServiceFallback.class)
public interface DemoService {
	
//	@RequestMapping(value = "/inventory/{steamId}/{appId}/{contextId}", method = RequestMethod.GET)
//	public String listInventory(@PathVariable("steamId") String steamId, @PathVariable("appId") int appId, @PathVariable("contextId") int contextId, 
//			@RequestParam(value = "l", defaultValue="schinese", required=false) String l,
//			@RequestParam(value = "count", defaultValue="15", required=false) int count);
	
}