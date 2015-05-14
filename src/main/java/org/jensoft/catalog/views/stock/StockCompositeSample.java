/*
 * JenSoft API - Charting Framework
 * http://www.jensoftapi.com
 * Copyright (c) JenSoft. All rights reserved.
 * See JenSoft Software License Agreement
 */
package org.jensoft.catalog.views.stock;

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
import org.jensoft.core.palette.color.NanoChromatique;
import org.jensoft.core.palette.color.RosePalette;
import org.jensoft.core.plugin.grid.Grid.GridOrientation;
import org.jensoft.core.plugin.grid.GridPlugin;
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
import org.jensoft.core.projection.Projection;
import org.jensoft.core.view.View;
import org.jensoft.core.view.background.ViewDarkBackground;

/**
 * <code>StockCompositeSample</code>
 * 
 * @author Sébastien Janaud
 */
@JenSoftView(background=ViewDarkBackground.class,description = "Display basic candles stick charts - stock incubator")
public class StockCompositeSample extends View {

	private static final long serialVersionUID = 4450265528566581832L;

	/**
	 * Create composite stock charts with multiple projection 
	 */
	public StockCompositeSample() {
		super();

		// set size in pixel for outer's components
		setPlaceHolderAxisSouth(80);
		setPlaceHolderAxisWest(120);
		setPlaceHolderAxisEast(120);

		createStocksProjection1();
		createStocksProjection2();

	}
	
	private void createStocksProjection2() {
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

		Projection.Linear proj = new Projection.Linear(start.getTime(), end.getTime(), 0,4*max);
		registerProjection(proj);

		proj.setThemeColor(RosePalette.LEMONPEEL);

//		// grid
//		ModeledGrid grids = new GridPlugin.ModeledGrid(GridOrientation.Horizontal);
//		grids.registerGridModels(GridModelRangeCollections.YoctoYotta);
//		grids.setGridColor(ColorPalette.alpha(TangoPalette.ALUMINIUM2, 100));
//		//grids.setGridStroke(StrokePalette.drawingStroke5);
//		proj.registerPlugin(grids);
				
		StockPlugin stockPlugin = new StockPlugin();
		proj.registerPlugin(stockPlugin);
		stockPlugin.addLayer(new StockLayer.Volume(NanoChromatique.GREEN));
		stockPlugin.setStocks(stocks);

		// legend
		Font f =  new Font("Dialog", Font.PLAIN, 12);
		TitleLegend legend = new TitleLegend("SLV - VOLUME - 2013");
		legend.setLegendFill(new TitleLegendGradientFill(NanoChromatique.GREEN, NanoChromatique.GREEN));
		legend.setFont(f);
		legend.setConstraints(new TitleLegendConstraints(LegendPosition.East, 0.2f, LegendAlignment.Rigth));
		TitleLegendPlugin legendPlugin = new TitleLegendPlugin(legend);
		proj.registerPlugin(legendPlugin);

		

		AxisMetricsPlugin.ModeledMetrics westMetrics = new AxisMetricsPlugin.ModeledMetrics.E();
		proj.registerPlugin(westMetrics);
		//westMetrics.setTextFont(InputFonts.getNeuropol(12));
		

//		AxisMetricsPlugin.TimeMetrics timingMetrics = new AxisMetricsPlugin.TimeMetrics(Axis.AxisSouth);
//		timingMetrics.setMetricsMedianFont(InputFonts.getNoMove(10));
//		timingMetrics.setMetricsMajorFont(InputFonts.getNoMove(12));
//		timingMetrics.registerTimeModel(new TimeMetricsManager.Minute1Model());
//		timingMetrics.registerTimeModel(new TimeMetricsManager.Minute15Model());
//		timingMetrics.registerTimeModel(new TimeMetricsManager.HourModel());
//		timingMetrics.registerTimeModel(new TimeMetricsManager.Hour3Model());
//		timingMetrics.registerTimeModels(new TimeMetricsManager.WeekModel());
//		timingMetrics.registerTimeModels(new TimeMetricsManager.MonthModel());
//		proj.registerPlugin(timingMetrics);

		

		// outline plug-in
		proj.registerPlugin(new OutlinePlugin());

		
		
	}

	private void createStocksProjection1(){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		Date start = null;
		Date end = null;
		try {
			start = sdf.parse("2012/12/01");
			end = sdf.parse("2014/01/01");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		// Projection.TimeX proj = new Projection.TimeX(minDate,maxDate,0, 100);
		Projection.Linear proj = new Projection.Linear(start.getTime(), end.getTime(), 12, 35);
		registerProjection(proj);

		
		StockPlugin stockPlugin =new StockPlugin();
		proj.registerPlugin(stockPlugin);
		
		stockPlugin.setStocks(loadStockData());
		
		
		stockPlugin.addLayer(new StockLayer.CandleStick());
		
		stockPlugin.addLayer(new StockLayer.BollingerBands(20,NanoChromatique.ORANGE,NanoChromatique.BLUE,NanoChromatique.BLUE));
		//stockPlugin.addLayer(new StockLayer.MovingAverageCurve(TangoPalette.SCARLETRED1.brighter(),20));
		//stockPlugin.addLayer(new StockLayer.MovingAverageCurve(TangoPalette.SCARLETRED2.brighter(),100));
		//stockPlugin.addLayer(new StockLayer.WeightedMovingAverageCurve(TangoPalette.CHAMELEON2.brighter(),100));
		//stockPlugin.addLayer(new StockLayer.ExponentialdMovingAverageCurve(TangoPalette.ORANGE3.brighter(),20));
		
		// legend
		Font f =  new Font("Dialog", Font.PLAIN, 12);
		TitleLegend legend = new TitleLegend("SLV - Bollinger Band - 2013");
		legend.setLegendFill(new TitleLegendGradientFill(NanoChromatique.BLUE, NanoChromatique.BLUE));
		legend.setFont(f);
		legend.setConstraints(new TitleLegendConstraints(LegendPosition.North, 0.1f, LegendAlignment.Rigth));
		TitleLegendPlugin legendPlugin = new TitleLegendPlugin(legend);
		proj.registerPlugin(legendPlugin);

		// grid plug-in
		GridPlugin grids = new GridPlugin.MultiplierGrid(0, 10, GridOrientation.Horizontal);
		grids.setGridColor(ColorPalette.alpha(NanoChromatique.ORANGE, 180));
		proj.registerPlugin(grids);

		AxisMetricsPlugin.ModeledMetrics westMetrics = new AxisMetricsPlugin.ModeledMetrics.W();
		proj.registerPlugin(westMetrics);
		westMetrics.setTextFont(f);
		
		Font fm =  new Font("Dialog", Font.PLAIN, 10);
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

		// outline plug-in
		proj.registerPlugin(new OutlinePlugin());

	}
	
	
	
	public static List<Stock> loadStockData() {
		List<Stock> data = new ArrayList<Stock>();
		try {
			InputStream is = StockCompositeSample.class.getResourceAsStream("slv-2013.csv");
			InputStreamReader isreader = new InputStreamReader(is);
			BufferedReader in = new BufferedReader(isreader);
			String line = null;
			int count = 0;
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
			while ((line = in.readLine()) != null) {
				count++;
				
				//System.out.println(line);
				if(count > 4){
					try {
						StringTokenizer tokenizer = new StringTokenizer(line,",");
						String date = tokenizer.nextToken().replace("\"", "");
						String close = tokenizer.nextToken().replace("\"", "");
						String volume = tokenizer.nextToken().replace("\"", "");
						String open = tokenizer.nextToken().replace("\"", "");
						String high = tokenizer.nextToken().replace("\"", "");
						String low = tokenizer.nextToken().replace("\"", "");

						Stock stock = new Stock();
						stock.setFixing(sdf.parse(date));
						stock.setFixingDurationMillis(24*60*60*1000); // 1 stock time duration =  24 H = thickness in chart
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
		ViewFrameUI ui =  new ViewFrameUI(new StockCompositeSample());
	}

}
