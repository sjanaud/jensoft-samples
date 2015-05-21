/*
 * JenSoft API - Charting Framework
 * http://www.jensoftapi.com
 * Copyright (c) JenSoft. All rights reserved.
 * See JenSoft Software License Agreement
 */
package org.jensoft.catalog.views.function.curve;
import java.awt.Color;
import java.awt.Font;

import org.jensoft.core.catalog.nature.JenSoftView;
import org.jensoft.core.glyphmetrics.GlyphMetric;
import org.jensoft.core.glyphmetrics.StylePosition;
import org.jensoft.core.glyphmetrics.painter.fill.GlyphFill;
import org.jensoft.core.glyphmetrics.painter.marker.TicTacMarker;
import org.jensoft.core.palette.color.ColorPalette;
import org.jensoft.core.palette.color.FilPalette;
import org.jensoft.core.palette.color.PetalPalette;
import org.jensoft.core.palette.color.RosePalette;
import org.jensoft.core.palette.color.Spectral;
import org.jensoft.core.plugin.background.DeviceGradientBackgroundPlugin;
import org.jensoft.core.plugin.background.DeviceNightBackground;
import org.jensoft.core.plugin.function.FunctionPlugin.CurveFunction;
import org.jensoft.core.plugin.function.FunctionToolkit;
import org.jensoft.core.plugin.function.curve.Curve;
import org.jensoft.core.plugin.function.curve.painter.draw.CurveDefaultDraw;
import org.jensoft.core.plugin.function.source.UserSourceFunction;
import org.jensoft.core.plugin.grid.Grid.GridOrientation;
import org.jensoft.core.plugin.grid.GridPlugin;
import org.jensoft.core.plugin.grid.GridPlugin.MultiplierGrid;
import org.jensoft.core.plugin.legend.title.TitleLegend;
import org.jensoft.core.plugin.legend.title.TitleLegendConstraints;
import org.jensoft.core.plugin.legend.title.TitleLegendConstraints.LegendAlignment;
import org.jensoft.core.plugin.legend.title.TitleLegendConstraints.LegendPosition;
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
import org.jensoft.core.view.View;
import org.jensoft.core.view.background.ViewDarkBackground;
/**
 * <code>CurveSplineXFunctionLabelSample</code>
 * 
 * @author Sébastien Janaud
 */
@JenSoftView(background = ViewDarkBackground.class, description = "Show how to use curve spline x function with labels")
public class CurveSplineXFunctionLabelSample extends View {

	private static final long serialVersionUID = -5790778805236269829L;

	/**
	 * create curve function sample view
	 */
	public CurveSplineXFunctionLabelSample() {
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

		// legend
		Font f =  new Font("Dialog", Font.PLAIN, 12);
		TitleLegend legend = new TitleLegend("Curve Label");
		legend.setFont(f);
		legend.setLegendFill(new TitleLegendGradientFill(FilPalette.BLUE15,
				FilPalette.BLUE1));
		legend.setConstraints(new TitleLegendConstraints(LegendPosition.South, 0.8f,
				LegendAlignment.Rigth));

		// source function
		double[] xValues = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
		double[] yValues = { 6, 1.8, 15, 1.9, 3.4, 6.1, 4.2, 8.5, 9.9, 12, 8 };
		UserSourceFunction source1 = new UserSourceFunction.SplineSource(
				xValues, yValues, 0.1);

		// curve function
		CurveFunction curveFunctions = new CurveFunction();
		proj.registerPlugin(curveFunctions);

		Curve curve = FunctionToolkit.createCurveFunction(source1,
				Spectral.SPECTRAL_BLUE2, new CurveDefaultDraw());
		curveFunctions.addFunction(curve);

		// glyph metrics
		//
		GlyphMetric g1 = new GlyphMetric();
		g1.setValue(5.3);
		g1.setGlyphMetricFill(new GlyphFill(Color.WHITE, PetalPalette.PETAL3_HC));
		// g1.setFont(InputFonts.getFont(InputFonts.NEUROPOL,12));
		g1.setMetricsLabel("MARKER 1");
		g1.setStylePosition(StylePosition.Default);
		g1.setDivergence(15);
		g1.setGlyphMetricMarkerPainter(new TicTacMarker(PetalPalette.PETAL3_HC));
		curve.addMetricsLabel(g1);

		GlyphMetric g2 = new GlyphMetric();
		g2.setValue(2.5);
		g2.setGlyphMetricFill(new GlyphFill(FilPalette.PINK1,
				FilPalette.PURPLE1));
		// g1.setFont(InputFonts.getFont(InputFonts.NEUROPOL,12));
		g2.setMetricsLabel("MARKER 2");
		g2.setStylePosition(StylePosition.Tangent);
		g2.setDivergence(15);
		g2.setGlyphMetricMarkerPainter(new TicTacMarker(FilPalette.PINK1, 3));
		curve.addMetricsLabel(g2);

		GlyphMetric g3 = new GlyphMetric();
		g3.setValue(7);
		g3.setGlyphMetricFill(new GlyphFill(FilPalette.BLUE15, FilPalette.BLUE1));
		// g1.setFont(InputFonts.getFont(InputFonts.NEUROPOL,12));
		g3.setMetricsLabel("MARKER 3");
		g3.setStylePosition(StylePosition.Radial);
		g3.setDivergence(15);
		g3.setGlyphMetricMarkerPainter(new TicTacMarker(FilPalette.PINK1, 1, 0,
				10));
		curve.addMetricsLabel(g3);

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

		// simple device background gradient
		DeviceGradientBackgroundPlugin night = new DeviceNightBackground();
		night.setAlpha(1f);
		proj.registerPlugin(night);

		// stripe
		MultiplierStripe stripePlugin = new StripePlugin.MultiplierStripe.H(0,2.5);
				
		StripePalette bp = new StripePalette();
		bp.addPaint(new Color(255, 255, 255, 5));
		bp.addPaint(ColorPalette.alpha(FilPalette.GREEN2, 20));
		stripePlugin.setStripePalette(bp);
		stripePlugin.setAlpha(0.3f);
		proj.registerPlugin(stripePlugin);

		MultiplierGrid gridLayout = new GridPlugin.MultiplierGrid(0, 2.5,GridOrientation.Horizontal);
		gridLayout.setGridColor(new Color(59, 89, 152, 100));
		proj.registerPlugin(gridLayout);

		MultiplierGrid gridLayout2 = new GridPlugin.MultiplierGrid(0, 2.5,GridOrientation.Vertical);
		gridLayout2.setGridColor(new Color(59, 89, 152, 100));
		proj.registerPlugin(gridLayout2);
	}
}
