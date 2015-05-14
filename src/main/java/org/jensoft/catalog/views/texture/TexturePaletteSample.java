/*
 * JenSoft API - Charting Framework
 * http://www.jensoftapi.com
 * Copyright (c) JenSoft. All rights reserved.
 * See JenSoft Software License Agreement
 */
package org.jensoft.catalog.views.texture;

import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.geom.Rectangle2D;

import org.jensoft.core.catalog.nature.JenSoftView;
import org.jensoft.core.catalog.ui.ViewFrameUI;
import org.jensoft.core.palette.TexturePalette;
import org.jensoft.core.plugin.AbstractPlugin;
import org.jensoft.core.projection.Projection;
import org.jensoft.core.view.View;
import org.jensoft.core.view.ViewPart;
import org.jensoft.core.view.background.ViewDarkBackground;

/**
 * <code>TexturePaletteSample</code>
 * 
 * @author Sébastien Janaud
 * 
 */
@JenSoftView(background = ViewDarkBackground.class, description = "Textures that can be used to fill shape")
public class TexturePaletteSample extends View {

	private static final long serialVersionUID = -2875434568800741512L;

	private int rowCount = 5;
	private int colCount = 2;
	private Projection projection;

	public TexturePaletteSample() {
		super(20);
		projection = new Projection.Linear(-1, 1, -1, 1);
		registerProjection(projection);
		AbstractPlugin plugin = new AbstractPlugin() {

			@Override
			protected void paintPlugin(View v2d, Graphics2D g2d,
					ViewPart viewPart) {
				if (viewPart == ViewPart.Device) {
					paintCell(g2d, TexturePalette.getBeeCarbonTexture0(), 0, 0);
					paintCell(g2d, TexturePalette.getBeeCarbonTexture1(), 0, 1);
					paintCell(g2d, TexturePalette.getBeeCarbonTexture2(), 1, 0);
					paintCell(g2d, TexturePalette.getBeeCarbonTexture3(), 1, 1);
					paintCell(g2d, TexturePalette.getPerforatedCircleSurface(),	2, 0);
					paintCell(g2d,TexturePalette.getPerforatedPolygonSurface(), 2, 1);
					paintCell(g2d, TexturePalette.getSquareCarbonFiber(), 3, 0);
					paintCell(g2d, TexturePalette.getTriangleCarbonFiber(), 3,1);
					paintCell(g2d, TexturePalette.getInterlacedCarbon1(), 4, 0);
					paintCell(g2d, TexturePalette.getInterlacedCarbon2(), 4, 1);
				}
			}
		};

		projection.registerPlugin(plugin);
	}

	private void paintCell(Graphics2D g2d, Paint texture, int row, int col) {
		double cellWidth = projection.getDevice2D().getDeviceWidth() / colCount;
		double cellHeight = projection.getDevice2D().getDeviceHeight()
				/ rowCount;
		double px = col * cellWidth;
		double py = row * cellHeight;

		Rectangle2D rect = new Rectangle2D.Double(px, py, cellWidth, cellHeight);
		g2d.setPaint(texture);
		g2d.fill(rect);
	}

	public static void main(String[] args) {
		ViewFrameUI ui = new ViewFrameUI(new TexturePaletteSample());
	}
}
