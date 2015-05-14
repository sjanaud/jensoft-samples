/*
 * JenSoft API - Charting Framework
 * http://www.jensoftapi.com
 * Copyright (c) JenSoft. All rights reserved.
 * See JenSoft Software License Agreement
 */
package org.jensoft.catalog.views.intro.breakpoint;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;

import org.jensoft.catalog.views.intro.DemoBreakPoint;
import org.jensoft.catalog.views.intro.JenSoftBreackpointExecutor;
import org.jensoft.core.graphics.Shader;
import org.jensoft.core.palette.color.ColorPalette;
import org.jensoft.core.palette.color.RosePalette;
import org.jensoft.core.plugin.translate.TranslateCompassWidget;
import org.jensoft.core.plugin.translate.TranslatePlugin;
import org.jensoft.core.plugin.translate.TranslatePlugin.ShiftDirection;
import org.jensoft.core.plugin.translate.TranslateX;
import org.jensoft.core.plugin.translate.TranslateY;
import org.jensoft.core.projection.Projection;
import org.jensoft.core.view.View;
import org.jensoft.core.view.WidgetPlugin.PushingBehavior;

public class TranslateBreakpoint extends DemoBreakPoint {

	public TranslateBreakpoint(JenSoftBreackpointExecutor jenSoftDemoExecutor, View view) {
		super(jenSoftDemoExecutor, view);
	}

	@Override
	protected DemoMessage getMessage() {
		DemoMessage msg = new DemoMessage("Continue and install translate.");
		msg.setSize(320, 110);
		msg.setTitle("JenSoft API - Translate");
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
				view.getWidgetPlugin().pushMessage("Install Translate", 200, null, PushingBehavior.Fast, f, Color.BLACK);

				wait(500);
				Projection proj = jenSoftDemoExecutor.getDefaultProjection();

				TranslatePlugin translate = new TranslatePlugin();
				proj.registerPlugin(translate);

				translate.registerWidget(new TranslateCompassWidget());

				TranslateX tx = new TranslateX(80, 14);
				TranslateY ty = new TranslateY(14, 80);
				tx.setOutlineColor(ColorPalette.BLACK);
				tx.setOutlineStroke(new BasicStroke(1.2f));
				tx.setButtonFillColor(RosePalette.CALYPSOBLUE);
				tx.setButtonDrawColor(null);
				tx.setShader(new Shader(new float[] { 0f, 1f }, new Color[] { new Color(30, 30, 30, 140), new Color(10, 10, 10, 255) }));
				ty.setOutlineColor(ColorPalette.BLACK);
				ty.setOutlineStroke(new BasicStroke(1.2f));
				ty.setButtonFillColor(RosePalette.CALYPSOBLUE);
				ty.setButtonDrawColor(null);
				ty.setShader(new Shader(new float[] { 0f, 1f }, new Color[] { new Color(30, 30, 30, 140), new Color(10, 10, 10, 255) }));
				translate.registerWidget(tx, ty);

				wait(800);
				translate.lockSelected();
				view.repaint();
				Thread.sleep(1000);

				translate.shift(ShiftDirection.East, 20);
				translate.joinShifting();
				Thread.sleep(200);

				translate.shift(ShiftDirection.West, 20);
				translate.joinShifting();
				Thread.sleep(200);
				
				translate.shift(ShiftDirection.North, 20);
				translate.joinShifting();
				Thread.sleep(200);
				
				translate.shift(ShiftDirection.South, 20);
				translate.joinShifting();
				Thread.sleep(200);
				
				proj.unregisterPlugin(translate);

			}
		} catch (InterruptedException e) {
		} finally {
			System.out.println("out executor : " + getClass().getSimpleName());
		}
	}

}
