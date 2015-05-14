/*
 * JenSoft API - Charting Framework
 * http://www.jensoftapi.com
 * Copyright (c) JenSoft. All rights reserved.
 * See JenSoft Software License Agreement
 */
package org.jensoft.catalog.views.pie.template;

import java.awt.Color;
import java.awt.Font;

import org.jensoft.core.catalog.nature.JenSoftView;
import org.jensoft.core.drawable.text.TextPath.PathSide;
import org.jensoft.core.drawable.text.TextPath.TextPosition;
import org.jensoft.core.palette.color.NanoChromatique;
import org.jensoft.core.palette.color.RosePalette;
import org.jensoft.core.palette.color.TangoPalette;
import org.jensoft.core.plugin.pie.Pie;
import org.jensoft.core.plugin.pie.PiePlugin;
import org.jensoft.core.plugin.pie.PieSlice;
import org.jensoft.core.plugin.pie.PieToolkit;
import org.jensoft.core.plugin.pie.painter.effect.PieLinearEffect;
import org.jensoft.core.plugin.pie.painter.fill.PieRadialFill;
import org.jensoft.core.plugin.pie.painter.label.PiePathLabel;
import org.jensoft.core.projection.Projection;
import org.jensoft.core.view.View;

@JenSoftView(description="Show pie model")
public class PiePlugin_Header_view3 extends View {

	private static final long serialVersionUID = -2679532578203235737L;

	public PiePlugin_Header_view3() {
		super(2);
		Projection proj = new Projection.Linear.Identity();
		registerProjection(proj);

		PiePlugin piePlugin = new PiePlugin();
		proj.registerPlugin(piePlugin);
		Pie pie = PieToolkit.createPie("pie", 70);
		PieLinearEffect fx1 = new PieLinearEffect(200);		
		fx1.setOffsetRadius(5);
		pie.setPieEffect(fx1);
		pie.setPieFill(new PieRadialFill());
		PieSlice s1 = PieToolkit.createSlice("gray", new Color(240, 240, 240, 240), 45, 0);
		PieSlice s2 = PieToolkit.createSlice("gray", NanoChromatique.ORANGE, 5, 0);
		PieSlice s3 = PieToolkit.createSlice("chameleon", new Color(78, 148, 44), 30, 0);
		PieToolkit.pushSlices(pie, s1, s2, s3);
		piePlugin.addPie(pie);
	
		Font f =  new Font("Dialog", Font.PLAIN, 12);
		
		PiePathLabel ppl = new PiePathLabel(TextPosition.Right, "- Create Yourself -");
		ppl.setPathSide(PathSide.Below);
		ppl.setLabelFont(f);
		ppl.setLabelColor(RosePalette.MANDARIN);
		ppl.setDivergence(2);
		s1.addSliceLabel(ppl);

		PiePathLabel ppl12 = PieToolkit.createPathLabel("JenSoft API", RosePalette.INDIGO, f, TextPosition.Middle);
		s1.addSliceLabel(ppl12);

		PiePathLabel ppl3 = new PiePathLabel(TextPosition.Left, "Pie Path Label", TangoPalette.CHAMELEON2.darker());
		float[] fractions = { 0f, 1f };
		Color[] colors = { Color.WHITE, RosePalette.LEMONPEEL };
		ppl3.setLabelFont(new Font("lucida console",Font.PLAIN,14));
		ppl3.setTextShader(fractions, colors);
		ppl3.setPathSide(PathSide.Above);
		ppl3.setDivergence(2);

		s3.addSliceLabel(ppl3);


	}

}
