/*
s * JenSoft API - Charting Framework
 * http://www.jensoftapi.com
 * Copyright (c) JenSoft. All rights reserved.
 * See JenSoft Software License Agreement
 */
package org.jensoft.catalog.views.monitoring.spectrum;

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

import org.jensoft.core.catalog.nature.JenSoftView;
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
import org.jensoft.core.plugin.stripe.StripePlugin.MultiplierStripe;
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
 * <code>SpectrumView</code>
 * 
 * @author Sébastien Janaud
 * 
 */
@JenSoftView(background=ViewDarkBackground.class,description="Show a monitoring line function real time like")
public class SpectrumView extends View {

	private static final long serialVersionUID = 3593171237158942199L;

	public SpectrumView() {
		super();

		setPlaceHolderAxisWest(60);
		setPlaceHolderAxisSouth(60);

		Projection proj = new Projection.Linear(-160, 160, -110, 0);
		proj.setName("spectrum proj");
		proj.setThemeColor(RosePalette.AEGEANBLUE);

		MultiplierStripe stripes = new StripePlugin.MultiplierStripe.H(-50, 20);
		StripePalette bp = new StripePalette();
		float[] fractions = { 0f, 0.5f, 1f };
		Color c = RosePalette.AMETHYST;
		Color[] colors = { ColorPalette.alpha(c, 200), ColorPalette.alpha(c, 80), ColorPalette.alpha(c, 200) };
		bp.addPaint(fractions, colors);
		bp.addPaint(new Color(255, 255, 255, 100));
		stripes.setStripePalette(bp);
		stripes.setAlpha(0.1f);
		proj.registerPlugin(stripes);

		GridPlugin gridLayout = new GridPlugin.MultiplierGrid(-50, 20, GridOrientation.Horizontal);
		gridLayout.setGridColor(new Color(59, 89, 152, 100));
		proj.registerPlugin(gridLayout);

		Font f =  new Font("Dialog", Font.PLAIN, 12);
		
		AxisMetricsPlugin.ModeledMetrics westMetrics = new AxisMetricsPlugin.ModeledMetrics.W();
		proj.registerPlugin(westMetrics);
		westMetrics.setTextFont(f);
		

		AxisMetricsPlugin.ModeledMetrics southMetrics = new AxisMetricsPlugin.ModeledMetrics.S();
		proj.registerPlugin(southMetrics);
		southMetrics.setTextFont(f);
		

		proj.registerPlugin(new OutlinePlugin());

		TitleLegend legend = new TitleLegend("Spectrum");
		legend.setLegendFill(new TitleLegendGradientFill(Color.WHITE, RosePalette.EMERALD));
		legend.setFont(f);
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
		compass.setCompassStyle(CompassStyle.Merge);
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
		registerProjection(proj);

		Vector<Vector<Point2D>> series = loadData();
		Vector<Point2D> serie_0 = series.get(0);
		LineSource lineSourceInit = new LineSource(serie_0);
		curve.setSourceFunction(lineSourceInit);
		
		CurveAnimator demoThread = new CurveAnimator(loadData(), areaCurvePlugin, curve, peakHolderPlugin);
		//demoThread.start();

	}

	class PeakHolderPlugin extends AbstractPlugin {

		public PeakHolderPlugin() {
			setName("PeakHolderPlugin");
		}

		private LineSource serie;

		public void setSerie(LineSource serie) {
			this.serie = serie;
			getProjection().getDevice2D().repaintDevice();
		}

		@Override
		protected void paintPlugin(View v2d, Graphics2D g2d, ViewPart viewPart) {

			if (viewPart != ViewPart.Device) {
				return;
			}

			if (serie == null) {
				return;
			}

			int indexMax = 0;
			int indexMin = 0;
			double max = serie.getCurrentFunction().get(0).getY();
			double min = serie.getCurrentFunction().get(0).getY();
			for (int i = 0; i < serie.getCurrentFunction().size(); i++) {

				Point2D p = serie.getCurrentFunction().get(i);

				if (p.getY() > max) {
					indexMax = i;
					max = p.getY();
				}
				if (p.getY() < min) {
					indexMin = i;
					min = p.getY();
				}
			}

			Point2D p2dUserMax = serie.getCurrentFunction().get(indexMax);
			Point2D p2dUserMin = serie.getCurrentFunction().get(indexMin);

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

	class CurveAnimator extends Thread {

		private AreaFunction areaCurvePlugin;
		private Area curve;
		private PeakHolderPlugin peakHolderPlugin;
		private Vector<Vector<Point2D>> data;

		public CurveAnimator(Vector<Vector<Point2D>> data, AreaFunction areaCurvePlugin, Area curve, PeakHolderPlugin peakHolderLayout) {
			setName("Dashboard Monitoring1 Thread");
			this.data = data;
			this.areaCurvePlugin = areaCurvePlugin;
			this.curve = curve;
			peakHolderPlugin = peakHolderLayout;
		}

		@Override
		public void run() {

			try {
				Thread.sleep(1500);
				while (true) {

					for (int i = 0; i < data.size(); i++) {

						Vector<Point2D> raw = data.get(i);
						LineSource s2d = new LineSource(raw);
						curve.setSourceFunction(s2d);
						curve.getPathFunction().setSolveGeometryRequest(true);
						peakHolderPlugin.setSerie(s2d);
						areaCurvePlugin.getProjection().getView().repaintDevice();
						Thread.sleep(150);

					}
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public static Vector<Vector<Point2D>> loadData() {
		Vector<Vector<Point2D>> data = new Vector<Vector<Point2D>>();
		try {
			InputStream is = SpectrumView.class.getResourceAsStream("data-spectrum.txt");
			InputStreamReader isreader = new InputStreamReader(is);
			BufferedReader in = new BufferedReader(isreader);
			String line = null;
			while ((line = in.readLine()) != null) {
				String c = line.substring(9, line.length() - 1);
				Vector<Point2D> v = loadEntry(c);
				data.add(v);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return data;
	}

	private static Vector<Point2D> loadEntry(String spectre) {
		Vector<Point2D> cVector = new Vector<Point2D>();
		boolean empty = false;
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
			}
		}
		return cVector;
	}

	private static Point2D loadXYPoint(String spoint) {
		String[] P = null;
		try {
			String p = spoint.substring(1, spoint.length() - 1);
			P = p.split(",");
		} catch (StringIndexOutOfBoundsException e) {
			e.printStackTrace();
		}
		return new Point2D.Double(Double.parseDouble(P[0]), Double.parseDouble(P[1]));
	}

}
