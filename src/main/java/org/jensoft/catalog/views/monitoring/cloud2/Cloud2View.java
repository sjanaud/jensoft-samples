/*
 * JenSoft API - Charting Framework
 * http://www.jensoftapi.com
 * Copyright (c) JenSoft. All rights reserved.
 * See JenSoft Software License Agreement
 */
package org.jensoft.catalog.views.monitoring.cloud2;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import org.jensoft.core.catalog.nature.JenSoftView;
import org.jensoft.core.palette.color.FilPalette;
import org.jensoft.core.palette.color.NanoChromatique;
import org.jensoft.core.palette.color.RosePalette;
import org.jensoft.core.plugin.AbstractPlugin;
import org.jensoft.core.plugin.background.DeviceNightBackground;
import org.jensoft.core.plugin.legend.title.TitleLegend;
import org.jensoft.core.plugin.legend.title.TitleLegendConstraints;
import org.jensoft.core.plugin.legend.title.TitleLegendConstraints.LegendAlignment;
import org.jensoft.core.plugin.legend.title.TitleLegendConstraints.LegendPosition;
import org.jensoft.core.plugin.legend.title.TitleLegendPlugin;
import org.jensoft.core.plugin.legend.title.painter.fil.TitleLegendGradientFill;
import org.jensoft.core.plugin.metrics.AxisMetricsPlugin;
import org.jensoft.core.plugin.outline.OutlinePlugin;
import org.jensoft.core.plugin.translate.TranslatePlugin;
import org.jensoft.core.plugin.zoom.box.ZoomBoxPlugin;
import org.jensoft.core.plugin.zoom.wheel.ZoomWheelPlugin;
import org.jensoft.core.projection.Projection;
import org.jensoft.core.view.View;
import org.jensoft.core.view.ViewPart;
import org.jensoft.core.view.background.ViewDarkBackground;

/**
 * <code>Cloud2View</code>
 * 
 * @author Sébastien Janaud
 * 
 */
@JenSoftView(background=ViewDarkBackground.class,description="Show a monitoring cloud point real time like")
public class Cloud2View extends View {

	private static final long serialVersionUID = -7042280707425749643L;

	public Cloud2View() {

		setPlaceHolderAxisWest(60);
		setPlaceHolderAxisSouth(60);

		Projection proj = new Projection.Linear(-100, 1612, -2, 82);
		proj.setName("cloud2 proj");
		proj.setThemeColor(RosePalette.LIME);
		proj.registerPlugin(new DeviceNightBackground());
		proj.registerPlugin(new OutlinePlugin());

		Font f =  new Font("Dialog", Font.PLAIN, 12);
		
		AxisMetricsPlugin.ModeledMetrics westMetrics = new AxisMetricsPlugin.ModeledMetrics.W();
		proj.registerPlugin(westMetrics);
		westMetrics.setTextFont(f);
		

		AxisMetricsPlugin.ModeledMetrics southMetrics = new AxisMetricsPlugin.ModeledMetrics.S();
		proj.registerPlugin(southMetrics);
		southMetrics.setTextFont(f);
		

		TitleLegend legend = new TitleLegend("Signal Input 2");
		legend.setLegendFill(new TitleLegendGradientFill(FilPalette.ORANGE1, FilPalette.GREEN4));
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

		Cloud2Plugin cloudPlugin = new Cloud2Plugin();
		proj.registerPlugin(cloudPlugin);

		PointerPlugin pointerPlugin = new PointerPlugin();
		proj.registerPlugin(pointerPlugin);

		registerProjection(proj);

		List<List<Point2D>> series = loadData();
		List<Point2D> serie_0 = series.get(0);
		
		cloudPlugin.setCloud(serie_0);
		
		MonitoringAnimator demoThread = new MonitoringAnimator(series, cloudPlugin, pointerPlugin);
		//demoThread.start();

	}

	class Cloud2Plugin extends AbstractPlugin {

		private List<Point2D> serie;
		

		public void setCloud(List<Point2D> serie) {
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

			g2d.setColor(getThemeColor());

			double average = 0;
			g2d.setColor(NanoChromatique.BLUE);
			for (int i = 0; i < serie.size(); i++) {
				Point2D p2dUser = serie.get(i);
				Projection proj = getProjection();

				Point2D p2dDevice = proj.userToPixel(p2dUser);

				Line2D l2d = new Line2D.Double(p2dDevice.getX(), p2dDevice.getY(), p2dDevice.getX(), p2dDevice.getY());
				g2d.draw(l2d);
				average = average + p2dDevice.getY();
			}

			average = average / serie.size();

			Line2D l2d = new Line2D.Double(0, average, getProjection().getDevice2D().getDeviceWidth(), average);
			g2d.setColor(NanoChromatique.ORANGE);
			g2d.draw(l2d);
		}

	}

	public class PointerPlugin extends AbstractPlugin implements AbstractPlugin.OnMoveListener, AbstractPlugin.OnPressListener, AbstractPlugin.OnDragListener {

		private Color markerColor = Color.LIGHT_GRAY;
		private Color markerLockColor = NanoChromatique.ORANGE;
		private Color pointerColor = RosePalette.AEGEANBLUE;

		public PointerPlugin() {
			setName("PointerPlugin");
			setOnMoveListener(this);
			setOnPressListener(this);
			setOnDragListener(this);
		}

		public Color getMarkerColor() {
			return markerColor;
		}

		public void setMarkerColor(Color markerColor) {
			this.markerColor = markerColor;
		}

		public Color getPointerColor() {
			return pointerColor;
		}

		public void setPointerColor(Color pointerColor) {
			this.pointerColor = pointerColor;
		}

		class Marker {

			boolean lockIntercept = false;
			boolean lockMove = false;
			int carrier = 1; // x
			double currentMER = 0; // y
		}

		private Marker marker = new Marker();

		private void interceptMarker(MouseEvent e) {

			Projection proj = getProjection();

			Point2D targetP2dUser = new Point2D.Double(marker.carrier, marker.currentMER);

			Point2D targetP2dDevice = proj.userToPixel(targetP2dUser);

			if (e.getX() < targetP2dDevice.getX() + 2 && e.getX() > targetP2dDevice.getX() - 2) {
				marker.lockIntercept = true;

			} else {
				marker.lockIntercept = false;

			}

			getProjection().getDevice2D().repaintDevice();
		}

		@Override
		public void onMove(MouseEvent me) {
			interceptMarker(me);
		}

		@Override
		public void onDrag(MouseEvent me) {
			if (!marker.lockMove) {
				return;
			}

			int currentX = me.getX();
			int currentY = me.getY();
			Projection proj = getProjection();
			// MANAGE MOVE MARKERS

			Point2D curentDevice = new Point2D.Double(currentX, currentY);
			Point2D curentUser = proj.pixelToUser(curentDevice);
			marker.carrier = (int) curentUser.getX();

			getProjection().getDevice2D().repaintDevice();

		}

		@Override
		public void onPress(MouseEvent me) {
			if (!marker.lockIntercept) {
				return;
			}
			marker.lockMove = true;

		}

		private List<Point2D> carriers;

		public void setMer(List<Point2D> carrierMer) {
			this.carriers = carrierMer;
			getProjection().getDevice2D().repaintDevice();
		}

		public void paintMarker(Graphics2D g2d) {

			Projection proj = getProjection();

			Point2D p2dUser = new Point2D.Double(marker.carrier, 0);
			Point2D p2dDevice = proj.userToPixel(p2dUser);

			Line2D l2dCarrier = new Line2D.Double(p2dDevice.getX(), 0, p2dDevice.getX(), proj.getDevice2D().getDeviceHeight());

			g2d.setColor(markerColor);
			if (marker.lockIntercept) {
				g2d.setColor(markerLockColor);
			}

			g2d.draw(l2dCarrier);

			// SHAPE CURRENT CARRIER
			double currentCarrierMER = getCarrierY(marker.carrier);
			Point2D p2dUserCurrentMer = new Point2D.Double(marker.carrier, currentCarrierMER);
			Point2D p2dDeviceCurrentMer = proj.userToPixel(p2dUserCurrentMer);

			Rectangle2D rect2D = new Rectangle2D.Double(p2dDeviceCurrentMer.getX() - 3, p2dDeviceCurrentMer.getY() - 3, 7, 7);
			g2d.setColor(pointerColor);
			g2d.fill(rect2D);

			// String annotation
			String annotation = "Carrier :" + marker.carrier + " MER :" + getCarrierY(marker.carrier);
			g2d.drawString(annotation, (int) p2dDevice.getX() + 10, proj.getDevice2D().getDeviceHeight() - 20);
		}

		private double getCarrierY(int carrier) {
			if (carriers == null) {
				return 0;
			}
			for (int i = 0; i < carriers.size(); i++) {
				int c = (int) carriers.get(i).getX();
				if (c == carrier) {
					return carriers.get(i).getY();
				}
			}
			return -1;
		}

		@Override
		protected void paintPlugin(View v2d, Graphics2D g2d, ViewPart viewPart) {

			if (viewPart != ViewPart.Device) {
				return;
			}

			paintMarker(g2d);
		}

	}

	/**
	 * simulate your real experience
	 */
	class MonitoringAnimator extends Thread {

		private Cloud2Plugin merPlugin;
		private PointerPlugin pointerPlugin;
		private List<List<Point2D>> series;

		public MonitoringAnimator(List<List<Point2D>> shoots, Cloud2Plugin merPlugin, PointerPlugin pointerPlugin) {
			setName("Cloud2 demo Thread");
			this.series = shoots;
			this.merPlugin = merPlugin;
			this.pointerPlugin = pointerPlugin;
		}

		@Override
		public void run() {

			try {
				Thread.sleep(1500);
				while (true) {

					for (int i = 0; i < series.size(); i++) {

						List<Point2D> serie = series.get(i);
						merPlugin.setCloud(serie);
						pointerPlugin.setMer(serie);
						Thread.sleep(150);

					}
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	private List<List<Point2D>> loadData() {
		List<List<Point2D>> points = new ArrayList<List<Point2D>>();
		try {
			InputStream is = getClass().getResourceAsStream("data-cloud2.txt");
			InputStreamReader isreader = new InputStreamReader(is);
			BufferedReader in = new BufferedReader(isreader);
			String line = null;
			while ((line = in.readLine()) != null) {
				String c = line.substring(5, line.length() - 1);
				List<Point2D> v = loadInput(c);
				points.add(v);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return points;

	}

	//private List<List<Point2D>> points = new ArrayList<List<Point2D>>();

	private List<Point2D> loadInput(String input) {
		List<Point2D> serie = new Vector<Point2D>();
		boolean empty = false;
		int count = 1;
		while (!empty) {
			int openCrochet = input.indexOf("[");
			int closeCrochet = input.indexOf("]");
			if (openCrochet == -1) {
				empty = true;
			} else {

				String point = input.substring(openCrochet, closeCrochet + 1);

				Point2D cp = loadYPoint(count, point);
				serie.add(cp);
				input = input.substring(closeCrochet + 1);
				count++;
			}
		}
		return serie;

	}

	private Point2D loadYPoint(int xindex, String spoint) {
		String p = "";
		try {
			p = spoint.substring(1, spoint.length() - 1);

		} catch (StringIndexOutOfBoundsException e) {
			e.printStackTrace();
		}

		return new Point2D.Double(xindex, Double.parseDouble(p));
	}

}
