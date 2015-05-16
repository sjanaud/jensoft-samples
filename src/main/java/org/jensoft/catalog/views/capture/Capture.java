/*
 * JenSoft API - Charting Framework
 * http://www.jensoftapi.com
 * Copyright (c) JenSoft. All rights reserved.
 * See JenSoft Software License Agreement
 */
package org.jensoft.catalog.views.capture;

import java.awt.Color;
import java.awt.Font;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

import org.jensoft.core.catalog.nature.JenSoftView;
import org.jensoft.core.catalog.ui.ViewFrameUI;
import org.jensoft.core.glyphmetrics.GlyphMetric;
import org.jensoft.core.glyphmetrics.GlyphMetricsNature;
import org.jensoft.core.glyphmetrics.StylePosition;
import org.jensoft.core.glyphmetrics.painter.fill.GlyphFill;
import org.jensoft.core.glyphmetrics.painter.marker.DefaultMarker;
import org.jensoft.core.glyphmetrics.painter.path.MetricsPathDefaultDraw;
import org.jensoft.core.graphics.Shader;
import org.jensoft.core.palette.color.NanoChromatique;
import org.jensoft.core.plugin.capture.CaptureDefaultDeviceContext;
import org.jensoft.core.plugin.capture.CapturePlugin;
import org.jensoft.core.plugin.function.MetricsPathFunction;
import org.jensoft.core.plugin.function.source.UserSourceFunction.SplineSource;
import org.jensoft.core.plugin.grid.Grid.GridOrientation;
import org.jensoft.core.plugin.grid.GridPlugin;
import org.jensoft.core.plugin.legend.title.TitleLegend;
import org.jensoft.core.plugin.legend.title.TitleLegendConstraints;
import org.jensoft.core.plugin.legend.title.TitleLegendConstraints.LegendAlignment;
import org.jensoft.core.plugin.legend.title.TitleLegendConstraints.LegendPosition;
import org.jensoft.core.plugin.legend.title.TitleLegendPlugin;
import org.jensoft.core.plugin.metrics.AxisMetricsPlugin;
import org.jensoft.core.plugin.outline.OutlinePlugin;
import org.jensoft.core.plugin.pathmetrics.MetricsPathPlugin;
import org.jensoft.core.plugin.stripe.StripePlugin;
import org.jensoft.core.plugin.stripe.painter.StripePaint;
import org.jensoft.core.plugin.stripe.painter.StripePalette;
import org.jensoft.core.plugin.translate.TranslatePlugin;
import org.jensoft.core.plugin.zoom.box.ZoomBoxPlugin;
import org.jensoft.core.plugin.zoom.lens.ZoomLensPlugin;
import org.jensoft.core.plugin.zoom.wheel.ZoomWheelPlugin;
import org.jensoft.core.projection.Projection;
import org.jensoft.core.view.View;
import org.jensoft.core.view.background.ViewDarkBackground;

/**
 * <code>Capture</code>
 * 
 * @author Sébastien Janaud
 */
@JenSoftView(background = ViewDarkBackground.class, description = "Show how to use capture plugin to create view image by plugin")
public class Capture extends View {

	public static void main(String[] args) {
		ViewFrameUI ui = new ViewFrameUI(new Capture());
	}
	
	/**
	 * create capture demo
	 */
	public Capture() {
		super();

		setPlaceHolderAxisSouth(80);
		setPlaceHolderAxisWest(120);
		setPlaceHolderAxisEast(120);

		// projection
		Projection proj = new Projection.Linear(-3000, 3000, -2000, 2000);
		proj.setName("capture");
		registerProjection(proj);

		Font font = new Font("lucida console", Font.PLAIN, 10);

		// create modeled axis plug-in in south part
		AxisMetricsPlugin.ModeledMetrics southMetrics = new AxisMetricsPlugin.ModeledMetrics.S();
		proj.registerPlugin(southMetrics);

		// create modeled axis plug-in in west part
		AxisMetricsPlugin.ModeledMetrics westMetrics = new AxisMetricsPlugin.ModeledMetrics.W();
		proj.registerPlugin(westMetrics);

		// outline plug-in
		proj.registerPlugin(new OutlinePlugin());
		Font f = new Font("Dialog", Font.PLAIN, 12);
		// legend plug-in
		TitleLegend legend = new TitleLegend("capture");
		legend.setFont(f);
		legend.setConstraints(new TitleLegendConstraints(LegendPosition.South,
				0.8f, LegendAlignment.Rigth));
		TitleLegendPlugin legendPlugin = new TitleLegendPlugin(legend);
		proj.registerPlugin(legendPlugin);

		// zoom box plug-in
		ZoomBoxPlugin zoomBoxPlugin = new ZoomBoxPlugin();
		proj.registerPlugin(zoomBoxPlugin);

		// translate plug-in
		TranslatePlugin toolTranslate = new TranslatePlugin();
		proj.registerPlugin(toolTranslate);

		// zoom wheel plug-in
		ZoomWheelPlugin zoomWheel = new ZoomWheelPlugin();
		proj.registerPlugin(zoomWheel);

		// zoom objectif plug-in
		ZoomLensPlugin objectif = new ZoomLensPlugin();
		proj.registerPlugin(objectif);

		// stripe plug-in
		StripePlugin stripePlugin = new StripePlugin.MultiplierStripe.H(0, 1000);
		StripePalette bp = new StripePalette();
		bp.addPaint(new StripePaint(new Color(255, 255, 255, 40)));
		bp.addPaint(new StripePaint(new Color(40, 40, 40, 40)));

		StripePaint pGradient = new StripePaint(Shader.Night.getFractions(),
				Shader.Night.getColors());
		stripePlugin.setStripePalette(bp);
		stripePlugin.setAlpha(0.3f);
		proj.registerPlugin(stripePlugin);

		// grid plug-in
		GridPlugin grids = new GridPlugin.MultiplierGrid(0, 1000,
				GridOrientation.Horizontal);
		grids.setGridColor(new Color(59, 89, 152, 100));
		proj.registerPlugin(grids);

		GridPlugin grids2 = new GridPlugin.MultiplierGrid(0, 1000,
				GridOrientation.Vertical);
		grids2.setGridColor(new Color(59, 89, 152, 100));
		proj.registerPlugin(grids2);

		// source function
		List<Point2D> sourceFunction = new ArrayList<Point2D>();
		double[] xValues0 = { -3000, -2500, -2000, -1500, -1000, -500, 500,
				1000, 1500, 2000, 2500, 3000 };
		double[] yValues0 = { 1456, -897, 678, -967, 700, -1000, +800, -799,
				1200, -3000, 1190, -1790 };
		for (int i = 0; i < xValues0.length; i++) {
			sourceFunction.add(new Point2D.Double(xValues0[i], yValues0[i]));
		}

		SplineSource io = new SplineSource(sourceFunction, 50);

		GlyphFill gradientWhiteRed = new GlyphFill(Color.WHITE, Color.RED);
		DefaultMarker markerWhite = new DefaultMarker(NanoChromatique.PURPLE);

		MetricsPathFunction metricsPath = new MetricsPathFunction(io);
		metricsPath.setPathPainter(new MetricsPathDefaultDraw(Color.YELLOW));

		GlyphMetric metric = new GlyphMetric();
		metric.setValue(0);
		metric.setStylePosition(StylePosition.Default);
		metric.setMetricsNature(GlyphMetricsNature.Median);
		metric.setMetricsLabel("0");
		metric.setDivergence(20);
		metric.setGlyphMetricFill(gradientWhiteRed);
		metric.setGlyphMetricMarkerPainter(markerWhite);
		metric.setFont(f);

		metricsPath.addMetrics(metric);

		metric = new GlyphMetric();
		metric.setValue(560);
		metric.setStylePosition(StylePosition.Tangent);
		metric.setMetricsNature(GlyphMetricsNature.Median);
		metric.setMetricsLabel("560");
		metric.setDivergence(20);
		metric.setGlyphMetricFill(gradientWhiteRed);
		metric.setGlyphMetricMarkerPainter(markerWhite);
		metric.setFont(f);

		metricsPath.addMetrics(metric);

		metric = new GlyphMetric();
		metric.setValue(1680);
		metric.setStylePosition(StylePosition.Radial);
		metric.setMetricsNature(GlyphMetricsNature.Median);
		metric.setMetricsLabel("1680");
		metric.setDivergence(10);
		metric.setGlyphMetricFill(gradientWhiteRed);
		metric.setGlyphMetricMarkerPainter(markerWhite);
		metric.setFont(f);
		metricsPath.addMetrics(metric);

		MetricsPathPlugin curveMetricsPathLayout = new MetricsPathPlugin(
				metricsPath);
		proj.registerPlugin(curveMetricsPathLayout);

		// capture plug-in
		CapturePlugin capture = new CapturePlugin();
		proj.registerPlugin(capture);
		capture.registerContext(new CaptureDefaultDeviceContext());

	}

}
