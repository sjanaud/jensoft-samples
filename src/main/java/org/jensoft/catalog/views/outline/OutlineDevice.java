/*
 * JenSoft API - Charting Framework
 * http://www.jensoftapi.com
 * Copyright (c) JenSoft. All rights reserved.
 * See JenSoft Software License Agreement
 */
package org.jensoft.catalog.views.outline;

import java.awt.BasicStroke;
import java.awt.Color;

import org.jensoft.core.catalog.nature.JenSoftView;
import org.jensoft.core.graphics.Shader;
import org.jensoft.core.palette.color.RosePalette;
import org.jensoft.core.plugin.outline.OutlinePlugin;
import org.jensoft.core.projection.DebugPaintProjectionPartPlugin;
import org.jensoft.core.projection.Projection;
import org.jensoft.core.view.Portfolio;
import org.jensoft.core.view.View;
import org.jensoft.core.view.ViewPart;
import org.jensoft.core.view.background.ViewDarkBackground;
import org.jensoft.core.view.background.ViewDefaultBackground;

/**
 * <code>OutlineDevice</code>
 * 
 * @author Sébastien Janaud
 */
@JenSoftView(background=ViewDarkBackground.class,description="Show view with device outline")
public class OutlineDevice extends View {

	private static final long serialVersionUID = 6741823282811227864L;

	/**
	 * create view with the device outline
	 */
	public OutlineDevice() {

		//  projection
		Projection proj = new Projection.Linear(0, 10, 0, 18);
		proj.setThemeColor(RosePalette.LEMONPEEL);
		registerProjection(proj);

		// device outline plug-in
		proj.registerPlugin(new OutlinePlugin(RosePalette.MANDARIN));

	}

	/**
	 * 
	 * @return sample portfolio
	 */
	@Portfolio(name = "OutlineDevice-1", width = 600, height = 400)
	public static View getPortofolio1() {

		// view root (default with 40 pixels for outer parts)
		View view = new View();

		// projection
		Projection proj = new Projection.Linear(0, 10, 0, 18);
		proj.setThemeColor(RosePalette.LEMONPEEL);
		view.registerProjection(proj);

		// device outline plug-in
		proj.registerPlugin(new OutlinePlugin(RosePalette.MANDARIN));

		// create view general background
		ViewDefaultBackground viewBackground = new ViewDefaultBackground();
		Shader s = new Shader(new float[] { 0f, 1f }, new Color[] { new Color(32, 39, 55), Color.BLACK });
		viewBackground.setShader(s);
		viewBackground.setOutlineStroke(new BasicStroke(2.5f));
		view.setBackgroundPainter(viewBackground);

		return view;
	}

	/**
	 * 
	 * @return sample portfolio
	 */
	@Portfolio(name = "OutlineDevice-2", width = 600, height = 400)
	public static View getPortofolio2() {
		// view root
		View view = new View();

		view.setPlaceHolder(20, ViewPart.East);
		view.setPlaceHolder(80, ViewPart.West);
		view.setPlaceHolder(80, ViewPart.South);
		view.setPlaceHolder(20, ViewPart.North);

		//  projection
		Projection proj = new Projection.Linear(0, 10, 0, 18);
		proj.setThemeColor(RosePalette.LEMONPEEL);
		view.registerProjection(proj);

		// device outline plug-in
		proj.registerPlugin(new OutlinePlugin(RosePalette.MANDARIN));

		// create view general background
		ViewDefaultBackground viewBackground = new ViewDefaultBackground();
		Shader s = new Shader(new float[] { 0f, 1f }, new Color[] { new Color(32, 39, 55), Color.BLACK });
		viewBackground.setShader(s);
		viewBackground.setOutlineStroke(new BasicStroke(2.5f));
		view.setBackgroundPainter(viewBackground);

		return view;
	}

	/**
	 * 
	 * @return sample portfolio
	 */
	@Portfolio(name = "OutlineDevice-3", width = 600, height = 400)
	public static View getPortofolio3() {
		// view root
		View view = new View();

		view.setPlaceHolder(20, ViewPart.East);
		view.setPlaceHolder(80, ViewPart.West);
		view.setPlaceHolder(80, ViewPart.South);
		view.setPlaceHolder(20, ViewPart.North);

		//  projection
		Projection proj = new Projection.Linear(0, 10, 0, 18);
		proj.setThemeColor(RosePalette.LEMONPEEL);
		view.registerProjection(proj);

		// device outline plug-in
		proj.registerPlugin(new OutlinePlugin(RosePalette.MANDARIN));

		DebugPaintProjectionPartPlugin showParts = new DebugPaintProjectionPartPlugin();
		proj.registerPlugin(showParts);

		// create view general background
		ViewDefaultBackground viewBackground = new ViewDefaultBackground();
		Shader s = new Shader(new float[] { 0f, 1f }, new Color[] { new Color(32, 39, 55), Color.BLACK });
		viewBackground.setShader(s);
		viewBackground.setOutlineStroke(new BasicStroke(2.5f));
		view.setBackgroundPainter(viewBackground);

		return view;
	}
}
