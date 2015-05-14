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
import org.jensoft.core.palette.color.ColorPalette;
import org.jensoft.core.palette.color.FilPalette;
import org.jensoft.core.palette.color.PetalPalette;
import org.jensoft.core.palette.color.RosePalette;
import org.jensoft.core.plugin.function.FunctionPlugin.CurveFunction;
import org.jensoft.core.plugin.function.FunctionToolkit;
import org.jensoft.core.plugin.function.curve.painter.draw.CurveDefaultDraw;
import org.jensoft.core.plugin.function.source.UserSourceFunction;
import org.jensoft.core.plugin.grid.Grid.GridOrientation;
import org.jensoft.core.plugin.grid.GridPlugin.MultiplierGrid;
import org.jensoft.core.plugin.metrics.AxisMetricsPlugin;
import org.jensoft.core.plugin.outline.OutlinePlugin;
import org.jensoft.core.plugin.stripe.StripePlugin.MultiplierStripe;
import org.jensoft.core.plugin.stripe.painter.StripePalette;
import org.jensoft.core.plugin.zoom.box.ZoomBoxDefaultDeviceContext;
import org.jensoft.core.plugin.zoom.box.ZoomBoxDonutWidget;
import org.jensoft.core.plugin.zoom.box.ZoomBoxPlugin;
import org.jensoft.core.plugin.zoom.box.ZoomBoxPlugin.UserZoomBox;
import org.jensoft.core.plugin.zoom.wheel.ZoomWheelPlugin;
import org.jensoft.core.projection.Projection;
import org.jensoft.core.view.View;
import org.jensoft.core.view.background.ViewDarkBackground;
import org.jensoft.core.plugin.stripe.StripePlugin;
import org.jensoft.core.plugin.grid.GridPlugin;
/**
 * <code>CurveZoomBox</code>
 * 
 * @author Sébastien Janaud
 */
@JenSoftView(background=ViewDarkBackground.class,description="Show how to use function with zoom box")
public class CurveZoomBox extends View {

	private static final long serialVersionUID = 5256807418865341632L;

	/**
	 * create curve function sample view
	 */
	public CurveZoomBox() {
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
		

		CurveFunction curveFunctions = new CurveFunction();
		proj.registerPlugin(curveFunctions);

		// source functions
		double[] xValues1 = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
		double[] yValues1 = { 6, 1.8, 15, 1.9, 3.4, 6.1, 4.2, 8.5, 9.9, 12, 8 };

		double[] xValues2 = { 0, 1, 2, 3, 4, 5.2, 6, 7, 8, 9, 10 };
		double[] yValues2 = { 3, 1, 5, 4, 4.8, 7.3, 2, 3, 7, 10, 6 };

		double[] xValues3 = { 0, 1.4, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
		double[] yValues3 = { 0.4, 7.5, 5, 1, 2.8, 5, 7, 9, 2, 4, 1 };

		UserSourceFunction source1 = new UserSourceFunction.SplineSource(xValues1, yValues1, 0.1);
		UserSourceFunction source2 = new UserSourceFunction.SplineSource(xValues2, yValues2, 0.1);
		UserSourceFunction source3 = new UserSourceFunction.SplineSource(xValues3, yValues3, 0.1);

		// curves function
		Curve curve1 = FunctionToolkit.createCurveFunction(source1, PetalPalette.PETAL1_HC, new CurveDefaultDraw());
		Curve curve2 = FunctionToolkit.createCurveFunction(source2, PetalPalette.PETAL2_HC, new CurveDefaultDraw());
		Curve curve3 = FunctionToolkit.createCurveFunction(source3, PetalPalette.PETAL3_HC, new CurveDefaultDraw());

		// add curves in curve view
		curveFunctions.addFunction(curve1);
		curveFunctions.addFunction(curve2);
		curveFunctions.addFunction(curve3);

		Font f =  new Font("Dialog", Font.PLAIN, 14);
		
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

		proj.setThemeColor(RosePalette.LEMONPEEL);

		MultiplierStripe stripePlugin = new StripePlugin.MultiplierStripe.H(0, 2.5);
		StripePalette bp = new StripePalette();
		bp.addPaint(new Color(255, 255, 255, 5));
		bp.addPaint(ColorPalette.alpha(FilPalette.GREEN4, 20));
		stripePlugin.setStripePalette(bp);
		stripePlugin.setAlpha(0.3f);
		proj.registerPlugin(stripePlugin);

		MultiplierGrid gridLayout = new GridPlugin.MultiplierGrid(0, 2.5, GridOrientation.Horizontal);
		gridLayout.setGridColor(new Color(59, 89, 152, 100));
		proj.registerPlugin(gridLayout);

		MultiplierGrid gridLayout2 = new GridPlugin.MultiplierGrid(0, 2.5, GridOrientation.Vertical);
		gridLayout2.setGridColor(new Color(59, 89, 152, 100));
		proj.registerPlugin(gridLayout2);

		// ZOOM AND TRANSLATE TOOL
		final ZoomBoxPlugin zoomPlugin = new ZoomBoxPlugin();
		zoomPlugin.setZoomBoxDrawColor(RosePalette.LEMONPEEL);
		zoomPlugin.setZoomBoxFillColor(RosePalette.INDIGO);
		zoomPlugin.registerContext(new ZoomBoxDefaultDeviceContext());
		zoomPlugin.registerWidget(new ZoomBoxDonutWidget());
		proj.registerPlugin(zoomPlugin);

		ZoomWheelPlugin wheelPlugin = new ZoomWheelPlugin();
		proj.registerPlugin(wheelPlugin);

		Thread t = new Thread() {

			@Override
			public void run() {
				try {
					Thread.sleep(600);
					UserZoomBox userZoom = zoomPlugin.createUserZoom(3, 6, 3, 12);
					userZoom.zoomIn();
					Thread.sleep(800);
					userZoom.zoomOut();

					Thread.sleep(600);
					UserZoomBox userZoom2 = zoomPlugin.createUserZoom(1.6, 3.4, 13, 15);
					userZoom2.zoomIn();
					Thread.sleep(800);
					userZoom2.zoomOut();

					Thread.sleep(600);
					UserZoomBox userZoom3 = zoomPlugin.createUserZoom(6, 9.8, 3, 8);
					userZoom3.zoomIn();
					Thread.sleep(800);
					userZoom3.zoomOut();

					Thread.sleep(600);
					UserZoomBox userZoom4 = zoomPlugin.createUserZoom(3.56, 4, 3, 6);
					userZoom4.zoomIn();
					Thread.sleep(800);
					userZoom4.zoomOut();

					Thread.sleep(600);
					UserZoomBox userZoom5 = zoomPlugin.createUserZoom(0, 10, 5, 8);
					userZoom5.zoomIn();
					Thread.sleep(800);
					userZoom5.zoomOut();

					Thread.sleep(600);
					UserZoomBox userZoom6 = zoomPlugin.createUserZoom(2.8, 3.2, 0, 15);
					userZoom6.zoomIn();
					Thread.sleep(800);
					userZoom6.zoomOut();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		};
		t.start();

	}

}
