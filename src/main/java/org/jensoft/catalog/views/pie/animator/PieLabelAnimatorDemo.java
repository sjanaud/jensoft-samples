/*
 * JenSoft API - Charting Framework
 * http://www.jensoftapi.com
 * Copyright (c) JenSoft. All rights reserved.
 * See JenSoft Software License Agreement
 */
package org.jensoft.catalog.views.pie.animator;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Stroke;

import org.jensoft.core.catalog.nature.JenSoftView;
import org.jensoft.core.palette.color.ColorPalette;
import org.jensoft.core.palette.color.RosePalette;
import org.jensoft.core.palette.color.TangoPalette;
import org.jensoft.core.plugin.pie.Pie;
import org.jensoft.core.plugin.pie.Pie.PieNature;
import org.jensoft.core.plugin.pie.PiePlugin;
import org.jensoft.core.plugin.pie.PieSlice;
import org.jensoft.core.plugin.pie.PieToolkit;
import org.jensoft.core.plugin.pie.animator.PieLabelAnimator;
import org.jensoft.core.plugin.pie.painter.effect.PieSliceLinearEffect;
import org.jensoft.core.plugin.pie.painter.fill.PieDefaultFill;
import org.jensoft.core.plugin.pie.painter.label.AbstractPieSliceLabel.Style;
import org.jensoft.core.plugin.pie.painter.label.PieRadialLabel;
import org.jensoft.core.plugin.translate.TranslatePlugin;
import org.jensoft.core.plugin.zoom.box.ZoomBoxPlugin;
import org.jensoft.core.plugin.zoom.percent.ZoomPercentPlugin;
import org.jensoft.core.projection.Projection;
import org.jensoft.core.view.View;

@JenSoftView(description="Pie with label animator.")
public class PieLabelAnimatorDemo extends View {

	private static final long serialVersionUID = 7218951487343357585L;

	public PieLabelAnimatorDemo() {
		super();

		Projection proj = new Projection.Linear.Identity();

		registerProjection(proj);

		PiePlugin piePlugin = new PiePlugin();

		Pie pie = new Pie();
		// define pie center in user projection
		pie.setPieNature(PieNature.User);
		pie.setCenterX(0);
		pie.setCenterY(0);
		pie.setRadius(120);
		pie.setPieFill(new PieDefaultFill());
		pie.setStartAngleDegree(0);

		PieSlice s1 = new PieSlice("white", new Color(240, 240, 240, 240));
		s1.setValue(45);
		s1.setDivergence(0);
		PieSliceLinearEffect pse1 = new PieSliceLinearEffect();
		pse1.setIncidenceAngleDegree(120);
		pse1.setOffsetRadius(10);
		s1.setSliceEffect(pse1);

		PieSlice s2 = new PieSlice("butter", ColorPalette.alpha(TangoPalette.BUTTER2, 240));
		s2.setValue(5.0);
		s2.setDivergence(0);
		s2.setSliceEffect(pse1);

		PieSlice s3 = new PieSlice("chameleon", ColorPalette.alpha(TangoPalette.CHAMELEON2, 240));
		s3.setValue(30.0);
		s3.setDivergence(0);
		s3.setSliceEffect(pse1);

		PieSlice s4 = new PieSlice("sky blue", ColorPalette.alpha(TangoPalette.SKYBLUE2, 240));
		s4.setValue(20.0);
		s4.setDivergence(0);
		s4.setSliceEffect(pse1);

		pie.addSlice(s1);
		pie.addSlice(s2);
		pie.addSlice(s3);
		pie.addSlice(s4);

		piePlugin.addPie(pie);

		// LABELS
		float[] fractions = { 0f, 0.5f, 1f };
		Color[] colors = { new Color(0, 0, 0, 100), new Color(0, 0, 0, 255), new Color(0, 0, 0, 255) };
		Stroke s = new BasicStroke(2);
		pie.setPassiveLabelAtMinPercent(0);

		Font f =  new Font("Dialog", Font.PLAIN, 12);
		
		// LABEL 1
		PieRadialLabel label1 = PieToolkit.createRadialLabel("Silver", ColorPalette.WHITE, f, 20);
		label1.setStyle(Style.Both);
		label1.setOutlineStroke(s);
		label1.setShader(fractions, colors);
		label1.setOutlineColor(RosePalette.REDWOOD);
		label1.setOutlineRound(20);

		// LABEL 2
		PieRadialLabel label2 = PieToolkit.createRadialLabel("Platinium", ColorPalette.WHITE, f, 20);
		label2.setStyle(Style.Both);
		label2.setOutlineStroke(s);
		label2.setShader(fractions, colors);
		label2.setOutlineColor(RosePalette.LIME);
		label2.setOutlineRound(20);

		// LABEL 3
		PieRadialLabel label3 = PieToolkit.createRadialLabel("Rhodium", ColorPalette.WHITE, f, 20);
		label3.setStyle(Style.Both);
		label3.setOutlineStroke(s);
		label3.setShader(fractions, colors);
		label3.setOutlineColor(RosePalette.COBALT);
		label3.setOutlineRound(20);

		PieRadialLabel label4 = PieToolkit.createRadialLabel("Uranium", ColorPalette.WHITE, f, 20);
		label4.setStyle(Style.Both);
		label4.setOutlineStroke(s);
		label4.setOutlineColor(RosePalette.EMERALD);
		label4.setShader(fractions, colors);
		label4.setOutlineRound(20);

		pie.addPieAnimator(new PieLabelAnimator(s1, label1));
		pie.addPieAnimator(new PieLabelAnimator(s2, label2));
		pie.addPieAnimator(new PieLabelAnimator(s3, label3));
		pie.addPieAnimator(new PieLabelAnimator(s4, label4));

		proj.registerPlugin(piePlugin);

		ZoomBoxPlugin zoomTool = new ZoomBoxPlugin();
		proj.registerPlugin(zoomTool);

		TranslatePlugin toolTranslate = new TranslatePlugin();
		proj.registerPlugin(toolTranslate);

		ZoomPercentPlugin percent = new ZoomPercentPlugin();
		proj.registerPlugin(percent);
	}
}
