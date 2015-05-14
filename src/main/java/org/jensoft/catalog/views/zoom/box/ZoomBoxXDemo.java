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
import org.jensoft.core.palette.color.FilPalette;
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
import org.jensoft.core.plugin.zoom.box.ZoomBoxDonutWidget;
import org.jensoft.core.plugin.zoom.box.ZoomBoxPlugin;
import org.jensoft.core.plugin.zoom.box.ZoomBoxPlugin.BoxType;
import org.jensoft.core.projection.Projection;
import org.jensoft.core.view.View;
import org.jensoft.core.view.background.ViewDarkBackground;

/**
 * <code>ZoomBoxYDemo</code>
 * 
 * @author Sébastien Janaud
 */
@JenSoftView(background=ViewDarkBackground.class)
public class ZoomBoxXDemo extends View {

	/** font demo */
	private Font font = new Font("lucida console", Font.PLAIN, 10);

	/**
	 * Create a demo with a zoom box on x dimension
	 */
	public ZoomBoxXDemo() {
		super();
		setPlaceHolder(100);

		// proj projection
		final Projection proj = new Projection.Linear(-10, 10, -10, 10);
		registerProjection(proj);
		proj.registerPlugin(new OutlinePlugin());
		proj.setThemeColor(RosePalette.EMERALD);

		// create modeled axis plug-in in south part
		AxisMetricsPlugin.ModeledMetrics southMetrics = new AxisMetricsPlugin.ModeledMetrics.S();
		proj.registerPlugin(southMetrics);
		southMetrics.setTextFont(font);
		

		// create modeled axis plug-in in west part
		AxisMetricsPlugin.ModeledMetrics westMetrics = new AxisMetricsPlugin.ModeledMetrics.W();
		proj.registerPlugin(westMetrics);
		westMetrics.setTextFont(font);
		

		// legend plug-in
		TitleLegendPlugin legendPlugin = new TitleLegendPlugin();
		proj.registerPlugin(legendPlugin);

		TitleLegend l1 = TitleLegendPlugin.createLegend("Zoom Box", FilPalette.PINK1, FilPalette.PURPLE1);
		legendPlugin.addLegend(l1);

		// stripes
		StripePlugin stripes = new StripePlugin.MultiplierStripe.H(0, 2.5);
		StripePalette bp = new StripePalette();
		bp.addPaint(new Color(255, 255, 255, 20));
		bp.addPaint(ColorPalette.alpha(FilPalette.GREEN4, 20));
		stripes.setStripePalette(bp);
		proj.registerPlugin(stripes);

		// grids
		GridPlugin gridLayout = new GridPlugin.MultiplierGrid(0, 2.5, GridOrientation.Horizontal);
		gridLayout.setGridColor(new Color(255, 255, 255, 60));
		proj.registerPlugin(gridLayout);

		GridPlugin gridLayout2 = new GridPlugin.MultiplierGrid(0, 2.5, GridOrientation.Vertical);
		gridLayout2.setGridColor(new Color(255, 255, 255, 60));
		proj.registerPlugin(gridLayout2);

		// zoom box
		final ZoomBoxPlugin zoomBox = new ZoomBoxPlugin();
		zoomBox.setZoomBoxDrawColor(RosePalette.MANDARIN);
		zoomBox.setZoomBoxFillColor(RosePalette.CALYPSOBLUE);
		ZoomBoxDonutWidget zoomBoxDonutWidget = new ZoomBoxDonutWidget();
		zoomBox.registerWidget(zoomBoxDonutWidget);
		proj.registerPlugin(zoomBox);
		zoomBox.setBoxType(BoxType.BoxX);
		zoomBox.registerContext(new ZoomBoxDefaultDeviceContext());

		zoomBox.lockSelected();

	}

}
