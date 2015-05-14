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
import org.jensoft.core.catalog.ui.ViewFrameUI;
import org.jensoft.core.palette.color.RosePalette;
import org.jensoft.core.palette.color.Spectral;
import org.jensoft.core.plugin.pie.Pie;
import org.jensoft.core.plugin.pie.PiePlugin;
import org.jensoft.core.plugin.pie.PieSlice;
import org.jensoft.core.plugin.pie.PieToolkit;
import org.jensoft.core.plugin.pie.painter.draw.PieSliceDefaultDraw;
import org.jensoft.core.projection.Projection;
import org.jensoft.core.view.View;

@JenSoftView(description="Pie draw")
public class PiePlugin_Draw_view1 extends View {
	
	private static final long serialVersionUID = 2133726302201009236L;

	public PiePlugin_Draw_view1() {
		super(2);
		Projection proj = new Projection.Linear.Identity();
		registerProjection(proj);

		PiePlugin piePlugin = new PiePlugin();
		proj.registerPlugin(piePlugin);
		
		Pie pie = PieToolkit.createPie("pie", 70);
		pie.setStartAngleDegree(25);
		
		PieSlice s1 = PieToolkit.createSlice("s1", new Color(240, 240, 240, 240), 45, 0);
		PieSlice s2 = PieToolkit.createSlice("s2", RosePalette.CHOCOLATE, 15, 0);
		PieSlice s3 = PieToolkit.createSlice("s3", RosePalette.INDIGO, 10, 0);
		PieSlice s4 = PieToolkit.createSlice("s4", Spectral.SPECTRAL_BLUE2, 20, 0);
		
		s4.setSliceDraw(new PieSliceDefaultDraw(RosePalette.HENNA, new BasicStroke(2f)));
		
		
		PieToolkit.pushSlices(pie, s1, s2, s3, s4);
		piePlugin.addPie(pie);
	}

	public static void main(String[] args) {
		ViewFrameUI ui = new ViewFrameUI(new PiePlugin_Draw_view1());
	}
}
