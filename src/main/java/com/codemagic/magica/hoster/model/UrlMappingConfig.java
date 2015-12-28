package com.codemagic.magica.hoster.model;

import java.util.List;

import com.codemagic.magica.hoster.model.ServiceConfiguration.EndpointType;

public class UrlMappingConfig {
	private boolean			dynamicInputURI;
	private EndpointType	readerType;
	private List<Fragment>	fragments;

	public boolean isDynamicInputURI() {
		return dynamicInputURI;
	}

	public void setDynamicInputURI(boolean dynamicInputURI) {
		this.dynamicInputURI = dynamicInputURI;
	}

	public EndpointType getReaderType() {
		return readerType;
	}

	public void setReaderType(EndpointType readerType) {
		this.readerType = readerType;
	}

	public List<Fragment> getFragments() {
		return fragments;
	}

	public void setFragments(List<Fragment> fragments) {
		this.fragments = fragments;
	}

}
