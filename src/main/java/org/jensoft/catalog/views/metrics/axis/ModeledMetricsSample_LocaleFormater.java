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
import java.util.Locale;

import org.jensoft.core.catalog.nature.JenSoftView;
import org.jensoft.core.catalog.ui.ViewFrameUI;
import org.jensoft.core.graphics.Shader;
import org.jensoft.core.palette.color.RosePalette;
import org.jensoft.core.palette.color.TangoPalette;
import org.jensoft.core.plugin.metrics.AxisMetricsPlugin;
import org.jensoft.core.plugin.outline.OutlinePlugin;
import org.jensoft.core.plugin.translate.TranslateCompassWidget;
import org.jensoft.core.plugin.translate.TranslateDefaultDeviceContext;
import org.jensoft.core.plugin.translate.TranslatePlugin;
import org.jensoft.core.plugin.zoom.box.ZoomBoxDefaultDeviceContext;
import org.jensoft.core.plugin.zoom.box.ZoomBoxDonutWidget;
import org.jensoft.core.plugin.zoom.box.ZoomBoxPlugin;
import org.jensoft.core.plugin.zoom.wheel.ZoomWheelPlugin;
import org.jensoft.core.projection.Projection;
import org.jensoft.core.view.Portfolio;
import org.jensoft.core.view.View;
import org.jensoft.core.view.ViewPart;
import org.jensoft.core.view.background.ViewDarkBackground;
import org.jensoft.core.view.background.ViewDefaultBackground;

/**
 * <code>ModeledMetricsTestlocaleSample</code>
 * 
 * @author Sébastien Janaud
 */
@JenSoftView(background=ViewDarkBackground.class,description ="Show how to use modeled metrics with locale")
public class ModeledMetricsSample_LocaleFormater extends View {

	private static final long serialVersionUID = 1787274622779219368L;

	
	public static void main(String[] args) {
		ViewFrameUI ui = new ViewFrameUI(new ModeledMetricsSample_LocaleFormater());
	}
	
	/**
	 * create the modeled metrics demo with locale
	 */
	public ModeledMetricsSample_LocaleFormater() {
		super();
		setPlaceHolder(100, ViewPart.South);
		setPlaceHolder(80, ViewPart.West);

		// create linear proj
		Projection.Linear proj = new Projection.Linear(-700, 1200, -140, 300);
		proj.setThemeColor(RosePalette.LEMONPEEL);
		registerProjection(proj);

		// register a zoom wheel plug-in
		ZoomWheelPlugin wheel = new ZoomWheelPlugin();
		proj.registerPlugin(wheel);

		// register a translate with context menu plug-in and lock selected for
		// direct use
		TranslatePlugin translate = new TranslatePlugin();
		translate.registerContext(new TranslateDefaultDeviceContext());
		translate.registerWidget(new TranslateCompassWidget());
		proj.registerPlugin(translate);

		// register a zoom box with context menu and widget history
		ZoomBoxPlugin zoomBox = new ZoomBoxPlugin();
		proj.registerPlugin(zoomBox);
		zoomBox.registerWidget(new ZoomBoxDonutWidget());
		zoomBox.registerContext(new ZoomBoxDefaultDeviceContext());

		Font font = new Font("lucida console", Font.PLAIN, 10);

		// create modeled axis plug-in in south part
		AxisMetricsPlugin.ModeledMetrics southMetrics = new AxisMetricsPlugin.ModeledMetrics.S();
		//southMetrics.setPaintAxisBaseLine(true);
		southMetrics.setBaseLineColor(Color.ORANGE);
		southMetrics.setAxisSpacing(0);
		proj.registerPlugin(southMetrics);
		southMetrics.setTextFont(font);
		southMetrics.setTextColor(TangoPalette.SCARLETRED3.brighter());
		
		
		
		//###########
		//SET LOCALE
		southMetrics.setLocale(Locale.US);	

		// create modeled axis plug-in in west part
		AxisMetricsPlugin.ModeledMetrics westMetrics = new AxisMetricsPlugin.ModeledMetrics.W();
		proj.registerPlugin(westMetrics);
		westMetrics.setTextFont(font);
		westMetrics.setTextColor(TangoPalette.SKYBLUE3.brighter());
		westMetrics.setMarkerColor(TangoPalette.SKYBLUE3);

		// register device outline plug-in
	   proj.registerPlugin(new OutlinePlugin());

	}

	@Portfolio(name = "Metrics-Modeled", width = 500, height = 250)
	public static View getPortofolio() {
		ModeledMetricsSample_LocaleFormater demo = new ModeledMetricsSample_LocaleFormater();

		ViewDefaultBackground viewBackground = new ViewDefaultBackground();
		Shader s = new Shader(new float[] { 0f, 1f }, new Color[] { new Color(32, 39, 55), Color.BLACK });
		viewBackground.setShader(s);
		viewBackground.setOutlineStroke(new BasicStroke(2.5f));

		demo.setBackgroundPainter(viewBackground);
		return demo;
	}
}