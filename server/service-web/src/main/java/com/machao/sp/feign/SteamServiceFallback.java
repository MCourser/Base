package com.machao.sp.feign;

import org.springframework.stereotype.Component;

@Component
public class SteamServiceFallback implements SteamService {

	@Override
	public String listInventory(String steamId, int appId, int contextId, String l, int count) {
		return "nothing";
	}
}
