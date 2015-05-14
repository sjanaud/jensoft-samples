/*
 * JenSoft API - Charting Framework
 * http://www.jensoftapi.com
 * Copyright (c) JenSoft. All rights reserved.
 * See JenSoft Software License Agreement
 */
package org.jensoft.catalog.views.view;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;

import org.jensoft.core.catalog.nature.JenSoftView;
import org.jensoft.core.glyphmetrics.GlyphMetric;
import org.jensoft.core.glyphmetrics.StylePosition;
import org.jensoft.core.glyphmetrics.painter.fill.GlyphFill;
import org.jensoft.core.glyphmetrics.painter.marker.RoundMarker;
import org.jensoft.core.graphics.Shader;
import org.jensoft.core.palette.TexturePalette;
import org.jensoft.core.palette.color.ColorPalette;
import org.jensoft.core.palette.color.FilPalette;
import org.jensoft.core.palette.color.RosePalette;
import org.jensoft.core.palette.color.TangoPalette;
import org.jensoft.core.plugin.background.DeviceTextureBackgroundPlugin;
import org.jensoft.core.plugin.function.FunctionPlugin.AreaFunction;
import org.jensoft.core.plugin.function.area.Area;
import org.jensoft.core.plugin.function.area.painter.draw.AreaDefaultDraw;
import org.jensoft.core.plugin.function.area.painter.fill.AreaGradientFill;
import org.jensoft.core.plugin.function.source.UserSourceFunction;
import org.jensoft.core.plugin.grid.Grid.GridOrientation;
import org.jensoft.core.plugin.grid.GridPlugin;
import org.jensoft.core.plugin.metrics.AxisMetricsPlugin;
import org.jensoft.core.plugin.outline.OutlinePlugin;
import org.jensoft.core.plugin.stripe.StripePlugin;
import org.jensoft.core.plugin.stripe.painter.StripePalette;
import org.jensoft.core.plugin.translate.TranslatePlugin;
import org.jensoft.core.plugin.zoom.box.ZoomBoxPlugin;
import org.jensoft.core.plugin.zoom.wheel.ZoomWheelPlugin;
import org.jensoft.core.projection.Projection;
import org.jensoft.core.view.Portfolio;
import org.jensoft.core.view.View;
import org.jensoft.core.view.background.ViewDarkBackground;
import org.jensoft.core.view.background.ViewDefaultBackground;

/**
 * <code>DeviceTexture</code>
 * 
 * @author Sébastien Janaud
 */
@JenSoftView(background=ViewDarkBackground.class,description="Display device with texture background")
public class DeviceTexture extends View {

	private static final long serialVersionUID = 1318388140033364145L;
	
	/** demo font */
	private Font font = new Font("lucida console", Font.PLAIN, 10);

	/**
	 * create the portfolio for the {@link #BubbleSimpleDemo()}
	 * 
	 * @return portfolio view
	 */
	@Portfolio(name = "DeviceTexture", width = 600, height = 400)
	public static View getPortofolio() {
		DeviceTexture demo = new DeviceTexture();
		ViewDefaultBackground viewBackground = new ViewDefaultBackground();
		Shader s = new Shader(new float[] { 0f, 1f }, new Color[] { new Color(32, 39, 55), Color.BLACK });
		viewBackground.setShader(s);
		viewBackground.setOutlineStroke(new BasicStroke(2.5f));
		demo.setBackgroundPainter(viewBackground);
		return demo;
	}

	/**
	 * create device image background demo
	 */
	public DeviceTexture() {

		//setPlaceHolder(100, WindowPart.East,WindowPart.West);
		// proj projection
		Projection proj = new Projection.Linear(1000, 9000, -500, 10500);
		proj.setThemeColor(TangoPalette.BUTTER1);
		registerProjection(proj);

		// device outline plug-in
		proj.registerPlugin(new OutlinePlugin(RosePalette.CALYPSOBLUE));

		// create modeled axis plug-in in south part
		AxisMetricsPlugin.ModeledMetrics southMetrics = new AxisMetricsPlugin.ModeledMetrics.S();
		proj.registerPlugin(southMetrics);
		southMetrics.setTextFont(font);
		

		// create modeled axis plug-in in west part
		AxisMetricsPlugin.ModeledMetrics westMetrics = new AxisMetricsPlugin.ModeledMetrics.W();
		proj.registerPlugin(westMetrics);
		westMetrics.setTextFont(font);
		

		// area functions plug-in
		AreaFunction areaFunctions = new AreaFunction();
		proj.registerPlugin(areaFunctions);

		// create source function
		double[] xValues1 = { 0, 1000, 2000, 3000, 4000, 5200, 6000, 7000, 8000, 9000, 10000 };
		double[] yValues1 = { 4000,0, 5000, 4000, 2008, 3600, 2000, 3000, 7000, 1000, 6000 };
		UserSourceFunction source = new UserSourceFunction.SplineSource(xValues1, yValues1, 100);

		// area curve functions
		Area areaCurve = new Area(source);
		areaCurve.setAreaFill(new AreaGradientFill());
		areaCurve.setAreaDraw(new AreaDefaultDraw(Color.WHITE, new BasicStroke(1f)));
		areaCurve.setThemeColor(FilPalette.BLUE6);
		areaCurve.setAreaBase(-100);
		areaFunctions.addFunction(areaCurve);

		Font f = new Font("Dialog", Font.PLAIN, 14);
		
		// glyph on curve function
		GlyphMetric metric = new GlyphMetric();
		metric.setValue(4.3);
		metric.setStylePosition(StylePosition.Tangent);
		metric.setDivergence(10);
		metric.setGlyphMetricFill(new GlyphFill(Color.WHITE, FilPalette.GREEN4));
		metric.setGlyphMetricMarkerPainter(new RoundMarker(FilPalette.GREEN5, Color.white));
		metric.setFont(f);
		areaCurve.addMetricsLabel(metric);

		metric = new GlyphMetric();
		metric.setValue(1.5);
		metric.setStylePosition(StylePosition.Tangent);
		metric.setDivergence(10);
		metric.setGlyphMetricFill(new GlyphFill(Color.WHITE, FilPalette.GREEN4));
		metric.setGlyphMetricMarkerPainter(new RoundMarker(FilPalette.GREEN5, Color.white));
		metric.setFont(f);
		areaCurve.addMetricsLabel(metric);

		// stripe plug-in
		StripePlugin stripePlugin = new StripePlugin.MultiplierStripe.H(0, 2.5);
		StripePalette bp = new StripePalette();
		bp.addPaint(new Color(255, 255, 255, 40));
		bp.addPaint(ColorPalette.alpha(TangoPalette.ORANGE3, 40));
		stripePlugin.setStripePalette(bp);
		stripePlugin.setAlpha(0.3f);
		proj.registerPlugin(stripePlugin);

		// grids plug-in
		GridPlugin grids = new GridPlugin.MultiplierGrid(0, 2.5, GridOrientation.Horizontal);
		grids.setGridColor(new Color(59, 89, 152, 100));
		proj.registerPlugin(grids);

		GridPlugin grids2 = new GridPlugin.MultiplierGrid(0, 2.5, GridOrientation.Vertical);
		grids2.setGridColor(new Color(59, 89, 152, 100));
		proj.registerPlugin(grids2);

		// zoom box plug-in
		ZoomBoxPlugin zoomTool = new ZoomBoxPlugin();
		proj.registerPlugin(zoomTool);

		// translate plug-in
		TranslatePlugin toolTranslate = new TranslatePlugin();
		proj.registerPlugin(toolTranslate);

		// zoom wheel plug-in
		ZoomWheelPlugin zoomWheel = new ZoomWheelPlugin();
		proj.registerPlugin(zoomWheel);

		// device image plug-in
		DeviceTextureBackgroundPlugin bgPlugin = new DeviceTextureBackgroundPlugin(TexturePalette.getBeeCarbonTexture1());
		proj.registerPlugin(bgPlugin);

	}
	
	

}
