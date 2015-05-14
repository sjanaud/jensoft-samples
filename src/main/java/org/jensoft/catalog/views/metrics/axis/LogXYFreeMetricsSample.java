/*
 * JenSoft API - Charting Framework
 * http://www.jensoftapi.com
 * Copyright (c) JenSoft. All rights reserved.
 * See JenSoft Software License Agreement
 */
package org.jensoft.catalog.views.metrics.axis;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;

import org.jensoft.core.catalog.nature.JenSoftView;
import org.jensoft.core.graphics.Shader;
import org.jensoft.core.palette.color.JennyPalette;
import org.jensoft.core.palette.color.RosePalette;
import org.jensoft.core.plugin.legend.title.TitleLegend;
import org.jensoft.core.plugin.legend.title.TitleLegendConstraints;
import org.jensoft.core.plugin.legend.title.TitleLegendConstraints.LegendAlignment;
import org.jensoft.core.plugin.legend.title.TitleLegendConstraints.LegendPosition;
import org.jensoft.core.plugin.legend.title.TitleLegendPlugin;
import org.jensoft.core.plugin.legend.title.painter.fil.TitleLegendGradientFill;
import org.jensoft.core.plugin.metrics.AxisMetricsPlugin;
import org.jensoft.core.plugin.metrics.AxisMetricsPlugin.Axis;
import org.jensoft.core.plugin.metrics.AxisMetricsPlugin.FreeMetrics;
import org.jensoft.core.plugin.metrics.manager.FreeMetricsManager;
import org.jensoft.core.plugin.outline.OutlinePlugin;
import org.jensoft.core.plugin.zoom.wheel.ZoomWheelPlugin;
import org.jensoft.core.projection.Projection;
import org.jensoft.core.view.Portfolio;
import org.jensoft.core.view.View;
import org.jensoft.core.view.background.ViewDarkBackground;
import org.jensoft.core.view.background.ViewDefaultBackground;

@JenSoftView(background=ViewDarkBackground.class)
public class LogXYFreeMetricsSample extends View {

	private static final long serialVersionUID = -5587954338980601279L;

	public LogXYFreeMetricsSample() {

		Projection proj = new Projection.Log(0.01, 1000, 0.01, 1000);
		proj.setThemeColor(Color.WHITE);

		registerProjection(proj);

		// metrics plug-ins
		FreeMetrics freeMetricsSouth = new AxisMetricsPlugin.FreeMetrics(Axis.AxisSouth);
		FreeMetrics freeMetricsWest = new AxisMetricsPlugin.FreeMetrics(Axis.AxisWest);

		freeMetricsSouth.setMarkerColor(JennyPalette.JENNY1);
		freeMetricsSouth.setTextColor(JennyPalette.JENNY1);
		
		freeMetricsWest.setMarkerColor(JennyPalette.JENNY1);
		freeMetricsWest.setTextColor(JennyPalette.JENNY1);
		// free metrics manager
		FreeMetricsManager freeMetricsManager = new FreeMetricsManager();
	
		freeMetricsManager.addMetrics(0.01);
		freeMetricsManager.addMetrics(0.1);
		freeMetricsManager.addMetrics(1);
		freeMetricsManager.addMetrics(10);
		freeMetricsManager.addMetrics(100);
		freeMetricsManager.addMetrics(1000);

		freeMetricsSouth.setMetricsManager(freeMetricsManager);
		freeMetricsWest.setMetricsManager(freeMetricsManager);

		proj.registerPlugin(freeMetricsSouth);
		proj.registerPlugin(freeMetricsWest);

		Font f =  new Font("Dialog", Font.PLAIN, 12);
		TitleLegend legend = new TitleLegend("Free Log Metrics");
		legend.setLegendFill(new TitleLegendGradientFill(Color.WHITE, RosePalette.NEPTUNE));
		legend.setFont(f);
		legend.setConstraints(new TitleLegendConstraints(LegendPosition.North, 0.3f, LegendAlignment.Rigth));
		TitleLegendPlugin legendPlugin = new TitleLegendPlugin(legend);
		proj.registerPlugin(legendPlugin);

		proj.registerPlugin(new OutlinePlugin());
		proj.registerPlugin(new ZoomWheelPlugin());

	}

	@Portfolio(name = "Metrics-LogXY-Free", width = 500, height = 250)
	public static View getPortofolio() {
		LogXYFreeMetricsSample demo = new LogXYFreeMetricsSample();

		ViewDefaultBackground viewBackground = new ViewDefaultBackground();
		Shader s = new Shader(new float[] { 0f, 1f }, new Color[] { new Color(32, 39, 55), Color.BLACK });
		viewBackground.setShader(s);
		viewBackground.setOutlineStroke(new BasicStroke(2.5f));

		demo.setBackgroundPainter(viewBackground);
		return demo;
	}

}
