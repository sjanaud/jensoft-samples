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
public class PiePlugin_Label_Bound_view1 extends View {

	private static final long serialVersionUID = 574734972759385426L;

	public PiePlugin_Label_Bound_view1() {
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
		PieSlice s4 = PieToolkit.createSlice("s4", RosePalette.MANDARIN, 5, 0);

		PieToolkit.pushSlices(pie, s1, s2, s3, s4);
		piePlugin.addPie(pie);

		pie.setPassiveLabelAtMinPercent(18);

		// LABELS
				float[] fractions = { 0f, 0.5f, 1f };
				Color[] colors = { new Color(0, 0, 0, 100), new Color(0, 0, 0, 255), new Color(0, 0, 0, 255) };
				Stroke s = new BasicStroke(1);
				pie.setPassiveLabelAtMinPercent(0);

				Font font =  new Font("Dialog", Font.PLAIN, 12);
				
				// LABEL 1
				PieBoundLabel label1 = PieToolkit.createBoundLabel("JENSOFTAPI", ColorPalette.WHITE, font);
				label1.setStyle(Style.Fill);
				label1.setOutlineStroke(s);
				label1.setShader(fractions, colors);
				label1.setOutlineColor(RosePalette.LEMONPEEL);
				label1.setOutlineRound(20);
				s1.addSliceLabel(label1);

				PieBoundLabel label2 = PieToolkit.createBoundLabel("API", ColorPalette.WHITE, font);
				label2.setStyle(Style.Fill);
				label2.setOutlineStroke(s);
				label2.setShader(fractions, colors);
				label2.setOutlineColor(RosePalette.LIME);
				label2.setOutlineRound(20);
				s2.addSliceLabel(label2);

				PieBoundLabel label3 = PieToolkit.createBoundLabel("CHART", ColorPalette.WHITE, font);
				label3.setStyle(Style.Fill);
				label3.setOutlineStroke(s);
				label3.setShader(fractions, colors);
				label3.setOutlineColor(RosePalette.EMERALD);
				label3.setOutlineRound(20);
				s3.addSliceLabel(label3);

				PieBoundLabel label4 = PieToolkit.createBoundLabel("VECTOR", ColorPalette.WHITE, font);
				label4.setStyle(Style.Fill);
				label4.setOutlineStroke(s);
				label4.setShader(fractions, colors);
				label4.setOutlineColor(RosePalette.AEGEANBLUE);
				label4.setOutlineRound(20);
				s4.addSliceLabel(label4);

	}
}
