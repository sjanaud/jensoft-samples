/*
 * JenSoft API - Charting Framework
 * http://www.jensoftapi.com
 * Copyright (c) JenSoft. All rights reserved.
 * See JenSoft Software License Agreement
 */
package org.jensoft.catalog.views.pie.effect;

import java.awt.Color;

import org.jensoft.core.catalog.nature.JenSoftView;
import org.jensoft.core.palette.color.NanoChromatique;
import org.jensoft.core.palette.color.RosePalette;
import org.jensoft.core.plugin.pie.Pie;
import org.jensoft.core.plugin.pie.PiePlugin;
import org.jensoft.core.plugin.pie.PieSlice;
import org.jensoft.core.plugin.pie.PieToolkit;
import org.jensoft.core.plugin.pie.painter.effect.CubicEffectFrame;
import org.jensoft.core.plugin.pie.painter.effect.PieCubicEffect;
import org.jensoft.core.projection.Projection;
import org.jensoft.core.view.View;

@JenSoftView(description="Show how to use pie cubic effect.")
public class PieEffectCubicDemo extends View {

	private static final long serialVersionUID = -104766778057267661L;

	public PieEffectCubicDemo() {
		super(0);
		Projection proj = new Projection.Linear.Identity();
		registerProjection(proj);

		PiePlugin piePlugin = new PiePlugin();
		proj.registerPlugin(piePlugin);

		Pie pie = PieToolkit.createPie("pie", 90);

		PieSlice s1 = PieToolkit.createSlice("gray", new Color(60, 60, 60), 30, 0);
		PieSlice s3 = PieToolkit.createSlice("chameleon", NanoChromatique.DARK_GRAY, 30, 0);
		PieSlice s4 = PieToolkit.createSlice("blue", RosePalette.COALBLACK, 20, 0);
		PieToolkit.pushSlices(pie, s1, s3, s4);
		piePlugin.addPie(pie);

		PieCubicEffect effect3 = new PieCubicEffect();
		effect3.setCubicKey(CubicEffectFrame.Moon2.getKeyFrame());
		
		//example of key
		//CubicEffectKey keyFrame = new CubicEffectKey(20, 0.4f, 120, 0.4f);

		effect3.setStartColor(new Color(255, 255, 255, 60));
		effect3.setEndColor(new Color(255, 255, 255, 0));
		pie.setPieEffect(effect3);
		pie.setStartAngleDegree(0);
	}
}
