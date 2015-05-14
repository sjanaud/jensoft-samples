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
import org.jensoft.core.catalog.ui.ViewFrameUI;
import org.jensoft.core.palette.color.ColorPalette;
import org.jensoft.core.palette.color.RosePalette;
import org.jensoft.core.plugin.pie.Pie;
import org.jensoft.core.plugin.pie.Pie.PieNature;
import org.jensoft.core.plugin.pie.PiePlugin;
import org.jensoft.core.plugin.pie.PieSlice;
import org.jensoft.core.plugin.pie.PieToolkit;
import org.jensoft.core.plugin.pie.painter.effect.CubicEffectFrame;
import org.jensoft.core.plugin.pie.painter.effect.PieCompoundEffect;
import org.jensoft.core.plugin.pie.painter.effect.PieCubicEffect;
import org.jensoft.core.plugin.pie.painter.effect.PieLinearEffect;
import org.jensoft.core.plugin.pie.painter.effect.PieReflectionEffect;
import org.jensoft.core.plugin.pie.painter.fill.PieRadialFill;
import org.jensoft.core.plugin.pie.painter.label.AbstractPieSliceLabel.Style;
import org.jensoft.core.plugin.pie.painter.label.PieBorderLabel;
import org.jensoft.core.plugin.pie.painter.label.PieBorderLabel.LinkStyle;
import org.jensoft.core.projection.Projection;
import org.jensoft.core.view.View;

@JenSoftView(description="Show how to use pie border label.")
public class PiePlugin_Label_Border_view2 extends View {

	
	public static void main(String[] args) {
		ViewFrameUI ui = new ViewFrameUI(new PiePlugin_Label_Border_view2());
	}
	private static final long serialVersionUID = 156889765687899L;

	public PiePlugin_Label_Border_view2() {
		super(2);

		Projection proj = new Projection.Linear(-1, 1, -1, 1);
		registerProjection(proj);

		PiePlugin piePlugin = new PiePlugin();
		proj.registerPlugin(piePlugin);

		Pie pie = new Pie("pie", 80);
		pie.setPieNature(PieNature.User);
		pie.setCenterX(0);
		pie.setCenterY(0);
		pie.setPieFill(new PieRadialFill());
		pie.setStartAngleDegree(40);

		PieLinearEffect linearFX = new PieLinearEffect();
		linearFX.setIncidenceAngleDegree(120);
		linearFX.setOffsetRadius(5);

		PieCubicEffect cubicFX = new PieCubicEffect();
		cubicFX.setCubicKey(CubicEffectFrame.Round4.getKeyFrame());
		pie.setPieEffect(cubicFX);

		PieReflectionEffect reflectionFX = new PieReflectionEffect();
		reflectionFX.setBlurEnabled(false);
		reflectionFX.setOpacity(0.6f);
		reflectionFX.setLength(0.5f);
		reflectionFX.setReflectLabel(false);

		PieCompoundEffect compoundFX = new PieCompoundEffect(linearFX, cubicFX, reflectionFX);
		pie.setPieEffect(compoundFX);

		PieSlice s1 = PieToolkit.createSlice("s1", new Color(240, 240, 240, 240), 45, 0);
		PieSlice s2 = PieToolkit.createSlice("s2", RosePalette.COALBLACK, 5, 0);
		PieSlice s3 = PieToolkit.createSlice("s3", new Color(78, 148, 44), 30, 0);
		PieSlice s4 = PieToolkit.createSlice("s4", RosePalette.AEGEANBLUE, 5, 0);
		PieSlice s5 = PieToolkit.createSlice("s5", RosePalette.INDIGO, 5, 0);
		

		PieToolkit.pushSlices(pie, s1, s2, s3, s4,s5);
		piePlugin.addPie(pie);

		pie.setPassiveLabelAtMinPercent(18);

		// LABELS
		float[] fractions = { 0f, 0.5f, 1f };
		Color[] colors = { new Color(0, 0, 0, 100), new Color(0, 0, 0, 255), new Color(0, 0, 0, 255) };
		Stroke s = new BasicStroke(2);
		pie.setPassiveLabelAtMinPercent(0);

		Font f =  new Font("Dialog", Font.PLAIN, 12);
		// LABEL 1
		PieBorderLabel label1 = PieToolkit.createBorderLabel("SILVER", ColorPalette.WHITE, f, 30);
		label1.setStyle(Style.Both);
		label1.setOutlineStroke(s);
		label1.setShader(fractions, colors);
		label1.setOutlineColor(RosePalette.REDWOOD);
		label1.setOutlineRound(20);
		label1.setLinkColor(RosePalette.COALBLACK);
		label1.setLinkStyle(LinkStyle.Line);
		label1.setLinkExtends(30);
		label1.setMargin(60);

		s1.addSliceLabel(label1);

		// LABEL 2
		PieBorderLabel label2 = PieToolkit.createBorderLabel("RHODIUM", ColorPalette.WHITE, f, 30);
		label2.setStyle(Style.Both);
		label2.setOutlineStroke(s);
		label2.setShader(fractions, colors);
		label2.setOutlineColor(RosePalette.LIME);
		label2.setOutlineRound(20);
		label2.setLinkColor(RosePalette.COALBLACK);
		label2.setLinkExtends(30);
		label2.setLinkStyle(LinkStyle.Line);
		label2.setMargin(40);
		s2.addSliceLabel(label2);

		// LABEL 3
		PieBorderLabel label3 = PieToolkit.createBorderLabel("COPPER", ColorPalette.WHITE, f, 30);
		label3.setStyle(Style.Both);
		label3.setOutlineStroke(s);
		label3.setShader(fractions, colors);
		label3.setOutlineColor(RosePalette.EMERALD);
		label3.setOutlineRound(20);
		label3.setLinkColor(RosePalette.COALBLACK);
		label3.setLinkStyle(LinkStyle.Line);
		label3.setLinkExtends(30);
		label3.setMargin(50);
		s3.addSliceLabel(label3);

		PieBorderLabel label4 = PieToolkit.createBorderLabel("PALLADIUM", ColorPalette.WHITE, f, 30);
		label4.setStyle(Style.Both);
		label4.setOutlineStroke(s);
		label4.setOutlineColor(RosePalette.COBALT);
		label4.setShader(fractions, colors);
		label4.setOutlineRound(20);
		label4.setLinkColor(RosePalette.COALBLACK);
		label4.setLinkStyle(LinkStyle.Line);
		label4.setLinkExtends(30);
		label4.setMargin(70);
		s4.addSliceLabel(label4);
		
		PieBorderLabel label5 = PieToolkit.createBorderLabel("PLATINIUM", ColorPalette.WHITE, f, 30);
		label5.setStyle(Style.Both);
		label5.setOutlineStroke(s);
		label5.setOutlineColor(RosePalette.COBALT);
		label5.setShader(fractions, colors);
		label5.setOutlineRound(20);
		label5.setLinkColor(RosePalette.COALBLACK);
		label5.setLinkStyle(LinkStyle.Line);
		label5.setLinkExtends(30);
		label5.setMargin(70);
		s5.addSliceLabel(label5);

	}
}
