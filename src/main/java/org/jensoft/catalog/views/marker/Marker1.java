/*
 * JenSoft API - Charting Framework
 * http://www.jensoftapi.com
 * Copyright (c) JenSoft. All rights reserved.
 * See JenSoft Software License Agreement
 */
package org.jensoft.catalog.views.marker;

import java.awt.BasicStroke;
import java.awt.Color;

import org.jensoft.core.catalog.nature.JenSoftView;
import org.jensoft.core.catalog.ui.ViewFrameUI;
import org.jensoft.core.graphics.Shader;
import org.jensoft.core.plugin.marker.MarkerPlugin;
import org.jensoft.core.plugin.marker.context.MarkerDefaultDeviceContext;
import org.jensoft.core.projection.Projection;
import org.jensoft.core.view.Portfolio;
import org.jensoft.core.view.View;
import org.jensoft.core.view.background.ViewDarkBackground;
import org.jensoft.core.view.background.ViewDefaultBackground;

@JenSoftView(background=ViewDarkBackground.class)
public class Marker1 extends View {

	public static void main(String[] args) {
		ViewFrameUI ui = new ViewFrameUI(new Marker1());
	}
	@Portfolio(name = "MarkerDemo", width = 800, height = 600)
	public static View getPortofolio() {
		Marker1 demo = new Marker1();

		ViewDefaultBackground viewBackground = new ViewDefaultBackground();
		Shader s = new Shader(new float[] { 0f, 1f }, new Color[] { new Color(32, 39, 55), Color.BLACK });
		viewBackground.setShader(s);
		viewBackground.setOutlineStroke(new BasicStroke(2.5f));

		demo.setBackgroundPainter(viewBackground);
		return demo;
	}

	public Marker1() {
		super();
		Projection proj = new Projection.Linear(-3000, 3000, -3000, 3000);

		registerProjection(proj);

		MarkerPlugin marker = new MarkerPlugin();
		marker.registerContext(new MarkerDefaultDeviceContext());

		proj.registerPlugin(marker);

	}

}
