package com.machao.sp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.machao.sp.feign.SteamService;

import io.swagger.annotations.ApiOperation;

@RestController
@RestControllerAdvice 
@RequestMapping("/steam")
public class SteamController extends BaseController{
	
	@Autowired
	private SteamService steamService;
	
//	@ApiOperation(value = "List Roles", notes = "list system roles")
//	@PreAuthorize("authenticated and hasPermission('/role/', 'role:list')")
	@ApiOperation(value = "List Inventory", notes = "list inventory")
	@GetMapping(value = "/inventory/{steamId}/{appId}")
	public ResponseEntity<String> listInventory(@PathVariable String steamId, @PathVariable int appId,
			@RequestParam(value = "l", defaultValue="schinese", required=false) String l,
			@RequestParam(value = "count", defaultValue="15", required=false) int count){
		return ResponseEntity.ok(steamService.listInventory(steamId, appId, 2, l, count));
	}
	
}
