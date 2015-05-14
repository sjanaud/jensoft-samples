/*
 * JenSoft API - Charting Framework
 * http://www.jensoftapi.com
 * Copyright (c) JenSoft. All rights reserved.
 * See JenSoft Software License Agreement
 */
package org.jensoft.catalog.views.function.area;
import org.jensoft.core.plugin.stripe.StripePlugin;
import org.jensoft.core.plugin.grid.GridPlugin;
import org.jensoft.core.plugin.function.area.Area;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.util.Calendar;
import java.util.Date;

import org.jensoft.core.catalog.nature.JenSoftView;
import org.jensoft.core.graphics.Shader;
import org.jensoft.core.palette.color.PetalPalette;
import org.jensoft.core.palette.color.RosePalette;
import org.jensoft.core.plugin.function.FunctionPlugin.AreaFunction;
import org.jensoft.core.plugin.function.FunctionPlugin;
import org.jensoft.core.plugin.function.area.painter.draw.AreaDefaultDraw;
import org.jensoft.core.plugin.function.area.painter.fill.AreaGradientFill;
import org.jensoft.core.plugin.function.source.UserSourceFunction;
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
import org.jensoft.core.view.Portfolio;
import org.jensoft.core.view.View;
import org.jensoft.core.view.ViewPart;
import org.jensoft.core.view.background.ViewDarkBackground;
import org.jensoft.core.view.background.ViewDefaultBackground;

/**
 * <code>CurveTimingXSample</code>
 * 
 * @author Sébastien Janaud
 */
@JenSoftView(background=ViewDarkBackground.class,description="Area with timing exemple")
public class AreaTimingXSample extends View {

	private static final long serialVersionUID = 5865053540110877959L;

	/**
	 * create curve function sample view
	 */
	public AreaTimingXSample() {
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

		Font fM =  new Font("Dialog", Font.PLAIN, 12);
		Font fm =  new Font("Dialog", Font.PLAIN, 10);
		//metrics
		
		// (note-1) AxisMetricsPlugin.TimeMetrics knows how to work with time or
		// linear proj. assume you define date
		// as millisecond in linear proj2D constructor on the implicit dimension.
		AxisMetricsPlugin.TimeMetrics timingMetrics = new AxisMetricsPlugin.TimeMetrics(Axis.AxisSouth);
		timingMetrics.setMedianTextFont(fm);
		timingMetrics.setMajorTextFont(fM);
		timingMetrics.registerTimeModel(new TimeMetricsManager.Minute1Model());
		timingMetrics.registerTimeModel(new TimeMetricsManager.Minute15Model());
		timingMetrics.registerTimeModel(new TimeMetricsManager.HourModel());
		timingMetrics.registerTimeModel(new TimeMetricsManager.Hour3Model());

		proj.registerPlugin(timingMetrics);
		
		//source function with timing
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

		// area function
		AreaFunction areaPlugin = new FunctionPlugin.AreaFunction();
		proj.registerPlugin(areaPlugin);
		
		Area area1 = new Area(source);
		area1.setThemeColor(PetalPalette.PETAL1_HC);
		area1.setAreaDraw(new AreaDefaultDraw(Color.WHITE, new BasicStroke(1.5f), Color.WHITE, new BasicStroke(1.5f)));
		area1.setAreaFill(new AreaGradientFill());
		area1.setAreaBase(0);
		
		areaPlugin.addFunction(area1);


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

	@Portfolio(name = "Function-Area-Metrics-TimingX", width = 500, height = 250)
	public static View getPortofolio() {
		AreaTimingXSample demo = new AreaTimingXSample();

		ViewDefaultBackground viewBackground = new ViewDefaultBackground();
		Shader s = new Shader(new float[] { 0f, 1f }, new Color[] { new Color(32, 39, 55), Color.BLACK });
		viewBackground.setShader(s);
		viewBackground.setOutlineStroke(new BasicStroke(2.5f));

		demo.setBackgroundPainter(viewBackground);
		return demo;
	}

}
