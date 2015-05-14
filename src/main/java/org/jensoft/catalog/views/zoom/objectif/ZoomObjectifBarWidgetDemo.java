/*
 * JenSoft API - Charting Framework
 * http://www.jensoftapi.com
 * Copyright (c) JenSoft. All rights reserved.
 * See JenSoft Software License Agreement
 */
package org.jensoft.catalog.views.zoom.objectif;

import java.awt.Color;
import java.awt.Font;

import org.jensoft.core.catalog.nature.JenSoftView;
import org.jensoft.core.catalog.ui.ViewFrameUI;
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
import org.jensoft.core.plugin.zoom.lens.LensX;
import org.jensoft.core.plugin.zoom.lens.LensY;
import org.jensoft.core.plugin.zoom.lens.ZoomLensPlugin;
import org.jensoft.core.projection.Projection;
import org.jensoft.core.view.View;
import org.jensoft.core.view.background.ViewDarkBackground;

/**
 * <code>ZoomObjectifBarWidgetDemo</code>
 * 
 * @author Sébastien Janaud
 */
@JenSoftView(background=ViewDarkBackground.class)
public class ZoomObjectifBarWidgetDemo extends View {

	/** font demo */
	private Font font = new Font("lucida console", Font.PLAIN, 10);

	
	public static void main(String[] args) {
		ViewFrameUI ui = new ViewFrameUI(new ZoomObjectifBarWidgetDemo());
	}
	public ZoomObjectifBarWidgetDemo() {
		super();

		setPlaceHolder(100);
		Projection proj = new Projection.Linear(-10, 10, -10, 10);
		registerProjection(proj);
		proj.registerPlugin(new OutlinePlugin());
		proj.setThemeColor(RosePalette.AEGEANBLUE);

		// create modeled axis plug-in in south part
		AxisMetricsPlugin.ModeledMetrics southMetrics = new AxisMetricsPlugin.ModeledMetrics.S();
		proj.registerPlugin(southMetrics);
		southMetrics.setTextFont(font);

		// create modeled axis plug-in in west part
		AxisMetricsPlugin.ModeledMetrics westMetrics = new AxisMetricsPlugin.ModeledMetrics.W();
		proj.registerPlugin(westMetrics);
		westMetrics.setTextFont(font);

		TitleLegendPlugin legendPlugin = new TitleLegendPlugin();
		proj.registerPlugin(legendPlugin);

		Font f = new Font("Dialog", Font.PLAIN, 12);
		
		TitleLegend l1 = TitleLegendPlugin.createLegend("Objectif Bars Widget", f, RosePalette.LIME, RosePalette.MANDARIN);
		legendPlugin.addLegend(l1);

		StripePlugin stripes = new StripePlugin.MultiplierStripe.H(0, 2.5);
		StripePalette bp = new StripePalette();
		bp.addPaint(ColorPalette.alpha(RosePalette.LEMONPEEL, 20));
		bp.addPaint(ColorPalette.alpha(RosePalette.MELON, 20));
		stripes.setStripePalette(bp);
		proj.registerPlugin(stripes);

		GridPlugin gridLayout = new GridPlugin.MultiplierGrid(0, 2.5, GridOrientation.Horizontal);
		gridLayout.setGridColor(new Color(255, 255, 255, 60));
		proj.registerPlugin(gridLayout);

		GridPlugin gridLayout2 = new GridPlugin.MultiplierGrid(0, 2.5, GridOrientation.Vertical);
		gridLayout2.setGridColor(new Color(255, 255, 255, 60));
		proj.registerPlugin(gridLayout2);

		// OBJECTIF PLUGIN and Widgets
		ZoomLensPlugin zoomObjectif = new ZoomLensPlugin();
		// create two objectif for x and y dimension
		LensX ox = new LensX();
		
		LensY oy = new LensY();
		oy.setxIndex(0);
		oy.setyIndex(1);
		// register widget in zoom objectif plugin
		zoomObjectif.registerWidget(ox);
		zoomObjectif.registerWidget(oy);

		ox.setOutlineColor(Color.BLACK);
		ox.setButton1DrawColor(RosePalette.CALYPSOBLUE);
		ox.setButton2DrawColor(RosePalette.CALYPSOBLUE);
		oy.setOutlineColor(Color.BLACK);
		oy.setButton1DrawColor(RosePalette.CALYPSOBLUE);
		oy.setButton2DrawColor(RosePalette.CALYPSOBLUE);

		proj.registerPlugin(zoomObjectif);

		// pre lock selected widget (selectable widget need a lock)
		zoomObjectif.lockSelected();

	}

}
