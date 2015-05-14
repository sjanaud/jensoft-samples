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
import org.jensoft.core.plugin.gauge.core.binder.path.PathArcAutoBinder;
import org.jensoft.core.plugin.gauge.core.binder.path.PathArcManualBinder;
import org.jensoft.core.plugin.translate.TranslateDefaultDeviceContext;
import org.jensoft.core.plugin.translate.TranslatePlugin;
import org.jensoft.core.projection.Projection;
import org.jensoft.core.view.View;
import org.jensoft.core.view.background.ViewDarkBackground;

/**
 * <code>GaugeArcAutoBinderAnimatorDemo</code>
 * 
 * @author Sébastien Janaud
 * 
 */
@JenSoftView(background = ViewDarkBackground.class,description="Gauge arc path auto binder. This sample show how to use arc auto path binder to design gauge metrics.",
				see={PathBinder.class,PathArcAutoBinder.class,PathArcManualBinder.class})
public class GaugeArcAutoBinderAnimatorDemo extends View {

	private static final long serialVersionUID = 870164250611475302L;

	private class GaugeArcAutoBinder extends RadialGauge {
		public GaugeArcAutoBinder() {
			super(0, 0, 90);
			GaugeBody body = new GaugeBody();
			addBody(body);
			
			GaugeMetricsPath path = new GaugeMetricsPath();
			AbstractPathAutoBinder autoArcBinder = new PathArcAutoBinder(120, 140, 0);
			path.setPathBinder(autoArcBinder);
			body.registerGaugeMetricsPath(path);

			PathAnimator animator = new PathAnimator(path);
			animator.start();
		}

	}

	private class PathAnimator extends Thread {

		private GaugeMetricsPath path;

		public PathAnimator(GaugeMetricsPath path) {
			this.path = path;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.lang.Thread#run()
		 */
		@Override
		public void run() {
			try {
				while (true) {
					for (int polarAngle = 0; polarAngle < 360; polarAngle = polarAngle + 30) {
						for (int radius = 0; radius < 300; radius = radius + 5) {
							AbstractPathAutoBinder binder = new PathArcAutoBinder(radius, 200, polarAngle);
							path.setPathBinder(binder);
							binder.setDebug(true);
							repaintView();
							Thread.sleep(50);
						}

					}
					for (int polarAngle = 0; polarAngle < 360; polarAngle = polarAngle + 30) {
						for (int radius = 0; radius < 300; radius = radius + 5) {
							AbstractPathAutoBinder binder = new PathArcAutoBinder(radius, 200, polarAngle, Direction.AntiClockwise);
							path.setPathBinder(binder);
							binder.setDebug(true);
							repaintView();
							Thread.sleep(50);
						}

					}
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}

	}


	public GaugeArcAutoBinderAnimatorDemo() {
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
	
	public static void main(String[] args) {
		ViewFrameUI ui = new ViewFrameUI(new GaugeArcAutoBinderAnimatorDemo());
	}

}
