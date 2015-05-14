/*
 * JenSoft API - Charting Framework
 * http://www.jensoftapi.com
 * Copyright (c) JenSoft. All rights reserved.
 * See JenSoft Software License Agreement
 */
package org.jensoft.catalog.views.pie.template;

import java.awt.BasicStroke;

import org.jensoft.core.catalog.nature.JenSoftView;
import org.jensoft.core.catalog.ui.ViewFrameUI;
import org.jensoft.core.palette.color.RosePalette;
import org.jensoft.core.plugin.pie.Pie;
import org.jensoft.core.plugin.pie.Pie.PieNature;
import org.jensoft.core.plugin.pie.PiePlugin;
import org.jensoft.core.plugin.pie.PieSlice;
import org.jensoft.core.plugin.pie.PieToolkit;
import org.jensoft.core.plugin.pie.painter.draw.PieSliceDefaultDraw;
import org.jensoft.core.projection.Projection;
import org.jensoft.core.view.View;

@JenSoftView(description="Pie draw")
public class PiePlugin_Draw_view2 extends View {

	private static final long serialVersionUID = -736335728494885054L;

	public PiePlugin_Draw_view2() {
		super(2);
		Projection proj = new Projection.Linear(-1, 1, -1, 1);
		registerProjection(proj);

		PiePlugin piePlugin = new PiePlugin();
		proj.registerPlugin(piePlugin);

		Pie pie = new Pie("pie draw", 70);
		pie.setPieNature(PieNature.User);
		pie.setStartAngleDegree(0);
		pie.setCenterX(0);
		pie.setCenterY(0);
		//pie.setPieFill(new PieDefaultFill());
		pie.setStartAngleDegree(25);

		PieSlice s1 = PieToolkit.createSlice("s1", RosePalette.EMERALD, 45, 0);
		PieSlice s2 = PieToolkit.createSlice("s2", RosePalette.LIGHTBROWN, 15, 0);
		PieSlice s3 = PieToolkit.createSlice("s3", RosePalette.CORALRED, 10, 0);
		PieSlice s4 = PieToolkit.createSlice("s4", RosePalette.AMETHYST, 20, 0);

		s1.setSliceDraw(new PieSliceDefaultDraw(RosePalette.EMERALD, new BasicStroke(2f)));
		s2.setSliceDraw(new PieSliceDefaultDraw(RosePalette.LIGHTBROWN, new BasicStroke(2f)));
		s3.setSliceDraw(new PieSliceDefaultDraw(RosePalette.CORALRED, new BasicStroke(2f)));
		s4.setSliceDraw(new PieSliceDefaultDraw(RosePalette.AMETHYST, new BasicStroke(2f)));

		PieToolkit.pushSlices(pie, s1, s2, s3, s4);
		piePlugin.addPie(pie);
	}
	
	public static void main(String[] args) {
		ViewFrameUI ui = new ViewFrameUI(new PiePlugin_Draw_view2());
	}

}
