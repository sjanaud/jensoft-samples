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
import java.awt.Stroke;

import org.jensoft.core.catalog.nature.JenSoftView;
import org.jensoft.core.glyphmetrics.GlyphMetric;
import org.jensoft.core.glyphmetrics.GlyphMetricsNature;
import org.jensoft.core.glyphmetrics.StylePosition;
import org.jensoft.core.glyphmetrics.painter.fill.GlyphFill;
import org.jensoft.core.glyphmetrics.painter.marker.TicTacMarker;
import org.jensoft.core.graphics.Shader;
import org.jensoft.core.palette.color.ColorPalette;
import org.jensoft.core.palette.color.FilPalette;
import org.jensoft.core.palette.color.NanoChromatique;
import org.jensoft.core.palette.color.RosePalette;
import org.jensoft.core.palette.color.TangoPalette;
import org.jensoft.core.plugin.function.FunctionPlugin;
import org.jensoft.core.plugin.function.FunctionPlugin.AreaFunction;
import org.jensoft.core.plugin.function.FunctionPlugin;
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
 * <code>AreaSplineXFunctionStroking</code>
 * 
 * @author Sébastien Janaud
 */
@JenSoftView(background=ViewDarkBackground.class,description="Area function strocking")
public class AreaSplineXFunctionStroking extends View {

	private static final long serialVersionUID = -6181044446849227435L;

	/**
	 * Create area function with styled strokes
	 */
	public AreaSplineXFunctionStroking() {
		super(50);

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
		

		// area functions plug-in
		AreaFunction areaPlugin = new FunctionPlugin.AreaFunction();
		proj.registerPlugin(areaPlugin);

		// sources functions
		double[] xValues1 = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
		double[] yValues1 = { 2, 1.8, 1.9, 15, 0.4, 1.4, 1.2, 0.2, 0.6, 0.4, 0.7 };

		double[] xValues2 = { 0, 1, 2, 3, 4, 5.2, 6, 7, 8, 9, 10 };
		double[] yValues2 = { 3, 1, 5, 4, 4.8, 7.3, 2, 3, 7, 10, 6 };

		double[] xValues3 = { 0, 1.4, 2, 3, 4, 5, 6, 6.5, 7, 8, 9, 10 };
		double[] yValues3 = { 0.4, 7.5, 5, 1, 2.8, 1, 4, 7, 9, 2, 4, 1 };

		UserSourceFunction source1 = new UserSourceFunction.SplineSource(xValues1, yValues1, 0.1);
		UserSourceFunction source2 = new UserSourceFunction.SplineSource(xValues2, yValues2, 0.1);
		UserSourceFunction source3 = new UserSourceFunction.SplineSource(xValues3, yValues3, 0.1);

		// strokes
		Stroke sDash1 = new BasicStroke(2f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_ROUND, 10, new float[] { 40, 5, 2, 5 }, 0);
		Stroke sDash2 = new BasicStroke(2f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_ROUND, 10, new float[] { 20, 8, 10, 8 }, 0);
		Stroke sDash3 = new BasicStroke(2f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_ROUND, 10, new float[] { 6, 3, 6, 3 }, 0);

		// areas functions
		Area area1 = new Area(source1);
		area1.setThemeColor(NanoChromatique.BLUE);
		area1.setAreaDraw(new AreaDefaultDraw(Color.WHITE, sDash1));
		area1.setAreaFill(new AreaGradientFill());
		area1.setAreaBase(-3);

		Area area2 = new Area(source2);
		area2.setThemeColor(NanoChromatique.GREEN);
		area2.setAreaDraw(new AreaDefaultDraw(Color.WHITE, sDash2));
		area2.setAreaFill(new AreaGradientFill());
		area2.setAreaBase(-3);

		Area area3 = new Area(source3);
		area3.setThemeColor(RosePalette.SAGE);
		area3.setAreaDraw(new AreaDefaultDraw(Color.WHITE, sDash3));
		area3.setAreaFill(new AreaGradientFill());
		area3.setAreaBase(-3);

		areaPlugin.addFunction(area1);
		areaPlugin.addFunction(area2);
		areaPlugin.addFunction(area3);

		
		Font f =  new Font("Dialog", Font.PLAIN, 12);
		
		
		// glyph metrics on area functions
		GlyphMetric metric = new GlyphMetric();
		metric.setValue(4.04);
		metric.setStylePosition(StylePosition.Default);
		metric.setMetricsNature(GlyphMetricsNature.Median);
		metric.setMetricsLabel("4.04");
		metric.setDivergence(20);
		metric.setGlyphMetricFill(new GlyphFill(Color.WHITE, FilPalette.GREEN4));
		metric.setGlyphMetricMarkerPainter(new TicTacMarker(FilPalette.GREEN5));
		metric.setFont(f);
		area2.addMetricsLabel(metric);

		metric = new GlyphMetric();
		metric.setValue(8.2);
		metric.setStylePosition(StylePosition.Default);
		metric.setMetricsLabel("8.2");
		metric.setDivergence(30);
		metric.setGlyphMetricFill(new GlyphFill(Color.WHITE, FilPalette.GREEN4));
		metric.setGlyphMetricMarkerPainter(new TicTacMarker(FilPalette.GREEN5));
		metric.setFont(f);
		area2.addMetricsLabel(metric);

		metric = new GlyphMetric();
		metric.setValue(5.5);
		metric.setStylePosition(StylePosition.Default);
		metric.setMetricsLabel("5.5");
		metric.setDivergence(20);
		metric.setGlyphMetricFill(new GlyphFill(Color.WHITE, FilPalette.BLUE14));
		metric.setGlyphMetricMarkerPainter(new TicTacMarker(FilPalette.BLUE14));
		metric.setFont(f);
		area3.addMetricsLabel(metric);

		metric = new GlyphMetric();
		metric.setValue(2.3);
		metric.setStylePosition(StylePosition.Default);
		metric.setMetricsLabel("2.3");
		metric.setDivergence(30);
		metric.setGlyphMetricFill(new GlyphFill(Color.WHITE, FilPalette.BLUE14));
		metric.setGlyphMetricMarkerPainter(new TicTacMarker(FilPalette.BLUE14));
		metric.setFont(f);
		area3.addMetricsLabel(metric);

		// legend plug-in
		Font fl =  new Font("Dialog", Font.PLAIN, 10);
		TitleLegend legend = new TitleLegend("Area Stroking");
		legend.setLegendFill(new TitleLegendGradientFill(Color.WHITE, RosePalette.EMERALD));
		legend.setFont(fl);
		legend.setConstraints(new TitleLegendConstraints(LegendPosition.East, 0.3f, LegendAlignment.Rigth));
		TitleLegendPlugin legendPlugin = new TitleLegendPlugin(legend);
		proj.registerPlugin(legendPlugin);

		// stripe plug-in
		MultiplierStripe stripePlugin = new StripePlugin.MultiplierStripe.H(0, 2.5);
		StripePalette bp = new StripePalette();
		bp.addPaint(new Color(255, 255, 255, 20));
		bp.addPaint(ColorPalette.alpha(TangoPalette.ORANGE3, 40));
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
	@Portfolio(name = "Function-Area-Stroking", width = 600, height = 400)
	public static View getPortofolio() {
		AreaSplineXFunctionStroking demo = new AreaSplineXFunctionStroking();

		ViewDefaultBackground viewBackground = new ViewDefaultBackground();
		Shader s = new Shader(new float[] { 0f, 1f }, new Color[] { new Color(32, 39, 55), Color.BLACK });
		viewBackground.setShader(s);
		viewBackground.setOutlineStroke(new BasicStroke(2.5f));

		demo.setBackgroundPainter(viewBackground);
		return demo;
	}

}
