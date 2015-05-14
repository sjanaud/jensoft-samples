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

import org.jensoft.core.catalog.nature.JenSoftView;
import org.jensoft.core.palette.color.ColorPalette;
import org.jensoft.core.palette.color.JennyPalette;
import org.jensoft.core.palette.color.NanoChromatique;
import org.jensoft.core.palette.color.PetalPalette;
import org.jensoft.core.palette.color.RosePalette;
import org.jensoft.core.palette.color.TangoPalette;
import org.jensoft.core.plugin.function.FunctionPlugin.AreaFunction;
import org.jensoft.core.plugin.function.FunctionPlugin;
import org.jensoft.core.plugin.function.area.painter.draw.AreaDefaultDraw;
import org.jensoft.core.plugin.function.area.painter.fill.AreaGradientFill;
import org.jensoft.core.plugin.function.source.UserSourceFunction;
import org.jensoft.core.plugin.grid.Grid.GridOrientation;
import org.jensoft.core.plugin.grid.GridPlugin.MultiplierGrid;
import org.jensoft.core.plugin.legend.title.TitleLegend;
import org.jensoft.core.plugin.legend.title.TitleLegendConstraints;
import org.jensoft.core.plugin.legend.title.TitleLegendConstraints.LegendAlignment;
import org.jensoft.core.plugin.legend.title.TitleLegendConstraints.LegendPosition;
import org.jensoft.core.plugin.legend.title.TitleLegendPlugin;
import org.jensoft.core.plugin.legend.title.painter.fil.TitleLegendGradientFill;
import org.jensoft.core.plugin.metrics.AxisMetricsPlugin;
import org.jensoft.core.plugin.outline.OutlinePlugin;
import org.jensoft.core.plugin.stripe.StripePlugin.MultiplierStripe;
import org.jensoft.core.plugin.stripe.painter.StripePalette;
import org.jensoft.core.plugin.zoom.box.ZoomBoxDefaultDeviceContext;
import org.jensoft.core.plugin.zoom.box.ZoomBoxDonutWidget;
import org.jensoft.core.plugin.zoom.box.ZoomBoxPlugin;
import org.jensoft.core.plugin.zoom.box.ZoomBoxPlugin.UserZoomBox;
import org.jensoft.core.projection.Projection;
import org.jensoft.core.view.View;
import org.jensoft.core.view.background.ViewDarkBackground;

/**
 * <code>AreaMultipleSplineXFunctionZoomBox</code>
 * 
 * @author Sébastien Janaud
 */
@JenSoftView(background=ViewDarkBackground.class,description="Area with multiple lines based on spline x function with programmatically zoom box")
public class AreaMultipleSplineXFunctionZoomBox extends View {

	private static final long serialVersionUID = 8302062638333182138L;

	/**
	 * Create demo with multiple programmatically zoom sequence
	 */
	public AreaMultipleSplineXFunctionZoomBox() {
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
		

		// source functions
		double[] xValues1 = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
		double[] yValues1 = { 2, 1.8, 1.9, 15, 0.4, 1.4, 1.2, 0.2, 0.6, 0.4, 0.7 };

		double[] xValues2 = { 0, 1, 2, 3, 4, 5.2, 6, 7, 8, 9, 10 };
		double[] yValues2 = { 3, 1, 5, 4, 4.8, 7.3, 2, 3, 7, 10, 6 };

		double[] xValues3 = { 0, 1.4, 2, 3, 4, 5, 6, 6.5, 7, 8, 9, 10 };
		double[] yValues3 = { 0.4, 7.5, 5, 1, 2.8, 1, 4, 7, 9, 2, 4, 1 };

		UserSourceFunction source1 = new UserSourceFunction.SplineSource(xValues1, yValues1, 0.1);
		UserSourceFunction source2 = new UserSourceFunction.SplineSource(xValues2, yValues2, 0.1);
		UserSourceFunction source3 = new UserSourceFunction.SplineSource(xValues3, yValues3, 0.1);

		// areas functions
		Area area1 = new Area(source1);
		area1.setThemeColor(PetalPalette.PETAL1_HC);
		area1.setAreaDraw(new AreaDefaultDraw(Color.WHITE, new BasicStroke(1.5f), Color.WHITE, new BasicStroke(1.5f)));
		area1.setAreaFill(new AreaGradientFill());
		area1.setAreaBase(-3);

		Area area2 = new Area(source2);
		area2.setThemeColor(PetalPalette.PETAL8_HC);
		area2.setAreaDraw(new AreaDefaultDraw(Color.WHITE, new BasicStroke(1.5f), Color.WHITE, new BasicStroke(1.5f)));
		area2.setAreaFill(new AreaGradientFill());
		area2.setAreaBase(-3);

		Area area3 = new Area(source3);
		area3.setThemeColor(NanoChromatique.GREEN);
		area3.setAreaDraw(new AreaDefaultDraw(Color.WHITE, new BasicStroke(1.5f), Color.WHITE, new BasicStroke(1.5f)));
		area3.setAreaFill(new AreaGradientFill());
		area3.setAreaBase(-3);

		// area function plugin
		AreaFunction areaPlugin = new FunctionPlugin.AreaFunction();

		areaPlugin.addFunction(area1);
		areaPlugin.addFunction(area2);
		areaPlugin.addFunction(area3);

		// legend plug-in
		Font f =  new Font("Dialog", Font.PLAIN, 12);
		TitleLegend legend = new TitleLegend("Zoom Box Area");
		legend.setLegendFill(new TitleLegendGradientFill(Color.WHITE, JennyPalette.JENNY8));
		legend.setFont(f);
		legend.setConstraints(new TitleLegendConstraints(LegendPosition.South, 0.8f, LegendAlignment.Rigth));

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

		// zoom box
		final ZoomBoxPlugin zoomBoxPlugin = new ZoomBoxPlugin();
		zoomBoxPlugin.setZoomBoxDrawColor(RosePalette.LEMONPEEL);
		zoomBoxPlugin.setZoomBoxFillColor(RosePalette.INDIGO);
		zoomBoxPlugin.registerContext(new ZoomBoxDefaultDeviceContext());
		zoomBoxPlugin.registerWidget(new ZoomBoxDonutWidget());
		proj.registerPlugin(zoomBoxPlugin);

		// automatic should not really be used, it is more use in demo to show
		// zoom box
		Thread t = new Thread() {

			@Override
			public void run() {
				try {
					Thread.sleep(4000);
					UserZoomBox userZoom = zoomBoxPlugin.createUserZoom(3, 6, 3, 12);
					userZoom.zoomIn();
					Thread.sleep(800);
					userZoom.zoomOut();

					Thread.sleep(600);
					UserZoomBox userZoom2 = zoomBoxPlugin.createUserZoom(1.6, 3.4, 13, 15);
					userZoom2.zoomIn();
					Thread.sleep(800);
					userZoom2.zoomOut();

					Thread.sleep(600);
					UserZoomBox userZoom3 = zoomBoxPlugin.createUserZoom(6, 9.8, 3, 8);
					userZoom3.zoomIn();
					Thread.sleep(800);
					userZoom3.zoomOut();

					Thread.sleep(600);
					UserZoomBox userZoom4 = zoomBoxPlugin.createUserZoom(3.56, 4, 3, 6);
					userZoom4.zoomIn();
					Thread.sleep(800);
					userZoom4.zoomOut();

					Thread.sleep(600);
					UserZoomBox userZoom5 = zoomBoxPlugin.createUserZoom(0, 10, 5, 8);
					userZoom5.zoomIn();
					Thread.sleep(800);
					userZoom5.zoomOut();

					Thread.sleep(600);
					UserZoomBox userZoom6 = zoomBoxPlugin.createUserZoom(2.8, 3.2, 0, 15);
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
