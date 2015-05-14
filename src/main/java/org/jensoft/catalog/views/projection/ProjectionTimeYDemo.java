/*
 * JenSoft API - Charting Framework
 * http://www.jensoftapi.com
 * Copyright (c) JenSoft. All rights reserved.
 * See JenSoft Software License Agreement
 */
package org.jensoft.catalog.views.projection;

import java.util.Calendar;
import java.util.Date;

import org.jensoft.core.catalog.nature.JenSoftView;
import org.jensoft.core.palette.color.RosePalette;
import org.jensoft.core.plugin.metrics.AxisMetricsPlugin;
import org.jensoft.core.plugin.metrics.AxisMetricsPlugin.Axis;
import org.jensoft.core.plugin.metrics.manager.TimeMetricsManager;
import org.jensoft.core.plugin.outline.OutlinePlugin;
import org.jensoft.core.projection.Projection;
import org.jensoft.core.view.View;
import org.jensoft.core.view.ViewPart;
import org.jensoft.core.view.background.ViewDarkBackground;

/**
 * <code>WindowTimeYDemo</code>
 * 
 * @author Sébastien Janaud
 */
@JenSoftView(background=ViewDarkBackground.class)
public class ProjectionTimeYDemo extends View {

	/**
	 * Create a demo with time projection on y dimension
	 */
	public ProjectionTimeYDemo() {
		super();
		setPlaceHolder(80, ViewPart.West);

		// create time
		Date now = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(now);
		// min time
		Calendar calMin = (Calendar) cal.clone();
		calMin.add(Calendar.HOUR_OF_DAY, -12);
		Date minDate = calMin.getTime();
		// max time
		Calendar calMax = (Calendar) cal.clone();
		calMax.add(Calendar.HOUR_OF_DAY, +8);
		Date maxDate = calMax.getTime();

		//  time on y dimension
		Projection timeY = new Projection.TimeY(0, 100, minDate, maxDate);
		registerProjection(timeY);

		timeY.setThemeColor(RosePalette.CALYPSOBLUE.brighter());

		// metrics west timing (y dimension) plug-in to figure out the projection
		// time
		AxisMetricsPlugin.TimeMetrics timingMetrics = new AxisMetricsPlugin.TimeMetrics(Axis.AxisWest);
		timingMetrics.registerTimeModel(new TimeMetricsManager.Minute1Model());
		timingMetrics.registerTimeModel(new TimeMetricsManager.HourModel());
		timingMetrics.registerTimeModel(new TimeMetricsManager.Hour3Model());
		timeY.registerPlugin(timingMetrics);

		// device outline plug-in
		timeY.registerPlugin(new OutlinePlugin());

	}

}
