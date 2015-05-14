/*
 * JenSoft API - Charting Framework
 * http://www.jensoftapi.com
 * Copyright (c) JenSoft. All rights reserved.
 * See JenSoft Software License Agreement
 */
package org.jensoft.catalog.views.donut3D.animator;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Stroke;

import org.jensoft.core.catalog.nature.JenSoftView;
import org.jensoft.core.palette.color.ColorPalette;
import org.jensoft.core.palette.color.RosePalette;
import org.jensoft.core.palette.color.TangoPalette;
import org.jensoft.core.plugin.donut3d.Donut3D;
import org.jensoft.core.plugin.donut3d.Donut3DPlugin;
import org.jensoft.core.plugin.donut3d.Donut3DSlice;
import org.jensoft.core.plugin.donut3d.Donut3DToolkit;
import org.jensoft.core.plugin.donut3d.animator.Donut3DLabelAnimator;
import org.jensoft.core.plugin.donut3d.painter.label.AbstractDonut3DSliceLabel.Style;
import org.jensoft.core.plugin.donut3d.painter.label.Donut3DRadialLabel;
import org.jensoft.core.projection.Projection;
import org.jensoft.core.view.View;
import org.jensoft.core.view.background.ViewNoBackground;

/**
 * <code>D3DLabelAnimator</code>
 * 
 * @author Sébastien Janaud
 */
@JenSoftView(background=ViewNoBackground.class,description="Show how to use slice label animator on donut3D slice.")
public class D3DLabelAnimator extends View {

	private static final long serialVersionUID = -8616592849015384306L;

	/**
	 * Create Donut3D with label animator
	 */
	public D3DLabelAnimator() {
		super(0);
		Projection proj = new Projection.Linear(-1, 1, -1, 1);
		proj.setName("compatible donut3D proj");
		registerProjection(proj);

		Donut3DPlugin donut3DPlugin = new Donut3DPlugin();
		proj.registerPlugin(donut3DPlugin);

		Donut3D donut3d = Donut3DToolkit.createDonut3D("myDonut", 80, 160, 80, 60, 40);
		donut3DPlugin.addDonut(donut3d);

		// create sections
		Donut3DSlice s1 = Donut3DToolkit.createDonut3DSlice("s1", new Color(250, 250, 250), 45);
		Donut3DSlice s2 = Donut3DToolkit.createDonut3DSlice("s2", TangoPalette.BUTTER2, 5);
		Donut3DSlice s3 = Donut3DToolkit.createDonut3DSlice("s3", TangoPalette.CHAMELEON2, 30);
		Donut3DSlice s4 = Donut3DToolkit.createDonut3DSlice("s4", TangoPalette.SKYBLUE2, 20);
		// add section in donut
		Donut3DToolkit.pushSlices(donut3d, s1, s2, s3, s4);

		// LABELS
		float[] fractions = { 0f, 0.3f, 0.7f, 1f };
		Color[] c = { new Color(0, 0, 0, 20), new Color(0, 0, 0, 150), new Color(0, 0, 0, 150), new Color(0, 0, 0, 20) };
		Stroke stroke = new BasicStroke(2f);
		Font f =  new Font("Dialog", Font.PLAIN, 12);
		// LABEL 1
		Donut3DRadialLabel label1 = Donut3DToolkit.createRadialLabel("Silver", RosePalette.COALBLACK, f, 30, 20, Style.Both);
		label1.setLabelColor(ColorPalette.WHITE);
		label1.setOutlineColor(RosePalette.REDWOOD);
		label1.setShader(fractions, c);
		label1.setOutlineStroke(stroke);

		// LABEL 2
		Donut3DRadialLabel label2 = Donut3DToolkit.createRadialLabel("Gold", RosePalette.COALBLACK, f, 30, 20, Style.Both);
		label2.setLabelColor(ColorPalette.WHITE);
		label2.setOutlineColor(RosePalette.LIME);
		label2.setShader(fractions, c);
		label2.setOutlineStroke(stroke);

		// LABEL 3
		Donut3DRadialLabel label3 = Donut3DToolkit.createRadialLabel("Platinium", RosePalette.COALBLACK, f, 30, 20, Style.Both);
		label3.setLabelColor(ColorPalette.WHITE);
		label3.setOutlineColor(RosePalette.COBALT);
		label3.setShader(fractions, c);
		label3.setOutlineStroke(stroke);

		// LABEL 4
		Donut3DRadialLabel label4 = Donut3DToolkit.createRadialLabel("Palladium", RosePalette.COALBLACK, f, 30, 20, Style.Both);
		label4.setLabelColor(ColorPalette.WHITE);
		label4.setOutlineColor(RosePalette.EMERALD);
		label4.setShader(fractions, c);
		label4.setOutlineStroke(stroke);

		// ANIMATOR
		donut3DPlugin.addDonutAnimator(new Donut3DLabelAnimator(s1, label1));
		donut3DPlugin.addDonutAnimator(new Donut3DLabelAnimator(s2, label2));
		donut3DPlugin.addDonutAnimator(new Donut3DLabelAnimator(s3, label3));
		donut3DPlugin.addDonutAnimator(new Donut3DLabelAnimator(s4, label4));
	}
}
