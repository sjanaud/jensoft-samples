/*
 * JenSoft API - Charting Framework
 * http://www.jensoftapi.com
 * Copyright (c) JenSoft. All rights reserved.
 * See JenSoft Software License Agreement
 */
package org.jensoft.catalog.views.zoom.wheel;

import java.awt.BorderLayout;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

import org.jensoft.core.catalog.nature.JenSoftDashboard;
import org.jensoft.core.catalog.ui.Dashboard;
import org.jensoft.core.plugin.zoom.wheel.ZoomWheelPlugin;

@JenSoftDashboard(views = { WheelView1.class, WheelView2.class },description="Show how to synchronize two zoom wheel plugin instances between two distincts views")
public class SynchronizedZoomWheel extends Dashboard {

	private static final long serialVersionUID = 4887689656149797027L;

	public SynchronizedZoomWheel() {
		setOpaque(false);
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		JPanel vp1 = new JPanel();
		vp1.setOpaque(false);
		JPanel vp2 = new JPanel();
		vp2.setOpaque(false);
		add(vp1);
		add(vp2);

		WheelView1 v1 = new WheelView1();
		WheelView2 v2 = new WheelView2();

		vp1.setLayout(new BorderLayout());
		vp1.add(v1, BorderLayout.CENTER);

		vp2.setLayout(new BorderLayout());
		vp2.add(v2, BorderLayout.CENTER);

		ZoomWheelPlugin.createSynchronizer(v1.getZoomWheel(), v2.getZoomWheel());
	}
}
