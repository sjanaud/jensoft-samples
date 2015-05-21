/*
 * JenSoft API - Charting Framework
 * http://www.jensoftapi.com
 * Copyright (c) JenSoft. All rights reserved.
 * See JenSoft Software License Agreement
 */
package org.jensoft.catalog.views.gauges.metrics;

import java.awt.Color;
import java.awt.Font;
import java.awt.geom.Arc2D;

import org.jensoft.core.catalog.nature.JenSoftView;
import org.jensoft.core.catalog.ui.ViewFrameUI;
import org.jensoft.core.glyphmetrics.AbstractMetricsPath.ProjectionNature;
import org.jensoft.core.glyphmetrics.GeneralMetricsPath;
import org.jensoft.core.glyphmetrics.GlyphMetric;
import org.jensoft.core.glyphmetrics.GlyphMetricsNature;
import org.jensoft.core.glyphmetrics.StylePosition;
import org.jensoft.core.glyphmetrics.painter.fill.GlyphFill;
import org.jensoft.core.glyphmetrics.painter.marker.RectangleMarker;
import org.jensoft.core.glyphmetrics.painter.marker.TicTacMarker;
import org.jensoft.core.glyphmetrics.painter.marker.TriangleMarker;
import org.jensoft.core.glyphmetrics.painter.marker.TriangleMarker.TriangleDirection;
import org.jensoft.core.glyphmetrics.painter.path.MetricsPathDefaultDraw;
import org.jensoft.core.palette.color.Alpha;
import org.jensoft.core.palette.color.NanoChromatique;
import org.jensoft.core.palette.color.PetalPalette;
import org.jensoft.core.plugin.gauge.core.GaugeMetricsPath;
import org.jensoft.core.plugin.grid.Grid.GridOrientation;
import org.jensoft.core.plugin.grid.GridPlugin;
import org.jensoft.core.plugin.legend.title.TitleLegend;
import org.jensoft.core.plugin.legend.title.TitleLegendConstraints;
import org.jensoft.core.plugin.legend.title.TitleLegendConstraints.LegendAlignment;
import org.jensoft.core.plugin.legend.title.TitleLegendConstraints.LegendPosition;
import org.jensoft.core.plugin.legend.title.TitleLegendPlugin;
import org.jensoft.core.plugin.legend.title.painter.fil.TitleLegendGradientFill;
import org.jensoft.core.plugin.metrics.AxisMetricsPlugin;
import org.jensoft.core.plugin.outline.OutlinePlugin;
import org.jensoft.core.plugin.pathmetrics.MetricsPathPlugin;
import org.jensoft.core.plugin.stripe.StripePlugin;
import org.jensoft.core.plugin.stripe.painter.StripePalette;
import org.jensoft.core.plugin.translate.TranslateDefaultDeviceContext;
import org.jensoft.core.plugin.translate.TranslatePlugin;
import org.jensoft.core.plugin.zoom.box.ZoomBoxPlugin;
import org.jensoft.core.plugin.zoom.lens.ZoomLensPlugin;
import org.jensoft.core.plugin.zoom.wheel.ZoomWheelPlugin;
import org.jensoft.core.projection.Projection;
import org.jensoft.core.view.View;
import org.jensoft.core.view.background.ViewDarkBackground;
/**
 * <code>GeneralMetricsMarkerDemo</code>
 * 
 * @author Sébastien Janaud
 * 
 */
@JenSoftView(background = ViewDarkBackground.class,description="Core class to manage metrics along a path. Show metrics and marker along a path.",
					see={GaugeMetricsPath.class,GeneralMetricsPath.class})
public class GeneralMetricsMarkerDemo extends View {

	private static final long serialVersionUID = -7629261597049240113L;

	private Projection proj;
	/**
	 * create GeneralMetricsPath sample
	 */
	public GeneralMetricsMarkerDemo() {
		super(60);

		/*
		 create simple view
		 */
		createBaseView();
		
		/*
		 Create metrics path steps :
		  - make an instance and set the path range (min and max value)
		  - append metrics glyph on this path (metrics label and marker)
		  - set the path geometry to lay out metrics on the geometry path
		  
		  - use metrics path plug-in (in educational mode) or any paint process to render the solved metrics
		 	For example, General metrics path is used in gauge metrics path for gauge metrics rendering.
		 */
		
		createMetricsPath();	

	}

	private void createMetricsPath() {
		
		//**********************************************************************
		// Gauge development requires a good understanding of GeneralMetricsPath
		//**********************************************************************
		
		
		GeneralMetricsPath metricsPath = new GeneralMetricsPath();
		
		
		//default is false. with true reverse option, glyph will be reverted to being readable (read char from left to right)
		//depends on your needs. 
		//metricsPath.setAutoReverseGlyph(false);
		
		
		
		metricsPath.setMin(0);
		metricsPath.setMax(260);
		
		//the shape geometry is define in user coordinate, then the path is zoomable (for gauge, the projection is in device)
		metricsPath.setProjectionNature(ProjectionNature.USER);
		
		//Arc2D arc2D = new Arc2D.Double(-1000, -1000, 1200, 1200, 90, -260, Arc2D.OPEN);
		//Arc2D arc2D = new Arc2D.Double(-1000, -1000, 1200, 1200, 90, 260, Arc2D.OPEN);
		//Arc2D arc2D = new Arc2D.Double(-1000, -1000, 1200, 1200, 90, 90, Arc2D.OPEN);
		//Arc2D arc2D = new Arc2D.Double(-1000, -1000, 1200, 1200, 0, 180,Arc2D.OPEN);
		Arc2D arc2D = new Arc2D.Double(-1000, -1000, 1200, 1200, 90, 270, Arc2D.OPEN);
		//the arc 2D is define in user coordinate system, and will be projected in device system coordinate

		metricsPath.append(arc2D);

		Font f =  new Font("Dialog", Font.PLAIN, 16);
		
		
		//**********************
		//  GlyphMetrics on path
		//**********************
		
		// metrics 0
		GlyphMetric metric = new GlyphMetric();
		metric.setValue(0);

		metric.setStylePosition(StylePosition.Default);
		metric.setMetricsNature(GlyphMetricsNature.Major);
		metric.setMetricsLabel("0");
		// metric.lockReverse();
		metric.setDivergence(-20);
		metric.setGlyphMetricFill(new GlyphFill(Color.WHITE,
				PetalPalette.PETAL1_HC));
		metric.setFont(f);
		metricsPath.addMetric(metric);
		
		TriangleMarker triangleMarker = new TriangleMarker(NanoChromatique.WHITE, NanoChromatique.RED,
															5, 8,
															10, TriangleDirection.In);
		metric.setGlyphMetricMarkerPainter(triangleMarker);

		// other 0
		metric = new GlyphMetric();
		metric.setValue(0);
		metric.setStylePosition(StylePosition.Default);
		metric.setMetricsNature(GlyphMetricsNature.Major);
		metric.setMetricsLabel("0");
		// metric.lockReverse();
		metric.setDivergence(20);
		metric.setGlyphMetricFill(new GlyphFill(Color.WHITE,
				PetalPalette.PETAL1_HC));
		// metric.setGlyphMetricMarkerPainter(new
		// TicTacMarker(PetalPalette.PETAL1_HC));
		// metric.setGlyphMetricDraw(new ClassicGlyphDraw(Color.WHITE));
		metric.setFont(f);
		metricsPath.addMetric(metric);

		// metrics 60
		metric = new GlyphMetric();
		metric.setValue(60);
		// metric.setSide(Side.SideRight);
		metric.setStylePosition(StylePosition.Tangent);
		metric.setMetricsNature(GlyphMetricsNature.Major);
		metric.setMetricsLabel("60");
		// metric.lockReverse();
		metric.setDivergence(40);
		metric.setGlyphMetricFill(new GlyphFill(Color.WHITE,
				PetalPalette.PETAL2_HC));
		metric.setGlyphMetricMarkerPainter(new TicTacMarker(
				PetalPalette.PETAL2_HC));
		metric.setFont(f);
		// metric.setGlyphMetricDraw(new ClassicGlyphDraw(Color.WHITE));
		
		triangleMarker = new TriangleMarker(NanoChromatique.WHITE, NanoChromatique.RED,
				5, 14,
				60, TriangleDirection.In);
		metric.setGlyphMetricMarkerPainter(triangleMarker);
		metricsPath.addMetric(metric);

		// other metrics 60
		metric = new GlyphMetric();
		metric.setValue(60);
		// metric.setSide(Side.SideRight);
		metric.setStylePosition(StylePosition.Tangent);
		metric.setMetricsNature(GlyphMetricsNature.Major);
		metric.setMetricsLabel("60");
		// metric.lockReverse();
		metric.setDivergence(0);
		metric.setGlyphMetricFill(new GlyphFill(Color.WHITE,
				PetalPalette.PETAL2_HC));
		// metric.setGlyphMetricMarkerPainter(new
		// TicTacMarker(PetalPalette.PETAL2_HC));
		metric.setFont(f);
		// metric.setGlyphMetricDraw(new ClassicGlyphDraw(Color.WHITE));
		metricsPath.addMetric(metric);

		metric = new GlyphMetric();
		metric.setValue(80);
		// metric.setSide(Side.SideRight);
		metric.setStylePosition(StylePosition.Tangent);
		metric.setMetricsNature(GlyphMetricsNature.Major);
		metric.setMetricsLabel("80");
		// metric.lockReverse();
		metric.setDivergence(40);
		metric.setGlyphMetricFill(new GlyphFill(Color.WHITE,
				PetalPalette.PETAL3_HC));
		metric.setGlyphMetricMarkerPainter(new TicTacMarker(
				PetalPalette.PETAL3_HC));
		metric.setFont(f);
		// metric.setGlyphMetricDraw(new ClassicGlyphDraw(Color.WHITE));
		metricsPath.addMetric(metric);

		metric = new GlyphMetric();
		metric.setValue(100);

		metric.setStylePosition(StylePosition.Tangent);
		metric.setMetricsNature(GlyphMetricsNature.Major);
		metric.setMetricsLabel("100");
		// metric.lockReverse();
		metric.setDivergence(-20);
		metric.setGlyphMetricFill(new GlyphFill(Color.WHITE,
				PetalPalette.PETAL4_HC));
		metric.setGlyphMetricMarkerPainter(new TicTacMarker(
				PetalPalette.PETAL4_HC));
		metric.setFont(f);
		
		RectangleMarker rectMarker = new RectangleMarker(NanoChromatique.WHITE, NanoChromatique.RED,
				5, 8,
				10);
		metric.setGlyphMetricMarkerPainter(rectMarker);
		metricsPath.addMetric(metric);

		metric = new GlyphMetric();
		metric.setValue(120);
		// metric.setSide(Side.SideRight);
		metric.setStylePosition(StylePosition.Tangent);
		metric.setMetricsNature(GlyphMetricsNature.Major);
		metric.setMetricsLabel("120");
		// metric.lockReverse();
		metric.setDivergence(-20);
		metric.setGlyphMetricFill(new GlyphFill(Color.WHITE,
				PetalPalette.PETAL5_HC));
		metric.setGlyphMetricMarkerPainter(new TicTacMarker(
				PetalPalette.PETAL5_HC));
		metric.setFont(f);
		metricsPath.addMetric(metric);

		metric = new GlyphMetric();
		metric.setValue(140);
		// metric.setSide(Side.SideRight);
		metric.setStylePosition(StylePosition.Tangent);
		metric.setMetricsNature(GlyphMetricsNature.Major);
		metric.setMetricsLabel("140");
		// metric.lockReverse();
		metric.setDivergence(-20);
		metric.setGlyphMetricFill(new GlyphFill(Color.WHITE,
				PetalPalette.PETAL6_HC));
		metric.setGlyphMetricMarkerPainter(new TicTacMarker(
				PetalPalette.PETAL6_HC));
		metric.setFont(f);
		metricsPath.addMetric(metric);

		metric = new GlyphMetric();
		metric.setValue(160);
		// metric.setSide(Side.SideRight);
		metric.setStylePosition(StylePosition.Tangent);
		metric.setMetricsNature(GlyphMetricsNature.Major);
		metric.setMetricsLabel("160");
		metric.setDivergence(30);
		metric.setGlyphMetricFill(new GlyphFill(Color.WHITE,
				PetalPalette.PETAL7_HC));
		metric.setGlyphMetricMarkerPainter(new TicTacMarker(
				PetalPalette.PETAL7_HC));
		metric.setFont(f);
		metricsPath.addMetric(metric);

		metric = new GlyphMetric();
		metric.setValue(180);
		metric.setStylePosition(StylePosition.Radial);
		metric.setMetricsNature(GlyphMetricsNature.Major);
		metric.setMetricsLabel("180");
		metric.setDivergence(30);
		metric.setGlyphMetricFill(new GlyphFill(Color.WHITE,
				PetalPalette.PETAL8_HC));
		metric.setGlyphMetricMarkerPainter(new TicTacMarker(
				PetalPalette.PETAL8_HC));
		metric.setFont(f);
		metricsPath.addMetric(metric);

		metric = new GlyphMetric();
		metric.setValue(200);
		metric.setStylePosition(StylePosition.Tangent);
		metric.setMetricsNature(GlyphMetricsNature.Major);
		metric.setMetricsLabel("200");
		metric.setDivergence(30);
		metric.setGlyphMetricFill(new GlyphFill(Color.WHITE,
				PetalPalette.PETAL8_HC));
		metric.setGlyphMetricMarkerPainter(new TicTacMarker(
				PetalPalette.PETAL8_HC));
		metric.setFont(f);
		metricsPath.addMetric(metric);


		//to display path
		metricsPath.setPathPainter(new MetricsPathDefaultDraw(new Alpha(NanoChromatique.GREEN,150)));
		

		// this plugin is not really use in reality, only in this pedagogical
		// practices about GeneralPathMetrics (use in gauge development)
		MetricsPathPlugin generalMetricPainter = new MetricsPathPlugin(
				metricsPath);
		proj.registerPlugin(generalMetricPainter);
		
	}

	private void createBaseView() {
		proj = new Projection.Linear(-1800, 800, -1500, 300);
		proj.setThemeColor(NanoChromatique.GREEN.brighter());
		registerProjection(proj);

		Font f =  new Font("Dialog", Font.PLAIN, 12);
		
		AxisMetricsPlugin.ModeledMetrics westMetrics = new AxisMetricsPlugin.ModeledMetrics.W();
		proj.registerPlugin(westMetrics);
		westMetrics.setTextFont(f);

		AxisMetricsPlugin.ModeledMetrics eastMetrics = new AxisMetricsPlugin.ModeledMetrics.E();
		proj.registerPlugin(eastMetrics);
		eastMetrics.setTextFont(f);

		AxisMetricsPlugin.ModeledMetrics southMetrics = new AxisMetricsPlugin.ModeledMetrics.S();
		proj.registerPlugin(southMetrics);
		southMetrics.setTextFont(f);

		AxisMetricsPlugin.ModeledMetrics northMetrics = new AxisMetricsPlugin.ModeledMetrics.N();
		proj.registerPlugin(northMetrics);
		northMetrics.setTextFont(f);
		northMetrics.getMetricsManager().unlockLabel();

		proj.registerPlugin(new OutlinePlugin());

		TitleLegend legend = new TitleLegend("GeneralMetricsPath");
		legend.setFont(f);
		legend.setLegendFill(new TitleLegendGradientFill(NanoChromatique.WHITE,
				NanoChromatique.RED.brighter()));
		legend.setConstraints(new TitleLegendConstraints(LegendPosition.East, 0.2f,
				LegendAlignment.Rigth));
		TitleLegendPlugin lgendL = new TitleLegendPlugin(legend);
		proj.registerPlugin(lgendL);

		ZoomBoxPlugin zoomTool = new ZoomBoxPlugin();
		proj.registerPlugin(zoomTool);

		final TranslatePlugin tranlate = new TranslatePlugin();
		tranlate.registerContext(new TranslateDefaultDeviceContext());
		proj.registerPlugin(tranlate);
		tranlate.lockSelected();

		ZoomWheelPlugin zoomWheel = new ZoomWheelPlugin();
		proj.registerPlugin(zoomWheel);

		ZoomLensPlugin objectif = new ZoomLensPlugin();
		proj.registerPlugin(objectif);

		StripePlugin.MultiplierStripe stripePlugin = new StripePlugin.MultiplierStripe.H(0, 400);
		StripePalette bp = new StripePalette();
		bp.addPaint(new Color(255, 255, 255, 40));
		bp.addPaint(new Color(40, 40, 40, 40));
		stripePlugin.setStripePalette(bp);
		stripePlugin.setAlpha(0.3f);
		proj.registerPlugin(stripePlugin);

		GridPlugin.MultiplierGrid grids = new GridPlugin.MultiplierGrid(0, 400,
				GridOrientation.Horizontal);
		grids.setGridColor(new Color(59, 89, 152, 100));
		proj.registerPlugin(grids);

		GridPlugin.MultiplierGrid grids2 = new GridPlugin.MultiplierGrid(0, 400,
				GridOrientation.Vertical);
		grids2.setGridColor(new Color(59, 89, 152, 100));
		proj.registerPlugin(grids2);
		
	}
	
	public static void main(String[] args) {
		ViewFrameUI ui = new ViewFrameUI(new GeneralMetricsMarkerDemo());
	}

}
