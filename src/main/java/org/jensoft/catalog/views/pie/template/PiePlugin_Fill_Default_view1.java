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
import org.jensoft.core.plugin.pie.painter.fill.PieDefaultFill;
import org.jensoft.core.projection.Projection;
import org.jensoft.core.view.View;

@JenSoftView(description = "Show how to use pie default fill.")
public class PiePlugin_Fill_Default_view1 extends View {

	private static final long serialVersionUID = -4790577104776416305L;

	public PiePlugin_Fill_Default_view1() {
		super(2);
		Projection proj = new Projection.Linear.Identity();
		registerProjection(proj);

		PiePlugin piePlugin = new PiePlugin();
		proj.registerPlugin(piePlugin);
		
		
		Pie pie = PieToolkit.createPie("pie", 70);
		pie.setStartAngleDegree(15);
		
		pie.setPieFill(new PieDefaultFill());
		
		PieSlice s1 = PieToolkit.createSlice("silver", new Color(240, 240, 240, 240), 45, 0);
		PieSlice s2 = PieToolkit.createSlice("gold", RosePalette.CHOCOLATE, 15, 0);
		PieSlice s3 = PieToolkit.createSlice("rhodium", RosePalette.INDIGO, 10, 0);
		PieSlice s4 = PieToolkit.createSlice("platinium", Spectral.SPECTRAL_BLUE2, 20, 0);
		
		PieToolkit.pushSlices(pie, s1, s2, s3, s4);
		piePlugin.addPie(pie);

	}
}
