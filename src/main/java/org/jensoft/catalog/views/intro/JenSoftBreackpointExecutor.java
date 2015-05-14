/*
 * JenSoft API - Charting Framework
 * http://www.jensoftapi.com
 * Copyright (c) JenSoft. All rights reserved.
 * See JenSoft Software License Agreement
 */
package org.jensoft.catalog.views.intro;

import java.awt.Font;
import java.util.ArrayList;
import java.util.List;

import org.jensoft.catalog.views.intro.breakpoint.BarAnimatorBreakpoint;
import org.jensoft.catalog.views.intro.breakpoint.BarAxisLabelBreakpoint;
import org.jensoft.catalog.views.intro.breakpoint.BarClimateCompositeBreakpoint;
import org.jensoft.catalog.views.intro.breakpoint.BarEffectBreakpoint;
import org.jensoft.catalog.views.intro.breakpoint.Donut2DBreakpoint;
import org.jensoft.catalog.views.intro.breakpoint.Donut3DBreakpoint;
import org.jensoft.catalog.views.intro.breakpoint.EndBreakpoint;
import org.jensoft.catalog.views.intro.breakpoint.GridBreakpoint;
import org.jensoft.catalog.views.intro.breakpoint.InitBreakpoint;
import org.jensoft.catalog.views.intro.breakpoint.MetricsBreakpoint;
import org.jensoft.catalog.views.intro.breakpoint.OutlineBreakpoint;
import org.jensoft.catalog.views.intro.breakpoint.PieBreakpoint;
import org.jensoft.catalog.views.intro.breakpoint.RealtimeCloudBreakpoint;
import org.jensoft.catalog.views.intro.breakpoint.RealtimeCurveBreakpoint;
import org.jensoft.catalog.views.intro.breakpoint.StripeBreakpoint;
import org.jensoft.catalog.views.intro.breakpoint.TextLegendBreakpoint;
import org.jensoft.catalog.views.intro.breakpoint.TranslateBreakpoint;
import org.jensoft.catalog.views.intro.breakpoint.ZoomBoxBreakpoint;
import org.jensoft.catalog.views.intro.breakpoint.ZoomWheelBreakpoint;
import org.jensoft.core.palette.color.PetalPalette;
import org.jensoft.core.palette.color.RosePalette;
import org.jensoft.core.plugin.message.MessagePlugin;
import org.jensoft.core.projection.Projection;
import org.jensoft.core.view.View;
import org.jensoft.core.view.WidgetPlugin.PushingBehavior;

/**
 * <code>JenSoftBreackpointExecutor</code> takes the responsibility to execute
 * JenSoft API demo breakpoints.
 * 
 * @author Sébastien Janaud
 */
public class JenSoftBreackpointExecutor extends Thread {

	/** view for this demo run */
	private View view;

	/** default proj */
	private Projection.Linear defaultWindow;

	/** message plug in */
	private MessagePlugin messagePlugin;

	/** demo break points */
	private List<DemoBreakPoint> breakpoints = new ArrayList<DemoBreakPoint>();

	/** skip all break points messages */
	private boolean skipBreackpointsMessages = true;

	public JenSoftBreackpointExecutor(View view) {
		this.view = view;
		view.setPlaceHolder(80);

		defaultWindow = new Projection.Linear(0, 0, 0, 0);

		defaultWindow.setThemeColor(PetalPalette.PETAL1_HC);
		view.registerProjection(defaultWindow);

		messagePlugin = new MessagePlugin();
		defaultWindow.registerPlugin(messagePlugin);

		InitBreakpoint initBreackpoint = new InitBreakpoint(this, view);
		initBreackpoint.setSkipMessage(true);
		OutlineBreakpoint outlineBreackpoint = new OutlineBreakpoint(this, view);
		TextLegendBreakpoint textLegendBreackpoint = new TextLegendBreakpoint(this, view);
		MetricsBreakpoint metricsBreackpoint = new MetricsBreakpoint(this, view);
		StripeBreakpoint stripeBreackpoint = new StripeBreakpoint(this, view);
		GridBreakpoint gridBreackpoint = new GridBreakpoint(this, view);
		ZoomWheelBreakpoint zoomWheelBreackpoint = new ZoomWheelBreakpoint(this, view);
		ZoomBoxBreakpoint zoomBoxBreackpoint = new ZoomBoxBreakpoint(this, view);
		TranslateBreakpoint translateBreackpoint = new TranslateBreakpoint(this, view);
		PieBreakpoint pieBreackpoint = new PieBreakpoint(this, view);
		Donut3DBreakpoint donut3DBreackpoint = new Donut3DBreakpoint(this, view);
		Donut2DBreakpoint donut2DBreackpoint = new Donut2DBreakpoint(this, view);
		BarAnimatorBreakpoint barAnimatorBreackpoint = new BarAnimatorBreakpoint(this, view);
		BarEffectBreakpoint barEffectBreackpoint = new BarEffectBreakpoint(this, view);
		BarAxisLabelBreakpoint barAxisLabelBreackpoint = new BarAxisLabelBreakpoint(this, view);
		BarClimateCompositeBreakpoint symbolCompositeBreackpoint = new BarClimateCompositeBreakpoint(this, view);
		RealtimeCurveBreakpoint realtimeCurveBreackpoint = new RealtimeCurveBreakpoint(this, view);
		RealtimeCloudBreakpoint realtimeCloudBreackpoint = new RealtimeCloudBreakpoint(this, view);
		EndBreakpoint endBreackpoint = new EndBreakpoint(JenSoftBreackpointExecutor.this, view);

		breakpoints.add(initBreackpoint);

		breakpoints.add(outlineBreackpoint);
		breakpoints.add(textLegendBreackpoint);
		breakpoints.add(metricsBreackpoint);
		breakpoints.add(stripeBreackpoint);
		breakpoints.add(gridBreackpoint);
		breakpoints.add(zoomWheelBreackpoint);
		breakpoints.add(zoomBoxBreackpoint);
		breakpoints.add(translateBreackpoint);
		breakpoints.add(pieBreackpoint);
		breakpoints.add(donut3DBreackpoint);

		breakpoints.add(donut2DBreackpoint);

		breakpoints.add(barAnimatorBreackpoint);
		breakpoints.add(barEffectBreackpoint);
		breakpoints.add(barAxisLabelBreackpoint);
		breakpoints.add(symbolCompositeBreackpoint);
		breakpoints.add(realtimeCurveBreackpoint);
		breakpoints.add(realtimeCloudBreackpoint);

		breakpoints.add(endBreackpoint);

	}

	/**
	 * @return the defaultWindow
	 */
	public Projection.Linear getDefaultProjection() {
		return defaultWindow;
	}

	/**
	 * @param defaultWindow
	 *            the defaultWindow to set
	 */
	public void setDefaultWindow(Projection.Linear defaultWindow) {
		this.defaultWindow = defaultWindow;
	}

	/**
	 * @return the view
	 */
	public View getView() {
		return view;
	}

	/**
	 * @return the messagePlugin
	 */
	public MessagePlugin getMessagePlugin() {
		return messagePlugin;
	}

	/**
	 * @return the skipBreackpointsMessages
	 */
	public boolean isSkipBreackpointsMessages() {
		return skipBreackpointsMessages;
	}

	@Override
	public void run() {
		synchronized (this) {
			try {
				Font f =  new Font("Dialog", Font.PLAIN, 14);
				view.getWidgetPlugin().pushMessage("JenSoft API", 0, null, PushingBehavior.Fast, f, RosePalette.HENNA, false);

				wait(1000);

				for (DemoBreakPoint point : breakpoints) {
					wait(500);
					point.start();
					point.join();
				}

			} catch (Exception e) {

			}
		}
	}
}