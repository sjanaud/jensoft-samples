/*
 * JenSoft API - Charting Framework
 * http://www.jensoftapi.com
 * Copyright (c) JenSoft. All rights reserved.
 * See JenSoft Software License Agreement
 */
package org.jensoft.catalog.views.zoom.objectif;

import java.awt.BorderLayout;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

import org.jensoft.core.catalog.nature.JenSoftDashboard;
import org.jensoft.core.catalog.ui.Dashboard;
import org.jensoft.core.plugin.zoom.lens.ZoomLensPlugin;

@JenSoftDashboard(views = { ObjectifView1.class, ObjectifView2.class },description="Show how to synchronize two zoom lens plugin instances between two distincts views")
public class SynchronizedZoomLens extends Dashboard {

	private static final long serialVersionUID = 4887689656149797027L;

	public SynchronizedZoomLens() {
		setOpaque(false);
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		JPanel vp1 = new JPanel();
		vp1.setOpaque(false);
		JPanel vp2 = new JPanel();
		vp2.setOpaque(false);
		add(vp1);
		add(vp2);

		ObjectifView1 v1 = new ObjectifView1();
		ObjectifView2 v2 = new ObjectifView2();

		vp1.setLayout(new BorderLayout());
		vp1.add(v1, BorderLayout.CENTER);

		vp2.setLayout(new BorderLayout());
		vp2.add(v2, BorderLayout.CENTER);

		ZoomLensPlugin.createSynchronizer(v1.getObjectif(), v2.getObjectif());
	}
}
