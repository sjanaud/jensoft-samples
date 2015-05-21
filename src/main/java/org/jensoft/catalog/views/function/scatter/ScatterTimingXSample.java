/*
 * JenSoft API - Charting Framework
 * http://www.jensoftapi.com
 * Copyright (c) JenSoft. All rights reserved.
 * See JenSoft Software License Agreement
 */
package org.jensoft.catalog.views.function.scatter;

import java.awt.Color;
import java.awt.Font;
import java.util.Calendar;
import java.util.Date;

import org.jensoft.core.catalog.nature.JenSoftView;
import org.jensoft.core.palette.color.RosePalette;
import org.jensoft.core.plugin.function.FunctionPlugin.ScatterFunction;
import org.jensoft.core.plugin.function.scatter.Scatter;
import org.jensoft.core.plugin.function.scatter.morphe.QInverseMorphe;
import org.jensoft.core.plugin.function.scatter.painter.fill.ScatterDefaultFill;
import org.jensoft.core.plugin.function.source.UserSourceFunction;
import org.jensoft.core.plugin.legend.title.TitleLegend;
import org.jensoft.core.plugin.legend.title.TitleLegendConstraints;
import org.jensoft.core.plugin.legend.title.TitleLegendConstraints.LegendAlignment;
import org.jensoft.core.plugin.legend.title.TitleLegendConstraints.LegendPosition;
import org.jensoft.core.plugin.legend.title.TitleLegendPlugin;
import org.jensoft.core.plugin.legend.title.painter.fil.TitleLegendGradientFill;
import org.jensoft.core.plugin.metrics.AxisMetricsPlugin;
import org.jensoft.core.plugin.metrics.AxisMetricsPlugin.Axis;
import org.jensoft.core.plugin.metrics.manager.TimeMetricsManager;
import org.jensoft.core.plugin.outline.OutlinePlugin;
import org.jensoft.core.plugin.translate.TranslateDefaultDeviceContext;
import org.jensoft.core.plugin.translate.TranslatePlugin;
import org.jensoft.core.plugin.zoom.box.ZoomBoxDefaultDeviceContext;
import org.jensoft.core.plugin.zoom.box.ZoomBoxPlugin;
import org.jensoft.core.plugin.zoom.lens.LensDefaultDeviceContext;
import org.jensoft.core.plugin.zoom.lens.LensX;
import org.jensoft.core.plugin.zoom.lens.LensY;
import org.jensoft.core.plugin.zoom.lens.ZoomLensPlugin;
import org.jensoft.core.plugin.zoom.wheel.ZoomWheelPlugin;
import org.jensoft.core.projection.Projection;
import org.jensoft.core.view.View;
import org.jensoft.core.view.ViewPart;
import org.jensoft.core.view.background.ViewDarkBackground;
/**
 * <code>ScatterTimingXSample</code>
 * 
 * @author Sébastien Janaud
 */
@JenSoftView(background=ViewDarkBackground.class,description="Show how to use scatter with timing")
public class ScatterTimingXSample extends View {

	private static final long serialVersionUID = -1465621551873723500L;

	/**
	 * create curve function sample view
	 */
	public ScatterTimingXSample() {
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

		// see note (note-1)
		// Projection.TimeX proj = new Projection.TimeX(minDate,maxDate,0, 100);
		Projection.Linear proj = new Projection.Linear(minDate.getTime(), maxDate.getTime(), 0, 100);
		proj.setThemeColor(RosePalette.LEMONPEEL);
		registerProjection(proj);

		Font fm =  new Font("Dialog", Font.PLAIN, 10);
		Font fM =  new Font("Dialog", Font.PLAIN, 12);
		// metrics

		// (note-1) AxisMetricsPlugin.TimeMetrics knows how to work with time or
		// linear proj. assume you define date
		// as millisecond in linear proj2D constructor on the implicit
		// dimension.
		AxisMetricsPlugin.TimeMetrics timingMetrics = new AxisMetricsPlugin.TimeMetrics(Axis.AxisSouth);
		timingMetrics.setMedianTextFont(fm);
		timingMetrics.setMajorTextFont(fM);
		timingMetrics.registerTimeModel(new TimeMetricsManager.Minute1Model());
		timingMetrics.registerTimeModel(new TimeMetricsManager.Minute15Model());
		timingMetrics.registerTimeModel(new TimeMetricsManager.HourModel());
		timingMetrics.registerTimeModel(new TimeMetricsManager.Hour3Model());

		proj.registerPlugin(timingMetrics);

		// source function with timing
		Calendar ref = (Calendar) cal.clone();

		Calendar p1 = (Calendar) ref.clone();
		p1.add(Calendar.HOUR_OF_DAY, -8);

		Calendar p2 = (Calendar) ref.clone();
		p2.add(Calendar.HOUR_OF_DAY, -4);

		Calendar p3 = (Calendar) ref.clone();
		p3.add(Calendar.HOUR_OF_DAY, 4);

		Calendar p4 = (Calendar) ref.clone();
		p4.add(Calendar.HOUR_OF_DAY, 8);

		Date[] xValues = { p1.getTime(), p2.getTime(), p3.getTime(), p4.getTime() };
		double[] yValues = { 10, 80, 40, 10 };

		UserSourceFunction source = new UserSourceFunction.SplineSource(xValues, yValues, 1000 * 60 * 60);

		// scatter function
		ScatterFunction scatterPlugin = new ScatterFunction();
		proj.registerPlugin(scatterPlugin);

		Scatter scatter1 = new Scatter(source);
		scatter1.setThemeColor(RosePalette.MANDARIN);
		scatter1.setScatterFill(new ScatterDefaultFill());
		scatter1.setScatterMorphe(new QInverseMorphe(5, 3));
		scatterPlugin.addFunction(scatter1);

		// legend plug-in
		Font f =  new Font("Dialog", Font.PLAIN, 12);
		TitleLegend legend = new TitleLegend("Timing X Scatter");
		legend.setLegendFill(new TitleLegendGradientFill(Color.WHITE, RosePalette.INDIGO.brighter()));
		legend.setFont(f);
		legend.setConstraints(new TitleLegendConstraints(LegendPosition.East, 0.3f, LegendAlignment.Rigth));
		TitleLegendPlugin legendPlugin = new TitleLegendPlugin(legend);
		proj.registerPlugin(legendPlugin);

		// zoom wheel
		ZoomWheelPlugin wheelPlugin = new ZoomWheelPlugin();
		proj.registerPlugin(wheelPlugin);

		// zooms box
		ZoomBoxPlugin zoomPlugin = new ZoomBoxPlugin();
		zoomPlugin.registerContext(new ZoomBoxDefaultDeviceContext());
		proj.registerPlugin(zoomPlugin);

		// zoom lens
		ZoomLensPlugin lensPlugin = new ZoomLensPlugin();
		lensPlugin.registerContext(new LensDefaultDeviceContext());
		// create two objectif for x and y dimension
		LensX ox = new LensX();
		LensY oy = new LensY();
		// register widget in zoom objectif plugin
		lensPlugin.registerWidget(ox);
		lensPlugin.registerWidget(oy);
		proj.registerPlugin(lensPlugin);

		ox.setOutlineColor(Color.BLACK);
		ox.setButton1DrawColor(RosePalette.CALYPSOBLUE);
		ox.setButton2DrawColor(RosePalette.CALYPSOBLUE);
		oy.setOutlineColor(Color.BLACK);
		oy.setButton1DrawColor(RosePalette.CALYPSOBLUE);
		oy.setButton2DrawColor(RosePalette.CALYPSOBLUE);

		// translate
		TranslatePlugin translatePlugin = new TranslatePlugin();
		translatePlugin.registerContext(new TranslateDefaultDeviceContext());
		proj.registerPlugin(translatePlugin);

		proj.registerPlugin(new OutlinePlugin());
	}
}
