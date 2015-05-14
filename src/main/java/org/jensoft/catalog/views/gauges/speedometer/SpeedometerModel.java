/*
 * Copyright (c) JenSoft API
 * This source file is part of JenSoft API, All rights reserved.
 * JENSOFT PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package org.jensoft.catalog.views.gauges.speedometer;

import org.jensoft.core.catalog.nature.JenSoftView;
import org.jensoft.core.catalog.ui.ViewFrameUI;
import org.jensoft.core.plugin.gauge.RadialGaugePlugin;
import org.jensoft.core.plugin.gauge.core.binder.AnchorBinder;
import org.jensoft.core.plugin.gauge.core.binder.PathBinder;
import org.jensoft.core.plugin.gauge.core.binder.path.PathArcAutoBinder;
import org.jensoft.core.plugin.gauge.core.binder.path.PathArcManualBinder;
import org.jensoft.core.plugin.gauge.speedometer.Speedometer;
import org.jensoft.core.plugin.translate.TranslateDefaultDeviceContext;
import org.jensoft.core.plugin.translate.TranslatePlugin;
import org.jensoft.core.projection.Projection;
import org.jensoft.core.view.View;
import org.jensoft.core.view.background.ViewNoBackground;

/**
 * 
 * <code>SpeedometerModel</code>
 * 
 * @author Sébastien Janaud
 * 
 */
@JenSoftView(background=ViewNoBackground.class,description="Gauge speedometer model for gauge developer.",
				see={Speedometer.class,PathBinder.class,PathArcAutoBinder.class,PathArcManualBinder.class,AnchorBinder.class })
public class SpeedometerModel extends View {

	private static final long serialVersionUID = -1986732743124460781L;

	public SpeedometerModel() {
		super(10);

		Projection proj = new Projection.Linear(-1, 1, -1, 1);

		Speedometer gauge = new Speedometer();
		RadialGaugePlugin gaugePlugin = new RadialGaugePlugin(gauge);

		proj.registerPlugin(gaugePlugin);

		TranslatePlugin translate = new TranslatePlugin();
		translate.registerContext(new TranslateDefaultDeviceContext());
		proj.registerPlugin(translate);

		registerProjection(proj);

	}
	
	public static void main(String[] args) {
		ViewFrameUI ui = new ViewFrameUI(new SpeedometerModel());
	}
	
}
