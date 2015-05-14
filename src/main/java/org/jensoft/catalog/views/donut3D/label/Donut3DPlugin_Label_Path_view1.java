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
import org.jensoft.core.palette.color.Spectral;
import org.jensoft.core.palette.color.TangoPalette;
import org.jensoft.core.plugin.donut3d.Donut3D;
import org.jensoft.core.plugin.donut3d.Donut3DPlugin;
import org.jensoft.core.plugin.donut3d.Donut3DSlice;
import org.jensoft.core.plugin.donut3d.Donut3DToolkit;
import org.jensoft.core.plugin.donut3d.painter.label.Donut3DPathLabel;
import org.jensoft.core.plugin.donut3d.painter.label.Donut3DPathLabel.Donut3DFacetPathName;
import org.jensoft.core.projection.Projection;
import org.jensoft.core.view.View;
import org.jensoft.core.view.background.ViewNoBackground;

/**
 * <code>Donut3DPathLabelDemo</code>
 * 
 * @author JenSoftAPI
 */
@JenSoftView(background=ViewNoBackground.class,description="Show how to use path label on donut3D slice.")
public class Donut3DPlugin_Label_Path_view1 extends View {

	private static final long serialVersionUID = 3816779251661506765L;

	/**
	 * Create Donut3D with path label demo
	 */
	public Donut3DPlugin_Label_Path_view1() {
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
		Donut3DSlice s1 = Donut3DToolkit.createDonut3DSlice("s1", new Color(250, 250, 250), 45);
		Donut3DSlice s2 = Donut3DToolkit.createDonut3DSlice("s2", NanoChromatique.ORANGE, 8);
		Donut3DSlice s3 = Donut3DToolkit.createDonut3DSlice("s3", RosePalette.COALBLACK, 15);
		Donut3DSlice s4 = Donut3DToolkit.createDonut3DSlice("s4", Spectral.SPECTRAL_BLUE1, 30);
		// add section in donut
		Donut3DToolkit.pushSlices(donut3d, s1, s2, s3, s4);

		
		Font f =  new Font("Dialog", Font.PLAIN, 12);
		
		Donut3DPathLabel ppl = new Donut3DPathLabel(TextPosition.Right, "JenSoft Framework");
		ppl.setPathSide(PathSide.Below);
		ppl.setLabelFont(f);
		ppl.setLabelColor(RosePalette.MANDARIN);
		ppl.setDivergence(2);
		ppl.setOffsetRight(0);
		// ppl.setLockReverse(true);
		// ppl.setAutoReverse(false);
		s1.addSliceLabel(ppl);

		ppl = new Donut3DPathLabel(TextPosition.Middle, "API");
		ppl.setPathSide(PathSide.Below);
		ppl.setFacetPathName(Donut3DFacetPathName.EndLineTop);
		ppl.setLabelFont(f);
		ppl.setLabelColor(RosePalette.CORALRED);
		ppl.setDivergence(2);
		ppl.setOffsetLeft(0);
		ppl.setOffsetRight(0);
		// ppl.setLockReverse(true);
		// ppl.setAutoReverse(false);
		s1.addSliceLabel(ppl);

		ppl = new Donut3DPathLabel(TextPosition.Left, "Pie Path Label", TangoPalette.CHAMELEON2.darker());
		float[] fractions2 = { 0f, 1f };
		Color[] colors = { RosePalette.EMERALD, RosePalette.EMERALD };
		ppl.setLabelFont(f);
		ppl.setTextShader(fractions2, colors);
		ppl.setPathSide(PathSide.Above);
		ppl.setDivergence(2);
		s3.addSliceLabel(ppl);
		
		ppl = new Donut3DPathLabel(TextPosition.Left, "Framework", Spectral.SPECTRAL_PURPLE1);
		ppl.setFacetPathName(Donut3DFacetPathName.OuterArcBottom);
		ppl.setLabelFont(f);
		ppl.setTextPosition(TextPosition.Middle);
		ppl.setPathSide(PathSide.Above);
		ppl.setDivergence(2);
		s3.addSliceLabel(ppl);

		ppl = new Donut3DPathLabel(TextPosition.Left, "API", Spectral.SPECTRAL_PURPLE1.darker());
		ppl.setFacetPathName(Donut3DFacetPathName.InnerArcTop);
		ppl.setLabelFont(f);
		ppl.setTextPosition(TextPosition.Left);
		ppl.setPathSide(PathSide.Above);
		ppl.setDivergence(2);
		s4.addSliceLabel(ppl);
	}
}
