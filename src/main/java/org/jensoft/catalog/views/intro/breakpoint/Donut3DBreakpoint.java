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
import java.awt.Stroke;

import org.jensoft.catalog.views.intro.DemoBreakPoint;
import org.jensoft.catalog.views.intro.JenSoftBreackpointExecutor;
import org.jensoft.core.palette.color.ColorPalette;
import org.jensoft.core.palette.color.RosePalette;
import org.jensoft.core.palette.color.TangoPalette;
import org.jensoft.core.plugin.donut3d.Donut3D;
import org.jensoft.core.plugin.donut3d.Donut3D.Donut3DNature;
import org.jensoft.core.plugin.donut3d.Donut3DPlugin;
import org.jensoft.core.plugin.donut3d.Donut3DSlice;
import org.jensoft.core.plugin.donut3d.Donut3DToolkit;
import org.jensoft.core.plugin.donut3d.painter.label.AbstractDonut3DSliceLabel.Style;
import org.jensoft.core.plugin.donut3d.painter.label.Donut3DBorderLabel;
import org.jensoft.core.plugin.donut3d.painter.label.Donut3DBorderLabel.LinkStyle;
import org.jensoft.core.plugin.donut3d.painter.label.Donut3DRadialLabel;
import org.jensoft.core.plugin.donut3d.painter.paint.Donut3DDefaultPaint;
import org.jensoft.core.projection.Projection;
import org.jensoft.core.view.View;
import org.jensoft.core.view.WidgetPlugin.PushingBehavior;

public class Donut3DBreakpoint extends DemoBreakPoint {

	public Donut3DBreakpoint(JenSoftBreackpointExecutor jenSoftDemoExecutor, View view) {
		super(jenSoftDemoExecutor, view);
	}

	@Override
	protected DemoMessage getMessage() {
		DemoMessage msg = new DemoMessage("Continue and install donut 3D.");
		msg.setSize(320, 110);
		msg.setTitle("JenSoft API - Donut3D");
		msg.setMessageTitleColor(Color.WHITE);
		msg.setMessageForeground(Color.WHITE);
		return msg;
	}

	private Donut3DPlugin donut3dPlugin;

	private Donut3D donut3d;
	private Donut3DSlice s1;
	private Donut3DSlice s2;
	private Donut3DSlice s3;
	private Donut3DSlice s4;

	class AnimatorPie3D extends Thread {

		int sleep = 20;

		@Override
		public void run() {
			synchronized (this) {
				jenSoftDemoExecutor.getDefaultProjection().setVisible(false);
				view.repaint();
				try {

					for (int i = 0; i <= 90; i = i + 2) {
						donut3d.setTilt(i);
						view.repaint();
						Thread.sleep(sleep);
					}

					Thread.sleep(1300);
					for (int i = 90; i >= 0; i = i - 2) {
						donut3d.setTilt(i);
						view.repaint();
						Thread.sleep(sleep);
					}

					Thread.sleep(2000);

					for (int i = 0; i <= 45; i = i + 2) {
						donut3d.setTilt(i);
						view.repaint();
						Thread.sleep(sleep);
					}
					Thread.sleep(500);
					for (int i = 50; i >= 10; i--) {
						donut3d.setThickness(i);
						view.repaint();
						Thread.sleep(sleep);
					}
					Thread.sleep(500);
					for (int i = 10; i <= 50; i++) {
						donut3d.setThickness(i);
						view.repaint();
						Thread.sleep(sleep);
					}

					donut3d.setThickness(50);

					for (int i = 60; i >= 0; i--) {
						donut3d.setInnerRadius(i);

						view.repaint();
						Thread.sleep(15);
					}

					for (int i = 0; i <= 60; i++) {
						donut3d.setInnerRadius(i);

						view.repaint();
						Thread.sleep(15);
					}

					// LABELS
					float[] fractions = { 0f, 0.3f, 0.7f, 1f };
					Color[] c = { new Color(0, 0, 0, 20), new Color(0, 0, 0, 150), new Color(0, 0, 0, 150), new Color(0, 0, 0, 20) };
					Font f =  new Font("Dialog", Font.PLAIN, 12);
					// LABEL 1
					Donut3DRadialLabel label1 = Donut3DToolkit.createRadialLabel("JenSoft", RosePalette.COALBLACK, f, 30, 20, Style.Both);
					label1.setLabelColor(ColorPalette.WHITE);
					label1.setOutlineColor(RosePalette.REDWOOD);
					label1.setShader(fractions, c);
					s2.addSliceLabel(label1);

					// LABEL 2
					Donut3DRadialLabel label2 = Donut3DToolkit.createRadialLabel("API", RosePalette.COALBLACK, f, 30, 20, Style.Both);
					label2.setLabelColor(ColorPalette.WHITE);
					label2.setOutlineColor(RosePalette.LIME);
					label2.setShader(fractions, c);
					s4.addSliceLabel(label2);

					for (int i = 0; i <= 80; i++) {
						s2.setDivergence(i);
						s4.setDivergence(i);
						view.repaint();
						Thread.sleep(sleep);
					}

					for (int i = 80; i >= 0; i--) {
						s2.setDivergence(i);
						s4.setDivergence(i);
						view.repaint();
						Thread.sleep(sleep);
					}

					view.repaint();
					wait(300);

					s1.removeAllSliceLabels();
					s2.removeAllSliceLabels();
					s3.removeAllSliceLabels();
					s4.removeAllSliceLabels();

					view.repaint();
					wait(500);

					// Border label
					Color[] colors2 = { new Color(0, 0, 0, 100), new Color(0, 0, 0, 255), new Color(0, 0, 0, 255) };
					float[] fractions2 = { 0f, 0.5f, 1f };
					Stroke s = new BasicStroke(2);
					// LABEL 1
					Donut3DBorderLabel blabel1 = Donut3DToolkit.createBorderLabel("View", ColorPalette.WHITE, f, 30);
					blabel1.setStyle(Style.Both);
					blabel1.setOutlineStroke(s);
					blabel1.setShader(fractions2, colors2);
					blabel1.setOutlineColor(RosePalette.REDWOOD);
					blabel1.setOutlineRound(20);
					blabel1.setLinkColor(RosePalette.REDWOOD);
					blabel1.setLinkStyle(LinkStyle.Quad);
					blabel1.setLinkExtends(40);
					blabel1.setMargin(30);
					s1.addSliceLabel(blabel1);

					// LABEL 2
					Donut3DBorderLabel blabel2 = Donut3DToolkit.createBorderLabel("Window", ColorPalette.WHITE, f, 30);
					blabel2.setStyle(Style.Both);
					blabel2.setOutlineStroke(s);
					blabel2.setShader(fractions2, colors2);
					blabel2.setOutlineColor(RosePalette.LIME);
					blabel2.setOutlineRound(20);
					blabel2.setLinkColor(RosePalette.LIME);
					blabel2.setLinkExtends(40);
					blabel2.setLinkStyle(LinkStyle.Quad);
					blabel2.setMargin(30);
					s2.addSliceLabel(blabel2);

					// LABEL 3
					Donut3DBorderLabel blabel3 = Donut3DToolkit.createBorderLabel("plugin", ColorPalette.WHITE, f, 30);
					blabel3.setStyle(Style.Both);
					blabel3.setOutlineStroke(s);
					blabel3.setShader(fractions2, colors2);
					blabel3.setOutlineColor(RosePalette.EMERALD);
					blabel3.setOutlineRound(20);
					blabel3.setLinkColor(RosePalette.EMERALD);
					blabel3.setLinkStyle(LinkStyle.Quad);
					blabel3.setLinkExtends(40);
					blabel3.setMargin(30);
					s3.addSliceLabel(blabel3);

					Donut3DBorderLabel blabel4 = Donut3DToolkit.createBorderLabel("widget", ColorPalette.WHITE, f, 30);
					blabel4.setStyle(Style.Both);
					blabel4.setOutlineStroke(s);
					blabel4.setOutlineColor(RosePalette.COBALT);
					blabel4.setShader(fractions2, colors2);
					blabel4.setOutlineRound(20);
					blabel4.setLinkColor(RosePalette.COBALT);
					blabel4.setLinkStyle(LinkStyle.Quad);
					blabel4.setLinkExtends(40);
					blabel4.setMargin(30);
					s4.addSliceLabel(blabel4);

					for (int i = 30; i <= 150; i = i + 1) {
						blabel1.setMargin(i);
						blabel2.setMargin(i);
						blabel3.setMargin(i);
						blabel4.setMargin(i);
						view.repaint();
						wait(10);
					}

					wait(800);
					view.repaint();

					donut3d.removeAllSliceLabels();

					wait(200);
					class SliceDiv extends Thread {

						Donut3DSlice s;

						@Override
						public void run() {
							for (int i = 0; i < 600; i = i + 8) {
								s.setDivergence(i);
								view.repaintDevice();
								try {
									Thread.sleep(30);
								} catch (InterruptedException e) {
								}
							}
						}
					}
					SliceDiv sd = new SliceDiv();
					sd.s = s1;
					sd.start();

					sd = new SliceDiv();
					sd.s = s2;
					sd.start();
					wait(100);
					sd = new SliceDiv();
					sd.s = s3;
					sd.start();
					wait(100);
					sd = new SliceDiv();
					sd.s = s4;
					sd.start();
					sd.join();

					// for (int i = 0; i < 500; i =i+8) {
					// s1.setDivergence(i);
					// s2.setDivergence(i);
					// s3.setDivergence(i);
					// s4.setDivergence(i);
					// wait(20);
					// view.repaint();
					// }

					wait(200);
					view.repaint();
					jenSoftDemoExecutor.getDefaultProjection().setVisible(true);
					wait(500);

				} catch (InterruptedException e) {
					Thread.currentThread().interrupt();
				}
			}

		}

	}

	class ShiftStartAngleAnimator extends Thread {

		private int sleep = 20;

		private int startAngleDegree;
		private int endAngleDegree;

		public ShiftStartAngleAnimator(int startAngleDegree, int endAngleDegree) {
			this.startAngleDegree = startAngleDegree;
			this.endAngleDegree = endAngleDegree;
		}

		@Override
		public void run() {

			try {

				while (true) {
					for (int i = startAngleDegree; i <= endAngleDegree; i = i + 2) {
						donut3d.setStartAngleDegree(i);
						view.repaint();
						Thread.sleep(sleep);
					}
				}

			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
			}

		}

	}

	@Override
	public void run() {
		super.run();
		try {
			synchronized (this) {
				Font f =  new Font("Dialog", Font.PLAIN, 14);
				view.getWidgetPlugin().pushMessage("Install Donut3D", 200, null, PushingBehavior.Fast, f, Color.BLACK);

				wait(500);

				Projection proj = new Projection.Linear(-1, 1, -1, 1);
				proj.setName("donut3D proj");

				donut3dPlugin = new Donut3DPlugin();
				proj.registerPlugin(donut3dPlugin);

				donut3d = new Donut3D();
				donut3d.setDonut3DNature(Donut3DNature.Donut3DUser);
				donut3d.setCenterX(0);
				donut3d.setCenterY(0);
				donut3d.setOuterRadius(130);
				donut3d.setInnerRadius(60);
				donut3d.setStartAngleDegree(60);
				donut3d.setThickness(50);
				donut3d.setTilt(0);

				Donut3DDefaultPaint paint = new Donut3DDefaultPaint();
				// set shadow incidence angle degree
				paint.setIncidenceAngleDegree(120);
				// will not paint the shadow top effect
				paint.setPaintTopEffect(true);
				paint.setAlphaTop(0.9f);
				// other face effect on inner and outer face
				paint.setPaintInnerEffect(true);
				paint.setPaintOuterEffect(true);
				// use alpha 0.7 alpha value to fill section
				paint.setAlphaFill(0.7f);
				// alpha value for each face effect
				// paint.setAlphaTop(alphaTop);
				// paint.setAlphaOuter(alphaOuter);
				// paint.setAlphaInner(alphaInner);

				donut3d.setDonut3DPaint(paint);

				s1 = new Donut3DSlice("white", new Color(250, 250, 250, 255));
				s1.setValue(45);
				s2 = new Donut3DSlice("yellow", TangoPalette.BUTTER2);
				s2.setValue(5);
				s3 = new Donut3DSlice("green", TangoPalette.CHAMELEON2);
				s3.setValue(30);
				s4 = new Donut3DSlice("blue", TangoPalette.SKYBLUE2);
				s4.setValue(20);

				donut3d.addSlice(s1);
				donut3d.addSlice(s2);
				donut3d.addSlice(s3);
				donut3d.addSlice(s4);

				Donut3DToolkit.createRadialLabel("myLabel");
				donut3dPlugin.addDonut(donut3d);
				proj.registerPlugin(donut3dPlugin);
				view.registerProjection(proj);

				ShiftStartAngleAnimator shiftStart = new ShiftStartAngleAnimator(0, 359);
				shiftStart.start();

				AnimatorPie3D animator = new AnimatorPie3D();
				animator.start();

				animator.join();
				shiftStart.interrupt();

				view.unregisterProjection(proj);
				view.repaint();

			}
		} catch (InterruptedException e) {
		} finally {
			System.out.println("out executor : " + getClass().getSimpleName());
		}
	}

}
