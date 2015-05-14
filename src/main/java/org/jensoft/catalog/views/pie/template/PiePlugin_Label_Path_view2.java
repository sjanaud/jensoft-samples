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
import org.jensoft.core.palette.color.RosePalette;
import org.jensoft.core.plugin.pie.Pie;
import org.jensoft.core.plugin.pie.PiePlugin;
import org.jensoft.core.plugin.pie.PieSlice;
import org.jensoft.core.plugin.pie.PieToolkit;
import org.jensoft.core.plugin.pie.painter.effect.CubicEffectFrame;
import org.jensoft.core.plugin.pie.painter.effect.PieCompoundEffect;
import org.jensoft.core.plugin.pie.painter.effect.PieCubicEffect;
import org.jensoft.core.plugin.pie.painter.effect.PieLinearEffect;
import org.jensoft.core.plugin.pie.painter.effect.PieReflectionEffect;
import org.jensoft.core.plugin.pie.painter.fill.PieRadialFill;
import org.jensoft.core.plugin.pie.painter.label.PiePathLabel;
import org.jensoft.core.plugin.pie.painter.label.PiePathLabel.PathName;
import org.jensoft.core.projection.Projection;
import org.jensoft.core.view.View;

@JenSoftView(description="Show how to use pie path label.")
public class PiePlugin_Label_Path_view2 extends View {

	private static final long serialVersionUID = 156889765687899L;

	public PiePlugin_Label_Path_view2() {
		super(2);

		Projection proj = new Projection.Linear.Identity();
		registerProjection(proj);

		PiePlugin piePlugin = new PiePlugin();
		proj.registerPlugin(piePlugin);

		Pie pie = new Pie("pie", 120);
		pie.setPieFill(new PieRadialFill());
		pie.setStartAngleDegree(40);

		PieLinearEffect linearFX = new PieLinearEffect();
		linearFX.setIncidenceAngleDegree(120);
		linearFX.setOffsetRadius(5);
		
		PieCubicEffect cubicFX = new PieCubicEffect();
		cubicFX.setCubicKey(CubicEffectFrame.Round2.getKeyFrame());		
		pie.setPieEffect(cubicFX);
		
		PieReflectionEffect reflectionFX = new PieReflectionEffect();
		reflectionFX.setBlurEnabled(false);
		reflectionFX.setOpacity(0.6f);
		reflectionFX.setLength(0.5f);
		
		PieCompoundEffect compoundFX = new PieCompoundEffect(linearFX,cubicFX,reflectionFX);
		pie.setPieEffect(compoundFX);

		PieSlice s1 = PieToolkit.createSlice("s1", new Color(240, 240, 240, 240), 45, 0);
		PieSlice s2 = PieToolkit.createSlice("s2", RosePalette.COALBLACK, 5, 0);
		PieSlice s3 = PieToolkit.createSlice("s3", new Color(78, 148, 44), 30, 0);

		PieToolkit.pushSlices(pie, s1, s2, s3);
		piePlugin.addPie(pie);
		Font f =  new Font("Dialog", Font.PLAIN, 12);
		
		PiePathLabel ppl0 = new PiePathLabel(TextPosition.Right, "COPPER");
		ppl0.setPathSide(PathSide.Below);
		//ppl0.setAutoReverse(true);
		//ppl0.setLockReverse(true);
		ppl0.setLabelFont(f);
		ppl0.setLabelColor(RosePalette.INDIGO);
		ppl0.setDivergence(2);
		s1.addSliceLabel(ppl0);

		PiePathLabel ppl = new PiePathLabel(TextPosition.Left, "RHODIUM");
		ppl.setPathSide(PathSide.Below);
		ppl.setAutoReverse(true);
		ppl.setLockReverse(true);
		ppl.setLabelFont(f);
		ppl.setLabelColor(new Color(78, 148, 44));
		ppl.setDivergence(2);
		s1.addSliceLabel(ppl);

		PiePathLabel ppl12 = PieToolkit.createPathLabel("PERTH MINT", RosePalette.INDIGO, f, TextPosition.Middle);
		s1.addSliceLabel(ppl12);

		PiePathLabel ppl3 = new PiePathLabel(TextPosition.Left, "ROYAL CANADIAN MINT", RosePalette.AMETHYST);
		float[] fractions = { 0f, 1f };
		Color[] colors = { Color.WHITE, Color.WHITE };
		ppl3.setLabelFont(f);
		ppl3.setDivergence(14);
		ppl3.setTextShader(fractions, colors);
		ppl3.setPathSide(PathSide.Above);
		s3.addSliceLabel(ppl3);
		//ppl3.setDivergence(2);
		
		ppl = new PiePathLabel(TextPosition.Middle, "SILVER", RosePalette.AMETHYST);
		ppl.setPathName(PathName.Start);
		ppl.setLabelFont(f);		
		ppl.setTextShader(fractions, colors);
		ppl.setPathSide(PathSide.Below);
		s1.addSliceLabel(ppl);
		
		ppl = new PiePathLabel(TextPosition.Middle, "PLATINIUM", RosePalette.AMETHYST);
		ppl.setPathName(PathName.Start);
		ppl.setLabelFont(f);
		Color[] colors2 = { new Color(78, 148, 44), RosePalette.COALBLACK };
		ppl.setTextShader(fractions, colors2);
		ppl.setPathSide(PathSide.Above);
		s1.addSliceLabel(ppl);
		

	}
}
