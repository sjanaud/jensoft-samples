/*
 * JenSoft API - Charting Framework
 * http://www.jensoftapi.com
 * Copyright (c) JenSoft. All rights reserved.
 * See JenSoft Software License Agreement
 */
package org.jensoft.catalog.views.pie.animator;

import java.awt.Color;

import org.jensoft.core.catalog.nature.JenSoftView;
import org.jensoft.core.palette.color.ColorPalette;
import org.jensoft.core.palette.color.TangoPalette;
import org.jensoft.core.plugin.pie.Pie;
import org.jensoft.core.plugin.pie.Pie.PieNature;
import org.jensoft.core.plugin.pie.PiePlugin;
import org.jensoft.core.plugin.pie.PieSlice;
import org.jensoft.core.plugin.pie.animator.PieDivergenceAnimator;
import org.jensoft.core.plugin.pie.painter.effect.PieLinearEffect;
import org.jensoft.core.plugin.pie.painter.effect.PieSliceLinearEffect;
import org.jensoft.core.plugin.pie.painter.fill.PieDefaultFill;
import org.jensoft.core.projection.Projection;
import org.jensoft.core.view.View;

/**
 * 
 * @author Sébastien Janaud
 *
 */
@JenSoftView(description="Pie with a divergence animator.")
public class PieDivergenceAnimatorDemo extends View {

	private static final long serialVersionUID = 8775017206611723102L;

	public PieDivergenceAnimatorDemo() {
		super();

		setPlaceHolderAxisSouth(80);
		setPlaceHolderAxisWest(120);
		setPlaceHolderAxisEast(120);

		Projection proj = new Projection.Linear.Identity();

		registerProjection(proj);

		PiePlugin piePlugin = new PiePlugin();
		proj.registerPlugin(piePlugin);

		Pie pie = new Pie();
		// define pie center in user coordinate
		pie.setPieNature(PieNature.User);
		pie.setCenterX(0);
		pie.setCenterY(0);
		pie.setRadius(120);

		pie.setPieFill(new PieDefaultFill());
		PieLinearEffect eff1 = new PieLinearEffect();
		eff1.setOffsetRadius(10);
		eff1.setIncidenceAngleDegree(120);
		pie.setPieEffect(eff1);
		pie.setStartAngleDegree(0);

		PieSlice s1 = new PieSlice("white", new Color(240, 240, 240, 240));
		s1.setValue(45);
		s1.setDivergence(0);
		PieSliceLinearEffect pse1 = new PieSliceLinearEffect();
		pse1.setIncidenceAngleDegree(120);
		pse1.setOffsetRadius(10);
		s1.setSliceEffect(pse1);

		PieSlice s2 = new PieSlice("butter", ColorPalette.alpha(TangoPalette.BUTTER2, 240));
		s2.setValue(5.0);
		s2.setDivergence(0);

		PieSlice s3 = new PieSlice("chameleon", ColorPalette.alpha(TangoPalette.CHAMELEON2, 240));
		s3.setValue(30.0);
		s3.setDivergence(0);

		PieSlice s4 = new PieSlice("sky blue", ColorPalette.alpha(TangoPalette.SKYBLUE2, 240));
		s4.setValue(20.0);
		s4.setDivergence(0);

		pie.addSlice(s1);
		pie.addSlice(s2);
		pie.addSlice(s3);
		pie.addSlice(s4);

		piePlugin.addPie(pie);

		pie.addPieAnimator(new PieDivergenceAnimator(40));

	}

}
