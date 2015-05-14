/*
 * JenSoft API - Charting Framework
 * http://www.jensoftapi.com
 * Copyright (c) JenSoft. All rights reserved.
 * See JenSoft Software License Agreement
 */
package org.jensoft.catalog.views.pie.template;

import java.awt.Color;

import org.jensoft.core.catalog.nature.JenSoftView;
import org.jensoft.core.palette.color.NanoChromatique;
import org.jensoft.core.palette.color.RosePalette;
import org.jensoft.core.palette.color.Spectral;
import org.jensoft.core.plugin.pie.Pie;
import org.jensoft.core.plugin.pie.PiePlugin;
import org.jensoft.core.plugin.pie.PieSlice;
import org.jensoft.core.plugin.pie.painter.effect.PieRadialEffect;
import org.jensoft.core.plugin.pie.painter.fill.PieDefaultFill;
import org.jensoft.core.projection.Projection;
import org.jensoft.core.view.View;

@JenSoftView(description="Show how to use pie radial effect.")
public class PiePlugin_Effect_Radial_view11 extends View {

	private static final long serialVersionUID = -8365104670388166334L;

	public PiePlugin_Effect_Radial_view11() {
		super(2);

		Projection proj = new Projection.Linear.Identity();
		registerProjection(proj);

		PiePlugin piePlugin = new PiePlugin();

		Pie pie = new Pie();
		pie.setRadius(70);
		pie.setStartAngleDegree(0);
		pie.setPieFill(new PieDefaultFill());

		PieRadialEffect eff1 = new PieRadialEffect();
		eff1.setOffsetRadius(5);
		pie.setPieEffect(eff1);

		PieSlice s1 = new PieSlice("gris", new Color(240, 240, 240, 255));
		s1.setValue(45);
		s1.setDivergence(0);

		PieSlice s2 = new PieSlice("bleu", NanoChromatique.ORANGE);
		s2.setValue(8.0);
		s2.setDivergence(0);

		PieSlice s3 = new PieSlice("bleu", RosePalette.COALBLACK);
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
