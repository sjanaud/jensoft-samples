/*
 * JenSoft API - Charting Framework
 * http://www.jensoftapi.com
 * Copyright (c) JenSoft. All rights reserved.
 * See JenSoft Software License Agreement
 */
package org.jensoft.catalog.views;

import java.io.File;

import org.jensoft.core.plugin.PluginPlatform;

/**
 * Generate all demos views portfolio
 * 
 * @author Sebastien Janaud
 */
public class DemoPortfolioGenerator {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String outputDir = System.getProperty("user.home") + File.separator + ".jensoft" + File.separator + "demo";
		PluginPlatform.createPortfolio(DemoPortfolioGenerator.class.getPackage().getName(), outputDir, 800, 400);		
		System.exit(-1);
	}

}
