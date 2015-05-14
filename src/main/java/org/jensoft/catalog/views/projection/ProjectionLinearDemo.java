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
import org.jensoft.core.view.background.ViewDarkBackground;

/**
 * <code>WindowLinearDemo</code>
 * 
 * @author Sébastien Janaud
 */
@JenSoftView(background=ViewDarkBackground.class)
public class ProjectionLinearDemo extends View {

	/**
	 * Create a demo with a basic linear projection
	 */
	public ProjectionLinearDemo() {

		super(60);

		//  linear projection
		Projection.Linear linear = new Projection.Linear(-20, 20, 0, 100);
		linear.setThemeColor(RosePalette.AEGEANBLUE);
		registerProjection(linear);

		// device outline
		linear.registerPlugin(new OutlinePlugin());

		// modeled axis plug-in in south part
		AxisMetricsPlugin.ModeledMetrics southMetrics = new AxisMetricsPlugin.ModeledMetrics.S();
		linear.registerPlugin(southMetrics);
		// southMetrics.registerMetricsModels(MetricsModelRangeCollections.MilliKilo);

		// modeled axis plug-in in west part
		AxisMetricsPlugin.ModeledMetrics westMetrics = new AxisMetricsPlugin.ModeledMetrics.W();
		linear.registerPlugin(westMetrics);

	}

}
