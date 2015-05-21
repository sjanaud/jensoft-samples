/*
 * JenSoft API - Charting Framework
 * http://www.jensoftapi.com
 * Copyright (c) JenSoft. All rights reserved.
 * See JenSoft Software License Agreement
 */
package org.jensoft.catalog.views.function.area;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;

import org.jensoft.core.catalog.nature.JenSoftView;
import org.jensoft.core.glyphmetrics.GlyphMetric;
import org.jensoft.core.glyphmetrics.StylePosition;
import org.jensoft.core.glyphmetrics.painter.fill.GlyphFill;
import org.jensoft.core.glyphmetrics.painter.marker.RoundMarker;
import org.jensoft.core.graphics.Shader;
import org.jensoft.core.palette.color.ColorPalette;
import org.jensoft.core.palette.color.FilPalette;
import org.jensoft.core.palette.color.RosePalette;
import org.jensoft.core.plugin.function.FunctionPlugin;
import org.jensoft.core.plugin.function.FunctionPlugin.AreaFunction;
import org.jensoft.core.plugin.function.area.Area;
import org.jensoft.core.plugin.function.area.painter.draw.AreaDefaultDraw;
import org.jensoft.core.plugin.function.area.painter.fill.AreaGradientFill;
import org.jensoft.core.plugin.function.source.UserSourceFunction;
import org.jensoft.core.plugin.grid.Grid.GridOrientation;
import org.jensoft.core.plugin.grid.GridPlugin;
import org.jensoft.core.plugin.grid.GridPlugin.MultiplierGrid;
import org.jensoft.core.plugin.legend.title.TitleLegend;
import org.jensoft.core.plugin.legend.title.TitleLegendConstraints;
import org.jensoft.core.plugin.legend.title.TitleLegendConstraints.LegendAlignment;
import org.jensoft.core.plugin.legend.title.TitleLegendConstraints.LegendPosition;
import org.jensoft.core.plugin.legend.title.TitleLegendPlugin;
import org.jensoft.core.plugin.legend.title.painter.fil.TitleLegendGradientFill;
import org.jensoft.core.plugin.metrics.AxisMetricsPlugin;
import org.jensoft.core.plugin.outline.OutlinePlugin;
import org.jensoft.core.plugin.stripe.StripePlugin;
import org.jensoft.core.plugin.stripe.StripePlugin.MultiplierStripe;
import org.jensoft.core.plugin.stripe.painter.StripePalette;
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
import org.jensoft.core.view.background.ViewDarkBackground;
import org.jensoft.core.view.background.ViewDefaultBackground;

/**
 * <code>AreaSimpleSplineXFunctionLabel</code>
 * 
 * @author Sébastien Janaud
 */
@JenSoftView(background=ViewDarkBackground.class,description="Area with spline line and labels based on spline x function")
public class AreaSimpleSplineXFunctionLabel extends View {

	private static final long serialVersionUID = -2966577609994307922L;

	/**
	 * Create area function with label
	 */
	public AreaSimpleSplineXFunctionLabel() {
		super(50);

		// proj projection
		Projection proj = new Projection.Linear(1, 6, 0, 18);
		registerProjection(proj);
		proj.setThemeColor(RosePalette.LIME);

		Font font =  new Font("Dialog", Font.PLAIN, 12);
		
		//Font font = new Font("lucida console", Font.PLAIN, 10);

		// create modeled axis plug-in in south part
		AxisMetricsPlugin.ModeledMetrics southMetrics = new AxisMetricsPlugin.ModeledMetrics.S();
		proj.registerPlugin(southMetrics);
		southMetrics.setTextFont(font);
		

		// create modeled axis plug-in in west part
		AxisMetricsPlugin.ModeledMetrics westMetrics = new AxisMetricsPlugin.ModeledMetrics.W();
		proj.registerPlugin(westMetrics);
		westMetrics.setTextFont(font);
		

		// area function plug-in
		AreaFunction areaPlugin = new FunctionPlugin.AreaFunction();
		proj.registerPlugin(areaPlugin);

		// device outline plug-in
		proj.registerPlugin(new OutlinePlugin());

		// source function
		double[] xValues1 = { 0, 1, 2, 3, 4, 5.2, 6, 7, 8, 9, 10 };
		double[] yValues1 = { 3, 1, 5, 4, 4.8, 7.3, 2, 3, 7, 10, 6 };
		UserSourceFunction source = new UserSourceFunction.SplineSource(xValues1, yValues1, 0.1);

		// area function
		Area area = new Area(source);
		area.setAreaFill(new AreaGradientFill());
		area.setAreaDraw(new AreaDefaultDraw(RosePalette.LEMONPEEL, Color.WHITE));
		area.setThemeColor(RosePalette.CORALRED);
		area.setAreaBase(0);
		areaPlugin.addFunction(area);

		// create glyphe on source function
		GlyphMetric metric;
		Font f =  new Font("Dialog", Font.PLAIN, 12);
		metric = new GlyphMetric();
		metric.setValue(1.5);
		metric.setStylePosition(StylePosition.Tangent);
		metric.setDivergence(10);
		metric.setGlyphMetricFill(new GlyphFill(Color.WHITE, FilPalette.BLUE12));
		metric.setGlyphMetricMarkerPainter(new RoundMarker(FilPalette.BLUE12, Color.white));
		metric.setFont(f);
		area.addMetricsLabel(metric);
		
		metric = new GlyphMetric();
		metric.setValue(2.8);
		metric.setStylePosition(StylePosition.Tangent);
		metric.setDivergence(10);
		metric.setGlyphMetricFill(new GlyphFill(Color.WHITE, FilPalette.BLUE4));
		metric.setGlyphMetricMarkerPainter(new RoundMarker(FilPalette.BLUE4, Color.white));
		metric.setFont(f);
		area.addMetricsLabel(metric);		
	
		metric = new GlyphMetric();
		metric.setValue(4.3);
		metric.setStylePosition(StylePosition.Tangent);
		metric.setDivergence(10);
		metric.setGlyphMetricFill(new GlyphFill(Color.WHITE, FilPalette.BLUE12));
		metric.setGlyphMetricMarkerPainter(new RoundMarker(FilPalette.BLUE12, Color.white));
		metric.setFont(f);
		area.addMetricsLabel(metric);
		
		metric = new GlyphMetric();
		metric.setValue(5.3);
		metric.setStylePosition(StylePosition.Tangent);
		metric.setDivergence(10);
		metric.setGlyphMetricFill(new GlyphFill(Color.WHITE, FilPalette.BLUE7));
		metric.setGlyphMetricMarkerPainter(new RoundMarker(FilPalette.BLUE7, Color.white));
		metric.setFont(f);
		area.addMetricsLabel(metric);

		

		// legend plug-in
		Font fl =  new Font("Dialog", Font.PLAIN, 11);
		TitleLegend legend = new TitleLegend("Simple Area Label");
		legend.setLegendFill(new TitleLegendGradientFill(Color.WHITE, FilPalette.BLUE12));
		legend.setFont(fl);
		legend.setConstraints(new TitleLegendConstraints(LegendPosition.East, 0.3f, LegendAlignment.Rigth));
		TitleLegendPlugin legendPlugin = new TitleLegendPlugin(legend);
		proj.registerPlugin(legendPlugin);

		// strip plug-in
		MultiplierStripe stripePlugin = new StripePlugin.MultiplierStripe.H(0, 2.5);
		StripePalette bp = new StripePalette();
		bp.addPaint(new Color(255, 255, 255, 20));
		bp.addPaint(ColorPalette.alpha(RosePalette.LEMONPEEL, 60));
		stripePlugin.setStripePalette(bp);
		proj.registerPlugin(stripePlugin);

		// grid plug-in
		MultiplierGrid gridLayout = new GridPlugin.MultiplierGrid(0, 2.5, GridOrientation.Horizontal);
		gridLayout.setGridColor(new Color(255, 255, 255, 60));
		proj.registerPlugin(gridLayout);

		MultiplierGrid gridLayout2 = new GridPlugin.MultiplierGrid(0, 2.5, GridOrientation.Vertical);
		gridLayout2.setGridColor(new Color(255, 255, 255, 60));
		proj.registerPlugin(gridLayout2);

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

	}

	/** 
	 * @return image portfolio
	 */
	@Portfolio(name = "Function-Area-SplineX-Label", width = 600, height = 400)
	public static View getPortofolio() {
		AreaSimpleSplineXFunctionLabel demo = new AreaSimpleSplineXFunctionLabel();

		ViewDefaultBackground viewBackground = new ViewDefaultBackground();
		Shader s = new Shader(new float[] { 0f, 1f }, new Color[] { new Color(32, 39, 55), Color.BLACK });
		viewBackground.setShader(s);
		viewBackground.setOutlineStroke(new BasicStroke(2.5f));

		demo.setBackgroundPainter(viewBackground);
		return demo;
	}

}
