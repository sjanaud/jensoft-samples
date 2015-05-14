/*
 * Copyright (c) JenSoft API
 * This source file is part of JenSoft API, All rights reserved.
 * JENSOFT PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package org.jensoft.catalog.views.gauges.oil;

import org.jensoft.core.catalog.nature.JenSoftView;
import org.jensoft.core.catalog.ui.ViewFrameUI;
import org.jensoft.core.plugin.gauge.RadialGaugePlugin;
import org.jensoft.core.plugin.gauge.core.binder.AnchorBinder;
import org.jensoft.core.plugin.gauge.core.binder.PathBinder;
import org.jensoft.core.plugin.gauge.core.binder.path.PathArcAutoBinder;
import org.jensoft.core.plugin.gauge.core.binder.path.PathArcManualBinder;
import org.jensoft.core.plugin.gauge.oil.GaugeOil;
import org.jensoft.core.projection.Projection;
import org.jensoft.core.view.View;
import org.jensoft.core.view.background.ViewNoBackground;

/**
 * 
 * <code>GaugeOilModel</code>
 * 
 * @author Sébastien Janaud
 * 
 */
@JenSoftView(background = ViewNoBackground.class, description = "Gauge Model for gauge developer. Gauge avionics oil Incubator", 
				see = { GaugeOil.class,PathBinder.class,PathArcAutoBinder.class,PathArcManualBinder.class,AnchorBinder.class  })
public class GaugeOilModel extends View {


	private static final long serialVersionUID = -4214613218363641655L;

	public static void main(String[] args) {
		ViewFrameUI ui = new ViewFrameUI(new GaugeOilModel());
	}
	
	public GaugeOilModel() {
		super(20);
		

		Projection proj = new Projection.Linear.Identity();

		GaugeOil gauge = new GaugeOil();
		RadialGaugePlugin layout = new RadialGaugePlugin(gauge);

		proj.registerPlugin(layout);

		registerProjection(proj);

	}

}
