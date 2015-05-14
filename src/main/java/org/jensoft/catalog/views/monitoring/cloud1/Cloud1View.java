/*
 * JenSoft API - Charting Framework
 * http://www.jensoftapi.com
 * Copyright (c) JenSoft. All rights reserved.
 * See JenSoft Software License Agreement
 */
package org.jensoft.catalog.views.monitoring.cloud1;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.jensoft.core.catalog.nature.JenSoftView;
import org.jensoft.core.graphics.Antialiasing;
import org.jensoft.core.palette.color.Alpha;
import org.jensoft.core.palette.color.ColorPalette;
import org.jensoft.core.palette.color.FilPalette;
import org.jensoft.core.palette.color.RosePalette;
import org.jensoft.core.plugin.AbstractPlugin;
import org.jensoft.core.plugin.background.DeviceNightBackground;
import org.jensoft.core.plugin.grid.Grid.GridOrientation;
import org.jensoft.core.plugin.grid.GridPlugin;
import org.jensoft.core.plugin.legend.title.TitleLegend;
import org.jensoft.core.plugin.legend.title.TitleLegendConstraints;
import org.jensoft.core.plugin.legend.title.TitleLegendConstraints.LegendAlignment;
import org.jensoft.core.plugin.legend.title.TitleLegendConstraints.LegendPosition;
import org.jensoft.core.plugin.legend.title.TitleLegendPlugin;
import org.jensoft.core.plugin.legend.title.painter.fil.TitleLegendGradientFill;
import org.jensoft.core.plugin.marker.MarkerPlugin;
import org.jensoft.core.plugin.marker.context.MarkerDefaultDeviceContext;
import org.jensoft.core.plugin.metrics.AxisMetricsPlugin;
import org.jensoft.core.plugin.outline.OutlinePlugin;
import org.jensoft.core.plugin.stripe.StripePlugin;
import org.jensoft.core.plugin.stripe.painter.StripePalette;
import org.jensoft.core.plugin.translate.TranslateCompassWidget;
import org.jensoft.core.plugin.translate.TranslateCompassWidget.CompassStyle;
import org.jensoft.core.plugin.translate.TranslateDefaultDeviceContext;
import org.jensoft.core.plugin.translate.TranslatePlugin;
import org.jensoft.core.plugin.translate.TranslateX;
import org.jensoft.core.plugin.translate.TranslateY;
import org.jensoft.core.plugin.zoom.box.ZoomBoxDefaultDeviceContext;
import org.jensoft.core.plugin.zoom.box.ZoomBoxPlugin;
import org.jensoft.core.plugin.zoom.lens.LensDefaultDeviceContext;
import org.jensoft.core.plugin.zoom.lens.LensX;
import org.jensoft.core.plugin.zoom.lens.LensY;
import org.jensoft.core.plugin.zoom.lens.ZoomLensPlugin;
import org.jensoft.core.plugin.zoom.percent.ZoomPercentDefaultDeviceContext;
import org.jensoft.core.plugin.zoom.percent.ZoomPercentPlugin;
import org.jensoft.core.plugin.zoom.wheel.ZoomWheelPlugin;
import org.jensoft.core.projection.Projection;
import org.jensoft.core.view.View;
import org.jensoft.core.view.ViewPart;
import org.jensoft.core.view.background.ViewDarkBackground;

/**
 * <code>Cloud1View</code>
 * 
 * @author Sébastien Janaud
 * 
 */
@JenSoftView(background=ViewDarkBackground.class,description="Show a monitoring cloud point real time like")
public class Cloud1View extends View {

	private static final long serialVersionUID = -2459721275416382433L;

	public Cloud1View() {

		setPlaceHolderAxisWest(60);
		setPlaceHolderAxisSouth(60);

		Projection proj = new Projection.Linear(-3000, 3000, -2500, 2500);
		proj.setThemeColor(RosePalette.SAFFRON);
		proj.setName("cloud1 proj");
		proj.registerPlugin(new DeviceNightBackground());

		Font f =  new Font("Dialog", Font.PLAIN, 12);
		
		AxisMetricsPlugin.ModeledMetrics westMetrics = new AxisMetricsPlugin.ModeledMetrics.W();
		proj.registerPlugin(westMetrics);
		westMetrics.setTextFont(f);
		

		AxisMetricsPlugin.ModeledMetrics southMetrics = new AxisMetricsPlugin.ModeledMetrics.S();
		proj.registerPlugin(southMetrics);
		southMetrics.setTextFont(f);
		

		proj.registerPlugin(new OutlinePlugin());

		
		TitleLegend legend = new TitleLegend("Measures");
		legend.setLegendFill(new TitleLegendGradientFill(RosePalette.EMERALD, RosePalette.COALBLACK));
		legend.setFont(f);
		legend.setConstraints(new TitleLegendConstraints(LegendPosition.East, 0.3f, LegendAlignment.Rigth));
		TitleLegendPlugin legendPlugin = new TitleLegendPlugin(legend);
		proj.registerPlugin(legendPlugin);

		StripePlugin bandLayout = new StripePlugin.MultiplierStripe.H(-200, 400);
		StripePalette bp = new StripePalette();
		bp.addPaint(new Color(255, 255, 255, 40));
		bp.addPaint(ColorPalette.alpha(FilPalette.GREEN5, 120));
		bandLayout.setStripePalette(bp);
		bandLayout.setAlpha(0.1f);
		proj.registerPlugin(bandLayout);

		GridPlugin gridLayout = new GridPlugin.MultiplierGrid(-200, 400, GridOrientation.Horizontal);
		gridLayout.setGridColor(new Color(59, 89, 152, 100));
		proj.registerPlugin(gridLayout);

		// ZOOM BOX
		ZoomBoxPlugin zoomBox = new ZoomBoxPlugin();
		zoomBox.registerContext(new ZoomBoxDefaultDeviceContext());
		proj.registerPlugin(zoomBox);

		// TRANSLATE
		TranslatePlugin translatePlugin = new TranslatePlugin();
		proj.registerPlugin(translatePlugin);

		// use pad widget with objectif
		TranslateX tx = new TranslateX(80, 12);
		tx.setButton1DrawColor(RosePalette.MANDARIN);
		tx.setButton2DrawColor(RosePalette.MANDARIN);
		TranslateY ty = new TranslateY(12, 80);
		ty.setButton1DrawColor(RosePalette.MANDARIN);
		ty.setButton2DrawColor(RosePalette.MANDARIN);
		translatePlugin.registerWidget(tx, ty);

		TranslateCompassWidget compass = new TranslateCompassWidget();
		translatePlugin.registerWidget(compass);
		compass.setRingFillColor(new Alpha(RosePalette.EMERALD, 150));
		compass.setRingDrawColor(Color.WHITE);
		compass.setRingNeedleFillColor(new Alpha(RosePalette.EMERALD, 150));
		compass.setRingNeedleDrawColor(Color.WHITE);
		compass.setCompassStyle(CompassStyle.Separate);
		// pre lock selected
		translatePlugin.lockSelected();

		translatePlugin.registerContext(new TranslateDefaultDeviceContext());

		// plugin/objectif
		ZoomLensPlugin zoomObjectif = new ZoomLensPlugin();

		// plugin/objectif/widget
		LensX xObjectif = new LensX(80, 12);
		LensY yObjectif = new LensY(12, 80);
		zoomObjectif.registerWidget(xObjectif);
		zoomObjectif.registerWidget(yObjectif);

		// plugin/objectif/context
		zoomObjectif.registerContext(new LensDefaultDeviceContext());

		proj.registerPlugin(zoomObjectif);

		// ZOOM PERCENT
		ZoomPercentPlugin zoomPercent = new ZoomPercentPlugin();
		zoomPercent.registerContext(new ZoomPercentDefaultDeviceContext());
		proj.registerPlugin(zoomPercent);

		ZoomWheelPlugin zoomWheel = new ZoomWheelPlugin();
		proj.registerPlugin(zoomWheel);

		MarkerPlugin markerPlugin = new MarkerPlugin();
		markerPlugin.registerContext(new MarkerDefaultDeviceContext());
		proj.registerPlugin(markerPlugin);

		CloudPlugin cloudPointPlugin = new CloudPlugin();
		cloudPointPlugin.setCloudColor(RosePalette.MANDARIN);
		proj.registerPlugin(cloudPointPlugin);

		registerProjection(proj);

		
		List<List<Point2D>> series = loadData();
		List<Point2D> serie_0 = series.get(0);
		
		cloudPointPlugin.setCloud(serie_0);
		
		CloudAnimator demoThread = new CloudAnimator(loadData(), cloudPointPlugin);
		//demoThread.start();

	}

	public class CloudPlugin extends AbstractPlugin {

		public CloudPlugin() {
			setName("CloudPoint");
		}

		private Cloud curentCloud = new Cloud();

		@Override
		protected void paintPlugin(View v2d, Graphics2D g2d, ViewPart viewPart) {
			if (curentCloud == null || curentCloud.userPoint == null) {
				return;
			}
			if (viewPart != ViewPart.Device) {
				return;
			}
			if (getCloudColor() == null) {
				g2d.setColor(getThemeColor());
			} else {
				g2d.setColor(getCloudColor());
			}

			Antialiasing aa = Antialiasing.On;
			aa.configureGraphics(g2d);

			curentCloud.devicePoint = new ArrayList<Point2D>();
			for (int i = 0; i < curentCloud.userPoint.size(); i++) {

				Point2D p2dUser = curentCloud.userPoint.get(i);

				if (getProjection().intercept(p2dUser)) {
					Point2D p2dDevice = null;
					p2dDevice = getProjection().userToPixel(p2dUser);
					curentCloud.devicePoint.add(p2dDevice);

					g2d.drawLine((int) p2dDevice.getX(), (int) p2dDevice.getY(), (int) p2dDevice.getX(), (int) p2dDevice.getY());
				}

			}

		}

		public void setCloud(List<Point2D> userPoints) {
			curentCloud.userPoint = userPoints;
			getProjection().getDevice2D().repaintDevice();
		}

		private Color cloudColor;

		public Color getCloudColor() {
			return cloudColor;
		}

		public void setCloudColor(Color constellationColor) {
			this.cloudColor = constellationColor;
		}

		class Cloud {
			List<Point2D> userPoint;
			List<Point2D> devicePoint;
		}

	}

	class CloudAnimator extends Thread {
		private CloudPlugin plugin;
		private List<List<Point2D>> series;

		public CloudAnimator(List<List<Point2D>> pointsSeries, CloudPlugin plugin) {
			setName("Cloud demo Thread");
			this.series = pointsSeries;
			this.plugin = plugin;
		}

		@Override
		public void run() {
			try {
				Thread.sleep(1500);
				while (true) {
					for (int i = 0; i < series.size(); i++) {
						List<Point2D> serie = series.get(i);
						plugin.setCloud(serie);
						Thread.sleep(150);
					}
				}
			} catch (Exception e) {
			}
		}
	}

	private List<List<Point2D>> loadData() {
		List<List<Point2D>> data = new ArrayList<List<Point2D>>();
		try {
			InputStream is = getClass().getResourceAsStream("data-cloud1.txt");
			InputStreamReader isreader = new InputStreamReader(is);
			BufferedReader in = new BufferedReader(isreader);
			String line = null;
			while ((line = in.readLine()) != null) {
				String c = line.substring(7, line.length() - 1);
				List<Point2D> v = loadEntry(c);
				data.add(v);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return data;
	}

	private List<Point2D> loadEntry(String cloud) {
		List<Point2D> points = new ArrayList<Point2D>();
		boolean empty = false;
		while (!empty) {
			int openCrochet = cloud.indexOf("[");
			int closeCrochet = cloud.indexOf("]");
			if (openCrochet == -1) {
				empty = true;
			} else {
				String point = cloud.substring(openCrochet, closeCrochet + 1);
				Point2D cp = loadXYPoint(point);
				points.add(cp);
				cloud = cloud.substring(closeCrochet + 1);
			}
		}
		return points;
	}

	private Point2D loadXYPoint(String spoint) {
		String[] P = null;
		try {
			String p = spoint.substring(1, spoint.length() - 1);
			P = p.split(",");
		} catch (StringIndexOutOfBoundsException e) {
		}
		return new Point2D.Double(Double.parseDouble(P[0]), Double.parseDouble(P[1]));
	}
}
