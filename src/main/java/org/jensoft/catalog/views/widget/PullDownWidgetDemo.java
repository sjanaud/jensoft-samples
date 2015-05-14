/*
 * JenSoft API - Charting Framework
 * http://www.jensoftapi.com
 * Copyright (c) JenSoft. All rights reserved.
 * See JenSoft Software License Agreement
 */
package org.jensoft.catalog.views.widget;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.jensoft.core.catalog.nature.JenSoftView;
import org.jensoft.core.graphics.Shader;
import org.jensoft.core.palette.color.Alpha;
import org.jensoft.core.palette.color.ColorPalette;
import org.jensoft.core.palette.color.FilPalette;
import org.jensoft.core.palette.color.NanoChromatique;
import org.jensoft.core.palette.color.RosePalette;
import org.jensoft.core.plugin.grid.Grid.GridOrientation;
import org.jensoft.core.plugin.grid.GridPlugin;
import org.jensoft.core.plugin.marker.MarkerPlugin;
import org.jensoft.core.plugin.marker.context.MarkerDefaultDeviceContext;
import org.jensoft.core.plugin.outline.OutlinePlugin;
import org.jensoft.core.plugin.stripe.StripePlugin;
import org.jensoft.core.plugin.stripe.painter.StripePalette;
import org.jensoft.core.plugin.translate.TranslateCompassWidget;
import org.jensoft.core.plugin.translate.TranslateDefaultDeviceContext;
import org.jensoft.core.plugin.translate.TranslatePlugin;
import org.jensoft.core.plugin.translate.TranslateX;
import org.jensoft.core.plugin.translate.TranslateY;
import org.jensoft.core.plugin.zoom.lens.LensDefaultDeviceContext;
import org.jensoft.core.plugin.zoom.lens.LensX;
import org.jensoft.core.plugin.zoom.lens.LensY;
import org.jensoft.core.plugin.zoom.lens.ZoomLensPlugin;
import org.jensoft.core.plugin.zoom.percent.ZoomPercentDefaultDeviceContext;
import org.jensoft.core.plugin.zoom.percent.ZoomPercentPlugin;
import org.jensoft.core.plugin.zoom.wheel.ZoomWheelPlugin;
import org.jensoft.core.projection.Projection;
import org.jensoft.core.sharedicon.SharedIcon;
import org.jensoft.core.sharedicon.common.Common;
import org.jensoft.core.view.View;
import org.jensoft.core.view.background.ViewDarkBackground;
import org.jensoft.core.widget.button.PullDownButtonWidget;
import org.jensoft.core.widget.button.PullItem;

/**
 * <code>PullDownWidgetDemo</code>
 * 
 * @author Sébastien Janaud
 */
@JenSoftView(background=ViewDarkBackground.class)
public class PullDownWidgetDemo extends View {

	/**
	 * Create a demo with a pull down widget that brings two actions
	 */
	public PullDownWidgetDemo() {
		super();

		// projection
		Projection proj = new Projection.Linear(0, 10, 0, 18);

		// stripe plug-in
		StripePlugin stripes = new StripePlugin.MultiplierStripe.H(0, 2.5);
		StripePalette bp = new StripePalette();
		bp.addPaint(new Color(255, 255, 255, 20));
		bp.addPaint(ColorPalette.alpha(FilPalette.GREEN4, 20));
		stripes.setStripePalette(bp);
		proj.registerPlugin(stripes);

		// grid plug-in
		GridPlugin gridLayout = new GridPlugin.MultiplierGrid(0, 2.5, GridOrientation.Horizontal);
		gridLayout.setGridColor(new Color(255, 255, 255, 60));
		proj.registerPlugin(gridLayout);

		GridPlugin gridLayout2 = new GridPlugin.MultiplierGrid(0, 2.5, GridOrientation.Vertical);
		gridLayout2.setGridColor(new Color(255, 255, 255, 60));
		proj.registerPlugin(gridLayout2);

		// wheel
		ZoomWheelPlugin zoomWheel = new ZoomWheelPlugin();
		proj.registerPlugin(zoomWheel);

		// translate plug-in
		final TranslatePlugin translatePlugin = new TranslatePlugin();
		proj.registerPlugin(translatePlugin);

		// register translate tx and ty widgets
		TranslateX tx = new TranslateX(80, 14);
		TranslateY ty = new TranslateY(14, 80);
		tx.setOutlineColor(ColorPalette.BLACK);
		tx.setOutlineStroke(new BasicStroke(1.2f));
		tx.setButtonFillColor(RosePalette.CALYPSOBLUE);
		tx.setButtonDrawColor(null);
		tx.setShader(new Shader(new float[] { 0f, 1f }, new Color[] { new Color(30, 30, 30, 140), new Color(10, 10, 10, 255) }));
		ty.setOutlineColor(ColorPalette.BLACK);
		ty.setOutlineStroke(new BasicStroke(1.2f));
		ty.setButtonFillColor(RosePalette.CALYPSOBLUE);
		ty.setButtonDrawColor(null);
		ty.setShader(new Shader(new float[] { 0f, 1f }, new Color[] { new Color(30, 30, 30, 140), new Color(10, 10, 10, 255) }));
		translatePlugin.registerWidget(tx, ty);
		translatePlugin.registerContext(new TranslateDefaultDeviceContext());

		TranslateCompassWidget compass = new TranslateCompassWidget();
		translatePlugin.registerWidget(compass);
		compass.setRingFillColor(new Alpha(RosePalette.COALBLACK, 255));
		compass.setRingDrawColor(null);

		// objectif plug-in
		final ZoomLensPlugin zoomObjectif = new ZoomLensPlugin();

		// use objectif ox and oy widgets
		LensX ox = new LensX(80, 14);
		ox.setOutlineColor(Color.BLACK);
		ox.setButton1DrawColor(RosePalette.CALYPSOBLUE);
		ox.setButton2DrawColor(RosePalette.CALYPSOBLUE);

		LensY oy = new LensY(14, 80);
		oy.setOutlineColor(Color.BLACK);
		oy.setButton1DrawColor(RosePalette.CALYPSOBLUE);
		oy.setButton2DrawColor(RosePalette.CALYPSOBLUE);

		zoomObjectif.registerWidget(ox);
		zoomObjectif.registerWidget(oy);

		// register objectif device context
		zoomObjectif.registerContext(new LensDefaultDeviceContext());

		proj.registerPlugin(zoomObjectif);

		// zoom percent
		ZoomPercentPlugin zoomPercent = new ZoomPercentPlugin();
		zoomPercent.registerContext(new ZoomPercentDefaultDeviceContext());
		proj.registerPlugin(zoomPercent);

		MarkerPlugin markerPlugin = new MarkerPlugin();
		markerPlugin.registerContext(new MarkerDefaultDeviceContext());
		proj.registerPlugin(markerPlugin);

		registerProjection(proj);

		// pull down widget
		PullDownButtonWidget pullDownWidget = new PullDownButtonWidget("myselectorPullDown", 60, 22, 100, 100);
		pullDownWidget.setOutlineColor(ColorPalette.BLACK);
		pullDownWidget.setOutlineRound(8);
		pullDownWidget.setPullFillColor(NanoChromatique.BLUE);
		pullDownWidget.setOutlineStroke(new BasicStroke(1.2f));
		pullDownWidget.setShader(new Shader(new float[] { 0f, 1f }, new Color[] { new Color(10, 10, 10, 255), new Color(30, 30, 30, 140) }));

		PullItem translatePullItem = new PullItem("translate", SharedIcon.getCommon(Common.TRANSLATE));

		translatePullItem.setListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (translatePlugin.isLockSelected()) {
					translatePlugin.unlockSelected();
				} else {
					translatePlugin.lockSelected();
				}

			}
		});

		PullItem objectifPullItem = new PullItem("objectif", SharedIcon.getCommon(Common.LENS16));
		objectifPullItem.setListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (zoomObjectif.isLockSelected()) {
					zoomObjectif.unlockSelected();
				} else {
					zoomObjectif.lockSelected();
				}
			}
		});

		pullDownWidget.addPullAction(translatePullItem);
		pullDownWidget.addPullAction(objectifPullItem);
		zoomWheel.registerWidget(pullDownWidget);

		// outline plug-in
		OutlinePlugin outline = new OutlinePlugin(NanoChromatique.ORANGE);
		proj.registerPlugin(outline);
		outline.inflateOutline(10000);

	}

}
