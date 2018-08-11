package com.machao.base.statis_resource.config;

import java.util.Set;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties("static-resource.burglar-chain")
public class BurglarChainConfig {
	private boolean enable;
	private Set<String> referers;

	public boolean isEnable() {
		return enable;
	}

	public void setEnable(boolean enable) {
		this.enable = enable;
	}

	public Set<String> getReferers() {
		return referers;
	}

	public void setReferers(Set<String> referers) {
		this.referers = referers;
	}

}
