/*
 * JenSoft API - Charting Framework
 * http://www.jensoftapi.com
 * Copyright (c) JenSoft. All rights reserved.
 * See JenSoft Software License Agreement
 */
package org.jensoft.catalog.views.pie.template;

import java.awt.BasicStroke;
import java.awt.Color;

import org.jensoft.core.catalog.nature.JenSoftView;
import org.jensoft.core.graphics.Shader;
import org.jensoft.core.palette.color.NanoChromatique;
import org.jensoft.core.palette.color.RosePalette;
import org.jensoft.core.palette.color.Spectral;
import org.jensoft.core.plugin.pie.Pie;
import org.jensoft.core.plugin.pie.PiePlugin;
import org.jensoft.core.plugin.pie.PieSlice;
import org.jensoft.core.plugin.pie.PieToolkit;
import org.jensoft.core.plugin.pie.painter.effect.PieLinearEffect;
import org.jensoft.core.plugin.pie.painter.effect.PieReflectionEffect;
import org.jensoft.core.projection.Projection;
import org.jensoft.core.view.View;
import org.jensoft.core.view.background.ViewDefaultBackground;

@JenSoftView(description = "Show how to use pie reflection effect.")
public class PiePlugin_Effect_Reflection_view14 extends View {

	private static final long serialVersionUID = 156889765687899L;

	public PiePlugin_Effect_Reflection_view14() {
		super(0);

		ViewDefaultBackground viewBackground = new ViewDefaultBackground();
		viewBackground.setShader(Shader.Night);
		viewBackground.setOutlineStroke(new BasicStroke(2.5f));
		setBackgroundPainter(viewBackground);

		Projection proj = new Projection.Linear(-1, 1, -3, 3);
		registerProjection(proj);

		PiePlugin piePlugin = new PiePlugin();
		proj.registerPlugin(piePlugin);
		// proj.registerPlugin(new OutlinePlugin(Color.black));
		Pie pie = PieToolkit.createPie("pie", 70);
		pie.setCenterY(1);
		pie.setStartAngleDegree(25);

		PieLinearEffect linearFX = new PieLinearEffect(90);
		linearFX.setOffsetRadius(4);
		pie.setPieEffect(linearFX);

		PieReflectionEffect reflectionFX = new PieReflectionEffect();
		reflectionFX.setLength(0.6f);
		reflectionFX.setOpacity(0.3f);
		pie.setPieEffect(reflectionFX);

		PieSlice s1 = PieToolkit.createSlice("s1", new Color(240, 240, 240, 240), 30, 0);
		PieSlice s2 = PieToolkit.createSlice("s2", RosePalette.AMETHYST, 15, 0);
		PieSlice s3 = PieToolkit.createSlice("s3", RosePalette.LEMONPEEL, 10, 0);
		PieSlice s4 = PieToolkit.createSlice("s4", NanoChromatique.ORANGE, 5, 0);
		PieSlice s5 = PieToolkit.createSlice("s5", Spectral.SPECTRAL_BLUE2, 20, 20);

		PieToolkit.pushSlices(pie, s1, s2, s3, s4, s5);
		piePlugin.addPie(pie);

	}

}
