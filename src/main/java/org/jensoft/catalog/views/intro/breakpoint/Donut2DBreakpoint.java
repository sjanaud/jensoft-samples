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
import org.jensoft.core.palette.color.ColorPalette;
import org.jensoft.core.palette.color.RosePalette;
import org.jensoft.core.palette.color.Spectral;
import org.jensoft.core.plugin.donut2d.Donut2D;
import org.jensoft.core.plugin.donut2d.Donut2D.Donut2DNature;
import org.jensoft.core.plugin.donut2d.Donut2DPlugin;
import org.jensoft.core.plugin.donut2d.Donut2DSlice;
import org.jensoft.core.plugin.donut2d.Donut2DToolkit;
import org.jensoft.core.plugin.donut2d.painter.fill.Donut2DRadialFill;
import org.jensoft.core.plugin.donut2d.painter.label.AbstractDonut2DSliceLabel.Style;
import org.jensoft.core.plugin.donut2d.painter.label.Donut2DBorderLabel;
import org.jensoft.core.plugin.outline.OutlinePlugin;
import org.jensoft.core.plugin.translate.TranslateDefaultDeviceContext;
import org.jensoft.core.plugin.translate.TranslatePlugin;
import org.jensoft.core.plugin.zoom.wheel.ZoomWheelPlugin;
import org.jensoft.core.projection.Projection;
import org.jensoft.core.view.View;
import org.jensoft.core.view.WidgetPlugin.PushingBehavior;

public class Donut2DBreakpoint extends DemoBreakPoint {

	public Donut2DBreakpoint(JenSoftBreackpointExecutor jenSoftDemoExecutor, View view) {
		super(jenSoftDemoExecutor, view);
	}

	@Override
	protected DemoMessage getMessage() {
		DemoMessage msg = new DemoMessage("Continue and install donut 2D.");
		msg.setSize(320, 110);
		msg.setTitle("JenSoft API - Donut 2D");
		msg.setMessageTitleColor(Color.WHITE);
		msg.setMessageForeground(Color.WHITE);
		return msg;
	}

	@Override
	public void run() {
		super.run();
		try {
			synchronized (this) {
				Font f =  new Font("Dialog", Font.PLAIN, 14);
				view.getWidgetPlugin().pushMessage("Install Outline", 500, null, PushingBehavior.Fast, f, Color.BLACK);

				wait(500);
				jenSoftDemoExecutor.getDefaultProjection().setVisible(false);
				view.repaint();
				Projection proj = new Projection.Linear(-2, 2, -2, 2);
				view.registerProjection(proj);
				proj.setName("donuts2D proj");
				proj.setThemeColor(Color.WHITE);
				proj.registerPlugin(new OutlinePlugin(RosePalette.MELON));

				proj.registerPlugin(new ZoomWheelPlugin());

				TranslatePlugin translatePlugin = new TranslatePlugin();
				translatePlugin.registerContext(new TranslateDefaultDeviceContext());
				proj.registerPlugin(translatePlugin);

				Donut2DPlugin donut2DPlugin = new Donut2DPlugin();
				proj.registerPlugin(donut2DPlugin);

				Donut2D donut1 = new Donut2D();
				donut1.setNature(Donut2DNature.User);
				donut1.setCenterX(0);
				donut1.setCenterY(0);
				donut1.setInnerRadius(50);
				donut1.setOuterRadius(80);
				donut1.setStartAngleDegree(50);
				donut1.setExplose(0);

				Donut2DRadialFill donut2DRadialFill = new Donut2DRadialFill();
				donut1.setDonut2DFill(donut2DRadialFill);

				final Donut2DSlice s1 = new Donut2DSlice("s1", Spectral.SPECTRAL_RED.brighter());
				s1.setValue(20.0);

				final Donut2DSlice s2 = new Donut2DSlice("s2", Spectral.SPECTRAL_BLUE2.brighter());
				s2.setValue(20.0);

				final Donut2DSlice s3 = new Donut2DSlice("s3", Spectral.SPECTRAL_PURPLE1.brighter());
				s3.setValue(20.0);

				// LABELS
				float[] fractions = { 0f, 0.3f, 0.7f, 1f };
				Color[] c = { new Color(0, 0, 0, 20), new Color(0, 0, 0, 150), new Color(0, 0, 0, 150), new Color(0, 0, 0, 20) };

				// LABEL 1
				Font f12 =  new Font("Dialog", Font.PLAIN, 12);
				Donut2DBorderLabel label1 = Donut2DToolkit.createBorderLabel("Symbian", RosePalette.COALBLACK, f12, 20, Style.Both);
				label1.setLinkColor(RosePalette.LEMONPEEL);
				// label1.setLinkExtends(30);
				label1.setLabelColor(ColorPalette.WHITE);
				label1.setOutlineColor(Color.BLACK);
				label1.setShader(fractions, c);
				s1.addSliceLabel(label1);

				donut1.addSlice(s1);
				donut1.addSlice(s2);
				donut1.addSlice(s3);

				// donut1.copyAlphaToSlices();

				donut2DPlugin.addDonut(donut1);

				for (int i = 0; i < 10; i++) {
					donut1.setInnerRadius(50 - i);
					donut1.setOuterRadius(80 + i);
					view.repaint();
					wait(20);
				}

				for (int i = 0; i < 10; i++) {
					donut1.setInnerRadius(40 + i);
					donut1.setOuterRadius(90 - i);
					view.repaint();
					wait(20);
				}

				for (int i = 0; i < 360; i = i + 4) {
					donut1.setStartAngleDegree(donut1.getStartAngleDegree() + 4);
					view.repaint();
					wait(20);
				}

				wait(200);
				for (int i = 0; i <= 40; i++) {
					donut1.setExplose(i);
					view.repaint();
					wait(10);
				}

				wait(200);
				for (int i = 40; i >= 0; i--) {
					donut1.setExplose(i);
					view.repaint();
					wait(10);
				}

				view.repaint();
				wait(1000);
				view.unregisterProjection(proj);
			}
		} catch (InterruptedException e) {
		} finally {
			System.out.println("out executor : " + getClass().getSimpleName());
		}
	}

}
