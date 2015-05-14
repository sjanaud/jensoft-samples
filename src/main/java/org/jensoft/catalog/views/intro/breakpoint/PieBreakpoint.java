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
import org.jensoft.core.drawable.text.TextPath.PathSide;
import org.jensoft.core.drawable.text.TextPath.TextPosition;
import org.jensoft.core.palette.color.ColorPalette;
import org.jensoft.core.palette.color.RosePalette;
import org.jensoft.core.palette.color.TangoPalette;
import org.jensoft.core.plugin.pie.Pie;
import org.jensoft.core.plugin.pie.PiePlugin;
import org.jensoft.core.plugin.pie.PieSlice;
import org.jensoft.core.plugin.pie.PieToolkit;
import org.jensoft.core.plugin.pie.painter.draw.PieDefaultDraw;
import org.jensoft.core.plugin.pie.painter.effect.PieCompoundEffect;
import org.jensoft.core.plugin.pie.painter.effect.PieLinearEffect;
import org.jensoft.core.plugin.pie.painter.effect.PieLinearEffect.Effect1ShiftIncidence;
import org.jensoft.core.plugin.pie.painter.effect.PieReflectionEffect;
import org.jensoft.core.plugin.pie.painter.label.AbstractPieSliceLabel.Style;
import org.jensoft.core.plugin.pie.painter.label.PieBorderLabel;
import org.jensoft.core.plugin.pie.painter.label.PieBorderLabel.LinkStyle;
import org.jensoft.core.plugin.pie.painter.label.PiePathLabel;
import org.jensoft.core.plugin.pie.painter.label.PieRadialLabel;
import org.jensoft.core.projection.Projection;
import org.jensoft.core.view.View;
import org.jensoft.core.view.WidgetPlugin.PushingBehavior;

public class PieBreakpoint extends DemoBreakPoint {

	public PieBreakpoint(JenSoftBreackpointExecutor jenSoftDemoExecutor, View view) {
		super(jenSoftDemoExecutor, view);
	}

	@Override
	protected DemoMessage getMessage() {
		DemoMessage msg = new DemoMessage("Continue and install pie.");
		msg.setSize(320, 110);
		msg.setTitle("JenSoft API - Pie");
		msg.setMessageTitleColor(Color.WHITE);
		msg.setMessageForeground(Color.WHITE);
		return msg;
	}

	@Override
	public void run() {
		super.run();
		try {
			synchronized (this) {

				jenSoftDemoExecutor.getDefaultProjection().setVisible(false);
				view.repaint();
				Font f =  new Font("Dialog", Font.PLAIN, 14);
				view.getWidgetPlugin().pushMessage("Install Pie", 500, null, PushingBehavior.Fast, f, Color.BLACK);
				wait(1000);
				Projection pieWindow = new Projection.Linear(-1, 1, -1, 1);
				view.registerProjection(pieWindow);

				Pie pie = PieToolkit.createPie("pie", 90);

				PieLinearEffect ple = new PieLinearEffect();
				ple.setOffsetRadius(5);
				pie.setPieEffect(ple);
				PieSlice s1 = PieToolkit.createSlice("s1", new Color(240, 240, 240, 240), 45, 0);
				PieSlice s2 = PieToolkit.createSlice("s2", ColorPalette.alpha(TangoPalette.BUTTER2, 240), 5, 0);
				PieSlice s3 = PieToolkit.createSlice("s3", ColorPalette.alpha(TangoPalette.CHAMELEON2, 240), 30, 0);
				PieSlice s4 = PieToolkit.createSlice("s4", ColorPalette.alpha(TangoPalette.SKYBLUE2, 240), 20, 0);
				PieToolkit.pushSlices(pie, s1, s2, s3, s4);

				PiePlugin piePlugin = new PiePlugin();
				pieWindow.registerPlugin(piePlugin);

				piePlugin.addPie(pie);

				pieWindow.getView().repaintDevice();

				Effect1ShiftIncidence inc = PieLinearEffect.shiftIncidence(pie);
				wait(2000);
				inc.interrupt();

				class SliceDiv extends Thread {

					PieSlice s;

					@Override
					public void run() {
						for (int i = 0; i <= 20; i++) {
							s.setDivergence(i);
							view.repaintDevice();
							try {
								Thread.sleep(5);
							} catch (InterruptedException e) {
							}
						}
					}
				}

				SliceDiv sd = new SliceDiv();
				sd.s = s1;
				sd.start();
				sd.join();
				sd = new SliceDiv();
				sd.s = s2;
				sd.start();
				sd.join();
				sd = new SliceDiv();
				sd.s = s3;
				sd.start();
				sd.join();
				sd = new SliceDiv();
				sd.s = s4;
				sd.start();
				sd.join();

				wait(500);

				PieDefaultDraw draw = new PieDefaultDraw();
				draw.setDrawColor(RosePalette.MANDARIN.brighter());
				draw.setDrawStroke(new BasicStroke(2f));

				for (int i = 0; i < 5; i++) {
					pie.setPieDraw(draw);
					view.repaintDevice();
					wait(100);
					pie.setPieDraw(null);
					view.repaintDevice();
					wait(100);
				}

				for (int i = 20; i >= 0; i--) {
					s1.setDivergence(i);
					s2.setDivergence(i);
					s3.setDivergence(i);
					s4.setDivergence(i);
					view.repaintDevice();
					wait(5);
				}
				wait(800);

				// sub effect 1
				PieLinearEffect linearEffect = new PieLinearEffect();
				linearEffect.setOffsetRadius(10);
				linearEffect.setIncidenceAngleDegree(300);

				// sub effect 2
				PieReflectionEffect reflection = new PieReflectionEffect();
				reflection.setLength(0.4f);
				reflection.setOpacity(0.35f);

				// compound
				PieCompoundEffect effect = new PieCompoundEffect(ple, reflection);
				pie.setPieEffect(effect);
				view.repaintDevice();
				wait(300);

				// LABELS
				float[] fractions = { 0f, 0.5f, 1f };
				Color[] colors = { new Color(0, 0, 0, 100), new Color(0, 0, 0, 255), new Color(0, 0, 0, 255) };
				Stroke s = new BasicStroke(2);
				pie.setPassiveLabelAtMinPercent(0);
				 f =  new Font("Dialog", Font.PLAIN, 12);
				// LABEL 1
				PieRadialLabel label1 = PieToolkit.createRadialLabel("Welcome", ColorPalette.WHITE, f, 20);
				label1.setStyle(Style.Both);
				label1.setOutlineStroke(s);
				label1.setShader(fractions, colors);
				label1.setOutlineColor(RosePalette.REDWOOD);
				label1.setOutlineRound(20);
				s1.addSliceLabel(label1);
				view.repaintDevice();
				wait(300);
				// LABEL 2
				PieRadialLabel label2 = PieToolkit.createRadialLabel("JenSoft", ColorPalette.WHITE, f, 20);
				label2.setStyle(Style.Both);
				label2.setOutlineStroke(s);
				label2.setShader(fractions, colors);
				label2.setOutlineColor(RosePalette.LIME);
				label2.setOutlineRound(20);
				s2.addSliceLabel(label2);
				view.repaintDevice();

				// LABEL 3
				PieRadialLabel label3 = PieToolkit.createRadialLabel("API", ColorPalette.WHITE, f, 20);
				label3.setStyle(Style.Both);
				label3.setOutlineStroke(s);
				label3.setShader(fractions, colors);
				label3.setOutlineColor(RosePalette.EMERALD);
				label3.setOutlineRound(20);
				s3.addSliceLabel(label3);
				view.repaintDevice();
				wait(300);

				PieRadialLabel label4 = PieToolkit.createRadialLabel("for Java", ColorPalette.WHITE, f, 20);
				label4.setStyle(Style.Both);
				label4.setOutlineStroke(s);
				label4.setOutlineColor(RosePalette.COBALT);
				label4.setShader(fractions, colors);
				label4.setOutlineRound(20);
				s4.addSliceLabel(label4);
				view.repaintDevice();

				Effect1ShiftIncidence incshift = PieLinearEffect.shiftIncidence(pie);
				for (int i = 0; i <= 180; i = i + 2) {
					pie.setStartAngleDegree(i);
					view.repaintDevice();
					wait(10);
				}
				wait(500);

				s1.removeAllSliceLabels();
				s2.removeAllSliceLabels();
				s3.removeAllSliceLabels();
				s4.removeAllSliceLabels();

				view.repaintDevice();
				wait(300);

				// Border label
				// LABEL 1
				PieBorderLabel blabel1 = PieToolkit.createBorderLabel("View", ColorPalette.WHITE, f, 30);
				blabel1.setStyle(Style.Both);
				blabel1.setOutlineStroke(s);
				blabel1.setShader(fractions, colors);
				blabel1.setOutlineColor(RosePalette.REDWOOD);
				blabel1.setOutlineRound(20);
				blabel1.setLinkColor(RosePalette.REDWOOD);
				blabel1.setLinkStyle(org.jensoft.core.plugin.pie.painter.label.PieBorderLabel.LinkStyle.Quad);
				blabel1.setLinkExtends(40);
				blabel1.setMargin(30);
				s1.addSliceLabel(blabel1);

				// LABEL 2
				PieBorderLabel blabel2 = PieToolkit.createBorderLabel("Window", ColorPalette.WHITE, f, 30);
				blabel2.setStyle(Style.Both);
				blabel2.setOutlineStroke(s);
				blabel2.setShader(fractions, colors);
				blabel2.setOutlineColor(RosePalette.LIME);
				blabel2.setOutlineRound(20);
				blabel2.setLinkColor(RosePalette.LIME);
				blabel2.setLinkExtends(40);
				blabel2.setLinkStyle(LinkStyle.Quad);
				blabel2.setMargin(30);
				s2.addSliceLabel(blabel2);

				// LABEL 3
				PieBorderLabel blabel3 = PieToolkit.createBorderLabel("plugin", ColorPalette.WHITE, f, 30);
				blabel3.setStyle(Style.Both);
				blabel3.setOutlineStroke(s);
				blabel3.setShader(fractions, colors);
				blabel3.setOutlineColor(RosePalette.EMERALD);
				blabel3.setOutlineRound(20);
				blabel3.setLinkColor(RosePalette.EMERALD);
				blabel3.setLinkStyle(LinkStyle.Quad);
				blabel3.setLinkExtends(40);
				blabel3.setMargin(30);
				s3.addSliceLabel(blabel3);

				PieBorderLabel blabel4 = PieToolkit.createBorderLabel("widget", ColorPalette.WHITE, f, 30);
				blabel4.setStyle(Style.Both);
				blabel4.setOutlineStroke(s);
				blabel4.setOutlineColor(RosePalette.COBALT);
				blabel4.setShader(fractions, colors);
				blabel4.setOutlineRound(20);
				blabel4.setLinkColor(RosePalette.COBALT);
				blabel4.setLinkStyle(LinkStyle.Quad);
				blabel4.setLinkExtends(40);
				blabel4.setMargin(30);
				s4.addSliceLabel(blabel4);

				for (int i = 30; i <= 150; i = i + 5) {
					blabel1.setMargin(i);
					blabel2.setMargin(i);
					blabel3.setMargin(i);
					blabel4.setMargin(i);
					view.repaintDevice();
					wait(10);
				}

				wait(500);
				incshift.interrupt();
				wait(500);

				for (int i = 0; i <= 360; i = i + 2) {
					pie.setStartAngleDegree(i);
					view.repaintDevice();
					wait(10);
				}
				wait(500);

				pie.removeAllSliceLabels();

				view.repaintDevice();
				wait(500);
				Font f12 =  new Font("Dialog", Font.PLAIN, 12);
				
				PiePathLabel ppl = new PiePathLabel(TextPosition.Right, "My name is S�bastien");
				ppl.setPathSide(PathSide.Below);
				ppl.setLabelFont(f12);
				ppl.setLabelColor(RosePalette.MANDARIN);
				ppl.setDivergence(2);
				s1.addSliceLabel(ppl);

				PiePathLabel ppl12 = PieToolkit.createPathLabel("JenSoft API", RosePalette.EMERALD, f12, TextPosition.Middle);
				s1.addSliceLabel(ppl12);

				PiePathLabel ppl3 = new PiePathLabel(TextPosition.Left, "Pie Path Label", TangoPalette.CHAMELEON2.darker());
				float[] fractions2 = { 0f, 1f };
				Color[] colors2 = { Color.BLACK, RosePalette.AMETHYST };
				ppl3.setLabelFont(f12);
				ppl3.setTextShader(fractions2, colors2);
				ppl3.setPathSide(PathSide.Above);
				ppl3.setDivergence(2);

				s3.addSliceLabel(ppl3);

				view.repaint();

				wait(800);

				for (int i = 10; i <= 200; i = i + 4) {
					//System.out.println("i : " + i);
					ppl.setOffsetRight(i);
					ppl3.setOffsetLeft(i);
					view.repaint();
					wait(10);
				}

				for (int i = 10; i <= 30; i = i + 1) {
					ppl12.setDivergence(ppl12.getDivergence() - 1);
					view.repaint();
					wait(15);
				}
				ppl12.setLabelColor(Color.BLACK);
				Font f16 =  new Font("Dialog", Font.PLAIN, 16);
				ppl12.setLabelFont(f16);
				view.repaint();

				pie.removeAllSliceLabels();
				wait(200);

				for (int i = 0; i < 600; i = i + 10) {
					s1.setDivergence(i);
					s2.setDivergence(i);
					s3.setDivergence(i);
					s4.setDivergence(i);
					wait(20);
					view.repaint();
				}

				view.unregisterProjection(pieWindow);
				jenSoftDemoExecutor.getDefaultProjection().setVisible(true);
				view.repaint();

			}
		} catch (InterruptedException e) {
		} finally {
			System.out.println("out executor : " + getClass().getSimpleName());
		}
	}

}
