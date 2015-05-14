/*
 * JenSoft API - Charting Framework
 * http://www.jensoftapi.com
 * Copyright (c) JenSoft. All rights reserved.
 * See JenSoft Software License Agreement
 */
package org.jensoft.catalog.views.pie.template;

import java.awt.Color;

import org.jensoft.core.catalog.nature.JenSoftView;
import org.jensoft.core.palette.color.RosePalette;
import org.jensoft.core.palette.color.Spectral;
import org.jensoft.core.plugin.pie.Pie;
import org.jensoft.core.plugin.pie.PiePlugin;
import org.jensoft.core.plugin.pie.PieSlice;
import org.jensoft.core.plugin.pie.PieToolkit;
import org.jensoft.core.plugin.pie.painter.effect.PieLinearEffect;
import org.jensoft.core.projection.Projection;
import org.jensoft.core.view.View;

@JenSoftView(description="Show pie model")
public class PiePlugin_Header_view1 extends View {

	private static final long serialVersionUID = 532496089789261034L;

	public PiePlugin_Header_view1() {
		super(2);
		Projection proj = new Projection.Linear.Identity();
		registerProjection(proj);

		PiePlugin piePlugin = new PiePlugin();
		proj.registerPlugin(piePlugin);
		Pie pie = PieToolkit.createPie("pie", 70);
		pie.setStartAngleDegree(25);
		
		PieLinearEffect fx1 = new PieLinearEffect(90);
		fx1.setOffsetRadius(4);
				
		pie.setPieEffect(fx1);
		
		PieSlice s1 = PieToolkit.createSlice("gray", new Color(240, 240, 240, 240), 45, 0);
		PieSlice s2 = PieToolkit.createSlice("gray", RosePalette.CHOCOLATE, 15, 0);
		PieSlice s3 = PieToolkit.createSlice("chameleon", RosePalette.INDIGO, 10, 0);
		PieSlice s4 = PieToolkit.createSlice("blue", Spectral.SPECTRAL_BLUE2, 20, 20);
		PieToolkit.pushSlices(pie, s1, s2, s3, s4);
		piePlugin.addPie(pie);


	}

}
