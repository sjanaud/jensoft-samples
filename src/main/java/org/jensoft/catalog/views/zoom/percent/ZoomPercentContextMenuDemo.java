/*
 * JenSoft API - Charting Framework
 * http://www.jensoftapi.com
 * Copyright (c) JenSoft. All rights reserved.
 * See JenSoft Software License Agreement
 */
package org.jensoft.catalog.views.zoom.percent;

import java.awt.Color;
import java.awt.Font;

import org.jensoft.core.catalog.nature.JenSoftView;
import org.jensoft.core.palette.color.ColorPalette;
import org.jensoft.core.palette.color.RosePalette;
import org.jensoft.core.plugin.grid.Grid.GridOrientation;
import org.jensoft.core.plugin.grid.GridPlugin;
import org.jensoft.core.plugin.legend.title.TitleLegend;
import org.jensoft.core.plugin.legend.title.TitleLegendPlugin;
import org.jensoft.core.plugin.outline.OutlinePlugin;
import org.jensoft.core.plugin.stripe.StripePlugin;
import org.jensoft.core.plugin.stripe.painter.StripePalette;
import org.jensoft.core.plugin.zoom.percent.ZoomPercentDefaultDeviceContext;
import org.jensoft.core.plugin.zoom.percent.ZoomPercentPlugin;
import org.jensoft.core.projection.Projection;
import org.jensoft.core.view.View;
import org.jensoft.core.view.background.ViewDarkBackground;

/**
 * <code>ZoomPercentContextMenuDemo</code>
 * 
 * @author Sébastien Janaud
 */
@JenSoftView(background=ViewDarkBackground.class)
public class ZoomPercentContextMenuDemo extends View {

	/**
	 * Create a demo with percent plug-in
	 */
	public ZoomPercentContextMenuDemo() {
		super();

		setPlaceHolder(100);
		Projection proj = new Projection.Linear(-10, 10, -10, 10);
		registerProjection(proj);
		proj.registerPlugin(new OutlinePlugin());

		TitleLegendPlugin legendPlugin = new TitleLegendPlugin();
		proj.registerPlugin(legendPlugin);

		Font f = new Font("Dialog", Font.PLAIN, 12);
		TitleLegend l1 = TitleLegendPlugin.createLegend("Zoom Percent Context Menu", f, RosePalette.LIME, RosePalette.MANDARIN);
		legendPlugin.addLegend(l1);

		StripePlugin stripes = new StripePlugin.MultiplierStripe.H(0, 2.5);
		StripePalette bp = new StripePalette();
		bp.addPaint(ColorPalette.alpha(RosePalette.MANDARIN, 20));
		bp.addPaint(ColorPalette.alpha(RosePalette.EMERALD, 20));
		stripes.setStripePalette(bp);
		proj.registerPlugin(stripes);

		GridPlugin gridLayout = new GridPlugin.MultiplierGrid(0, 2.5, GridOrientation.Horizontal);
		gridLayout.setGridColor(new Color(255, 255, 255, 60));
		proj.registerPlugin(gridLayout);

		GridPlugin gridLayout2 = new GridPlugin.MultiplierGrid(0, 2.5, GridOrientation.Vertical);
		gridLayout2.setGridColor(new Color(255, 255, 255, 60));
		proj.registerPlugin(gridLayout2);

		// ZOOM PERCENT
		ZoomPercentPlugin zoomPercent = new ZoomPercentPlugin();
		zoomPercent.registerContext(new ZoomPercentDefaultDeviceContext());
		proj.registerPlugin(zoomPercent);
		zoomPercent.lockSelected();

	}

}
