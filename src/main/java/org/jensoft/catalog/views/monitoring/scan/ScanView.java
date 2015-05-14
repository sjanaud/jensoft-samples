/*
 * JenSoft API - Charting Framework
 * http://www.jensoftapi.com
 * Copyright (c) JenSoft. All rights reserved.
 * See JenSoft Software License Agreement
 */
package org.jensoft.catalog.views.monitoring.scan;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.jensoft.core.catalog.nature.JenSoftView;
import org.jensoft.core.graphics.Antialiasing;
import org.jensoft.core.palette.color.Alpha;
import org.jensoft.core.palette.color.FilPalette;
import org.jensoft.core.palette.color.RosePalette;
import org.jensoft.core.plugin.AbstractPlugin;
import org.jensoft.core.plugin.legend.title.TitleLegend;
import org.jensoft.core.plugin.legend.title.TitleLegendConstraints;
import org.jensoft.core.plugin.legend.title.TitleLegendConstraints.LegendAlignment;
import org.jensoft.core.plugin.legend.title.TitleLegendConstraints.LegendPosition;
import org.jensoft.core.plugin.legend.title.TitleLegendPlugin;
import org.jensoft.core.plugin.legend.title.painter.fil.TitleLegendGradientFill;
import org.jensoft.core.plugin.metrics.AxisMetricsPlugin;
import org.jensoft.core.plugin.outline.OutlinePlugin;
import org.jensoft.core.plugin.progress.ProgressDevicePlugin;
import org.jensoft.core.plugin.ray.Ray;
import org.jensoft.core.plugin.ray.Ray.RayNature;
import org.jensoft.core.plugin.ray.Ray.ThicknessType;
import org.jensoft.core.plugin.ray.RayPlugin;
import org.jensoft.core.plugin.ray.painter.draw.RayDefaultDraw;
import org.jensoft.core.plugin.ray.painter.fill.RayDefaultFill;
import org.jensoft.core.plugin.translate.TranslatePlugin;
import org.jensoft.core.plugin.zoom.box.ZoomBoxPlugin;
import org.jensoft.core.plugin.zoom.wheel.ZoomWheelPlugin;
import org.jensoft.core.projection.Projection;
import org.jensoft.core.view.View;
import org.jensoft.core.view.ViewPart;
import org.jensoft.core.view.background.ViewDarkBackground;

/**
 * <code>ScanView</code>
 * 
 * @author Sébastien Janaud
 * 
 */
@JenSoftView(background=ViewDarkBackground.class,description="Show a monitoring scan channel real time like")
public class ScanView extends View {

	private static final long serialVersionUID = -8537806571044651797L;

	public ScanView() {

		setPlaceHolderAxisWest(60);
		setPlaceHolderAxisSouth(60);

		Projection proj = new Projection.Linear(0, 150, 0, 103);
		proj.setName("scan proj");
		proj.setThemeColor(RosePalette.LEMONPEEL);

		Font f =  new Font("Dialog", Font.PLAIN, 12);
		
		AxisMetricsPlugin.ModeledMetrics westMetrics = new AxisMetricsPlugin.ModeledMetrics.W();
		proj.registerPlugin(westMetrics);
		westMetrics.setTextFont(f);
		

		AxisMetricsPlugin.ModeledMetrics southMetrics = new AxisMetricsPlugin.ModeledMetrics.S();
		proj.registerPlugin(southMetrics);
		southMetrics.setTextFont(f);
		

		proj.registerPlugin(new OutlinePlugin());

		TitleLegend legend = new TitleLegend("Scan");
		legend.setLegendFill(new TitleLegendGradientFill(FilPalette.ORANGE1, FilPalette.ORANGE2));
		legend.setFont(f);
		legend.setConstraints(new TitleLegendConstraints(LegendPosition.East, 0.3f, LegendAlignment.Rigth));
		TitleLegendPlugin legendPlugin = new TitleLegendPlugin(legend);
		proj.registerPlugin(legendPlugin);

		ZoomBoxPlugin zoomTool = new ZoomBoxPlugin();
		proj.registerPlugin(zoomTool);

		TranslatePlugin toolTranslate = new TranslatePlugin();
		proj.registerPlugin(toolTranslate);

		ZoomWheelPlugin zoomWheel = new ZoomWheelPlugin();
		proj.registerPlugin(zoomWheel);

		ScanInfoPlugin scanPlugin = new ScanInfoPlugin();
		RayPlugin rayPlugin = new RayPlugin();
		ProgressDevicePlugin progressPlugin = new ProgressDevicePlugin();

		proj.registerPlugin(rayPlugin);
		proj.registerPlugin(scanPlugin);
		proj.registerPlugin(progressPlugin);

		registerProjection(proj);

		ScanAnimator scanAnimator = new ScanAnimator(rayPlugin, scanPlugin, progressPlugin);
		//scanAnimator.start();

	}

	class ScanInfoPlugin extends AbstractPlugin {

		private List<Ray> channels = new ArrayList<Ray>();

		public ScanInfoPlugin() {
			setName("ScanInfoPlugin");
			setAntialiasing(Antialiasing.On);
		}

		boolean lockScan = false;

		public void start() {
			lockScan = true;
		}

		public void stop() {
			lockScan = false;
		}

		public void addChannel(Ray channel) {
			channels.add(channel);
		}

		private void paintScanningInfo(Graphics2D g2d) {
			String annotation = "Number Scanned channels : " + channels.size();
			g2d.setFont(new Font("Tahoma", Font.PLAIN, 10));
			g2d.setColor(Color.BLACK);
			g2d.setComposite(java.awt.AlphaComposite.getInstance(java.awt.AlphaComposite.SRC_OVER, 1f));
			g2d.drawString(annotation, 5, 20);
		}

		private void paintCurrentScanningInfo(Graphics2D g2d) {
			String annotation = "Number Scanned channels : " + channels.size();
			g2d.setFont(new Font("Tahoma", Font.PLAIN, 10));
			g2d.setColor(Color.BLACK);
			g2d.setComposite(java.awt.AlphaComposite.getInstance(java.awt.AlphaComposite.SRC_OVER, 1f));
			g2d.drawString(annotation, 5, 40);
		}

		private void paintCurrentLastMultiplexScanningInfo(Graphics2D g2d) {

			if (channels.size() - 1 >= 0) {
				Ray sm = channels.get(channels.size() - 1);
				String annotation = "Last Channel detect : " + sm.getName() + " | Power " + sm.getRayValue() + " dBm";
				g2d.setFont(new Font("Tahoma", Font.PLAIN, 10));
				g2d.setColor(Color.BLACK);
				g2d.setComposite(java.awt.AlphaComposite.getInstance(java.awt.AlphaComposite.SRC_OVER, 1f));
				g2d.drawString(annotation, 5, 60);
			}
		}

		@Override
		protected void paintPlugin(View v2d, Graphics2D g2d, ViewPart viewPart) {

			if (viewPart != ViewPart.Device) {
				return;
			}

			if (lockScan) {

				paintCurrentScanningInfo(g2d);
				paintCurrentLastMultiplexScanningInfo(g2d);
			} else {
				paintScanningInfo(g2d);
			}
		}

	}

	class ScanAnimator extends Thread {

		private Random rand = new Random();
		private RayPlugin rayPlugin;
		private ScanInfoPlugin scanPlugin;
		private ProgressDevicePlugin progressPlugin;

		public ScanAnimator(RayPlugin rayPlugin, ScanInfoPlugin scanPlugin, ProgressDevicePlugin progressPlugin) {
			this.rayPlugin = rayPlugin;
			this.scanPlugin = scanPlugin;
			this.progressPlugin = progressPlugin;
		}

		private void makeChannel(int i) {

			Ray ray = new Ray();
			ray.setRayNature(RayNature.XRay);
			ray.setRay(i);
			ray.setRayBase(0);
			ray.setThickness(6);
			ray.setThicknessType(ThicknessType.Device);
			ray.setAscentValue(0);
			ray.setRayFill(new RayDefaultFill(new Alpha(RosePalette.CALYPSOBLUE, 250)));
			ray.setRayDraw(new RayDefaultDraw(new Alpha(Color.WHITE, 80)));
			rayPlugin.addRay(ray);

			ray.inflate(rand.nextInt(76), 0, 200, 20);
			scanPlugin.addChannel(ray);

			scanPlugin.getProjection().getView().repaintDevice();
		}

		@Override
		public void run() {
			try {

				Thread.sleep(1000);

				scanPlugin.start();
				progressPlugin.start();

				for (int i = 0; i < 150; i++) {
					progressPlugin.setCurentProcess(i);
					scanPlugin.getProjection().getDevice2D().repaintDevice();

					Thread.sleep(20);

					if (i == 12) {
						makeChannel(12);
					}

					if (i == 16) {
						makeChannel(16);
					}

					if (i == 20) {
						makeChannel(20);
					}

					if (i == 32) {
						makeChannel(32);
					}

					if (i == 59) {
						makeChannel(59);
					}

					if (i == 70) {
						makeChannel(70);
					}

					if (i == 77) {
						makeChannel(77);
					}

					if (i == 80) {
						makeChannel(80);
					}

					if (i == 89) {
						makeChannel(89);
					}

					if (i == 130) {
						makeChannel(130);
					}

					Thread.sleep(200);
				}

				scanPlugin.stop();
				progressPlugin.stop();

				scanPlugin.getProjection().getDevice2D().repaintDevice();

				Thread.sleep(10000);

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	@Override
	protected void paintComponent(Graphics g) {
		// super.paintComponent(g);
	}
}
