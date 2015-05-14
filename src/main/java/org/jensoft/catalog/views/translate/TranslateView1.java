/*
 * JenSoft API - Charting Framework
 * http://www.jensoftapi.com
 * Copyright (c) JenSoft. All rights reserved.
 * See JenSoft Software License Agreement
 */
package org.jensoft.catalog.views.translate;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;

import org.jensoft.core.catalog.nature.JenSoftView;
import org.jensoft.core.graphics.Shader;
import org.jensoft.core.palette.color.Alpha;
import org.jensoft.core.palette.color.ColorPalette;
import org.jensoft.core.palette.color.FilPalette;
import org.jensoft.core.palette.color.RosePalette;
import org.jensoft.core.plugin.function.FunctionPlugin.CurveFunction;
import org.jensoft.core.plugin.function.FunctionToolkit;
import org.jensoft.core.plugin.function.curve.Curve;
import org.jensoft.core.plugin.function.curve.painter.draw.CurveDefaultDraw;
import org.jensoft.core.plugin.function.source.UserSourceFunction;
import org.jensoft.core.plugin.grid.Grid.GridOrientation;
import org.jensoft.core.plugin.grid.GridPlugin.MultiplierGrid;
import org.jensoft.core.plugin.legend.title.TitleLegend;
import org.jensoft.core.plugin.legend.title.TitleLegendPlugin;
import org.jensoft.core.plugin.metrics.AxisMetricsPlugin;
import org.jensoft.core.plugin.outline.OutlinePlugin;
import org.jensoft.core.plugin.stripe.StripePlugin.MultiplierStripe;
import org.jensoft.core.plugin.stripe.painter.StripePalette;
import org.jensoft.core.plugin.translate.TranslateCompassWidget;
import org.jensoft.core.plugin.translate.TranslateDefaultDeviceContext;
import org.jensoft.core.plugin.translate.TranslatePlugin;
import org.jensoft.core.plugin.translate.TranslateX;
import org.jensoft.core.plugin.translate.TranslateY;
import org.jensoft.core.projection.Projection;
import org.jensoft.core.view.View;
import org.jensoft.core.view.background.ViewDarkBackground;
import org.jensoft.core.plugin.stripe.StripePlugin;
import org.jensoft.core.plugin.grid.GridPlugin;

@JenSoftView(background=ViewDarkBackground.class,description="a translate (view 1) view which is use in translate synchronization demo, see SynchronizedTranslate Dashboard.",ignore=true)
public class TranslateView1 extends View {

	private static final long serialVersionUID = -2403797321867972563L;
	
	/**expose translate to dashboard*/
	private TranslatePlugin translate;

	public TranslateView1() {
		Projection proj = new Projection.Linear(-10, 10, -5, 20);
		registerProjection(proj);
		proj.registerPlugin(new OutlinePlugin());
		proj.setThemeColor(RosePalette.COALBLACK);
		TitleLegendPlugin legendPlugin = new TitleLegendPlugin();
		proj.registerPlugin(legendPlugin);

		TitleLegend l1 = TitleLegendPlugin.createLegend("Translate Box Synchronizer", RosePalette.COALBLACK, RosePalette.MANDARIN);
		legendPlugin.addLegend(l1);

		Font f = new Font("Dialog", Font.PLAIN, 12);
		
		// create modeled axis plug-in in south part
		AxisMetricsPlugin.ModeledMetrics southMetrics = new AxisMetricsPlugin.ModeledMetrics.S();
		proj.registerPlugin(southMetrics);
		southMetrics.setTextColor(RosePalette.LEMONPEEL);
		southMetrics.setMarkerColor(RosePalette.LEMONPEEL);
		southMetrics.setTextFont(f);
		

		// create modeled axis plug-in in west part
		AxisMetricsPlugin.ModeledMetrics westMetrics = new AxisMetricsPlugin.ModeledMetrics.W();
		proj.registerPlugin(westMetrics);
		westMetrics.setTextColor(RosePalette.LEMONPEEL);
		westMetrics.setMarkerColor(RosePalette.LEMONPEEL);
		westMetrics.setTextFont(f);
		

		MultiplierStripe stripes = new StripePlugin.MultiplierStripe.H(0, 2.5);
		StripePalette bp = new StripePalette();
		bp.addPaint(new Color(255, 255, 255, 20));
		bp.addPaint(ColorPalette.alpha(FilPalette.GREEN4, 20));
		stripes.setStripePalette(bp);
		proj.registerPlugin(stripes);

		MultiplierGrid gridLayout = new GridPlugin.MultiplierGrid(0, 2.5, GridOrientation.Horizontal);
		gridLayout.setGridColor(new Color(255, 255, 255, 60));
		proj.registerPlugin(gridLayout);

		MultiplierGrid gridLayout2 = new GridPlugin.MultiplierGrid(0, 2.5, GridOrientation.Vertical);
		gridLayout2.setGridColor(new Color(255, 255, 255, 60));
		proj.registerPlugin(gridLayout2);

		translate = new TranslatePlugin();
		translate.setThemeColor(RosePalette.INDIGO);
		translate.registerContext(new TranslateDefaultDeviceContext());

		TranslateCompassWidget compass = new TranslateCompassWidget();
		compass.setRingFillColor(new Alpha(RosePalette.COBALT, 250));
		// compass.setRingFillColor(null);
		// compass.setRingDrawColor(new Alpha(RosePalette.COALBLACK, 200));
		// compass.setRingDrawStroke(new BasicStroke(1.5f));
		translate.registerWidget(compass);

		TranslateX tx = new TranslateX(80, 14);
		TranslateY ty = new TranslateY(14, 80);
		tx.setOutlineColor(ColorPalette.BLACK);
		tx.setOutlineStroke(new BasicStroke(1.2f));
		tx.setButtonFillColor(RosePalette.CALYPSOBLUE);
		tx.setButtonDrawColor(null);
		tx.setShader(new Shader(new float[] { 0f, 1f }, new Color[] { new Color(30, 30, 30, 140), new Color(10, 10, 10, 255) }));
		ty.setOutlineColor(ColorPalette.BLACK);
		ty.setOutlineStroke(new BasicStroke(1.2f));
		ty.setButtonFillColor(RosePalette.CALYPSOBLUE);
		ty.setButtonDrawColor(null);
		ty.setShader(new Shader(new float[] { 0f, 1f }, new Color[] { new Color(30, 30, 30, 140), new Color(10, 10, 10, 255) }));
		translate.registerWidget(tx, ty);

		proj.registerPlugin(translate);
		
		// source function
		double[] xValues = {-10,-5, -2,0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
		double[] yValues = {8, 1, 3,6, 1.8, 15, 1.9, 3.4, 6.1, 4.2, 8.5, 9.9, 12, 8 };
		UserSourceFunction source1 = new UserSourceFunction.SplineSource(xValues, yValues, 0.1);

		// curve function
		CurveFunction curveFunctions = new CurveFunction();
		proj.registerPlugin(curveFunctions);

		Curve curve = FunctionToolkit.createCurveFunction(source1, RosePalette.LEMONPEEL, new CurveDefaultDraw());
		curveFunctions.addFunction(curve);
	}

	/**
	 * @return the translate
	 */
	public TranslatePlugin getTranslate() {
		return translate;
	}

}
