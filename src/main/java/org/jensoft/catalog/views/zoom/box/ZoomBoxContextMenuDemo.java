/*
 * JenSoft API - Charting Framework
 * http://www.jensoftapi.com
 * Copyright (c) JenSoft. All rights reserved.
 * See JenSoft Software License Agreement
 */
package org.jensoft.catalog.views.zoom.box;

import java.awt.Color;
import java.awt.Font;

import org.jensoft.core.catalog.nature.JenSoftView;
import org.jensoft.core.palette.color.ColorPalette;
import org.jensoft.core.palette.color.RosePalette;
import org.jensoft.core.plugin.grid.Grid.GridOrientation;
import org.jensoft.core.plugin.grid.GridPlugin;
import org.jensoft.core.plugin.legend.title.TitleLegend;
import org.jensoft.core.plugin.legend.title.TitleLegendPlugin;
import org.jensoft.core.plugin.metrics.AxisMetricsPlugin;
import org.jensoft.core.plugin.outline.OutlinePlugin;
import org.jensoft.core.plugin.stripe.StripePlugin;
import org.jensoft.core.plugin.stripe.painter.StripePalette;
import org.jensoft.core.plugin.zoom.box.ZoomBoxDefaultDeviceContext;
import org.jensoft.core.plugin.zoom.box.ZoomBoxPlugin;
import org.jensoft.core.plugin.zoom.lens.LensDefaultDeviceContext;
import org.jensoft.core.projection.Projection;
import org.jensoft.core.view.View;
import org.jensoft.core.view.background.ViewDarkBackground;

/**
 * <code>ZoomBoxContextMenuDemo</code>
 * 
 * @author Sébastien Janaud
 */
@JenSoftView(background=ViewDarkBackground.class)
public class ZoomBoxContextMenuDemo extends View {

	/** font demo */
	private Font font = new Font("lucida console", Font.PLAIN, 10);

	/**
	 * Create demo with a zoom box and zoom box default context menu
	 */
	public ZoomBoxContextMenuDemo() {
		super();

		setPlaceHolder(100);

		// proj projection
		Projection proj = new Projection.Linear(-10, 10, -10, 10);
		registerProjection(proj);
		proj.registerPlugin(new OutlinePlugin());

		// create modeled axis plug-in in south part
		AxisMetricsPlugin.ModeledMetrics southMetrics = new AxisMetricsPlugin.ModeledMetrics.S();
		proj.registerPlugin(southMetrics);
		southMetrics.setTextFont(font);
		

		// create modeled axis plug-in in west part
		AxisMetricsPlugin.ModeledMetrics westMetrics = new AxisMetricsPlugin.ModeledMetrics.W();
		proj.registerPlugin(westMetrics);
		westMetrics.setTextFont(font);
		

		// legend
		TitleLegendPlugin legendPlugin = new TitleLegendPlugin();
		proj.registerPlugin(legendPlugin);
		Font f = new Font("Dialog", Font.PLAIN, 12);
		TitleLegend l1 = TitleLegendPlugin.createLegend("Zoom Box Context Menu", f, RosePalette.LIME, RosePalette.MANDARIN);
		legendPlugin.addLegend(l1);

		// stripe plug-in
		StripePlugin stripes = new StripePlugin.MultiplierStripe.H(0, 2.5);
		StripePalette bp = new StripePalette();
		bp.addPaint(ColorPalette.alpha(RosePalette.MANDARIN, 20));
		bp.addPaint(ColorPalette.alpha(RosePalette.EMERALD, 20));
		stripes.setStripePalette(bp);
		proj.registerPlugin(stripes);

		// grid plug-in
		GridPlugin gridLayout = new GridPlugin.MultiplierGrid(0, 2.5, GridOrientation.Horizontal);
		gridLayout.setGridColor(new Color(255, 255, 255, 60));
		proj.registerPlugin(gridLayout);

		GridPlugin gridLayout2 = new GridPlugin.MultiplierGrid(0, 2.5, GridOrientation.Vertical);
		gridLayout2.setGridColor(new Color(255, 255, 255, 60));
		proj.registerPlugin(gridLayout2);

		// zoom box
		ZoomBoxPlugin zoomBox = new ZoomBoxPlugin();
		zoomBox.registerContext(new ZoomBoxDefaultDeviceContext());
		zoomBox.registerContext(new LensDefaultDeviceContext());
		proj.registerPlugin(zoomBox);
		zoomBox.lockSelected();

	}

}
