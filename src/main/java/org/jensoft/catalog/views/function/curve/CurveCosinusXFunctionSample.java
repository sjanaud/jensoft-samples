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
import org.jensoft.core.glyphmetrics.painter.marker.RoundMarker;
import org.jensoft.core.palette.color.ColorPalette;
import org.jensoft.core.palette.color.FilPalette;
import org.jensoft.core.palette.color.NanoChromatique;
import org.jensoft.core.palette.color.PetalPalette;
import org.jensoft.core.palette.color.RosePalette;
import org.jensoft.core.plugin.function.FunctionPlugin.CurveFunction;
import org.jensoft.core.plugin.function.FunctionToolkit;
import org.jensoft.core.plugin.function.curve.Curve;
import org.jensoft.core.plugin.function.curve.painter.draw.CurveDefaultDraw;
import org.jensoft.core.plugin.function.source.FunctionNature;
import org.jensoft.core.plugin.function.source.MathSourceFunction;
import org.jensoft.core.plugin.function.source.MathSourceFunction.MathFunction;
import org.jensoft.core.plugin.function.source.SourceFunction;
import org.jensoft.core.plugin.function.tools.sourcetracker.SourceTrackerDeviceContext;
import org.jensoft.core.plugin.function.tools.sourcetracker.SourceTrackerPlugin;
import org.jensoft.core.plugin.grid.Grid.GridOrientation;
import org.jensoft.core.plugin.grid.GridPlugin;
import org.jensoft.core.plugin.grid.GridPlugin.FreeGrid;
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
import org.jensoft.core.view.View;
import org.jensoft.core.view.background.ViewDarkBackground;

/**
 * <code>CurveCosinusXFunctionSample</code>
 * 
 * @author Sébastien Janaud
 */
@JenSoftView(background=ViewDarkBackground.class,description="Show a custom math x source function")
public class CurveCosinusXFunctionSample extends View {

	private static final long serialVersionUID = 4896866904573672022L;

	/**
	 * create curve function sample view
	 */
	public CurveCosinusXFunctionSample() {
		super();
		setPlaceHolder(40);

		// proj projection
		Projection proj = new Projection.Linear(0, 10, -1.5, 1.5);
		registerProjection(proj);
		proj.setThemeColor(RosePalette.CORALRED);

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

		// Source function
		SourceFunction cosSourceFunction = new MathSourceFunction(MathFunction.cos, 0.1, FunctionNature.XFunction);

		// Curve function
		Curve curve = FunctionToolkit.createCurveFunction(cosSourceFunction, NanoChromatique.BLUE, new CurveDefaultDraw());
		curveFunctions.addFunction(curve);

		Font f =  new Font("Dialog", Font.PLAIN, 12);
		
		// metrics on curve function
		GlyphMetric metric = new GlyphMetric();
		metric.setValue(2.5);
		metric.setStylePosition(StylePosition.Tangent);
		metric.setMetricsLabel("2.5");
		metric.setDivergence(10);
		metric.setGlyphMetricFill(new GlyphFill(Color.WHITE, PetalPalette.PETAL1_HC));
		metric.setGlyphMetricMarkerPainter(new RoundMarker(PetalPalette.PETAL1_HC, Color.white));
		metric.setFont(f);
		curve.addMetricsLabel(metric);

		// source tracker
		final SourceTrackerPlugin sourceTracker = new SourceTrackerPlugin();
		sourceTracker.registerSourceFunction(cosSourceFunction);
		sourceTracker.registerContext(new SourceTrackerDeviceContext());
		proj.registerPlugin(sourceTracker);
		sourceTracker.setGlyphMetricsFill(new GlyphFill(Color.WHITE, FilPalette.PINK1));

		// legend
		TitleLegend legend = new TitleLegend("Cosinus X Function");
		legend.setFont(f);
		legend.setLegendFill(new TitleLegendGradientFill(NanoChromatique.BLUE, NanoChromatique.DARK_GRAY));
		legend.setConstraints(new TitleLegendConstraints(LegendPosition.East, 0.2f, LegendAlignment.Rigth));
		TitleLegendPlugin legendPlugin = new TitleLegendPlugin(legend);
		proj.registerPlugin(legendPlugin);

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

		// stripe
		MultiplierStripe stripePlugin = new StripePlugin.MultiplierStripe.H(0, 2.5);
		StripePalette bp = new StripePalette();
		bp.addPaint(new Color(255, 255, 255, 5));
		bp.addPaint(ColorPalette.alpha(FilPalette.ORANGE1, 20));
		stripePlugin.setStripePalette(bp);
		stripePlugin.setAlpha(0.3f);
		proj.registerPlugin(stripePlugin);

		// grids
		MultiplierGrid gridLayout = new GridPlugin.MultiplierGrid(0, 2.5, GridOrientation.Horizontal);
		gridLayout.setGridColor(new Color(59, 89, 152, 100));
		proj.registerPlugin(gridLayout);

		MultiplierGrid gridLayout2 = new GridPlugin.MultiplierGrid(0, 2.5, GridOrientation.Vertical);
		gridLayout2.setGridColor(new Color(59, 89, 152, 100));
		proj.registerPlugin(gridLayout2);

		FreeGrid gridLayoutLimit = new GridPlugin.FreeGrid(GridOrientation.Horizontal);
		gridLayoutLimit.setGridColor(RosePalette.EMERALD);
		gridLayoutLimit.addGrid(1);
		gridLayoutLimit.addGrid(-1);
		proj.registerPlugin(gridLayoutLimit);

	}

}
