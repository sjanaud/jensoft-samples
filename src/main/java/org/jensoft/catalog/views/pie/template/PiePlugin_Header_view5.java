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
import org.jensoft.core.palette.color.TangoPalette;
import org.jensoft.core.plugin.pie.Pie;
import org.jensoft.core.plugin.pie.PiePlugin;
import org.jensoft.core.plugin.pie.PieSlice;
import org.jensoft.core.plugin.pie.PieToolkit;
import org.jensoft.core.plugin.pie.painter.effect.PieLinearEffect;
import org.jensoft.core.plugin.pie.painter.label.AbstractPieSliceLabel.Style;
import org.jensoft.core.plugin.pie.painter.label.PieBorderLabel;
import org.jensoft.core.plugin.pie.painter.label.PieBorderLabel.LinkStyle;
import org.jensoft.core.projection.Projection;
import org.jensoft.core.view.View;

@JenSoftView(description="Show pie model")
public class PiePlugin_Header_view5 extends View {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3725277910785777706L;

	public PiePlugin_Header_view5() {
		super(2);
		Projection proj = new Projection.Linear.Identity();
		registerProjection(proj);
		
		
		PiePlugin piePlugin = new PiePlugin();
		proj.registerPlugin(piePlugin);
		Pie pie = PieToolkit.createPie("pie", 50);
		
		PieLinearEffect fx1 = new PieLinearEffect(200);
		fx1.setOffsetRadius(2);
		pie.setPieEffect(fx1);
		
		PieSlice s1 = PieToolkit.createSlice("gray", new Color(240, 240, 240, 240), 45, 0);
		PieSlice s2 = PieToolkit.createSlice("gray", ColorPalette.alpha(TangoPalette.BUTTER2, 240), 5, 0);
		PieSlice s3 = PieToolkit.createSlice("chameleon", ColorPalette.alpha(TangoPalette.CHAMELEON2, 240), 30, 0);
		PieSlice s4 = PieToolkit.createSlice("blue", ColorPalette.alpha(TangoPalette.SKYBLUE2, 240), 20, 0);
		PieToolkit.pushSlices(pie, s1, s2, s3, s4);
		piePlugin.addPie(pie);

		pie.setPassiveLabelAtMinPercent(18);

		// LABELS
		float[] fractions = { 0f, 0.5f, 1f };
		Color[] colors = { new Color(0, 0, 0, 100), new Color(0, 0, 0, 255), new Color(0, 0, 0, 255) };
		Stroke s = new BasicStroke(2);
		pie.setPassiveLabelAtMinPercent(0);

		Font f =  new Font("Dialog", Font.PLAIN, 12);
		// LABEL 1
		PieBorderLabel label1 = PieToolkit.createBorderLabel("View", ColorPalette.WHITE, f, 30);
		label1.setStyle(Style.Both);
		label1.setOutlineStroke(s);
		label1.setShader(fractions, colors);
		label1.setOutlineColor(RosePalette.REDWOOD);
		label1.setOutlineRound(20);
		label1.setLinkColor(RosePalette.COALBLACK);
		label1.setLinkStyle(LinkStyle.Quad);
		label1.setLinkExtends(30);
		label1.setMargin(40);

		s1.addSliceLabel(label1);

		// LABEL 2
		PieBorderLabel label2 = PieToolkit.createBorderLabel("Window", ColorPalette.WHITE, f, 30);
		label2.setStyle(Style.Both);
		label2.setOutlineStroke(s);
		label2.setShader(fractions, colors);
		label2.setOutlineColor(RosePalette.LIME);
		label2.setOutlineRound(20);
		label2.setLinkColor(RosePalette.COALBLACK);
		label2.setLinkExtends(30);
		label2.setLinkStyle(LinkStyle.Quad);
		label2.setMargin(40);
		s2.addSliceLabel(label2);

		// LABEL 3
		PieBorderLabel label3 = PieToolkit.createBorderLabel("plugin", ColorPalette.WHITE, f, 30);
		label3.setStyle(Style.Both);
		label3.setOutlineStroke(s);
		label3.setShader(fractions, colors);
		label3.setOutlineColor(RosePalette.EMERALD);
		label3.setOutlineRound(20);
		label3.setLinkColor(RosePalette.COALBLACK);
		label3.setLinkStyle(LinkStyle.Quad);
		label3.setLinkExtends(30);
		label3.setMargin(40);
		s3.addSliceLabel(label3);

		PieBorderLabel label4 = PieToolkit.createBorderLabel("widget", ColorPalette.WHITE, f, 30);
		label4.setStyle(Style.Both);
		label4.setOutlineStroke(s);
		label4.setOutlineColor(RosePalette.COBALT);
		label4.setShader(fractions, colors);
		label4.setOutlineRound(20);
		label4.setLinkColor(RosePalette.COALBLACK);
		label4.setLinkStyle(LinkStyle.Quad);
		label4.setLinkExtends(30);
		label4.setMargin(40);
		s4.addSliceLabel(label4);

	}

}
