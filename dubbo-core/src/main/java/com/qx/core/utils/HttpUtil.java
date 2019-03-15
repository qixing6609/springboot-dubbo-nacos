package com.qx.core.utils;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;

public class HttpUtil {
	public static String basePath() throws MalformedURLException,
			URISyntaxException {
		String basePath = "http://" + ThreadContent.request().getServerName()
				+ ":" + ThreadContent.request().getServerPort()
				+ ThreadContent.request().getContextPath();
		URL urlObj = new URL(basePath);
		if (urlObj.getPort() == urlObj.getDefaultPort()) {
			urlObj = new URL(urlObj.getProtocol(), urlObj.getHost(), -1,
					urlObj.getPath());
		}
		return urlObj.toURI().toString();
	}
}