/*
 * JenSoft API - Charting Framework
 * http://www.jensoftapi.com
 * Copyright (c) JenSoft. All rights reserved.
 * See JenSoft Software License Agreement
 */
package org.jensoft.catalog.views.metrics.device;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;

import org.jensoft.core.catalog.nature.JenSoftView;
import org.jensoft.core.graphics.Shader;
import org.jensoft.core.palette.color.RosePalette;
import org.jensoft.core.plugin.legend.title.TitleLegend;
import org.jensoft.core.plugin.legend.title.TitleLegendConstraints;
import org.jensoft.core.plugin.legend.title.TitleLegendConstraints.LegendAlignment;
import org.jensoft.core.plugin.legend.title.TitleLegendConstraints.LegendPosition;
import org.jensoft.core.plugin.legend.title.TitleLegendPlugin;
import org.jensoft.core.plugin.legend.title.painter.fil.TitleLegendGradientFill;
import org.jensoft.core.plugin.metrics.DeviceMetricsPlugin;
import org.jensoft.core.plugin.metrics.DeviceMetricsPlugin.DeviceStaticMetrics;
import org.jensoft.core.plugin.outline.OutlinePlugin;
import org.jensoft.core.plugin.zoom.wheel.ZoomWheelPlugin;
import org.jensoft.core.plugin.zoom.wheel.ZoomWheelPlugin.ZoomWheelType;
import org.jensoft.core.projection.Projection;
import org.jensoft.core.view.Portfolio;
import org.jensoft.core.view.View;
import org.jensoft.core.view.background.ViewDarkBackground;
import org.jensoft.core.view.background.ViewDefaultBackground;

/**
 * <code>DeviceXStaticMetricsSample</code>
 * 
 * @author Sébastien Janaud
 */
@JenSoftView(background=ViewDarkBackground.class)
public class DeviceXStaticMetricsSample extends View {

	public DeviceXStaticMetricsSample() {
		super();

		// proj linear projection
		Projection proj = new Projection.Linear(-3000, 3000, -2500, 2500);
		proj.setThemeColor(RosePalette.EMERALD);
		registerProjection(proj);

		// device x metrics
		DeviceStaticMetrics deviceXMetrics = new DeviceMetricsPlugin.DeviceStaticMetrics.X(4, -500);
		proj.registerPlugin(deviceXMetrics);
		deviceXMetrics.setMarkerColor(RosePalette.LEMONPEEL);
		deviceXMetrics.setTextColor(RosePalette.LEMONPEEL);
		deviceXMetrics.setBaseLineColor(RosePalette.MELON);

		// device outline plug-in
		proj.registerPlugin(new OutlinePlugin());

		// legend plug-in
		Font f =  new Font("Dialog", Font.PLAIN, 12);
		TitleLegend legend = new TitleLegend("Device Static Metrics");
		legend.setLegendFill(new TitleLegendGradientFill(Color.WHITE, RosePalette.NEPTUNE));
		legend.setFont(f);
		legend.setConstraints(new TitleLegendConstraints(LegendPosition.East, 0.2f, LegendAlignment.Rigth));
		TitleLegendPlugin legendPlugin = new TitleLegendPlugin(legend);
		proj.registerPlugin(legendPlugin);

		// zoom wheel on x dimension only
		proj.registerPlugin(new ZoomWheelPlugin(ZoomWheelType.ZoomX));

	}

	/**
	 * create demo portfolio
	 * 
	 * @return demo image portfolio
	 */
	@Portfolio(name = "Metrics-Device-Static", width = 500, height = 250)
	public static View getPortofolio() {
		DeviceXStaticMetricsSample demo = new DeviceXStaticMetricsSample();

		ViewDefaultBackground viewBackground = new ViewDefaultBackground();
		Shader s = new Shader(new float[] { 0f, 1f }, new Color[] { new Color(32, 39, 55), Color.BLACK });
		viewBackground.setShader(s);
		viewBackground.setOutlineStroke(new BasicStroke(2.5f));

		demo.setBackgroundPainter(viewBackground);
		return demo;
	}

}
