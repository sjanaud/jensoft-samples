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
public class LogXFreeMetricsSample extends View {

	private static final long serialVersionUID = 154736593558489825L;

	public LogXFreeMetricsSample() {

		Projection proj = new Projection.LogX(0.01, 1000, 0, 100);
		proj.setThemeColor(Color.WHITE);

		registerProjection(proj);

		FreeMetrics afm = new AxisMetricsPlugin.FreeMetrics(Axis.AxisSouth);
		afm.setMarkerColor(JennyPalette.JENNY1);
		afm.setTextColor(JennyPalette.JENNY1);
		
		FreeMetricsManager freeMetricsLayout = new FreeMetricsManager();
		freeMetricsLayout.addMetrics(0.01);
		freeMetricsLayout.addMetrics(0.1);
		freeMetricsLayout.addMetrics(1);
		freeMetricsLayout.addMetrics(10);
		freeMetricsLayout.addMetrics(100);
		freeMetricsLayout.addMetrics(1000);

		afm.setMetricsManager(freeMetricsLayout);

		proj.registerPlugin(afm);

		Font f =  new Font("Dialog", Font.PLAIN, 12);
		TitleLegend legend = new TitleLegend("Free Log X Metrics");
		legend.setLegendFill(new TitleLegendGradientFill(Color.WHITE, RosePalette.NEPTUNE));
		legend.setFont(f);
		legend.setConstraints(new TitleLegendConstraints(LegendPosition.North, 0.3f, LegendAlignment.Rigth));
		TitleLegendPlugin legendPlugin = new TitleLegendPlugin(legend);
		proj.registerPlugin(legendPlugin);

		proj.registerPlugin(new OutlinePlugin());
		proj.registerPlugin(new ZoomWheelPlugin());

	}

	@Portfolio(name = "Metrics-LogX-Free", width = 500, height = 250)
	public static View getPortofolio() {
		LogXFreeMetricsSample demo = new LogXFreeMetricsSample();

		ViewDefaultBackground viewBackground = new ViewDefaultBackground();
		Shader s = new Shader(new float[] { 0f, 1f }, new Color[] { new Color(32, 39, 55), Color.BLACK });
		viewBackground.setShader(s);
		viewBackground.setOutlineStroke(new BasicStroke(2.5f));

		demo.setBackgroundPainter(viewBackground);
		return demo;
	}

}
