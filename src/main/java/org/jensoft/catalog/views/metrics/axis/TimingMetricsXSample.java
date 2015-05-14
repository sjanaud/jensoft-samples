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
import java.util.Calendar;
import java.util.Date;

import org.jensoft.core.catalog.nature.JenSoftView;
import org.jensoft.core.catalog.ui.ViewFrameUI;
import org.jensoft.core.graphics.Shader;
import org.jensoft.core.palette.color.RosePalette;
import org.jensoft.core.plugin.legend.title.TitleLegend;
import org.jensoft.core.plugin.legend.title.TitleLegendConstraints;
import org.jensoft.core.plugin.legend.title.TitleLegendConstraints.LegendAlignment;
import org.jensoft.core.plugin.legend.title.TitleLegendConstraints.LegendPosition;
import org.jensoft.core.plugin.legend.title.TitleLegendPlugin;
import org.jensoft.core.plugin.legend.title.painter.fil.TitleLegendGradientFill;
import org.jensoft.core.plugin.metrics.AxisMetricsPlugin;
import org.jensoft.core.plugin.metrics.AxisMetricsPlugin.Axis;
import org.jensoft.core.plugin.metrics.Metrics.Gravity;
import org.jensoft.core.plugin.metrics.manager.TimeMetricsManager;
import org.jensoft.core.plugin.outline.OutlinePlugin;
import org.jensoft.core.plugin.translate.TranslateDefaultDeviceContext;
import org.jensoft.core.plugin.translate.TranslatePlugin;
import org.jensoft.core.plugin.zoom.wheel.ZoomWheelPlugin;
import org.jensoft.core.projection.Projection;
import org.jensoft.core.view.Portfolio;
import org.jensoft.core.view.View;
import org.jensoft.core.view.ViewPart;
import org.jensoft.core.view.background.ViewDarkBackground;
import org.jensoft.core.view.background.ViewDefaultBackground;

@JenSoftView(background=ViewDarkBackground.class)
public class TimingMetricsXSample extends View {

	@Portfolio(name = "Metrics-TimingX", width = 500, height = 250)
	public static View getPortofolio() {
		TimingMetricsXSample demo = new TimingMetricsXSample();

		ViewDefaultBackground viewBackground = new ViewDefaultBackground();
		Shader s = new Shader(new float[] { 0f, 1f }, new Color[] { new Color(32, 39, 55), Color.BLACK });
		viewBackground.setShader(s);
		viewBackground.setOutlineStroke(new BasicStroke(2.5f));

		demo.setBackgroundPainter(viewBackground);
		return demo;
	}
	
	
	public static void main(String[] args) {
		ViewFrameUI ui = new ViewFrameUI(new TimingMetricsXSample());
	}

	public TimingMetricsXSample() {
		setPlaceHolder(100, ViewPart.South, ViewPart.North);
		Date now = new Date();

		Calendar cal = Calendar.getInstance();
		cal.setTime(now);

		Calendar calMin = (Calendar) cal.clone();
		calMin.add(Calendar.HOUR_OF_DAY, -12);
		Date minDate = calMin.getTime();

		Calendar calMax = (Calendar) cal.clone();
		calMax.add(Calendar.HOUR_OF_DAY, +8);
		Date maxDate = calMax.getTime();
		System.out.println("date Max : " + maxDate);

		// see note (note-1)
		// Window2D.TimeX proj = new Window2D.TimeX(minDate,maxDate,0, 100);
		Projection.Linear proj = new Projection.Linear(minDate.getTime(), maxDate.getTime(), 0, 100);
		registerProjection(proj);

		ZoomWheelPlugin wheel = new ZoomWheelPlugin();
		proj.registerPlugin(wheel);
		TranslatePlugin translate = new TranslatePlugin();
		translate.registerContext(new TranslateDefaultDeviceContext());
		proj.registerPlugin(translate);

		Font fm =  new Font("Dialog", Font.PLAIN, 10);
		Font fM =  new Font("Dialog", Font.PLAIN, 12);
		// create timing axis with timing frames models
		// (note-1) AxisMetricsPlugin.AxisTiming knows how to work with time or
		// linear proj. assume you define date
		// as millisecond in linear constructor on the implicit dimension.
		AxisMetricsPlugin.TimeMetrics timingMetrics = new AxisMetricsPlugin.TimeMetrics(Axis.AxisSouth);
		timingMetrics.setMedianTextFont(fm);
		timingMetrics.setMajorTextFont(fM);
		timingMetrics.setGravity(Gravity.Natural);
		timingMetrics.registerTimeModel(new TimeMetricsManager.Minute1Model());
		timingMetrics.registerTimeModel(new TimeMetricsManager.Minute15Model());
		timingMetrics.registerTimeModel(new TimeMetricsManager.HourModel());
		timingMetrics.registerTimeModel(new TimeMetricsManager.Hour3Model());
		proj.registerPlugin(timingMetrics);

		// create the legend
		Font f =  new Font("Dialog", Font.PLAIN, 12);
		TitleLegend legend = new TitleLegend("timing x metrics");
		legend.setLegendFill(new TitleLegendGradientFill(RosePalette.EMERALD, RosePalette.NEPTUNE));
		legend.setFont(f);
		legend.setConstraints(new TitleLegendConstraints(LegendPosition.East, 0.3f, LegendAlignment.Rigth));
		TitleLegendPlugin legendPlugin = new TitleLegendPlugin(legend);
		proj.registerPlugin(legendPlugin);

		proj.registerPlugin(new OutlinePlugin());

	}
}
