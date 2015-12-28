package com.codemagic.magica.hoster.model;

public class ServiceConfiguration {
	public enum EndpointType {
		HTTP, FTP, JMS
	};

	public enum DataFormat {
		XML, JSON, FLAT
	};

	private String			jarLocation;
	private String			objectMappingLocation;
	private String			urlMappingLocation;
	private String			sourcePackage;
	private String			sourceClassName;
	private EndpointType	sourceEndpointType;
	private DataFormat		sourceDataFormat;
	private String			sourceEndpointConfigLocation;
	private String			targetPackage;
	private String			targetClassName;
	private EndpointType	targetEndpointType;
	private DataFormat		targetDataFormat;
	private String			targetEndpointConfigLocation;

	public String getJarLocation() {
		return jarLocation;
	}

	public void setJarLocation(String jarLocation) {
		this.jarLocation = jarLocation;
	}

	public EndpointType getSourceEndpointType() {
		return sourceEndpointType;
	}

	public void setSourceEndpointType(EndpointType sourceEndpointType) {
		this.sourceEndpointType = sourceEndpointType;
	}

	public DataFormat getSourceDataFormat() {
		return sourceDataFormat;
	}

	public void setSourceDataFormat(DataFormat sourceDataFormat) {
		this.sourceDataFormat = sourceDataFormat;
	}

	public String getSourceEndpointConfigLocation() {
		return sourceEndpointConfigLocation;
	}

	public void setSourceEndpointConfigLocation(String sourceEndpointConfigLocation) {
		this.sourceEndpointConfigLocation = sourceEndpointConfigLocation;
	}

	public EndpointType getTargetEndpointType() {
		return targetEndpointType;
	}

	public void setTargetEndpointType(EndpointType targetEndpointType) {
		this.targetEndpointType = targetEndpointType;
	}

	public DataFormat getTargetDataFormat() {
		return targetDataFormat;
	}

	public void setTargetDataFormat(DataFormat targetDataFormat) {
		this.targetDataFormat = targetDataFormat;
	}

	public String getTargetEndpointConfigLocation() {
		return targetEndpointConfigLocation;
	}

	public void setTargetEndpointConfigLocation(String targetEndpointConfigLocation) {
		this.targetEndpointConfigLocation = targetEndpointConfigLocation;
	}

	public String getSourceClassName() {
		return sourceClassName;
	}

	public void setSourceClassName(String sourceClassName) {
		this.sourceClassName = sourceClassName;
	}

	public String getTargetClassName() {
		return targetClassName;
	}

	public void setTargetClassName(String targetClassName) {
		this.targetClassName = targetClassName;
	}

	public String getSourcePackage() {
		return sourcePackage;
	}

	public void setSourcePackage(String sourcePackage) {
		this.sourcePackage = sourcePackage;
	}

	public String getTargetPackage() {
		return targetPackage;
	}

	public void setTargetPackage(String targetPackage) {
		this.targetPackage = targetPackage;
	}

	public String getObjectMappingLocation() {
		return objectMappingLocation;
	}

	public void setObjectMappingLocation(String objectMappingLocation) {
		this.objectMappingLocation = objectMappingLocation;
	}

	public String getUrlMappingLocation() {
		return urlMappingLocation;
	}

	public void setUrlMappingLocation(String urlMappingLocation) {
		this.urlMappingLocation = urlMappingLocation;
	}

}
