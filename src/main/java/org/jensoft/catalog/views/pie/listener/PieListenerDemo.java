/*
 * JenSoft API - Charting Framework
 * http://www.jensoftapi.com
 * Copyright (c) JenSoft. All rights reserved.
 * See JenSoft Software License Agreement
 */
package org.jensoft.catalog.views.pie.listener;

import java.awt.Color;

import org.jensoft.core.catalog.nature.JenSoftView;
import org.jensoft.core.palette.color.ColorPalette;
import org.jensoft.core.palette.color.TangoPalette;
import org.jensoft.core.plugin.pie.Pie;
import org.jensoft.core.plugin.pie.PieEvent;
import org.jensoft.core.plugin.pie.PieListener;
import org.jensoft.core.plugin.pie.PiePlugin;
import org.jensoft.core.plugin.pie.PieSlice;
import org.jensoft.core.plugin.pie.PieToolkit;
import org.jensoft.core.plugin.pie.painter.effect.PieLinearEffect;
import org.jensoft.core.projection.Projection;
import org.jensoft.core.view.View;

@JenSoftView(description="Show how to use pie listener.")
public class PieListenerDemo extends View {

	private static final long serialVersionUID = 3057620418519004240L;

	public PieListenerDemo() {
		super(0);
		Projection proj = new Projection.Linear.Identity();
		registerProjection(proj);

		PiePlugin piePlugin = new PiePlugin();
		proj.registerPlugin(piePlugin);
		Pie pie = PieToolkit.createPie("pie", 90);
		pie.setPieEffect(new PieLinearEffect());
		PieSlice s1 = new PieSlice("gray", new Color(240, 240, 240, 240), 45);
		PieSlice s2 = new PieSlice("gray", ColorPalette.alpha(TangoPalette.BUTTER2, 240), 5);
		PieSlice s3 = new PieSlice("chameleon", ColorPalette.alpha(TangoPalette.CHAMELEON2, 240), 30);
		PieSlice s4 = new PieSlice("blue", ColorPalette.alpha(TangoPalette.SKYBLUE2, 240), 20);
		PieToolkit.pushSlices(pie, s1, s2, s3, s4);
		piePlugin.addPie(pie);

		PieLinearEffect effect1 = new PieLinearEffect();

		effect1.setOffsetRadius(10);
		effect1.setIncidenceAngleDegree(300);
		pie.setPieEffect(effect1);

		pie.addPieListener(new PieListener() {

			@Override
			public void pieSliceReleased(PieEvent e) {
				System.out.println("slice released" + e.getSlice().getName());
			}

			@Override
			public void pieSlicePressed(PieEvent e) {
				System.out.println("slice pressed" + e.getSlice().getName());
			}

			@Override
			public void pieSliceExited(PieEvent e) {
				System.out.println("slice exited" + e.getSlice().getName());
			}

			@Override
			public void pieSliceEntered(PieEvent e) {
				System.out.println("slice entered" + e.getSlice().getName());
			}

			@Override
			public void pieSliceClicked(PieEvent e) {
				System.out.println("slice clicked" + e.getSlice().getName());

			}
		});
	}
}
