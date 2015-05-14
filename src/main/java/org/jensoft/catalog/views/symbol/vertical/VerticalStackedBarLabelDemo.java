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
import org.jensoft.core.graphics.Shader;
import org.jensoft.core.palette.TexturePalette;
import org.jensoft.core.palette.color.ColorPalette;
import org.jensoft.core.palette.color.FilPalette;
import org.jensoft.core.palette.color.PetalPalette;
import org.jensoft.core.palette.color.RosePalette;
import org.jensoft.core.palette.color.TangoPalette;
import org.jensoft.core.plugin.grid.Grid.GridOrientation;
import org.jensoft.core.plugin.grid.GridPlugin;
import org.jensoft.core.plugin.grid.GridPlugin.FreeGrid;
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
import org.jensoft.core.plugin.symbol.painter.draw.BarDefaultDraw;
import org.jensoft.core.plugin.symbol.painter.effect.BarEffect1;
import org.jensoft.core.plugin.symbol.painter.fill.BarFill1;
import org.jensoft.core.plugin.symbol.painter.fill.BarFill2;
import org.jensoft.core.plugin.symbol.painter.label.BarSymbolRelativeLabel;
import org.jensoft.core.plugin.symbol.painter.label.BarSymbolRelativeLabel.HorizontalAlignment;
import org.jensoft.core.plugin.symbol.painter.label.BarSymbolRelativeLabel.VerticalAlignment;
import org.jensoft.core.plugin.translate.TranslatePlugin;
import org.jensoft.core.plugin.zoom.box.ZoomBoxPlugin;
import org.jensoft.core.plugin.zoom.wheel.ZoomWheelPlugin;
import org.jensoft.core.projection.Projection;
import org.jensoft.core.view.Portfolio;
import org.jensoft.core.view.View;
import org.jensoft.core.view.background.ViewDarkBackground;
import org.jensoft.core.view.background.ViewDefaultBackground;

@JenSoftView(background=ViewDarkBackground.class)
public class VerticalStackedBarLabelDemo extends View {

	@Portfolio(name = "VerticalStackedBarLabelDemo", width = 600, height = 400)
	public static View getPortofolio() {
		VerticalStackedBarLabelDemo demo = new VerticalStackedBarLabelDemo();

		ViewDefaultBackground viewBackground = new ViewDefaultBackground();
		Shader s = new Shader(new float[] { 0f, 1f }, new Color[] { new Color(32, 39, 55), Color.BLACK });
		viewBackground.setShader(s);
		viewBackground.setOutlineStroke(new BasicStroke(2.5f));

		demo.setBackgroundPainter(viewBackground);
		return demo;
	}

	public VerticalStackedBarLabelDemo() {
		super();

		setPlaceHolderAxisSouth(140);
		setPlaceHolderAxisWest(120);
		setPlaceHolderAxisEast(120);

		// WINDOW
		Projection proj = new Projection.Linear(0, 0, -10, 100);
		registerProjection(proj);

		/**
		 * BAR SYMBOL CREATION IN SIMPLE VIEW
		 */

		// GROUP 1

		// create group with properties
		BarSymbolGroup group1 = new BarSymbolGroup("G1");
		group1.setBase(0);
		group1.setThickness(25);
		group1.setRound(8);
		group1.setMorpheStyle(MorpheStyle.Round);
		group1.setBarDraw(new BarDefaultDraw(Color.WHITE));
		group1.setBarFill(new BarFill2());
		group1.setBarEffect(new BarEffect1());

		// create symbol 1 for group1
		StackedBarSymbol b1g1 = new StackedBarSymbol("b1g1");
		b1g1.setAscentValue(74);
		b1g1.addStack("s1", FilPalette.COPPER1, 12, new BarSymbolRelativeLabel(VerticalAlignment.Middle, HorizontalAlignment.WestLeft, Color.WHITE, FilPalette.COPPER1, Color.WHITE));
		b1g1.addStack("s2", FilPalette.COPPER2, 20, new BarSymbolRelativeLabel(VerticalAlignment.Middle, HorizontalAlignment.WestLeft, Color.WHITE, FilPalette.COPPER2, Color.WHITE));
		b1g1.addStack("s3", FilPalette.COPPER3, 40, new BarSymbolRelativeLabel(VerticalAlignment.Middle, HorizontalAlignment.WestLeft, Color.WHITE, FilPalette.COPPER3, Color.WHITE));

		// create symbol 1 for group1
		StackedBarSymbol b2g1 = new StackedBarSymbol("b2g1");
		b2g1.setAscentValue(58);
		b2g1.addStack("s1", FilPalette.COPPER1, 20, new BarSymbolRelativeLabel(VerticalAlignment.Middle, HorizontalAlignment.EastRight, Color.WHITE, FilPalette.COPPER1, Color.WHITE));
		b2g1.addStack("s2", FilPalette.COPPER2, 40, new BarSymbolRelativeLabel(VerticalAlignment.Middle, HorizontalAlignment.EastRight, Color.WHITE, FilPalette.COPPER2, Color.WHITE));
		b2g1.addStack("s3", FilPalette.COPPER3, 20, new BarSymbolRelativeLabel(VerticalAlignment.Middle, HorizontalAlignment.EastRight, Color.WHITE, FilPalette.COPPER3, Color.WHITE));

		// lay out symbol in group
		group1.addSymbol(b1g1);
		group1.addSymbol(SymbolComponent.createStrut(BarSymbol.class, 6));
		group1.addSymbol(b2g1);

		// GROUP 2 and symbols
		BarSymbolGroup group2 = new BarSymbolGroup("G2");
		group2.setBase(0);
		group2.setThickness(25);
		group2.setRound(8);
		group2.setMorpheStyle(MorpheStyle.Round);
		group2.setBarDraw(new BarDefaultDraw(Color.WHITE));
		group2.setBarFill(new BarFill1());
		group2.setBarEffect(new BarEffect1());

		StackedBarSymbol b1g2 = new StackedBarSymbol("b1g2");
		b1g2.setAscentValue(73);
		b1g2.addStack("s2", FilPalette.BLUE3, 12, new BarSymbolRelativeLabel(VerticalAlignment.Middle, HorizontalAlignment.WestLeft, Color.WHITE, FilPalette.BLUE3, Color.BLACK));
		b1g2.addStack("s2", FilPalette.BLUE2, 18, new BarSymbolRelativeLabel(VerticalAlignment.Middle, HorizontalAlignment.WestLeft, Color.WHITE, FilPalette.BLUE2, Color.BLACK));
		b1g2.addStack("s3", FilPalette.BLUE1, 26, new BarSymbolRelativeLabel(VerticalAlignment.Middle, HorizontalAlignment.WestLeft, Color.WHITE, FilPalette.BLUE1, Color.BLACK));

		StackedBarSymbol b2g2 = new StackedBarSymbol("b2g2");
		b2g2.setAscentValue(38);
		b2g2.addStack("s1", FilPalette.BLUE3, 23, new BarSymbolRelativeLabel(VerticalAlignment.Middle, HorizontalAlignment.EastRight, Color.WHITE, FilPalette.BLUE3, Color.BLACK));
		b2g2.addStack("s2", FilPalette.BLUE2, 15, new BarSymbolRelativeLabel(VerticalAlignment.Middle, HorizontalAlignment.EastRight, Color.WHITE, FilPalette.BLUE2, Color.BLACK));
		b2g2.addStack("s3", FilPalette.BLUE1, 7, new BarSymbolRelativeLabel(VerticalAlignment.Middle, HorizontalAlignment.EastRight, Color.WHITE, FilPalette.BLUE1, Color.BLACK));

		group2.addSymbol(b1g2);
		group2.addSymbol(SymbolComponent.createStrut(BarSymbol.class, 6));
		group2.addSymbol(b2g2);

		// GROUP 3
		BarSymbolGroup group3 = new BarSymbolGroup("G3");
		group3.setBase(0);
		group3.setThickness(25);
		group3.setRound(8);
		group3.setMorpheStyle(MorpheStyle.Round);
		group3.setBarDraw(new BarDefaultDraw(Color.WHITE));
		group3.setBarFill(new BarFill2());
		group3.setBarEffect(new BarEffect1());

		StackedBarSymbol b1g3 = new StackedBarSymbol("b1g3");
		b1g3.setAscentValue(63);
		b1g3.addStack("s1", TangoPalette.ORANGE1, 17);
		b1g3.addStack("s2", TangoPalette.ORANGE2, 6);
		b1g3.addStack("s3", TangoPalette.ORANGE3, 39);

		StackedBarSymbol b2g3 = new StackedBarSymbol("b2g3");
		b2g3.setAscentValue(46);
		b2g3.addStack("s1", TangoPalette.ORANGE1, 12, new BarSymbolRelativeLabel(VerticalAlignment.Middle, HorizontalAlignment.EastAcross, Color.WHITE, TangoPalette.ORANGE1, Color.BLACK));
		b2g3.addStack("s2", TangoPalette.ORANGE2, 30, new BarSymbolRelativeLabel(VerticalAlignment.Middle, HorizontalAlignment.EastAcross, Color.WHITE, TangoPalette.ORANGE2, Color.BLACK));
		b2g3.addStack("s3", TangoPalette.ORANGE3, 18, new BarSymbolRelativeLabel(VerticalAlignment.Middle, HorizontalAlignment.EastAcross, Color.WHITE, TangoPalette.ORANGE3, Color.BLACK));

		group3.addSymbol(b1g3);
		group3.addSymbol(SymbolComponent.createStrut(BarSymbol.class, 6));
		group3.addSymbol(b2g3);

		/***
		 * VIEW DECORATION
		 */

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

		FreeGrid grids = new GridPlugin.FreeGrid(GridOrientation.Horizontal);
		grids.getGridManager().addGrid(-32, "JenSoft", PetalPalette.PETAL4_HC, 0.001f);
		proj.registerPlugin(grids);

		// CREATE BAR PLUGIN
		SymbolPlugin barPlugin = new SymbolPlugin();
		// LAY OUT SYMBOL IN PLUGIN
		BarSymbolLayer barLayer = new BarSymbolLayer();
		barPlugin.addLayer(barLayer);
		barLayer.addSymbol(SymbolComponent.createGlue(BarSymbol.class));
		barLayer.addSymbol(group1);
		barLayer.addSymbol(SymbolComponent.createStrut(BarSymbol.class, 100));
		barLayer.addSymbol(group2);
		barLayer.addSymbol(SymbolComponent.createStrut(BarSymbol.class, 100));
		barLayer.addSymbol(group3);
		barLayer.addSymbol(SymbolComponent.createGlue(BarSymbol.class));

		barPlugin.setNature(SymbolNature.Vertical);
		proj.registerPlugin(barPlugin);

		// ADD LISTENER FOR CUSTOM ACTION
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

		
		Font f = new Font("Dialog", Font.PLAIN, 12);
		
		
		// AXIS ON SCALAR DIMENSION X
		AxisMetricsPlugin.ModeledMetrics westMetrics = new AxisMetricsPlugin.ModeledMetrics.W();
		proj.registerPlugin(westMetrics);
		westMetrics.setTextFont(f);
		

		// OUTLINE
		proj.registerPlugin(new OutlinePlugin());

		// LEGEND
		
		TitleLegend legend = new TitleLegend("Bar Relative Label");
		legend.setLegendFill(new TitleLegendGradientFill(Color.WHITE, FilPalette.COPPER2));
		legend.setFont(f);
		legend.setConstraints(new TitleLegendConstraints(LegendPosition.South, 0.8f, LegendAlignment.Rigth));
		TitleLegendPlugin lgendL = new TitleLegendPlugin();
		lgendL.addLegend(legend);
		proj.registerPlugin(lgendL);

		// TOOLS
		ZoomBoxPlugin zoomTool = new ZoomBoxPlugin();
		proj.registerPlugin(zoomTool);

		TranslatePlugin toolTranslate = new TranslatePlugin();
		proj.registerPlugin(toolTranslate);

		ZoomWheelPlugin zoomWheel = new ZoomWheelPlugin();
		proj.registerPlugin(zoomWheel);

	}

}
