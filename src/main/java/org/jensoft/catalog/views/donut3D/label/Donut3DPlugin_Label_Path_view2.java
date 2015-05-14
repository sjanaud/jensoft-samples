/*
 * JenSoft API - Charting Framework
 * http://www.jensoftapi.com
 * Copyright (c) JenSoft. All rights reserved.
 * See JenSoft Software License Agreement
 */
package org.jensoft.catalog.views.donut3D.label;

import java.awt.Color;
import java.awt.Font;

import org.jensoft.core.catalog.nature.JenSoftView;
import org.jensoft.core.drawable.text.TextPath.PathSide;
import org.jensoft.core.drawable.text.TextPath.TextPosition;
import org.jensoft.core.palette.color.NanoChromatique;
import org.jensoft.core.palette.color.RosePalette;
import org.jensoft.core.plugin.donut3d.Donut3D;
import org.jensoft.core.plugin.donut3d.Donut3DPlugin;
import org.jensoft.core.plugin.donut3d.Donut3DSlice;
import org.jensoft.core.plugin.donut3d.Donut3DToolkit;
import org.jensoft.core.plugin.donut3d.painter.label.Donut3DPathLabel;
import org.jensoft.core.projection.Projection;
import org.jensoft.core.view.View;
import org.jensoft.core.view.background.ViewNoBackground;

/**
 * <code>Donut3DPlugin_Label_Path_view2</code>
 * 
 * @author JenSoftAPI
 */
@JenSoftView(background=ViewNoBackground.class,description="Show how to use path label on donut3D slice.")
public class Donut3DPlugin_Label_Path_view2 extends View {

	private static final long serialVersionUID = -1517887118528887076L;

	/**
	 * Create Donut3D with path label demo
	 */
	public Donut3DPlugin_Label_Path_view2() {
		super(0);
		Projection proj = new Projection.Linear(-1, 1, -1, 1);
		proj.setName("compatible donut3D proj");
		registerProjection(proj);

		Donut3DPlugin donut3DPlugin = new Donut3DPlugin();
		proj.registerPlugin(donut3DPlugin);

		// DONUT
		Donut3D donut3d = Donut3DToolkit.createDonut3D("myDonut", 60, 120, 70, 60, 40);
		donut3DPlugin.addDonut(donut3d);

		// create sections
		Donut3DSlice s1 = Donut3DToolkit.createDonut3DSlice("s1", new Color(240, 240, 240, 240), 45, 0);
		Donut3DSlice s2 = Donut3DToolkit.createDonut3DSlice("s2", RosePalette.COALBLACK, 5, 0);
		Donut3DSlice s3 = Donut3DToolkit.createDonut3DSlice("s3", new Color(78, 148, 44), 30, 0);
		
		// add section in donut
		Donut3DToolkit.pushSlices(donut3d, s1, s2, s3);

		Font f =  new Font("Dialog", Font.PLAIN, 12);
		
		Donut3DPathLabel ppl0 = new Donut3DPathLabel(TextPosition.Right, "SILVER");
		ppl0.setPathSide(PathSide.Below);
		//ppl0.setAutoReverse(true);
		//ppl0.setLockReverse(true);
		ppl0.setLabelFont(f);
		ppl0.setLabelColor(RosePalette.INDIGO);
		ppl0.setDivergence(2);
		s1.addSliceLabel(ppl0);


		Donut3DPathLabel ppl;

		ppl = Donut3DToolkit.createPathLabel("PERTH MINT BULLION", RosePalette.INDIGO, new Font("lucida console",Font.PLAIN,12), TextPosition.Left);
		ppl.setDivergence(2);
		ppl.setOffsetLeft(10);
		ppl.setOffsetRight(40);
		ppl.setPathSide(PathSide.Below);
		s1.addSliceLabel(ppl);
		
		ppl = new Donut3DPathLabel(TextPosition.Left, "ROYAL US MINT", RosePalette.AMETHYST);
		float[] fractions = { 0f, 1f };
		Color[] colors = { Color.WHITE, NanoChromatique.ORANGE };
		ppl.setLabelFont(new Font("lucida console",Font.PLAIN,12));
		ppl.setTextShader(fractions, colors);
		ppl.setPathSide(PathSide.Above);
		ppl.setDivergence(2);
		ppl.setOffsetLeft(5);
		s3.addSliceLabel(ppl);
	}
}
