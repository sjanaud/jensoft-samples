/*
 * JenSoft API - Charting Framework
 * http://www.jensoftapi.com
 * Copyright (c) JenSoft. All rights reserved.
 * See JenSoft Software License Agreement
 */
package org.jensoft.catalog.views.symbol.vertical;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;

import org.jensoft.core.catalog.nature.JenSoftView;
import org.jensoft.core.catalog.ui.ViewFrameUI;
import org.jensoft.core.graphics.Shader;
import org.jensoft.core.palette.TexturePalette;
import org.jensoft.core.palette.color.ColorPalette;
import org.jensoft.core.palette.color.FilPalette;
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
import org.jensoft.core.plugin.outline.OutlinePlugin;
import org.jensoft.core.plugin.stripe.StripePlugin;
import org.jensoft.core.plugin.stripe.painter.StripePalette;
import org.jensoft.core.plugin.symbol.BarEvent;
import org.jensoft.core.plugin.symbol.BarListener;
import org.jensoft.core.plugin.symbol.BarSymbol;
import org.jensoft.core.plugin.symbol.BarSymbol.MorpheStyle;
import org.jensoft.core.plugin.symbol.BarSymbolGroup;
import org.jensoft.core.plugin.symbol.BarSymbolLayer;
import org.jensoft.core.plugin.symbol.StackedBarSymbol;
import org.jensoft.core.plugin.symbol.SymbolComponent;
import org.jensoft.core.plugin.symbol.SymbolPlugin;
import org.jensoft.core.plugin.symbol.SymbolPlugin.SymbolNature;
import org.jensoft.core.plugin.symbol.painter.axis.BarDefaultAxisLabel;
import org.jensoft.core.plugin.symbol.painter.draw.BarDefaultDraw;
import org.jensoft.core.plugin.symbol.painter.effect.BarEffect1;
import org.jensoft.core.plugin.symbol.painter.fill.BarFill1;
import org.jensoft.core.plugin.symbol.painter.fill.BarFill2;
import org.jensoft.core.plugin.translate.TranslatePlugin;
import org.jensoft.core.plugin.zoom.box.ZoomBoxPlugin;
import org.jensoft.core.plugin.zoom.wheel.ZoomWheelPlugin;
import org.jensoft.core.projection.Projection;
import org.jensoft.core.view.Portfolio;
import org.jensoft.core.view.View;
import org.jensoft.core.view.background.ViewDarkBackground;
import org.jensoft.core.view.background.ViewDefaultBackground;

@JenSoftView(background=ViewDarkBackground.class)
public class VerticalBarAxisLabelDemo extends View {

	
	public static void main(String[] args) {
		ViewFrameUI ui = new ViewFrameUI(new VerticalBarAxisLabelDemo());
		
	}
	
	@Portfolio(name = "VerticalBarAxisLabelDemo", width = 600, height = 400)
	public static View getPortofolio() {
		VerticalBarAxisLabelDemo demo = new VerticalBarAxisLabelDemo();

		ViewDefaultBackground viewBackground = new ViewDefaultBackground();
		Shader s = new Shader(new float[] { 0f, 1f }, new Color[] { new Color(32, 39, 55), Color.BLACK });
		viewBackground.setShader(s);
		viewBackground.setOutlineStroke(new BasicStroke(2.5f));

		demo.setBackgroundPainter(viewBackground);
		return demo;
	}

	public VerticalBarAxisLabelDemo() {

		super();
		setPlaceHolderAxisSouth(140);
		setPlaceHolderAxisWest(120);
		setPlaceHolderAxisEast(120);

		// projection
		Projection proj = new Projection.Linear(0, 0, -10, 90);
		registerProjection(proj);

		
		// GROUP 1
		BarSymbolGroup group1 = new BarSymbolGroup("G1");
		group1.setBase(0);
		group1.setThickness(36);
		group1.setRound(8);
		group1.setMorpheStyle(MorpheStyle.Round);
		group1.setBarDraw(new BarDefaultDraw(Color.WHITE));
		group1.setBarFill(new BarFill2());
		group1.setBarEffect(new BarEffect1());

		// symbol 1
		StackedBarSymbol b1g1 = new StackedBarSymbol("b1g1");
		b1g1.setAscentValue(74);
		b1g1.addStack("s1", FilPalette.COPPER1, 12);
		b1g1.addStack("s2", FilPalette.COPPER2, 20);
		b1g1.addStack("s3", FilPalette.COPPER3, 40);

		// symbol 2
		StackedBarSymbol b2g1 = new StackedBarSymbol("b2g1");
		b2g1.setAscentValue(58);
		b2g1.addStack("s1", FilPalette.COPPER1, 20);
		b2g1.addStack("s2", FilPalette.COPPER2, 40);
		b2g1.addStack("s3", FilPalette.COPPER3, 20);

		// lay out symbol in group 1
		group1.addSymbol(b1g1);
		group1.addSymbol(SymbolComponent.createStrut(BarSymbol.class, 6));
		group1.addSymbol(b2g1);

		/***
		 * SYMBOL AXIS LABEL GROUP 1
		 */
		Font f = new Font("Dialog", Font.PLAIN, 10);
		
		// axis label for symbol 1
		BarDefaultAxisLabel axisLabelBar1Group1 = new BarDefaultAxisLabel("b1 G1", Color.BLACK);
		axisLabelBar1Group1.setFont(f);
		axisLabelBar1Group1.setFillColor(FilPalette.COPPER1);
		axisLabelBar1Group1.setDrawColor(Color.WHITE);
		axisLabelBar1Group1.setTextColor(Color.WHITE);
		axisLabelBar1Group1.setText("Symbol 1");
		axisLabelBar1Group1.setTextPaddingY(1);
		axisLabelBar1Group1.setOffsetY(10);
		b1g1.setAxisLabel(axisLabelBar1Group1);

		// axis label for symbol 2
		BarDefaultAxisLabel axisLabelBar2Group1 = new BarDefaultAxisLabel("b2 G1", Color.BLACK);
		axisLabelBar2Group1.setFont(f);
		axisLabelBar2Group1.setFillColor(FilPalette.COPPER1);
		axisLabelBar2Group1.setDrawColor(Color.WHITE);
		axisLabelBar2Group1.setTextColor(Color.WHITE);
		axisLabelBar2Group1.setText("Symbol 2");
		axisLabelBar2Group1.setTextPaddingY(1);
		axisLabelBar2Group1.setOffsetY(30);
		b2g1.setAxisLabel(axisLabelBar2Group1);

		Font f14 = new Font("Dialog", Font.PLAIN, 14);
		
		// axis label for group
		BarDefaultAxisLabel axisLabelGroup1 = new BarDefaultAxisLabel("Group1", Color.BLACK);
		axisLabelGroup1.setDrawColor(Color.BLACK);
		axisLabelGroup1.setFillColor(FilPalette.COPPER3);
		axisLabelGroup1.setDrawColor(FilPalette.COPPER1);
		axisLabelGroup1.setTextColor(Color.WHITE);
		axisLabelGroup1.setText("Group 1");
		axisLabelGroup1.setFont(f14);
		axisLabelGroup1.setTextPaddingY(1);
		axisLabelGroup1.setOffsetY(50);
		group1.setAxisLabel(axisLabelGroup1);

		/**
		 * GROUP 2
		 */
		BarSymbolGroup group2 = new BarSymbolGroup("Group 2");
		group2.setBase(0);
		group2.setThickness(36);
		group2.setRound(8);
		group2.setMorpheStyle(MorpheStyle.Round);
		group2.setBarDraw(new BarDefaultDraw(Color.WHITE));
		group2.setBarFill(new BarFill1());
		group2.setBarEffect(new BarEffect1());

		// symbol 1 group 2
		StackedBarSymbol b1g2 = new StackedBarSymbol("b1g2");
		b1g2.setAscentValue(73);
		b1g2.addStack("s2", FilPalette.BLUE3, 12);
		b1g2.addStack("s2", FilPalette.BLUE2, 18);
		b1g2.addStack("s3", FilPalette.BLUE1, 26);

		// symbol 2 group 2
		StackedBarSymbol b2g2 = new StackedBarSymbol("b2g2");
		b2g2.setAscentValue(38);
		b2g2.addStack("s1", FilPalette.BLUE3, 23);
		b2g2.addStack("s2", FilPalette.BLUE2, 15);
		b2g2.addStack("s3", FilPalette.BLUE1, 7);

		// lay out symbol in group 2
		group2.addSymbol(b1g2);
		group2.addSymbol(SymbolComponent.createStrut(BarSymbol.class, 6));
		group2.addSymbol(b2g2);

		/***
		 * SYMBOL AXIS LABEL GROUP 2
		 */

		// axis label for symbol 1
		BarDefaultAxisLabel axisLabelBar1Group2 = new BarDefaultAxisLabel("b1 G2", Color.BLACK);
		axisLabelBar1Group2.setFont(f);
		axisLabelBar1Group2.setFillColor(FilPalette.BLUE3);
		axisLabelBar1Group2.setDrawColor(Color.WHITE);
		axisLabelBar1Group2.setTextColor(Color.WHITE);
		axisLabelBar1Group2.setText("Symbol 1");
		axisLabelBar1Group2.setTextPaddingY(1);
		axisLabelBar1Group2.setOffsetY(10);
		b1g2.setAxisLabel(axisLabelBar1Group2);

		// axis label for symbol 2
		BarDefaultAxisLabel axisLabelBar2Group2 = new BarDefaultAxisLabel("b2 G2", Color.BLACK);
		axisLabelBar2Group2.setFont(f);
		axisLabelBar2Group2.setFillColor(FilPalette.BLUE3);
		axisLabelBar2Group2.setDrawColor(Color.WHITE);
		axisLabelBar2Group2.setTextColor(Color.WHITE);
		axisLabelBar2Group2.setText("Symbol 2");
		axisLabelBar2Group2.setTextPaddingY(1);
		axisLabelBar2Group2.setOffsetY(30);
		b2g2.setAxisLabel(axisLabelBar2Group2);

		// axis label for group
		BarDefaultAxisLabel axisLabelGroup2 = new BarDefaultAxisLabel("Group2", Color.BLACK);
		axisLabelGroup2.setDrawColor(Color.BLACK);
		axisLabelGroup2.setFillColor(FilPalette.BLUE1);
		axisLabelGroup2.setDrawColor(FilPalette.BLUE3);
		axisLabelGroup2.setTextColor(Color.WHITE);
		axisLabelGroup2.setText("Group 2");
		axisLabelGroup2.setFont(f14);
		axisLabelGroup2.setTextPaddingY(1);
		axisLabelGroup2.setOffsetY(50);
		group2.setAxisLabel(axisLabelGroup2);

		// CREATE BAR PLUGIN AND SYMBOL LAY OUT
		SymbolPlugin barPlugin = new SymbolPlugin();
		barPlugin.setNature(SymbolNature.Vertical);

		BarSymbolLayer barLayer = new BarSymbolLayer();
		barPlugin.addLayer(barLayer);

		barLayer.addSymbol(SymbolComponent.createGlue(BarSymbol.class));
		barLayer.addSymbol(group1);
		barLayer.addSymbol(SymbolComponent.createStrut(BarSymbol.class, 100));
		barLayer.addSymbol(group2);
		barLayer.addSymbol(SymbolComponent.createGlue(BarSymbol.class));

		proj.registerPlugin(barPlugin);

		// affect a priority to bar plugin layer (paint as the last layer)
		barPlugin.setPriority(100);

		StripePlugin stripePlugin = new StripePlugin.MultiplierStripe.H(-30, 20);
		StripePalette bp = new StripePalette();
		float[] fractions = { 0f, 0.5f, 1f };
		Color c = RosePalette.AMETHYST;
		Color[] colors = { ColorPalette.alpha(c, 200), ColorPalette.alpha(c, 80), ColorPalette.alpha(c, 200) };
		bp.addPaint(fractions, colors);
		bp.addPaint(TexturePalette.getTriangleCarbonFiber());
		stripePlugin.setStripePalette(bp);
		stripePlugin.setAlpha(0.1f);
		proj.registerPlugin(stripePlugin);

		GridPlugin gridLayout = new GridPlugin.MultiplierGrid(-30, 20, GridOrientation.Horizontal);
		gridLayout.setGridColor(new Color(255, 255, 255, 60));
		proj.registerPlugin(gridLayout);

		barLayer.addBarListener(new BarListener() {

			@Override
			public void barSymbolReleased(BarEvent e) {
				System.out.println("bar released : " + e.getBarSymbol().getName());

			}

			@Override
			public void barSymbolPressed(BarEvent e) {
				System.out.println("bar pressed : " + e.getBarSymbol().getName());

			}

			@Override
			public void barSymbolExited(BarEvent e) {
				System.out.println("bar exited : " + e.getBarSymbol().getName());

			}

			@Override
			public void barSymbolEntered(BarEvent e) {
				System.out.println("bar entered : " + e.getBarSymbol().getName());

			}

			@Override
			public void barSymbolClicked(BarEvent e) {
				System.out.println("bar clicked : " + e.getBarSymbol().getName());

			}
		});

		// METRICS
		AxisMetricsPlugin.ModeledMetrics westMetrics = new AxisMetricsPlugin.ModeledMetrics.W();
		proj.registerPlugin(westMetrics);
		westMetrics.setTextFont(f);
		

		// OUTLINE
		proj.registerPlugin(new OutlinePlugin());

		// LEGEND
		TitleLegend legend = new TitleLegend("Bar Axis SymbolLabel");
		legend.setLegendFill(new TitleLegendGradientFill(Color.WHITE, FilPalette.COPPER2));
		legend.setFont(f);
		legend.setConstraints(new TitleLegendConstraints(LegendPosition.South, 0.8f, LegendAlignment.Rigth));
		TitleLegendPlugin legendPlg = new TitleLegendPlugin();
		legendPlg.addLegend(legend);
		proj.registerPlugin(legendPlg);

		// TOOLS
		ZoomBoxPlugin zoomTool = new ZoomBoxPlugin();
		proj.registerPlugin(zoomTool);

		TranslatePlugin toolTranslate = new TranslatePlugin();
		proj.registerPlugin(toolTranslate);

		ZoomWheelPlugin zoomWheel = new ZoomWheelPlugin();
		proj.registerPlugin(zoomWheel);

	}

}
