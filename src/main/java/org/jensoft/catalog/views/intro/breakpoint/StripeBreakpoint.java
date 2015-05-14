/*
 * JenSoft API - Charting Framework
 * http://www.jensoftapi.com
 * Copyright (c) JenSoft. All rights reserved.
 * See JenSoft Software License Agreement
 */
package org.jensoft.catalog.views.intro.breakpoint;

import java.awt.Color;
import java.awt.Font;

import org.jensoft.catalog.views.intro.DemoBreakPoint;
import org.jensoft.catalog.views.intro.JenSoftBreackpointExecutor;
import org.jensoft.core.palette.color.Alpha;
import org.jensoft.core.palette.color.ColorPalette;
import org.jensoft.core.palette.color.TangoPalette;
import org.jensoft.core.plugin.stripe.StripePlugin;
import org.jensoft.core.plugin.stripe.painter.StripePalette;
import org.jensoft.core.projection.Projection;
import org.jensoft.core.view.View;
import org.jensoft.core.view.WidgetPlugin.PushingBehavior;

public class StripeBreakpoint extends DemoBreakPoint {

	public StripeBreakpoint(JenSoftBreackpointExecutor jenSoftDemoExecutor, View view) {
		super(jenSoftDemoExecutor, view);
	}

	@Override
	protected DemoMessage getMessage() {
		DemoMessage msg = new DemoMessage("Continue and install stripes.");
		msg.setSize(320, 110);
		msg.setTitle("JenSoft API - Stripe");
		msg.setMessageTitleColor(Color.WHITE);
		msg.setMessageForeground(new Alpha(Color.WHITE, 250));
		return msg;
	}

	@Override
	public void run() {
		super.run();
		try {
			synchronized (this) {
				Font f =  new Font("Dialog", Font.PLAIN, 14);
				view.getWidgetPlugin().pushMessage("Installing Stripe", 500, null, PushingBehavior.Fast, f, Color.black);

				Projection.Linear proj = jenSoftDemoExecutor.getDefaultProjection();
				proj.bound(-2800, 2800, -2800, +2800);

				StripePlugin bandLayout = new StripePlugin.MultiplierStripe.H(0, 500);
				StripePalette bp = new StripePalette();
				bp.addPaint(new Color(255, 255, 255, 40));
				bp.addPaint(ColorPalette.alpha(TangoPalette.ORANGE3, 40));
				bandLayout.setStripePalette(bp);
				bandLayout.setAlpha(0.3f);
				proj.registerPlugin(bandLayout);
				view.repaint();
				Thread.sleep(400);

			}
		} catch (InterruptedException e) {
		}
	}

}
