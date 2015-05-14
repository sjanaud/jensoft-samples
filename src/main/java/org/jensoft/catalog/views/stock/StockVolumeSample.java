/*
 * JenSoft API - Charting Framework
 * http://www.jensoftapi.com
 * Copyright (c) JenSoft. All rights reserved.
 * See JenSoft Software License Agreement
 */
package org.jensoft.catalog.views.stock;

import java.awt.Color;
import java.awt.Font;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.StringTokenizer;

import org.jensoft.core.catalog.nature.JenSoftView;
import org.jensoft.core.catalog.ui.ViewFrameUI;
import org.jensoft.core.palette.color.ColorPalette;
import org.jensoft.core.palette.color.RosePalette;
import org.jensoft.core.palette.color.TangoPalette;
import org.jensoft.core.plugin.grid.Grid.GridOrientation;
import org.jensoft.core.plugin.grid.GridPlugin;
import org.jensoft.core.plugin.grid.GridPlugin.ModeledGrid;
import org.jensoft.core.plugin.grid.manager.ModeledGridManager.GridModelRangeCollections;
import org.jensoft.core.plugin.legend.title.TitleLegend;
import org.jensoft.core.plugin.legend.title.TitleLegendConstraints;
import org.jensoft.core.plugin.legend.title.TitleLegendConstraints.LegendAlignment;
import org.jensoft.core.plugin.legend.title.TitleLegendConstraints.LegendPosition;
import org.jensoft.core.plugin.legend.title.TitleLegendPlugin;
import org.jensoft.core.plugin.legend.title.painter.fil.TitleLegendGradientFill;
import org.jensoft.core.plugin.metrics.AxisMetricsPlugin;
import org.jensoft.core.plugin.metrics.AxisMetricsPlugin.Axis;
import org.jensoft.core.plugin.metrics.manager.TimeMetricsManager;
import org.jensoft.core.plugin.outline.OutlinePlugin;
import org.jensoft.core.plugin.stock.Stock;
import org.jensoft.core.plugin.stock.StockLayer;
import org.jensoft.core.plugin.stock.StockPlugin;
import org.jensoft.core.plugin.translate.TranslateDefaultDeviceContext;
import org.jensoft.core.plugin.translate.TranslatePlugin;
import org.jensoft.core.plugin.zoom.box.ZoomBoxDefaultDeviceContext;
import org.jensoft.core.plugin.zoom.box.ZoomBoxPlugin;
import org.jensoft.core.plugin.zoom.box.ZoomBoxPlugin.BoxType;
import org.jensoft.core.plugin.zoom.lens.LensDefaultDeviceContext;
import org.jensoft.core.plugin.zoom.lens.LensX;
import org.jensoft.core.plugin.zoom.lens.LensY;
import org.jensoft.core.plugin.zoom.lens.ZoomLensPlugin;
import org.jensoft.core.plugin.zoom.wheel.ZoomWheelPlugin;
import org.jensoft.core.plugin.zoom.wheel.ZoomWheelPlugin.ZoomWheelType;
import org.jensoft.core.projection.Projection;
import org.jensoft.core.view.View;
import org.jensoft.core.view.background.ViewDarkBackground;

/**
 * <code>StockVolumeSample</code>
 * 
 * @author Sébastien Janaud
 */
@JenSoftView(background = ViewDarkBackground.class, description = "Display stock volume charts")
public class StockVolumeSample extends View {

	private static final long serialVersionUID = 3651598852985079631L;
	
	private TranslatePlugin translate;
	private ZoomBoxPlugin zoomBox;
	private ZoomWheelPlugin zoomWheel;
	private ZoomLensPlugin zoomLens;

	/**
	 * Create stock volume chart
	 */
	public StockVolumeSample() {
		super();

		// set size in pixel for outer's components
		setPlaceHolderAxisSouth(80);
		setPlaceHolderAxisWest(80);

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		Date start = null;
		Date end = null;
		try {
			start = sdf.parse("2012/12/01");
			end = sdf.parse("2014/01/01");
		} catch (ParseException e) {
			e.printStackTrace();
		}

		List<Stock> stocks = loadStockData();
		double max = 0;
		for (Stock stock : stocks) {
			max = Math.max(max, stock.getVolume());
		}

		Projection.Linear proj = new Projection.Linear(start.getTime(), end.getTime(), 0,max+max/4);
		registerProjection(proj);

		proj.setThemeColor(RosePalette.LEMONPEEL);

		// grid
		ModeledGrid grids = new GridPlugin.ModeledGrid(GridOrientation.Horizontal);
		grids.registerGridModels(GridModelRangeCollections.YoctoYotta);
		grids.setGridColor(ColorPalette.alpha(TangoPalette.ALUMINIUM2, 100));
		//grids.setGridStroke(StrokePalette.drawingStroke5);
		proj.registerPlugin(grids);
				
		StockPlugin stockPlugin = new StockPlugin();
		proj.registerPlugin(stockPlugin);
		stockPlugin.addLayer(new StockLayer.Volume());
		stockPlugin.setStocks(stocks);

		// legend
		Font f =  new Font("Dialog", Font.PLAIN, 12);
		TitleLegend legend = new TitleLegend("SLV - VOLUME - 2013");
		legend.setLegendFill(new TitleLegendGradientFill(TangoPalette.ALUMINIUM5, TangoPalette.ALUMINIUM6));
		legend.setFont(f);
		legend.setConstraints(new TitleLegendConstraints(LegendPosition.North, 0.1f, LegendAlignment.Rigth));
		TitleLegendPlugin legendPlugin = new TitleLegendPlugin(legend);
		proj.registerPlugin(legendPlugin);

		

		AxisMetricsPlugin.ModeledMetrics westMetrics = new AxisMetricsPlugin.ModeledMetrics.W();
		proj.registerPlugin(westMetrics);
		westMetrics.setTextFont(f);
		
		Font fm =  new Font("Dialog", Font.PLAIN, 12);
		Font fM =  new Font("Dialog", Font.PLAIN, 12);
		
		AxisMetricsPlugin.TimeMetrics timingMetrics = new AxisMetricsPlugin.TimeMetrics(Axis.AxisSouth);
		timingMetrics.setMedianTextFont(fm);
		timingMetrics.setMajorTextFont(fM);
		timingMetrics.registerTimeModel(new TimeMetricsManager.Minute1Model());
		timingMetrics.registerTimeModel(new TimeMetricsManager.Minute15Model());
		timingMetrics.registerTimeModel(new TimeMetricsManager.HourModel());
		timingMetrics.registerTimeModel(new TimeMetricsManager.Hour3Model());
		timingMetrics.registerTimeModels(new TimeMetricsManager.WeekModel());
		timingMetrics.registerTimeModels(new TimeMetricsManager.MonthModel());
		proj.registerPlugin(timingMetrics);

		
		
		// grid plug-in
		//GridPlugin grids = new GridPlugin.MultiplierGrid(0, 80, GridOrientation.Horizontal);
		//grids.setGridColor(new Color(59, 89, 152, 100));
		//proj.registerPlugin(grids);

		// outline plug-in
		proj.registerPlugin(new OutlinePlugin());

		// zoom box plug-in
		zoomBox = new ZoomBoxPlugin();
		proj.registerPlugin(zoomBox);
		zoomBox.setBoxType(BoxType.BoxX);
		zoomBox.registerContext(new ZoomBoxDefaultDeviceContext());

		// translate plug-in
		translate = new TranslatePlugin();
		proj.registerPlugin(translate);
		translate.registerContext(new TranslateDefaultDeviceContext());

		// zoom wheel plug-in
		zoomWheel = new ZoomWheelPlugin();
		zoomWheel.setZoomWheelType(ZoomWheelType.ZoomX);
		proj.registerPlugin(zoomWheel);

		// lens plug-in
		zoomLens = new ZoomLensPlugin();
		zoomLens.registerContext(new LensDefaultDeviceContext());
		// zoomLens.setZoomLensType(ZoomLensType.ZoomX);
		proj.registerPlugin(zoomLens);
		// zoomLens.lockSelected();

		LensX ox = new LensX(120, 12, 100, 0);
		LensY oy = new LensY(12,60);

		zoomLens.registerWidget(ox, oy);

		ox.setOutlineColor(Color.BLACK);
		ox.setButton1DrawColor(RosePalette.CALYPSOBLUE);
		ox.setButton2DrawColor(RosePalette.CALYPSOBLUE);
		oy.setOutlineColor(Color.BLACK);
		oy.setButton1DrawColor(RosePalette.CALYPSOBLUE);
		oy.setButton2DrawColor(RosePalette.CALYPSOBLUE);

	}

	public TranslatePlugin getTranslate() {
		return translate;
	}

	public ZoomBoxPlugin getZoomBox() {
		return zoomBox;
	}

	public ZoomWheelPlugin getZoomWheel() {
		return zoomWheel;
	}

	public ZoomLensPlugin getZoomLens() {
		return zoomLens;
	}

	public static List<Stock> loadStockData() {
		List<Stock> data = new ArrayList<Stock>();
		try {
			InputStream is = StockVolumeSample.class.getResourceAsStream("slv-2013.csv");
			InputStreamReader isreader = new InputStreamReader(is);
			BufferedReader in = new BufferedReader(isreader);
			String line = null;
			int count = 0;
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
			while ((line = in.readLine()) != null) {
				count++;

				// System.out.println(line);
				if (count > 4) {
					try {
						StringTokenizer tokenizer = new StringTokenizer(line, ",");
						String date = tokenizer.nextToken().replace("\"", "");
						String close = tokenizer.nextToken().replace("\"", "");
						String volume = tokenizer.nextToken().replace("\"", "");
						String open = tokenizer.nextToken().replace("\"", "");
						String high = tokenizer.nextToken().replace("\"", "");
						String low = tokenizer.nextToken().replace("\"", "");

						Stock stock = new Stock();
						stock.setFixing(sdf.parse(date));
						stock.setFixingDurationMillis(24 * 60 * 60 * 1000); // 1
																			// stock
																			// time
																			// duration
																			// =
																			// 24
																			// H
																			// =
																			// thickness
																			// in
																			// chart
						stock.setClose(Double.parseDouble(close));
						stock.setOpen(Double.parseDouble(open));
						stock.setVolume(Double.parseDouble(volume));
						stock.setOpen(Double.parseDouble(open));
						stock.setLow(Double.parseDouble(low));
						stock.setHigh(Double.parseDouble(high));

						data.add(stock);

					} catch (NumberFormatException e) {
						e.printStackTrace();
					} catch (ParseException e) {
						e.printStackTrace();
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return data;
	}

	public static void main(String[] args) {
		ViewFrameUI ui = new ViewFrameUI(new StockVolumeSample());
	}

}
