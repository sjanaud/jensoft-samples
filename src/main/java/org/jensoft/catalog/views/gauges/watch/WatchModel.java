/*
 * Copyright (c) JenSoft API
 * This source file is part of JenSoft API, All rights reserved.
 * JENSOFT PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package org.jensoft.catalog.views.gauges.watch;

import org.jensoft.core.catalog.nature.JenSoftView;
import org.jensoft.core.catalog.ui.ViewFrameUI;
import org.jensoft.core.plugin.gauge.RadialGaugePlugin;
import org.jensoft.core.plugin.gauge.core.binder.AnchorBinder;
import org.jensoft.core.plugin.gauge.core.binder.PathBinder;
import org.jensoft.core.plugin.gauge.core.binder.path.PathArcAutoBinder;
import org.jensoft.core.plugin.gauge.core.binder.path.PathArcManualBinder;
import org.jensoft.core.plugin.gauge.watch.Watch;
import org.jensoft.core.projection.Projection;
import org.jensoft.core.view.View;
import org.jensoft.core.view.background.ViewNoBackground;

/**
 * 
 * <code>WatchModel</code>
 * 
 * @author Sébastien Janaud
 * 
 */
@JenSoftView(background=ViewNoBackground.class,description="Gauge watch Model for gauge developer.",
				see={Watch.class,PathBinder.class,PathArcAutoBinder.class,PathArcManualBinder.class,AnchorBinder.class })
public class WatchModel extends View {

	private static final long serialVersionUID = 9017345112270079237L;

	public WatchModel() {
    	super(10);

        Projection proj = new Projection.Linear(-1, 1, -1, 1);

        Watch gauge = new Watch();
        RadialGaugePlugin layout = new RadialGaugePlugin(gauge);

        proj.registerPlugin(layout);

        registerProjection(proj);
    }
	
	public static void main(String[] args) {
		ViewFrameUI ui = new ViewFrameUI(new WatchModel());
	}
}
