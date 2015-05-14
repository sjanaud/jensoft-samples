/*
 * Copyright (c) JenSoft API
 * This source file is part of JenSoft API, All rights reserved.
 * JENSOFT PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package org.jensoft.catalog.views.gauges.compass;

import org.jensoft.core.catalog.nature.JenSoftView;
import org.jensoft.core.catalog.ui.ViewFrameUI;
import org.jensoft.core.plugin.gauge.RadialGaugePlugin;
import org.jensoft.core.plugin.gauge.compass.GaugeCompass;
import org.jensoft.core.plugin.translate.TranslateDefaultDeviceContext;
import org.jensoft.core.plugin.translate.TranslatePlugin;
import org.jensoft.core.projection.Projection;
import org.jensoft.core.view.View;
import org.jensoft.core.view.background.ViewNoBackground;

/**
 * 
 * <code>GaugeCompassModel</code>
 * 
 * @author Sébastien Janaud
 * 
 */
@JenSoftView(background = ViewNoBackground.class, description = "Gauge Compass model for gauge developer.", see = { GaugeCompass.class })
public class GaugeCompassModel extends View {

	private static final long serialVersionUID = 1975408243670931070L;

	public static void main(String[] args) {
		ViewFrameUI ui = new ViewFrameUI(new GaugeCompassModel());
	}
	
	public GaugeCompassModel() {
		super(10);

		Projection proj = new Projection.Linear(-1, 1, -1, 1);

		GaugeCompass gauge = new GaugeCompass();
		RadialGaugePlugin layout = new RadialGaugePlugin(gauge);

		proj.registerPlugin(layout);

		TranslatePlugin translate = new TranslatePlugin();
		translate.registerContext(new TranslateDefaultDeviceContext());
		proj.registerPlugin(translate);

		registerProjection(proj);
	}
}
