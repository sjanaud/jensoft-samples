/*
 * JenSoft API - Charting Framework
 * http://www.jensoftapi.com
 * Copyright (c) JenSoft. All rights reserved.
 * See JenSoft Software License Agreement
 */
package org.jensoft.catalog.views.function;

import java.awt.Color;
import java.awt.Font;
import java.awt.geom.Point2D;
import java.util.Vector;

import org.jensoft.core.catalog.nature.JenSoftView;
import org.jensoft.core.glyphmetrics.GlyphMetric;
import org.jensoft.core.glyphmetrics.GlyphMetricsNature;
import org.jensoft.core.glyphmetrics.StylePosition;
import org.jensoft.core.glyphmetrics.painter.fill.GlyphFill;
import org.jensoft.core.glyphmetrics.painter.marker.DefaultMarker;
import org.jensoft.core.glyphmetrics.painter.path.MetricsPathDefaultDraw;
import org.jensoft.core.palette.color.FilPalette;
import org.jensoft.core.plugin.capture.CaptureDefaultDeviceContext;
import org.jensoft.core.plugin.capture.CapturePlugin;
import org.jensoft.core.plugin.function.MetricsPathFunction;
import org.jensoft.core.plugin.function.source.UserSourceFunction;
import org.jensoft.core.plugin.function.source.UserSourceFunction.SplineSource;
import org.jensoft.core.plugin.grid.Grid.GridOrientation;
import org.jensoft.core.plugin.grid.GridPlugin;
import org.jensoft.core.plugin.grid.GridPlugin.MultiplierGrid;
import org.jensoft.core.plugin.legend.title.TitleLegend;
import org.jensoft.core.plugin.legend.title.TitleLegendConstraints;
import org.jensoft.core.plugin.legend.title.TitleLegendConstraints.LegendAlignment;
import org.jensoft.core.plugin.legend.title.TitleLegendConstraints.LegendPosition;
import org.jensoft.core.plugin.legend.title.TitleLegendPlugin;
import org.jensoft.core.plugin.metrics.AxisMetricsPlugin;
import org.jensoft.core.plugin.outline.OutlinePlugin;
import org.jensoft.core.plugin.pathmetrics.MetricsPathPlugin;
import org.jensoft.core.plugin.stripe.StripePlugin;
import org.jensoft.core.plugin.stripe.StripePlugin.MultiplierStripe;
import org.jensoft.core.plugin.stripe.painter.StripePalette;
import org.jensoft.core.plugin.translate.TranslatePlugin;
import org.jensoft.core.plugin.zoom.box.ZoomBoxPlugin;
import org.jensoft.core.plugin.zoom.lens.ZoomLensPlugin;
import org.jensoft.core.plugin.zoom.wheel.ZoomWheelPlugin;
import org.jensoft.core.projection.Projection;
import org.jensoft.core.view.View;
import org.jensoft.core.view.background.ViewDarkBackground;
@JenSoftView(background=ViewDarkBackground.class,description="Show hhow to use core metrics path function")
public class FunctionMetricsDemo extends View {

	private static final long serialVersionUID = 3825644135436938536L;

	public FunctionMetricsDemo() {
		super();

		setPlaceHolderAxisSouth(80);
		setPlaceHolderAxisWest(120);
		setPlaceHolderAxisEast(120);

		Projection proj2D = new Projection.Linear(-3000, 3000, -2000, 2000);
		proj2D.setThemeColor(FilPalette.GREEN4);
		registerProjection(proj2D);
		Font f =  new Font("Dialog", Font.PLAIN, 12);
		
		AxisMetricsPlugin.ModeledMetrics westMetrics = new AxisMetricsPlugin.ModeledMetrics.W();
		proj2D.registerPlugin(westMetrics);
		westMetrics.setTextFont(f);
		

		AxisMetricsPlugin.ModeledMetrics eastMetrics = new AxisMetricsPlugin.ModeledMetrics.E();
		proj2D.registerPlugin(eastMetrics);
		eastMetrics.setTextFont(f);

		AxisMetricsPlugin.ModeledMetrics southMetrics = new AxisMetricsPlugin.ModeledMetrics.S();
		proj2D.registerPlugin(southMetrics);
		southMetrics.setTextFont(f);
		

		AxisMetricsPlugin.ModeledMetrics northMetrics = new AxisMetricsPlugin.ModeledMetrics.N();
		proj2D.registerPlugin(northMetrics);
		northMetrics.setTextFont(f);
		northMetrics.getMetricsManager().unlockLabel();

		proj2D.registerPlugin(new OutlinePlugin());

		TitleLegend legend = new TitleLegend("curve metrics demo");
		legend.setFont(f);
		legend.setConstraints(new TitleLegendConstraints(LegendPosition.South, 0.8f, LegendAlignment.Rigth));
		TitleLegendPlugin lgendL = new TitleLegendPlugin(legend);
		proj2D.registerPlugin(lgendL);

		ZoomBoxPlugin zoomTool = new ZoomBoxPlugin();
		proj2D.registerPlugin(zoomTool);

		TranslatePlugin toolTranslate = new TranslatePlugin();
		proj2D.registerPlugin(toolTranslate);

		ZoomWheelPlugin zoomWheel = new ZoomWheelPlugin();
		proj2D.registerPlugin(zoomWheel);

		ZoomLensPlugin objectif = new ZoomLensPlugin();
		proj2D.registerPlugin(objectif);

		MultiplierStripe stripePlugin = new StripePlugin.MultiplierStripe.H(0, 1000);
		StripePalette bp = new StripePalette();
		bp.addPaint(new Color(255, 255, 255, 40));
		bp.addPaint(new Color(40, 40, 40, 40));
		stripePlugin.setStripePalette(bp);
		stripePlugin.setAlpha(0.3f);
		proj2D.registerPlugin(stripePlugin);

		MultiplierGrid grids = new GridPlugin.MultiplierGrid(0, 1000, GridOrientation.Horizontal);
		grids.setGridColor(new Color(59, 89, 152, 100));
		proj2D.registerPlugin(grids);

		MultiplierGrid grids2 = new GridPlugin.MultiplierGrid(0, 1000, GridOrientation.Vertical);
		grids2.setGridColor(new Color(59, 89, 152, 100));
		proj2D.registerPlugin(grids2);

		Vector<Point2D> serie1 = new Vector<Point2D>();
		double[] xValues0 = { -3000, -2500, -2000, -1500, -1000, -500, 500, 1000, 1500, 2000, 2500, 3000 };
		double[] yValues0 = { 1456, -897, 678, -967, 700, -1000, +800, -799, 1200, -3000, 1190, -1790 };
		for (int i = 0; i < xValues0.length; i++) {
			serie1.add(new Point2D.Double(xValues0[i], yValues0[i]));
		}

		SplineSource io = new UserSourceFunction.SplineSource(serie1, 50);

		GlyphFill gradientWhiteRed = new GlyphFill(Color.WHITE, Color.RED);
		DefaultMarker markerWhite = new DefaultMarker(Color.WHITE);

		
		MetricsPathFunction metricsPath = new MetricsPathFunction(io);
		metricsPath.setPathPainter(new MetricsPathDefaultDraw(Color.YELLOW));

		Font fm =  new Font("Dialog", Font.PLAIN, 14);
		
		GlyphMetric metric = new GlyphMetric();
		metric.setValue(0);
		metric.setStylePosition(StylePosition.Default);
		metric.setMetricsNature(GlyphMetricsNature.Median);
		metric.setMetricsLabel("0");
		metric.setDivergence(20);
		metric.setGlyphMetricFill(gradientWhiteRed);
		metric.setGlyphMetricMarkerPainter(markerWhite);
		metric.setFont(fm);

		metricsPath.addMetrics(metric);

		metric = new GlyphMetric();
		metric.setValue(560);
		metric.setStylePosition(StylePosition.Tangent);
		metric.setMetricsNature(GlyphMetricsNature.Median);
		metric.setMetricsLabel("560");
		metric.setDivergence(20);
		metric.setGlyphMetricFill(gradientWhiteRed);
		metric.setGlyphMetricMarkerPainter(markerWhite);
		metric.setFont(fm);

		metricsPath.addMetrics(metric);

		metric = new GlyphMetric();
		metric.setValue(1680);
		metric.setStylePosition(StylePosition.Radial);
		metric.setMetricsNature(GlyphMetricsNature.Median);
		metric.setMetricsLabel("1680");
		metric.setDivergence(10);
		metric.setGlyphMetricFill(gradientWhiteRed);
		metric.setGlyphMetricMarkerPainter(markerWhite);
		metric.setFont(fm);
		metricsPath.addMetrics(metric);

		//this plugin is not really use in reality, only in this pedagogical practices about metrics path function (path metrics is already included in function)
		MetricsPathPlugin curveMetricsPathLayout = new MetricsPathPlugin(metricsPath);

		proj2D.registerPlugin(curveMetricsPathLayout);

		proj2D.registerPlugin(new ZoomWheelPlugin());

		CapturePlugin capture = new CapturePlugin();
		capture.registerContext(new CaptureDefaultDeviceContext());
		proj2D.registerPlugin(capture);

	}

}
