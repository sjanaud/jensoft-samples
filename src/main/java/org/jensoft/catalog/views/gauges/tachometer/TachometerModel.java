/*
 * Copyright (c) JenSoft API
 * This source file is part of JenSoft API, All rights reserved.
 * JENSOFT PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package org.jensoft.catalog.views.gauges.tachometer;

import org.jensoft.core.catalog.nature.JenSoftView;
import org.jensoft.core.catalog.ui.ViewFrameUI;
import org.jensoft.core.plugin.gauge.RadialGaugePlugin;
import org.jensoft.core.plugin.gauge.core.binder.AnchorBinder;
import org.jensoft.core.plugin.gauge.core.binder.PathBinder;
import org.jensoft.core.plugin.gauge.core.binder.path.PathArcAutoBinder;
import org.jensoft.core.plugin.gauge.core.binder.path.PathArcManualBinder;
import org.jensoft.core.plugin.gauge.tachometer.Tachometer;
import org.jensoft.core.projection.Projection;
import org.jensoft.core.view.View;
import org.jensoft.core.view.background.ViewNoBackground;

/**
 * 
 * <code>TachometerModel</code>
 * 
 * @author Sébastien Janaud
 * 
 */
@JenSoftView(background=ViewNoBackground.class,description="Gauge tachometer model for gauge developer.",
				see={Tachometer.class,PathBinder.class,PathArcAutoBinder.class,PathArcManualBinder.class,AnchorBinder.class })
public class TachometerModel extends View {

	private static final long serialVersionUID = 4937186892773139474L;

	public TachometerModel() {
		super(10);

		Projection proj = new Projection.Linear(-1, 1, -1, 1);
		registerProjection(proj);

		Tachometer gauge = new Tachometer();
		RadialGaugePlugin gaugePlugin = new RadialGaugePlugin(gauge);
		proj.registerPlugin(gaugePlugin);
	

	}
	
	public static void main(String[] args) {
		ViewFrameUI ui = new ViewFrameUI(new TachometerModel());
	}
}
