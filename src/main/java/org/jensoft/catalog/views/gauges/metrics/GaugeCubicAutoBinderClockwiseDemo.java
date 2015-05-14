package org.jensoft.catalog.views.gauges.metrics;

import org.jensoft.core.catalog.nature.JenSoftView;
import org.jensoft.core.catalog.ui.ViewFrameUI;
import org.jensoft.core.plugin.gauge.RadialGaugePlugin;
import org.jensoft.core.plugin.gauge.core.GaugeBody;
import org.jensoft.core.plugin.gauge.core.GaugeMetricsPath;
import org.jensoft.core.plugin.gauge.core.RadialGauge;
import org.jensoft.core.plugin.gauge.core.binder.PathBinder;
import org.jensoft.core.plugin.gauge.core.binder.path.AbstractPathAutoBinder;
import org.jensoft.core.plugin.gauge.core.binder.path.AbstractPathAutoBinder.Direction;
import org.jensoft.core.plugin.gauge.core.binder.path.PathArcManualBinder;
import org.jensoft.core.plugin.gauge.core.binder.path.PathCubicAutoBinder;
import org.jensoft.core.plugin.translate.TranslateDefaultDeviceContext;
import org.jensoft.core.plugin.translate.TranslatePlugin;
import org.jensoft.core.projection.Projection;
import org.jensoft.core.view.View;
import org.jensoft.core.view.background.ViewDarkBackground;

/**
 * <code>GaugeCubicAutoBinderAnimatorDemo</code>
 * 
 * @author Sébastien Janaud
 * 
 */
@JenSoftView(background = ViewDarkBackground.class,description="Gauge cubic path auto binder. This sample show how to use cubic curve auto path binder to design gauge metrics.",
				see={PathBinder.class,PathCubicAutoBinder.class,PathArcManualBinder.class})
public class GaugeCubicAutoBinderClockwiseDemo extends View {

	private static final long serialVersionUID = -2883308274556575849L;

	public static void main(String[] args) {
		ViewFrameUI ui = new ViewFrameUI(new GaugeCubicAutoBinderClockwiseDemo());
	}
	private class GaugeArcAutoBinder extends RadialGauge {
		public GaugeArcAutoBinder() {
			super(0, 0, 90);
			
			GaugeBody body = new GaugeBody();
			addBody(body);
			
			
			GaugeMetricsPath path = new GaugeMetricsPath();
			AbstractPathAutoBinder binder = new PathCubicAutoBinder(120, 140, 30,Direction.Clockwise);
			binder.setDebug(true);
			path.setPathBinder(binder);
			body.registerGaugeMetricsPath(path);

		}
	}



	public GaugeCubicAutoBinderClockwiseDemo() {
		super(20);

		Projection proj = new Projection.Linear.Identity();
		registerProjection(proj);

		GaugeArcAutoBinder gauge = new GaugeArcAutoBinder();
		RadialGaugePlugin gaugePlugin = new RadialGaugePlugin(gauge);
		proj.registerPlugin(gaugePlugin);

		TranslatePlugin translate = new TranslatePlugin();
		translate.registerContext(new TranslateDefaultDeviceContext());
		proj.registerPlugin(translate);

	}

}
