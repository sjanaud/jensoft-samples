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
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import org.jensoft.core.catalog.nature.JenSoftView;
import org.jensoft.core.glyphmetrics.GlyphMetric;
import org.jensoft.core.glyphmetrics.StylePosition;
import org.jensoft.core.glyphmetrics.painter.fill.GlyphFill;
import org.jensoft.core.glyphmetrics.painter.marker.RoundMarker;
import org.jensoft.core.palette.color.ColorPalette;
import org.jensoft.core.palette.color.FilPalette;
import org.jensoft.core.palette.color.TangoPalette;
import org.jensoft.core.plugin.function.FunctionPlugin.AreaFunction;
import org.jensoft.core.plugin.function.area.Area;
import org.jensoft.core.plugin.function.area.painter.draw.AreaDefaultDraw;
import org.jensoft.core.plugin.function.area.painter.fill.AreaGradientFill;
import org.jensoft.core.plugin.function.source.UserSourceFunction;
import org.jensoft.core.plugin.grid.Grid.GridOrientation;
import org.jensoft.core.plugin.grid.GridPlugin;
import org.jensoft.core.plugin.metrics.AxisMetricsPlugin;
import org.jensoft.core.plugin.stripe.StripePlugin;
import org.jensoft.core.plugin.stripe.painter.StripePalette;
import org.jensoft.core.plugin.translate.TranslatePlugin;
import org.jensoft.core.plugin.zoom.box.ZoomBoxPlugin;
import org.jensoft.core.plugin.zoom.wheel.ZoomWheelPlugin;
import org.jensoft.core.projection.Projection;
import org.jensoft.core.view.View;
import org.jensoft.core.view.background.ViewImageBackground;

/**
 * <code>ViewImage</code>
 * 
 * @author Sébastien Janaud
 */
@JenSoftView(description="Display view image background")
public class ViewImage extends View {

	/** demo font */
	private Font font = new Font("lucida console", Font.PLAIN, 10);

	/**
	 * create a demo with an image background for the entire view background
	 */
	public ViewImage() {
		super();

		// proj projection
		Projection proj = new Projection.Linear(0, 10, 0, 18);
		registerProjection(proj);

		// create modeled axis plug-in in south part
		AxisMetricsPlugin.ModeledMetrics southMetrics = new AxisMetricsPlugin.ModeledMetrics.S();
		proj.registerPlugin(southMetrics);
		southMetrics.setTextFont(font);
		

		// create modeled axis plug-in in west part
		AxisMetricsPlugin.ModeledMetrics westMetrics = new AxisMetricsPlugin.ModeledMetrics.W();
		proj.registerPlugin(westMetrics);
		westMetrics.setTextFont(font);
		

		// area curve function plug-in
		AreaFunction areaFunctions = new AreaFunction();
		proj.registerPlugin(areaFunctions);

		
		

		// source function
		double[] xValues1 = { 0, 1, 2, 3, 4, 5.2, 6, 7, 8, 9, 10 };
		double[] yValues1 = { 3, 1, 5, 4, 4.8, 7.3, 2, 3, 7, 10, 6 };
		UserSourceFunction source = new UserSourceFunction.SplineSource(xValues1, yValues1, 0.1);

		// area curve function
		Area areaCurve = new Area(source);
		areaCurve.setAreaFill(new AreaGradientFill());
		areaCurve.setAreaDraw(new AreaDefaultDraw(Color.WHITE, new BasicStroke(1f)));
		areaCurve.setThemeColor(FilPalette.GREEN4);
		areaCurve.setAreaBase(0);
		areaFunctions.addFunction(areaCurve);

		Font f = new Font("Dialog", Font.PLAIN, 14);
		
		// glyph for curve functions
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

		// grid plug-in
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
		
		// view background is not a plugin, it's just a background for the whole view.
				
				InputStream iis = null;
				try {
					iis = this.getClass().getResourceAsStream("winter.jpg");
					BufferedImage image = ImageIO.read(iis);
					ViewImageBackground imageBackground = new ViewImageBackground(image);
					setBackgroundPainter(imageBackground);
				} catch (IOException e) {
					e.printStackTrace();
					System.err.println("loaded image failed :"+e.getMessage());
				}finally{
					if(iis != null)
						try {
							iis.close();
						} catch (IOException e) {}
				}

	}

}
