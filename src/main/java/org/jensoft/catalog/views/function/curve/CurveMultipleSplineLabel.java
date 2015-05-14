/*
 * JenSoft API - Charting Framework
 * http://www.jensoftapi.com
 * Copyright (c) JenSoft. All rights reserved.
 * See JenSoft Software License Agreement
 */
package org.jensoft.catalog.views.function.curve;
import org.jensoft.core.plugin.function.curve.Curve;
import java.awt.Color;
import java.awt.Font;

import org.jensoft.core.catalog.nature.JenSoftView;
import org.jensoft.core.glyphmetrics.GlyphMetric;
import org.jensoft.core.glyphmetrics.GlyphMetricsNature;
import org.jensoft.core.glyphmetrics.StylePosition;
import org.jensoft.core.glyphmetrics.painter.fill.GlyphFill;
import org.jensoft.core.glyphmetrics.painter.marker.RoundMarker;
import org.jensoft.core.palette.color.PetalPalette;
import org.jensoft.core.palette.color.RosePalette;
import org.jensoft.core.plugin.background.DeviceGradientBackgroundPlugin;
import org.jensoft.core.plugin.background.DeviceNightBackground;
import org.jensoft.core.plugin.function.FunctionPlugin;
import org.jensoft.core.plugin.function.FunctionToolkit;
import org.jensoft.core.plugin.function.curve.painter.draw.CurveDefaultDraw;
import org.jensoft.core.plugin.function.source.UserSourceFunction;
import org.jensoft.core.plugin.grid.Grid.GridOrientation;
import org.jensoft.core.plugin.grid.GridPlugin.MultiplierGrid;
import org.jensoft.core.plugin.metrics.AxisMetricsPlugin;
import org.jensoft.core.plugin.outline.OutlinePlugin;
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
import org.jensoft.core.view.View;
import org.jensoft.core.view.background.ViewDarkBackground;
import org.jensoft.core.plugin.stripe.StripePlugin;
import org.jensoft.core.plugin.grid.GridPlugin;
/**
 * <code>CurveMultipleSplineLabel</code>
 * 
 * @author Sébastien Janaud
 */
@JenSoftView(background=ViewDarkBackground.class,description="Show how to use curve function and labels")
public class CurveMultipleSplineLabel extends View {

	private static final long serialVersionUID = -2586815317944182797L;

	/**
	 * create curve function sample view
	 */
	public CurveMultipleSplineLabel() {
		super();
		setPlaceHolder(40);

		// proj projection
		Projection proj = new Projection.Linear(0, 10, 0, 18);
		registerProjection(proj);
		proj.setThemeColor(RosePalette.LEMONPEEL);

		// device outline plug-in
		proj.registerPlugin(new OutlinePlugin());

		Font font = new Font("lucida console", Font.PLAIN, 10);

		// create modeled axis plug-in in south part
		AxisMetricsPlugin.ModeledMetrics southMetrics = new AxisMetricsPlugin.ModeledMetrics.S();
		proj.registerPlugin(southMetrics);
		southMetrics.setTextFont(font);
		

		// create modeled axis plug-in in west part
		AxisMetricsPlugin.ModeledMetrics westMetrics = new AxisMetricsPlugin.ModeledMetrics.W();
		proj.registerPlugin(westMetrics);
		westMetrics.setTextFont(font);
		

		FunctionPlugin<Curve> curveFunctions = new FunctionPlugin.CurveFunction();
		proj.registerPlugin(curveFunctions);

		double[] xValues1 = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
		double[] yValues1 = { 6, 1.8, 15, 1.9, 3.4, 6.1, 4.2, 8.5, 9.9, 12, 8 };

		double[] xValues2 = { 0, 1, 2, 3, 4, 5.2, 6, 7, 8, 9, 10 };
		double[] yValues2 = { 3, 1, 5, 4, 4.8, 7.3, 2, 3, 7, 10, 6 };

		double[] xValues3 = { 0, 1.4, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
		double[] yValues3 = { 0.4, 7.5, 5, 1, 2.8, 5, 7, 9, 2, 4, 1 };

		UserSourceFunction source1 = new UserSourceFunction.SplineSource(xValues1, yValues1, 0.1);
		UserSourceFunction source2 = new UserSourceFunction.SplineSource(xValues2, yValues2, 0.1);
		UserSourceFunction source3 = new UserSourceFunction.SplineSource(xValues3, yValues3, 0.1);

		// create curves
		Curve curve1 = FunctionToolkit.createCurveFunction(source1, PetalPalette.PETAL1_HC, new CurveDefaultDraw());
		Curve curve2 = FunctionToolkit.createCurveFunction(source2, PetalPalette.PETAL2_HC, new CurveDefaultDraw());
		Curve curve3 = FunctionToolkit.createCurveFunction(source3, PetalPalette.PETAL3_HC, new CurveDefaultDraw());

		// add curves in curve view
		curveFunctions.addFunction(curve1);
		curveFunctions.addFunction(curve2);
		curveFunctions.addFunction(curve3);

		Font f =  new Font("Dialog", Font.PLAIN, 12);
		
		GlyphMetric metric = new GlyphMetric();
		metric.setValue(2.5);
		metric.setStylePosition(StylePosition.Tangent);
		metric.setMetricsLabel("2.5");
		metric.setDivergence(10);
		metric.setGlyphMetricFill(new GlyphFill(Color.WHITE, PetalPalette.PETAL1_HC));
		metric.setGlyphMetricMarkerPainter(new RoundMarker(PetalPalette.PETAL1_HC, Color.white));
		metric.setFont(f);
		curve1.addMetricsLabel(metric);

		metric = new GlyphMetric();
		metric.setValue(1.5);
		metric.setStylePosition(StylePosition.Tangent);
		metric.setMetricsLabel("1.5");
		metric.setDivergence(10);
		metric.setGlyphMetricFill(new GlyphFill(Color.WHITE, PetalPalette.PETAL2_HC));
		metric.setGlyphMetricMarkerPainter(new RoundMarker(PetalPalette.PETAL2_HC, Color.white));
		metric.setFont(f);
		curve2.addMetricsLabel(metric);

		metric = new GlyphMetric();
		metric.setValue(5.6);
		metric.setStylePosition(StylePosition.Tangent);
		metric.setMetricsLabel("5.6");
		metric.setDivergence(10);
		metric.setGlyphMetricFill(new GlyphFill(Color.WHITE, PetalPalette.PETAL3_HC));
		metric.setGlyphMetricMarkerPainter(new RoundMarker(PetalPalette.PETAL3_HC, Color.white));
		metric.setFont(f);
		curve3.addMetricsLabel(metric);

		metric = new GlyphMetric();
		metric.setValue(8.2);
		metric.setStylePosition(StylePosition.Tangent);
		metric.setMetricsNature(GlyphMetricsNature.Median);
		metric.setMetricsLabel("8.2");
		metric.setDivergence(10);
		metric.setGlyphMetricFill(new GlyphFill(Color.WHITE, PetalPalette.PETAL2_HC));
		metric.setGlyphMetricMarkerPainter(new RoundMarker(PetalPalette.PETAL2_HC, Color.white));
		metric.setFont(f);
		curve2.addMetricsLabel(metric);

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

		// decoration a night gradient (dark blue to black)
		DeviceGradientBackgroundPlugin night = new DeviceNightBackground();
		night.setAlpha(1f);
		proj.registerPlugin(night);

		MultiplierStripe stripePlugin = new StripePlugin.MultiplierStripe.H(0, 2.5);
		StripePalette bp = new StripePalette();
		bp.addPaint(new Color(255, 255, 255, 40));
		bp.addPaint(new Color(40, 40, 40, 40));
		stripePlugin.setStripePalette(bp);
		stripePlugin.setAlpha(0.3f);
		proj.registerPlugin(stripePlugin);

		MultiplierGrid gridLayout = new GridPlugin.MultiplierGrid(0, 2.5, GridOrientation.Horizontal);
		gridLayout.setGridColor(new Color(255, 255, 255, 60));
		proj.registerPlugin(gridLayout);

		MultiplierGrid gridLayout2 = new GridPlugin.MultiplierGrid(0, 2.5, GridOrientation.Vertical);
		gridLayout2.setGridColor(new Color(255, 255, 255, 60));
		proj.registerPlugin(gridLayout2);

	}
}
