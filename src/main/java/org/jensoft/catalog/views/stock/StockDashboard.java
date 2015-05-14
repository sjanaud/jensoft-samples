/*
 * JenSoft API - Charting Framework
 * http://www.jensoftapi.com
 * Copyright (c) JenSoft. All rights reserved.
 * See JenSoft Software License Agreement
 */
package org.jensoft.catalog.views.stock;

import java.awt.BorderLayout;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

import org.jensoft.core.catalog.nature.JenSoftDashboard;
import org.jensoft.core.catalog.ui.Dashboard;
import org.jensoft.core.catalog.ui.DashboardFrameUI;
import org.jensoft.core.plugin.translate.TranslatePlugin;
import org.jensoft.core.plugin.zoom.box.ZoomBoxPlugin;
import org.jensoft.core.plugin.zoom.lens.ZoomLensPlugin;
import org.jensoft.core.plugin.zoom.wheel.ZoomWheelPlugin;

/**
 * <code>StockDashboard</code> shows stock charts sample
 * 
 * @author Sébastien Janaud
 * 
 */
@JenSoftDashboard(views = { StockCandleStickSample.class, StockVolumeSample.class }, description = "Show stock market dashboard.")
public class StockDashboard extends Dashboard {

	private static final long serialVersionUID = 4887689656149797027L;

	public StockDashboard() {
		setOpaque(false);
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		JPanel vp1 = new JPanel();
		vp1.setOpaque(false);
		JPanel vp2 = new JPanel();
		vp2.setOpaque(false);
		add(vp1);
		add(vp2);

		StockCandleStickSample v1 = new StockCandleStickSample();
		StockVolumeSample v2 = new StockVolumeSample();

		vp1.setLayout(new BorderLayout());
		vp1.add(v1, BorderLayout.CENTER);

		vp2.setLayout(new BorderLayout());
		vp2.add(v2, BorderLayout.CENTER);

		TranslatePlugin.createSynchronizer(v1.getTranslate(), v2.getTranslate());
		ZoomWheelPlugin.createSynchronizer(v1.getZoomWheel(), v2.getZoomWheel());

		ZoomBoxPlugin.createSynchronizer(v1.getZoomBox(), v2.getZoomBox());

		// not need sync
		ZoomLensPlugin.createSynchronizer(v1.getZoomLens(), v2.getZoomLens());
	}

	public static void main(String[] args) {
		DashboardFrameUI dashboard = new DashboardFrameUI(new StockDashboard());
	}
}
