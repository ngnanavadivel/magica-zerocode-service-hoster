package com.codemagic.magica.hoster.core;

import static com.codemagic.magica.hoster.common.util.ExceptionUtil.wrapAndThrowAsServiceHosterException;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Component;

import com.codemagic.magica.hoster.common.Constants;
import com.codemagic.magica.hoster.common.exception.ServiceHosterException;
import com.codemagic.magica.hoster.common.util.WorkspaceUtils;

@Component
public class WorkspaceManager {

	private static final String	CONFIG			= "config";
	private static final String	MAPPING			= "mapping";
	private static final String	ENDPOINT		= "endpoint";
	private static final String	JAR				= "jar";
	private static final String	RUNTIME			= "runtime";
	private static final String	SOURCE			= "source";
	private static final String	TARGET			= "target";
	private static final String	WORKSPACE_DIR	= "MagicaServiceHoster/Workspace";

	private String	client;
	private String	service;
	private String	version;
	private String	resourceDiscriminator;

	public void init(String client, String service, String version, String resourceDiscriminator) {
		this.client = client;
		this.service = service;
		this.version = version;
		this.resourceDiscriminator = resourceDiscriminator;
	}

	public void createServiceWorkspace(String clientId, String serviceId, String version, String resourceDiscriminator)
			throws ServiceHosterException {
		init(clientId, serviceId, version, resourceDiscriminator);
		createServiceDirectories();
		createJarDir();
		createMappingDir();
		createSourceEndpointDir();
		createTargetEndpointDir();
	}

	public void createJarDir() throws ServiceHosterException {
		try {
			WorkspaceUtils.mkdirs(getJarDir());
		} catch (Exception e) {
			wrapAndThrowAsServiceHosterException(e);
		}
	}

	public void createMappingDir() throws ServiceHosterException {
		try {
			WorkspaceUtils.mkdirs(getMappingDir());
		} catch (Exception e) {
			wrapAndThrowAsServiceHosterException(e);
		}
	}

	public void createServiceDirectories() throws ServiceHosterException {
		try {
			WorkspaceUtils.mkdirs(getServiceDirectory());
		} catch (Exception e) {
			wrapAndThrowAsServiceHosterException(e);
		}
	}

	public void createSourceEndpointDir() throws ServiceHosterException {
		try {
			WorkspaceUtils.mkdirs(getSourceEndpointDir());
		} catch (Exception e) {
			wrapAndThrowAsServiceHosterException(e);
		}
	}

	public void createTargetEndpointDir() throws ServiceHosterException {
		try {
			WorkspaceUtils.mkdirs(getTargetEndpointDir());
		} catch (Exception e) {
			wrapAndThrowAsServiceHosterException(e);
		}
	}

	public void createWorkspace() throws ServiceHosterException {
		try {
			WorkspaceUtils.mkdirs(getWorkspaceDir());
		} catch (Exception e) {
			wrapAndThrowAsServiceHosterException(e);
		}
	}

	public String getJarDir() {
		StringBuilder path = new StringBuilder();
		path.append(getServiceConfigDir());
		path.append(File.separator);
		path.append(RUNTIME);
		path.append(File.separator);
		path.append(JAR);
		return path.toString();
	}

	public String getServiceDirectory() {
		StringBuilder path = new StringBuilder();
		path.append(getWorkspaceDir());
		path.append(File.separator);
		path.append(client);
		path.append(File.separator);
		path.append(service);
		path.append(File.separator);
		path.append(version);
		path.append(File.separator);
		path.append(resourceDiscriminator);
		return path.toString();
	}

	public String getServiceConfigDir() {
		StringBuilder path = new StringBuilder();
		path.append(getServiceDirectory());
		path.append(File.separator);
		path.append(CONFIG);
		return path.toString();
	}

	public String getServiceConfigFile() {
		StringBuilder path = new StringBuilder();
		path.append(getServiceConfigDir());
		path.append(File.separator);
		path.append(Constants.SERVICE_CFG_XML_FILE);
		return path.toString();
	}

	public String getSourceEndpointDir() {
		StringBuilder path = new StringBuilder();
		path.append(getServiceConfigDir());
		path.append(File.separator);
		path.append(RUNTIME);
		path.append(File.separator);
		path.append(SOURCE);
		path.append(File.separator);
		path.append(ENDPOINT);
		return path.toString();
	}

	public String getTargetEndpointDir() {
		StringBuilder path = new StringBuilder();
		path.append(getServiceConfigDir());
		path.append(File.separator);
		path.append(RUNTIME);
		path.append(File.separator);
		path.append(TARGET);
		path.append(File.separator);
		path.append(ENDPOINT);
		return path.toString();
	}

	public String getMappingDir() {
		StringBuilder path = new StringBuilder();
		path.append(getServiceConfigDir());
		path.append(File.separator);
		path.append(RUNTIME);
		path.append(File.separator);
		path.append(MAPPING);
		return path.toString();
	}

	public String getWorkspaceDir() {
		StringBuilder path = new StringBuilder();
		path.append(FileUtils.getUserDirectoryPath());
		path.append(File.separator);
		path.append(WORKSPACE_DIR);
		return path.toString();
	}
}
