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
 * <code>WindowLogYDemo</code>
 * 
 * @author Sébastien Janaud
 * 
 */
@JenSoftView(background=ViewDarkBackground.class)
public class ProjectionLogYDemo extends View {

	/**
	 * Create demo with log y projection
	 */
	public ProjectionLogYDemo() {
		super();
		setPlaceHolder(100, ViewPart.South, ViewPart.West);

		//  log y projection
		Projection logY = new Projection.LogY(0, 100, 0.099, 1010);
		logY.setThemeColor(RosePalette.LEMONPEEL);
		registerProjection(logY);

		// add free metrics on west part (y dimension) plug-in to figure out log
		// scale
		AxisMetricsPlugin.FreeMetrics freeMetrics = new AxisMetricsPlugin.FreeMetrics.W();
		freeMetrics.addMetrics(0.01);
		freeMetrics.addMetrics(0.1);
		freeMetrics.addMetrics(1);
		freeMetrics.addMetrics(10);
		freeMetrics.addMetrics(100);
		freeMetrics.addMetrics(1000);
		logY.registerPlugin(freeMetrics);

		// device outline plug-in
		logY.registerPlugin(new OutlinePlugin());

	}

}
