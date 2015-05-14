/*
 * JenSoft API - Charting Framework
 * http://www.jensoftapi.com
 * Copyright (c) JenSoft. All rights reserved.
 * See JenSoft Software License Agreement
 */
package org.jensoft.catalog.views.intro;

import org.jensoft.core.catalog.nature.Intro;
import org.jensoft.core.catalog.nature.JenSoftView;
import org.jensoft.core.catalog.ui.ViewFrameUI;
import org.jensoft.core.view.View;
import org.jensoft.core.view.background.ViewDarkBackground;

/**
 * <code>JenSoftAPIntro</code>demo
 * 
 * @author Sébastien Janaud
 */

@Intro
@JenSoftView(background=ViewDarkBackground.class,description="Show JenSoft API capabilities animation.")
public class JenSoftAPIntro extends View {

	private static final long serialVersionUID = -7525972054850967185L;

	/**
	 * API Intro
	 */
	public JenSoftAPIntro() {
		setName("JenSoft API");
		JenSoftBreackpointExecutor jenSoftDemoExecutor = new JenSoftBreackpointExecutor(this);
		jenSoftDemoExecutor.start();
	}
	
	public static void main(String[] args) {
		ViewFrameUI ui = new ViewFrameUI(new JenSoftAPIntro());
	}

}
