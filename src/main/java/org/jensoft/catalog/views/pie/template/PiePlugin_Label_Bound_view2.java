/*
 * JenSoft API - Charting Framework
 * http://www.jensoftapi.com
 * Copyright (c) JenSoft. All rights reserved.
 * See JenSoft Software License Agreement
 */
package org.jensoft.catalog.views.pie.template;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Stroke;

import org.jensoft.core.catalog.nature.JenSoftView;
import org.jensoft.core.palette.color.ColorPalette;
import org.jensoft.core.palette.color.RosePalette;
import org.jensoft.core.plugin.pie.Pie;
import org.jensoft.core.plugin.pie.Pie.PieNature;
import org.jensoft.core.plugin.pie.PiePlugin;
import org.jensoft.core.plugin.pie.PieSlice;
import org.jensoft.core.plugin.pie.PieToolkit;
import org.jensoft.core.plugin.pie.painter.effect.PieCompoundEffect;
import org.jensoft.core.plugin.pie.painter.effect.PieLinearEffect;
import org.jensoft.core.plugin.pie.painter.effect.PieReflectionEffect;
import org.jensoft.core.plugin.pie.painter.fill.PieRadialFill;
import org.jensoft.core.plugin.pie.painter.label.AbstractPieSliceLabel.Style;
import org.jensoft.core.plugin.pie.painter.label.PieBoundLabel;
import org.jensoft.core.projection.Projection;
import org.jensoft.core.view.View;

@JenSoftView(description="Show how to use pie bound label.")
public class PiePlugin_Label_Bound_view2 extends View {

	private static final long serialVersionUID = -418906765094957049L;

	public PiePlugin_Label_Bound_view2() {
		super(2);

		Projection proj = new Projection.Linear(-1, 1, -1, 1);
		registerProjection(proj);

		PiePlugin piePlugin = new PiePlugin();
		proj.registerPlugin(piePlugin);

		Pie pie = new Pie("pie", 120);
		pie.setPieNature(PieNature.User);
		pie.setCenterX(0);
		pie.setCenterY(0);
		pie.setPieFill(new PieRadialFill());
		pie.setStartAngleDegree(40);

		PieLinearEffect linearFX = new PieLinearEffect();
		linearFX.setIncidenceAngleDegree(120);
		linearFX.setOffsetRadius(5);

		PieReflectionEffect reflectionFX = new PieReflectionEffect();
		reflectionFX.setBlurEnabled(false);
		reflectionFX.setOpacity(0.6f);
		reflectionFX.setLength(0.5f);
		reflectionFX.setReflectLabel(false);

		PieCompoundEffect compoundFX = new PieCompoundEffect(linearFX, reflectionFX);
		pie.setPieEffect(compoundFX);

		PieSlice s1 = PieToolkit.createSlice("s1", new Color(240, 240, 240, 240), 45, 0);
		PieSlice s2 = PieToolkit.createSlice("s2", RosePalette.COALBLACK, 5, 0);
		PieSlice s3 = PieToolkit.createSlice("s3", new Color(78, 148, 44), 30, 0);
		PieSlice s4 = PieToolkit.createSlice("s4", RosePalette.AEGEANBLUE, 5, 0);
		PieSlice s5 = PieToolkit.createSlice("s5", RosePalette.INDIGO, 5, 0);

		PieToolkit.pushSlices(pie, s1, s2, s3, s4, s5);
		piePlugin.addPie(pie);

		pie.setPassiveLabelAtMinPercent(18);

		// LABELS
		Stroke s = new BasicStroke(2.4f);
		pie.setPassiveLabelAtMinPercent(0);

		Font font = new Font("lucida console", Font.PLAIN, 14);

		// LABEL 1
		PieBoundLabel label1 = PieToolkit.createBoundLabel("JENSOFTAPI",  RosePalette.COALBLACK, font);
		label1.setStyle(Style.Both);
		label1.setOutlineStroke(s);
		label1.setFillColor(s1.getThemeColor().brighter().brighter());
		//label1.setShader(fractions, colors);
		label1.setOutlineColor(new Color(240, 240, 240, 240));
		label1.setOutlineRound(20);
		s1.addSliceLabel(label1);

		PieBoundLabel label2 = PieToolkit.createBoundLabel("API", ColorPalette.WHITE, font);
		label2.setStyle(Style.Both);
		label2.setOutlineStroke(s);
		label2.setFillColor(s2.getThemeColor().brighter().brighter());
		//label2.setShader(fractions, colors);
		label2.setOutlineColor(RosePalette.COALBLACK);
		label2.setOutlineRound(20);
		s2.addSliceLabel(label2);

		PieBoundLabel label3 = PieToolkit.createBoundLabel("CHART", ColorPalette.WHITE, font);
		label3.setStyle(Style.Both);
		label3.setOutlineStroke(s);
		label3.setFillColor(s3.getThemeColor().brighter().brighter());
		//label3.setShader(fractions, colors);
		label3.setOutlineColor(new Color(78, 148, 44));
		label3.setOutlineRound(20);
		s3.addSliceLabel(label3);

		PieBoundLabel label4 = PieToolkit.createBoundLabel("VECTOR", RosePalette.COALBLACK, font);
		label4.setStyle(Style.Both);
		label4.setOutlineStroke(s);
		label4.setFillColor(s4.getThemeColor().brighter().brighter());
		//label4.setShader(fractions, colors);
		label4.setOutlineColor(RosePalette.AEGEANBLUE);
		label4.setOutlineRound(20);
		s4.addSliceLabel(label4);

		PieBoundLabel label5 = PieToolkit.createBoundLabel("PIE", ColorPalette.WHITE, font);
		label5.setStyle(Style.Both);
		label5.setOutlineStroke(s);
		label5.setFillColor(s5.getThemeColor().brighter().brighter());
		//label5.setShader(fractions, colors);
		label5.setOutlineColor(RosePalette.INDIGO);
		label5.setOutlineRound(20);
		s5.addSliceLabel(label5);

	}
}
