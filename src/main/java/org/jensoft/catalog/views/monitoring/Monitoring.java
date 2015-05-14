/*
 * JenSoft API - Charting Framework
 * http://www.jensoftapi.com
 * Copyright (c) JenSoft. All rights reserved.
 * See JenSoft Software License Agreement
 */
package org.jensoft.catalog.views.monitoring;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

import org.jensoft.catalog.views.monitoring.cloud1.Cloud1View;
import org.jensoft.catalog.views.monitoring.cloud2.Cloud2View;
import org.jensoft.catalog.views.monitoring.scan.ScanView;
import org.jensoft.catalog.views.monitoring.spectrum.SpectrumView;
import org.jensoft.core.catalog.nature.JenSoftDashboard;
import org.jensoft.core.catalog.ui.Dashboard;

/**
 * <code>Monitoring</code>
 * 
 * @author Sébastien Janaud
 * 
 */
@JenSoftDashboard(views = { Cloud1View.class, ScanView.class, SpectrumView.class, Cloud2View.class })
public class Monitoring extends Dashboard {

	private static final long serialVersionUID = -5113068592757401743L;

	public Monitoring() {
		setOpaque(false);
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		JPanel vp1 = new JPanel();
		vp1.setOpaque(false);
		JPanel vp2 = new JPanel();
		vp2.setOpaque(false);
		add(vp1);
		add(vp2);

		vp1.setLayout(new BoxLayout(vp1, BoxLayout.X_AXIS));
		vp1.add(new Cloud1View());
		vp1.add(new ScanView());

		vp2.setLayout(new BoxLayout(vp2, BoxLayout.X_AXIS));
		vp2.add(new SpectrumView());
		vp2.add(new Cloud2View());
	}
}
