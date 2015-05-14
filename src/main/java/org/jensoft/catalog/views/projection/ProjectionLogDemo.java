/*
 * JenSoft API - Charting Framework
 * http://www.jensoftapi.com
 * Copyright (c) JenSoft. All rights reserved.
 * See JenSoft Software License Agreement
 */
package org.jensoft.catalog.views.projection;

import org.jensoft.core.catalog.nature.JenSoftView;
import org.jensoft.core.catalog.ui.ViewFrameUI;
import org.jensoft.core.palette.color.RosePalette;
import org.jensoft.core.plugin.metrics.AxisMetricsPlugin;
import org.jensoft.core.plugin.outline.OutlinePlugin;
import org.jensoft.core.projection.Projection;
import org.jensoft.core.view.View;
import org.jensoft.core.view.ViewPart;
import org.jensoft.core.view.background.ViewDarkBackground;

/**
 * <code>WindowLogDemo</code>
 * 
 * @author Sébastien Janaud
 */
@JenSoftView(background=ViewDarkBackground.class)
public class ProjectionLogDemo extends View {

	
	/**
	 * Create demo with log x and y projection
	 */
	public ProjectionLogDemo() {
		super();
		setPlaceHolder(100, ViewPart.South, ViewPart.North);

		//  log/log projection
		Projection log = new Projection.Log(0.099, 1010, 0.099, 1010);
		log.setThemeColor(RosePalette.LEMONPEEL);
		registerProjection(log);

		// add free metrics on south part (x dimension) plug-in to figure out
		// log scale
		AxisMetricsPlugin.FreeMetrics freeMetricsX = new AxisMetricsPlugin.FreeMetrics.S();
		freeMetricsX.addMetrics(0.01);
		freeMetricsX.addMetrics(0.1);
		freeMetricsX.addMetrics(1);
		freeMetricsX.addMetrics(10);
		freeMetricsX.addMetrics(100);
		freeMetricsX.addMetrics(1000);
		log.registerPlugin(freeMetricsX);

		// add free metrics on west part (y dimension) plug-in to figure out log
		// scale
		AxisMetricsPlugin.FreeMetrics freeMetricsY = new AxisMetricsPlugin.FreeMetrics.W();
		freeMetricsY.addMetrics(0.01);
		freeMetricsY.addMetrics(0.1);
		freeMetricsY.addMetrics(1);
		freeMetricsY.addMetrics(10);
		freeMetricsY.addMetrics(100);
		freeMetricsY.addMetrics(1000);
		log.registerPlugin(freeMetricsY);

		// device outline plug-in
		log.registerPlugin(new OutlinePlugin());

	}
	
	public static void main(String[] args) {
		ViewFrameUI ui = new ViewFrameUI(new ProjectionLogDemo());
	}

}
