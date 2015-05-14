/*
 * JenSoft API - Charting Framework
 * http://www.jensoftapi.com
 * Copyright (c) JenSoft. All rights reserved.
 * See JenSoft Software License Agreement
 */
package org.jensoft.catalog.views.intro.breakpoint;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Vector;

import org.jensoft.catalog.views.intro.DemoBreakPoint;
import org.jensoft.catalog.views.intro.JenSoftBreackpointExecutor;
import org.jensoft.core.palette.color.Alpha;
import org.jensoft.core.palette.color.ColorPalette;
import org.jensoft.core.palette.color.NanoChromatique;
import org.jensoft.core.palette.color.RosePalette;
import org.jensoft.core.plugin.AbstractPlugin;
import org.jensoft.core.plugin.function.FunctionPlugin.AreaFunction;
import org.jensoft.core.plugin.function.area.Area;
import org.jensoft.core.plugin.function.area.painter.draw.AreaDefaultDraw;
import org.jensoft.core.plugin.function.area.painter.fill.AreaGradientFill;
import org.jensoft.core.plugin.function.source.UserSourceFunction.LineSource;
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
import org.jensoft.core.view.WidgetPlugin.PushingBehavior;

public class RealtimeCurveBreakpoint extends DemoBreakPoint {

	public RealtimeCurveBreakpoint(JenSoftBreackpointExecutor jenSoftDemoExecutor, View view) {
		super(jenSoftDemoExecutor, view);
	}

	@Override
	protected DemoMessage getMessage() {
		DemoMessage msg = new DemoMessage("Continue and install curve.");
		msg.setSize(320, 110);
		msg.setTitle("JenSoft API - Curve");
		msg.setMessageTitleColor(Color.WHITE);
		msg.setMessageForeground(Color.WHITE);
		return msg;
	}

	public class PeakHolderPlugin extends AbstractPlugin {

		public PeakHolderPlugin() {
			setName("PeakHolderPlugin");
		}

		private LineSource sourceFunction;

		public void setSerie(LineSource serie) {
			this.sourceFunction = serie;
			getProjection().getDevice2D().repaintDevice();
		}

		@Override
		protected void paintPlugin(View v2d, Graphics2D g2d, ViewPart viewPart) {

			if (viewPart != ViewPart.Device) {
				return;
			}

			if (sourceFunction == null) {
				return;
			}

			int indexMax = 0;
			int indexMin = 0;
			double max = sourceFunction.getCurrentFunction().get(0).getY();
			double min = sourceFunction.getCurrentFunction().get(0).getY();
			for (int i = 0; i < sourceFunction.getCurrentFunction().size(); i++) {

				Point2D p = sourceFunction.getCurrentFunction().get(i);

				if (p.getY() > max) {
					indexMax = i;
					max = p.getY();
				}
				if (p.getY() < min) {
					indexMin = i;
					min = p.getY();
				}
			}

			Point2D p2dUserMax = sourceFunction.getCurrentFunction().get(indexMax);
			Point2D p2dUserMin = sourceFunction.getCurrentFunction().get(indexMin);

			Point2D p2dDeviceMax = getProjection().userToPixel(p2dUserMax);
			Point2D p2dDeviceMin = getProjection().userToPixel(p2dUserMin);

			Rectangle2D rect2DMax = new Rectangle2D.Double(p2dDeviceMax.getX() - 10, p2dDeviceMax.getY() - 10, 20, 20);
			Rectangle2D rect2DMin = new Rectangle2D.Double(p2dDeviceMin.getX() - 10, p2dDeviceMin.getY() - 10, 20, 20);

			g2d.setColor(Color.WHITE);
			g2d.draw(rect2DMax);
			g2d.draw(rect2DMin);

			g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.3f));
			g2d.setColor(NanoChromatique.RED);
			g2d.fill(rect2DMax);
			g2d.setColor(NanoChromatique.BLUE);
			g2d.fill(rect2DMin);
			g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));

			g2d.setFont(new Font("Tahoma", Font.PLAIN, 10));

			String messageMax = "PEAK MAX :" + p2dUserMax.getY();
			String messageMin = "PEAK MIN :" + p2dUserMin.getY();

			g2d.setColor(Color.RED);
			g2d.drawString(messageMax, getProjection().getDevice2D().getDeviceWidth() - 100, 30);
			g2d.drawString(messageMin, getProjection().getDevice2D().getDeviceWidth() - 100, 40);

		}

	}

	public class SpectrumAnimator extends Thread {

		private AreaFunction areaCurvePlugin;
		private Area curve;
		private PeakHolderPlugin peakHolderPlugin;
		private Vector<Vector<Point2D>> shoots;

		public SpectrumAnimator(Vector<Vector<Point2D>> shoots, AreaFunction spectrumLayout, Area curve, PeakHolderPlugin peakHolderLayout) {
			setName("Intro Spectrum Thread");
			this.shoots = shoots;
			areaCurvePlugin = spectrumLayout;
			this.curve = curve;
			peakHolderPlugin = peakHolderLayout;
		}

		@Override
		public void run() {

			try {

				long flagTime = 1000 * 10;
				long startMillis = System.currentTimeMillis();
				boolean flagrun = true;
				while (flagrun || !interrupted()) {

					for (int i = 0; i < shoots.size(); i++) {
						long currentDuration = System.currentTimeMillis() - startMillis;
						if (currentDuration > flagTime) {
							flagrun = false;
							interrupt();
						}
						Vector<Point2D> shoot = shoots.get(i);
						LineSource s2d = new LineSource(shoot);
						curve.setSourceFunction(s2d);
						curve.getPathFunction().setSolveGeometryRequest(true);
						peakHolderPlugin.setSerie(s2d);
						areaCurvePlugin.getProjection().getView().repaintDevice();
						Thread.sleep(150);

					}
				}

			} catch (Exception e) {
			}
		}
	}

	private Vector<Vector<Point2D>> loadShoots() {
		Vector<Vector<Point2D>> shoots = new Vector<Vector<Point2D>>();

		try {
			InputStream is = getClass().getResourceAsStream("monitoring-input2.txt");
			InputStreamReader isreader = new InputStreamReader(is);
			BufferedReader in = new BufferedReader(isreader);
			String line = null;
			while ((line = in.readLine()) != null) {
				String c = line.substring(9, line.length() - 1);
				Vector<Point2D> v = loadSpectre(c);
				shoots.add(v);
			}
		} catch (IOException e) {
			System.err.println("Cannot load ad resource " + "monitoring-input2.txt" + " from class " + getClass());
			e.printStackTrace();

		} catch (Throwable e) {
			e.printStackTrace();
		}

		return shoots;

	}

	private Vector<Point2D> loadSpectre(String spectre) {
		Vector<Point2D> cVector = new Vector<Point2D>();

		boolean empty = false;
		int count = 0;
		while (!empty) {
			int openCrochet = spectre.indexOf("[");
			int closeCrochet = spectre.indexOf("]");

			if (openCrochet == -1) {
				empty = true;
			} else {

				String point = spectre.substring(openCrochet, closeCrochet + 1);
				Point2D cp = loadXYPoint(point);
				cVector.add(cp);
				spectre = spectre.substring(closeCrochet + 1);
				count++;
			}

		}
		return cVector;

	}

	private Point2D loadXYPoint(String spoint) {

		String[] P = null;
		try {
			String p = spoint.substring(1, spoint.length() - 1);
			P = p.split(",");
		} catch (StringIndexOutOfBoundsException e) {
			e.printStackTrace();
		}

		return new Point2D.Double(Double.parseDouble(P[0]), Double.parseDouble(P[1]));
	}

	@Override
	public void run() {
		super.run();
		try {
			synchronized (this) {
				Font f =  new Font("Dialog", Font.PLAIN, 14);
				view.getWidgetPlugin().pushMessage("Install Spectrum ", 500, null, PushingBehavior.Fast, f, Color.BLACK);

				wait(1000);

				view.setPlaceHolderAxisWest(100);

				Projection proj = new Projection.Linear(-160, 160, -110, 0);
				proj.setName("spectrum proj");
				proj.setThemeColor(RosePalette.AEGEANBLUE);
				view.registerProjection(proj);

				StripePlugin bandLayout = new StripePlugin.MultiplierStripe.H(-50, 20);
				StripePalette bp = new StripePalette();
				float[] fractions = { 0f, 0.5f, 1f };
				Color c = RosePalette.AMETHYST;
				Color[] colors = { ColorPalette.alpha(c, 200), ColorPalette.alpha(c, 80), ColorPalette.alpha(c, 200) };
				bp.addPaint(fractions, colors);
				bp.addPaint(new Color(255, 255, 255, 100));
				bandLayout.setStripePalette(bp);
				bandLayout.setAlpha(0.1f);
				proj.registerPlugin(bandLayout);

				GridPlugin gridLayout = new GridPlugin.MultiplierGrid(-50, 20, GridOrientation.Horizontal);
				gridLayout.setGridColor(new Color(255, 255, 255, 60));
				proj.registerPlugin(gridLayout);

				Font f12 =  new Font("Dialog", Font.PLAIN, 12);
				
				// metrics
				AxisMetricsPlugin.ModeledMetrics westMetrics = new AxisMetricsPlugin.ModeledMetrics.W();
				proj.registerPlugin(westMetrics);
				westMetrics.setTextFont(f12);
				

				AxisMetricsPlugin.ModeledMetrics southMetrics = new AxisMetricsPlugin.ModeledMetrics.S();
				proj.registerPlugin(southMetrics);
				southMetrics.setTextFont(f12);
				

				proj.registerPlugin(new OutlinePlugin());

				TitleLegend legend = new TitleLegend("Spectrum");
				legend.setLegendFill(new TitleLegendGradientFill(Color.WHITE, RosePalette.EMERALD));
				legend.setFont(f12);
				legend.setConstraints(new TitleLegendConstraints(LegendPosition.East, 0.3f, LegendAlignment.Rigth));
				TitleLegendPlugin legendPlugin = new TitleLegendPlugin(legend);
				proj.registerPlugin(legendPlugin);

				// ZOOM BOX
				ZoomBoxPlugin zoomBox = new ZoomBoxPlugin();
				zoomBox.registerContext(new ZoomBoxDefaultDeviceContext());
				proj.registerPlugin(zoomBox);

				// TRANSLATE
				TranslatePlugin translatePlugin = new TranslatePlugin();
				proj.registerPlugin(translatePlugin);

				// use pad widget with objectif
				TranslateX tx = new TranslateX(80, 12);
				TranslateY ty = new TranslateY(12, 80);
				translatePlugin.registerWidget(tx, ty);

				TranslateCompassWidget compass = new TranslateCompassWidget();
				translatePlugin.registerWidget(compass);
				compass.setRingFillColor(new Alpha(RosePalette.EMERALD, 150));
				compass.setRingDrawColor(Color.WHITE);
				compass.setRingNeedleFillColor(new Alpha(RosePalette.EMERALD, 150));
				compass.setRingNeedleDrawColor(Color.WHITE);

				translatePlugin.registerContext(new TranslateDefaultDeviceContext());

				// plugin/objectif
				ZoomLensPlugin zoomObjectif = new ZoomLensPlugin();

				// plugin/objectif/widget
				LensX ox = new LensX(80, 12);
				ox.setOutlineColor(Color.WHITE);
				ox.setButton1DrawColor(RosePalette.REDWOOD);
				ox.setButton2DrawColor(RosePalette.REDWOOD);

				LensY oy = new LensY(12, 80);
				oy.setOutlineColor(Color.WHITE);
				oy.setButton1DrawColor(RosePalette.REDWOOD);
				oy.setButton2DrawColor(RosePalette.REDWOOD);

				zoomObjectif.registerWidget(ox);
				zoomObjectif.registerWidget(oy);

				// plugin/objectif/context
				zoomObjectif.registerContext(new LensDefaultDeviceContext());

				proj.registerPlugin(zoomObjectif);

				// pre lock selected widget (selectable widget need a lock)
				zoomObjectif.lockSelected();

				// ZOOM PERCENT
				ZoomPercentPlugin zoomPercent = new ZoomPercentPlugin();
				zoomPercent.registerContext(new ZoomPercentDefaultDeviceContext());
				proj.registerPlugin(zoomPercent);

				ZoomWheelPlugin zoomWheel = new ZoomWheelPlugin();
				proj.registerPlugin(zoomWheel);

				MarkerPlugin markerPlugin = new MarkerPlugin();
				markerPlugin.registerContext(new MarkerDefaultDeviceContext());
				proj.registerPlugin(markerPlugin);

				AreaFunction areaCurvePlugin = new AreaFunction();
				proj.registerPlugin(areaCurvePlugin);

				Area curve = new Area();
				curve.setAreaFill(new AreaGradientFill());
				curve.setAreaDraw(new AreaDefaultDraw(Color.WHITE, Color.WHITE));
				curve.setAreaBase(-120);
				curve.setThemeColor(NanoChromatique.RED);
				areaCurvePlugin.addFunction(curve);

				PeakHolderPlugin peakHolderPlugin = new PeakHolderPlugin();
				proj.registerPlugin(peakHolderPlugin);
				view.repaint();

				// Thread.sleep(1000000);

				SpectrumAnimator demoThread = new SpectrumAnimator(loadShoots(), areaCurvePlugin, curve, peakHolderPlugin);
				demoThread.start();
				demoThread.join();
				Thread.sleep(1000);
				view.unregisterProjection(proj);

				view.repaint();

			}
		} catch (InterruptedException e) {
		} finally {
			// System.out.println("out executor : " +
			// getClass().getSimpleName());
		}
	}

}
