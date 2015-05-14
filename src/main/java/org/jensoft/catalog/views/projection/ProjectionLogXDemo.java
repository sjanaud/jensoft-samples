/*
 * JenSoft API - Charting Framework
 * http://www.jensoftapi.com
 * Copyright (c) JenSoft. All rights reserved.
 * See JenSoft Software License Agreement
 */
package org.jensoft.catalog.views.projection;

import org.jensoft.core.catalog.nature.JenSoftView;
import org.jensoft.core.palette.color.RosePalette;
import org.jensoft.core.plugin.metrics.AxisMetricsPlugin;
import org.jensoft.core.plugin.outline.OutlinePlugin;
import org.jensoft.core.projection.Projection;
import org.jensoft.core.view.View;
import org.jensoft.core.view.ViewPart;
import org.jensoft.core.view.background.ViewDarkBackground;

/**
 * <code>WindowLogXDemo</code>
 * 
 * @author Sébastien Janaud
 * 
 */
@JenSoftView(background=ViewDarkBackground.class)
public class ProjectionLogXDemo extends View {

	/**
	 * Create demo with log x projection
	 */
	public ProjectionLogXDemo() {
		super();
		setPlaceHolder(100, ViewPart.South, ViewPart.North);

		//  log x projection
		Projection logX = new Projection.LogX(0.099, 1010, 0, 100);
		logX.setThemeColor(RosePalette.LEMONPEEL);
		registerProjection(logX);

		// add free metrics on south part (x dimension) plug-in to figure out
		// log scale
		AxisMetricsPlugin.FreeMetrics freeMetrics = new AxisMetricsPlugin.FreeMetrics.S();
		freeMetrics.addMetrics(0.01);
		freeMetrics.addMetrics(0.1);
		freeMetrics.addMetrics(1);
		freeMetrics.addMetrics(10);
		freeMetrics.addMetrics(100);
		freeMetrics.addMetrics(1000);
		logX.registerPlugin(freeMetrics);

		// device outline plug-in
		logX.registerPlugin(new OutlinePlugin());

	}

}
