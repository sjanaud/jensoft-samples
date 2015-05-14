/*
 * JenSoft API - Charting Framework
 * http://www.jensoftapi.com
 * Copyright (c) JenSoft. All rights reserved.
 * See JenSoft Software License Agreement
 */
package org.jensoft.catalog.views.pie.template;

import org.jensoft.core.catalog.nature.JenSoftView;
import org.jensoft.core.palette.color.RosePalette;
import org.jensoft.core.palette.color.Spectral;
import org.jensoft.core.plugin.pie.Pie;
import org.jensoft.core.plugin.pie.PiePlugin;
import org.jensoft.core.plugin.pie.PieSlice;
import org.jensoft.core.plugin.pie.painter.effect.CubicEffectFrame;
import org.jensoft.core.plugin.pie.painter.effect.PieCubicEffect;
import org.jensoft.core.plugin.pie.painter.effect.PieLinearEffect;
import org.jensoft.core.plugin.pie.painter.fill.PieRadialFill;
import org.jensoft.core.projection.Projection;
import org.jensoft.core.view.View;

@JenSoftView(description="Show how to use cubic effect.")
public class PiePlugin_Effect_Cubic_view15 extends View {

	private static final long serialVersionUID = -4880145155991422854L;

	public PiePlugin_Effect_Cubic_view15() {
		super(2);
		
		Projection proj = new Projection.Linear.Identity();
		registerProjection(proj);
		
		PiePlugin piePlugin = new PiePlugin();

		Pie pie = new Pie();
		pie.setRadius(70);
		pie.setPieFill(new PieRadialFill());
		
		pie.setStartAngleDegree(90);
		
		PieLinearEffect eff1 = new PieLinearEffect();		
		eff1.setIncidenceAngleDegree(90);
		eff1.setOffsetRadius(5);
		
		
		PieCubicEffect cubicFX = new PieCubicEffect();
		cubicFX.setCubicKey(CubicEffectFrame.Wave1.getKeyFrame());		
		pie.setPieEffect(cubicFX);

		

		PieSlice s1 = new PieSlice("gris", RosePalette.FOXGLOWE);
		s1.setValue(45);
		s1.setDivergence(0);

		PieSlice s2 = new PieSlice("bleu", Spectral.SPECTRAL_PURPLE1);
		s2.setValue(8.0);
		s2.setDivergence(0);

		PieSlice s3 = new PieSlice("bleu", Spectral.SPECTRAL_PURPLE2);
		s3.setValue(15.0);
		s3.setDivergence(0);


		PieSlice s4 = new PieSlice("purple", Spectral.SPECTRAL_BLUE1);
		s4.setValue(30.0);
		s4.setDivergence(0);

		pie.addSlice(s1);
		pie.addSlice(s2);
		pie.addSlice(s3);
		pie.addSlice(s4);

		piePlugin.addPie(pie);

		proj.registerPlugin(piePlugin);
	}
}
