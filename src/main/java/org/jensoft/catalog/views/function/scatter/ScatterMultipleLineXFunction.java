/*
 * JenSoft API - Charting Framework
 * http://www.jensoftapi.com
 * Copyright (c) JenSoft. All rights reserved.
 * See JenSoft Software License Agreement
 */
package org.jensoft.catalog.views.function.scatter;

import java.awt.Color;
import java.awt.Font;

import org.jensoft.core.catalog.nature.JenSoftView;
import org.jensoft.core.palette.color.RosePalette;
import org.jensoft.core.palette.color.Spectral;
import org.jensoft.core.plugin.function.FunctionPlugin.ScatterFunction;
import org.jensoft.core.plugin.function.scatter.Scatter;
import org.jensoft.core.plugin.function.scatter.morphe.EllipseMorphe;
import org.jensoft.core.plugin.function.scatter.morphe.QInverseMorphe;
import org.jensoft.core.plugin.function.scatter.morphe.QStarMorphe;
import org.jensoft.core.plugin.function.scatter.painter.fill.ScatterDefaultFill;
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
import org.jensoft.core.plugin.zoom.box.ZoomBoxDonutWidget;
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
 * <code>ScatterMultipleLineXFunction</code>
 * 
 * @author Sébastien Janaud
 */
@JenSoftView(background=ViewDarkBackground.class,description="Shwo to use multiple scatter line x function")
public class ScatterMultipleLineXFunction extends View {

	private static final long serialVersionUID = -5833555420516809887L;
	
	/** font */
	private Font font = new Font("lucida console", Font.PLAIN, 10);

	/**
	 * Create scatter view
	 */
	public ScatterMultipleLineXFunction() {
		super();

		// proj projection
		Projection proj = new Projection.Linear(0, 10, 0, 18);
		registerProjection(proj);

		// device outline plug-in
		proj.registerPlugin(new OutlinePlugin(RosePalette.EMERALD));

		// create modeled axis plug-in in south part
		AxisMetricsPlugin.ModeledMetrics southMetrics = new AxisMetricsPlugin.ModeledMetrics.S();
		proj.registerPlugin(southMetrics);
		southMetrics.setTextFont(font);
		

		// create modeled axis plug-in in west part
		AxisMetricsPlugin.ModeledMetrics westMetrics = new AxisMetricsPlugin.ModeledMetrics.W();
		proj.registerPlugin(westMetrics);
		westMetrics.setTextFont(font);
		

		// scatter function plug-in
		ScatterFunction scatterPlugin = new ScatterFunction();
		proj.registerPlugin(scatterPlugin);

		// source functions
		double[] xValues1 = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
		double[] yValues1 = { 6, 1.8, 15, 1.9, 3.4, 6.1, 4.2, 8.5, 9.9, 12, 8 };

		double[] xValues2 = { 0, 1, 2, 3, 4, 5.2, 6, 7, 8, 9, 10 };
		double[] yValues2 = { 3, 1, 5, 4, 4.8, 7.3, 2, 3, 7, 10, 6 };

		double[] xValues3 = { 0, 1.4, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
		double[] yValues3 = { 0.4, 7.5, 5, 1, 2.8, 5, 7, 9, 2, 4, 1 };

		UserSourceFunction source1 = new UserSourceFunction.LineSource(xValues1, yValues1);
		UserSourceFunction source2 = new UserSourceFunction.LineSource(xValues2, yValues2);
		UserSourceFunction source3 = new UserSourceFunction.LineSource(xValues3, yValues3);

		// scatters functions
		Scatter scatter1 = new Scatter(source1);
		scatter1.setThemeColor(Spectral.SPECTRAL_GREEN.brighter());
		scatter1.setScatterFill(new ScatterDefaultFill());
		scatter1.setScatterMorphe(new QInverseMorphe(5, 3));

		Scatter scatter2 = new Scatter(source2);
		scatter2.setThemeColor(Spectral.SPECTRAL_RED.brighter());
		scatter2.setScatterFill(new ScatterDefaultFill());
		scatter2.setScatterMorphe(new QStarMorphe(3, 6, 5));

		Scatter scatter3 = new Scatter(source3);
		scatter3.setThemeColor(Spectral.SPECTRAL_YELLOW.brighter());
		scatter3.setScatterFill(new ScatterDefaultFill());
		scatter3.setScatterMorphe(new EllipseMorphe(8, 8));

		scatterPlugin.addFunction(scatter1);
		scatterPlugin.addFunction(scatter2);
		scatterPlugin.addFunction(scatter3);

		// legend plug-in
		Font f =  new Font("Dialog", Font.PLAIN, 12);
		TitleLegend legend = new TitleLegend("Multiple Line X Scatter");
		legend.setLegendFill(new TitleLegendGradientFill(Color.WHITE, RosePalette.INDIGO.brighter()));
		legend.setFont(f);
		legend.setConstraints(new TitleLegendConstraints(LegendPosition.East, 0.3f, LegendAlignment.Rigth));
		TitleLegendPlugin legendPlugin = new TitleLegendPlugin(legend);
		proj.registerPlugin(legendPlugin);

		// stripe plug in
		MultiplierStripe stripePlugin = new StripePlugin.MultiplierStripe.H(0, 2.5);
		StripePalette bp = new StripePalette();
		bp.addPaint(new Color(255, 255, 255, 40));
		bp.addPaint(new Color(40, 40, 40, 40));
		stripePlugin.setStripePalette(bp);
		stripePlugin.setAlpha(0.3f);
		proj.registerPlugin(stripePlugin);

		// grid plug-in
		MultiplierGrid gridLayout = new GridPlugin.MultiplierGrid(0, 2.5, GridOrientation.Horizontal);
		gridLayout.setGridColor(new Color(59, 89, 152, 100));
		proj.registerPlugin(gridLayout);

		MultiplierGrid gridLayout2 = new GridPlugin.MultiplierGrid(0, 2.5, GridOrientation.Vertical);
		gridLayout2.setGridColor(new Color(59, 89, 152, 100));
		proj.registerPlugin(gridLayout2);

		// zoom box plug-in
		final ZoomBoxPlugin zoomPlugin = new ZoomBoxPlugin();
		zoomPlugin.setZoomBoxDrawColor(RosePalette.LEMONPEEL);
		zoomPlugin.setZoomBoxFillColor(RosePalette.LAPISBLUE);
		zoomPlugin.registerContext(new ZoomBoxDefaultDeviceContext());
		zoomPlugin.registerWidget(new ZoomBoxDonutWidget());
		proj.registerPlugin(zoomPlugin);

		// zoom wheel
		ZoomWheelPlugin wheelPlugin = new ZoomWheelPlugin();
		proj.registerPlugin(wheelPlugin);

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
}
