/*
 * JenSoft API - Charting Framework
 * http://www.jensoftapi.com
 * Copyright (c) JenSoft. All rights reserved.
 * See JenSoft Software License Agreement
 */
package org.jensoft.catalog.views.translate;

import java.awt.BorderLayout;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

import org.jensoft.core.catalog.nature.JenSoftDashboard;
import org.jensoft.core.catalog.ui.Dashboard;
import org.jensoft.core.plugin.translate.TranslatePlugin;

@JenSoftDashboard(views = { TranslateView1.class, TranslateView2.class },description="Show how to synchronize two translate plugin instances between two distincts views.")
public class SynchronizedTranslate extends Dashboard {

	private static final long serialVersionUID = 4887689656149797027L;

	public SynchronizedTranslate() {
		setOpaque(false);
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		JPanel vp1 = new JPanel();
		vp1.setOpaque(false);
		JPanel vp2 = new JPanel();
		vp2.setOpaque(false);
		add(vp1);
		add(vp2);

		TranslateView1 v1 = new TranslateView1();
		TranslateView2 v2 = new TranslateView2();

		vp1.setLayout(new BorderLayout());
		vp1.add(v1, BorderLayout.CENTER);

		vp2.setLayout(new BorderLayout());
		vp2.add(v2, BorderLayout.CENTER);

		TranslatePlugin.createSynchronizer(v1.getTranslate(), v2.getTranslate());
	}
}
