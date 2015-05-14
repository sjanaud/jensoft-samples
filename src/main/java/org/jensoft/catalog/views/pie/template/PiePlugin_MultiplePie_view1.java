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
import org.jensoft.core.drawable.text.TextPath.PathSide;
import org.jensoft.core.drawable.text.TextPath.TextPosition;
import org.jensoft.core.palette.color.ColorPalette;
import org.jensoft.core.palette.color.RosePalette;
import org.jensoft.core.palette.color.Spectral;
import org.jensoft.core.palette.color.TangoPalette;
import org.jensoft.core.plugin.pie.Pie;
import org.jensoft.core.plugin.pie.PiePlugin;
import org.jensoft.core.plugin.pie.PieSlice;
import org.jensoft.core.plugin.pie.PieToolkit;
import org.jensoft.core.plugin.pie.animator.PieDivergenceAnimator;
import org.jensoft.core.plugin.pie.painter.effect.PieLinearEffect;
import org.jensoft.core.plugin.pie.painter.label.AbstractPieSliceLabel.Style;
import org.jensoft.core.plugin.pie.painter.label.PieBorderLabel;
import org.jensoft.core.plugin.pie.painter.label.PieBorderLabel.LinkStyle;
import org.jensoft.core.plugin.pie.painter.label.PiePathLabel;
import org.jensoft.core.plugin.pie.painter.label.PieRadialLabel;
import org.jensoft.core.projection.Projection;
import org.jensoft.core.view.View;

@JenSoftView(description="Show how to use multiple pie.")
public class PiePlugin_MultiplePie_view1 extends View {

	private static final long serialVersionUID = 1804070007766876066L;

	public PiePlugin_MultiplePie_view1() {
		super(0);
		Projection.Linear proj = new Projection.Linear(-1, 1, -1, 1);
		proj.setName("compatible donut3D proj");
		registerProjection(proj);

		PiePlugin piePlugin = new PiePlugin();
		proj.registerPlugin(piePlugin);
		proj.bound(-4, 4, -4, 4);

		Pie pie = PieToolkit.createPie("pie1", 50);
		pie.setCenterX(-2);
		pie.setCenterY(-2);
		pie.setPieEffect(new PieLinearEffect(40, 4));
		PieSlice s1 = PieToolkit.createSlice("gray", new Color(240, 240, 240, 240), 45, 0);
		PieSlice s2 = PieToolkit.createSlice("gray", ColorPalette.alpha(TangoPalette.BUTTER2, 240), 5, 0);
		PieSlice s3 = PieToolkit.createSlice("chameleon", ColorPalette.alpha(TangoPalette.CHAMELEON2, 240), 30, 0);
		PieSlice s4 = PieToolkit.createSlice("blue", ColorPalette.alpha(TangoPalette.SKYBLUE2, 240), 20, 0);
		PieToolkit.pushSlices(pie, s1, s2, s3, s4);
		piePlugin.addPie(pie);

		pie = PieToolkit.createPie("pie2", 50);
		pie.setCenterX(-2);
		pie.setCenterY(2);
		pie.setPieEffect(new PieLinearEffect(80, 4));
		s1 = PieToolkit.createSlice("gray", new Color(240, 240, 240, 240), 45, 0);
		s2 = PieToolkit.createSlice("gray", ColorPalette.alpha(Spectral.SPECTRAL_RED, 240), 5, 0);
		s3 = PieToolkit.createSlice("chameleon", ColorPalette.alpha(Spectral.SPECTRAL_BLUE1, 240), 30, 0);
		s4 = PieToolkit.createSlice("blue", ColorPalette.alpha(Spectral.SPECTRAL_PURPLE1, 240), 20, 0);
		PieToolkit.pushSlices(pie, s1, s2, s3, s4);
		piePlugin.addPie(pie);

		Font f =  new Font("Dialog", Font.PLAIN, 12);
		
		PiePathLabel ppl = new PiePathLabel(TextPosition.Right, "My name is S�bastien");
		ppl.setPathSide(PathSide.Below);
		ppl.setLabelFont(f);
		ppl.setLabelColor(RosePalette.MANDARIN);
		ppl.setDivergence(2);
		s1.addSliceLabel(ppl);

		PiePathLabel ppl12 = PieToolkit.createPathLabel("JenSoft API", RosePalette.INDIGO, f, TextPosition.Middle);
		s1.addSliceLabel(ppl12);

		PiePathLabel ppl3 = new PiePathLabel(TextPosition.Left, "Pie Path Label", TangoPalette.CHAMELEON2.darker());
		float[] fractions = { 0f, 1f };
		Color[] colors = { Color.BLACK, RosePalette.AMETHYST };
		ppl3.setLabelFont(f);
		ppl3.setTextShader(fractions, colors);
		ppl3.setPathSide(PathSide.Above);
		ppl3.setDivergence(2);

		s3.addSliceLabel(ppl3);

		pie = PieToolkit.createPie("pie3", 50);
		pie.setCenterX(2);
		pie.setCenterY(-2);
		pie.setPieEffect(new PieLinearEffect(180, 4));
		s1 = PieToolkit.createSlice("gray", new Color(240, 240, 240, 240), 45, 0);
		s2 = PieToolkit.createSlice("gray", ColorPalette.alpha(TangoPalette.BUTTER2, 240), 5, 0);
		s3 = PieToolkit.createSlice("chameleon", ColorPalette.alpha(TangoPalette.CHAMELEON2, 240), 30, 0);
		s4 = PieToolkit.createSlice("blue", ColorPalette.alpha(TangoPalette.SKYBLUE2, 240), 20, 0);
		PieToolkit.pushSlices(pie, s1, s2, s3, s4);
		piePlugin.addPie(pie);

		// LABELS
		float[] fractions2 = { 0f, 0.5f, 1f };
		Color[] colors2 = { new Color(0, 0, 0, 100), new Color(0, 0, 0, 255), new Color(0, 0, 0, 255) };
		Stroke s = new BasicStroke(2);
		pie.setPassiveLabelAtMinPercent(0);

		// LABEL 1
		PieBorderLabel label1 = PieToolkit.createBorderLabel("View", ColorPalette.WHITE, f, 30);
		label1.setStyle(Style.Both);
		label1.setOutlineStroke(s);
		label1.setShader(fractions2, colors2);
		label1.setOutlineColor(RosePalette.REDWOOD);
		label1.setOutlineRound(20);
		label1.setLinkColor(RosePalette.REDWOOD);
		label1.setLinkStyle(LinkStyle.Quad);
		label1.setLinkExtends(40);
		label1.setMargin(50);

		s1.addSliceLabel(label1);

		// LABEL 2
		PieBorderLabel label2 = PieToolkit.createBorderLabel("Window", ColorPalette.WHITE, f, 30);
		label2.setStyle(Style.Both);
		label2.setOutlineStroke(s);
		label2.setShader(fractions2, colors2);
		label2.setOutlineColor(RosePalette.LIME);
		label2.setOutlineRound(20);
		label2.setLinkColor(RosePalette.LIME);
		label2.setLinkExtends(40);
		label2.setLinkStyle(LinkStyle.Quad);
		label2.setMargin(50);
		s2.addSliceLabel(label2);

		// LABEL 3
		PieBorderLabel label3 = PieToolkit.createBorderLabel("plugin", ColorPalette.WHITE, f, 30);
		label3.setStyle(Style.Both);
		label3.setOutlineStroke(s);
		label3.setShader(fractions2, colors2);
		label3.setOutlineColor(RosePalette.EMERALD);
		label3.setOutlineRound(20);
		label3.setLinkColor(RosePalette.EMERALD);
		label3.setLinkStyle(LinkStyle.Quad);
		label3.setLinkExtends(40);
		label3.setMargin(50);
		s3.addSliceLabel(label3);

		PieBorderLabel label4 = PieToolkit.createBorderLabel("widget", ColorPalette.WHITE, f, 30);
		label4.setStyle(Style.Both);
		label4.setOutlineStroke(s);
		label4.setOutlineColor(RosePalette.COBALT);
		label4.setShader(fractions2, colors2);
		label4.setOutlineRound(20);
		label4.setLinkColor(RosePalette.COBALT);
		label4.setLinkStyle(LinkStyle.Quad);
		label4.setLinkExtends(40);
		label4.setMargin(50);
		s4.addSliceLabel(label4);

		pie = PieToolkit.createPie("pie4", 50);
		pie.setCenterX(2);
		pie.setCenterY(2);
		pie.setPieEffect(new PieLinearEffect(280, 4));
		s1 = PieToolkit.createSlice("gray", new Color(240, 240, 240, 240), 45, 0);
		s2 = PieToolkit.createSlice("gray", ColorPalette.alpha(TangoPalette.BUTTER2, 240), 5, 0);
		s3 = PieToolkit.createSlice("chameleon", ColorPalette.alpha(TangoPalette.CHAMELEON2, 240), 30, 0);
		s4 = PieToolkit.createSlice("blue", ColorPalette.alpha(TangoPalette.SKYBLUE2, 240), 20, 0);
		PieToolkit.pushSlices(pie, s1, s2, s3, s4);
		piePlugin.addPie(pie);

		pie.addPieAnimator(new PieDivergenceAnimator());

		// LABEL 1
		PieRadialLabel label11 = PieToolkit.createRadialLabel("Symbian", ColorPalette.WHITE, f, 20);
		label11.setStyle(Style.Both);
		label11.setOutlineStroke(s);
		label11.setShader(fractions2, colors2);
		label11.setOutlineColor(RosePalette.REDWOOD);
		label11.setOutlineRound(20);
		s1.addSliceLabel(label11);

		// LABEL 2
		PieRadialLabel label22 = PieToolkit.createRadialLabel("WiMo", ColorPalette.WHITE, f, 20);
		label22.setStyle(Style.Both);
		label22.setOutlineStroke(s);
		label22.setShader(fractions2, colors2);
		label22.setOutlineColor(RosePalette.LIME);
		label22.setOutlineRound(20);
		s2.addSliceLabel(label22);

		// LABEL 3
		PieRadialLabel label33 = PieToolkit.createRadialLabel("iPhone", ColorPalette.WHITE, f, 20);
		label33.setStyle(Style.Both);
		label33.setOutlineStroke(s);
		label33.setShader(fractions2, colors2);
		label33.setOutlineColor(RosePalette.EMERALD);
		label33.setOutlineRound(20);
		s3.addSliceLabel(label33);

		PieRadialLabel label44 = PieToolkit.createRadialLabel("Android", ColorPalette.WHITE, f, 20);
		label44.setStyle(Style.Both);
		label44.setOutlineStroke(s);
		label44.setOutlineColor(RosePalette.COBALT);
		label44.setShader(fractions2, colors2);
		label44.setOutlineRound(20);
		s4.addSliceLabel(label44);

	}

}
